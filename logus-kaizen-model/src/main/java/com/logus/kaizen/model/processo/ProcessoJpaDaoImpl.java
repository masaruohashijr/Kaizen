/**
 *
 */
package com.logus.kaizen.model.processo;

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
public class ProcessoJpaDaoImpl extends AbstractJpaDao<Processo> implements ProcessoDao {

	/**
	 * @param emf
	 */
	public ProcessoJpaDaoImpl() {
		super(ApoioDataServiceImpl.getEMF());
	}

	private static ProcessoJpaDaoImpl instance;

	public static ProcessoJpaDaoImpl getInstance() {
		if (instance == null) {
			instance = new ProcessoJpaDaoImpl();
		}
		return instance;
	}

	public Collection<Processo> loadProcessos() {
		return loadCollectionByFilter("flg_ativo", Boolean.TRUE);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#assignTo(java.lang.
	 * Object, java.lang.Object)
	 */
	@Override
	public void assignTo(Processo source, Processo dest) {
		dest.assignFrom(source);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#getTableName()
	 */
	@Override
	public String getTableName() {
		return Processo.TABLE_NAME;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#updateEnabled()
	 */
	@Override
	protected boolean updateEnabled() {
		return LoginManager.getAccessControl().hasAccess(FuncionalidadeProcesso.FUNC_PROCESSO_CADASTRAR.getId());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#queryEnabled()
	 */
	@Override
	protected boolean queryEnabled() {
		return LoginManager.getAccessControl().hasAccess(FuncionalidadeProcesso.FUNC_PROCESSO_CONSULTAR.getId());
	}

}
