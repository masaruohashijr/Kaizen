package com.logus.kaizen.view.apoio.ambiente;

import com.logus.core.model.translation.TM;
import com.logus.core.view.form.BeanForm;
import com.logus.kaizen.model.apoio.ApoioDataService;
import com.logus.kaizen.model.apoio.ambiente.Ambiente;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.textfield.TextArea;
/**
*
* @author Masaru Ohashi Júnior
* @since 1 de mar de 2019
* @version 1.0.0
*
*/
@SuppressWarnings("serial")
public class AmbienteForm extends BeanForm<Ambiente> {

	/**
	 * Construtor.
	 *
	 * @param object objeto a ser editado por este formulário.
	 */
	public AmbienteForm(Ambiente object) {
		super(object);
		add(focus(fullWidth(createClienteComboBox())));
		add(fullWidth(createNomeTextField()));
		add(fullWidth(createAcronimoTextField()));
		add(fullWidth(createDescricaoTextArea()));
		add(fullWidth(createHostTextField()));
		add(createAtivoCheckBox());
	}

	private Component createAcronimoTextField() {
		return createTextField(TM.translate(KaizenTranslator.AMBIENTE_ACRONIMO), "acronimo");
	}

	private Component createClienteComboBox() {
		return createBeanComboBox(TM.translate(KaizenTranslator.CLIENTE), "cliente",
				ApoioDataService.get().getClienteDao().loadAll());
	}

	private Component createHostTextField() {
		return createTextField(TM.translate(KaizenTranslator.AMBIENTE_HOST), "host");
	}

	private Component createNomeTextField() {
		return createTextField(TM.translate(KaizenTranslator.NOME), "nome");
	}

	/**
	 * Cria um campo textArea para inserção de uma descrição para o ambiente.
	 *
	 * @return Component
	 */
	private TextArea createDescricaoTextArea() {
		return createTextArea(TM.translate(KaizenTranslator.AMBIENTE_DESCRICAO), "descricao");
	}

	private Checkbox createAtivoCheckBox() {
		return createCheckBoxField(TM.translate(KaizenTranslator.ATIVO), "ativo");
	}
}
