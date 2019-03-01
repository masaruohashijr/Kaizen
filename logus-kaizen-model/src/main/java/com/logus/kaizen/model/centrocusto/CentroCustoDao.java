package com.logus.kaizen.model.centrocusto;

import java.util.Collection;

import com.logus.core.model.persistence.DataAccessObject;

/**
 * Serviços de persistência para um {@link CentroCusto}.
 *
 * @author Walace Zloccowick Maia
 */
public interface CentroCustoDao
  extends DataAccessObject<CentroCusto> {

  /**
   * @return os centros de custo ativos.
   */
  Collection<CentroCusto> loadAtivos();

}
