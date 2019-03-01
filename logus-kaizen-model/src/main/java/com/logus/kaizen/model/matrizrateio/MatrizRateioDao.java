package com.logus.kaizen.model.matrizrateio;

import java.util.Collection;

import com.logus.kaizen.model.matrizrateio.MatrizRateio.TipoMatriz;
import com.logus.core.model.persistence.DataAccessObject;

/**
 * Serviços de persistência para {@link MatrizRateio}.
 *
 * @author Walace Zloccowick Maia
 */
public interface MatrizRateioDao
  extends DataAccessObject<MatrizRateio> {

  /**
   * Carrega as matrizes de rateio de um dado tipo.
   *
   * @param tipo Tipo das matrizes de rateio que serão carregadas.
   * @return a coleção contendo as matrizes do tipo informado.
   */
  public Collection<MatrizRateio> loadByTipo(TipoMatriz tipo);

}
