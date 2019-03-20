/**
 *
 */
package com.logus.kaizen.view.apoio.projeto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import com.logus.core.model.translation.TM;
import com.logus.core.view.form.BeanForm;
import com.logus.kaizen.app.KaizenListSelector;
import com.logus.kaizen.model.apoio.processo.Passo;
import com.logus.kaizen.model.apoio.processo.Processo;
import com.logus.kaizen.model.apoio.projeto.Papel;
import com.logus.kaizen.model.apoio.projeto.Projeto;
import com.logus.kaizen.model.apoio.tipomondai.PapelPassoItem;
import com.logus.kaizen.model.apoio.tipomondai.TipoMondaiProjeto;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.kaizen.view.apoio.processo.PassoGrid;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.dom.Element;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class PapelPassoForm extends BeanForm<PapelPassoItem> {

	/**
	 *
	 */
	private static final long serialVersionUID = -5726930569029997422L;
	private KaizenListSelector<Passo> passosSelector;
	private KaizenListSelector<Papel> papeisSelector;
	private Collection<Passo> passos;
	private Collection<Papel> papeis;

	/**
	 * Método construtor
	 *
	 * @param TipoMondai object
	 */
	protected PapelPassoForm(PapelPassoItem papelPasso) {
		super(papelPasso);
		TipoMondaiProjeto tipoMondaiProjeto = papelPasso.getTipoMondaiProjeto();
		if (null != tipoMondaiProjeto) {
			Processo processo = tipoMondaiProjeto.getProcesso();
			if (null != processo) {
				passos = processo.getPassos();
			} else {
				passos = new ArrayList<Passo>();
			}
			Projeto projeto = tipoMondaiProjeto.getProjeto();
			if (null != projeto) {
				papeis = projeto.getPapeis();
			}
		}
		add(focus(fullWidth(createPassoListSelector())));
		add(fullWidth(createPapelListSelector()));
		VerticalLayout frmLayout = new VerticalLayout();
		frmLayout.setSizeFull();
		frmLayout.add(fullWidth(fullHeight(createDescricaoTextArea())));
		frmLayout.add(createAtivoCheckBox());
		add(frmLayout);
	}

	private Component createPassoListSelector() {
		Passo passoSelecionado = getObject().getPasso();
		Collection<Passo> selected = new LinkedList<>();
		if (null != passoSelecionado) {
			selected.add(passoSelecionado);
		}
		passosSelector = new KaizenListSelector<Passo>("", false,
				new PassoGrid(), SelectionMode.MULTI, passos, selected);
		Element element = passosSelector.getElement();
		element.insertChild(0, new Html(String.format("<label>%s</label>", TM.translate(KaizenTranslator.PROCESSO_PASSO_PLURAL))).getElement());
		passosSelector.setSizeFull();
		return passosSelector;
	}

	private Component createPapelListSelector() {
		Papel papelSelecionado = getObject().getPapel();
		Collection<Papel> selected = new LinkedList<>();
		if (null != papelSelecionado) {
			selected.add(papelSelecionado);
		}
		papeisSelector = new KaizenListSelector<Papel>("", false,
				new PapelGrid(), SelectionMode.MULTI, papeis, selected);
		Element element = papeisSelector.getElement();
		element.insertChild(0, new Html(String.format("<label>%s</label>", TM.translate(KaizenTranslator.PROJETO_PAPEL_PLURAL))).getElement());
		papeisSelector.setSizeFull();
		return papeisSelector;
	}

	private TextArea createDescricaoTextArea() {
		TextArea taDescricao = createTextArea(TM.translate(KaizenTranslator.PAPEL_PASSO_ITEM_DESCRICAO), "descricao");
		taDescricao.setSizeFull();
		return taDescricao;
	}

	private Checkbox createAtivoCheckBox() {
		return createCheckBoxField(TM.translate(KaizenTranslator.ATIVO), "ativo");
	}

	public KaizenListSelector<Passo> getPassosSelector() {
		return passosSelector;
	}

	public KaizenListSelector<Papel> getPapeisSelector() {
		return papeisSelector;
	}

}
