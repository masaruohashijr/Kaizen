/**
 *
 */
package com.logus.kaizen.view.solicitacao;

import java.time.LocalDate;

import org.apache.commons.lang3.StringUtils;

import com.logus.core.model.aut.login.LoginManager;
import com.logus.core.model.dialog.DialogButtonType;
import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.form.BeanForm;
import com.logus.core.view.form.EnumComboBox;
import com.logus.core.view.form.TabSheet;
import com.logus.kaizen.app.KaizenListSelector;
import com.logus.kaizen.model.apoio.ApoioDataService;
import com.logus.kaizen.model.apoio.biblioteca.Biblioteca;
import com.logus.kaizen.model.solicitacao.ItemSolicitacao;
import com.logus.kaizen.model.solicitacao.RepositorioEnum;
import com.logus.kaizen.model.solicitacao.Solicitacao;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.kaizen.view.biblioteca.BibliotecaGrid;
import com.logus.kaizen.view.list.ItemSolicitacaoListEditor;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

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

	private TabSheet tabs;

	/**
	 * Método construtor
	 *
	 * @param Solicitacao object
	 */
	protected SolicitacaoForm(Solicitacao object) {
		super(object);
		add(expand(fullSize(createTabSheet())));
	}

	private Component createTabSheet() {
		tabs = new TabSheet();
		// Falta I18N nas Captions das Abas
		tabs.addTab("Dados Básicos", VaadinIcon.INFO_CIRCLE.create(), createDadosBasicosPage());
		tabs.addTab("Alterações", VaadinIcon.INFO_CIRCLE.create(), createAlteracoesPage());
		tabs.addTab("Ambientes", VaadinIcon.INFO_CIRCLE.create(), createAmbientesEditor());
		tabs.addTab("Repositório", VaadinIcon.INFO_CIRCLE.create(), createRepositorioPage());
		return tabs;
	}

	private Component createRepositorioPage() {
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.add(fullWidth(createRepositorioComboBoxEdit()));
		return vLayout;
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

	private Component createAlteracoesPage() {
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.add(focus(fullWidth(createProdutoComboBoxEdit())));
		vLayout.add(fullWidth(createVersaoTextFieldEdit()));
		vLayout.add(fullWidth(createBibliotecasSelector()));
		return vLayout;
	}

	private Component createBibliotecasSelector() {
		KaizenListSelector<Biblioteca> bibliotecasSelector = new KaizenListSelector<Biblioteca>("", false,
				new BibliotecaGrid(), SelectionMode.MULTI, ApoioDataService.get().getBibliotecaDao().loadBibliotecas(),
				getObject().getBibliotecas());
		bibliotecasSelector.setHeight("45%");
		return bibliotecasSelector;
	}

	private Component createDadosBasicosPage() {
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.add(focus(fullWidth(createIssueTextField())));
		vLayout.add(fullWidth(createTituloIssueTextField()));
		vLayout.add(fullWidth(createSolicitanteTextFieldEdit()));
		vLayout.add(fullWidth(createDataCommitTextField()));
		vLayout.add(fullWidth(createDataSolicitacaoTextField()));
		vLayout.add(createAtivoCheckBox());
		return vLayout;
	}

	private Component createRepositorioComboBoxEdit() {
		EnumComboBox<RepositorioEnum> cbRepositorio = createEnumComboBox(
				TM.translate(KaizenTranslator.SOLICITACAO_REPOSITORIO), "repositorio", RepositorioEnum.class);
		return cbRepositorio;
	}

	private Component createSolicitanteTextFieldEdit() {
		TextField tfNome = createTextField(TM.translate(KaizenTranslator.SOLICITACAO_NOME_SOLICITANTE),
				"nomeSolicitante");
		String nomeUsuario = LoginManager.getAccessControl().getUser().getNome();
		tfNome.setValue(StringUtils.capitalize(nomeUsuario));
		tfNome.setEnabled(false);
		return tfNome;
	}

	private Component createVersaoTextFieldEdit() {
		TextField tfVersao = createTextField(TM.translate(KaizenTranslator.SOLICITACAO_VERSAO), "versao");
		return tfVersao;
	}

	private Component createProdutoComboBoxEdit() {
		return createBeanComboBox(TM.translate(KaizenTranslator.PRODUTO), "produto",
				ApoioDataService.get().getProdutoDao().loadProdutos());
	}

	private Component createDataCommitTextField() {
		DatePicker dpCommit = createDateField(TM.translate(KaizenTranslator.SOLICITACAO_DATA_COMMIT), "dataCommit");
		dpCommit.setValue(LocalDate.now());
		return dpCommit;
	}

	private Component createDataSolicitacaoTextField() {
		DatePicker dpSolicitacao = createDateField(TM.translate(KaizenTranslator.SOLICITACAO_DATA_SOLICITACAO),
				"dataSolicitacao");
		dpSolicitacao.setValue(LocalDate.now());
		dpSolicitacao.setReadOnly(true);
		dpSolicitacao.setEnabled(false);
		return dpSolicitacao;
	}

	/**
	 * Cria um campo textfield para inserção de um nome para a solicitacao
	 *
	 * @return Component
	 */
	private TextField createIssueTextField() {
		return createTextField(TM.translate(KaizenTranslator.SOLICITACAO_ISSUE), "issue");
	}

	private TextField createTituloIssueTextField() {
		return createTextField(TM.translate(KaizenTranslator.SOLICITACAO_TITULO_ISSUE), "tituloIssue");
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
