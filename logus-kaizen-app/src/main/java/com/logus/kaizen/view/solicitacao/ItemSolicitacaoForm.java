package com.logus.kaizen.view.solicitacao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.logus.core.model.translation.TM;
import com.logus.core.view.form.BeanForm;
import com.logus.core.view.form.TabSheet;
import com.logus.kaizen.app.KaizenListSelector;
import com.logus.kaizen.model.apoio.ApoioDataService;
import com.logus.kaizen.model.apoio.ambiente.Ambiente;
import com.logus.kaizen.model.apoio.atendimento.Atendimento;
import com.logus.kaizen.model.apoio.urgencia.Urgencia;
import com.logus.kaizen.model.solicitacao.ItemSolicitacao;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.kaizen.view.ambiente.AmbienteSolicitacaoGrid;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
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
public class ItemSolicitacaoForm extends BeanForm<ItemSolicitacao> {

	private TabSheet tabs;
	private KaizenListSelector<Ambiente> ambientesSelector;

	/**
	 * Construtor.
	 *
	 * @param object objeto a ser editado por este formulário.
	 */
	public ItemSolicitacaoForm(ItemSolicitacao object) {
		super(object);
		add(createTabSheets());
	}

	private Component createTabSheets() {
		tabs = new TabSheet();
		// Falta I18N nas Captions das Abas
		tabs.addTab("Principal", VaadinIcon.INFO_CIRCLE.create(), createPrincipalPage());
		tabs.addTab("Ambientes", VaadinIcon.INFO_CIRCLE.create(), createAmbientesPage());
		return tabs;
	}

	private Component createPrincipalPage() {
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.add(focus(fullWidth(createUrgenciaComboBox())));
		vLayout.add(fullWidth(createAtendimentoComboBox()));
		vLayout.add(fullWidth(createDataUltimoAtendimentoDatePicker()));
		vLayout.add(fullWidth(createGCMTextField()));
		return vLayout;
	}

	private Component createAmbientesPage() {
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.add(fullHeight(fullWidth(createAmbienteListSelector())));
		vLayout.setSizeFull();
		return vLayout;
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

	private Component createUrgenciaComboBox() {
		ComboBox<Urgencia> urgencias = createBeanComboBox(TM.translate(KaizenTranslator.URGENCIA), "urgencia",
				ApoioDataService.get().getUrgenciaDao().loadAll());
		List<Urgencia> listUrgencias = new ArrayList<>(ApoioDataService.get().getUrgenciaDao().loadAll());
		urgencias.setValue(getDefaultUrgencia(listUrgencias));
		return urgencias;
	}

	private Urgencia getDefaultUrgencia(List<Urgencia> urgenciaList) {
		for (Urgencia urgencia : urgenciaList) {
			if (urgencia.getNome().equals("Normal")) {
				return urgencia;
			}
		}
		return null;
	}

	private Component createAtendimentoComboBox() {
		ComboBox<Atendimento> atendimentos = createBeanComboBox(TM.translate(KaizenTranslator.ATENDIMENTO),
				"atendimento", ApoioDataService.get().getAtendimentoDao().loadAll());
		List<Atendimento> atendimentosList = new ArrayList<>(ApoioDataService.get().getAtendimentoDao().loadAll());
		Atendimento padrao = getDefaultAtendimento(atendimentosList);
		atendimentos.setValue(padrao);
		atendimentos.setEnabled(false);
		return atendimentos;
	}

	private Atendimento getDefaultAtendimento(List<Atendimento> atendimentoList) {
		for (Atendimento atendimento : atendimentoList) {
			if (atendimento.getTitulo().equals("Solicitado")) {
				return atendimento;
			}
		}
		return null;
	}

	private Component createDataUltimoAtendimentoDatePicker() {
		DatePicker dateField = createDateField(TM.translate(KaizenTranslator.ITEM_SOLICITACAO_DATA_ULTIMO_ATENDIMENTO),
				"dataUltimoAtendimento");
		dateField.setEnabled(false);
		return dateField;
	}

	private Component createGCMTextField() {
		TextField tfGCM = createTextField(TM.translate(KaizenTranslator.ITEM_SOLICITACAO_GCM), "gcm");
		tfGCM.setEnabled(false);
		return tfGCM;
	}

	public KaizenListSelector<Ambiente> getAmbientesSelector() {
		return ambientesSelector;
	}

}
