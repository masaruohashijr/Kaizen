/**
 *
 */
package com.logus.kaizen.view.processo;

import java.util.Collection;

import com.logus.kaizen.model.apoio.ApoioDataService;
import com.logus.kaizen.model.processo.Processo;
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
@Route(value = "processo", layout = KaizenMainLayout.class)
public class ProcessoPage extends KaizenAbstractPage<Processo> {

	/*
	 * Construtor
	 */
	public ProcessoPage() {
		super();
		setEditWindowWidth(1000);
		setEditWindowHeight(800);
	}

	/**
	 *
	 */
	private static final long serialVersionUID = -99267169911263536L;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.view.list.ListViewPage#loadAll()
	 */
	@Override
	protected Collection<Processo> loadAll() {
		return ApoioDataService.get().getProcessoDao().loadProcessos();
	}

	/**
	 * Método responsável por criar a tabela contendo todos os processos persistidos
	 * no banco
	 *
	 * @return BeanGrid<Status>
	 */
	@Override
	protected BeanGrid<Processo> createGrid() {
		return new ProcessoGrid();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.view.list.AbstractListEditor#createForm(java.lang.Object)
	 */
	@Override
	protected BeanForm<Processo> createForm(Processo processo) {
		return new ProcessoForm(processo);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.logus.core.view.list.AbstractListEditor#deleteObject(java.lang.Object)
	 */
	@Override
	protected void deleteObject(Processo processo) throws Exception {
		ApoioDataService.get().getProcessoDao().delete(processo);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.logus.core.view.list.AbstractListEditor#insertObject(java.lang.Object)
	 */
	@Override
	protected void insertObject(Processo processo) throws Exception {
		ApoioDataService.get().getProcessoDao().insert(processo);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.logus.core.view.list.AbstractListEditor#updateObject(java.lang.Object)
	 */
	@Override
	protected void updateObject(Processo processo) throws Exception {
		ApoioDataService.get().getProcessoDao().update(processo);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.view.list.AbstractListEditor#getNomeEntidade()
	 */
	@Override
	protected Object getNomeEntidade() {
		return TM.translate(KaizenTranslator.PROCESSO);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.view.list.AbstractListEditor#getNomeEntidadePlural()
	 */
	@Override
	protected String getNomeEntidadePlural() {
		return TM.translate(KaizenTranslator.PROCESSO_PLURAL);
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
