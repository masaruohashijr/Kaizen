package com.logus.kaizen.view.matrizrateio;

import com.logus.kaizen.model.matrizrateio.ItemRateio;
import com.logus.kaizen.model.matrizrateio.MatrizRateio;
import com.logus.kaizen.model.matrizrateio.MatrizRateio.TipoMatriz;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.core.model.dialog.DialogButtonType;
import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.form.BeanForm;
import com.logus.core.view.form.EnumRadioGroup;
import com.logus.core.view.form.TabSheet;
import com.logus.core.view.list.AbstractListEditor;
import com.logus.core.view.list.BeanGrid;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;

/**
 * {@link BeanForm} para a edição de uma {@link MatrizRateio}.
 *
 * @author Walace Zloccowick Maia
 */
@SuppressWarnings("serial")
public class MatrizRateioForm
  extends BeanForm<MatrizRateio> {

  /**
   * @see MatrizRateio#getItensRateio()
   */
  private AbstractListEditor<ItemRateio> itensEditor;
  /**
   * @see MatrizRateio#getSql()
   */
  private Component sqlTextArea;

  /**
   * TabSheet.
   */
  private TabSheet tabs;

  /**
   * Formulário para edição de matrizes de rateio.
   *
   * @param object objeto a ser alterado.
   */
  public MatrizRateioForm(MatrizRateio object) {
    super(object);
    add(expand(fullSize(createTabSheet())));
    defineVisibilidades(object.getTipo());
  }

  /**
   * Cria o componente {@link #tabs}.
   *
   * @return o componente criado.
   */
  private Component createTabSheet() {
    tabs = new TabSheet();
    tabs.addTab(TM.translate(KaizenTranslator.MATRIZ_RATEIO_PRINCIPAL),
                VaadinIcon.INFO_CIRCLE.create(), createPrincipalPage());
    tabs.addTab(TM.translate(KaizenTranslator.ITEM_RATEIO_PLURAL),
                VaadinIcon.SHARE.create(), createItensEditor());
    tabs.addTab(TM.translate(KaizenTranslator.MATRIZ_RATEIO_SQL_RATEIO),
                VaadinIcon.CODE.create(), fullSize(createSQLTextArea()));
    return tabs;
  }

  /**
   * Cria a {@link Tab} principal.
   *
   * @return o componente criado.
   */
  private Component createPrincipalPage() {
    FormLayout layout = new FormLayout();
    layout.add(focus(fullWidth(createNomeField())));
    layout.add(createTipoRadioGroup());
    return layout;
  }

  /**
   * Cria o componente para seleção do {@link TipoMatriz}.
   *
   * @return o componente criado.
   */
  private Component createTipoRadioGroup() {
    EnumRadioGroup<MatrizRateio.TipoMatriz> rg = createEnumRadioGroup("tipo",
                                                                      MatrizRateio.TipoMatriz.class);
    rg.addValueChangeListener(ev -> {
      defineVisibilidades(ev.getValue());
    });
    return addTopCaption(rg,
                         TM.translate(KaizenTranslator.MATRIZ_RATEIO_TIPO));
  }

  /**
   * Define a habilitação dos componentes conforme o tipo de matriz informado.
   *
   * @param tipoMatriz tipo de matriz que estabelecerá a visibilidade dos
   *                   controles.
   */
  private void defineVisibilidades(TipoMatriz tipoMatriz) {
    tabs.getTab(itensEditor).setEnabled(tipoMatriz == TipoMatriz.ESTATICA);
    tabs.getTab(sqlTextArea).setEnabled(tipoMatriz == TipoMatriz.DINAMICA);
  }

  /**
   * Cria o campo para edição do nome.
   *
   * @return o componente criado.
   */
  private Component createNomeField() {
    return createTextField(TM.translate(KaizenTranslator.NOME), "nome");
  }

  /**
   * Cria o campo para edição da expressão SQL em matrizes dinâmicas.
   *
   * @return o componente criado.
   */
  private Component createSQLTextArea() {
    return required(sqlTextArea = createTextArea(TM
        .translate(KaizenTranslator.MATRIZ_RATEIO_SQL_RATEIO), "sql"));
  }

  /**
   * Cria o componente para a edição da lista de itens de rateio.
   *
   * @return o componente criado.
   */
  private Component createItensEditor() {
    itensEditor = new AbstractListEditor<ItemRateio>(DialogButtonType.OK) {

      @Override
      protected BeanGrid<ItemRateio> createGrid() {
        return new ItemRateioGrid();
      }

      @Override
      protected BeanForm<ItemRateio> createForm(ItemRateio object) {
        object.setMatrizRateio(getObject());
        return new ItemRateioForm(object);
      }

      @Override
      protected void deleteObject(ItemRateio obj)
        throws Exception {
        getObject().getItensRateio().remove(obj);
      }

      @Override
      protected void insertObject(ItemRateio obj)
        throws Exception {
        getObject().getItensRateio().add(obj);
      }

      @Override
      protected void updateObject(ItemRateio obj)
        throws Exception {
        // O ItemSolicitacaoListEditor já atualizou o objeto no Grid, que é a mesma
        // instância do item do objeto alterado.
      }

      @Override
      protected Object getNomeEntidade() {
        return TM.translate(KaizenTranslator.ITEM_RATEIO);
      }

      @Override
      protected Object getArtigoEntidade() {
        return TM.translate(CoreTranslator.ART_MAS_SING);
      }

      @Override
      protected String getNomeEntidadePlural() {
        return null; // sem título
      }

    };
    itensEditor.setEditWindowWidth(500);
    itensEditor.updateObjects(getObject().getItensRateio());
    return itensEditor;
  }

}
