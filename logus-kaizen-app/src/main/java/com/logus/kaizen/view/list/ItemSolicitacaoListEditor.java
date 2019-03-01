package com.logus.kaizen.view.list;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import javax.persistence.Entity;

import com.logus.kaizen.model.apoio.ambiente.Ambiente;
import com.logus.kaizen.model.solicitacao.ItemSolicitacao;
import com.logus.kaizen.view.edit.ItemSolicitacaoEditWindow;
import com.logus.kaizen.view.solicitacao.ItemSolicitacaoForm;
import com.logus.kaizen.view.solicitacao.ItemSolicitacaoGrid;
import com.logus.core.model.dialog.DialogButtonType;
import com.logus.core.model.list.ListEditorButtonType;
import com.logus.core.model.persistence.Assignable;
import com.logus.core.model.persistence.jpa.JpaReflections;
import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.model.util.Strings;
import com.logus.core.view.dialog.NotificationDialog;
import com.logus.core.view.dialog.YesNoDialog;
import com.logus.core.view.exceptions.ExceptionHandler;
import com.logus.core.view.form.FormLayoutUtil;
import com.logus.core.view.list.BeanGrid;
import com.logus.core.view.list.ListEditorButton;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Focusable;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

/**
 *
 * @param <T> tipo dos objetos a serem editados.
 * @author Walace Zloccowick Maia.
 */
@SuppressWarnings("serial")
public abstract class ItemSolicitacaoListEditor<T extends Object> extends VerticalLayout {

	/**
	 * Objeto que apresentará a listagem.
	 */
	private BeanGrid<ItemSolicitacao> grid;

	/**
	 * Botão para inclusão de um novo registro no Grid.
	 */
	private Button insertButton;

	/**
	 * Botão par exclusão de um registro do Grid.
	 */
	private Button deleteButton;

	/**
	 * Botão para edição de um registro do Grid.
	 */
	private Button updateButton;

	/**
	 * Botão para permitir a definição de um filtro avançado para o Grid.
	 */
	private Button filterButton;

	/**
	 * Componente que permitirá o filtro do Grid.
	 */
	private TextField filter;

	/**
	 * Botão que permitirá a visualização do registro selecionado.
	 */
	private Button viewButton;

	/**
	 * Botão que permitirá a cópia do registro selecionado.
	 */
	private Button copyButton;

	/**
	 * Determina se os objetos poderão ser apenas visualizados, sendo desativada a
	 * opção de alterá-los.
	 */
	private boolean readOnly;

	/**
	 * Define o botão de confirmação do fechamento da caixa de diálogo de edição do
	 * objeto.
	 */
	private DialogButtonType confirmButtonType;

	/**
	 * Largura da janela de edição.
	 */
	private int editWindowWidth = 800;

	/**
	 * Altura da janela de edição.
	 */
	private int editWindowHeight = -1;

	/**
	 * Determina se os títulos dos botões devem ser apresentados.
	 */
	private boolean showButtonCaptions;

	/**
	 * Barra de botões.
	 */
	private FlexLayout toolbar;

	/**
	 * @return {@link #editWindowWidth}.
	 */
	public int getEditWindowWidth() {
		return editWindowWidth;
	}

	/**
	 * @param editWindowWidth atualiza {@link #editWindowWidth}.
	 */
	public void setEditWindowWidth(int editWindowWidth) {
		this.editWindowWidth = editWindowWidth;
	}

	/**
	 * @return {@link #editWindowHeight}.
	 */
	public int getEditWindowHeight() {
		return editWindowHeight;
	}

	/**
	 * @param editWindowHeight atualiza {@link #editWindowHeight}.
	 */
	public void setEditWindowHeight(int editWindowHeight) {
		this.editWindowHeight = editWindowHeight;
	}

	/**
	 * @return {@link #grid}.
	 */
	public BeanGrid<ItemSolicitacao> getGrid() {
		return grid;
	}

	/**
	 * Salva os textos dos botões de forma permitir que {@link #showButtonCaptions}
	 * possa se alternar adequadamente entre {@code true} e {@code false}.
	 */
	private Map<Button, String> captions = new HashMap<Button, String>();

	/**
	 * Construtor.
	 *
	 * @param confirmButtonType inicializa {@link #confirmButtonType}.
	 */
	public ItemSolicitacaoListEditor(DialogButtonType confirmButtonType) {
		super();
		this.confirmButtonType = confirmButtonType;
		addClassName("list-editor");
		if (!Strings.isEmpty(getNomeEntidadePlural())) {
			add(createCaption());
		}
		add(FormLayoutUtil.fullWidth(createToolBar()));
		add(FormLayoutUtil.fullSize(internalCreateGrid()));
		setShowButtonCaptions(false);
	}

	/**
	 * Cria o {@link #grid}.
	 *
	 * @return o componente criado.
	 */
	private BeanGrid<ItemSolicitacao> internalCreateGrid() {
		grid = createGrid();
		grid.addSelectionListener(e -> {
			refreshButtons();
		});
		return grid;
	}

	/**
	 * Cria o título do diálogo.
	 *
	 * @return o componente criado.
	 */
	private Component createCaption() {
		Html captionLabel = new Html(String.format("<h3>%s</h3>", getNomeEntidadePlural()));
		captionLabel.getElement().getStyle().set("margin-block-start", "10px");
		captionLabel.getElement().getStyle().set("margin-block-end", "0px");
		return captionLabel;
	}

	/**
	 * @return {@link #readOnly}.
	 */
	public boolean isReadOnly() {
		return readOnly;
	}

	/**
	 * @param readOnly atualiza {@link #readOnly}.
	 */
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
		refreshButtons();
	}

	/**
	 * Controla a habilitação de botões em conformidade com o objeto selecionado.
	 */
	protected void refreshButtons() {
		Object selected = getSelectedObject();
		deleteButton.setEnabled(selected != null && !isReadOnly());
		updateButton.setEnabled(selected != null && !isReadOnly());
		copyButton.setEnabled(selected != null && !isReadOnly());
		insertButton.setEnabled(!isReadOnly());
		viewButton.setEnabled(selected != null);
		filterButton.setEnabled(false);
	}

	/**
	 * Cria a barra de botões.
	 *
	 * @return o barra criada.
	 */
	protected Component createToolBar() {
		toolbar = new FlexLayout();
		toolbar.getStyle().set("flex-wrap", "wrap");
		// toolbar.setDefaultVerticalComponentAlignment(Alignment.CENTER);
		// toolbar.setSpacing(false);
		Component filter = createFilter();
		toolbar.add(filter, createAdvancedFilterButton(), createInsertButton(), createEditButton(), createViewButton(),
				createCopyButton(), createDeleteButton());

		if (hasHelp()) {
			toolbar.add(createHelpButton());
		}
		toolbar.expand(filter);
		return toolbar;
	}

	/**
	 * @return {@code true} caso haja help previsto o contexto deste
	 *         {@link ItemSolicitacaoListEditor}.
	 */
	private boolean hasHelp() {
		return false;
	}

	/**
	 * Cria o botão para acionar o help de contexto para este editor.
	 *
	 * @return o botão criado.
	 */
	private Component createHelpButton() {
		Button helpButton = ListEditorButton.createButton(ListEditorButtonType.HELP, () -> {
		});
		return helpButton;
	}

	/**
	 * @return Retorna o objeto selecionado.
	 */
	public ItemSolicitacao getSelectedObject() {
		return (ItemSolicitacao) grid.getSelectedRow();
	}

	/**
	 * Cria o componente de filtro.
	 *
	 * @return o componente criado.
	 */
	private TextField createFilter() {
		filter = new TextField() {

			@Override
			public void setReadOnly(boolean readOnly) {
				// Será sempre editável independente do formulário onde for
				// inserido ser readOnly
			}

		};
		filter.setPlaceholder(TM.translate(CoreTranslator.LIST_EDITOR_TYPE_TO_FILTER));
		filter.addValueChangeListener(e -> setFilter(e.getValue().toString()));
		filter.setValueChangeMode(ValueChangeMode.EAGER);
		return filter;
	}

	/**
	 * Define o filtro que irá restringir os objetos da lista.
	 *
	 * @param filterString filtro restritor.
	 */
	protected void setFilter(String filterString) {
		grid.getDataProvider().clearFilters();
		if (filterString != null && !filterString.isEmpty()) {
			grid.getDataProvider().addFilter(item -> {
				for (Column<ItemSolicitacao> column : grid.getColumns()) {
					Object value = grid.getValue(column, item);
					if (value != null && value.toString().toLowerCase().contains(filterString.toLowerCase())) {
						return true;
					}
				}
				return false;
			});
		}
	}

	/**
	 * Cria o botão de filtro avançado.
	 *
	 * @return o objeto criado.
	 */
	private Component createAdvancedFilterButton() {
		return filterButton = ListEditorButton.createButton(ListEditorButtonType.FILTER,
				() -> notification("A ser implementado..."));

	}

	/**
	 * Cria o botão de inclusão.
	 *
	 * @return o objeto criado.
	 */
	private Component createInsertButton() {
		return insertButton = ListEditorButton.createButton(ListEditorButtonType.INSERT, () -> insert());
	}

	/**
	 * Cria o botão de edição.
	 *
	 * @return o objeto criado.
	 */
	private Component createEditButton() {
		return updateButton = ListEditorButton.createButton(ListEditorButtonType.EDIT, () -> edit());
	}

	/**
	 * Cria o botão de cópia.
	 *
	 * @return o objeto criado.
	 */
	private Component createCopyButton() {
		return copyButton = ListEditorButton.createButton(ListEditorButtonType.COPY, () -> copy());
	}

	/**
	 * Abre um diálogo permitindo a inclusão a partir de uma cópia do objeto
	 * selecionado.
	 */
	protected void copy() {
		insert(copyOf(getSelectedObject()), String.format(TM.translate(CoreTranslator.LIST_EDITOR_COPY_TITLE,
				getArtigoEntidade(), getNomeEntidade(), getSelectedObject())), copyButton);
	}

	/**
	 * Realiza a duplicação do objeto informado para fins de edição. O identificador
	 * do objeto poderá ser anulado e o seu título manipulado para garantir que
	 * possa ser diferenciado do objeto original. Esta implementação padrão irá
	 * apenas anular a chave primária, caso se trate de uma entidade JPA.
	 *
	 * @param object objeto cuja cópia será produzida.
	 * @return a cópia do objeto.
	 */
	protected ItemSolicitacao copyOf(ItemSolicitacao object) {
		if (object.getClass().isAnnotationPresent(Entity.class)) {
			ItemSolicitacao copy = clone(object);
			JpaReflections.setNullKey(copy);
			return copy;
		}
		throw new UnsupportedOperationException(
				TM.translate(CoreTranslator.LIST_EDITOR_COPY_OF_NOT_IMPLEMENTED, object.getClass()));
	}

	/**
	 * Cria o botão de edição.
	 *
	 * @return o objeto criado.
	 */
	private Component createViewButton() {
		return viewButton = ListEditorButton.createButton(ListEditorButtonType.VIEW, () -> view());
	}

	/**
	 * @return o título para o relatório que será apresentado.
	 */
	public String getReportTitle() {
		return getNomeEntidadePlural() == null ? getNomeEntidade().toString() : getNomeEntidadePlural();
	}

	/**
	 * Cria o botão de exclusão.
	 *
	 * @return o objeto criado.
	 */
	private Component createDeleteButton() {
		return deleteButton = ListEditorButton.createButton(ListEditorButtonType.DELETE, () -> delete());
	}

	/**
	 * Atualiza os objetos da lista.
	 *
	 * @param objects objetos a serem definidos para a lista.
	 */
	public void updateObjects(Collection<ItemSolicitacao> objects) {
		grid.setObjects(objects);
		refreshButtons();
		setFilter(filter.getValue());
	}

	/**
	 * Adiciona um novo objeto ao Grid.
	 *
	 * @param newObject objeto a ser adicionado.
	 */
	public void addObject(ItemSolicitacao newObject) {
		grid.addObject(newObject);
		refreshButtons();
	}

	/**
	 * Remove um objeto do Grid.
	 *
	 * @param object
	 */
	public void removeObject(ItemSolicitacao object) {
		grid.removeObject(object);
		refreshButtons();
	}

	/**
	 * Remove o objeto selecionado no Grid.
	 */
	public void removeSelectedObject() {
		removeObject(getSelectedObject());
	}

	/**
	 * Move o objeto selecionado no Grid para cima.
	 */
	public void upSelected() {
		grid.upObject(grid.getSelectedRow());
	}

	/**
	 * Move o objeto selecionado no Grid para baixo.
	 */
	public void downSelected() {
		grid.downObject(grid.getSelectedRow());
	}

	/**
	 * Atualiza o objeto selecionado, substituindo pelo objeto informado.
	 *
	 * @param newObject objeto que substituirá o objeto selecionado.
	 */
	public void updateSelecteObject(ItemSolicitacao newObject) {
		grid.updateObject(getSelectedObject(), newObject);
		refreshButtons();
	}

	/**
	 * Executa o popup que permite visualizar o objeto selecionado.
	 */
	protected void view() {
		ItemSolicitacao selected = getSelectedObject();
		selected = clone(selected);
		Dialog window = new ItemSolicitacaoEditWindow<ItemSolicitacao>(
				TM.translate(CoreTranslator.LIST_EDITOR_VIEW_TITLE, getArtigoEntidade(), getNomeEntidade(), selected),
				createForm(selected), DialogButtonType.CLOSE, true, editWindowWidth, editWindowHeight);
		window.open();
	}

	/**
	 * Executa o popup que permite alterar o objeto selecionado.
	 */
	protected void edit() {
		ItemSolicitacao selected = getSelectedObject();
		selected = clone(selected);
		final Dialog window = new ItemSolicitacaoEditWindow<ItemSolicitacao>(
				TM.translate(CoreTranslator.LIST_EDITOR_EDIT_TITLE, getArtigoEntidade(), getNomeEntidade(), selected),
				createForm(selected), confirmButtonType, false, editWindowWidth, editWindowHeight) {
			private static final long serialVersionUID = 1L;

			public void confirm(ItemSolicitacao obj) throws Exception {
				updateObject(obj);
				grid.updateObject(getSelectedObject(), obj);
				refreshButtons();
				notification(TM.translate(CoreTranslator.LIST_EDITOR_UPDATE_FEEDBACK, getArtigoEntidade(),
						getNomeEntidade(), obj));
				updateButton.focus();

			}
		};
		window.open();
	}

	/**
	 * Notificações sobre as ações de edição.
	 *
	 * @param message mensagem a ser notificada.
	 */
	private void notification(String message) {
		Notification.show(message, 5000, Position.BOTTOM_END);
	}

	/**
	 * Executa o popup que permite inserir um novo objeto.
	 */
	protected void insert() {
		insert(createObject(),
				TM.translate(CoreTranslator.LIST_EDITOR_INSERT_TITLE, getArtigoEntidade(), getNomeEntidade()),
				insertButton);
	}

	/**
	 * Executa o popup que permite inserir um novo objeto.
	 *
	 * @param object  objeto a ser inserido.
	 * @param caption título do diálogo a ser aberto.
	 * @param toFocus componente a ser focado após o diálogo de inclusão ser
	 *                fechado.
	 */
	private void insert(ItemSolicitacao object, String caption, final Focusable<?> toFocus) {
		final Dialog window = new ItemSolicitacaoEditWindow<ItemSolicitacao>(caption, createForm(object),
				confirmButtonType, false, editWindowWidth, editWindowHeight) {
			private static final long serialVersionUID = 1L;

			public void confirm(ItemSolicitacao obj) throws Exception {
				if (!objExisteGrid(obj)) {
					insertObject(obj);
					grid.addObject(obj);
					refreshButtons();
					notification(TM.translate(CoreTranslator.LIST_EDITOR_INSERT_FEEDBACK, getArtigoEntidade(),
							getNomeEntidade(), obj));
					toFocus.focus();
				} else {
					NotificationDialog.showError("Ambiente já selecionado. Para alterar a Urgência você deve selecionar o Item já cadastrado e acionar o botão 'Alterar'.");
					return;
				}
			}

			private boolean objExisteGrid(ItemSolicitacao obj) {
				boolean existe = false;
				List<ItemSolicitacao> items = grid.getItems();
				for (ItemSolicitacao itemSolicitacao : items) {
					Ambiente ambienteObj = obj.getAmbiente();
					String nomeClienteObj = ambienteObj.getCliente().getNome();
					String nomeAmbienteObj = ambienteObj.getNome();
					String nmObj = nomeClienteObj + nomeAmbienteObj;
					String nomeClienteItem = itemSolicitacao.getAmbiente().getCliente().getNome();
					String nomeAmbienteItem = itemSolicitacao.getAmbiente().getNome();
					String nmItem = nomeClienteItem + nomeAmbienteItem;
					existe = Objects.equals(nmObj, nmItem);
					break;
				}
				return existe;
			}
		};
		window.open();
	}

	/**
	 * Exclui o objeto selecionado.
	 */
	protected void delete() {
		String msg = TM.translate(CoreTranslator.LIST_EDITOR_DELETE_CONFIRMATION, getArtigoEntidade(),
				getNomeEntidade(), getSelectedObject());
		YesNoDialog.execute(TM.translate(CoreTranslator.LIST_EDITOR_DELETE_TITLE, getArtigoEntidade(),
				getNomeEntidade(), getSelectedObject()), msg, () -> {
					ItemSolicitacao selected = getSelectedObject();
					try {
						deleteObject(selected);
						grid.removeObject(selected);
						refreshButtons();
						notification(TM.translate(CoreTranslator.LIST_EDITOR_DELETE_FEEDBACK, getArtigoEntidade(),
								getNomeEntidade(), selected));
						deleteButton.focus();

					} catch (Exception e) {
						ExceptionHandler.handleErrorException(e);
					}
				});

	}

	/**
	 * Retorna um clone do objeto informado. Assume-se que o objeto clonado estará
	 * profundamente carregado.
	 *
	 * @param object objeto cuja carga profunda será realizada.
	 * @return uma versão completamente carregada do objeto informado. É importante
	 *         produzir uma cópia independente da cópia informada, preservando a
	 *         cópia original.
	 */
	protected ItemSolicitacao clone(ItemSolicitacao object) {
		if (object instanceof Assignable<?>) {
			try {
				ItemSolicitacao clone = (ItemSolicitacao) object.getClass().newInstance();
				clone.assignFrom(object);
				return clone;
			} catch (InstantiationException | IllegalAccessException e) {
				throw new UnsupportedOperationException(
						TM.translate(CoreTranslator.CONSTRUCTOR_WITHOUT_ARGUMENTS_NOT_FOUND, object.getClass()));
			}

		}
		throw new UnsupportedOperationException(
				TM.translate(CoreTranslator.LIST_EDITOR_CLONE_NOT_IMPLEMENTED, object.getClass()));
	}

	/**
	 * Cria o Grid que será apresentado.
	 *
	 * @return o Grid criado.
	 */
	protected abstract ItemSolicitacaoGrid createGrid();

	/**
	 * Cria o formulário capaz de editar o objeto.
	 *
	 * @param object objeto a ser editado pelo formulário.
	 * @return o formulário criado.
	 */
	protected abstract ItemSolicitacaoForm createForm(ItemSolicitacao object);

	/**
	 * Cria nova instância de em um tipo parametrizado da classe.
	 *
	 * @param typeIndex índice do tipo parametrizado cuja instância será gerada.
	 * @return a nova instância criada.
	 * @throws IllegalAccessException se o tipo genérico da classe não possuir um
	 *                                construtor sem parâmetros.
	 * @throws InstantiationException se o tipo genérico não permitir instanciação.
	 */
	private Object newInstance(Integer typeIndex) throws IllegalAccessException, InstantiationException {
		return getType().newInstance();
	}

	/**
	 * @return o tipo genérico da classe.
	 */
	@SuppressWarnings({ "unchecked" })
	protected Class<T> getType() {
		return (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * Cria um novo objeto.
	 *
	 * @return o objeto criado.
	 */
	protected ItemSolicitacao createObject() {
		try {
			return (ItemSolicitacao) newInstance(0);
		} catch (IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Exclui o objeto informado do banco de dados.
	 *
	 * @param obj objeto a ser excluído.
	 * @throws Exception caso ocorra algum problema ao remover o objeto.
	 */
	protected abstract void deleteObject(ItemSolicitacao obj) throws Exception;

	/**
	 * Insere o objeto informado no banco de dados.
	 *
	 * @param obj objeto a ser inserido.
	 * @throws Exception caso ocorra algum problema ao atualizar o objeto.
	 */
	protected abstract void insertObject(ItemSolicitacao obj) throws Exception;

	/**
	 * Atualiza o objeto informado no banco de dados.
	 *
	 * @param obj objeto a ser atualizado.
	 * @throws Exception caso ocorra algum problema ao atualizar o objeto.
	 */
	protected abstract void updateObject(ItemSolicitacao obj) throws Exception;

	/**
	 * @return o nome da entidade que será editada.
	 */
	protected abstract Object getNomeEntidade();

	/**
	 * @return O Nome da Entidade sendo editada, no plural.
	 */
	protected abstract String getNomeEntidadePlural();

	/**
	 * @return o artigo definido singular para a entidade.
	 */
	protected abstract Object getArtigoEntidade();

	/**
	 * @param showButtonCaptions atualiza {@link #showButtonCaptions}.
	 */
	public void setShowButtonCaptions(boolean showButtonCaptions) {
		this.showButtonCaptions = showButtonCaptions;
		adjustButtonCaptions();
	}

	/**
	 * Atualiza os títulos e hints dos botões contidos em {@link #toolbar} em
	 * conformidade com {@link #showButtonCaptions}.
	 */
	private void adjustButtonCaptions() {
		Stream<Component> it = toolbar.getChildren();
		it.forEach(c -> {
			if (c instanceof Button) {
				adjustButtonCaption((Button) c);
			}
		});
	}

	/**
	 * Ajusta o título e hint do botão informado em conformidade com
	 * {@link #showButtonCaptions}.
	 *
	 * @param btn botão que terá as propriedades ajustadas.
	 */
	protected void adjustButtonCaption(Button btn) {
		// TODO: Verificar como ajustar o hint
		if (btn.getText() != null) {
			captions.put(btn, btn.getText());
		}
		if (showButtonCaptions) {
			btn.setText(captions.get(btn));
		} else {
			btn.setText(null);
		}
	}

}
