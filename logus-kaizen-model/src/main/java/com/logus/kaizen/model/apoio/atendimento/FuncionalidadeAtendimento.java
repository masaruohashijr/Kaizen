package com.logus.kaizen.model.apoio.atendimento;

import com.logus.core.model.aut.Funcionalidade;
/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class FuncionalidadeAtendimento extends Funcionalidade {

	/**
	 * Cadastro de status.
	 */
	public static final FuncionalidadeAtendimento FUNC_ATENDIMENTO_CADASTRAR = create("FUNC_ATENDIMENTO_CADASTRAR","Cadastrar Atendimento");
	/**
	 * Consulta de status.
	 */
	public static final FuncionalidadeAtendimento FUNC_ATENDIMENTO_CONSULTAR = create("FUNC_ATENDIMENTO_CONSULTAR","Consultar Atendimento");

	/**
	 * Cria uma nova funcionalidade.
	 *
	 * @param id   identificador da nova funcionalidade.
	 * @param name nome da nova funcionalidade.
	 * @return a funcionalidade criada.
	 */
	private static FuncionalidadeAtendimento create(String id, String name) {
		FuncionalidadeAtendimento result = new FuncionalidadeAtendimento(id, name);
		addToMap(id, result);
		return result;
	}

	/**
	   * Construtor
	   *
	   * @param id   identificador da funcionalidade.
	   * @param nome nome da funcionalidade.
	   */
	  protected FuncionalidadeAtendimento(String id, String nome) {
	    super(id, nome);
	  }

}