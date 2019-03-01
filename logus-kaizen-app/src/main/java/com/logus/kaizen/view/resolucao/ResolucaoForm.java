/**
 *
 */
package com.logus.kaizen.view.resolucao;

import com.logus.kaizen.model.resolucao.Resolucao;
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
public class ResolucaoForm extends BeanForm<Resolucao> {

	/**
	 *
	 */
	private static final long serialVersionUID = -5726930569029997422L;

	/**
	 * Método construtor
	 *
	 * @param Resolucao object
	 */
	protected ResolucaoForm(Resolucao object) {
		super(object);
		add(focus(fullWidth(createNomeTextField())));
		add(fullWidth(createDescricaoTextArea()));
		add(createAtivoCheckBox());
	}

	/**
	 * Cria um campo textfield para inserção de um nome para a Resolucao.
	 *
	 * @return Component
	 */
	private TextField createNomeTextField() {
		return createTextField(TM.translate(KaizenTranslator.NOME), "nome");
	}

	/**
	 * Cria um campo textArea para inserção de uma descrição para a Resolucao.
	 *
	 * @return Component
	 */
	private TextArea createDescricaoTextArea() {
		return createTextArea(TM.translate(KaizenTranslator.RESOLUCAO_DESCRICAO), "descricao");
	}

	/**
	 * Cria um checkbox para definir se a resolução estará ativada no momento da
	 * inserção, por padrão ele já está ativado.
	 *
	 * @return Component
	 */
	private Checkbox createAtivoCheckBox() {
		return createCheckBoxField(TM.translate(KaizenTranslator.ATIVO), "ativo");
	}

}
