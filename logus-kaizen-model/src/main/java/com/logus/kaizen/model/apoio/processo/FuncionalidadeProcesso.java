package com.logus.kaizen.model.apoio.processo;

import com.logus.core.model.aut.Funcionalidade;
/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class FuncionalidadeProcesso extends Funcionalidade {

	/**
	 * Cadastro de processo.
	 */
	public static final FuncionalidadeProcesso FUNC_PROCESSO_CADASTRAR = create("FUNC_PROCESSO_CADASTRAR","Cadastrar Processo");
	/**
	 * Consulta de processos.
	 */
	public static final FuncionalidadeProcesso FUNC_PROCESSO_CONSULTAR = create("FUNC_PROCESSO_CONSULTAR","Consultar Processos");

	/**
	 * Cria uma nova funcionalidade.
	 *
	 * @param id   identificador da nova funcionalidade.
	 * @param name nome da nova funcionalidade.
	 * @return a funcionalidade criada.
	 */
	private static FuncionalidadeProcesso create(String id, String name) {
		FuncionalidadeProcesso result = new FuncionalidadeProcesso(id, name);
		addToMap(id, result);
		return result;
	}

	/**
	   * Construtor
	   *
	   * @param id   identificador da funcionalidade.
	   * @param nome nome da funcionalidade.
	   */
	  protected FuncionalidadeProcesso(String id, String nome) {
	    super(id, nome);
	  }

}