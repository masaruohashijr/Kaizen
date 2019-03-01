package com.logus.kaizen.model.centrocusto;

import com.logus.kaizen.model.matrizrateio.MatrizRateioDao;

/**
 * Ponto de partida para a criação de uma fábrica de DAO's para o módulo de
 * Custos.
 *
 * @author Walace Zloccowick Maia
 */
public abstract class CustosDataService {

  /**
   * Singleton.
   */
  private static CustosDataService instance;

  /**
   * @return a instância concreta da fábrica de DAO's.
   */
  public static CustosDataService get() {
    if (instance == null) {
      instance = new CustosDataServiceImpl();
    }
    return instance;
  }

  /**
   * @return uma instância de um {@link CentroCustoDao}.
   */
  public abstract CentroCustoDao getCentroCustoDao();

  /**
   * @return uma instância de um {@link MatrizRateioDao}.
   */
  public abstract MatrizRateioDao getMatrizRateioDao();

}
