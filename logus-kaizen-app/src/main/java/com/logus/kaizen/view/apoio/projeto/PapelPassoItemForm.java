/**
 *
 */
package com.logus.kaizen.view.apoio.projeto;

import java.util.Collection;

import com.logus.core.model.translation.TM;
import com.logus.core.view.form.BeanComboBox;
import com.logus.core.view.form.BeanForm;
import com.logus.kaizen.model.apoio.processo.Passo;
import com.logus.kaizen.model.apoio.projeto.Papel;
import com.logus.kaizen.model.apoio.tipomondai.PapelPassoItem;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class PapelPassoItemForm extends BeanForm<PapelPassoItem> {

	/**
	 *
	 */
	private static final long serialVersionUID = -5726930569029997422L;
	private PapelPassoItem papelPasso;
	private TextField tfTransicao;
	private TextField tfAtdDestino;
	private TextField tfAtdOrigem;

	/**
	 * Método construtor
	 *
	 * @param TipoMondai object
	 */
	protected PapelPassoItemForm(PapelPassoItem papelPasso) {
		super(papelPasso);
		this.papelPasso = papelPasso;
		add(focus(fullWidth(createPassoBeanComboBox())));
		add(fullWidth(createTransicaoTextFieldEdit()));
		add(fullWidth(createAtendimentoOrigemTextFieldEdit()));
		add(fullWidth(createAtendimentoDestinoTextFieldEdit()));
		add(fullWidth(createPapelBeanComboBox()));
		add(fullWidth(createDescricaoTextArea()));
		add(createAtivoCheckBox());
	}

	private Component createPassoBeanComboBox() {
		Collection<Passo> passos = papelPasso.getTipoMondaiProjeto().getProcesso().getPassos();
		BeanComboBox<Passo> cbPassos = createBeanComboBox(TM.translate(KaizenTranslator.PROCESSO_PASSO_PLURAL), "passo",
				passos);
		cbPassos.addValueChangeListener(e -> {
			populateTextFields(cbPassos.getValue());
		});
		return cbPassos;
	}

	private void populateTextFields(Passo passo) {
		this.tfTransicao.setValue(passo.getTransicao().getNome());
		this.tfAtdOrigem.setValue(passo.getAtendimentoOrigem().getTitulo());
		this.tfAtdDestino.setValue(passo.getAtendimentoDestino().getTitulo());
	}

	private Component createPapelBeanComboBox() {
		Collection<Papel> papeis = papelPasso.getTipoMondaiProjeto().getProjeto().getPapeis();
		BeanComboBox<Papel> cbProcesso = createBeanComboBox(TM.translate(KaizenTranslator.PROJETO_PAPEL_PLURAL),
				"papel", papeis);
		return cbProcesso;
	}

	private Component createAtendimentoDestinoTextFieldEdit() {
		this.tfAtdDestino = new TextField("Atendimento Destino");
		if (null != getObject().getAtendimentoDestinoPasso()) {
			tfAtdDestino.setValue(getObject().getAtendimentoDestinoPasso().getTitulo());
		}
		tfAtdDestino.setEnabled(false);
		return tfAtdDestino;
	}

	private Component createAtendimentoOrigemTextFieldEdit() {
		this.tfAtdOrigem = new TextField("Atendimento Origem");
		if (null != getObject().getAtendimentoOrigemPasso()) {
			tfAtdOrigem.setValue(getObject().getAtendimentoOrigemPasso().getTitulo());
		}
		tfAtdOrigem.setEnabled(false);
		return tfAtdOrigem;
	}

	private Component createTransicaoTextFieldEdit() {
		this.tfTransicao = new TextField("Transição");
		if (null != getObject().getTransicaoPasso()) {
			tfTransicao.setValue(getObject().getTransicaoPasso().getNome());
		}
		tfTransicao.setEnabled(false);
		return tfTransicao;
	}

	private TextArea createDescricaoTextArea() {
		return createTextArea(TM.translate(KaizenTranslator.PAPEL_PASSO_ITEM_DESCRICAO), "descricao");
	}

	private Checkbox createAtivoCheckBox() {
		return createCheckBoxField(TM.translate(KaizenTranslator.ATIVO), "ativo");
	}

}
