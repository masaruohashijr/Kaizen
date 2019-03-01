package com.logus.kaizen.model.apoio.cliente;

import com.logus.core.model.aut.Funcionalidade;
/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class FuncionalidadeCliente extends Funcionalidade {

	/**
	 * Cadastro de clientes.
	 */
	public static final FuncionalidadeCliente FUNC_CLIENTE_CADASTRAR = create("FUNC_CLIENTE_CADASTRAR","Cadastrar Cliente");
	/**
	 * Consulta de clientes.
	 */
	public static final FuncionalidadeCliente FUNC_CLIENTE_CONSULTAR = create("FUNC_CLIENTE_CONSULTAR","Consultar Cliente");

	/**
	 * Cria uma nova funcionalidade.
	 *
	 * @param id   identificador da nova funcionalidade.
	 * @param name nome da nova funcionalidade.
	 * @return a funcionalidade criada.
	 */
	private static FuncionalidadeCliente create(String id, String name) {
		FuncionalidadeCliente result = new FuncionalidadeCliente(id, name);
		addToMap(id, result);
		return result;
	}

	/**
	   * Construtor
	   *
	   * @param id   identificador da funcionalidade.
	   * @param nome nome da funcionalidade.
	   */
	  protected FuncionalidadeCliente(String id, String nome) {
	    super(id, nome);
	  }

}