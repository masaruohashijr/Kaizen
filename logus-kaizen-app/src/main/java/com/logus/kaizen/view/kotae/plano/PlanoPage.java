/**
 *
 */
package com.logus.kaizen.view.kotae.plano;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import com.logus.core.model.aut.login.LoginManager;
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
import com.logus.kaizen.model.apoio.ambiente.Ambiente;
import com.logus.kaizen.model.apoio.atendimento.Atendimento;
import com.logus.kaizen.model.apoio.processo.Passo;
import com.logus.kaizen.model.apoio.resolucao.Resolucao;
import com.logus.kaizen.model.apoio.tipomondai.AbstractAtribuicaoPassoItem;
import com.logus.kaizen.model.kotae.configuracao.KotaeConfiguracao;
import com.logus.kaizen.model.kotae.plano.Liberacao;
import com.logus.kaizen.model.kotae.plano.Plano;
import com.logus.kaizen.model.solicitacao.ItemSolicitacao;
import com.logus.kaizen.model.solicitacao.Solicitacao;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.kaizen.view.apoio.processo.ProcessoUtil;
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
@Route(value = "plano", layout = KaizenMainLayout.class)
public class PlanoPage extends KaizenAbstractListEditor<Plano> {

	/*
	 * Construtor
	 */
	public PlanoPage() {
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
	protected Collection<Plano> loadAll() {
		return ApoioDataService.get().getPlanoDao().loadPlanos();
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

	@Override
	protected void view() {
		Plano selected = getSelectedObject();
		selected = clone(selected);
		Dialog window = new EditWindow<Plano>(
				TM.translate(CoreTranslator.LIST_EDITOR_VIEW_TITLE, getArtigoEntidade(), getNomeEntidade(), selected),
				createForm(selected), DialogButtonType.CLOSE, true, super.editWindowWidth, super.editWindowHeight);
		window.open();
	}

	@Override
	protected void edit() {
		Plano selected = getSelectedObject();
		selected = clone(selected);
		Collection<AbstractAtribuicaoPassoItem> atribuicoesPassoItem = ProcessoUtil.getProximosPassosItens(selected);

		final Dialog window = new KaizenEditWindow<Plano>(
				TM.translate(CoreTranslator.LIST_EDITOR_EDIT_TITLE, getArtigoEntidade(), getNomeEntidade(), selected),
				createForm(selected), String.valueOf(""), false, super.editWindowWidth, super.editWindowHeight, new ControleTempos(),
				atribuicoesPassoItem, true) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void confirmAction(AbstractAtribuicaoPassoItem atribuicaoPassoItem) {
				try {
					if (!readOnly) {
						Plano plano = form.getObject();
						if (null != atribuicaoPassoItem) {
							// Atendimento Destino
							Passo passo = (null == atribuicaoPassoItem.getPasso())?(Passo) atribuicaoPassoItem:atribuicaoPassoItem.getPasso();
							Atendimento atendimentoDestino = passo.getAtendimentoDestino();
							plano.setAtendimento(atendimentoDestino);
							Resolucao resolucao = passo.getResolucao();
							plano.setResolucao(resolucao);
							plano = setAtendimentoDestino(plano);
						}
						validate();
						confirm(plano);
					}
					close();
				} catch (Exception e) {
					ExceptionHandler.handleErrorException(e);
				}

			}

			public void confirm(Plano obj) throws Exception {
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
	protected void insert(Plano selected, String caption, Focusable<?> toFocus) {
		Collection<KotaeConfiguracao> collection = ApoioDataService.get().getKotaeConfiguracaoDao()
				.loadConfiguracaoByTipo(KotaeConfiguracao.TipoKotae.PLANO);
		KotaeConfiguracao configuracao = (KotaeConfiguracao) collection.toArray()[0];
		selected.setConfiguracao(configuracao);
		final Dialog window = new KaizenEditWindow<Plano>(
				TM.translate(CoreTranslator.LIST_EDITOR_INSERT_TITLE, getArtigoEntidade(), getNomeEntidade(), selected),
				createForm(selected), String.valueOf(""), false, super.editWindowWidth, super.editWindowHeight, new ControleTempos(), null, true) {
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
							Atendimento atendimentoOrigem = null;
							atendimentoOrigem = ((Passo) atribuicaoPassoItem).getAtendimentoOrigem();
							resolucao = ((Passo) atribuicaoPassoItem).getResolucao();
							form.getObject().setAtendimento(atendimentoOrigem);
							form.getObject().setResolucao(resolucao);
						}
						validate();
						confirm(form.getObject());
					}
					close();
				} catch (Exception e) {
					ExceptionHandler.handleErrorException(e);
				}
			}

			public void confirm(Plano obj) throws Exception {
				insertObject(obj);
				grid.addObject(obj);
				refreshButtons();
				notification(TM.translate(CoreTranslator.LIST_EDITOR_INSERT_FEEDBACK, getArtigoEntidade(),
						getNomeEntidade(), obj));
				toFocus.focus();
			}

			@Override
			protected void chronosAction() {
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
					Plano selected = getSelectedObject();
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

	private Plano setAtendimentoDestino(Plano plano) {

		Atendimento atendimentoPlano = plano.getAtendimento();
		Collection<Solicitacao> solicitacoes = plano.getSolicitacoes();
		HashSet<Ambiente> ambientesSet = new HashSet<>();
		Collection<Liberacao> liberacoes = plano.getLiberacoes();

		// Carrega as liberações selecionadas pelo deployer.
		for (Liberacao liberacao : liberacoes) {
			ambientesSet.add(liberacao.getAmbiente());
		}

		// Itera cada uma das solicitações
		for (Solicitacao solicitacao : solicitacoes) {
			HashSet<Ambiente> copiaAmbientesSet = new HashSet<>(ambientesSet);
			Collection<ItemSolicitacao> itensSolicitacao = solicitacao.getItensSolicitacao();

			// Itera o arraylist contendo todos os itens de solicitação
			// de cada solicitação.
			for (ItemSolicitacao item: itensSolicitacao) {
				Ambiente ambienteSolicitado = item.getAmbiente();
				if(copiaAmbientesSet.contains(ambienteSolicitado)) {
					item.setAtendimento(atendimentoPlano);
					item.setDataUltimoAtendimento(new Date(System.currentTimeMillis()));
					item.setCodigoResponsavel(LoginManager.getAccessControl().getUser().getCodigo());
					copiaAmbientesSet.remove(ambienteSolicitado);
				}
			}
//			CollectionSynchronizer.synchronize(itensSolicitacao, solicitacao.getItensSolicitacao(), item -> {
//				item.setSolicitacao(solicitacao);
//			});

			Object[] array = copiaAmbientesSet.toArray();
			for (int i = 0; i < array.length; i++) {
				ItemSolicitacao itemSolicitacao = new ItemSolicitacao();
				itemSolicitacao.setAmbiente((Ambiente) array[i]);
				itemSolicitacao.setAtendimento(atendimentoPlano);
				itemSolicitacao.setDataUltimoAtendimento(new Date(System.currentTimeMillis()));
				itemSolicitacao.setCodigoResponsavel(LoginManager.getAccessControl().getUser().getCodigo());
				itemSolicitacao.setSolicitado(false);
				solicitacao.getItensSolicitacao().add(itemSolicitacao);
			}

		}

		return plano;
	}


}
