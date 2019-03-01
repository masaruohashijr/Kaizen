package com.logus.kaizen.model.matrizrateio;

import java.math.BigDecimal;
import java.util.Map;

import com.logus.kaizen.model.centrocusto.CentroCusto;

/**
 * Serviços para rateio de custos.
 *
 * @author Walace Zloccowick Maia
 */
public abstract class RateioDividaService {

  /**
   * Singleton.
   */
  private static RateioDividaService instance;

  /**
   * @return a instância concreta da fábrica de DAO's.
   */
  public static RateioDividaService get() {
    if (instance == null) {
      instance = new RateioDividaServiceImpl();
    }
    return instance;
  }

  /**
   * Produz um mapa com centros de custo e percentuais definidos por uma matriz
   * de rateio para um dado mês.
   *
   * @param matriz matriz de rateio para a qual o mapa será produzido.
   * @param mes    mês das despesas que serão rateadas em conformidade com a
   *               matriz.
   * @return o mapa produzido.
   */
  public  abstract Map<CentroCusto, BigDecimal> getRateio(MatrizRateio matriz,
                                                       int mes);


}
