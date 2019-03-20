/**
 *
 */
package com.logus.kaizen.view.apoio.tipomondai;

import java.util.ArrayList;
import java.util.Collection;

import com.logus.core.model.dialog.DialogButtonType;
import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.form.BeanComboBox;
import com.logus.core.view.form.BeanForm;
import com.logus.kaizen.model.apoio.ApoioDataService;
import com.logus.kaizen.model.apoio.processo.Passo;
import com.logus.kaizen.model.apoio.processo.Processo;
import com.logus.kaizen.model.apoio.tipomondai.FuncaoPassoItem;
import com.logus.kaizen.model.apoio.tipomondai.TipoMondai;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.kaizen.view.apoio.projeto.FuncaoPassoForm;
import com.logus.kaizen.view.apoio.projeto.FuncaoPassoGrid;
import com.logus.kaizen.view.form.KaizenTabSheet;
import com.logus.kaizen.view.list.FuncaoPassoListEditor;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.treegrid.TreeGrid;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class TipoMondaiForm extends BeanForm<TipoMondai> {

	/**
	 *
	 */
	private static final long serialVersionUID = -5726930569029997422L;
	private KaizenTabSheet tabs;
	private TreeGrid<Passo> treeGrid;
	private FuncaoPassoListEditor<FuncaoPassoItem> funcoesPassosEditor;

	/**
	 * Método construtor
	 *
	 * @param TipoMondai object
	 */
	protected TipoMondaiForm(TipoMondai tipoMondai) {
		super(tipoMondai);
		add(createTabSheet());
		populaGridPassos(tipoMondai.getProcesso());
	}

	private Component createTabSheet() {
		tabs = new KaizenTabSheet();
		Component dadosBasicosPage = fullSize(createDadosBasicosPage());
		tabs.addTab("Dados Básicos", VaadinIcon.INFO_CIRCLE.create(), dadosBasicosPage);
		VerticalLayout funcoesPassosPage = new VerticalLayout();
		tabs.addTab("Configuração do Processo", VaadinIcon.INFO_CIRCLE.create(), funcoesPassosPage);
		Tabs tbs = tabs.getTabs(tabs);
		Tab funcoesPassosTab = tabs.getTab(1);
		tbs.addSelectedChangeListener(event -> {
			Component selectedPage = tbs.getSelectedTab();
			selectedPage.setVisible(true);
			if (selectedPage.equals(funcoesPassosTab) && funcoesPassosPage.getChildren().count() == 0) {
				funcoesPassosPage.add(fullSize(createFuncoesPassosEditor()));
			}
		});
		return tabs;
	}

	private Component createDadosBasicosPage() {
		VerticalLayout vLayout = new VerticalLayout();
		TextField tfNome = createNomeTextField();
		tfNome.setHeight("75px");
		vLayout.add(focus(fullWidth(tfNome)));
		TextArea taDescricao = createDescricaoTextArea();
		taDescricao.setHeight("20%");
		vLayout.add(fullWidth(taDescricao));
		vLayout.add(fullWidth(createProcessosBeanComboBox()));
		vLayout.add(fullWidth(createPassosGrid()));
		vLayout.add(createAtivoCheckBox());
		return vLayout;
	}

	private Component createPassosGrid() {
		this.treeGrid = new TreeGrid<>();
		treeGrid.addColumn(Passo::getNome).setFlexGrow(1).setHeader("Nome");
		treeGrid.addColumn(Passo::getTransicao).setFlexGrow(1).setHeader("Transição");
		treeGrid.addColumn(Passo::getAtendimentoOrigem).setFlexGrow(1).setHeader("Origem");
		treeGrid.addColumn(Passo::getAtendimentoDestino).setFlexGrow(1).setHeader("Destino");
		treeGrid.setHeight("40%");
		treeGrid.setWidth("100%");
		return treeGrid;
	}

	private ComboBox<Processo> createProcessosBeanComboBox() {
		BeanComboBox<Processo> cbProcesso = createBeanComboBox(TM.translate(KaizenTranslator.PROCESSO), "processo",
				ApoioDataService.get().getProcessoDao().loadProcessos());
		cbProcesso.addValueChangeListener(ev -> {
			populaGridPassos(ev.getValue());
		});
		return cbProcesso;

	}

	private void populaGridPassos(Processo processo) {
		if (null != processo) {
			Collection<Passo> passos = processo.getPassos();
			this.treeGrid.setItems(passos);
		} else {
			this.treeGrid.setItems(new ArrayList<>());
		}
		treeGrid.getDataProvider().refreshAll();
	}

	private Component createFuncoesPassosEditor() {
		this.funcoesPassosEditor = new FuncaoPassoListEditor<FuncaoPassoItem>(DialogButtonType.OK) {

			/**
			 *
			 */
			private static final long serialVersionUID = 4135382607413869340L;

			@Override
			protected FuncaoPassoForm createForm(FuncaoPassoItem funcaoPassoItem) {
				setEditWindowWidth(1024);
				setEditWindowHeight(768);
				funcaoPassoItem.setTipoMondai(getObject());
				return new FuncaoPassoForm(funcaoPassoItem);
			}

			@Override
			protected FuncaoPassoGrid createGrid() {
				return new FuncaoPassoGrid();
			}

			@Override
			protected void deleteObject(FuncaoPassoItem papel) throws Exception {
				getObject().getFuncoesPassos().remove(papel);
			}

			@Override
			protected Object getArtigoEntidade() {
				return TM.translate(CoreTranslator.ART_FEM_SING);
			}

			@Override
			protected Object getNomeEntidade() {
				return TM.translate(KaizenTranslator.FUNCAO);
			}

			@Override
			protected String getNomeEntidadePlural() {
				return null;
			}

			@Override
			protected void insertObject(FuncaoPassoItem funcaoPasso) throws Exception {
				getObject().getFuncoesPassos().add(funcaoPasso);
			}

			@Override
			protected void updateObject(FuncaoPassoItem funcaoPasso) throws Exception {
			}
		};
		funcoesPassosEditor.setSizeFull();
		funcoesPassosEditor.updateObjects(getObject().getFuncoesPassos());
		return funcoesPassosEditor;
	}

	/**
	 * Cria um campo textfield para inserção de um nome para a biblioteca
	 *
	 * @return Component
	 */
	private TextField createNomeTextField() {
		return createTextField(TM.translate(KaizenTranslator.NOME), "nome");
	}

	/**
	 * Cria um campo textArea para inserção de uma descrição para a biblioteca.
	 *
	 * @return Component
	 */
	private TextArea createDescricaoTextArea() {
		return createTextArea(TM.translate(KaizenTranslator.TIPO_MONDAI_DESCRICAO), "descricao");
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
