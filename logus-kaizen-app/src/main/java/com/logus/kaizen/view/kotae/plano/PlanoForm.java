/**
 *
 */
package com.logus.kaizen.view.kotae.plano;

import java.time.LocalDate;
import java.util.Collection;

import com.logus.core.model.aut.login.LoginManager;
import com.logus.core.model.dialog.DialogButtonType;
import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.form.BeanComboBox;
import com.logus.core.view.form.BeanForm;
import com.logus.core.view.form.ListSelector;
import com.logus.kaizen.model.apoio.ApoioDataService;
import com.logus.kaizen.model.kotae.configuracao.KotaeConfiguracao;
import com.logus.kaizen.model.kotae.configuracao.KotaeConfiguracao.TipoKotae;
import com.logus.kaizen.model.kotae.plano.Liberacao;
import com.logus.kaizen.model.kotae.plano.Plano;
import com.logus.kaizen.model.solicitacao.ItemSolicitacao;
import com.logus.kaizen.model.solicitacao.Solicitacao;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.kaizen.view.form.KaizenTabSheet;
import com.logus.kaizen.view.list.LiberacaoListEditor;
import com.logus.kaizen.view.mondai.ItemSolicitacaoGrid;
import com.logus.kaizen.view.mondai.SolicitacaoPlanoGrid;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
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
public class PlanoForm extends BeanForm<Plano> {

	/**
	 *
	 */
	private static final long serialVersionUID = -5726930569029997422L;

	/**
	 * TabSheet.
	 */
	private KaizenTabSheet tabs;

	private LiberacaoListEditor<Liberacao> liberacoesEditor;

	private TextArea taBugFixes;

	private TextArea taReleaseNotes;

	private BeanComboBox<KotaeConfiguracao> cbConfig;

	/**
	 * Método construtor
	 *
	 * @param Plano object
	 */
	@SuppressWarnings("deprecation")
	protected PlanoForm(Plano object) {
		super(object);
		add(expand(fullSize(createTabSheet())));
	}

	private Component createTabSheet() {
		tabs = new KaizenTabSheet();
		Component dadosBasicosPage = fullSize(createDadosBasicosPage());
		tabs.addTab("Dados Básicos", VaadinIcon.INFO_CIRCLE.create(), dadosBasicosPage);
		VerticalLayout solicitacoesPage = new VerticalLayout();
		tabs.addTab("Solicitações", VaadinIcon.INFO_CIRCLE.create(), solicitacoesPage);
		VerticalLayout releaseNotesPage = new VerticalLayout();
		tabs.addTab("Release Notes", VaadinIcon.INFO_CIRCLE.create(), releaseNotesPage);
		VerticalLayout liberacoesPage = new VerticalLayout();
		tabs.addTab("Liberações", VaadinIcon.INFO_CIRCLE.create(), liberacoesPage);
		VerticalLayout anotacoesPage = new VerticalLayout();
		tabs.addTab("Anotações", VaadinIcon.INFO_CIRCLE.create(), anotacoesPage);
		Tabs tbs = tabs.getTabs(tabs);
		Tab solicitacoesTab = tabs.getTab(1);
		Tab releaseNotesTab = tabs.getTab(2);
		Tab liberacoesTab = tabs.getTab(3);
		Tab anotacoesTab = tabs.getTab(4);
		tbs.addSelectedChangeListener(event -> {
			Component selectedPage = tbs.getSelectedTab();
			selectedPage.setVisible(true);
			if (selectedPage.equals(solicitacoesTab) && solicitacoesPage.getChildren().count()==0) {
				solicitacoesPage.add(fullSize(createSolicitacoesSelector()));
			} else if (selectedPage.equals(releaseNotesTab) && releaseNotesPage.getChildren().count()==0) {
				releaseNotesPage.add(fullSize(createReleaseNotesSelector()));
			} else if (selectedPage.equals(liberacoesTab) && liberacoesPage.getChildren().count()==0) {
				liberacoesPage.add(fullSize(createLiberacoesEditor()));
			} else if (selectedPage.equals(anotacoesTab) && anotacoesPage.getChildren().count()==0) {
				anotacoesPage.add(fullSize(createAnotacoesEditor()));
				String value = populateAnotacoesTextAreas(getObject().getSolicitacoes());
				taBugFixes.setValue(value);
				taReleaseNotes.setValue(value);
			}
		});

		return tabs;
	}

	private Component createConfiguracaoComboBoxEdit() {
		Collection<KotaeConfiguracao> configuracoes = ApoioDataService.get().getKotaeConfiguracaoDao().loadConfiguracoes();
		this.cbConfig = createBeanComboBox(TM.translate(KaizenTranslator.KOTAE_CONFIGURACAO), "configuracao",
				configuracoes);
		int size = configuracoes.size();
		switch (size) {
		case 0:
			break;
		case 1:
			KotaeConfiguracao configuracao = (KotaeConfiguracao) configuracoes.toArray()[0];
			if(configuracao.getTipoKotae() == TipoKotae.PLANO) {
				this.cbConfig.setValue(configuracao);
				this.cbConfig.setEnabled(false);
				break;
			}
		default:
			this.cbConfig.setEnabled(true);
		}
		return cbConfig;
	}

	private String populateAnotacoesTextAreas(Collection<Solicitacao> solicitacoes) {
		StringBuilder sb = new StringBuilder();
		for (Solicitacao solicitacao : solicitacoes) {
			sb.append(solicitacao.getTituloMondai().concat(System.lineSeparator()));
		}
		return sb.toString();
	}

	private Component createDadosBasicosPage() {
		VerticalLayout vLayout = new VerticalLayout();
		FormLayout frmLayout = new FormLayout();
		frmLayout.add(focus(fullWidth(createReferenciaTextField())));
		frmLayout.add(fullWidth(createVersaoTextFieldEdit()));
		frmLayout.add(fullWidth(createDataPlanejamentoTextField()));
		frmLayout.add(fullWidth(createResponsavelTextFieldEdit()));
		frmLayout.add(fullWidth(createDataIntegracaoTextField()));
		frmLayout.add(fullWidth(createConfiguracaoComboBoxEdit()));
		vLayout.add(frmLayout);
		vLayout.add(fullSize(createDescricaoTextArea()));
		vLayout.add(createAtivoCheckBox());
		return vLayout;
	}

	/**
	 * Cria um campo textArea para inserção de uma descrição para a biblioteca.
	 *
	 * @return Component
	 */
	private TextArea createDescricaoTextArea() {
		return createTextArea(TM.translate(KaizenTranslator.PLANO_DESCRICAO), "descricao");
	}

	private Component createLiberacoesEditor() {
		liberacoesEditor = new LiberacaoListEditor<Liberacao>(DialogButtonType.OK) {

			private static final long serialVersionUID = 1L;

			@Override
			protected LiberacaoGrid createGrid() {
				return new LiberacaoGrid();
			}

			@Override
			protected LiberacaoForm createForm(Liberacao object) {
				setEditWindowWidth(900);
				setEditWindowHeight(700);
				object.setPlano(getObject());
				return new LiberacaoForm(object);
			}

			@Override
			protected void deleteObject(Liberacao obj) throws Exception {
				getObject().getLiberacoes().remove(obj);
			}

			@Override
			protected void insertObject(Liberacao obj) throws Exception {
//				Date agora = new Date(System.currentTimeMillis());
//				obj.setDataLiberacao(agora);
				getObject().getLiberacoes().add(obj);
			}

			@Override
			protected void updateObject(Liberacao obj) throws Exception {
				// O ItemSolicitacaoListEditor já atualizou o objeto no Grid, que é a mesma
				// instância do item do objeto alterado.
			}

			@Override
			protected Object getNomeEntidade() {
				return TM.translate(KaizenTranslator.LIBERACAO);
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
		liberacoesEditor.setSizeFull();
		liberacoesEditor.updateObjects(getObject().getLiberacoes());
		return liberacoesEditor;

	}

	private Component createAnotacoesEditor() {
		VerticalLayout vLayout = new VerticalLayout();
		createBugFixesTextArea();
		vLayout.add(fullWidth(taBugFixes));
		createReleaseNotesTextArea();
		vLayout.add(fullWidth(taReleaseNotes));
		return vLayout;
	}

	/**
	 * Contém os itens da solicitação
	 * @return
	 */
	private Component createSolicitacoesSelector() {
		Collection<Solicitacao> solicitacoes = getObject().getSolicitacoes();
		Solicitacao srcSolicitacao = new Solicitacao();
		for (Solicitacao solicitacao : solicitacoes) {
			if(solicitacao.getPlano().equals(getObject())) {
				 srcSolicitacao = solicitacao;
				 break;
			}
		}
		ListSelector<ItemSolicitacao> solicitacoesSelector = createListSelector("", new ItemSolicitacaoGrid(),
				ApoioDataService.get().getItemSolicitacaoDao().loadItensSolicitacao(), srcSolicitacao.getItensSolicitacao());
		Component component = solicitacoesSelector.getComponentAt(0);
		Element tfElement = component.getElement();
		tfElement.setAttribute("style", "width:100%");
		return solicitacoesSelector;
	}

	private Component createReleaseNotesSelector() {
		ListSelector<Solicitacao> releaseNotesSelector = createListSelector("", new SolicitacaoPlanoGrid(),
				ApoioDataService.get().getSolicitacaoDao().loadSolicitacoes(), getObject().getSolicitacoes());
		Component component = releaseNotesSelector.getComponentAt(0);
		Element tfElement = component.getElement();
		tfElement.setAttribute("style", "width:100%");
		return releaseNotesSelector;
	}

	private void createReleaseNotesTextArea() {
		taReleaseNotes = createTextArea("Release Notes", "releaseNotes");
		taReleaseNotes.setHeight("40%");
	}

	private void createBugFixesTextArea() {
		taBugFixes = createTextArea("Bug Fixes", "bugFixes");
		taBugFixes.setHeight("40%");
	}

	private Component createReferenciaTextField() {
		return createTextField(TM.translate(KaizenTranslator.PLANO_REFERENCIA), "referencia");
	}

	private Component createResponsavelTextFieldEdit() {
		TextField tfCodigoResponsavel = createTextField(TM.translate(KaizenTranslator.PLANO_CODIGO_RESPONSAVEL_OBRIGATORIO), "codigoResponsavel");
		String codigoResponsavel = LoginManager.getAccessControl().getUser().getCodigo();
		tfCodigoResponsavel.setValue(codigoResponsavel);
		tfCodigoResponsavel.setEnabled(false);
		return tfCodigoResponsavel;
	}

	private Component createVersaoTextFieldEdit() {
		TextField tfVersao = createTextField(TM.translate(KaizenTranslator.PLANO_VERSAO), "versao");
		return tfVersao;
	}

	private Component createDataPlanejamentoTextField() {
		DatePicker dpPlanejamento = createDateField(TM.translate(KaizenTranslator.PLANO_DATA_PLANEJAMENTO),
				"dataPlanejamento");
		dpPlanejamento.setValue(LocalDate.now());
		return dpPlanejamento;
	}

	private Component createDataIntegracaoTextField() {
		DatePicker dpIntegracao = createDateField(TM.translate(KaizenTranslator.PLANO_DATA_INTEGRACAO),
				"dataIntegracao");
		dpIntegracao.setEnabled(false);
		return dpIntegracao;
	}

	/**
	 * Cria um checkbox para definir se o credor estará ativado no momento da
	 * inserção, por padrão ele já está ativado.
	 *
	 * @return Component
	 */
	private Checkbox createAtivoCheckBox() {
		return createCheckBoxField(TM.translate(KaizenTranslator.ATIVO), "ativo");
	}

}
