/**
 *
 */
package com.logus.kaizen.view.chronos;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.PersistenceException;

import com.logus.core.model.dialog.DialogButtonType;
import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.model.util.Formats;
import com.logus.core.model.util.Strings;
import com.logus.core.view.dialog.YesNoDialog;
import com.logus.core.view.exceptions.ExceptionHandler;
import com.logus.core.view.form.BeanForm;
import com.logus.core.view.list.BeanGrid;
import com.logus.core.view.list.EditWindow;
import com.logus.kaizen.app.HasButtonsToolbar;
import com.logus.kaizen.app.KaizenListSelector;
import com.logus.kaizen.app.KaizenMainLayout;
import com.logus.kaizen.model.apoio.ApoioDataService;
import com.logus.kaizen.model.apoio.atendimento.Atendimento;
import com.logus.kaizen.model.apoio.processo.Passo;
import com.logus.kaizen.model.apoio.tipomondai.AbstractAtribuicaoPassoItem;
import com.logus.kaizen.model.chronos.Chronos;
import com.logus.kaizen.model.kotae.configuracao.KotaeConfiguracao;
import com.logus.kaizen.model.solicitacao.Solicitacao;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.kaizen.view.apoio.processo.ProcessoUtil;
import com.logus.kaizen.view.edit.KaizenEditWindow;
import com.logus.kaizen.view.list.ControleTempos;
import com.logus.kaizen.view.list.KaizenAbstractListEditor;
import com.vaadin.flow.component.Focusable;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
@Route(value = "chronos", layout = KaizenMainLayout.class)
public class ChronosPage extends KaizenAbstractListEditor<Chronos> {

	/*
	 * Construtor
	 */
	public ChronosPage() {
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
	protected Collection<Chronos> loadAll() {
		return ApoioDataService.get().getToguruDao().loadTogurus();
	}

	/**
	 * Método responsável por criar a tabela contendo todas as solicitações
	 * persistidas no banco
	 *
	 * @return BeanGrid<Deploy>
	 */
	@Override
	protected BeanGrid<Chronos> createGrid() {
		return new ChronosGrid();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.view.list.AbstractListEditor#createForm(java.lang.Object)
	 */
	@Override
	protected BeanForm<Chronos> createForm(Chronos chronos) {
		return new ChronosForm(chronos);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.logus.core.view.list.AbstractListEditor#deleteObject(java.lang.Object)
	 */
	@Override
	protected void deleteObject(Chronos chronos) throws Exception {
		ApoioDataService.get().getToguruDao().delete(chronos);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.logus.core.view.list.AbstractListEditor#insertObject(java.lang.Object)
	 */
	@Override
	protected void insertObject(Chronos chronos) throws Exception {
		ApoioDataService.get().getToguruDao().insert(chronos);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.logus.core.view.list.AbstractListEditor#updateObject(java.lang.Object)
	 */
	@Override
	protected void updateObject(Chronos chronos) throws Exception {
		ApoioDataService.get().getToguruDao().update(chronos);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.view.list.AbstractListEditor#getNomeEntidade()
	 */
	@Override
	protected Object getNomeEntidade() {
		return TM.translate(KaizenTranslator.CHRONOS);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.view.list.AbstractListEditor#getNomeEntidadePlural()
	 */
	@Override
	protected String getNomeEntidadePlural() {
		return TM.translate(KaizenTranslator.CHRONOS_PLURAL);
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
		Chronos selected = getSelectedObject();
		selected = clone(selected);
		Dialog window = new EditWindow<Chronos>(
				TM.translate(CoreTranslator.LIST_EDITOR_VIEW_TITLE, getArtigoEntidade(), getNomeEntidade(), selected),
				createForm(selected), DialogButtonType.CLOSE, true, super.editWindowWidth, super.editWindowHeight);
		window.open();
	}

	@Override
	protected void edit() {
		Chronos selected = getSelectedObject();
		selected = clone(selected);
		Collection<AbstractAtribuicaoPassoItem> atribuicoesPassoItem = ProcessoUtil.getProximosPassosItens(selected);
		AbstractAtribuicaoPassoItem ultimaAtribuicaoPassoItem = ProcessoUtil.getUltimoPassoItem(selected);
		final Dialog window = new KaizenEditWindow<Chronos>(
				TM.translate(CoreTranslator.LIST_EDITOR_EDIT_TITLE, getArtigoEntidade(), getNomeEntidade(), selected),
				createForm(selected), String.valueOf(""), false, super.editWindowWidth, super.editWindowHeight, new ControleTempos(),
				atribuicoesPassoItem, false) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void confirmAction(AbstractAtribuicaoPassoItem atribuicaoPassoItem) {
				try {
					if (!readOnly) {
						Chronos chronos = form.getObject();
						TextField tfDataInicio = ((ChronosForm)form).getTfDataInicio();
						TextField tfDataFim = ((ChronosForm)form).getTfDataFim();
						String valorDataFim = tfDataInicio.getValue();
						chronos.setDataInicio(Formats.getDateTimeFormat().parse(valorDataFim));
						valorDataFim = tfDataFim.getValue();
						if(!Strings.isEmpty(valorDataFim)) {
							chronos.setDataFim(Formats.getDateTimeFormat().parse(valorDataFim));
						}
						if (null != atribuicaoPassoItem) {
							// Atendimento Destino
							Passo passo = (null == atribuicaoPassoItem.getPasso()) ? (Passo) atribuicaoPassoItem
									: atribuicaoPassoItem.getPasso();
							Atendimento atendimentoDestino = passo.getAtendimentoDestino();
							chronos.setAtendimento(atendimentoDestino);
							if (((Passo) ultimaAtribuicaoPassoItem).getAtendimentoDestino()
									.equals(atendimentoDestino)) {
								chronos.setDataFim(new Date(System.currentTimeMillis()));
							}
						}
						validate();
						confirm(chronos);
					}
					close();
				} catch (Exception e) {
					ExceptionHandler.handleErrorException(e);
				}
			}

			public void confirm(Chronos chronos) throws Exception {
				updateObject(chronos);
				grid.updateObject(getSelectedObject(), chronos);
				refreshButtons();
				notification(TM.translate(CoreTranslator.LIST_EDITOR_UPDATE_FEEDBACK, getArtigoEntidade(),
						getNomeEntidade(), chronos));
				updateButton.focus();
			}

			@Override
			protected void chronosAction() {
			}

		};
		window.open();
	}

	@Override
	protected void insert(Chronos selected, String caption, Focusable<?> toFocus) {
		Collection<KotaeConfiguracao> collection = ApoioDataService.get().getKotaeConfiguracaoDao()
				.loadConfiguracaoByTipo(KotaeConfiguracao.TipoKotae.TOGURU);
		KotaeConfiguracao configuracao = (KotaeConfiguracao) collection.toArray()[0];
		selected.setConfiguracao(configuracao);
		AbstractAtribuicaoPassoItem atribuicaoPassoItem = ProcessoUtil.getPrimeiroAtendimento(selected);
		Passo passo = (null == atribuicaoPassoItem.getPasso()) ? (Passo) atribuicaoPassoItem
				: atribuicaoPassoItem.getPasso();
		selected.setAtendimento(passo.getAtendimentoOrigem());
		final Dialog window = new KaizenEditWindow<Chronos>(
				TM.translate(CoreTranslator.LIST_EDITOR_INSERT_TITLE, getArtigoEntidade(), getNomeEntidade(), selected),
				createForm(selected), String.valueOf("Iniciar"), false, super.editWindowWidth, super.editWindowHeight, new ControleTempos(),
				null, false) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void confirmAction(AbstractAtribuicaoPassoItem atribuicaoPassoItem) {
				try {
					if (null == atribuicaoPassoItem) {
						atribuicaoPassoItem = ProcessoUtil.getPrimeiroAtendimento(selected);
					}
					Chronos chronos = form.getObject();
					KaizenListSelector<Solicitacao> solicitacoesSelector = ((ChronosForm) form)
							.getSolicitacoesSelector();
					BeanGrid<Solicitacao> gridSolicitacoes = solicitacoesSelector.getGrid();
					Set<Solicitacao> selectedSolicitacoes = gridSolicitacoes.getSelectedItems();
					Object[] array = selectedSolicitacoes.toArray();
					if(array.length==0 && Strings.isEmpty(chronos.getTituloChronos())) {
						throw new PersistenceException(TM.translate(KaizenTranslator.CHRONOS_SOLICITACAO_OBRIGATORIA));
					}
					if(array.length > 0) {
						Solicitacao solicitacao = (Solicitacao) array[0];
						chronos.setSolicitacao(solicitacao);
					}
					if (!readOnly) {
						if (null != atribuicaoPassoItem) {
							Atendimento atendimentoOrigem = null;
							atendimentoOrigem = ((Passo) atribuicaoPassoItem).getAtendimentoOrigem();
							form.getObject().setAtendimento(atendimentoOrigem);
						}
						chronos.setDataInicio(new Date(System.currentTimeMillis()));
						validate();
						confirm(chronos);
						encerraDemaisAtividadesResponsavel(chronos);
						UI.getCurrent().navigate(ChronosPage.class);
					}
					close();
				} catch (Exception e) {
					ExceptionHandler.handleErrorException(e);
				}
			}

	public void confirm(Chronos obj) throws Exception {
		insertObject(obj);
		grid.addObject(obj);
		refreshButtons();
		notification(
				TM.translate(CoreTranslator.LIST_EDITOR_INSERT_FEEDBACK, getArtigoEntidade(), getNomeEntidade(), obj));
		toFocus.focus();
	}

	@Override
	protected void chronosAction() {
	}

	};window.open();}

	@Override
	protected void delete() {
		String msg = TM.translate(CoreTranslator.LIST_EDITOR_DELETE_CONFIRMATION, getArtigoEntidade(),
				getNomeEntidade(), getSelectedObject());
		YesNoDialog.execute(TM.translate(CoreTranslator.LIST_EDITOR_DELETE_TITLE, getArtigoEntidade(),
				getNomeEntidade(), getSelectedObject()), msg, () -> {
					Chronos selected = getSelectedObject();
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

	private void encerraDemaisAtividadesResponsavel(Chronos selected) {
		AbstractAtribuicaoPassoItem ultimoPassoItem = ProcessoUtil.getUltimoPassoItem(selected);
		Collection<Chronos> chronosAbertosResponsavel = ApoioDataService.get().geToguruDao()
				.loadTogurusByResponsavel(selected.getCodigoResponsavel());
		for (Chronos chronos : chronosAbertosResponsavel) {
			if(!selected.equals(chronos)) {
				chronos.setDataFim(new Date(System.currentTimeMillis()));
				chronos.setAtendimento(((Passo) ultimoPassoItem).getAtendimentoDestino());
				ApoioDataService.get().getToguruDao().update(chronos);
			}
		}
	}

}
