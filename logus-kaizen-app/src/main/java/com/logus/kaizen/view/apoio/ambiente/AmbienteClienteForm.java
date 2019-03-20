package com.logus.kaizen.view.apoio.ambiente;

import com.logus.core.model.translation.TM;
import com.logus.core.view.form.BeanForm;
import com.logus.kaizen.model.apoio.ambiente.Ambiente;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.checkbox.Checkbox;
/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
public class AmbienteClienteForm extends BeanForm<Ambiente> {

	/**
	 * Construtor.
	 *
	 * @param object objeto a ser editado por este formulário.
	 */
	public AmbienteClienteForm(Ambiente object) {
		super(object);
		add(focus(fullWidth(createNomeTextField())));
		add(fullWidth(createAcronimoTextField()));
		add(fullWidth(createHostTextField()));
		add(createAtivoCheckBox());
	}

	private Component createAcronimoTextField() {
		return createTextField(TM.translate(KaizenTranslator.AMBIENTE_ACRONIMO), "acronimo");
	}

	private Component createHostTextField() {
		return createTextField("Host", "host");
	}

	private Component createNomeTextField() {
		return createTextField("Nome", "nome");
	}

	private Checkbox createAtivoCheckBox() {
		return createCheckBoxField(TM.translate(KaizenTranslator.ATIVO), "ativo");
	}
}
