/**
 *
 */
package com.logus.kaizen.model.apoio.urgencia;

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
public class UrgenciaJpaDaoImpl
extends AbstractJpaDao<Urgencia>
implements UrgenciaDao {

  /**
   * @param emf
   */
  public UrgenciaJpaDaoImpl() {
    super(ApoioDataServiceImpl.getEMF());
  }

  private static UrgenciaJpaDaoImpl instance;

  public static UrgenciaJpaDaoImpl getInstance() {
    if(instance == null) {
      instance = new UrgenciaJpaDaoImpl();
    }
    return instance;
  }

  @Override
  public Collection<Urgencia> loadUrgencias() {
    return loadCollection("seq_urgencia, nom_urgencia, flg_ativo",TableNames.TB_URGENCIA, "flg_ativo = ?", "nom_urgencia", Boolean.TRUE);
  }

  /* (non-Javadoc)
   * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#assignTo(java.lang.Object, java.lang.Object)
   */
  @Override
  public void assignTo(Urgencia source, Urgencia dest) {
    dest.assignFrom(source);
  }

  /* (non-Javadoc)
   * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#getTableName()
   */
  @Override
  public String getTableName() {
    return TableNames.TB_URGENCIA;
  }

  /* (non-Javadoc)
   * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#updateEnabled()
   */
  @Override
  protected boolean updateEnabled() {
    return LoginManager.getAccessControl().hasAccess(FuncionalidadeUrgencia.FUNC_URGENCIA_CADASTRAR.getId());
  }

  /* (non-Javadoc)
   * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#queryEnabled()
   */
  @Override
  protected boolean queryEnabled() {
    return LoginManager.getAccessControl().hasAccess(FuncionalidadeUrgencia.FUNC_URGENCIA_CONSULTAR.getId());
  }


}
