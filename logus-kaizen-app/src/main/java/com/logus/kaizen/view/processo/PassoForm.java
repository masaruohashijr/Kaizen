package com.logus.kaizen.view.processo;

import com.logus.core.model.translation.TM;
import com.logus.core.view.form.BeanForm;
import com.logus.core.view.form.TabSheet;
import com.logus.kaizen.model.apoio.ApoioDataService;
import com.logus.kaizen.model.processo.Passo;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.vaadin.flow.component.Component;
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
@SuppressWarnings("serial")
public class PassoForm extends BeanForm<Passo> {

	private TabSheet tabs;

	/**
	 * Construtor.
	 *
	 * @param object objeto a ser editado por este formulário.
	 */
	public PassoForm(Passo object) {
		super(object);
		add(fullHeight(createTabSheets()));
	}

	private Component createTabSheets() {
		tabs = new TabSheet();
		// Falta I18N nas Captions das Abas
		tabs.addTab("Principal", VaadinIcon.INFO_CIRCLE.create(), createPrincipalPage());
		return tabs;
	}

	private Component createPrincipalPage() {
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.add(focus(fullWidth(createTransicaoComboBox())));
		vLayout.add(fullWidth(createAtendimentoOrigemComboBox()));
		vLayout.add(fullWidth(createAtendimentoDestinoComboBox()));
		vLayout.add(fullWidth(createNomeTextField()));
		vLayout.add(fullWidth(createDescricaoTextArea()));
		return vLayout;
	}

	private Component createAtendimentoDestinoComboBox() {
		return createBeanComboBox(TM.translate(KaizenTranslator.ATENDIMENTO_DESTINO), "atendimentoDestino",
				ApoioDataService.get().getAtendimentoDao().loadAll());
	}

	private Component createAtendimentoOrigemComboBox() {
		return createBeanComboBox(TM.translate(KaizenTranslator.ATENDIMENTO_ORIGEM), "atendimentoOrigem",
				ApoioDataService.get().getAtendimentoDao().loadAll());
	}

	private Component createTransicaoComboBox() {
		return createBeanComboBox(TM.translate(KaizenTranslator.TRANSICAO), "transicao",
				ApoioDataService.get().getTransicaoDao().loadAll());
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
