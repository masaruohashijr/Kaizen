/**
 *
 */
package com.logus.kaizen.model.solicitacao;

import java.util.Collection;

import com.logus.core.model.aut.login.LoginManager;
import com.logus.kaizen.model.TableNames;
import com.logus.kaizen.model.apoio.ApoioDataServiceImpl;
import com.logus.kaizen.model.persistence.jpa.AbstractKaizenJpaDao;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class ItemSolicitacaoJpaDaoImpl extends AbstractKaizenJpaDao<ItemSolicitacao> implements ItemSolicitacaoDao {


	/**
	 * @param emf
	 */
	public ItemSolicitacaoJpaDaoImpl() {
		super(ApoioDataServiceImpl.getEMF());
	}

	private static ItemSolicitacaoJpaDaoImpl instance;

	public static ItemSolicitacaoJpaDaoImpl getInstance() {
		if (instance == null) {
			instance = new ItemSolicitacaoJpaDaoImpl();
		}
		return instance;
	}


	@Override
	public Collection<ItemSolicitacao> loadItensSolicitacao() {
		return loadCollectionByFilter("solicitado = ? AND seq_atendimento IS NULL", Boolean.TRUE);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#assignTo(java.lang.
	 * Object, java.lang.Object)
	 */
	@Override
	public void assignTo(ItemSolicitacao source, ItemSolicitacao dest) {
		dest.assignFrom(source);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#getTableName()
	 */
	@Override
	public String getTableName() {
		return TableNames.TB_ITEM_SOLICITACAO;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#updateEnabled()
	 */
	@Override
	protected boolean updateEnabled() {
		return LoginManager.getAccessControl().hasAccess(FuncionalidadeSolicitacao.FUNC_SOLICITACAO_CADASTRAR.getId());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#queryEnabled()
	 */
	@Override
	protected boolean queryEnabled() {
		return LoginManager.getAccessControl().hasAccess(FuncionalidadeSolicitacao.FUNC_SOLICITACAO_CONSULTAR.getId());
	}

}
