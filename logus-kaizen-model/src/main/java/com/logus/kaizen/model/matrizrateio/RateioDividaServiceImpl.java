package com.logus.kaizen.model.matrizrateio;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.logus.kaizen.model.centrocusto.CentroCusto;
import com.logus.kaizen.model.matrizrateio.MatrizRateio.TipoMatriz;

/**
 * Implementação de serviços para Rateio de custos.
 *
 * @author Walace Zloccowick Maia
 */
public class RateioDividaServiceImpl
  extends RateioDividaService {

  /**
   * Produz um mapa com centros de custo e percentuais definidos por uma matriz
   * de rateio para um dado mês.
   *
   * @param matriz matriz de rateio para a qual o mapa será produzido.
   * @param mes    mês das despesas que serão rateadas em conformidade com a
   *               matriz.
   * @return o mapa produzido.
   */
  public Map<CentroCusto, BigDecimal> getRateio(MatrizRateio matriz,
                                                int mes) {
    Map<CentroCusto, BigDecimal> result = new HashMap<CentroCusto, BigDecimal>();
    if (matriz.getTipo() == TipoMatriz.ESTATICA) {
      for (ItemRateio item : matriz.getItensRateio()) {
        result.put(item.getCentroCusto(), item.getPercentual());
      }
    } else {
      // TODO: Executar SQL e realizar a carga do mapa.
      return Collections.emptyMap();
    }
    return result;
  }

}
