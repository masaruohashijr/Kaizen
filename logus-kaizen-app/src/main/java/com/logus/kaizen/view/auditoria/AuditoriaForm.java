/**
 *
 */
package com.logus.kaizen.view.auditoria;

import com.logus.core.model.translation.TM;
import com.logus.core.view.form.BeanForm;
import com.logus.kaizen.model.auditoria.ItemMudanca;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.textfield.TextField;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class AuditoriaForm extends BeanForm<ItemMudanca> {

	/**
	 *
	 */
	private static final long serialVersionUID = -5726930569029997422L;

	/**
	 * Método construtor
	 *
	 * @param ItemMudanca object
	 */
	protected AuditoriaForm(ItemMudanca object) {
		super(object);
		add(focus(fullWidth(createNomeTextField())));
		fullWidth(createDataMudancaTextField());
		fullWidth(createEntidadeTextField());
		fullWidth(createNomeCampoTextField());
		fullWidth(createValorAntigoTextField());
		fullWidth(createValorNovoTextField());
		add(createAtivoCheckBox());
	}

	private Component createValorNovoTextField() {
		TextField valorNovo = createTextField(TM.translate(KaizenTranslator.AUDITORIA_VALOR_NOVO), "autor");
		valorNovo.setEnabled(false);
		return valorNovo;
	}

	private Component createValorAntigoTextField() {
		TextField valorAntigo = createTextField(TM.translate(KaizenTranslator.AUDITORIA_VALOR_ANTIGO), "autor");
		valorAntigo.setEnabled(false);
		return valorAntigo;
	}

	private Component createNomeCampoTextField() {
		TextField nomeCampo = createTextField(TM.translate(KaizenTranslator.AUDITORIA_NOME_CAMPO), "autor");
		nomeCampo.setEnabled(false);
		return nomeCampo;
	}

	private Component createEntidadeTextField() {
		TextField autor = createTextField(TM.translate(KaizenTranslator.AUDITORIA_ENTIDADE), "autor");
		autor.setEnabled(false);
		return autor;
	}

	private Component createDataMudancaTextField() {
		TextField dataMudanca = createTextField(TM.translate(KaizenTranslator.AUDITORIA_DATA_MUDANCA), "autor");
		dataMudanca.setEnabled(false);
		return dataMudanca;
	}

	/**
	 * Cria um campo textfield para inserção de um nome para o ItemMudanca.
	 *
	 * @return Component
	 */
	private TextField createNomeTextField() {
		TextField autor = createTextField(TM.translate(KaizenTranslator.AUDITORIA_AUTOR), "autor");
		autor.setEnabled(false);
		return autor;
	}

	/**
	 * Cria um checkbox para definir se o credor estará ativado no momento da
	 * inserção, por padrão ele já está ativado.
	 *
	 * @return Component
	 */
	private Checkbox createAtivoCheckBox() {
		Checkbox ativo = createCheckBoxField(TM.translate(KaizenTranslator.ATIVO), "ativo");
		ativo.setEnabled(false);
		return ativo;
	}

}
