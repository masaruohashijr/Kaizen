package com.logus.kaizen.model.apoio.transicao;

import com.logus.core.model.aut.Funcionalidade;
/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class FuncionalidadeTransicao extends Funcionalidade {

	/**
	 * Cadastro de transição.
	 */
	public static final FuncionalidadeTransicao FUNC_TRANSICAO_CADASTRAR = create("FUNC_TRANSICAO_CADASTRAR","Cadastrar Transição");
	/**
	 * Consulta de transições.
	 */
	public static final FuncionalidadeTransicao FUNC_TRANSICAO_CONSULTAR = create("FUNC_TRANSICAO_CONSULTAR","Consultar Transições");

	/**
	 * Cria uma nova funcionalidade.
	 *
	 * @param id   identificador da nova funcionalidade.
	 * @param name nome da nova funcionalidade.
	 * @return a funcionalidade criada.
	 */
	private static FuncionalidadeTransicao create(String id, String name) {
		FuncionalidadeTransicao result = new FuncionalidadeTransicao(id, name);
		addToMap(id, result);
		return result;
	}

	/**
	   * Construtor
	   *
	   * @param id   identificador da funcionalidade.
	   * @param nome nome da funcionalidade.
	   */
	  protected FuncionalidadeTransicao(String id, String nome) {
	    super(id, nome);
	  }

}