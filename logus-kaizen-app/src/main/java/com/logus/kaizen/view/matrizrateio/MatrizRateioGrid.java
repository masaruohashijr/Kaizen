package com.logus.kaizen.view.matrizrateio;

import com.logus.kaizen.model.matrizrateio.MatrizRateio;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.list.BeanGrid;

/**
 * {@link BeanGrid} para {@link MatrizRateio}.
 *
 * @author Walace Zloccowick Maia
 */
@SuppressWarnings("serial")
public class MatrizRateioGrid
  extends BeanGrid<MatrizRateio> {

  /**
   * Construtor.
   */
  public MatrizRateioGrid() {
    super();
    addColumn(MatrizRateio::getId).setFlexGrow(0)
        .setHeader(TM.translate(KaizenTranslator.CODIGO));
    addColumn(MatrizRateio::getNome).setFlexGrow(1)
        .setHeader(TM.translate(KaizenTranslator.NOME));
  }

}
