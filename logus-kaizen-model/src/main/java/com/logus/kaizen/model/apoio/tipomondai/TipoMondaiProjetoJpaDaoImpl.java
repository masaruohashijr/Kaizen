/**
 *
 */
package com.logus.kaizen.model.apoio.tipomondai;

import java.util.Collection;

import com.logus.core.model.aut.login.LoginManager;
import com.logus.core.model.persistence.jpa.AbstractJpaDao;
import com.logus.kaizen.model.TableNames;
import com.logus.kaizen.model.apoio.ApoioDataServiceImpl;
import com.logus.kaizen.model.apoio.projeto.FuncionalidadeProjeto;
/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class TipoMondaiProjetoJpaDaoImpl extends AbstractJpaDao<TipoMondaiProjeto> implements TipoMondaiProjetoDao {

	/**
	 * @param emf
	 */
	public TipoMondaiProjetoJpaDaoImpl() {
		super(ApoioDataServiceImpl.getEMF());
	}

	private static TipoMondaiProjetoJpaDaoImpl instance;

	public static TipoMondaiProjetoJpaDaoImpl getInstance() {
		if (instance == null) {
			instance = new TipoMondaiProjetoJpaDaoImpl();
		}
		return instance;
	}

	public Collection<TipoMondaiProjeto> loadProjetos() {
		return loadCollectionByFilter("flg_ativo", Boolean.TRUE);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#assignTo(java.lang.
	 * Object, java.lang.Object)
	 */
	@Override
	public void assignTo(TipoMondaiProjeto source, TipoMondaiProjeto dest) {
		dest.assignFrom(source);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#getTableName()
	 */
	@Override
	public String getTableName() {
		return TableNames.TB_PROJETO;
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
	public Collection<TipoMondaiProjeto> loadTiposMondaiProjetoSemProcessos() {
		return loadCollectionByFilter("seq_processo = ?", "");
	}
}
