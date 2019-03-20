/**
 *
 */
package com.logus.kaizen.model.apoio.funcao;

import java.util.Collection;

import com.logus.core.model.aut.login.LoginManager;
import com.logus.core.model.persistence.jpa.AbstractJpaDao;
import com.logus.kaizen.model.TableNames;
import com.logus.kaizen.model.apoio.ApoioDataServiceImpl;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class ItemFuncaoJpaDaoImpl extends AbstractJpaDao<ItemFuncao> implements ItemFuncaoDao {

	/**
	 * @param emf
	 */
	public ItemFuncaoJpaDaoImpl() {
		super(ApoioDataServiceImpl.getEMF());
	}

	private static ItemFuncaoJpaDaoImpl instance;

	public static ItemFuncaoJpaDaoImpl getInstance() {
		if (instance == null) {
			instance = new ItemFuncaoJpaDaoImpl();
		}
		return instance;
	}

	@Override
	public Collection<ItemFuncao> loadItensFuncoesPorUsuario(String codigoUsuario) {
		return loadCollectionByFilter("cod_usuario = ?", codigoUsuario);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#assignTo(java.lang.
	 * Object, java.lang.Object)
	 */
	@Override
	public void assignTo(ItemFuncao source, ItemFuncao dest) {
		dest.assignFrom(source);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#getTableName()
	 */
	@Override
	public String getTableName() {
		return TableNames.TB_ITEM_FUNCAO;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#updateEnabled()
	 */
	@Override
	protected boolean updateEnabled() {
		return LoginManager.getAccessControl().hasAccess(FuncionalidadeFuncao.FUNC_FUNCAO_CADASTRAR.getId());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#queryEnabled()
	 */
	@Override
	protected boolean queryEnabled() {
		return LoginManager.getAccessControl().hasAccess(FuncionalidadeFuncao.FUNC_FUNCAO_CONSULTAR.getId());
	}

}
