package com.logus.kaizen.model.chronos;

import com.logus.core.model.aut.Funcionalidade;
/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class FuncionalidadeChronos extends Funcionalidade {

	/**
	 * Cadastro de chronos.
	 */
	public static final FuncionalidadeChronos FUNC_CHRONOS_CADASTRAR = create("FUNC_CHRONOS_CADASTRAR","Cadastrar Chronos");
	/**
	 * Consulta de chronos.
	 */
	public static final FuncionalidadeChronos FUNC_CHRONOS_CONSULTAR = create("FUNC_CHRONOS_CONSULTAR","Consultar Chronos");

	/**
	 * Cria uma nova funcionalidade.
	 *
	 * @param id   identificador da nova funcionalidade.
	 * @param name nome da nova funcionalidade.
	 * @return a funcionalidade criada.
	 */
	private static FuncionalidadeChronos create(String id, String name) {
		FuncionalidadeChronos result = new FuncionalidadeChronos(id, name);
		addToMap(id, result);
		return result;
	}

	/**
	   * Construtor
	   *
	   * @param id   identificador da funcionalidade.
	   * @param nome nome da funcionalidade.
	   */
	  protected FuncionalidadeChronos(String id, String nome) {
	    super(id, nome);
	  }

}