/**
 *
 */
package com.logus.kaizen.model.apoio.tipomondai;

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
public class TipoMondaiJpaDaoImpl extends AbstractJpaDao<TipoMondai> implements TipoMondaiDao {

	/**
	 * @param emf
	 */
	public TipoMondaiJpaDaoImpl() {
		super(ApoioDataServiceImpl.getEMF());
	}

	private static TipoMondaiJpaDaoImpl instance;

	public static TipoMondaiJpaDaoImpl getInstance() {
		if (instance == null) {
			instance = new TipoMondaiJpaDaoImpl();
		}
		return instance;
	}

	@Override
	public Collection<TipoMondai> loadTiposMondai() {
		return loadCollectionByFilter("flg_ativo = ?", Boolean.TRUE);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#assignTo(java.lang.
	 * Object, java.lang.Object)
	 */
	@Override
	public void assignTo(TipoMondai source, TipoMondai dest) {
		dest.assignFrom(source);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#getTableName()
	 */
	@Override
	public String getTableName() {
		return TipoMondai.NOME_TABELA_TIPO_MONDAI;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#updateEnabled()
	 */
	@Override
	protected boolean updateEnabled() {
		return LoginManager.getAccessControl().hasAccess(FuncionalidadeTipoMondai.FUNC_TIPO_MONDAI_CADASTRAR.getId());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#queryEnabled()
	 */
	@Override
	protected boolean queryEnabled() {
		return LoginManager.getAccessControl().hasAccess(FuncionalidadeTipoMondai.FUNC_TIPO_MONDAI_CONSULTAR.getId());
	}

}
