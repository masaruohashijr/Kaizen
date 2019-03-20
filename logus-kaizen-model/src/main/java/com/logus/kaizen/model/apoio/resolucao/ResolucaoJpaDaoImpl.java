/**
 *
 */
package com.logus.kaizen.model.apoio.resolucao;

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
public class ResolucaoJpaDaoImpl extends AbstractJpaDao<Resolucao> implements ResolucaoDao {

	/**
	 * @param emf
	 */
	public ResolucaoJpaDaoImpl() {
		super(ApoioDataServiceImpl.getEMF());
	}

	private static ResolucaoJpaDaoImpl instance;

	public static ResolucaoJpaDaoImpl getInstance() {
		if (instance == null) {
			instance = new ResolucaoJpaDaoImpl();
		}
		return instance;
	}

	public Collection<Resolucao> loadResolucoes() {
		return loadCollectionByFilter("flg_ativo", Boolean.TRUE);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#assignTo(java.lang.
	 * Object, java.lang.Object)
	 */
	@Override
	public void assignTo(Resolucao source, Resolucao dest) {
		dest.assignFrom(source);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#getTableName()
	 */
	@Override
	public String getTableName() {
		return TableNames.TB_RESOLUCAO;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#updateEnabled()
	 */
	@Override
	protected boolean updateEnabled() {
		return LoginManager.getAccessControl().hasAccess(FuncionalidadeResolucao.FUNC_RESOLUCAO_CADASTRAR.getId());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#queryEnabled()
	 */
	@Override
	protected boolean queryEnabled() {
		return LoginManager.getAccessControl().hasAccess(FuncionalidadeResolucao.FUNC_RESOLUCAO_CONSULTAR.getId());
	}

}
