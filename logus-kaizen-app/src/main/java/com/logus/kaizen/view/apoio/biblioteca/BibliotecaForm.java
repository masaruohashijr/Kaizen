/**
 *
 */
package com.logus.kaizen.view.apoio.biblioteca;

import com.logus.core.model.translation.TM;
import com.logus.core.view.form.BeanForm;
import com.logus.kaizen.model.apoio.biblioteca.Biblioteca;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class BibliotecaForm extends BeanForm<Biblioteca> {

	/**
	 *
	 */
	private static final long serialVersionUID = -5726930569029997422L;

	/**
	 * Método construtor
	 *
	 * @param Biblioteca object
	 */
	protected BibliotecaForm(Biblioteca biblioteca) {
		super(biblioteca);
		add(focus(fullWidth(createNomeTextField())));
		add(fullWidth(createDescricaoTextArea()));
		add(createAtivoCheckBox());
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
		return createTextArea(TM.translate(KaizenTranslator.BIBLIOTECA_DESCRICAO), "descricao");
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
