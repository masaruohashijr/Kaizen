/**
 *
 */
package com.logus.kaizen.view.apoio.projeto;

import java.util.ArrayList;
import java.util.Collection;

import com.logus.core.model.translation.TM;
import com.logus.core.view.form.BeanForm;
import com.logus.core.view.form.Shuttle;
import com.logus.kaizen.model.apoio.UsuarioDataService;
import com.logus.kaizen.model.apoio.projeto.ItemPapel;
import com.logus.kaizen.model.apoio.projeto.Papel;
import com.logus.kaizen.model.apoio.usuario.Usuario;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.kaizen.view.form.KaizenTabSheet;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class PapelForm extends BeanForm<Papel> {

	/**
	 *
	 */
	private static final long serialVersionUID = -5726930569029997422L;

	/**
	 * TabSheet.
	 */
	private KaizenTabSheet tabs;

	/**
	 * Método construtor
	 *
	 * @param Passo object
	 */
	public PapelForm(Papel papel) {
		super(papel);
		add(expand(fullSize(createTabSheet())));
	}

	private Component createTabSheet() {
		tabs = new KaizenTabSheet();
		Component dadosBasicosPage = fullSize(createPrincipalPage());
		tabs.addTab("Dados Básicos", VaadinIcon.INFO_CIRCLE.create(), dadosBasicosPage);
		VerticalLayout colaboradoresPage = new VerticalLayout();
		tabs.addTab("Colaboradores", VaadinIcon.INFO_CIRCLE.create(), colaboradoresPage);
		Tabs tbs = tabs.getTabs(tabs);
		Tab colaboradoresTab = tabs.getTab(1);
		tbs.addSelectedChangeListener(event -> {
			Component selectedPage = tbs.getSelectedTab();
			selectedPage.setVisible(true);
			if (selectedPage.equals(colaboradoresTab) && colaboradoresPage.getChildren().count() == 0) {
				colaboradoresPage.add(fullSize(createColaboradoresSelector()));
			}
		});
		return tabs;
	}

	private Component createColaboradoresSelector() {
		VerticalLayout vLayout = new VerticalLayout();
		Collection<Usuario> usuarios = UsuarioDataService.get().getUsuarioDao().loadUsuarios();
		Shuttle<ItemPapel> shuttle = createShuttle(TM.translate(KaizenTranslator.COLABORADOR_PLURAL),
				converter(usuarios), getObject().getItensPapel());
		vLayout.setSizeFull();
		vLayout.add(shuttle);
		return vLayout;
	}

	private Collection<ItemPapel> converter(Collection<Usuario> usuarios) {
		Collection<ItemPapel> collection = new ArrayList<>();
		for (Usuario usuario : usuarios) {
			ItemPapel itemPapel = new ItemPapel();
			itemPapel.setPapel(getObject());
			itemPapel.setCodigoUsuario(usuario.getCodigo());
			collection.add(itemPapel);
		}
		return collection;
	}

	private Component createPrincipalPage() {
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.add(focus(fullWidth(createNomeTextField())));
		vLayout.add(fullWidth(createDescricaoTextArea()));
		vLayout.add(createAtivoCheckBox());
		return vLayout;
	}

	/**
	 * Cria um campo textfield para inserção de um nome para o Passo.
	 *
	 * @return Component
	 */
	private TextField createNomeTextField() {
		return createTextField(TM.translate(KaizenTranslator.NOME), "nome");
	}

	/**
	 * Cria um campo textArea para inserção de uma descrição para o Passo.
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
