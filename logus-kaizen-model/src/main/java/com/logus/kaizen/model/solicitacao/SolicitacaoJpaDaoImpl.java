/**
 *
 */
package com.logus.kaizen.model.solicitacao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;

import com.logus.core.model.aut.login.LoginManager;
import com.logus.core.model.persistence.PersistenceException;
import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.kaizen.model.TableNames;
import com.logus.kaizen.model.apoio.ApoioDataService;
import com.logus.kaizen.model.apoio.ApoioDataServiceImpl;
import com.logus.kaizen.model.apoio.projeto.Projeto;
import com.logus.kaizen.model.persistence.jpa.AbstractKaizenJpaDao;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class SolicitacaoJpaDaoImpl extends AbstractKaizenJpaDao<Solicitacao> implements SolicitacaoDao {

	private int tentativas;

	/**
	 * @param emf
	 */
	public SolicitacaoJpaDaoImpl() {
		super(ApoioDataServiceImpl.getEMF());
		tentativas = 0;
	}

	private static SolicitacaoJpaDaoImpl instance;

	public static SolicitacaoJpaDaoImpl getInstance() {
		if (instance == null) {
			instance = new SolicitacaoJpaDaoImpl();
		}
		return instance;
	}

	@Override
	public void insert(Solicitacao solicitacao) {
		if (!updateEnabled()) {
			throw new PersistenceException(TM.translate(CoreTranslator.JPA_DAO_INSERT_NOT_ALLOWED));
		}

		PersistenceException ex = new PersistenceException();
		validate(solicitacao, ex);
		validateInsert(solicitacao, ex);
		if (!ex.hasErrors()) {
			EntityManager em = getEMF().createEntityManager();
			Projeto projeto = solicitacao.getProjeto();
			try {
				em.getTransaction().begin();
				// Recupera o seq_mondai em projeto.
				if (null != projeto) {
					long idMondai = projeto.getIdMondai();
					solicitacao.setIdMondai(++ idMondai);
				}
				em.persist(solicitacao);
				onInsert(em, solicitacao);
				em.getTransaction().commit();
			} catch (Exception e) {
				ex.initCause(e);
				if (e.getCause() instanceof OptimisticLockException && tentativas < 3) {
					Projeto persistedProject = em.find(Projeto.class, projeto.getId());
					solicitacao.setProjeto(persistedProject);
					long idMondai = projeto.getIdMondai();
					solicitacao.setIdMondai(++idMondai);
					insert(solicitacao);
					tentativas ++;
				} else {
					ex.addError("Número de tentativas de persistência superior a 3 vezes. Tente novamente mais tarde.");
					ex.appendException(e);
					tentativas = 0;
				}
				em.getTransaction().rollback();
			} finally {
				em.close();
			}
		}
		if (ex.getErrors().size() > 0) {
			throw ex;
		}
	}

	@Override
	protected void onInsert(EntityManager em, Solicitacao object) {
		object.getProjeto().setIdMondai(object.getIdMondai());
		ApoioDataService.get().getProjetoDao().update(object.getProjeto());
	}

	@Override
	public Collection<Solicitacao> loadSolicitacoes() {
//		return loadCollectionByFilter("flg_ativo = ?", Boolean.TRUE);
		return loadCollection("*", TableNames.TB_SOLICITACAO, "flg_ativo = ?", "dat_solicitacao desc", Boolean.TRUE);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#assignTo(java.lang.
	 * Object, java.lang.Object)
	 */
	@Override
	public void assignTo(Solicitacao source, Solicitacao dest) {
		dest.assignFrom(source);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#getTableName()
	 */
	@Override
	public String getTableName() {
		return TableNames.TB_SOLICITACAO;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#updateEnabled()
	 */
	@Override
	protected boolean updateEnabled() {
		return LoginManager.getAccessControl().hasAccess(FuncionalidadeSolicitacao.FUNC_SOLICITACAO_CADASTRAR.getId());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#queryEnabled()
	 */
	@Override
	protected boolean queryEnabled() {
		return LoginManager.getAccessControl().hasAccess(FuncionalidadeSolicitacao.FUNC_SOLICITACAO_CONSULTAR.getId());
	}

}
