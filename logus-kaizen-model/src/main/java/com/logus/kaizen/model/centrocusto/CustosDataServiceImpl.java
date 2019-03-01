package com.logus.kaizen.model.centrocusto;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.logus.kaizen.model.matrizrateio.MatrizRateioDao;
import com.logus.kaizen.model.matrizrateio.MatrizRateioJpaDaoImpl;

/**
 * Implementação dos serviços de persistência para o módulo de Custos.
 *
 * @author Walace Zloccowick Maia
 */
public class CustosDataServiceImpl
  extends CustosDataService {

  /**
   * Nome da persistence-unit JPA.
   */
  private static final String PERSISTENCE_UNIT_NAME = "Kaizen_Persistence_Unit";

  /**
   * Singleton para factory de gerência de Entidades.
   */
  private static EntityManagerFactory emf;

  /**
   * @return {@link #emf}.
   */
  public static EntityManagerFactory getEMF() {
    if (emf == null) {
      emf = (EntityManagerFactory) Persistence
          .createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }
    return emf;
  }

  @Override
  public CentroCustoDao getCentroCustoDao() {
    return CentroCustoJpaDaoImpl.getInstance();
  }

  @Override
  public MatrizRateioDao getMatrizRateioDao() {
    return MatrizRateioJpaDaoImpl.getInstance();
  }

}
