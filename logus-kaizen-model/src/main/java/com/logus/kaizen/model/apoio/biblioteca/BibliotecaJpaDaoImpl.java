/**
 *
 */
package com.logus.kaizen.model.apoio.biblioteca;

import java.util.Collection;

import com.logus.core.model.aut.login.LoginManager;
import com.logus.core.model.persistence.jpa.AbstractJpaDao;
import com.logus.kaizen.model.apoio.ApoioDataServiceImpl;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class BibliotecaJpaDaoImpl extends AbstractJpaDao<Biblioteca> implements BibliotecaDao {

	/**
	 * @param emf
	 */
	public BibliotecaJpaDaoImpl() {
		super(ApoioDataServiceImpl.getEMF());
	}

	private static BibliotecaJpaDaoImpl instance;

	public static BibliotecaJpaDaoImpl getInstance() {
		if (instance == null) {
			instance = new BibliotecaJpaDaoImpl();
		}
		return instance;
	}

	@Override
	public Collection<Biblioteca> loadBibliotecas() {
		return loadCollectionByFilter("flg_ativo = ?", Boolean.TRUE);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#assignTo(java.lang.
	 * Object, java.lang.Object)
	 */
	@Override
	public void assignTo(Biblioteca source, Biblioteca dest) {
		dest.assignFrom(source);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#getTableName()
	 */
	@Override
	public String getTableName() {
		return Biblioteca.TB_BIBLIOTECA;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#updateEnabled()
	 */
	@Override
	protected boolean updateEnabled() {
		return LoginManager.getAccessControl().hasAccess(FuncionalidadeBiblioteca.FUNC_BIBLIOTECA_CADASTRAR.getId());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#queryEnabled()
	 */
	@Override
	protected boolean queryEnabled() {
		return LoginManager.getAccessControl().hasAccess(FuncionalidadeBiblioteca.FUNC_BIBLIOTECA_CONSULTAR.getId());
	}

}
