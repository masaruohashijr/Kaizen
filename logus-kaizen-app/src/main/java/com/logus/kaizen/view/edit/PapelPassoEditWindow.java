package com.logus.kaizen.view.edit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import com.logus.core.model.dialog.DialogButtonType;
import com.logus.core.model.persistence.PersistenceException;
import com.logus.core.model.persistence.jpa.JpaValidator;
import com.logus.core.view.dialog.DialogButton;
import com.logus.core.view.dialog.Responsiveness;
import com.logus.core.view.exceptions.ExceptionHandler;
import com.logus.core.view.list.BeanGrid;
import com.logus.kaizen.model.apoio.processo.Passo;
import com.logus.kaizen.model.apoio.projeto.Papel;
import com.logus.kaizen.model.apoio.tipomondai.PapelPassoItem;
import com.logus.kaizen.view.apoio.projeto.PapelPassoForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 * Classe que servirá de ponto de partida para janelas que apresentam
 * formulários, com opções para confirmação e cancelamento da edição.
 *
 * @author Masaru Ohashi Júnior
 * @version 1.0.0
 * @since 22 de fev de 2019
 */
@SuppressWarnings("serial")
public class PapelPassoEditWindow<T extends Object> extends Dialog {

	/**
	 * Determina se o formulário apresenta os objetos somente para leitura.
	 */
	private boolean readOnly;

	/**
	 * Formulário a ser usado para editar o objeto.
	 */
	private Collection<PapelPassoForm> forms;

	private PapelPassoForm form;

	/**
	 * Título da janela.
	 */
	private String caption;

	/**
	 * @return o objeto que está sendo editado.
	 */
	public PapelPassoItem getObject() {
		return form.getObject();
	}

	/**
	 * Qual botão será disponível para a confirmação do diálogo.
	 */
	DialogButtonType confirmButtonType = DialogButtonType.SAVE;

	/**
	 * Layout principal vertical da janela.
	 */
	private VerticalLayout vLayout;

	/**
	 * Construtor.
	 *
	 * @param caption           título da janela.
	 * @param form              formulário que permite a alteração dos campos do
	 *                          objeto.
	 * @param confirmButtonType tipo de botão para confirmação da edição.
	 * @param readOnly          se {@code true}, a janela será usada apenas para a
	 *                          visualização dos dados do objeto informado.
	 */
	public PapelPassoEditWindow(String caption, PapelPassoForm form, DialogButtonType confirmButtonType,
			boolean readOnly) {
		this(caption, form, confirmButtonType, readOnly, 1000, 600);
	}

	/**
	 * Largura pretendida para o diálogo. Será menor se não houver espaço disponível
	 * no navegador.
	 */
	private int intendedWidth;
	/**
	 * Altura pretendida para o diálogo. Será menor se não houver espaço disponível
	 * no navegador.
	 */
	private int intendedHeight;

	/**
	 * Construtor.
	 *
	 * @param caption           título da janela.
	 * @param liberacaoForm     formulário que permite a alteração dos campos do
	 *                          objeto.
	 * @param confirmButtonType tipo de botão para confirmação da edição.
	 * @param readOnly          se {@code true}, a janela será usada apenas para a
	 *                          visualização dos dados do objeto informado.
	 * @param intendedWidth     inicializa {@link #intendedWidth}.
	 * @param intendedHeight    inicializa {@link #intendedHeight}.
	 */
	public PapelPassoEditWindow(String caption, PapelPassoForm papelPassoForm, DialogButtonType confirmButtonType,
			boolean readOnly, int intendedWidth, int intendedHeight) {
		super();
		// Diferenças
		this.forms = new ArrayList<>();
		this.forms.add(papelPassoForm);
		// fim Diferencas
		this.confirmButtonType = confirmButtonType;
		this.form = papelPassoForm;
		this.readOnly = readOnly;
		this.caption = caption;
		this.intendedWidth = intendedWidth;
		this.intendedHeight = intendedHeight;
		setCloseOnOutsideClick(false);
		add(createVerticalLayout());
		addButtonsShortcuts(papelPassoForm);
	}

	@Override
	public void open() {
		Responsiveness.control(this, intendedWidth, intendedHeight);
		super.open();
	}

	@Override
	public void close() {
		Responsiveness.release(this);
		super.close();
	}

	/**
	 * Permite definir atalhos para os botões inseridos em cada diálogo.
	 *
	 * @param dispatcher componente capaz de sinalizar o pressionamento do teclado.
	 */
	private void addButtonsShortcuts(Component dispatcher) {
		dispatcher.getElement().addEventListener("keypress", event -> confirmAction())
				.setFilter("event.key == 'Enter'");
	}

	/**
	 * Cria o layout principal da janela.
	 *
	 * @return o layout criado.
	 *
	 */
	private Component createVerticalLayout() {
		vLayout = new VerticalLayout();
		vLayout.add(createTitle());
		if (form != null) {
			form.setReadOnly(readOnly);
			vLayout.add(form);
			if (intendedWidth > 0) {
				vLayout.setHorizontalComponentAlignment(Alignment.STRETCH, form);
			}
			if (intendedHeight > 0) {
				vLayout.expand(form);
			}
		}
		HorizontalLayout buttonsBar = createButtonsBar();
		vLayout.add(buttonsBar);
		vLayout.setHorizontalComponentAlignment(Alignment.END, buttonsBar);
		vLayout.setPadding(false);
		vLayout.setSizeFull();
		return vLayout;
	}

	/**
	 * Cria um título para a janela.
	 *
	 * @return o componente criado.
	 */
	private Component createTitle() {
		Html captionLabel = new Html(String.format("<h4>%s</h4>", caption));
		captionLabel.getElement().getStyle().set("margin-block-start", "10px");
		captionLabel.getElement().getStyle().set("margin-block-end", "0px");
		return captionLabel;
	}

	/**
	 * Cria a barra de botões que permite confirmar ou cancelar a edição.
	 *
	 * @return o {@link HorizontalLayout} contendo os botões.
	 */
	public HorizontalLayout createButtonsBar() {
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setWidth("100%");
		hLayout.add(createConfirmButton(), createCancelButton());
		if (hasRegisteredHelp()) {
			hLayout.add(createHelpButton());
		}
		hLayout.setSpacing(true);
		hLayout.setJustifyContentMode(JustifyContentMode.END);
		return hLayout;
	}

	/**
	 * Determina se há um tópico de ajuda registrado para este objeto.
	 *
	 * @return {@code true} se houver tal tópico registrado e {@code false} se não
	 *         houver.
	 */
	protected boolean hasRegisteredHelp() {
		return false;
	}

	/**
	 * Cria o botão para apresentação do help de contexto.
	 *
	 * @return o botão criado.
	 */
	private Component createHelpButton() {
		return DialogButton.createButton(DialogButtonType.HELP, () -> {

		});
	}

	/**
	 * Cria o botão que confirma as alterações realizadas no objeto que está sendo
	 * editado.
	 *
	 * @return o botão criado.
	 */
	private Button createConfirmButton() {
		return DialogButton.createButton(confirmButtonType, () -> {
			confirmAction();
		});
	}

	/**
	 * Ação executada quando o botão de confirmação é pressionado.
	 */
	private void confirmAction() {
		try {
			if (!readOnly) {
				BeanGrid<Passo> gridPassosSelector = form.getPassosSelector().getGrid();
				BeanGrid<Papel> gridPapeisSelector = form.getPapeisSelector().getGrid();
				Set<Passo> selectedPassos = gridPassosSelector.getSelectedItems();
				Set<Papel> selectedPapeis = gridPapeisSelector.getSelectedItems();
				Object[] arrayPassos = selectedPassos.toArray();
				Object[] arrayPapeis = selectedPapeis.toArray();
				PapelPassoItem clone = null;
				for (int i = 0; i < arrayPassos.length; i++) {
					for (int j = 0; j < arrayPapeis.length; j++) {
						clone = new PapelPassoItem();
						clone.assignFrom(form.getObject());
						Passo objPasso = (Passo) arrayPassos[i];
						Papel objPapel = (Papel) arrayPapeis[j];
						clone.setPasso(objPasso);
						clone.setPapel(objPapel);
						validate(clone);
						confirm(clone);
					}
				}
			}
			close();

		} catch (Exception e) {
			ExceptionHandler.handleErrorException(e);
		}
	}

	private void validate(PapelPassoItem papelPassoItem) {
		PersistenceException ex = new PersistenceException();
		// Validações de anotações
		try {
			JpaValidator.validate(papelPassoItem);
		} catch (PersistenceException e) {
			ex.appendException(e);
		}
		if (ex.hasErrors()) {
			throw ex;
		}
	}

	/**
	 * Cria o botão que cancela as alterações realizadas no objeto que está sendo
	 * alterado.
	 *
	 * @return o botão criado.
	 */
	private Button createCancelButton() {
		Button button = DialogButton.createButton(DialogButtonType.CANCEL, () -> {
			cancel(form.getObject());
			close();
		});
		button.setVisible(!readOnly);
		return button;
	}

	/**
	 * Método que permite definir o comportamento ao confirmar a edição.
	 *
	 * @param papelPassoItem objeto que será persistido.
	 * @throws Exception qualquer problema ocorrido durante a confirmação.
	 */
	public void confirm(PapelPassoItem papelPassoItem) throws Exception {
		// opcional na visualização de objetos.
	}

	/**
	 * Método que permite definir o comportamento ao cancelar a edição.
	 *
	 * @param itemSolicitacao objeto que estava sendo editado e cuja edição foi
	 *                        cancelada.
	 */
	public void cancel(PapelPassoItem papelPassoItem) {
		// opcional
	}

}
