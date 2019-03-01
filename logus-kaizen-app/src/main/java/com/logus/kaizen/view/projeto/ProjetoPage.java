/**
 *
 */
package com.logus.kaizen.view.projeto;

import java.util.Collection;

import com.logus.kaizen.model.apoio.ApoioDataService;
import com.logus.kaizen.model.projeto.Projeto;
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
@Route(value = "projeto", layout = KaizenMainLayout.class)
public class ProjetoPage extends KaizenAbstractPage<Projeto> {

	/**
	 *
	 */
	private static final long serialVersionUID = -99267169911263536L;

	public ProjetoPage() {
		super();
		setEditWindowWidth(1000);
		setEditWindowHeight(800);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.view.list.ListViewPage#loadAll()
	 */
	@Override
	protected Collection<Projeto> loadAll() {
		return ApoioDataService.get().getProjetoDao().loadAll();
	}

	/**
	 * Método responsável por criar a tabela contendo todos os projetos persistidos
	 * no banco
	 *
	 * @return BeanGrid<Status>
	 */
	@Override
	protected BeanGrid<Projeto> createGrid() {
		return new ProjetoGrid();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.view.list.AbstractListEditor#createForm(java.lang.Object)
	 */
	@Override
	protected BeanForm<Projeto> createForm(Projeto projeto) {
		return new ProjetoForm(projeto);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.logus.core.view.list.AbstractListEditor#deleteObject(java.lang.Object)
	 */
	@Override
	protected void deleteObject(Projeto processo) throws Exception {
		ApoioDataService.get().getProjetoDao().delete(processo);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.logus.core.view.list.AbstractListEditor#insertObject(java.lang.Object)
	 */
	@Override
	protected void insertObject(Projeto processo) throws Exception {
		ApoioDataService.get().getProjetoDao().insert(processo);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.logus.core.view.list.AbstractListEditor#updateObject(java.lang.Object)
	 */
	@Override
	protected void updateObject(Projeto processo) throws Exception {
		ApoioDataService.get().getProjetoDao().update(processo);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.view.list.AbstractListEditor#getNomeEntidade()
	 */
	@Override
	protected Object getNomeEntidade() {
		return TM.translate(KaizenTranslator.PROJETO);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.view.list.AbstractListEditor#getNomeEntidadePlural()
	 */
	@Override
	protected String getNomeEntidadePlural() {
		return TM.translate(KaizenTranslator.PROJETO_PLURAL);
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
