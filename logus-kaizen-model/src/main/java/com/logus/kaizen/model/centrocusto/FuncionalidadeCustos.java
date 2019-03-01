package com.logus.kaizen.model.centrocusto;

import com.logus.core.model.aut.Funcionalidade;

/**
 * Funcionalidades disponibilizadas pelo módulo de Custos.
 *
 * @author Walace Zloccowick Maia
 */
public class FuncionalidadeCustos
  extends Funcionalidade {

  /**
   * Cadastro de centros de custo.
   */
  public static final FuncionalidadeCustos FUNC_CENTRO_CUSTO_CADASTRAR = create("FUNC_CENTRO_CUSTO_CADASTRAR",
                                                                                "Cadastrar centros de custo");
  /**
   * Consulta de centros de custo.
   */
  public static final FuncionalidadeCustos FUNC_CENTRO_CUSTO_CONSULTAR = create("FUNC_CENTRO_CUSTO_CONSULTAR",
                                                                                "Consultar centros de custo");

  /**
   * Cadastro de matrizes de rateio.
   */
  public static final FuncionalidadeCustos FUNC_MATRIZ_RATEIO_CADASTRAR = create("FUNC_MATRIZ_RATEIO_CADASTRAR",
                                                                                 "Cadastrar matrizes de rateio");

  /**
   * Consulta de matrizes de rateio.
   */
  public static final FuncionalidadeCustos FUNC_MATRIZ_RATEIO_CONSULTAR = create("FUNC_MATRIZ_RATEIO_CONSULTAR",
                                                                                 "Consultar matrizes de rateio");

  /**
   * Cria uma nova funcionalidade.
   *
   * @param id   identificador da nova funcionalidade.
   * @param name nome da nova funcionalidade.
   * @return a funcionalidade criada.
   */
  private static FuncionalidadeCustos create(String id, String name) {
    FuncionalidadeCustos result = new FuncionalidadeCustos(id, name);
    addToMap(id, result);
    return result;
  }

  /**
   * Construtor
   *
   * @param id   identificador da funcionalidade.
   * @param nome nome da funcionalidade.
   */
  protected FuncionalidadeCustos(String id, String nome) {
    super(id, nome);
  }

}
