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
import com.logus.kaizen.model.apoio.ApoioDataService;
import com.logus.kaizen.model.apoio.funcao.Funcao;
import com.logus.kaizen.model.apoio.processo.Passo;
import com.logus.kaizen.model.apoio.processo.Processo;
import com.logus.kaizen.model.apoio.tipomondai.FuncaoPassoItem;
import com.logus.kaizen.model.apoio.tipomondai.TipoMondai;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.kaizen.view.apoio.funcao.FuncaoGrid;
import com.logus.kaizen.view.apoio.processo.PassoGrid;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.dom.Element;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class FuncaoPassoForm extends BeanForm<FuncaoPassoItem> {

	/**
	 *
	 */
	private static final long serialVersionUID = -5726930569029997422L;
	private KaizenListSelector<Passo> passosSelector;
	private KaizenListSelector<Funcao> funcoesSelector;
	private Collection<Passo> passos;
	private Collection<Funcao> funcoes;

	/**
	 * Método construtor
	 *
	 * @param TipoMondai object
	 */
	public FuncaoPassoForm(FuncaoPassoItem funcaoPasso) {
		super(funcaoPasso);
		TipoMondai tipoMondai = funcaoPasso.getTipoMondai();
		if (null != tipoMondai) {
			Processo processo = tipoMondai.getProcesso();
			if (null != processo) {
				passos = processo.getPassos();
			} else {
				passos = new ArrayList<Passo>();
			}
		}
		funcoes = ApoioDataService.get().getFuncaoDao().loadFuncoes();
		add(focus(fullWidth(createPassoListSelector())));
		add(fullWidth(createFuncaoListSelector()));
		FormLayout frmLayout = new FormLayout();
		frmLayout.setSizeFull();
		frmLayout.add(fullWidth(createDescricaoTextArea()));
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

	private Component createFuncaoListSelector() {
		Funcao papelSelecionado = getObject().getFuncao();
		Collection<Funcao> selected = new LinkedList<>();
		if (null != papelSelecionado) {
			selected.add(papelSelecionado);
		}
		funcoesSelector = new KaizenListSelector<Funcao>("", false,
				new FuncaoGrid(), SelectionMode.MULTI, funcoes, selected);
		Element element = funcoesSelector.getElement();
		element.insertChild(0, new Html(String.format("<label>%s</label>", TM.translate(KaizenTranslator.FUNCAO_PLURAL))).getElement());
		funcoesSelector.setSizeFull();
		return funcoesSelector;
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

	public KaizenListSelector<Funcao> getFuncoesSelector() {
		return funcoesSelector;
	}

}
