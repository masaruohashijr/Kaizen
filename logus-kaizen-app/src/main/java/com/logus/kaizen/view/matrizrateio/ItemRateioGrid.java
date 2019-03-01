package com.logus.kaizen.view.matrizrateio;

import com.logus.kaizen.model.matrizrateio.ItemRateio;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.model.util.Formats;
import com.logus.core.view.list.BeanGrid;

/**
 * {@link BeanGrid} para {@link ItemRateio}.
 *
 * @author Walace Zloccowick Maia
 */
@SuppressWarnings("serial")
public class ItemRateioGrid
  extends BeanGrid<ItemRateio> {

  /**
   * Construtor.
   */
  public ItemRateioGrid() {
    super();
    addColumn(ItemRateio::getCentroCusto).setFlexGrow(1)
        .setHeader(TM
            .translate(KaizenTranslator.CENTRO_CUSTO));
    addColumn(itemRateio -> Formats.getDecimalFormat(2)
        .format(itemRateio.getPercentual())).setFlexGrow(0)
            .setHeader(TM
                .translate(KaizenTranslator.ITEM_RATEIO_PERCENTUAL));
  }

}
