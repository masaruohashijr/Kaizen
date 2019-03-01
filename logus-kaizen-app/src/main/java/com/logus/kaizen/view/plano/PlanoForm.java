/**
 *
 */
package com.logus.kaizen.view.plano;

import java.time.LocalDate;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

import com.logus.kaizen.model.apoio.ApoioDataService;
import com.logus.kaizen.model.plano.Liberacao;
import com.logus.kaizen.model.plano.Plano;
import com.logus.kaizen.model.solicitacao.Solicitacao;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.core.model.aut.login.LoginManager;
import com.logus.core.model.dialog.DialogButtonType;
import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.form.BeanForm;
import com.logus.core.view.form.ListSelector;
import com.logus.kaizen.view.form.KaizenTabSheet;
import com.logus.kaizen.view.list.LiberacaoListEditor;
import com.logus.kaizen.view.solicitacao.SolicitacaoGrid;
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
		Tabs tbs = tabs.getTabs(tabs);
		tabs.addTab("Dados Básicos", VaadinIcon.INFO_CIRCLE.create(), fullSize(createDadosBasicosPage()));
		tabs.addTab("Solicitações", VaadinIcon.INFO_CIRCLE.create(), createSolicitacoesSelector());
		tabs.addTab("Liberações", VaadinIcon.INFO_CIRCLE.create(), fullSize(createLiberacoesEditor()));
		tabs.addTab("Anotações", VaadinIcon.INFO_CIRCLE.create(), fullSize(createAnotacoesEditor()));
		Tab anotacoesTab = tabs.getTab(3);
		tbs.addSelectedChangeListener(event -> {
			Component selectedPage = tbs.getSelectedTab();
			selectedPage.setVisible(true);
			if (selectedPage.equals(anotacoesTab)) {
				String value = populateAnotacoesTextAreas(getObject().getSolicitacoes());
				taBugFixes.setValue(value);
				taReleaseNotes.setValue(value);
			}
		});
		return tabs;
	}

	private String populateAnotacoesTextAreas(Collection<Solicitacao> solicitacoes) {
		StringBuilder sb = new StringBuilder();
		for (Solicitacao solicitacao : solicitacoes) {
			sb.append(solicitacao.getIssue().concat(" - ").concat(solicitacao.getTituloIssue())
					.concat(System.lineSeparator()));
		}
		return sb.toString();
	}

	private Component createDadosBasicosPage() {
		FormLayout frmLayout = new FormLayout();
		frmLayout.add(focus(fullWidth(createReferenciaTextField())));
		frmLayout.add(fullWidth(createVersaoTextFieldEdit()));
		frmLayout.add(fullWidth(createDescricaoTextArea()));
		frmLayout.add(fullWidth(createGCMTextFieldEdit()));
		frmLayout.add(fullWidth(createDataPlanejamentoTextField()));
		frmLayout.add(fullWidth(createDataIntegracaoTextField()));
		frmLayout.add(createAtivoCheckBox());
		return frmLayout;
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
		System.out.println("PlanoForm.createLiberacoesEditor().Linha:138 " + getObject());
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

	private Component createSolicitacoesSelector() {
		ListSelector<Solicitacao> solicitacoesSelector = createListSelector("", new SolicitacaoGrid(),
				ApoioDataService.get().getSolicitacaoDao().loadSolicitacoes(), getObject().getSolicitacoes());
		Component component = solicitacoesSelector.getComponentAt(0);
		Element tfElement = component.getElement();
		tfElement.setAttribute("style", "width:100%");
		return solicitacoesSelector;
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

	private Component createGCMTextFieldEdit() {
		TextField tfNome = createTextField(TM.translate(KaizenTranslator.PLANO_NOME_GCM_OBRIGATORIO), "nomGcm");
		String nomeUsuario = LoginManager.getAccessControl().getUser().getNome();
		tfNome.setValue(StringUtils.capitalize(nomeUsuario));
		tfNome.setEnabled(false);
		return tfNome;
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
