/**
 *
 */
package com.logus.kaizen.model.apoio;

import com.logus.kaizen.model.apoio.usuario.UsuarioDao;
/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public abstract class UsuarioDataService {

  /**
   * Singleton.
   */
  private static UsuarioDataService instance;

  /**
   * @return a instância concreta da fábrica de DAO's.
   */
  public static UsuarioDataService get() {
    if (instance == null) {
      instance = new UsuarioDataServiceImpl();
    }
    return instance;
  }

  public abstract UsuarioDao getUsuarioDao();

}
