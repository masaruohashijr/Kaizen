/**
 *
 */
package com.logus.kaizen.view.produto;

import com.logus.kaizen.model.apoio.produto.Produto;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.form.BeanForm;
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
public class ProdutoForm extends BeanForm<Produto> {

	/**
	 *
	 */
	private static final long serialVersionUID = -5726930569029997422L;

	/**
	 * Método construtor
	 *
	 * @param Produto object
	 */
	protected ProdutoForm(Produto object) {
		super(object);
		add(focus(fullWidth(createNomeTextField())));
		add(fullWidth(createDescricaoTextArea()));
		add(createAtivoCheckBox());
	}

	/**
	 * Cria um campo textfield para inserção de um nome para o produto
	 *
	 * @return Component
	 */
	private TextField createNomeTextField() {
		return createTextField(TM.translate(KaizenTranslator.NOME), "nome");
	}

	/**
	 * Cria um campo textArea para inserção de uma descrição para o produto.
	 *
	 * @return Component
	 */
	private TextArea createDescricaoTextArea() {
		return createTextArea(TM.translate(KaizenTranslator.BIBLIOTECA_DESCRICAO), "descricao");
	}

	/**
	 * Cria um checkbox para definir se o produto estará ativado no momento da
	 * inserção, por padrão ele já está ativado.
	 *
	 * @return Component
	 */
	private Checkbox createAtivoCheckBox() {
		return createCheckBoxField(TM.translate(KaizenTranslator.ATIVO), "ativo");
	}

}
