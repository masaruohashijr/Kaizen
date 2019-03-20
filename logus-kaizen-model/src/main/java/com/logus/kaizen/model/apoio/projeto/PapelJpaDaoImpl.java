/**
 *
 */
package com.logus.kaizen.model.apoio.projeto;

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
public class PapelJpaDaoImpl extends AbstractJpaDao<Papel> implements PapelDao {

	/**
	 * @param emf
	 */
	public PapelJpaDaoImpl() {
		super(ApoioDataServiceImpl.getEMF());
	}

	private static PapelJpaDaoImpl instance;

	public static PapelJpaDaoImpl getInstance() {
		if (instance == null) {
			instance = new PapelJpaDaoImpl();
		}
		return instance;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#assignTo(java.lang.
	 * Object, java.lang.Object)
	 */
	@Override
	public void assignTo(Papel source, Papel dest) {
		dest.assignFrom(source);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#getTableName()
	 */
	@Override
	public String getTableName() {
		return TableNames.TB_PAPEL;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#updateEnabled()
	 */
	@Override
	protected boolean updateEnabled() {
		return LoginManager.getAccessControl().hasAccess(FuncionalidadeProjeto.FUNC_PROJETO_CADASTRAR.getId());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#queryEnabled()
	 */
	@Override
	protected boolean queryEnabled() {
		return LoginManager.getAccessControl().hasAccess(FuncionalidadeProjeto.FUNC_PROJETO_CONSULTAR.getId());
	}

	@Override
	public Collection<Papel> loadPapeisDoProjeto(Projeto projeto) {
		return loadCollectionByFilter("flg_ativo = ? and seq_projeto = ?", Boolean.TRUE, new Long(projeto.getId()));
	}

}
