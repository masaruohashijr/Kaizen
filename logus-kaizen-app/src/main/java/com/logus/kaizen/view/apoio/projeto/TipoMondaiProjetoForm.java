/**
 *
 */
package com.logus.kaizen.view.apoio.projeto;

import java.util.ArrayList;
import java.util.Collection;

import com.logus.core.model.dialog.DialogButtonType;
import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.form.BeanComboBox;
import com.logus.core.view.form.BeanForm;
import com.logus.core.view.form.TabSheet;
import com.logus.kaizen.model.apoio.ApoioDataService;
import com.logus.kaizen.model.apoio.processo.Passo;
import com.logus.kaizen.model.apoio.processo.Processo;
import com.logus.kaizen.model.apoio.tipomondai.PapelPassoItem;
import com.logus.kaizen.model.apoio.tipomondai.TipoMondai;
import com.logus.kaizen.model.apoio.tipomondai.TipoMondaiProjeto;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.kaizen.view.list.PapelPassoListEditor;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.treegrid.TreeGrid;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class TipoMondaiProjetoForm extends BeanForm<TipoMondaiProjeto> {

	/**
	 *
	 */
	private static final long serialVersionUID = -5726930569029997422L;
	private TabSheet tabs;
	private PapelPassoListEditor<PapelPassoItem> papeisPassosEditor;
	private TreeGrid<Passo> treeGrid;

	/**
	 * Método construtor
	 *
	 * @param TipoMondai object
	 */
	protected TipoMondaiProjetoForm(TipoMondaiProjeto tipoMondaiProjeto) {
		super(tipoMondaiProjeto);
		add(fullHeight(createTabSheet()));
		populaGridPassos(tipoMondaiProjeto.getProcesso());
	}

	private Component createTabSheet() {
		tabs = new TabSheet();
		tabs.addTab("Dados Básicos", VaadinIcon.INFO_CIRCLE.create(), createDadosBasicosPage());
		tabs.addTab("Configuração do Processo", VaadinIcon.INFO_CIRCLE.create(), createPapeisPassosEditor());
		return tabs;
	}

	private Component createPapeisPassosEditor() {
		this.papeisPassosEditor = new PapelPassoListEditor<PapelPassoItem>(DialogButtonType.OK) {

			/**
			 *
			 */
			private static final long serialVersionUID = 4135382607413869340L;

			@Override
			protected PapelPassoForm createForm(PapelPassoItem papelPassoItem) {
				setEditWindowWidth(800);
				setEditWindowHeight(700);
				papelPassoItem.setTipoMondaiProjeto(getObject());
//				return new PapelPassoItemForm(papelPassoItem);
				return new PapelPassoForm(papelPassoItem);
			}

			@Override
			protected PapelPassoGrid createGrid() {
//				return new PapelPassoItemGrid();
				return new PapelPassoGrid();
			}

			@Override
			protected void deleteObject(PapelPassoItem papel) throws Exception {
				getObject().getPapeisPassosItens().remove(papel);
			}

			@Override
			protected Object getArtigoEntidade() {
				return TM.translate(CoreTranslator.ART_MAS_SING);
			}

			@Override
			protected Object getNomeEntidade() {
				return TM.translate(KaizenTranslator.PROJETO_PAPEL);
			}

			@Override
			protected String getNomeEntidadePlural() {
				return null;
			}

			@Override
			protected void insertObject(PapelPassoItem papelPasso) throws Exception {
				getObject().getPapeisPassosItens().add(papelPasso);
			}

			@Override
			protected void updateObject(PapelPassoItem papelPasso) throws Exception {
			}
		};
		papeisPassosEditor.setSizeFull();
		papeisPassosEditor.updateObjects(getObject().getPapeisPassosItens());
//		populaGridPassos(getObject().getProcesso());
		return papeisPassosEditor;
	}



	private Component createDadosBasicosPage() {
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.add(focus(fullWidth(createTipoMondaiBeanComboBox())));
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
		treeGrid.setSizeFull();
		return treeGrid;
	}

	private ComboBox<Processo> createProcessosBeanComboBox() {
		BeanComboBox<Processo> cbProcesso = createBeanComboBox(TM.translate(KaizenTranslator.PROCESSO),
				"processo", ApoioDataService.get().getProcessoDao().loadProcessos());
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

	private ComboBox<TipoMondai> createTipoMondaiBeanComboBox() {
		return createBeanComboBox(TM.translate(KaizenTranslator.TIPO_MONDAI), "tipoMondai",
				ApoioDataService.get().getTipoMondaiDao().loadTiposMondaiSemProcesso());
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
