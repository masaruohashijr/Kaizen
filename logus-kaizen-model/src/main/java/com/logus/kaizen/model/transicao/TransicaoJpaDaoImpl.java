/**
 *
 */
package com.logus.kaizen.model.transicao;

import java.util.Collection;

import com.logus.kaizen.model.apoio.ApoioDataServiceImpl;
import com.logus.core.model.aut.login.LoginManager;
import com.logus.core.model.persistence.jpa.AbstractJpaDao;
/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class TransicaoJpaDaoImpl extends AbstractJpaDao<Transicao> implements TransicaoDao {

	/**
	 * @param emf
	 */
	public TransicaoJpaDaoImpl() {
		super(ApoioDataServiceImpl.getEMF());
	}

	private static TransicaoJpaDaoImpl instance;

	public static TransicaoJpaDaoImpl getInstance() {
		if (instance == null) {
			instance = new TransicaoJpaDaoImpl();
		}
		return instance;
	}

	public Collection<Transicao> loadTransicoes() {
		return loadCollectionByFilter("flg_ativo", Boolean.TRUE);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#assignTo(java.lang.
	 * Object, java.lang.Object)
	 */
	@Override
	public void assignTo(Transicao source, Transicao dest) {
		dest.assignFrom(source);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#getTableName()
	 */
	@Override
	public String getTableName() {
		return Transicao.TABLE_NAME;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#updateEnabled()
	 */
	@Override
	protected boolean updateEnabled() {
		return LoginManager.getAccessControl().hasAccess(FuncionalidadeTransicao.FUNC_TRANSICAO_CADASTRAR.getId());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#queryEnabled()
	 */
	@Override
	protected boolean queryEnabled() {
		return LoginManager.getAccessControl().hasAccess(FuncionalidadeTransicao.FUNC_TRANSICAO_CONSULTAR.getId());
	}

}
