/**
 *
 */
package com.logus.kaizen.model.apoio.produto;

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
public class ProdutoJpaDaoImpl
extends AbstractJpaDao<Produto>
implements ProdutoDao {

  /**
   * @param emf
   */
  public ProdutoJpaDaoImpl() {
    super(ApoioDataServiceImpl.getEMF());
  }

  private static ProdutoJpaDaoImpl instance;

  public static ProdutoJpaDaoImpl getInstance() {
    if(instance == null) {
      instance = new ProdutoJpaDaoImpl();
    }
    return instance;
  }

  @Override
  public Collection<Produto> loadProdutos() {
	  return loadCollection("seq_produto, nom_produto, flg_ativo",Produto.TABLE_NAME, "flg_ativo = ?", "nom_produto", Boolean.TRUE);
  }

  /* (non-Javadoc)
   * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#assignTo(java.lang.Object, java.lang.Object)
   */
  @Override
  public void assignTo(Produto source, Produto dest) {
    dest.assignFrom(source);
  }

  /* (non-Javadoc)
   * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#getTableName()
   */
  @Override
  public String getTableName() {
    return Produto.TABLE_NAME;
  }

  /* (non-Javadoc)
   * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#updateEnabled()
   */
  @Override
  protected boolean updateEnabled() {
    return LoginManager.getAccessControl().hasAccess(FuncionalidadeProduto.FUNC_PRODUTO_CADASTRAR.getId());
  }

  /* (non-Javadoc)
   * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#queryEnabled()
   */
  @Override
  protected boolean queryEnabled() {
    return LoginManager.getAccessControl().hasAccess(FuncionalidadeProduto.FUNC_PRODUTO_CONSULTAR.getId());
  }


}
