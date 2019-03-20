/**
 *
 */
package com.logus.kaizen.view.mondai;

import java.util.ArrayList;
import java.util.Collection;

import com.logus.core.model.aut.login.LoginManager;
import com.logus.core.model.translation.TM;
import com.logus.core.view.form.BeanComboBox;
import com.logus.core.view.form.BeanForm;
import com.logus.kaizen.model.apoio.ApoioDataService;
import com.logus.kaizen.model.apoio.projeto.Projeto;
import com.logus.kaizen.model.apoio.tipomondai.TipoMondai;
import com.logus.kaizen.model.apoio.tipomondai.TipoMondaiProjeto;
import com.logus.kaizen.model.solicitacao.Solicitacao;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class MondaiForm extends BeanForm<Solicitacao> {

	/**
	 *
	 */
	private static final long serialVersionUID = -5726930569029997422L;

	private BeanComboBox<TipoMondai> cbTipoMondai;

	private BeanComboBox<Projeto> cbProjeto;

	/**
	 * Método construtor
	 *
	 * @param Solicitacao object
	 */
	protected MondaiForm(Solicitacao object) {
		super(object);
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.add(focus(fullWidth(createTipoBeanComboBox())));
		hLayout.add(fullWidth(createProjetoBeanComboBox()));
		hLayout.add(fullWidth(createSolicitanteTextFieldEdit()));
		add(fullWidth(hLayout));
		add(fullWidth(createTituloMondaiTextField()));
		add(fullSize(createDescricaoTextArea()));
	}

	private Component createTipoBeanComboBox() {
		cbTipoMondai = createBeanComboBox(TM.translate(KaizenTranslator.TIPO_MONDAI), "tipoMondai",
				ApoioDataService.get().getTipoMondaiDao().loadTiposMondai());
		cbTipoMondai.addValueChangeListener(e -> {
			TipoMondai tipoMondai = cbTipoMondai.getValue();
			if (null != tipoMondai.getProcesso()) {
				this.cbProjeto.clear();
				cbProjeto.setEnabled(false);
			} else {
				Collection<TipoMondaiProjeto> tiposMondaiProjeto = tipoMondai.getTiposMondaiProjeto();
				Collection<Projeto> projetos = new ArrayList<>();
				for (TipoMondaiProjeto tipoMondaiProjeto : tiposMondaiProjeto) {
					projetos.add(tipoMondaiProjeto.getProjeto());
				}
				this.cbProjeto.setItems(projetos);
				int size = projetos.size();
				switch (size) {
				case 0:
					break;
				case 1:
					Projeto projeto = (Projeto) projetos.toArray()[0];
					cbProjeto.setValue(projeto);
					break;
				default:
					this.cbProjeto.setEnabled(true);
				}
			}
		});
		return cbTipoMondai;
	}

	private Component createProjetoBeanComboBox() {
		this.cbProjeto = createBeanComboBox(TM.translate(KaizenTranslator.PROJETO), "projeto",
				new ArrayList<Projeto>());
		this.cbProjeto.setEnabled(false);
		return this.cbProjeto;
	}

	private TextField createTituloMondaiTextField() {
		return createTextField(TM.translate(KaizenTranslator.SOLICITACAO_TITULO_MONDAI), "tituloMondai");
	}

	private TextArea createDescricaoTextArea() {
		return createTextArea(TM.translate(KaizenTranslator.SOLICITACAO_DESCRICAO), "descricao");
	}

	private TextField createSolicitanteTextFieldEdit() {
		TextField tfNome = createTextField(TM.translate(KaizenTranslator.SOLICITACAO_CODIGO_SOLICITANTE),
				"codigoSolicitante");
		String codigoUsuario = LoginManager.getAccessControl().getUser().getCodigo();
		tfNome.setValue(codigoUsuario);
		tfNome.setEnabled(false);
		return tfNome;
	}

	public void reload() {
		if (null != cbTipoMondai) {
			cbTipoMondai.setItems(ApoioDataService.get().getTipoMondaiDao().loadTiposMondai());
		}

	}

}
