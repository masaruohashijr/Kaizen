/**
 *
 */
package com.logus.kaizen.view.mondai;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import com.logus.core.model.aut.login.LoginManager;
import com.logus.core.model.dialog.DialogButtonType;
import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.dialog.NotificationDialog;
import com.logus.core.view.dialog.YesNoDialog;
import com.logus.core.view.exceptions.ExceptionHandler;
import com.logus.core.view.form.BeanForm;
import com.logus.core.view.list.BeanGrid;
import com.logus.core.view.list.EditWindow;
import com.logus.kaizen.app.HasButtonsToolbar;
import com.logus.kaizen.app.KaizenMainLayout;
import com.logus.kaizen.model.apoio.ApoioDataService;
import com.logus.kaizen.model.apoio.atendimento.Atendimento;
import com.logus.kaizen.model.apoio.funcao.ItemFuncao;
import com.logus.kaizen.model.apoio.processo.Passo;
import com.logus.kaizen.model.apoio.projeto.ItemPapel;
import com.logus.kaizen.model.apoio.resolucao.Resolucao;
import com.logus.kaizen.model.apoio.tipomondai.AbstractAtribuicaoPassoItem;
import com.logus.kaizen.model.chronos.Chronos;
import com.logus.kaizen.model.kotae.configuracao.KotaeConfiguracao;
import com.logus.kaizen.model.kotae.configuracao.KotaeConfiguracao.TipoKotae;
import com.logus.kaizen.model.solicitacao.ItemAtendimento;
import com.logus.kaizen.model.solicitacao.Solicitacao;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.kaizen.view.apoio.processo.ProcessoUtil;
import com.logus.kaizen.view.edit.KaizenEditWindow;
import com.logus.kaizen.view.list.ControleTempos;
import com.logus.kaizen.view.list.KaizenAbstractListEditor;
import com.vaadin.flow.component.Focusable;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.router.Route;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
@Route(value = "solicitacao", layout = KaizenMainLayout.class)
public class SolicitacaoPage extends KaizenAbstractListEditor<Solicitacao> {

	private KotaeConfiguracao configuracao;

	/*
	 * Construtor
	 */
	public SolicitacaoPage() {
		super(DialogButtonType.SAVE, HasButtonsToolbar.TRUE);
		setEditWindowWidth(1000);
		setEditWindowHeight(800);

		if (null == configuracao) {
			Collection<KotaeConfiguracao> configuracoes = ApoioDataService.get().getKotaeConfiguracaoDao()
					.loadConfiguracaoByTipo(TipoKotae.TOGURU);
			if (configuracoes.size() > 0) {
				configuracao = (KotaeConfiguracao) configuracoes.toArray()[0];
			}
		}
	}

	/**
	 * Serial
	 */
	private static final long serialVersionUID = -99267169911263536L;
	private String codigoUsuario;
	private Collection<ItemFuncao> itensFuncoesUsuario;
	private Collection<ItemPapel> itensPapeisProjetoUsuario;
	private SolicitacaoGrid solicitacaoGrid;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.view.list.ListViewPage#loadAll()
	 */
	@Override
	protected Collection<Solicitacao> loadAll() {
		return ApoioDataService.get().getSolicitacaoDao().loadSolicitacoes();
	}

	/**
	 * Método responsável por criar a tabela contendo todas as solicitações
	 * persistidas no banco
	 *
	 * @return BeanGrid<Solicitacao>
	 */
	@Override
	protected BeanGrid<Solicitacao> createGrid() {
		solicitacaoGrid = new SolicitacaoGrid();
		return solicitacaoGrid;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.view.list.AbstractListEditor#createForm(java.lang.Object)
	 */
	@Override
	protected BeanForm<Solicitacao> createForm(Solicitacao solicitacao) {
		SolicitacaoForm solicitacaoForm = new SolicitacaoForm(solicitacao);
		initAcessos(solicitacaoForm);
		solicitacaoForm.setItensPapeisProjetoUsuario(itensPapeisProjetoUsuario);
		return solicitacaoForm;
	}

	private void initAcessos(SolicitacaoForm solicitacaoForm) {
		codigoUsuario = LoginManager.getAccessControl().getUser().getCodigo();
		solicitacaoForm.setCodigoUsuario(codigoUsuario);
		itensFuncoesUsuario = ApoioDataService.get().getItemFuncaoDao().loadItensFuncoesPorUsuario(codigoUsuario);
		solicitacaoForm.setItensFuncoesUsuario(itensFuncoesUsuario);
		itensPapeisProjetoUsuario = ApoioDataService.get().getItemPapelDao()
				.loadPapeisDoProjetoDoUsuario(codigoUsuario);
	}

	private BeanForm<Solicitacao> createMondaiForm(Solicitacao solicitacao) {
		return new MondaiForm(solicitacao);
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

	@Override
	protected void view() {
		Solicitacao selected = getSelectedObject();
		selected = clone(selected);
		Dialog window = new EditWindow<Solicitacao>(
				TM.translate(CoreTranslator.LIST_EDITOR_VIEW_TITLE, getArtigoEntidade(), getNomeEntidade(), selected),
				createForm(selected), DialogButtonType.CLOSE, true, super.editWindowWidth, super.editWindowHeight);
		window.open();
	}

	@Override
	protected void edit() {
		Solicitacao selected = getSelectedObject();
		selected = clone(selected);
		SolicitacaoForm solicitacaoForm = (SolicitacaoForm) createForm(selected);
		initAcessos(solicitacaoForm);
		Collection<AbstractAtribuicaoPassoItem> atribuicoesPassoItem = ProcessoUtil.getProximosPassosItens(selected,
				itensPapeisProjetoUsuario, itensFuncoesUsuario, codigoUsuario);

		boolean chronosAtivo = false;
		if (null != configuracao) {
			Chronos toguru = new Chronos();
			toguru.setConfiguracao(configuracao);
			AbstractAtribuicaoPassoItem primeiroAtendimento = ProcessoUtil.getPrimeiroAtendimento(toguru);
			Atendimento atendimentoOrigem = ((Passo) primeiroAtendimento).getAtendimentoOrigem();
			Chronos toguruAtual = ApoioDataService.get().getToguruDao().loadUltimoToguruDoResponsavel(atendimentoOrigem,
					codigoUsuario);
			if (null != toguruAtual) {
				if (selected.equals(toguruAtual.getSolicitacao())) {
					chronosAtivo = true;
					selected.setChronosAtivo(chronosAtivo);
				}
			}
		}
		ControleTempos controleTempos = new ControleTempos(true, chronosAtivo);
		final Dialog window = new KaizenEditWindow<Solicitacao>(
				TM.translate(CoreTranslator.LIST_EDITOR_EDIT_TITLE, getArtigoEntidade(), getNomeEntidade(), selected),
				solicitacaoForm, String.valueOf(""), false, super.editWindowWidth, super.editWindowHeight,
				controleTempos, atribuicoesPassoItem, chronosAtivo) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void confirmAction(AbstractAtribuicaoPassoItem atribuicaoPassoItem) {
				try {
					if (!readOnly) {
						if (null != atribuicaoPassoItem) {
							// Atendimento Destino
							Passo passo = (null == atribuicaoPassoItem.getPasso()) ? (Passo) atribuicaoPassoItem
									: atribuicaoPassoItem.getPasso();
							Atendimento atendimentoDestino = passo.getAtendimentoDestino();
							form.getObject().setAtendimento(atendimentoDestino);
							// Varrer Itens de Atendimento
							Collection<ItemAtendimento> itensAtendimento = getObject().getItensAtendimento();
							// Date
							Date agora = new Date(System.currentTimeMillis());
							// set Data Fim Vigencia
							for (ItemAtendimento itemAtendimento : itensAtendimento) {
								if (null == itemAtendimento.getDataFimVigencia()) {
									itemAtendimento.setDataFimVigencia(agora);
									break;
								}
							}
							// Novo Item de Atendimento
							ItemAtendimento novoItemAtendimento = new ItemAtendimento();
							novoItemAtendimento.setSolicitacao(getObject());
							novoItemAtendimento.setAtendimento(atendimentoDestino);
							novoItemAtendimento.setDataInicioVigencia(agora);
							getObject().getItensAtendimento().add(novoItemAtendimento);
							// Resolução vinculada ao Passo do Processo
							form.getObject().setResolucao(passo.getResolucao());
						}
						validate();
						confirm(form.getObject());
					}
					close();
				} catch (Exception e) {
					ExceptionHandler.handleErrorException(e);
				}

			}

			public void confirm(Solicitacao obj) throws Exception {
				updateObject(obj);
				grid.updateObject(getSelectedObject(), obj);
				refreshButtons();
				notification(TM.translate(CoreTranslator.LIST_EDITOR_UPDATE_FEEDBACK, getArtigoEntidade(),
						getNomeEntidade(), obj));
				updateButton.focus();
			}

			@Override
			protected void chronosAction() {
				// Início Chronos
				Chronos toguru = new Chronos();
				if (null == codigoUsuario) {
					codigoUsuario = LoginManager.getAccessControl().getUser().getCodigo();
				}
				Date dataInicio, dataFim;
				dataInicio = dataFim = new Date(System.currentTimeMillis());
				if (null == configuracao) {
					Collection<KotaeConfiguracao> configuracoes = ApoioDataService.get().getKotaeConfiguracaoDao()
							.loadConfiguracaoByTipo(TipoKotae.TOGURU);
					configuracao = (KotaeConfiguracao) configuracoes.toArray()[0];
				}
				toguru = toguru.ativo(Boolean.TRUE).codigoResponsavel(codigoUsuario).dataInicio(dataInicio)
						.configuracao(configuracao).solicitacao(form.getObject());
				AbstractAtribuicaoPassoItem primeiraAtribuicaoPassoItem = ProcessoUtil.getPrimeiroAtendimento(toguru);
				Passo primeiroPasso = null;
				if (primeiraAtribuicaoPassoItem instanceof Passo) {
					primeiroPasso = (Passo) primeiraAtribuicaoPassoItem;
				} else {
					primeiroPasso = primeiraAtribuicaoPassoItem.getPasso();
				}
				Atendimento atendimentoOrigem = primeiroPasso.getAtendimentoOrigem();

				if (!form.getObject().isChronosAtivo()) {
					// inicio do insert do Chronos novo
					toguru = toguru.atendimento(atendimentoOrigem);
					ApoioDataService.get().getToguruDao().insert(toguru);
					// fim do insert do Chronos novo
					NotificationDialog.showInfo(TM.translate(KaizenTranslator.CHRONOS_INICIADO),
							getObject().getChaveMondai() + " em andamento.");
				} else {
					// Chronos é do Mondai editado no momento.
					Chronos ultimoToguruDoResponsavel = ApoioDataService.get().getToguruDao().loadUltimoToguruDoResponsavel(atendimentoOrigem, codigoUsuario);
					AbstractAtribuicaoPassoItem ultimaAtribuicaoPassoItem = ProcessoUtil.getUltimoPassoItem(ultimoToguruDoResponsavel);
					Passo ultimoPasso = null;
					if (ultimaAtribuicaoPassoItem instanceof Passo) {
						ultimoPasso = (Passo) ultimaAtribuicaoPassoItem;
					} else {
						ultimoPasso = ultimaAtribuicaoPassoItem.getPasso();
					}
					ultimoToguruDoResponsavel.setDataFim(dataFim);
					ultimoToguruDoResponsavel.setAtendimento(ultimoPasso.getAtendimentoDestino());
					ApoioDataService.get().getToguruDao().update(ultimoToguruDoResponsavel);
					NotificationDialog.showInfo(TM.translate(KaizenTranslator.CHRONOS_TERMINADO),
							"Atividade em " + form.getObject().getChaveMondai() + " concluída.");
				}
				encerraDemaisAtividadesResponsavel(toguru);
				HashMap<String, Button> mapaBotoes = ((SolicitacaoGrid) grid).getMapaBotoes();
				Button buttonMondaiIniciado = mapaBotoes.get(form.getObject().getChaveMondai());
				for (Button button : mapaBotoes.values()) {
					if (button.equals(buttonMondaiIniciado) && !form.getObject().isChronosAtivo()) {
						button.setText(TM.translate(KaizenTranslator.CHRONOS_TERMINO));
						button.getElement().getStyle().set("backgroundColor", "red");
					} else {
						button.setText(TM.translate(KaizenTranslator.CHRONOS_INICIO));
						button.getElement().getStyle().remove("backgroundColor");
					}
				}
				close();
			}

			private void encerraDemaisAtividadesResponsavel(Chronos selected) {
				AbstractAtribuicaoPassoItem ultimaAtribuicaoPassoItem = ProcessoUtil.getUltimoPassoItem(selected);
				Passo ultimoPasso = null;
				if (ultimaAtribuicaoPassoItem instanceof Passo) {
					ultimoPasso = (Passo) ultimaAtribuicaoPassoItem;
				} else {
					ultimoPasso = ultimaAtribuicaoPassoItem.getPasso();
				}
				Collection<Chronos> togurusAbertosResponsavel = ApoioDataService.get().geToguruDao()
						.loadTogurusByResponsavel(selected.getCodigoResponsavel());
				for (Chronos toguru : togurusAbertosResponsavel) {
					if (!selected.equals(toguru)
							&& !toguru.getAtendimento().equals(ultimoPasso.getAtendimentoDestino())) {
						toguru.setDataFim(new Date(System.currentTimeMillis()));
						toguru.setAtendimento(ultimoPasso.getAtendimentoDestino());
						ApoioDataService.get().getToguruDao().update(toguru);
					}
				}
			}

		};
		window.open();
	}

	@Override
	protected void insert(Solicitacao selected, String caption, Focusable<?> toFocus) {
		final Dialog window = new KaizenEditWindow<Solicitacao>(
				TM.translate(CoreTranslator.LIST_EDITOR_INSERT_TITLE, getArtigoEntidade(), getNomeEntidade(), selected),
				createMondaiForm(selected), String.valueOf(""), false, super.editWindowWidth, super.editWindowHeight,
				new ControleTempos(), null, true) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void confirmAction(AbstractAtribuicaoPassoItem atribuicaoPassoItem) {
				try {
					Resolucao resolucao = null;
					if (null == atribuicaoPassoItem) {
						atribuicaoPassoItem = ProcessoUtil.getPrimeiroAtendimento(selected);
					}
					if (!readOnly) {
						if (null != atribuicaoPassoItem) {
							Atendimento atendimentoOrigem;

							if (atribuicaoPassoItem instanceof Passo) {
								atendimentoOrigem = ((Passo) atribuicaoPassoItem).getAtendimentoOrigem();
								resolucao = ((Passo) atribuicaoPassoItem).getResolucao();
							} else {
								atendimentoOrigem = atribuicaoPassoItem.getPasso().getAtendimentoOrigem();
								resolucao = atribuicaoPassoItem.getPasso().getResolucao();
							}
							form.getObject().setResolucao(resolucao);
							form.getObject().setAtendimento(atendimentoOrigem);
							ItemAtendimento itemAtendimento = new ItemAtendimento();
							itemAtendimento.setSolicitacao(getObject());
							itemAtendimento.setAtendimento(atendimentoOrigem);
							itemAtendimento.setDataInicioVigencia(new Date(System.currentTimeMillis()));
							getObject().getItensAtendimento().add(itemAtendimento);
							form.getObject().setDataSolicitacao(new Date(System.currentTimeMillis()));
						}
						validate();
						confirm(form.getObject());
					}
					close();
				} catch (Exception e) {
					ExceptionHandler.handleErrorException(e);
				}
			}

			public void confirm(Solicitacao obj) throws Exception {
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
					Solicitacao selected = getSelectedObject();
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
