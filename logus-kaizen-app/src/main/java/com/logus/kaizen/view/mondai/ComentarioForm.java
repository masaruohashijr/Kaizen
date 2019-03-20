package com.logus.kaizen.view.mondai;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

import com.logus.core.model.translation.TM;
import com.logus.core.model.util.Formats;
import com.logus.core.view.app.LegacyAccessControl;
import com.logus.core.view.form.BeanComboBox;
import com.logus.core.view.form.BeanForm;
import com.logus.kaizen.model.apoio.ApoioDataService;
import com.logus.kaizen.model.apoio.funcao.Funcao;
import com.logus.kaizen.model.apoio.projeto.Papel;
import com.logus.kaizen.model.solicitacao.Comentario;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
public class ComentarioForm extends BeanForm<Comentario> {

	private BeanComboBox<Papel> cbPapel;
	private BeanComboBox<Funcao> cbFuncao;

	/**
	 * Construtor.
	 *
	 * @param object objeto a ser editado por este formulário.
	 */
	public ComentarioForm(Comentario object) {
		super(object);
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.add(fullWidth(createAutorLabel()));
		hLayout.add(fullWidth(createDataAtualizacaoTextField()));
		hLayout.add(fullWidth(createPapelBeanComboBox()));
		hLayout.add(fullWidth(createFuncaoBeanComboBox()));
		fullWidth(hLayout);
		add(hLayout);
		add(focus(fullSize(createComentarioTextFieldEdit())));
	}

	private Component createFuncaoBeanComboBox() {
		Collection<Funcao> funcoes = ApoioDataService.get().getFuncaoDao().loadFuncoes();
		this.cbFuncao = createBeanComboBox(TM.translate(KaizenTranslator.COMENTARIO_FUNCAO), "funcao",
				funcoes);
		this.cbFuncao.setEnabled(true);
		return this.cbFuncao;
	}

	private Component createPapelBeanComboBox() {
		Collection<Papel> papeisProjeto = ApoioDataService.get().getPapelDao().loadPapeisDoProjeto(
				getObject().getSolicitacao().getProjeto());
		this.cbPapel = createBeanComboBox(TM.translate(KaizenTranslator.COMENTARIO_PROJETO_PAPEL), "papel",
				papeisProjeto);
		this.cbPapel.setEnabled(true);
		return this.cbPapel;
	}


	private Component createAutorLabel() {
		TextField autorTxtField = new TextField(TM.translate(KaizenTranslator.COMENTARIO_AUTOR));
		autorTxtField.setValue(LegacyAccessControl.get().getUser().getCodigo());
		autorTxtField.setEnabled(false);
		return autorTxtField;
	}

	private Component createDataAtualizacaoTextField() {
		TextField tfDataAtualizacao = new TextField();
		tfDataAtualizacao.setLabel(TM.translate(KaizenTranslator.COMENTARIO_DATA_ATUALIZACAO));
		tfDataAtualizacao.setEnabled(false);
		if (null == getObject().getId()) {
			tfDataAtualizacao.setValue(Formats.format(LocalDate.now()).toString());
		} else {
			Date dataAtualizacao = getObject().getDataAtualizacao();
			if (null != dataAtualizacao) {
				tfDataAtualizacao.setValue(Formats.format(dataAtualizacao).toString());
			}
		}
		return tfDataAtualizacao;
	}

	private Component createComentarioTextFieldEdit() {
		return createTextArea(TM.translate(KaizenTranslator.COMENTARIO), "comentario");
	}

}
