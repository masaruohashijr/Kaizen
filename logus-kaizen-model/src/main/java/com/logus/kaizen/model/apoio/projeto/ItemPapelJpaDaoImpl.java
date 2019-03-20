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
public class ItemPapelJpaDaoImpl extends AbstractJpaDao<ItemPapel> implements ItemPapelDao {

	/**
	 * @param emf
	 */
	public ItemPapelJpaDaoImpl() {
		super(ApoioDataServiceImpl.getEMF());
	}

	private static ItemPapelJpaDaoImpl instance;

	public static ItemPapelJpaDaoImpl getInstance() {
		if (instance == null) {
			instance = new ItemPapelJpaDaoImpl();
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
	public void assignTo(ItemPapel source, ItemPapel dest) {
		dest.assignFrom(source);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#getTableName()
	 */
	@Override
	public String getTableName() {
		return TableNames.TB_ITEM_PAPEL;
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
	public Collection<ItemPapel> loadPapeisDoProjetoDoUsuario(String codigoUsuario) {
		return loadCollectionByFilter("cod_usuario = ?", codigoUsuario);
	}

}
