package com.logus.kaizen.model.persistence.jpa;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.OptimisticLockException;
import javax.persistence.Query;

import com.logus.core.model.persistence.DataAccessObject;
import com.logus.core.model.persistence.PersistenceException;
import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.model.util.Strings;

/**
 * Ponto de partida para camada de persistência de um objeto baseada em JPA.
 *
 * @author Walace Zloccowick Maia
 * @param <T> Tipo do objeto que será persistido.
 */
public abstract class AbstractKaizenJpaDao<T extends Object> implements DataAccessObject<T> {

	/**
	 * Entity Manager Factory.
	 */
	private EntityManagerFactory emf;

	/**
	 * @return {@link #emf}
	 */
	public EntityManagerFactory getEMF() {
		return emf;
	}

	/**
	 * @param emf inicializa {@link #emf}.
	 */
	public AbstractKaizenJpaDao(EntityManagerFactory emf) {
		super();
		this.emf = emf;
	}

	/**
	 * Atribui todos os atributos de um objeto origem a outro, para fins de
	 * persistência.
	 *
	 * @param source objeto cujos atributos serão fornecidos.
	 * @param dest   objeto cujos atributos serão atualizados.
	 */
	public abstract void assignTo(T source, T dest);

	@Override
	public void delete(T object) {
		deleteByKey(keyOf(object));
	}

	/**
	 * @return o tipo genérico da classe.
	 */
	@SuppressWarnings({ "unchecked" })
	protected Class<? super T> getType() {
		return (Class<? super T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteByKey(Object key) {
		if (!deleteEnabled()) {
			throw new PersistenceException(TM.translate(CoreTranslator.JPA_DAO_DELETE_NOT_ALLOWED));
		}

		EntityManager em = getEMF().createEntityManager();
		T persisted = null;
		try {
			em.getTransaction().begin();
			persisted = (T) em.find(getType(), key);
			em.remove(persisted);
			onDelete(em, persisted);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw new PersistenceException(TM.translate(CoreTranslator.JPA_DAO_DELETE_CONSTRAINT_VIOLATION,
					persisted == null ? "" : persisted.toString()), e);
		} finally {
			em.close();
		}
	}

	/**
	 * @return o nome da tabela onde o objeto se encontra.
	 */
	public abstract String getTableName();

	@Override
	public void insert(T object) {
		if (!updateEnabled()) {
			throw new PersistenceException(TM.translate(CoreTranslator.JPA_DAO_INSERT_NOT_ALLOWED));
		}

		PersistenceException ex = new PersistenceException();
		validate(object, ex);
		validateInsert(object, ex);
		if (!ex.hasErrors()) {
			EntityManager em = getEMF().createEntityManager();
			try {
				em.getTransaction().begin();
				em.persist(object);
				onInsert(em, object);
				em.getTransaction().commit();
			} catch (Exception e) {
				ex.initCause(e);
				ex.appendException(e);
			} finally {
				em.close();
			}
		}
		if (ex.getErrors().size() > 0) {
			throw ex;
		}
	}

	/**
	 * Retorna a chave de um objeto.
	 *
	 * @param object objeto cuja chave será obtida.
	 * @return a chave do objeto.
	 */
	public Object keyOf(Object object) {
		return getEMF().getPersistenceUnitUtil().getIdentifier(object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T load(Object key) {
		EntityManager em = getEMF().createEntityManager();
		try {
			return (T) em.find(getType(), key);
		} finally {
			em.close();
		}
	}

	@Override
	public Collection<T> loadAll() {
		return loadCollectionByFilter(null);
	}

	@Override
	public Collection<T> loadAllAndFilter(Filter<T> filter) {
		Collection<T> all = loadAll();
		Iterator<T> it = all.iterator();
		while (it.hasNext()) {
			if (!filter.accept(it.next())) {
				it.remove();
			}
		}
		return all;
	}

	@Override
	public Collection<T> loadByFilterAndFilter(Filter<T> filter, String sqlFilter, Object... parameters) {
		Collection<T> all = loadCollectionByFilter(sqlFilter, parameters);
		if (filter != null) {
			Iterator<T> it = all.iterator();
			while (it.hasNext()) {
				if (!filter.accept(it.next())) {
					it.remove();
				}
			}
		}
		return all;
	}

	/**
	 * Carrega um objeto restrito por um filtro. Se vários objetos satisfizerem o
	 * critério de filtro, o primeiro será retornado. Se nenhum objeto satisfizer o
	 * filtro, {@code null} será retornado.
	 *
	 * @param filter     filtro que restringirá o objeto a ser carregado.
	 * @param parameters parâmetros para bind do filtro.
	 * @return o objeto carregado.
	 */
	public T loadByFilter(String filter, Object... parameters) {
		Collection<T> objects = loadCollectionByFilter(filter, parameters);
		if (objects.size() > 0) {
			return objects.iterator().next();
		}
		return null;
	}

	/**
	 * @return tabelas e respectivos joins a serem inseridos na cláusula from da
	 *         consulta.
	 */
	protected String getJoinClause() {
		return getTableName();
	}

	/**
	 * Carrega uma coleção de objetos restritos por um filtro informado e parâmetros
	 * demandados por este filtro. O {@link #getStartFilter()} será acrescido também
	 * ao filtro.
	 *
	 * @param filter     filtro que restringirá o objeto a ser carregado.
	 * @param parameters parâmetros para bind do filtro.
	 * @return objetos que satisfazem os critérios de filtro carregados.
	 */
	public Collection<T> loadCollectionByFilter(String filter, Object... parameters) {
		return loadCollectionInternal("*", getJoinClause(), filter, null, true, parameters);
	}

	/**
	 * Carrega uma coleção de objetos restritos por um filtro informado e parâmetros
	 * demandados por este filtro.
	 *
	 * @param filter     filtro que restringirá o objeto a ser carregado.
	 * @param parameters parâmetros para bind do filtro.
	 * @return objetos que satisfazem os critérios de filtro carregados.
	 */
	public Collection<T> loadCollectionByFilterWithoutStartFilter(String filter, Object... parameters) {
		return loadCollectionInternal("*", getJoinClause(), filter, null, false, parameters);
	}

	/**
	 * Carrega uma coleção de objetos com base nas componentes da cláusula SQL.
	 * Considera o filtro inicial definido por {@link #getStartFilter()} e
	 * {@link #getStartParameters()}.
	 *
	 * @param columns    colunas a serem carregadas.
	 * @param tables     tabelas a serem inseridas na cláusula from.
	 * @param filter     filtro que restringirá o objeto a ser carregado a ser
	 *                   acrescido na cláusula where.
	 * @param orderBy    ordenação da consulta.
	 * @param parameters parâmetros para bind do filtro.
	 * @return a coleção dos objetos carregados.
	 */
	protected Collection<T> loadCollection(String columns, String tables, String filter, String orderBy,
			Object... parameters) {
		return loadCollectionInternal(columns, tables, filter, orderBy, true, parameters);
	}

	/**
	 * Carrega uma coleção de objetos com base nas componentes da cláusula SQL. Não
	 * considera o filtro inicial definido por {@link #getStartFilter()} e
	 * {@link #getStartParameters()}.
	 *
	 * @param columns    colunas a serem carregadas.
	 * @param tables     tabelas a serem inseridas na cláusula from.
	 * @param filter     filtro que restringirá o objeto a ser carregado a ser
	 *                   acrescido na cláusula where.
	 * @param orderBy    ordenação da consulta.
	 * @param parameters parâmetros para bind do filtro.
	 * @return a coleção dos objetos carregados.
	 */
	protected Collection<T> loadCollectionWithoutStartFilter(String columns, String tables, String filter,
			String orderBy, Object... parameters) {
		return loadCollectionInternal(columns, tables, filter, orderBy, false, parameters);
	}

	/**
	 * Carrega uma coleção de objetos com base nas componentes da cláusula SQL.
	 * Considera o filtro inicial definido por {@link #getStartFilter()} e
	 * {@link #getStartParameters()}.
	 *
	 * @param columns        colunas a serem carregadas.
	 * @param tables         tabelas a serem inseridas na cláusula from.
	 * @param filter         filtro que restringirá o objeto a ser carregado a ser
	 *                       acrescido na cláusula where.
	 * @param orderBy        ordenação da consulta.
	 * @param useStartFilter {@code true} para acrescer o {@link #getStartFilter()}
	 *                       na cláusula where.
	 * @param parameters     parâmetros para bind do filtro.
	 * @return a coleção dos objetos carregados.
	 */
	private Collection<T> loadCollectionInternal(String columns, String tables, String filter, String orderBy,
			boolean useStartFilter, Object... parameters) {
		if (!queryEnabled()) {
			throw new PersistenceException(TM.translate(CoreTranslator.JPA_DAO_QUERY_NOT_ALLOWED));
		}

		EntityManager em = getEMF().createEntityManager();
		try {
			// Soma todos os parâmetros necessários à execução da consulta.
			List<?> startParameters = getStartParameters();
			int length = useStartFilter ? parameters.length + startParameters.size() : parameters.length;
			Object[] allParameters = new Object[length];
			int i = 0;
			if (useStartFilter) {
				for (Object parameter : startParameters) {
					allParameters[i++] = parameter;
				}
			}
			for (Object parameter : parameters) {
				allParameters[i++] = parameter;
			}

			String sql = " select " + columns.toUpperCase();
			sql += " from " + tables;
			String allFilter = filter;
			if (useStartFilter) {
				allFilter = appendFilter(getStartFilter(), allFilter);
			}
			if (!Strings.isEmpty(allFilter)) {
				sql += " where " + allFilter;
			}
			if (!Strings.isEmpty(orderBy)) {
				sql += " order by " + orderBy;
			}
			return loadCollectionBySql(sql, allParameters);
		} finally {
			em.close();
		}
	}

	/**
	 * Carrega uma coleção de objetos a partir de uma cláusula SQL.
	 *
	 * @param sql        cláusula SQL para a qual os objetos serão retornados.
	 * @param parameters parâmetros para bind do filtro.
	 * @return o objeto carregado.
	 */
	@SuppressWarnings("unchecked")
	public Collection<T> loadCollectionBySql(String sql, Object... parameters) {
		EntityManager em = getEMF().createEntityManager();
		try {
			Query query = em.createNativeQuery(sql, getType());
			int i = 1;
			for (Object parameter : parameters) {
				query.setParameter(i++, parameter);
			}
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	/**
	 * Soma dois filtros com o operador AND
	 *
	 * @param filter1 primeiro filtro.
	 * @param filter2 segundo filtro.
	 * @return o resultando da soma.
	 */
	protected String appendFilter(String filter1, String filter2) {
		if (filter1 == null || filter1.trim().length() == 0) {
			return filter2;
		}
		if (filter2 == null || filter2.trim().length() == 0) {
			return filter1;
		}
		return ("(" + filter1 + ") and (" + filter2 + ")");
	}

	/**
	 * Filtro inicial ao qual o {@link DataAccessObject} será submetido,
	 * independente daqueles informados pela camada de apresentação.
	 *
	 * @return o filtro inicial.
	 */
	protected String getStartFilter() {
		return null;
	}

	/**
	 * @return lista de parâmetros para o filtro inicial.
	 * @see #getStartFilter()
	 */
	protected List<?> getStartParameters() {
		return Collections.emptyList();
	}

	@Override
	public void update(T object) {
		if (!updateEnabled()) {
			throw new PersistenceException(TM.translate(CoreTranslator.JPA_DAO_UPDATE_NOT_ALLOWED));
		}

		PersistenceException ex = new PersistenceException();
		validate(object, ex);
		validateUpdate(object, ex);
		if (!ex.hasErrors()) {
			EntityManager em = getEMF().createEntityManager();
			try {
				em.getTransaction().begin();
				@SuppressWarnings("unchecked")
				T persisted = (T) em.find(getType(), keyOf(object));
				@SuppressWarnings("unchecked")
				T oldObject = (T) getType().newInstance();
				assignTo(persisted, oldObject);
				assignTo(object, persisted);
				onUpdate(em, oldObject, persisted);
				em.getTransaction().commit();
				// Garante que alterações realizadas no objeto ao persistí-lo
				// (sequence, por exemplo) se reflitam no objeto informado.
				assignTo(persisted, object);
			} catch (Exception e) {
				if (e.getCause() instanceof OptimisticLockException) {
					ex.addError(
							String.format(TM.translate(CoreTranslator.JPA_DAO_CONCURRENT_MODIFICATION_ERROR, object),
									object.toString()));
				} else {
					ex.appendException(e);
				}
				ex.initCause(e);

			} finally {
				em.close();
			}
		}
		if (ex.hasErrors()) {
			throw ex;
		}
	}

	@Override
	public void update(EntityManager em, T object) {
		if (!updateEnabled()) {
			throw new PersistenceException(TM.translate(CoreTranslator.JPA_DAO_UPDATE_NOT_ALLOWED));
		}

		PersistenceException ex = new PersistenceException();
		try {
			validate(object, ex);
			validateUpdate(object, ex);
			if (!ex.hasErrors()) {
				@SuppressWarnings("unchecked")
				T persisted = (T) em.find(getType(), keyOf(object));
				@SuppressWarnings("unchecked")
				T oldObject = (T) getType().newInstance();
				assignTo(persisted, oldObject);
				assignTo(object, persisted);
				onUpdate(em, oldObject, persisted);
				// Garante que alterações realizadas no objeto ao persistí-lo
				// (sequence, por exemplo) se reflitam no objeto informado.
				assignTo(persisted, object);
			}
		} catch (Exception e) {
			ex.initCause(e);
			ex.appendException(e);
		}
		if (ex.hasErrors()) {
			throw ex;
		}
	}

	@Override
	public void insert(EntityManager em, T object) {
		if (!insertEnabled()) {
			throw new PersistenceException(TM.translate(CoreTranslator.JPA_DAO_QUERY_NOT_ALLOWED));
		}
		PersistenceException ex = new PersistenceException();
		validate(object, ex);
		validateInsert(object, ex);
		if (!ex.hasErrors()) {
			try {
				em.persist(object);
				onInsert(em, object);
			} catch (Exception e) {
				ex.initCause(e);
				ex.appendException(e);
			}
		}
		if (ex.getErrors().size() > 0) {
			throw ex;
		}
	}

	/**
	 * Executado dentro da transação de atualização do objeto, permitindo a execução
	 * de uma outra atualização no banco de dados na mesma transação.
	 *
	 * @param em        EntityManager que tem a transação aberta.
	 * @param oldObject versão persistida do objeto que está sendo atualizado.
	 * @param newObject nova versão do objeto que está sendo atualizado.
	 */
	protected void onUpdate(EntityManager em, T oldObject, T newObject) {
		// opcional
	}

	/**
	 * Executado dentro da transação de inclusão do objeto, permitindo a execução de
	 * uma outra atualização no banco de dados na mesma transação.
	 *
	 * @param em     EntityManager que tem a transação aberta.
	 * @param object objeto que está sendo inserido.
	 */
	protected void onInsert(EntityManager em, T object) {
		// opcional
	}

	/**
	 * Executado dentro da transação de exclusão do objeto, permitindo a execução de
	 * uma outra atualização no banco de dados na mesma transação.
	 *
	 * @param em     EntityManager que tem a transação aberta.
	 * @param object objeto que está sendo removido.
	 */
	protected void onDelete(EntityManager em, T object) {
		// opcional
	}

	/**
	 * Realiza a validação de um objeto para persistência, acrescendo os erros
	 * encontrados à Exception informada.
	 *
	 * @param object objeto que será validado.
	 * @param e      exceção que receberá os erros.
	 */
	protected void validate(T object, PersistenceException e) {
	}

	/**
	 * Realiza as validações de um objeto específicas para inserção, acrescendo os
	 * erros encontrados à Exception informada.
	 *
	 * @param object objeto que será validado.
	 * @param e      exceção que receberá os erros.
	 */
	protected void validateInsert(T object, PersistenceException e) {
	}

	/**
	 * Realiza as validações de um objeto específicas para alteração, acrescendo os
	 * erros encontrados à Exception informada.
	 *
	 * @param object objeto que será validado.
	 * @param e      exceção que receberá os erros.
	 */
	protected void validateUpdate(T object, PersistenceException e) {
	}

	// ----------------------------------------------------------------------------
	// CONTROLE DE ACESSO
	// ----------------------------------------------------------------------------

	/**
	 * Determina se é possível atualizar os registros deste DAO.
	 *
	 * @return {@code true} caso seja possível, do contrário {@code false}.
	 */
	protected abstract boolean updateEnabled();

	/**
	 * Determina se é possível consultar os registros deste DAO.
	 *
	 * @return {@code true} caso seja possível, do contrário {@code false}.
	 */
	protected abstract boolean queryEnabled();

	/**
	 * Determina se é possível incluir registros neste DAO.
	 *
	 * @return {@code true} caso seja possível, do contrário {@code false}.
	 */

	protected boolean insertEnabled() {
		return updateEnabled();
	}

	/**
	 * Determina se é possível excluir os registros deste DAO.
	 *
	 * @return {@code true} caso seja possível, do contrário {@code false}.
	 */
	protected boolean deleteEnabled() {
		return updateEnabled();
	}

}
