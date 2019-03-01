package com.logus.kaizen.model.projeto;

import com.logus.core.model.aut.Funcionalidade;
/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class FuncionalidadeProjeto extends Funcionalidade {

	/**
	 * Cadastro de projetos.
	 */
	public static final FuncionalidadeProjeto FUNC_PROJETO_CADASTRAR = create("FUNC_PROJETO_CADASTRAR","Cadastrar Projeto");
	/**
	 * Consulta de projetos.
	 */
	public static final FuncionalidadeProjeto FUNC_PROJETO_CONSULTAR = create("FUNC_PROJETO_CONSULTAR","Consultar Projeto");
	/**
	 * Cadastro de papéis.
	 */
	public static final FuncionalidadeProjeto FUNC_PAPEL_CADASTRAR = create("FUNC_PAPEL_CADASTRAR","Cadastrar Passo");
	/**
	 * Consulta de papéis.
	 */
	public static final FuncionalidadeProjeto FUNC_PAPEL_CONSULTAR = create("FUNC_PAPEL_CONSULTAR","Consultar Passo");

	/**
	 * Cria uma nova funcionalidade.
	 *
	 * @param id   identificador da nova funcionalidade.
	 * @param name nome da nova funcionalidade.
	 * @return a funcionalidade criada.
	 */
	private static FuncionalidadeProjeto create(String id, String name) {
		FuncionalidadeProjeto result = new FuncionalidadeProjeto(id, name);
		addToMap(id, result);
		return result;
	}

	/**
	   * Construtor
	   *
	   * @param id   identificador da funcionalidade.
	   * @param nome nome da funcionalidade.
	   */
	  protected FuncionalidadeProjeto(String id, String nome) {
	    super(id, nome);
	  }

}