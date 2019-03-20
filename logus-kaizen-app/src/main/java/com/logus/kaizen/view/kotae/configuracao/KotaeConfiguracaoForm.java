package com.logus.kaizen.view.kotae.configuracao;

import com.logus.core.model.translation.TM;
import com.logus.core.view.form.BeanForm;
import com.logus.core.view.form.EnumRadioGroup;
import com.logus.kaizen.model.apoio.ApoioDataService;
import com.logus.kaizen.model.kotae.configuracao.KotaeConfiguracao;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.vaadin.flow.component.Component;

public class KotaeConfiguracaoForm extends BeanForm<KotaeConfiguracao> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public KotaeConfiguracaoForm(KotaeConfiguracao kotaeConfiguracao) {
		super(kotaeConfiguracao);
		add(focus(fullWidth(createTipoKotaeComboBox())));
		add(fullSize(createDescricaoTextArea()));
		add(fullWidth(createProcessoComboBox()));
	}

	private Component createDescricaoTextArea() {
		return createTextArea(TM.translate(KaizenTranslator.KOTAE_DESCRICAO), "descricao");
	}

	private Component createProcessoComboBox() {
		return createBeanComboBox(TM.translate(KaizenTranslator.PROCESSO), "processo",
				ApoioDataService.get().getProcessoDao().loadProcessos());
	}

	private Component createTipoKotaeComboBox() {
		EnumRadioGroup<KotaeConfiguracao.TipoKotae> rg = createEnumRadioGroup("tipoKotae", KotaeConfiguracao.TipoKotae.class);
		return addTopCaption(rg, TM.translate(KaizenTranslator.KOTAE_TIPO));
	}

}
