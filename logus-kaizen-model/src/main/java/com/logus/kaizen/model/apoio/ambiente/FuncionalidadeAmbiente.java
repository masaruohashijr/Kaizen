package com.logus.kaizen.model.apoio.ambiente;

import com.logus.core.model.aut.Funcionalidade;
/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class FuncionalidadeAmbiente extends Funcionalidade {

	/**
	 * Cadastro de ambientes.
	 */
	public static final FuncionalidadeAmbiente FUNC_AMBIENTE_CADASTRAR = create("FUNC_AMBIENTE_CADASTRAR","Cadastrar Ambiente");
	/**
	 * Consulta de ambientes.
	 */
	public static final FuncionalidadeAmbiente FUNC_AMBIENTE_CONSULTAR = create("FUNC_AMBIENTE_CONSULTAR","Consultar Ambiente");

	/**
	 * Cria uma nova funcionalidade.
	 *
	 * @param id   identificador da nova funcionalidade.
	 * @param name nome da nova funcionalidade.
	 * @return a funcionalidade criada.
	 */
	private static FuncionalidadeAmbiente create(String id, String name) {
		FuncionalidadeAmbiente result = new FuncionalidadeAmbiente(id, name);
		addToMap(id, result);
		return result;
	}

	/**
	   * Construtor
	   *
	   * @param id   identificador da funcionalidade.
	   * @param nome nome da funcionalidade.
	   */
	  protected FuncionalidadeAmbiente(String id, String nome) {
	    super(id, nome);
	  }

}