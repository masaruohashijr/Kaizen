package com.logus.kaizen.model.resolucao;

import com.logus.core.model.aut.Funcionalidade;
/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class FuncionalidadeResolucao extends Funcionalidade {

	/**
	 * Cadastro de resolução.
	 */
	public static final FuncionalidadeResolucao FUNC_RESOLUCAO_CADASTRAR = create("FUNC_RESOLUCAO_CADASTRAR","Cadastrar Resolução");
	/**
	 * Consulta de resoluções.
	 */
	public static final FuncionalidadeResolucao FUNC_RESOLUCAO_CONSULTAR = create("FUNC_RESOLUCAO_CONSULTAR","Consultar Resoluções");

	/**
	 * Cria uma nova funcionalidade.
	 *
	 * @param id   identificador da nova funcionalidade.
	 * @param name nome da nova funcionalidade.
	 * @return a funcionalidade criada.
	 */
	private static FuncionalidadeResolucao create(String id, String name) {
		FuncionalidadeResolucao result = new FuncionalidadeResolucao(id, name);
		addToMap(id, result);
		return result;
	}

	/**
	   * Construtor
	   *
	   * @param id   identificador da funcionalidade.
	   * @param nome nome da funcionalidade.
	   */
	  protected FuncionalidadeResolucao(String id, String nome) {
	    super(id, nome);
	  }

}