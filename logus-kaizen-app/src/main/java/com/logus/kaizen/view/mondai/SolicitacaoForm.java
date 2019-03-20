
package com.logus.kaizen.view.mondai;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.logus.core.model.aut.login.LoginManager;
import com.logus.core.model.dialog.DialogButtonType;
import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.model.util.Formats;
import com.logus.core.view.app.LegacyAccessControl;
import com.logus.core.view.form.BeanComboBox;
import com.logus.core.view.form.BeanForm;
import com.logus.core.view.form.EnumComboBox;
import com.logus.core.view.form.ListSelector;
import com.logus.core.view.list.BeanGrid;
import com.logus.kaizen.app.HasButtonsToolbar;
import com.logus.kaizen.app.KaizenListSelector;
import com.logus.kaizen.model.apoio.ApoioDataService;
import com.logus.kaizen.model.apoio.UsuarioDataService;
import com.logus.kaizen.model.apoio.atendimento.Atendimento;
import com.logus.kaizen.model.apoio.biblioteca.Biblioteca;
import com.logus.kaizen.model.apoio.funcao.ItemFuncao;
import com.logus.kaizen.model.apoio.processo.Passo;
import com.logus.kaizen.model.apoio.processo.Processo;
import com.logus.kaizen.model.apoio.projeto.ItemPapel;
import com.logus.kaizen.model.apoio.projeto.Projeto;
import com.logus.kaizen.model.apoio.resolucao.Resolucao;
import com.logus.kaizen.model.apoio.tipomondai.TipoMondai;
import com.logus.kaizen.model.apoio.tipomondai.TipoMondaiProjeto;
import com.logus.kaizen.model.apoio.usuario.Usuario;
import com.logus.kaizen.model.chronos.Chronos;
import com.logus.kaizen.model.solicitacao.Comentario;
import com.logus.kaizen.model.solicitacao.ItemAtendimento;
import com.logus.kaizen.model.solicitacao.ItemSolicitacao;
import com.logus.kaizen.model.solicitacao.RepositorioEnum;
import com.logus.kaizen.model.solicitacao.Solicitacao;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.kaizen.view.apoio.biblioteca.BibliotecaGrid;
import com.logus.kaizen.view.chronos.ChronosSolicitacaoGrid;
import com.logus.kaizen.view.form.KaizenTabSheet;
import com.logus.kaizen.view.list.ComentarioListEditor;
import com.logus.kaizen.view.list.ItemSolicitacaoListEditor;
import com.logus.kaizen.view.list.KaizenAbstractListEditor;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Focusable;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Element;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class SolicitacaoForm extends BeanForm<Solicitacao> {

	/**
	 *
	 */
	private static final long serialVersionUID = -5726930569029997422L;

	private ItemSolicitacaoListEditor<ItemSolicitacao> itensEditor;
	private ComentarioListEditor<Comentario> comentarioEditor;
	private BeanComboBox<Projeto> beanCBProjeto;

	private KaizenTabSheet tabs;

	private BeanComboBox<TipoMondai> cbTipoMondai;

	private BeanComboBox<Atendimento> cbAtendimento;

	private KaizenAbstractListEditor<ItemAtendimento> itensAtendimentoEditor;

	private String codigoUsuario;

	private Collection<ItemFuncao> itensFuncoesUsuario;

	private Collection<ItemPapel> itensPapeisProjetoUsuario;

	private Solicitacao solicitacao;

	private KaizenAbstractListEditor<Chronos> itensTempusEditor;

	/**
	 * Método construtor
	 *
	 * @param Solicitacao object
	 */
	protected SolicitacaoForm(Solicitacao object) {
		super(object);
		if (null == codigoUsuario) {
			codigoUsuario = LoginManager.getAccessControl().getUser().getCodigo();
		}
		if (null == itensFuncoesUsuario) {
			itensFuncoesUsuario = ApoioDataService.get().getItemFuncaoDao().loadItensFuncoesPorUsuario(codigoUsuario);
		}
		if (null == itensPapeisProjetoUsuario) {
			itensPapeisProjetoUsuario = ApoioDataService.get().getItemPapelDao()
					.loadPapeisDoProjetoDoUsuario(codigoUsuario);
		}
		solicitacao = getObject();
		add(expand(fullSize(createTabSheet())));
		if (null != object.getId()) {
			cbTipoMondai.setEnabled(false);
			beanCBProjeto.setEnabled(false);
		} else {
			habilitaAbas(false);
			cbAtendimento.setEnabled(false);
		}
	}

	private void habilitaAbas(boolean enabled) {
		int count = (int) this.tabs.getChildren().count();
		for (int i = 1; count > 0 && i < count - 1; i++) {
			Tab tab = tabs.getTab(i);
			tab.setEnabled(enabled);
		}
	}

	private Component createTabSheet() {
		tabs = new KaizenTabSheet();
		tabs.addTab("Geral", VaadinIcon.INFO_CIRCLE.create(), createDadosBasicosPage());
		VerticalLayout temposPage = new VerticalLayout();
		tabs.addTab("Tempos", VaadinIcon.INFO_CIRCLE.create(), temposPage);
		VerticalLayout comentariosPage = new VerticalLayout();
		tabs.addTab("Comentários", VaadinIcon.INFO_CIRCLE.create(), comentariosPage);
		VerticalLayout historicoPage = new VerticalLayout();
		tabs.addTab("Histórico", VaadinIcon.INFO_CIRCLE.create(), historicoPage);
		VerticalLayout buildPage = new VerticalLayout();
		tabs.addTab("Build", VaadinIcon.INFO_CIRCLE.create(), buildPage);
		VerticalLayout ambientesPage = new VerticalLayout();
		tabs.addTab("Ambientes", VaadinIcon.INFO_CIRCLE.create(), ambientesPage);
		VerticalLayout dependenciasPage = new VerticalLayout();
		tabs.addTab("Dependências", VaadinIcon.INFO_CIRCLE.create(), dependenciasPage);
		VerticalLayout linksPage = new VerticalLayout();
		tabs.addTab("Links", VaadinIcon.INFO_CIRCLE.create(), linksPage);
		Tabs tbs = tabs.getTabs(tabs);
		// Falta I18N nas Captions das Abas
		Tab temposTab = tabs.getTab(1);
		Tab comentariosTab = tabs.getTab(2);
		Tab historicoTab = tabs.getTab(3);
		Tab buildTab = tabs.getTab(4);
		Tab ambientesTab = tabs.getTab(5);
		Tab dependenciasTab = tabs.getTab(6);
		Tab linksTab = tabs.getTab(7);
		tbs.addSelectedChangeListener(event -> {
			Component selectedPage = tbs.getSelectedTab();
			selectedPage.setVisible(true);
			if (selectedPage.equals(temposTab) && temposPage.getChildren().count() == 0) {
				temposPage.add(createTemposPage());
			} else if (selectedPage.equals(comentariosTab) && comentariosPage.getChildren().count() == 0) {
				Component comentariosEditor = createComentariosEditor();
				comentariosPage.add(comentariosEditor);
				comentariosPage.expand(comentariosEditor);
			} else if (selectedPage.equals(historicoTab) && historicoPage.getChildren().count() == 0) {
				historicoPage.add(createItensAtendimentoEditor());
			} else if (selectedPage.equals(buildTab) && buildPage.getChildren().count() == 0) {
				buildPage.add(createBuildPage());
			} else if (selectedPage.equals(ambientesTab) && ambientesPage.getChildren().count() == 0) {
				ambientesPage.add(createAmbientesEditor());
			} else if (selectedPage.equals(dependenciasTab) && dependenciasPage.getChildren().count() == 0) {
				dependenciasPage.add(fullSize(createDependenciasSelector()));
			} else if (selectedPage.equals(linksTab) && linksPage.getChildren().count() == 0) {
				linksPage.add(createAnexosPage());
			}
		});
		return tabs;
	}

	private Component createDependenciasSelector() {
		ListSelector<Solicitacao> dependenciasSelector = createListSelector("", new SolicitacaoPlanoGrid(),
				ApoioDataService.get().getSolicitacaoDao().loadSolicitacoes(), getObject().getDependencias());
		Component component = dependenciasSelector.getComponentAt(0);
		Element tfElement = component.getElement();
		tfElement.setAttribute("style", "width:100%");
		return dependenciasSelector;
	}

	private Component createItensAtendimentoEditor() {
		this.itensAtendimentoEditor = new KaizenAbstractListEditor<ItemAtendimento>(DialogButtonType.OK,
				HasButtonsToolbar.FALSE) {

			/**
			 *
			 */
			private static final long serialVersionUID = 4135382607413869340L;

			@Override
			protected void view() {
			}

			@Override
			protected void edit() {
			}

			@Override
			protected void insert(ItemAtendimento object, String caption, Focusable<?> toFocus) {
			}

			@Override
			protected void delete() {
			}

			@Override
			protected BeanForm<ItemAtendimento> createForm(ItemAtendimento object) {
				return null;
			}

			@Override
			protected void deleteObject(ItemAtendimento obj) throws Exception {
				getObject().getItensAtendimento().remove(obj);
			}

			@Override
			protected void insertObject(ItemAtendimento obj) throws Exception {
				getObject().getItensAtendimento().add((ItemAtendimento) obj);
			}

			@Override
			protected void updateObject(ItemAtendimento obj) throws Exception {

			}

			@Override
			protected Collection<ItemAtendimento> loadAll() {
				return null;
			}

			@Override
			protected BeanGrid<ItemAtendimento> createGrid() {
				return new ItemAtendimentoGrid();
			}

			@Override
			protected Object getNomeEntidade() {
				return TM.translate(KaizenTranslator.ITEM_ATENDIMENTO);
			}

			@Override
			protected String getNomeEntidadePlural() {
				return null;
			}

			@Override
			protected Object getArtigoEntidade() {
				return TM.translate(CoreTranslator.ART_MAS_SING);
			}
		};
		itensAtendimentoEditor.setSizeFull();
		itensAtendimentoEditor.updateObjects(getObject().getItensAtendimento());
		return itensAtendimentoEditor;
	}

	private Component createAnexosPage() {
		return new VerticalLayout();
	}

	private Component createTipoBeanComboBox() {
		cbTipoMondai = createBeanComboBox(TM.translate(KaizenTranslator.TIPO_MONDAI), "tipoMondai",
				ApoioDataService.get().getTipoMondaiDao().loadTiposMondai());
		cbTipoMondai.addValueChangeListener(e -> {
			TipoMondai tipoMondai = cbTipoMondai.getValue();
			if (null != tipoMondai.getProcesso()) {
				beanCBProjeto.setEnabled(false);
				habilitaAbas(true);
			} else {
				Collection<TipoMondaiProjeto> tiposMondaiProjeto = tipoMondai.getTiposMondaiProjeto();
				Collection<Projeto> projetos = new ArrayList<>();
				for (TipoMondaiProjeto tipoMondaiProjeto : tiposMondaiProjeto) {
					projetos.add(tipoMondaiProjeto.getProjeto());
				}
				this.beanCBProjeto.setItems(projetos);
			}
		});
		return cbTipoMondai;
	}

	private Component createProjetoBeanComboBox() {
		this.beanCBProjeto = createBeanComboBox(TM.translate(KaizenTranslator.PROJETO), "projeto",
				new ArrayList<Projeto>());
		this.beanCBProjeto.setEnabled(false);
		this.beanCBProjeto.addValueChangeListener(e -> {
			habilitaAbas(true);
		});
		return this.beanCBProjeto;
	}

	private Component createTemposPage() {
		VerticalLayout vLayout = new VerticalLayout();
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.add(focus(fullWidth(createDataParaFicarProntoDatePicker())));
		hLayout.add(fullWidth(createDataCommitDatePicker()));
		hLayout.add(fullWidth(createDataSolicitacaoTextField()));
		fullWidth(hLayout);
		vLayout.add(hLayout);
		vLayout.add(createTempusEditor());
		fullSize(vLayout);
		return vLayout;
	}

	private Component createTempusEditor() {
		this.itensTempusEditor = new KaizenAbstractListEditor<Chronos>(DialogButtonType.OK, HasButtonsToolbar.FALSE) {

			/**
			 *
			 */
			private static final long serialVersionUID = 4135382607413869340L;

			@Override
			protected void view() {
			}

			@Override
			protected void edit() {
			}

			@Override
			protected void insert(Chronos object, String caption, Focusable<?> toFocus) {
			}

			@Override
			protected void delete() {
			}

			@Override
			protected BeanForm<Chronos> createForm(Chronos object) {
				return null;
			}

			@Override
			protected void deleteObject(Chronos obj) throws Exception {
				getObject().getTogurus().remove(obj);
			}

			@Override
			protected void insertObject(Chronos obj) throws Exception {
				getObject().getTogurus().add((Chronos) obj);
			}

			@Override
			protected void updateObject(Chronos obj) throws Exception {

			}

			@Override
			protected Collection<Chronos> loadAll() {
				return null;
			}

			@Override
			protected BeanGrid<Chronos> createGrid() {
				return new ChronosSolicitacaoGrid();
			}

			@Override
			protected Object getNomeEntidade() {
				return TM.translate(KaizenTranslator.CHRONOS);
			}

			@Override
			protected String getNomeEntidadePlural() {
				return null;
			}

			@Override
			protected Object getArtigoEntidade() {
				return TM.translate(CoreTranslator.ART_MAS_SING);
			}
		};
		itensTempusEditor.setSizeFull();
		itensTempusEditor.updateObjects(ApoioDataService.get().getToguruDao().loadTogurusOrdenadoPorInicio());
		return itensTempusEditor;
	}

	private Component createDataParaFicarProntoDatePicker() {
		DatePicker dpFicarPronto = createDateField(TM.translate(KaizenTranslator.SOLICITACAO_DATA_FICAR_PRONTO),
				"dataFicarPronto");
		dpFicarPronto.setValue(LocalDate.now());
		return dpFicarPronto;
	}

	private Component createAmbientesEditor() {
		itensEditor = new ItemSolicitacaoListEditor<ItemSolicitacao>(DialogButtonType.OK) {

			/**
			 *
			 */
			private static final long serialVersionUID = 1173130487087082696L;

			@Override
			protected ItemSolicitacaoGrid createGrid() {
				return new ItemSolicitacaoGrid();
			}

			@Override
			protected ItemSolicitacaoForm createForm(ItemSolicitacao object) {
				setEditWindowWidth(900);
				setEditWindowHeight(700);
				object.setSolicitacao(getObject());
				return new ItemSolicitacaoForm(object);
			}

			@Override
			protected void deleteObject(ItemSolicitacao obj) throws Exception {
				getObject().getItensSolicitacao().remove(obj);
			}

			@Override
			protected void insertObject(ItemSolicitacao obj) throws Exception {
				getObject().getItensSolicitacao().add(obj);
			}

			@Override
			protected void updateObject(ItemSolicitacao obj) throws Exception {
				// O ItemSolicitacaoListEditor já atualizou o objeto no Grid, que é a mesma
				// instância do item do objeto alterado.
			}

			@Override
			protected Object getNomeEntidade() {
				return TM.translate(KaizenTranslator.ITEM_SOLICITACAO);
			}

			@Override
			protected Object getArtigoEntidade() {
				return TM.translate(CoreTranslator.ART_MAS_SING);
			}

			@Override
			protected String getNomeEntidadePlural() {
				return null; // sem título
			}

		};
		itensEditor.setSizeFull();
		itensEditor.updateObjects(getObject().getItensSolicitacao());
		return itensEditor;

	}

	private Component createComentariosEditor() {
		comentarioEditor = new ComentarioListEditor<Comentario>(DialogButtonType.OK) {

			/**
			 *
			 */
			private static final long serialVersionUID = 1173130487087082696L;

			@Override
			protected ComentarioGrid createGrid() {
				return new ComentarioGrid();
			}

			@Override
			protected ComentarioForm createForm(Comentario object) {
				setEditWindowWidth(900);
				setEditWindowHeight(700);
				object.setSolicitacao(getObject());
				return new ComentarioForm(object);
			}

			@Override
			protected void deleteObject(Comentario obj) throws Exception {
				getObject().getComentarios().remove(obj);
			}

			@Override
			protected void insertObject(Comentario obj) throws Exception {
				obj.setAutor(LegacyAccessControl.get().getUser().getCodigo());
				obj.setDataCriacao(new Date(System.currentTimeMillis()));
				getObject().getComentarios().add(obj);
			}

			@Override
			protected void updateObject(Comentario obj) throws Exception {
				obj.setAutor(LegacyAccessControl.get().getUser().getCodigo());
				obj.setDataAtualizacao(new Date(System.currentTimeMillis()));
				getObject().getComentarios().add(obj);
			}

			@Override
			protected Object getNomeEntidade() {
				return TM.translate(KaizenTranslator.COMENTARIO);
			}

			@Override
			protected Object getArtigoEntidade() {
				return TM.translate(CoreTranslator.ART_MAS_SING);
			}

			@Override
			protected String getNomeEntidadePlural() {
				return null; // sem título
			}

		};
		comentarioEditor.setSizeFull();
		Collection<Comentario> comentariosIrrestritos = ApoioDataService.get().getComentariosDao()
				.loadComentariosAutorizados(solicitacao, itensFuncoesUsuario, itensPapeisProjetoUsuario);
		comentarioEditor.updateObjects(comentariosIrrestritos);
		return comentarioEditor;

	}

	private Component createBuildPage() {
		VerticalLayout vLayout = new VerticalLayout();
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.add(focus(fullWidth(createVersaoTextFieldEdit())));
		hLayout.add(fullWidth(createProdutoComboBoxEdit()));
		vLayout.add(fullWidth(hLayout));
		vLayout.add(fullWidth(createRepositorioComboBoxEdit()));
		vLayout.add(fullWidth(fullHeight(createBibliotecasSelector())));
		vLayout.setSizeFull();
		return vLayout;
	}

	private Component createBibliotecasSelector() {
		KaizenListSelector<Biblioteca> bibliotecasSelector = new KaizenListSelector<Biblioteca>("", false,
				new BibliotecaGrid(), SelectionMode.MULTI, ApoioDataService.get().getBibliotecaDao().loadBibliotecas(),
				getObject().getBibliotecas());
		Element element = bibliotecasSelector.getElement();
		element.insertChild(0,
				new Html(String.format("<label>%s</label>", TM.translate(KaizenTranslator.BIBLIOTECA_PLURAL)))
						.getElement());
		bibliotecasSelector.setHeight("40%");
		return bibliotecasSelector;
	}

	private Component createDadosBasicosPage() {
		VerticalLayout vLayout = new VerticalLayout();
		HorizontalLayout hL1 = new HorizontalLayout();

		TextField mondaiTextField = createMondaiTextField();
		mondaiTextField.setWidth("10%");
		hL1.add(focus(mondaiTextField));

		TextField tituloMondaiTextField = createTituloMondaiTextField();
		tituloMondaiTextField.setWidth("90%");
		hL1.add(tituloMondaiTextField);

		fullWidth(hL1);

		HorizontalLayout hL2 = new HorizontalLayout();

		TextField tfSolicitante = createSolicitanteTextFieldEdit();
		tfSolicitante.setWidth("25%");
		hL2.add(tfSolicitante);

		ComboBox<Usuario> cbResponsavel = createResponsavelComboBox();
		cbResponsavel.setWidth("25%");
		hL2.add(cbResponsavel);

		BeanComboBox<Atendimento> cbAtendimento = createAtendimentoBeanComboBox();
		cbAtendimento.setWidth("25%");
		hL2.add(cbAtendimento);

		BeanComboBox<Resolucao> cbResolucao = createResolucaoTextFieldEdit();
		cbResolucao.setWidth("25%");
		hL2.add(cbResolucao);

		fullWidth(hL2);

		HorizontalLayout hL3 = new HorizontalLayout();
		Component cbTipoBean = createTipoBeanComboBox();
		fullWidth(cbTipoBean);
		hL3.add(cbTipoBean);
		Component cbProjeto = createProjetoBeanComboBox();
		fullWidth(cbProjeto);
		hL3.add(cbProjeto);

		fullWidth(hL3);

		TextArea taDescricao = createDescricaoTextArea();
		taDescricao.setWidth("100%");
		taDescricao.setHeight("20%");

		vLayout.add(hL1, hL2, hL3);
		vLayout.add(taDescricao);
		vLayout.add(checkBoxAtivo());
		return vLayout;
	}

	private ComboBox<Usuario> createResponsavelComboBox() {
		ComboBox<Usuario> cbUsuarios = new ComboBox<>();
		Collection<Usuario> usuarios = UsuarioDataService.get().getUsuarioDao().loadUsuarios();
		cbUsuarios.setItems(usuarios);
		cbUsuarios.setLabel(TM.translate(KaizenTranslator.SOLICITACAO_CODIGO_RESPONSAVEL));
		String codigoResponsavelAtual = getObject().getCodigoResponsavelAtual();
		if (null != codigoResponsavelAtual) {
			for (Usuario usuario : usuarios) {
				if (codigoResponsavelAtual.equals(usuario.getCodigo())) {
					cbUsuarios.setValue(usuario);
					break;
				}
			}
		}
		cbUsuarios.addValueChangeListener(e -> bindResponsavel(cbUsuarios.getValue()));
		return cbUsuarios;
	}

	private void bindResponsavel(Usuario usuario) {
		TextField tfResponsavel = createTextField("", "codigoResponsavelAtual");
		tfResponsavel.setValue(usuario.getCodigo());
	}

	private BeanComboBox<Atendimento> createAtendimentoBeanComboBox() {
		List<Atendimento> atendimentos = new ArrayList<>();
		atendimentos.addAll(populaArrayList());
		cbAtendimento = createBeanComboBox(TM.translate(KaizenTranslator.ATENDIMENTO), "atendimento", atendimentos);
		cbAtendimento.addValueChangeListener(e -> {
			ItemAtendimento itemAtendimento = new ItemAtendimento();
			itemAtendimento.setSolicitacao(getObject());
			itemAtendimento.setAtendimento(e.getValue());
			itemAtendimento.setDataInicioVigencia(new Date(System.currentTimeMillis()));
			getObject().getItensAtendimento().add(itemAtendimento);
		});
		return cbAtendimento;
	}

	private Set<Atendimento> populaArrayList() {
		List<TipoMondaiProjeto> tiposMondaiProjeto = (List<TipoMondaiProjeto>) getObject().getTipoMondai()
				.getTiposMondaiProjeto();
		Processo processo = null;
		for (TipoMondaiProjeto tipoMondaiProjeto : tiposMondaiProjeto) {
			Projeto projetoTMP = tipoMondaiProjeto.getProjeto();
			Projeto projeto = getObject().getProjeto();
			if (projeto.equals(projetoTMP)) {
				processo = tipoMondaiProjeto.getProcesso();
				break;
			}
		}
		Collection<Passo> passos = processo.getPassos();
		Set<Atendimento> atendimentos = new HashSet<>();
		for (Passo passo : passos) {
			atendimentos.add(passo.getAtendimentoOrigem());
			atendimentos.add(passo.getAtendimentoDestino());
		}
		return atendimentos;
	}

	private TextArea createDescricaoTextArea() {
		return createTextArea(TM.translate(KaizenTranslator.SOLICITACAO_DESCRICAO), "descricao");
	}

	private Component createRepositorioComboBoxEdit() {
		EnumComboBox<RepositorioEnum> cbRepositorio = createEnumComboBox(
				TM.translate(KaizenTranslator.SOLICITACAO_REPOSITORIO), "repositorio", RepositorioEnum.class);
		return cbRepositorio;
	}

	private TextField createSolicitanteTextFieldEdit() {
		TextField tfNome = createTextField(TM.translate(KaizenTranslator.SOLICITACAO_CODIGO_SOLICITANTE),
				"codigoSolicitante");
		tfNome.setEnabled(false);
		return tfNome;
	}

	private BeanComboBox<Resolucao> createResolucaoTextFieldEdit() {
		return createBeanComboBox(TM.translate(KaizenTranslator.RESOLUCAO), "resolucao",
				ApoioDataService.get().getResolucaoDao().loadResolucoes());
	}

	private Component createVersaoTextFieldEdit() {
		TextField tfVersao = createTextField(TM.translate(KaizenTranslator.SOLICITACAO_VERSAO), "versao");
		return tfVersao;
	}

	private Component createProdutoComboBoxEdit() {
		return createBeanComboBox(TM.translate(KaizenTranslator.PRODUTO), "produto",
				ApoioDataService.get().getProdutoDao().loadProdutos());
	}

	private Component createDataCommitDatePicker() {
		DatePicker dpCommit = createDateField(TM.translate(KaizenTranslator.SOLICITACAO_DATA_COMMIT), "dataCommit");
		dpCommit.setValue(LocalDate.now());
		return dpCommit;
	}

	private Component createDataSolicitacaoTextField() {
		TextField tfSolicitacao = new TextField();
		tfSolicitacao.setLabel(TM.translate(KaizenTranslator.SOLICITACAO_DATA_SOLICITACAO));
		tfSolicitacao.setEnabled(false);
		tfSolicitacao.setValue(Formats.format(getObject().getDataSolicitacao()).toString());
		if (null == getObject().getId()) {
			tfSolicitacao.setValue(Formats.format(LocalDate.now()).toString());
		}
		tfSolicitacao.setHeight("57px");
		fullHeight(tfSolicitacao);
		return tfSolicitacao;
	}

	/**
	 * Cria um campo textfield para inserção de um nome para a solicitacao
	 *
	 * @return Component
	 */
	private TextField createMondaiTextField() {
		return createTextField(TM.translate(KaizenTranslator.SOLICITACAO_MONDAI), "chaveMondai");
	}

	private TextField createTituloMondaiTextField() {
		return createTextField(TM.translate(KaizenTranslator.SOLICITACAO_TITULO_MONDAI), "tituloMondai");
	}

	/**
	 * Cria um checkbox para definir se o credor estará ativado no momento da
	 * inserção, por padrão ele já está ativado.
	 *
	 * @return Component
	 */
	private Checkbox checkBoxAtivo() {
		return createCheckBoxField(TM.translate(KaizenTranslator.ATIVO), "ativo");
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public void setItensFuncoesUsuario(Collection<ItemFuncao> itensFuncoesUsuario) {
		this.itensFuncoesUsuario = itensFuncoesUsuario;
	}

	public void setItensPapeisProjetoUsuario(Collection<ItemPapel> itensPapeisProjetoUsuario) {
		this.itensPapeisProjetoUsuario = itensPapeisProjetoUsuario;
	}

}
