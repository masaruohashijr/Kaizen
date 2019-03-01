/**
 *
 */
package com.logus.kaizen.view.processo;

import com.logus.core.model.dialog.DialogButtonType;
import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.form.BeanForm;
import com.logus.core.view.form.TabSheet;
import com.logus.core.view.list.AbstractListEditor;
import com.logus.core.view.list.BeanGrid;
import com.logus.kaizen.model.processo.Passo;
import com.logus.kaizen.model.processo.Processo;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class ProcessoForm extends BeanForm<Processo> {

	/**
	 *
	 */
	private static final long serialVersionUID = -5726930569029997422L;
	private TabSheet tabs;
	private AbstractListEditor<Passo> passosEditor;

	/**
	 * Método construtor
	 *
	 * @param Processo object
	 */
	protected ProcessoForm(Processo object) {
		super(object);
		add(fullHeight(createTabSheet()));
	}

	private Component createTabSheet() {
		tabs = new TabSheet();
		tabs.addTab("Principal", VaadinIcon.INFO_CIRCLE.create(), createPrincipalPage());
		tabs.addTab("Passos", VaadinIcon.INFO_CIRCLE.create(), createPassosEditor());
		return tabs;
	}

	private Component createPassosEditor() {
		passosEditor = new AbstractListEditor<Passo>(DialogButtonType.OK) {

			private static final long serialVersionUID = 4135382607413869340L;

			@Override
			protected BeanForm<Passo> createForm(Passo object) {
				setEditWindowWidth(900);
				setEditWindowHeight(700);
				object.setProcesso(getObject());
				return new PassoForm(object);
			}

			@Override
			protected BeanGrid<Passo> createGrid() {
				return new PassoGrid();
			}

			@Override
			protected void deleteObject(Passo passo) throws Exception {
				getObject().getPassos().remove(passo);
			}

			@Override
			protected Object getArtigoEntidade() {
				return TM.translate(CoreTranslator.ART_MAS_SING);
			}

			@Override
			protected Object getNomeEntidade() {
				return TM.translate(KaizenTranslator.PROCESSO_PASSO);
			}

			@Override
			protected String getNomeEntidadePlural() {
				return TM.translate(KaizenTranslator.PROCESSO_PASSO_PLURAL);
			}

			@Override
			protected void insertObject(Passo passo) throws Exception {
				getObject().getPassos().add(passo);
			}

			@Override
			protected void updateObject(Passo passo) throws Exception {
			}
		};
		passosEditor.setSizeFull();
		passosEditor.getElement().removeChild(0);
		passosEditor.updateObjects(getObject().getPassos());
		return passosEditor;

	}

	private Component createPrincipalPage() {
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.add(focus(fullWidth(createNomeTextField())));
		vLayout.add(fullWidth(createDescricaoTextArea()));
		vLayout.add(createAtivoCheckBox());
		return vLayout;
	}

	/**
	 * Cria um campo textfield para inserção de um nome para o Processo.
	 *
	 * @return Component
	 */
	private TextField createNomeTextField() {
		return createTextField(TM.translate(KaizenTranslator.NOME), "nome");
	}

	/**
	 * Cria um campo textArea para inserção de uma descrição para o Processo.
	 *
	 * @return Component
	 */
	private TextArea createDescricaoTextArea() {
		return createTextArea(TM.translate(KaizenTranslator.PROCESSO_DESCRICAO), "descricao");
	}

	/**
	 * Cria um checkbox para definir se o processo estará ativado no momento da
	 * inserção, por padrão ele já está ativado.
	 *
	 * @return Component
	 */
	private Checkbox createAtivoCheckBox() {
		return createCheckBoxField(TM.translate(KaizenTranslator.ATIVO), "ativo");
	}

}
