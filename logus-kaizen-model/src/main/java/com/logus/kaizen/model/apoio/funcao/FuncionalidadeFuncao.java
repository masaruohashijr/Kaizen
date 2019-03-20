package com.logus.kaizen.model.apoio.funcao;

import com.logus.core.model.aut.Funcionalidade;
/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class FuncionalidadeFuncao extends Funcionalidade {

	/**
	 * Cadastro de funções.
	 */
	public static final FuncionalidadeFuncao FUNC_FUNCAO_CADASTRAR = create("FUNC_FUNCAO_CADASTRAR","Cadastrar Funcao");
	/**
	 * Consulta de funções.
	 */
	public static final FuncionalidadeFuncao FUNC_FUNCAO_CONSULTAR = create("FUNC_FUNCAO_CONSULTAR","Consultar Funcao");

	/**
	 * Cria uma nova funcionalidade.
	 *
	 * @param id   identificador da nova funcionalidade.
	 * @param name nome da nova funcionalidade.
	 * @return a funcionalidade criada.
	 */
	private static FuncionalidadeFuncao create(String id, String name) {
		FuncionalidadeFuncao result = new FuncionalidadeFuncao(id, name);
		addToMap(id, result);
		return result;
	}

	/**
	   * Construtor
	   *
	   * @param id   identificador da funcionalidade.
	   * @param nome nome da funcionalidade.
	   */
	  protected FuncionalidadeFuncao(String id, String nome) {
	    super(id, nome);
	  }

}