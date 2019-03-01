package com.logus.kaizen.model.matrizrateio;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.logus.kaizen.model.centrocusto.CentroCusto;
import com.logus.kaizen.model.centrocusto.CustosDataServiceImpl;
import com.logus.kaizen.model.centrocusto.FuncionalidadeCustos;
import com.logus.kaizen.model.matrizrateio.MatrizRateio.TipoMatriz;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.core.model.aut.login.LoginManager;
import com.logus.core.model.persistence.PersistenceException;
import com.logus.core.model.persistence.jpa.AbstractJpaDao;
import com.logus.core.model.translation.TM;
import com.logus.core.model.util.Strings;

/**
 * Implementação JPA para MatrizRateioDao.
 *
 * @author Walace Zloccowick Maia
 */
public class MatrizRateioJpaDaoImpl
  extends AbstractJpaDao<MatrizRateio>
  implements MatrizRateioDao {

  /**
   * Singleton.
   */
  private static MatrizRateioJpaDaoImpl instance;

  /**
   * @return the instance
   */
  public static MatrizRateioJpaDaoImpl getInstance() {
    if (instance == null) {
      instance = new MatrizRateioJpaDaoImpl();
    }
    return instance;
  }

  /**
   * Construtor
   */
  private MatrizRateioJpaDaoImpl() {
    super(CustosDataServiceImpl.getEMF());
  }

  @Override
  public void assignTo(MatrizRateio source, MatrizRateio dest) {
    dest.assignFrom(source);
  }

  @Override
  public String getTableName() {
    return MatrizRateio.TABLE_NAME;
  }

  @Override
  public void validate(MatrizRateio object, PersistenceException ex) {
    if (object.getTipo() == TipoMatriz.ESTATICA) {
      validateMatrizEstatica(object, ex);
    } else {
      validateMatrizDinamica(object, ex);
    }
  }

  /**
   * Matrizes estáticas devem possuir uma lista não vazia de {@link ItemRateio}.
   *
   * @param object objeto a ser validado
   * @param ex     {@link PersistenceException} que receberá erros caso a
   *               validação falhe.
   */
  public void validateMatrizEstatica(MatrizRateio object,
                                     PersistenceException ex) {
    if (object.getItensRateio().isEmpty()) {
      ex.addError(TM
          .translate(KaizenTranslator.MATRIZ_RATEIO_PELO_MENOS_UM_ITEM));
    }
    validateUnicidadeCentrosCusto(object, ex);
    validateSomaPercentuais(object, ex);

  }

  /**
   * Matrizes dinâmicas devem possuir uma cláusula SQL válida para definição dos
   * itens de rateio.
   *
   * @param object matriz a ser validada.
   * @param ex     {@link PersistenceException} que receberá erros caso a
   *               validação falhe.
   */
  private void validateMatrizDinamica(MatrizRateio object,
                                      PersistenceException ex) {
    if (Strings.isEmpty(object.getSql())) {
      ex.addError(TM
          .translate(KaizenTranslator.MATRIZ_RATEIO_SQL_OBRIGATORIO));
    }
  }

  /**
   * Um centro de custo não pode ocorrer em dois itens da mesma matriz de
   * rateio.
   *
   * @param object matriz cujos itens serão analisados.
   * @param ex     {@link PersistenceException} que receberá erros caso a
   *               validação falhe.
   */
  private void validateUnicidadeCentrosCusto(MatrizRateio object,
                                             PersistenceException ex) {
    Set<CentroCusto> centrosCusto = new HashSet<CentroCusto>();
    for (ItemRateio item : object.getItensRateio()) {
      if (centrosCusto.contains(item.getCentroCusto())) {
        ex.addError(TM
            .translate(KaizenTranslator.MATRIZ_RATEIO_ITENS_REPETIDOS,
                       item.getCentroCusto().getNome()));
      }
      centrosCusto.add(item.getCentroCusto());
    }
  }

  /**
   * A soma dos percentuais de uma matriz de rateio deve totalizar 100.
   *
   * @param object matriz cujos itens serão analisados.
   * @param ex     {@link PersistenceException} que receberá erros caso a
   *               validação falhe.
   */
  private void validateSomaPercentuais(MatrizRateio object,
                                       PersistenceException ex) {
    BigDecimal total = BigDecimal.ZERO;
    for (ItemRateio item : object.getItensRateio()) {
      total = total.add(item.getPercentual());
    }
    if (BigDecimal.valueOf(100).compareTo(total) != 0) {
      ex.addError(TM
          .translate(KaizenTranslator.MATRIZ_RATEIO_TOTALIZAR_100));
    }
  }

  @Override
  public Collection<MatrizRateio> loadByTipo(TipoMatriz tipo) {
    return loadCollectionByFilter("tip_matriz = ?", tipo.name());
  }

  @Override
  protected boolean queryEnabled() {
    return LoginManager.getAccessControl()
        .hasAccess(FuncionalidadeCustos.FUNC_MATRIZ_RATEIO_CONSULTAR
            .getId());
  }

  @Override
  protected boolean updateEnabled() {
    return LoginManager.getAccessControl()
        .hasAccess(FuncionalidadeCustos.FUNC_MATRIZ_RATEIO_CADASTRAR
            .getId());
  }

}
