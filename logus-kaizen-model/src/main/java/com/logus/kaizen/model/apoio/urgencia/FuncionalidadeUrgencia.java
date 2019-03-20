package com.logus.kaizen.model.apoio.urgencia;

import com.logus.core.model.aut.Funcionalidade;
/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class FuncionalidadeUrgencia extends Funcionalidade {

	/**
	 * Cadastro de urgências.
	 */
	public static final FuncionalidadeUrgencia FUNC_URGENCIA_CADASTRAR = create("FUNC_URGENCIA_CADASTRAR","Cadastrar Funcao");
	/**
	 * Consulta de urgências.
	 */
	public static final FuncionalidadeUrgencia FUNC_URGENCIA_CONSULTAR = create("FUNC_URGENCIA_CONSULTAR","Consultar Funcao");

	/**
	 * Cria uma nova funcionalidade.
	 *
	 * @param id   identificador da nova funcionalidade.
	 * @param name nome da nova funcionalidade.
	 * @return a funcionalidade criada.
	 */
	private static FuncionalidadeUrgencia create(String id, String name) {
		FuncionalidadeUrgencia result = new FuncionalidadeUrgencia(id, name);
		addToMap(id, result);
		return result;
	}

	/**
	   * Construtor
	   *
	   * @param id   identificador da funcionalidade.
	   * @param nome nome da funcionalidade.
	   */
	  protected FuncionalidadeUrgencia(String id, String nome) {
	    super(id, nome);
	  }

}