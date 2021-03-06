/**
 *
 */
package com.logus.kaizen.model.kotae.plano;

import java.util.Collection;

import com.logus.core.model.aut.login.LoginManager;
import com.logus.core.model.persistence.jpa.AbstractJpaDao;
import com.logus.kaizen.model.TableNames;
import com.logus.kaizen.model.apoio.ApoioDataServiceImpl;
/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class PlanoJpaDaoImpl
extends AbstractJpaDao<Plano>
implements PlanoDao {

  /**
   * @param emf
   */
  public PlanoJpaDaoImpl() {
    super(ApoioDataServiceImpl.getEMF());
  }

  private static PlanoJpaDaoImpl instance;

  public static PlanoJpaDaoImpl getInstance() {
    if(instance == null) {
      instance = new PlanoJpaDaoImpl();
    }
    return instance;
  }

  @Override
  public Collection<Plano> loadPlanos() {
    return loadCollection("*", TableNames.TB_PLANO, "flg_ativo = ?", "seq_plano desc", Boolean.TRUE);
  }

  /* (non-Javadoc)
   * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#assignTo(java.lang.Object, java.lang.Object)
   */
  @Override
  public void assignTo(Plano source, Plano dest) {
    dest.assignFrom(source);
  }

  /* (non-Javadoc)
   * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#getTableName()
   */
  @Override
  public String getTableName() {
    return TableNames.TB_PLANO;
  }

  /* (non-Javadoc)
   * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#updateEnabled()
   */
  @Override
  protected boolean updateEnabled() {
    return LoginManager.getAccessControl().hasAccess(FuncionalidadePlano.FUNC_PLANO_CADASTRAR.getId());
  }

  /* (non-Javadoc)
   * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#queryEnabled()
   */
  @Override
  protected boolean queryEnabled() {
    return LoginManager.getAccessControl().hasAccess(FuncionalidadePlano.FUNC_PLANO_CONSULTAR.getId());
  }


}
