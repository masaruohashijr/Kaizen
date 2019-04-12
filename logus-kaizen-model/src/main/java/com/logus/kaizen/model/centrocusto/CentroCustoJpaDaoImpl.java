package com.logus.kaizen.model.centrocusto;

import java.util.Collection;

import com.logus.core.model.aut.login.LoginManager;
import com.logus.core.model.persistence.jpa.AbstractJpaDao;

/**
 * Implementação JPA para um {@link CentroCustoDao}.
 *
 * @author Walace Zloccowick Maia
 */
public class CentroCustoJpaDaoImpl
  extends AbstractJpaDao<CentroCusto>
  implements CentroCustoDao {


  /**
   * Singleton.
   */
  private static CentroCustoJpaDaoImpl instance;

  /**
   * @return {@link #instance}.
   */
  public static CentroCustoJpaDaoImpl getInstance() {
    if (instance == null) {
      instance = new CentroCustoJpaDaoImpl();
    }
    return instance;
  }

  /**
   * Construtor.
   */
  public CentroCustoJpaDaoImpl() {
    super(CustosDataServiceImpl.getEMF());
  }

  @Override
  public void assignTo(CentroCusto source, CentroCusto dest) {
    dest.assignFrom(source);
  }

  @Override
  public String getTableName() {
    return CentroCusto.TABLE_NAME;
  }

  @Override
  protected boolean queryEnabled() {
    return LoginManager.getAccessControl()
        .hasAccess(FuncionalidadeCustos.FUNC_CENTRO_CUSTO_CONSULTAR
            .getId());
  }

  @Override
  protected boolean updateEnabled() {
    return LoginManager.getAccessControl()
        .hasAccess(FuncionalidadeCustos.FUNC_CENTRO_CUSTO_CADASTRAR
            .getId());
  }

  @Override
  public Collection<CentroCusto> loadAtivos() {
    return loadCollectionByFilter("flg_ativo = ?", Boolean.TRUE);
  }

}
