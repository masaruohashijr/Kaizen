/**
 *
 */
package com.logus.kaizen.view.kotae.configuracao;

import java.util.Collection;

import com.logus.core.model.dialog.DialogButtonType;
import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.dialog.YesNoDialog;
import com.logus.core.view.exceptions.ExceptionHandler;
import com.logus.core.view.form.BeanForm;
import com.logus.core.view.list.BeanGrid;
import com.logus.core.view.list.EditWindow;
import com.logus.kaizen.app.HasButtonsToolbar;
import com.logus.kaizen.app.KaizenMainLayout;
import com.logus.kaizen.model.apoio.ApoioDataService;
import com.logus.kaizen.model.apoio.tipomondai.AbstractAtribuicaoPassoItem;
import com.logus.kaizen.model.kotae.configuracao.KotaeConfiguracao;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.kaizen.view.edit.KaizenEditWindow;
import com.logus.kaizen.view.list.ControleTempos;
import com.logus.kaizen.view.list.KaizenAbstractListEditor;
import com.vaadin.flow.component.Focusable;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.router.Route;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
@Route(value = "kotaeconfiguracao", layout = KaizenMainLayout.class)
public class KotaeConfiguracaoPage extends KaizenAbstractListEditor<KotaeConfiguracao> {

	/*
	 * Construtor
	 */
	public KotaeConfiguracaoPage() {
		super(DialogButtonType.SAVE, HasButtonsToolbar.TRUE);
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
	protected Collection<KotaeConfiguracao> loadAll() {
		return ApoioDataService.get().getKotaeConfiguracaoDao().loadAll();
	}

	/**
	 * Método responsável por criar a tabela contendo todas as solicitações
	 * persistidas no banco
	 *
	 * @return BeanGrid<Deploy>
	 */
	@Override
	protected BeanGrid<KotaeConfiguracao> createGrid() {
		return new KotaeConfiguracaoGrid();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.view.list.AbstractListEditor#createForm(java.lang.Object)
	 */
	@Override
	protected BeanForm<KotaeConfiguracao> createForm(KotaeConfiguracao KotaeConfiguracao) {
		return new KotaeConfiguracaoForm(KotaeConfiguracao);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.logus.core.view.list.AbstractListEditor#deleteObject(java.lang.Object)
	 */
	@Override
	protected void deleteObject(KotaeConfiguracao kotaeConfiguracao) throws Exception {
		ApoioDataService.get().getKotaeConfiguracaoDao().delete(kotaeConfiguracao);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.logus.core.view.list.AbstractListEditor#insertObject(java.lang.Object)
	 */
	@Override
	protected void insertObject(KotaeConfiguracao kotaeConfiguracao) throws Exception {
		ApoioDataService.get().getKotaeConfiguracaoDao().insert(kotaeConfiguracao);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.logus.core.view.list.AbstractListEditor#updateObject(java.lang.Object)
	 */
	@Override
	protected void updateObject(KotaeConfiguracao kotaeConfiguracao) throws Exception {
		ApoioDataService.get().getKotaeConfiguracaoDao().update(kotaeConfiguracao);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.view.list.AbstractListEditor#getNomeEntidade()
	 */
	@Override
	protected Object getNomeEntidade() {
		return TM.translate(KaizenTranslator.KOTAE_CONFIGURACAO);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.view.list.AbstractListEditor#getNomeEntidadePlural()
	 */
	@Override
	protected String getNomeEntidadePlural() {
		return TM.translate(KaizenTranslator.KOTAE_CONFIGURACAO_PLURAL);
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

	@Override
	protected void view() {
		KotaeConfiguracao selected = getSelectedObject();
		selected = clone(selected);
		Dialog window = new EditWindow<KotaeConfiguracao>(
				TM.translate(CoreTranslator.LIST_EDITOR_VIEW_TITLE, getArtigoEntidade(), getNomeEntidade(), selected),
				createForm(selected), DialogButtonType.CLOSE, true, super.editWindowWidth, super.editWindowHeight);
		window.open();
	}

	@Override
	protected void edit() {
		KotaeConfiguracao selected = getSelectedObject();
		selected = clone(selected);

		final Dialog window = new KaizenEditWindow<KotaeConfiguracao>(
				TM.translate(CoreTranslator.LIST_EDITOR_EDIT_TITLE, getArtigoEntidade(), getNomeEntidade(), selected),
				createForm(selected), String.valueOf(""), false, super.editWindowWidth, super.editWindowHeight, new ControleTempos(),
				null, true) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void confirmAction(AbstractAtribuicaoPassoItem atribuicaoPassoItem) {
				try {
					if (!readOnly) {
						validate();
						confirm(form.getObject());
					}
					close();
				} catch (Exception e) {
					ExceptionHandler.handleErrorException(e);
				}

			}

			public void confirm(KotaeConfiguracao obj) throws Exception {
				updateObject(obj);
				grid.updateObject(getSelectedObject(), obj);
				refreshButtons();
				notification(TM.translate(CoreTranslator.LIST_EDITOR_UPDATE_FEEDBACK, getArtigoEntidade(),
						getNomeEntidade(), obj));
				updateButton.focus();
			}

			@Override
			protected void chronosAction() {
				// TODO Auto-generated method stub

			}

		};
		window.open();
	}

	@Override
	protected void insert(KotaeConfiguracao selected, String caption, Focusable<?> toFocus) {
		final Dialog window = new KaizenEditWindow<KotaeConfiguracao>(
				TM.translate(CoreTranslator.LIST_EDITOR_INSERT_TITLE, getArtigoEntidade(), getNomeEntidade(), selected),
				createForm(selected), String.valueOf(""), false, super.editWindowWidth, super.editWindowHeight, new ControleTempos(), null, true) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void confirmAction(AbstractAtribuicaoPassoItem atribuicaoPassoItem) {
				try {
					if (!readOnly) {
						validate();
						confirm(form.getObject());
					}
					close();
				} catch (Exception e) {
					ExceptionHandler.handleErrorException(e);
				}
			}

			public void confirm(KotaeConfiguracao obj) throws Exception {
				insertObject(obj);
				grid.addObject(obj);
				refreshButtons();
				notification(TM.translate(CoreTranslator.LIST_EDITOR_INSERT_FEEDBACK, getArtigoEntidade(),
						getNomeEntidade(), obj));
				toFocus.focus();
			}

			@Override
			protected void chronosAction() {
				// TODO Auto-generated method stub

			}

		};
		window.open();
	}

	@Override
	protected void delete() {
		String msg = TM.translate(CoreTranslator.LIST_EDITOR_DELETE_CONFIRMATION, getArtigoEntidade(),
				getNomeEntidade(), getSelectedObject());
		YesNoDialog.execute(TM.translate(CoreTranslator.LIST_EDITOR_DELETE_TITLE, getArtigoEntidade(),
				getNomeEntidade(), getSelectedObject()), msg, () -> {
					KotaeConfiguracao selected = getSelectedObject();
					try {
						deleteObject(selected);
						grid.removeObject(selected);
						refreshButtons();
						notification(TM.translate(CoreTranslator.LIST_EDITOR_DELETE_FEEDBACK, getArtigoEntidade(),
								getNomeEntidade(), selected));
						deleteButton.focus();

					} catch (Exception e) {
						ExceptionHandler.handleErrorException(e);
					}
				});
	}

}
