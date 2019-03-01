package com.logus.kaizen.model.apoio.produto;

import com.logus.core.model.aut.Funcionalidade;
/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class FuncionalidadeProduto extends Funcionalidade {

	/**
	 * Cadastro de produtos.
	 */
	public static final FuncionalidadeProduto FUNC_PRODUTO_CADASTRAR = create("FUNC_PRODUTO_CADASTRAR","Cadastrar Produto");
	/**
	 * Consulta de produtos.
	 */
	public static final FuncionalidadeProduto FUNC_PRODUTO_CONSULTAR = create("FUNC_PRODUTO_CONSULTAR","Consultar Produto");

	/**
	 * Cria uma nova funcionalidade.
	 *
	 * @param id   identificador da nova funcionalidade.
	 * @param name nome da nova funcionalidade.
	 * @return a funcionalidade criada.
	 */
	private static FuncionalidadeProduto create(String id, String name) {
		FuncionalidadeProduto result = new FuncionalidadeProduto(id, name);
		addToMap(id, result);
		return result;
	}

	/**
	   * Construtor
	   *
	   * @param id   identificador da funcionalidade.
	   * @param nome nome da funcionalidade.
	   */
	  protected FuncionalidadeProduto(String id, String nome) {
	    super(id, nome);
	  }

}