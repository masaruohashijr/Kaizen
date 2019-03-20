/**
 *
 */
package com.logus.kaizen.model.apoio.usuario;

import java.util.Collection;

import com.logus.core.model.aut.login.LoginManager;
import com.logus.core.model.persistence.jpa.AbstractJpaDao;
import com.logus.kaizen.model.TableNames;
import com.logus.kaizen.model.apoio.UsuarioDataServiceImpl;
/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class UsuarioJpaDaoImpl
extends AbstractJpaDao<Usuario>
implements UsuarioDao {

  /**
   * @param emf
   */
  public UsuarioJpaDaoImpl() {
    super(UsuarioDataServiceImpl.getEMF());
  }

  private static UsuarioJpaDaoImpl instance;

  public static UsuarioJpaDaoImpl getInstance() {
    if(instance == null) {
      instance = new UsuarioJpaDaoImpl();
    }
    return instance;
  }

  @Override
  public Collection<Usuario> loadUsuarios() {
    return loadCollection("cod_usuario, nom_usuario",TableNames.TB_USUARIO, "", "nom_usuario", Boolean.TRUE);
  }

  /* (non-Javadoc)
   * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#assignTo(java.lang.Object, java.lang.Object)
   */
  @Override
  public void assignTo(Usuario source, Usuario dest) {
    dest.assignFrom(source);
  }

  /* (non-Javadoc)
   * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#getTableName()
   */
  @Override
  public String getTableName() {
    return TableNames.TB_USUARIO;
  }

  /* (non-Javadoc)
   * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#updateEnabled()
   */
  @Override
  protected boolean updateEnabled() {
    return LoginManager.getAccessControl().hasAccess(FuncionalidadeUsuario.FUNC_USUARIO_CADASTRAR.getId());
  }

  /* (non-Javadoc)
   * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#queryEnabled()
   */
  @Override
  protected boolean queryEnabled() {
    return LoginManager.getAccessControl().hasAccess(FuncionalidadeUsuario.FUNC_USUARIO_CONSULTAR.getId());
  }


}
