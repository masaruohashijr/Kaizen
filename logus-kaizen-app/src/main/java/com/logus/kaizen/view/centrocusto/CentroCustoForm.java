package com.logus.kaizen.view.centrocusto;

import com.logus.kaizen.model.centrocusto.CentroCusto;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.form.BeanForm;
import com.vaadin.flow.component.Component;

/**
 * {@link BeanForm} para edição de um {@link CentroCusto}.
 *
 * @author Walace Zloccowick Maia
 */
@SuppressWarnings("serial")
public class CentroCustoForm
  extends BeanForm<CentroCusto> {

  /**
   * Construtor.
   *
   * @param object objeto a ser editado por este formulário.
   */
  public CentroCustoForm(CentroCusto object) {
    super(object);
    add(focus(fullWidth(createNomeEdit())));
    add(createAtivoEdit());
  }

  /**
   * Cria o componente para edição do nome do centro de custo.
   *
   * @return o componente criado.
   */
  private Component createNomeEdit() {
    return createTextField(TM.translate(KaizenTranslator.NOME), "nome");
  }

  /**
   * Cria o componente para edição da propriedade
   *
   * @return o componente criado.
   */
  private Component createAtivoEdit() {
    return createCheckBoxField(TM
        .translate(KaizenTranslator.ATIVO), "ativo");

  }

}
