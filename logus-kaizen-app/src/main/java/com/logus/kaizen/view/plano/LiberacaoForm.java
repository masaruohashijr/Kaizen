package com.logus.kaizen.view.plano;

import java.util.Collection;
import java.util.LinkedList;

import org.apache.commons.lang3.StringUtils;

import com.logus.core.model.aut.login.LoginManager;
import com.logus.core.model.translation.TM;
import com.logus.core.view.form.BeanForm;
import com.logus.core.view.form.TabSheet;
import com.logus.kaizen.app.KaizenListSelector;
import com.logus.kaizen.model.apoio.ApoioDataService;
import com.logus.kaizen.model.apoio.ambiente.Ambiente;
import com.logus.kaizen.model.plano.Liberacao;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.kaizen.view.ambiente.AmbienteSolicitacaoGrid;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
public class LiberacaoForm extends BeanForm<Liberacao> {

	private TabSheet tabs;
	private KaizenListSelector<Ambiente> ambientesSelector;

	/**
	 * Construtor.
	 *
	 * @param object objeto a ser editado por este formulário.
	 */
	public LiberacaoForm(Liberacao object) {
		super(object);
		add(createTabSheets());
	}

	private Component createTabSheets() {
		tabs = new TabSheet();
		// Falta I18N nas Captions das Abas
		tabs.addTab("Principal", VaadinIcon.INFO_CIRCLE.create(), createPrincipalPage());
		tabs.addTab("Ambientes", VaadinIcon.INFO_CIRCLE.create(), createAmbienteListSelector());
		return tabs;
	}

	private Component createPrincipalPage() {
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.add(focus(fullWidth(createReferenciaTextField())));
		vLayout.add(fullWidth(createVersaoTextFieldEdit()));
		vLayout.add(fullWidth(createDataLiberacaoDatePicker()));
		vLayout.add(fullWidth(createGCMTextField()));
		return vLayout;
	}

	private Component createVersaoTextFieldEdit() {
		TextField tfVersao = createTextField(TM.translate(KaizenTranslator.SOLICITACAO_VERSAO), "versao");
		return tfVersao;
	}

	private Component createReferenciaTextField() {
		return createTextField(TM.translate(KaizenTranslator.PLANO_REFERENCIA), "referencia");
	}

	private Component createAmbienteListSelector() {
		Ambiente ambienteSelecionado = getObject().getAmbiente();
		Collection<Ambiente> selected = new LinkedList<>();
		if (null != ambienteSelecionado) {
			selected.add(ambienteSelecionado);
		}
		ambientesSelector = new KaizenListSelector<Ambiente>("", false, new AmbienteSolicitacaoGrid(),
				SelectionMode.MULTI, ApoioDataService.get().getAmbienteDao().loadAll(), selected);
		ambientesSelector.setSizeFull();
		return ambientesSelector;
	}

	private Component createDataLiberacaoDatePicker() {
		DatePicker dateField = createDateField(TM.translate(KaizenTranslator.LIBERACAO_DATA), "dataLiberacao");
		dateField.setEnabled(false);
		return dateField;
	}

	private Component createGCMTextField() {
		TextField tfResponsavel = createTextField(TM.translate(KaizenTranslator.LIBERACAO_RESPONSAVEL), "responsavel");
		String nomeUsuario = LoginManager.getAccessControl().getUser().getNome();
		tfResponsavel.setValue(StringUtils.capitalize(nomeUsuario));
		tfResponsavel.setEnabled(false);
		return tfResponsavel;
	}

	public KaizenListSelector<Ambiente> getAmbientesSelector() {
		return ambientesSelector;
	}

}
