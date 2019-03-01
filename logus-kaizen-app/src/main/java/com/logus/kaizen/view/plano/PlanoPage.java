/**
 *
 */
package com.logus.kaizen.view.plano;

import java.util.Collection;

import com.logus.kaizen.model.apoio.ApoioDataService;
import com.logus.kaizen.model.plano.Plano;
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
@Route(value = "plano", layout = KaizenMainLayout.class)
public class PlanoPage extends KaizenAbstractPage<Plano> {

	/*
	 * Construtor
	 */
	public PlanoPage() {
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
	protected Collection<Plano> loadAll() {
		return ApoioDataService.get().getPlanoDao().loadAll();
	}

	/**
	 * Método responsável por criar a tabela contendo todas as solicitações
	 * persistidas no banco
	 *
	 * @return BeanGrid<Deploy>
	 */
	@Override
	protected BeanGrid<Plano> createGrid() {
		return new PlanoGrid();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.view.list.AbstractListEditor#createForm(java.lang.Object)
	 */
	@Override
	protected BeanForm<Plano> createForm(Plano plano) {
		return new PlanoForm(plano);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.logus.core.view.list.AbstractListEditor#deleteObject(java.lang.Object)
	 */
	@Override
	protected void deleteObject(Plano plano) throws Exception {
		ApoioDataService.get().getPlanoDao().delete(plano);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.logus.core.view.list.AbstractListEditor#insertObject(java.lang.Object)
	 */
	@Override
	protected void insertObject(Plano plano) throws Exception {
		ApoioDataService.get().getPlanoDao().insert(plano);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.logus.core.view.list.AbstractListEditor#updateObject(java.lang.Object)
	 */
	@Override
	protected void updateObject(Plano plano) throws Exception {
		ApoioDataService.get().getPlanoDao().update(plano);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.view.list.AbstractListEditor#getNomeEntidade()
	 */
	@Override
	protected Object getNomeEntidade() {
		return TM.translate(KaizenTranslator.PLANO);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.view.list.AbstractListEditor#getNomeEntidadePlural()
	 */
	@Override
	protected String getNomeEntidadePlural() {
		return TM.translate(KaizenTranslator.PLANO_PLURAL);
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
