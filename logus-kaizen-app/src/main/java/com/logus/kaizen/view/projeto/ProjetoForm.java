/**
 *
 */
package com.logus.kaizen.view.projeto;

import com.logus.core.model.dialog.DialogButtonType;
import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.form.BeanForm;
import com.logus.core.view.form.TabSheet;
import com.logus.core.view.list.AbstractListEditor;
import com.logus.core.view.list.BeanGrid;
import com.logus.kaizen.model.apoio.ApoioDataService;
import com.logus.kaizen.model.apoio.tipomondai.TipoMondai;
import com.logus.kaizen.model.apoio.tipomondai.TipoMondaiProjeto;
import com.logus.kaizen.model.projeto.Papel;
import com.logus.kaizen.model.projeto.Projeto;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class ProjetoForm extends BeanForm<Projeto> {

	/**
	 *
	 */
	private static final long serialVersionUID = -5726930569029997422L;
	private TabSheet tabs;
	private AbstractListEditor<Papel> papeisEditor;
	private AbstractListEditor<TipoMondaiProjeto> tiposMondaiProjetoEditor;

	/**
	 * Método construtor
	 *
	 * @param Projeto object
	 */
	protected ProjetoForm(Projeto object) {
		super(object);
		add(fullHeight(createTabSheet()));
	}

	private Component createTabSheet() {
		tabs = new TabSheet();
		tabs.addTab("Dados Básicos", VaadinIcon.INFO_CIRCLE.create(), createPrincipalPage());
		tabs.addTab("Papéis", VaadinIcon.INFO_CIRCLE.create(), createPapeisEditor());
		tabs.addTab("Tipos de Mondai", VaadinIcon.INFO_CIRCLE.create(), createTiposMondaiEditor());
		return tabs;
	}

	private Component createPapeisEditor() {
		papeisEditor = new AbstractListEditor<Papel>(DialogButtonType.OK) {

			/**
			 *
			 */
			private static final long serialVersionUID = 4135382607413869340L;

			@Override
			protected BeanForm<Papel> createForm(Papel papel) {
				papel.setProjeto(getObject());
				return new PapelForm(papel);
			}

			@Override
			protected BeanGrid<Papel> createGrid() {
				return new PapelGrid();
			}

			@Override
			protected void deleteObject(Papel papel) throws Exception {
				getObject().getPapeis().remove(papel);
			}

			@Override
			protected Object getArtigoEntidade() {
				// TODO Auto-generated method stub
				return TM.translate(CoreTranslator.ART_MAS_SING);
			}

			@Override
			protected Object getNomeEntidade() {
				return TM.translate(KaizenTranslator.PROJETO_PAPEL);
			}

			@Override
			protected String getNomeEntidadePlural() {
				// TODO Auto-generated method stub
				return TM.translate(KaizenTranslator.PROJETO_PAPEL_PLURAL);
			}

			@Override
			protected void insertObject(Papel papel) throws Exception {
				getObject().getPapeis().add(papel);
			}

			@Override
			protected void updateObject(Papel papel) throws Exception {
			}
		};
		papeisEditor.setSizeFull();
		papeisEditor.getElement().removeChild(0);
		papeisEditor.updateObjects(getObject().getPapeis());
		return papeisEditor;
	}

	private Component createTiposMondaiEditor() {
		tiposMondaiProjetoEditor = new AbstractListEditor<TipoMondaiProjeto>(DialogButtonType.OK) {

			/**
			 *
			 */
			private static final long serialVersionUID = 4135382607413869340L;

			@Override
			protected BeanForm<TipoMondaiProjeto> createForm(TipoMondaiProjeto tipoMondai) {
				tipoMondai.setProjeto(getObject());
				return new TipoMondaiProjetoForm(tipoMondai);
			}

			@Override
			protected BeanGrid<TipoMondaiProjeto> createGrid() {
				return new TipoMondaiProjetoGrid();
			}

			@Override
			protected void deleteObject(TipoMondaiProjeto tipoMondaiProjeto) throws Exception {
				getObject().getTiposMondaiProjeto().remove(tipoMondaiProjeto);
			}

			@Override
			protected Object getArtigoEntidade() {
				// TODO Auto-generated method stub
				return TM.translate(CoreTranslator.ART_MAS_SING);
			}

			@Override
			protected Object getNomeEntidade() {
				return TM.translate(KaizenTranslator.TIPO_MONDAI_PROJETO);
			}

			@Override
			protected String getNomeEntidadePlural() {
				// TODO Auto-generated method stub
				return TM.translate(KaizenTranslator.TIPO_MONDAI_PROJETO_PLURAL);
			}

			@Override
			protected void insertObject(TipoMondaiProjeto tipoMondaiProjeto) throws Exception {
				getObject().getTiposMondaiProjeto().add(tipoMondaiProjeto);
			}

			@Override
			protected void updateObject(TipoMondaiProjeto tipoMondaiProjeto) throws Exception {
			}
		};
		tiposMondaiProjetoEditor.setSizeFull();
		tiposMondaiProjetoEditor.getElement().removeChild(0);
		tiposMondaiProjetoEditor.updateObjects(getObject().getTiposMondaiProjeto());
		return tiposMondaiProjetoEditor;
	}

	private Component createPrincipalPage() {
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.add(focus(fullWidth(createClienteComboBox())));
		vLayout.add(fullWidth(createNomeTextField()));
		vLayout.add(fullWidth(createDescricaoTextArea()));
		vLayout.add(fullWidth(createChaveJiraTextField()));
		vLayout.add(fullWidth(createChaveMondaiTextField()));
		vLayout.add(createAtivoCheckBox());
		return vLayout;
	}

	private Component createChaveMondaiTextField() {
		return createTextField(TM.translate(KaizenTranslator.PROJETO_CHAVE_MONDAI), "chaveMondai");
	}

	private Component createChaveJiraTextField() {
		return createTextField(TM.translate(KaizenTranslator.PROJETO_CHAVE_JIRA), "chaveJira");
	}

	private Component createClienteComboBox() {
		return createBeanComboBox(TM.translate(KaizenTranslator.CLIENTE), "cliente",
				ApoioDataService.get().getClienteDao().loadAll());
	}

	/**
	 * Cria um campo textfield para inserção de um nome para o Projeto.
	 *
	 * @return Component
	 */
	private TextField createNomeTextField() {
		return createTextField(TM.translate(KaizenTranslator.NOME), "nome");
	}

	/**
	 * Cria um campo textArea para inserção de uma descrição para o Projeto.
	 *
	 * @return Component
	 */
	private TextArea createDescricaoTextArea() {
		return createTextArea(TM.translate(KaizenTranslator.PROJETO_DESCRICAO), "descricao");
	}

	/**
	 * Cria um checkbox para definir se o projeto estará ativado no momento da
	 * inserção, por padrão ele já está ativado.
	 *
	 * @return Component
	 */
	private Checkbox createAtivoCheckBox() {
		return createCheckBoxField(TM.translate(KaizenTranslator.ATIVO), "ativo");
	}

}
