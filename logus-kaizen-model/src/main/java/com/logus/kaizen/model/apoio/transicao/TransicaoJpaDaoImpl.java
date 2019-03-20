/**
 *
 */
package com.logus.kaizen.model.apoio.transicao;

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
		return loadCollection("*",TableNames.TB_TRANSICAO,"flg_ativo = ?", "nom_transicao asc", Boolean.TRUE);
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
		return TableNames.TB_TRANSICAO;
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
