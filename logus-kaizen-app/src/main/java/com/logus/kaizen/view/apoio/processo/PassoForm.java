package com.logus.kaizen.view.apoio.processo;

import com.logus.core.model.translation.TM;
import com.logus.core.view.form.BeanForm;
import com.logus.kaizen.model.apoio.ApoioDataService;
import com.logus.kaizen.model.apoio.processo.Passo;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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
@SuppressWarnings("serial")
public class PassoForm extends BeanForm<Passo> {

	/**
	 * Construtor.
	 *
	 * @param object objeto a ser editado por este formulário.
	 */
	public PassoForm(Passo object) {
		super(object);
		HorizontalLayout hLayout1 = new HorizontalLayout();
		VerticalLayout vLayout1 = new VerticalLayout();
		VerticalLayout vLayout2 = new VerticalLayout();

		vLayout1.add(focus(fullWidth(createTransicaoComboBox())));
		vLayout1.add(fullWidth(createResolucaoComboBox()));
		vLayout2.add(fullWidth(createAtendimentoOrigemComboBox()));
		vLayout2.add(fullWidth(createAtendimentoDestinoComboBox()));
		hLayout1.add(vLayout1, vLayout2);
		fullWidth(hLayout1);
		add(hLayout1);
		add(fullWidth(createNomeTextField()));
		add(fullWidth(fullHeight(createDescricaoTextArea())));
		add(createAtivoCheckBox());
	}

	private Component createResolucaoComboBox() {
		return createBeanComboBox(TM.translate(KaizenTranslator.PASSO_RESOLUCAO), "resolucao",
				ApoioDataService.get().getResolucaoDao().loadResolucoes());
	}

	private Component createAtendimentoDestinoComboBox() {
		return createBeanComboBox(TM.translate(KaizenTranslator.ATENDIMENTO_DESTINO), "atendimentoDestino",
				ApoioDataService.get().getAtendimentoDao().loadAtendimentos());
	}

	private Component createAtendimentoOrigemComboBox() {
		return createBeanComboBox(TM.translate(KaizenTranslator.ATENDIMENTO_ORIGEM), "atendimentoOrigem",
				ApoioDataService.get().getAtendimentoDao().loadAtendimentos());
	}

	private Component createTransicaoComboBox() {
		return createBeanComboBox(TM.translate(KaizenTranslator.TRANSICAO), "transicao",
				ApoioDataService.get().getTransicaoDao().loadAll());
	}

	private Component createAtivoCheckBox() {
		return createCheckBoxField(TM.translate(KaizenTranslator.ATIVO), "ativo");
	}



	/**
	 * Cria um campo textfield para inserção de um nome para o Passo.
	 *
	 * @return Component
	 */
	private TextField createNomeTextField() {
		return createTextField(TM.translate(KaizenTranslator.NOME), "nome");
	}

	/**
	 * Cria um campo textArea para inserção de uma descrição para o Passo.
	 *
	 * @return Component
	 */
	private TextArea createDescricaoTextArea() {
		return createTextArea(TM.translate(KaizenTranslator.PASSO_DESCRICAO), "descricao");
	}
}
