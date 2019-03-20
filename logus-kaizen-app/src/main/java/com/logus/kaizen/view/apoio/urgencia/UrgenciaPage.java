/**
 *
 */
package com.logus.kaizen.view.apoio.urgencia;

import java.util.Collection;

import com.logus.core.model.dialog.DialogButtonType;
import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.form.BeanForm;
import com.logus.core.view.list.BeanGrid;
import com.logus.kaizen.app.KaizenAbstractPage;
import com.logus.kaizen.app.KaizenMainLayout;
import com.logus.kaizen.model.apoio.ApoioDataService;
import com.logus.kaizen.model.apoio.urgencia.Urgencia;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.vaadin.flow.router.Route;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
@Route(value = "urgencia", layout = KaizenMainLayout.class)
public class UrgenciaPage extends KaizenAbstractPage<Urgencia> {

	public UrgenciaPage() {
		super(DialogButtonType.SAVE);
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
	protected Collection<Urgencia> loadAll() {
		return ApoioDataService.get().getUrgenciaDao().loadAll();
	}

	/**
	 * Método responsável por criar a tabela contendo todas as priorizacoes
	 * persistidos no banco
	 *
	 * @return BeanGrid<Priorizacao>
	 */
	@Override
	protected BeanGrid<Urgencia> createGrid() {
		return new UrgenciaGrid();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.view.list.AbstractListEditor#createForm(java.lang.Object)
	 */
	@Override
	protected BeanForm<Urgencia> createForm(Urgencia urgencia) {
		return new UrgenciaForm(urgencia);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.logus.core.view.list.AbstractListEditor#deleteObject(java.lang.Object)
	 */
	@Override
	protected void deleteObject(Urgencia urgencia) throws Exception {
		ApoioDataService.get().getUrgenciaDao().delete(urgencia);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.logus.core.view.list.AbstractListEditor#insertObject(java.lang.Object)
	 */
	@Override
	protected void insertObject(Urgencia urgencia) throws Exception {
		ApoioDataService.get().getUrgenciaDao().insert(urgencia);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.logus.core.view.list.AbstractListEditor#updateObject(java.lang.Object)
	 */
	@Override
	protected void updateObject(Urgencia urgencia) throws Exception {
		ApoioDataService.get().getUrgenciaDao().update(urgencia);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.view.list.AbstractListEditor#getNomeEntidade()
	 */
	@Override
	protected Object getNomeEntidade() {
		return TM.translate(KaizenTranslator.URGENCIA);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.view.list.AbstractListEditor#getNomeEntidadePlural()
	 */
	@Override
	protected String getNomeEntidadePlural() {
		return TM.translate(KaizenTranslator.URGENCIA_PLURAL);
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
