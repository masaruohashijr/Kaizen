/**
 *
 */
package com.logus.kaizen.view.projeto;

import com.logus.core.model.translation.TM;
import com.logus.core.view.form.BeanForm;
import com.logus.kaizen.model.apoio.ApoioDataService;
import com.logus.kaizen.model.apoio.tipomondai.TipoMondai;
import com.logus.kaizen.model.apoio.tipomondai.TipoMondaiProjeto;
import com.logus.kaizen.model.processo.Processo;
import com.logus.kaizen.model.projeto.Projeto;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class TipoMondaiProjetoForm extends BeanForm<TipoMondaiProjeto> {

	/**
	 *
	 */
	private static final long serialVersionUID = -5726930569029997422L;

	/**
	 * Método construtor
	 *
	 * @param TipoMondai object
	 */
	protected TipoMondaiProjetoForm(TipoMondaiProjeto tipoMondaiProjeto) {
		super(tipoMondaiProjeto);
		add(focus(fullWidth(createProcessosBeanComboBox())));
		add(fullWidth(createTipoMondaiBeanComboBox()));
		add(createAtivoCheckBox());
	}


	private ComboBox<Processo> createProcessosBeanComboBox() {
		return createBeanComboBox(TM.translate(KaizenTranslator.PROCESSO_PLURAL), "processo", ApoioDataService.get().getProcessoDao().loadProcessos());
	}

	private ComboBox<TipoMondai> createTipoMondaiBeanComboBox() {
		return createBeanComboBox(TM.translate(KaizenTranslator.TIPO_MONDAI), "tipoMondai", ApoioDataService.get().getTipoMondaiDao().loadTiposMondai());
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

}
