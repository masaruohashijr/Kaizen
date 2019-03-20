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
public class FuncaoJpaDaoImpl extends AbstractJpaDao<Funcao> implements FuncaoDao {

	/**
	 * @param emf
	 */
	public FuncaoJpaDaoImpl() {
		super(ApoioDataServiceImpl.getEMF());
	}

	private static FuncaoJpaDaoImpl instance;

	public static FuncaoJpaDaoImpl getInstance() {
		if (instance == null) {
			instance = new FuncaoJpaDaoImpl();
		}
		return instance;
	}

	@Override
	public Collection<Funcao> loadFuncoes() {
		return loadCollection("seq_funcao, nom_funcao, flg_ativo", TableNames.TB_FUNCAO, "flg_ativo = ?", "nom_funcao",
				Boolean.TRUE);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#assignTo(java.lang.
	 * Object, java.lang.Object)
	 */
	@Override
	public void assignTo(Funcao source, Funcao dest) {
		dest.assignFrom(source);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#getTableName()
	 */
	@Override
	public String getTableName() {
		return TableNames.TB_FUNCAO;
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
