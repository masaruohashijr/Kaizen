/**
 *
 */
package com.logus.kaizen.view.cliente;

import com.logus.kaizen.model.apoio.ambiente.Ambiente;
import com.logus.kaizen.model.apoio.cliente.Cliente;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.core.model.dialog.DialogButtonType;
import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.form.BeanForm;
import com.logus.core.view.form.TabSheet;
import com.logus.core.view.list.AbstractListEditor;
import com.logus.core.view.list.BeanGrid;
import com.logus.kaizen.view.ambiente.AmbienteClienteForm;
import com.logus.kaizen.view.ambiente.AmbienteClienteGrid;
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
public class ClienteForm extends BeanForm<Cliente> {

	/**
	 *
	 */
	private static final long serialVersionUID = -5726930569029997422L;

	private AbstractListEditor<Ambiente> ambientesEditor;

	private TabSheet tabs;

	/**
	 * Método construtor
	 *
	 * @param Cliente object
	 */
	protected ClienteForm(Cliente object) {
		super(object);
		add(fullHeight(createTabSheet()));
	}

	private Component createTabSheet() {
		tabs = new TabSheet();
		tabs.addTab("Cliente", VaadinIcon.INFO_CIRCLE.create(), createPrincipalPage());
		tabs.addTab("Ambientes", VaadinIcon.INFO_CIRCLE.create(), createAmbientesEditor());
		return tabs;
	}

	private Component createPrincipalPage() {
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.add(focus(fullWidth(createNomeTextField())));
		vLayout.add(fullWidth(createDescricaoTextArea()));
		vLayout.add(fullWidth(createAtivoCheckBox()));
		return vLayout;
	}

	private Component createAmbientesEditor() {
		ambientesEditor = new AbstractListEditor<Ambiente>(DialogButtonType.OK) {

			/**
			 *
			 */
			private static final long serialVersionUID = -5883295672429545251L;

			@Override
			protected BeanGrid<Ambiente> createGrid() {
				return new AmbienteClienteGrid();
			}

			@Override
			protected BeanForm<Ambiente> createForm(Ambiente object) {
				object.setCliente(getObject());
				return new AmbienteClienteForm(object);
			}

			@Override
			protected void deleteObject(Ambiente obj) throws Exception {
				getObject().getAmbientes().remove(obj);
			}

			@Override
			protected void insertObject(Ambiente obj) throws Exception {
				getObject().getAmbientes().add(obj);
			}

			@Override
			protected void updateObject(Ambiente obj) throws Exception {
				// O AmbienteEditor já atualizou o objeto no Grid, que é a mesma
				// instância do item do objeto alterado.
			}

			@Override
			protected Object getNomeEntidade() {
				return TM.translate(KaizenTranslator.AMBIENTE);
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
		ambientesEditor.setSizeFull();
		ambientesEditor.updateObjects(getObject().getAmbientes());
		return ambientesEditor;
	}

	/**
	 * Cria um campo textfield para inserção de um nome para o cliente
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
		return createTextArea(TM.translate(KaizenTranslator.CLIENTE_DESCRICAO), "descricao");
	}

	/**
	 * Cria um checkbox para definir se o cliente estará ativado no momento da
	 * inserção, por padrão ele já está ativado.
	 *
	 * @return Component
	 */
	private Checkbox createAtivoCheckBox() {
		return createCheckBoxField(TM.translate(KaizenTranslator.ATIVO), "ativo");
	}

}
