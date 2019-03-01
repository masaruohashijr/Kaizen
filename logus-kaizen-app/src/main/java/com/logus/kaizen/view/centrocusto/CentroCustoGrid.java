package com.logus.kaizen.view.centrocusto;

import com.logus.kaizen.model.centrocusto.CentroCusto;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.list.BeanGrid;

/**
 * Grid para visualização de {@link CentroCusto}.
 *
 * @author Walace Zloccowick Maia
 */
@SuppressWarnings("serial")
public class CentroCustoGrid
  extends BeanGrid<CentroCusto> {

  /**
   * Construtor.
   */
  public CentroCustoGrid() {
    super();
    addColumn(CentroCusto::getId).setFlexGrow(0)
        .setHeader(TM.translate(KaizenTranslator.CODIGO));
    addColumn(CentroCusto::getNome).setFlexGrow(1)
        .setHeader(TM.translate(KaizenTranslator.NOME));
    addColumn(c -> TM
        .translate(c.isAtivo() ? CoreTranslator.YES : CoreTranslator.NO))
            .setHeader(TM.translate(KaizenTranslator.ATIVO))
            .setFlexGrow(0);
  }

}
