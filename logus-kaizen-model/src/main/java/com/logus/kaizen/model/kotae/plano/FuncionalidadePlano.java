package com.logus.kaizen.model.kotae.plano;

import com.logus.core.model.aut.Funcionalidade;
/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class FuncionalidadePlano extends Funcionalidade {

	/**
	 * Cadastro de planos de integração de builds.
	 */
	public static final FuncionalidadePlano FUNC_PLANO_CADASTRAR = create("FUNC_TOGURU_CADASTRAR","Cadastrar Plano de Integração de Builds");
	/**
	 * Consulta de planos de integração de builds.
	 */
	public static final FuncionalidadePlano FUNC_PLANO_CONSULTAR = create("FUNC_TOGURU_CONSULTAR","Consultar Plano de Integração de Builds");

	/**
	 * Cria uma nova funcionalidade.
	 *
	 * @param id   identificador da nova funcionalidade.
	 * @param name nome da nova funcionalidade.
	 * @return a funcionalidade criada.
	 */
	private static FuncionalidadePlano create(String id, String name) {
		FuncionalidadePlano result = new FuncionalidadePlano(id, name);
		addToMap(id, result);
		return result;
	}

	/**
	   * Construtor
	   *
	   * @param id   identificador da funcionalidade.
	   * @param nome nome da funcionalidade.
	   */
	  protected FuncionalidadePlano(String id, String nome) {
	    super(id, nome);
	  }

}