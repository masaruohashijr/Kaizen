package com.logus.kaizen.view.mondai;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.logus.core.model.translation.TM;
import com.logus.core.view.form.TabSheet;
import com.logus.kaizen.app.KaizenListSelector;
import com.logus.kaizen.model.apoio.ApoioDataService;
import com.logus.kaizen.model.apoio.ambiente.Ambiente;
import com.logus.kaizen.model.apoio.atendimento.Atendimento;
import com.logus.kaizen.model.apoio.urgencia.Urgencia;
import com.logus.kaizen.model.solicitacao.ItemSolicitacao;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.kaizen.view.apoio.ambiente.AmbienteSolicitacaoGrid;
import com.logus.kaizen.view.form.KaizenBeanForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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
public class ItemSolicitacaoForm extends KaizenBeanForm<ItemSolicitacao> {

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
		HorizontalLayout hLayout = new HorizontalLayout();
		HorizontalLayout hLayout2 = new HorizontalLayout();
		hLayout.add(focus(fullWidth(createUrgenciaComboBox())));
		hLayout.add(fullWidth(createAtendimentoComboBox()));
		vLayout.add(fullWidth(hLayout));
		hLayout2.add(fullWidth(createDataUltimoAtendimentoDatePicker()));
		hLayout2.add(fullWidth(createResponsavelTextField()));
		vLayout.add(fullWidth(hLayout2));
		vLayout.add(fullWidth(createSolicitadoCheckBox()));
		// Campo para exibição somente.
		return vLayout;
	}

	private Checkbox createSolicitadoCheckBox() {
		Checkbox cbSolicitado = createCheckBoxField(TM.translate(KaizenTranslator.ITEM_SOLICITACAO_SOLICITADO), "solicitado");
		cbSolicitado.setValue(Boolean.TRUE);
		cbSolicitado.setEnabled(false);
		return cbSolicitado;
	}

	private Component createAmbientesPage() {
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.add(fullHeight(createAmbienteListSelector()));
		vLayout.setSizeFull();
		return vLayout;
	}

	private Component createAmbienteListSelector() {
		Ambiente ambienteSelecionado = ((ItemSolicitacao) getObject()).getAmbiente();
		Collection<Ambiente> selected = new LinkedList<>();
		if (null != ambienteSelecionado) {
			selected.add(ambienteSelecionado);
		}
		ambientesSelector = new KaizenListSelector<Ambiente>("", true, new AmbienteSolicitacaoGrid(),
				SelectionMode.MULTI, ApoioDataService.get().getAmbienteDao().loadAll(), selected);
//		fullWidth(ambientesSelector);
//		ListSelector<Ambiente> ambientesSelector = new ListSelector<>("", new AmbienteSolicitacaoGrid(), SelectionMode.MULTI, ApoioDataService.get().getAmbienteDao().loadAll(), selected);
		ambientesSelector.getElement().getChild(0).setAttribute("style", "width:100%");
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
//		ComboBox<Atendimento> atendimentos = createBeanComboBox(TM.translate(KaizenTranslator.ATENDIMENTO),
//				"atendimento", ApoioDataService.get().getAtendimentoDao().loadAll());
		ComboBox<Atendimento> atendimentos = new ComboBox<Atendimento>();
		atendimentos.setLabel(TM.translate(KaizenTranslator.ATENDIMENTO));
		List<Atendimento> atendimentosList = new ArrayList<>(ApoioDataService.get().getAtendimentoDao().loadAll());
		atendimentos.setItems(atendimentosList);
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

	private Component createResponsavelTextField() {
		TextField tfGCM = createTextField(TM.translate(KaizenTranslator.ITEM_SOLICITACAO_RESPONSAVEL), "codigoResponsavel");
		tfGCM.setEnabled(false);
		return tfGCM;
	}

	public KaizenListSelector<Ambiente> getKaizenListSelector() {
		return ambientesSelector;
	}

}
