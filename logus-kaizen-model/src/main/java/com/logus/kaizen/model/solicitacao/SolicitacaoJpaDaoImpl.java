/**
 *
 */
package com.logus.kaizen.model.solicitacao;

import java.util.Collection;

import com.logus.kaizen.model.apoio.ApoioDataServiceImpl;
import com.logus.core.model.aut.login.LoginManager;
import com.logus.core.model.persistence.jpa.AbstractJpaDao;
/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class SolicitacaoJpaDaoImpl
extends AbstractJpaDao<Solicitacao>
implements SolicitacaoDao {

  /**
   * @param emf
   */
  public SolicitacaoJpaDaoImpl() {
    super(ApoioDataServiceImpl.getEMF());
  }

  private static SolicitacaoJpaDaoImpl instance;

  public static SolicitacaoJpaDaoImpl getInstance() {
    if(instance == null) {
      instance = new SolicitacaoJpaDaoImpl();
    }
    return instance;
  }

  @Override
  public Collection<Solicitacao> loadSolicitacoes() {
    return loadCollectionByFilter("flg_ativo = ?", Boolean.TRUE);
  }

  /* (non-Javadoc)
   * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#assignTo(java.lang.Object, java.lang.Object)
   */
  @Override
  public void assignTo(Solicitacao source, Solicitacao dest) {
    dest.assignFrom(source);
  }

  /* (non-Javadoc)
   * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#getTableName()
   */
  @Override
  public String getTableName() {
    return Solicitacao.TABLE_SOLICITACAO;
  }

  /* (non-Javadoc)
   * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#updateEnabled()
   */
  @Override
  protected boolean updateEnabled() {
    return LoginManager.getAccessControl().hasAccess(FuncionalidadeSolicitacao.FUNC_SOLICITACAO_CADASTRAR.getId());
  }

  /* (non-Javadoc)
   * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#queryEnabled()
   */
  @Override
  protected boolean queryEnabled() {
    return LoginManager.getAccessControl().hasAccess(FuncionalidadeSolicitacao.FUNC_SOLICITACAO_CONSULTAR.getId());
  }


}
