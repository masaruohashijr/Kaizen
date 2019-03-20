/**
 *
 */
package com.logus.kaizen.model.auditoria;

import  java.util.Collection;

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
public class ItemMudancaJpaDaoImpl extends AbstractJpaDao<ItemMudanca> implements ItemMudancaDao {

	/**
	 * @param emf
	 */
	public ItemMudancaJpaDaoImpl() {
		super(ApoioDataServiceImpl.getEMF());
	}

	private static ItemMudancaJpaDaoImpl instance;

	public static ItemMudancaJpaDaoImpl getInstance() {
		if (instance == null) {
			instance = new ItemMudancaJpaDaoImpl();
		}
		return instance;
	}

	@Override
	public Collection<ItemMudanca> loadItensMudanca() {
		return loadCollection("*", TableNames.TB_ITEM_MUDANCA, "flg_ativo = ?", "seq_item_grupo desc", Boolean.TRUE);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#assignTo(java.lang.
	 * Object, java.lang.Object)
	 */
	@Override
	public void assignTo(ItemMudanca source, ItemMudanca dest) {
		dest.assignFrom(source);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#getTableName()
	 */
	@Override
	public String getTableName() {
		return TableNames.TB_ITEM_MUDANCA;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#updateEnabled()
	 */
	@Override
	protected boolean updateEnabled() {
		return LoginManager.getAccessControl().hasAccess(FuncionalidadeGrupoMudanca.FUNC_GRUPO_MUDANCA_CADASTRAR.getId());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#queryEnabled()
	 */
	@Override
	protected boolean queryEnabled() {
		return LoginManager.getAccessControl().hasAccess(FuncionalidadeGrupoMudanca.FUNC_GRUPO_MUDANCA_CONSULTAR.getId());
	}

}
