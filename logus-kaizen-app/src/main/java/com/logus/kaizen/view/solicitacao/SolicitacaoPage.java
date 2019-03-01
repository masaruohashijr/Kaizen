/**
 *
 */
package com.logus.kaizen.view.solicitacao;

import java.util.Collection;

import com.logus.kaizen.model.apoio.ApoioDataService;
import com.logus.kaizen.model.solicitacao.Solicitacao;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.form.BeanForm;
import com.logus.core.view.list.BeanGrid;
import com.logus.kaizen.app.KaizenAbstractPage;
import com.logus.kaizen.app.KaizenMainLayout;
import com.vaadin.flow.router.Route;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
@Route(value = "solicitacao", layout = KaizenMainLayout.class)
public class SolicitacaoPage extends KaizenAbstractPage<Solicitacao> {

	/*
	 * Construtor
	 */
	public SolicitacaoPage() {
		super();
		setEditWindowWidth(1000);
		setEditWindowHeight(800);
	}

	/**
	 * Serial
	 */
	private static final long serialVersionUID = -99267169911263536L;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.view.list.ListViewPage#loadAll()
	 */
	@Override
	protected Collection<Solicitacao> loadAll() {
		return ApoioDataService.get().getSolicitacaoDao().loadAll();
	}

	/**
	 * Método responsável por criar a tabela contendo todas as solicitações
	 * persistidas no banco
	 *
	 * @return BeanGrid<Solicitacao>
	 */
	@Override
	protected BeanGrid<Solicitacao> createGrid() {
		return new SolicitacaoGrid();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.view.list.AbstractListEditor#createForm(java.lang.Object)
	 */
	@Override
	protected BeanForm<Solicitacao> createForm(Solicitacao solicitacao) {
		return new SolicitacaoForm(solicitacao);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.logus.core.view.list.AbstractListEditor#deleteObject(java.lang.Object)
	 */
	@Override
	protected void deleteObject(Solicitacao solicitacao) throws Exception {
		ApoioDataService.get().getSolicitacaoDao().delete(solicitacao);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.logus.core.view.list.AbstractListEditor#insertObject(java.lang.Object)
	 */
	@Override
	protected void insertObject(Solicitacao solicitacao) throws Exception {
		ApoioDataService.get().getSolicitacaoDao().insert(solicitacao);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.logus.core.view.list.AbstractListEditor#updateObject(java.lang.Object)
	 */
	@Override
	protected void updateObject(Solicitacao solicitacao) throws Exception {
		ApoioDataService.get().getSolicitacaoDao().update(solicitacao);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.view.list.AbstractListEditor#getNomeEntidade()
	 */
	@Override
	protected Object getNomeEntidade() {
		return TM.translate(KaizenTranslator.SOLICITACAO);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.view.list.AbstractListEditor#getNomeEntidadePlural()
	 */
	@Override
	protected String getNomeEntidadePlural() {
		return TM.translate(KaizenTranslator.SOLICITACAO_PLURAL);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.view.list.AbstractListEditor#getArtigoEntidade()
	 */
	@Override
	protected Object getArtigoEntidade() {
		return TM.translate(CoreTranslator.ART_MAS_SING);
	}

}
