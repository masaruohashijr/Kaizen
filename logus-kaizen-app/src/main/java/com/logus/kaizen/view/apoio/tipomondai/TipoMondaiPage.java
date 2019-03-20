/**
 *
 */
package com.logus.kaizen.view.apoio.tipomondai;

import java.util.Collection;

import com.logus.core.model.dialog.DialogButtonType;
import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.form.BeanForm;
import com.logus.core.view.list.BeanGrid;
import com.logus.kaizen.app.KaizenAbstractPage;
import com.logus.kaizen.app.KaizenMainLayout;
import com.logus.kaizen.model.apoio.ApoioDataService;
import com.logus.kaizen.model.apoio.tipomondai.TipoMondai;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.vaadin.flow.router.Route;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
@Route(value = "tipoMondai", layout = KaizenMainLayout.class)
public class TipoMondaiPage extends KaizenAbstractPage<TipoMondai> {

	/*
	 * Construtor
	 */
	public TipoMondaiPage() {
		super(DialogButtonType.SAVE);
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
	protected Collection<TipoMondai> loadAll() {
		return ApoioDataService.get().getTipoMondaiDao().loadAll();
	}

	/**
	 * Método responsável por criar a tabela contendo todas as tipoMondais
	 * persistidas no banco
	 *
	 * @return BeanGrid<TipoMondai>
	 */
	@Override
	protected BeanGrid<TipoMondai> createGrid() {
		return new TipoMondaiGrid();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.view.list.AbstractListEditor#createForm(java.lang.Object)
	 */
	@Override
	protected BeanForm<TipoMondai> createForm(TipoMondai tipoMondai) {
		return new TipoMondaiForm(tipoMondai);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.logus.core.view.list.AbstractListEditor#deleteObject(java.lang.Object)
	 */
	@Override
	protected void deleteObject(TipoMondai tipoMondai) throws Exception {
		ApoioDataService.get().getTipoMondaiDao().delete(tipoMondai);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.logus.core.view.list.AbstractListEditor#insertObject(java.lang.Object)
	 */
	@Override
	protected void insertObject(TipoMondai tipoMondai) throws Exception {
		ApoioDataService.get().getTipoMondaiDao().insert(tipoMondai);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.logus.core.view.list.AbstractListEditor#updateObject(java.lang.Object)
	 */
	@Override
	protected void updateObject(TipoMondai tipoMondai) throws Exception {
		ApoioDataService.get().getTipoMondaiDao().update(tipoMondai);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.view.list.AbstractListEditor#getNomeEntidade()
	 */
	@Override
	protected Object getNomeEntidade() {
		return TM.translate(KaizenTranslator.TIPO_MONDAI);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.view.list.AbstractListEditor#getNomeEntidadePlural()
	 */
	@Override
	protected String getNomeEntidadePlural() {
		return TM.translate(KaizenTranslator.TIPO_MONDAI_PLURAL);
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
