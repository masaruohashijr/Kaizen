/**
 *
 */
package com.logus.kaizen.view.chronos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import com.logus.common.base.Strings;
import com.logus.core.model.aut.login.LoginManager;
import com.logus.core.model.translation.TM;
import com.logus.core.model.util.Formats;
import com.logus.core.view.form.BeanComboBox;
import com.logus.core.view.form.BeanForm;
import com.logus.core.view.form.ListSelector;
import com.logus.kaizen.app.KaizenListSelector;
import com.logus.kaizen.model.apoio.ApoioDataService;
import com.logus.kaizen.model.apoio.projeto.Projeto;
import com.logus.kaizen.model.chronos.Chronos;
import com.logus.kaizen.model.kotae.configuracao.KotaeConfiguracao;
import com.logus.kaizen.model.kotae.configuracao.KotaeConfiguracao.TipoKotae;
import com.logus.kaizen.model.solicitacao.Solicitacao;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.kaizen.view.mondai.SolicitacaoChronosGrid;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Element;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class ChronosForm extends BeanForm<Chronos> {

	/**
	 *
	 */
	private static final long serialVersionUID = -5726930569029997422L;
	private KaizenListSelector<Solicitacao> solicitacoesSelector;
	private BeanComboBox<KotaeConfiguracao> cbConfig;
	private TextField tfDataInicio;
	private TextField tfDataFim;
	private BeanComboBox<Projeto> projetoCB;

	/**
	 * Método construtor
	 *
	 * @param Plano object
	 */
	protected ChronosForm(Chronos toguru) {
		super(toguru);
		HorizontalLayout hLayout1 = new HorizontalLayout();
		hLayout1.add(focus(fullWidth(createTituloTextField())));
		hLayout1.add(fullWidth(createProjetoBeanComboBox()));
		hLayout1.setWidth("100%");
		add(hLayout1);
		HorizontalLayout hLayout2 = new HorizontalLayout();
		hLayout2.add(fullWidth(createDataInicioTextField()));
		hLayout2.add(fullWidth(createDataFimTextField()));
		hLayout2.add(fullWidth(createResponsavelTextFieldEdit()));
		hLayout2.add(fullWidth(createConfiguracaoComboBoxEdit()));
		Collection<Solicitacao> selecteds = new ArrayList<>();
		Solicitacao solicitacao = getObject().getSolicitacao();
		if (null != solicitacao) {
			selecteds.add(solicitacao);
		}
		hLayout2.setWidth("100%");
		ListSelector<Solicitacao> solicitacoesSelector = createSolicitacoesSelector(selecteds);
		add(hLayout2);
		add(fullSize(solicitacoesSelector));
		add(createAtivoCheckBox());
	}

	private Component createTituloTextField() {
		TextField tituloChronos = createTextField(TM.translate(KaizenTranslator.CHRONOS_TITULO), "tituloChronos");
		tituloChronos.addBlurListener(listener->{
			if(!Strings.isEmpty(listener.getSource().getValue())) {
				projetoCB.setEnabled(true);
				focus(projetoCB);
				solicitacoesSelector.setEnabled(false);
			} else {
				projetoCB.setEnabled(false);
				solicitacoesSelector.setEnabled(true);
			}
		});
		return tituloChronos;
	}

	private Component createProjetoBeanComboBox() {
		projetoCB = createBeanComboBox(TM.translate(KaizenTranslator.PROJETO), "projeto",
				ApoioDataService.get().getProjetoDao().loadProjetos());
		if(null == getObject().getId()) {
			projetoCB.setEnabled(false);
		}
		projetoCB.addValueChangeListener(listener -> {
			if(!Strings.isEmpty(listener.getSource().getValue())) {
				solicitacoesSelector.setEnabled(false);
			} else {
				solicitacoesSelector.setEnabled(true);
			}
		});
		return projetoCB;
	}


	private ListSelector<Solicitacao> createSolicitacoesSelector(Collection<Solicitacao> selecteds) {
		Collection<Solicitacao> selectedObjects = new LinkedList<>();
		Solicitacao solicitacao = getObject().getSolicitacao();
		if (null != solicitacao) {
			selectedObjects.add(solicitacao);
		}
		solicitacoesSelector = new KaizenListSelector<>("", true, new SolicitacaoChronosGrid(), SelectionMode.SINGLE,
				ApoioDataService.get().getSolicitacaoDao().loadSolicitacoes(), selectedObjects);
		Component component = solicitacoesSelector.getComponentAt(0);
		Element tfElement = component.getElement();
		tfElement.setAttribute("style", "width:100%");
		return solicitacoesSelector;
	}

	private Component createResponsavelTextFieldEdit() {
		TextField tfCodigoResponsavel = createTextField(TM.translate(KaizenTranslator.CHRONOS_RESPONSAVEL),
				"codigoResponsavel");
		String codigoResponsavel = LoginManager.getAccessControl().getUser().getCodigo();
		tfCodigoResponsavel.setValue(codigoResponsavel);
		tfCodigoResponsavel.setEnabled(false);
		return tfCodigoResponsavel;
	}

	private Component createDataInicioTextField() {
		tfDataInicio = new TextField();
		tfDataInicio.setLabel(TM.translate(KaizenTranslator.CHRONOS_DATA_INICIO));
		tfDataInicio.setReadOnly(true);
		tfDataInicio.setEnabled(true);
		tfDataInicio.getElement().setAttribute("readOnly", "true");
		if (null == getObject().getId()) {
			tfDataInicio.setValue(Formats.getDateTimeFormat().format(new Date(System.currentTimeMillis())));
		} else {
			Date dataInicio = getObject().getDataInicio();
			if (null != dataInicio) {
				tfDataInicio.setValue(Formats.getDateTimeFormat().format(dataInicio));
			}
		}
		return tfDataInicio;
	}

	private Component createDataFimTextField() {
		tfDataFim = new TextField();
		tfDataFim.setLabel(TM.translate(KaizenTranslator.CHRONOS_DATA_FIM));
		tfDataFim.setReadOnly(true);
		tfDataFim.getElement().setAttribute("readOnly", "true");
			Date dataFim = getObject().getDataFim();
			if (null != dataFim) {
				tfDataFim.setValue(Formats.getDateTimeFormat().format(dataFim));
			}
		if(Strings.isEmpty(tfDataFim.getValue())) {
			tfDataFim.setEnabled(false);
		}
		return tfDataFim;
	}

	private Component createConfiguracaoComboBoxEdit() {
		Collection<KotaeConfiguracao> configuracoes =
				ApoioDataService.get().getKotaeConfiguracaoDao().loadConfiguracaoByTipo(TipoKotae.TOGURU);
		this.cbConfig = createBeanComboBox(TM.translate(KaizenTranslator.KOTAE_CONFIGURACAO), "configuracao",
				configuracoes);
		this.cbConfig.setValue((KotaeConfiguracao) configuracoes.toArray()[0]);
		this.cbConfig.setEnabled(false);
		return cbConfig;
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

	public KaizenListSelector<Solicitacao> getSolicitacoesSelector() {
		return solicitacoesSelector;
	}

	public TextField getTfDataInicio() {
		return tfDataInicio;
	}

	public void setTfDataInicio(TextField tfDataInicio) {
		this.tfDataInicio = tfDataInicio;
	}

	public TextField getTfDataFim() {
		return tfDataFim;
	}

	public void setTfDataFim(TextField tfDataFim) {
		this.tfDataFim = tfDataFim;
	}
}
