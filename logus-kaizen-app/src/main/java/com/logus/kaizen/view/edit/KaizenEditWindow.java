package com.logus.kaizen.view.edit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import com.logus.common.base.Strings;
import com.logus.core.model.dialog.DialogButtonType;
import com.logus.core.model.persistence.PersistenceException;
import com.logus.core.model.persistence.jpa.JpaValidator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.dialog.DialogButton;
import com.logus.core.view.dialog.Responsiveness;
import com.logus.core.view.form.BeanForm;
import com.logus.kaizen.model.apoio.processo.Passo;
import com.logus.kaizen.model.apoio.tipomondai.AbstractAtribuicaoPassoItem;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.kaizen.view.list.ControleTempos;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
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
public abstract class KaizenEditWindow<T extends Object> extends Dialog {

	/**
	 * Determina se o formulário apresenta os objetos somente para leitura.
	 */
	private boolean readOnly;

	/**
	 * Formulário a ser usado para editar o objeto.
	 */
	private Collection<BeanForm<T>> forms;

	protected BeanForm<T> form;

	/**
	 * Título da janela.
	 */
	private String caption;

	/**
	 * @return o objeto que está sendo editado.
	 */
	public T getObject() {
		return (T) form.getObject();
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
	public KaizenEditWindow(String caption, BeanForm<T> form, DialogButtonType confirmButtonType, boolean readOnly) {
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

	private Collection<AbstractAtribuicaoPassoItem> atribuicoesPassoItem;

	private String buttonCaption;

	private ControleTempos controleTempos;

	/**
	 * Construtor.
	 *
	 * @param caption           título da janela.
	 * @param form              formulário que permite a alteração dos campos do
	 *                          objeto.
	 * @param confirmButtonType tipo de botão para confirmação da edição.
	 * @param readOnly          se {@code true}, a janela será usada apenas para a
	 *                          visualização dos dados do objeto informado.
	 * @param intendedWidth     inicializa {@link #intendedWidth}.
	 * @param intendedHeight    inicializa {@link #intendedHeight}.
	 */
	public KaizenEditWindow(String caption, BeanForm<T> form, DialogButtonType confirmButtonType, boolean readOnly,
			int intendedWidth, int intendedHeight) {
		super();
		// Diferenças
		this.forms = new ArrayList<>();
		this.forms.add(form);
		// fim Diferencas
		this.confirmButtonType = confirmButtonType;
		this.form = form;
		this.readOnly = readOnly;
		this.caption = caption;
		this.intendedWidth = intendedWidth;
		this.intendedHeight = intendedHeight;
		setCloseOnOutsideClick(false);
		add(createVerticalLayout());
		addButtonsShortcuts(form);
	}

	public KaizenEditWindow(
			String caption,
			BeanForm<T> form,
			String buttonCaption,
			boolean readOnly,
			int intendedWidth,
			int intendedHeight,
			ControleTempos controleTempos,
			Collection<AbstractAtribuicaoPassoItem> atribuicoesPassoItem,
			boolean hasShortCuts) {
		super();
		this.buttonCaption = buttonCaption;
		this.controleTempos = controleTempos;
		// Diferenças
		this.forms = new ArrayList<>();
		this.forms.add(form);
		// fim Diferencas
		this.confirmButtonType = DialogButtonType.SAVE;
		this.form = form;
		this.readOnly = readOnly;
		this.caption = caption;
		this.intendedWidth = intendedWidth;
		this.intendedHeight = intendedHeight;
		this.atribuicoesPassoItem = atribuicoesPassoItem;
		setCloseOnOutsideClick(false);
		add(createVerticalLayout());
		if(hasShortCuts) {
			addButtonsShortcuts(form);
		}
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
		dispatcher.getElement().addEventListener("keypress", event -> confirmAction(null))
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
		HorizontalLayout h1 = new HorizontalLayout();
		HorizontalLayout h2 = new HorizontalLayout();
		hLayout.setWidth("100%");
		h1.setWidth("100%");
		if(controleTempos.isControlaTempo()) {
			h1.add(createChronosButton());
		}
		h2.setSpacing(true);
		h2.setJustifyContentMode(JustifyContentMode.START);
		hLayout.add(h1);
		h2.setWidth("100%");
		Passo passo = null;
		HashSet<String> botoes = new HashSet<>();
		if (null != atribuicoesPassoItem && !atribuicoesPassoItem.isEmpty()) {
			for (AbstractAtribuicaoPassoItem atribuicaoPassoItem : atribuicoesPassoItem) {
				passo = (null == atribuicaoPassoItem.getPasso())?(Passo) atribuicaoPassoItem:atribuicaoPassoItem.getPasso();
				String nomePasso = (null != passo) ? passo.getTransicao().getNome() : atribuicaoPassoItem.getAtribuicao();
				if (null != passo && !botoes.contains(nomePasso)) {
					botoes.add(nomePasso);
					Button createPassoButton = createPassoButton(atribuicaoPassoItem);
					h2.add(createPassoButton);
				}
			}
		}
		if (Strings.isEmpty(buttonCaption)) {
			h2.add(createConfirmButton());
		} else {
			h2.add(createActionButton());
		}
		h2.add(createCancelButton());
		if (hasRegisteredHelp()) {
			h2.add(createHelpButton());
		}
		h2.setSpacing(true);
		h2.setJustifyContentMode(JustifyContentMode.END);
		hLayout.add(h2);
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
			confirmAction(null);
		});
	}

	private Button createChronosButton() {
		String titulo = "Iniciar "+TM.translate(KaizenTranslator.CHRONOS);
		Button button = createButton(titulo, VaadinIcon.CLOCK.create(), () -> {
			chronosAction();
		});
		button.getElement().setAttribute("theme", "primary");
		if(controleTempos.isChronosAtivo()) {
			titulo = "Terminar "+TM.translate(KaizenTranslator.CHRONOS);
			button.setText(titulo);
			button.getElement().getStyle().set("backgroundColor", "red");
		}
		return button;
	}

	private Button createActionButton() {
		return createButton(this.buttonCaption, () -> {
			confirmAction(null);
		});
	}

	private Button createPassoButton(AbstractAtribuicaoPassoItem atribuicaoPassoItem) {
		Passo passo = (null == atribuicaoPassoItem.getPasso())?(Passo) atribuicaoPassoItem:atribuicaoPassoItem.getPasso();
		Button passoButton = createButton(passo.getTransicao().getNome(), () -> {
			confirmAction(atribuicaoPassoItem);
		});
		passoButton.getStyle().set("backgroundColor", "green");
		return passoButton;
	}

	private Button createButton(String caption, final Runnable action) {
		DialogButtonType type = DialogButtonType.SAVE;
		Button button = createButton(caption, getIcon(type), action);
		setStyle(type, button);
		return button;
	}

	private Button createButton(String caption, Icon icon, final Runnable action) {
		Button button = new KaizenDialogButton();
		button.addClickListener(ev -> {
			action.run();
		});
		button.setText(caption);
		button.setIcon(icon);
		return button;
	}

	private static Icon getIcon(DialogButtonType type) {
		switch (type) {
		case OK:
		case SAVE:
		case YES:
			return VaadinIcon.CHECK.create();
		case CANCEL:
		case NO:
		case CLOSE:
			return VaadinIcon.CLOSE.create();
		case HELP:
			return VaadinIcon.QUESTION_CIRCLE.create();
		default:
			return null;
		}
	}

	/**
	 * Ação executada quando o botão de confirmação é pressionado.
	 */
	protected abstract void confirmAction(AbstractAtribuicaoPassoItem atribuicaoPassoItem);
	protected abstract void chronosAction();

	protected void validate(Object object) {
		PersistenceException ex = new PersistenceException();
		// Validações de anotações
		try {
			JpaValidator.validate(object);
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
	 * @param itemSolicitacao objeto que será persistido.
	 * @throws Exception qualquer problema ocorrido durante a confirmação.
	 */
	public void confirm(T object) throws Exception {
		// opcional na visualização de objetos.
	}

	/**
	 * Método que permite definir o comportamento ao cancelar a edição.
	 *
	 * @param itemSolicitacao objeto que estava sendo editado e cuja edição foi
	 *                        cancelada.
	 */
	public void cancel(Object object) {
		// opcional
	}

	/**
	 * Promove validações a partir de anotações JPA e de campo.
	 */
	protected void validate() {
		validate(this.form.getObject());
	}

	private static void setStyle(DialogButtonType type, Button button) {
		switch (type) {
		case OK:
		case SAVE:
		case YES:
		case CLOSE:
			button.getElement().setAttribute("theme", "primary");
			break;
		case CANCEL:
		case NO:
		case HELP:
		default:
			break;
		}

	}
}
