package com.logus.kaizen.model.solicitacao;

import com.logus.core.model.aut.Funcionalidade;
/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class FuncionalidadeSolicitacao extends Funcionalidade {

	/**
	 * Cadastro de solicitações.
	 */
	public static final FuncionalidadeSolicitacao FUNC_SOLICITACAO_CADASTRAR = create("FUNC_SOLICITACAO_CADASTRAR","Cadastrar Solicitação");
	/**
	 * Consulta de solicitações.
	 */
	public static final FuncionalidadeSolicitacao FUNC_SOLICITACAO_CONSULTAR = create("FUNC_SOLICITACAO_CONSULTAR","Consultar Solicitação");

	/**
	 * Cria uma nova funcionalidade.
	 *
	 * @param id   identificador da nova funcionalidade.
	 * @param name nome da nova funcionalidade.
	 * @return a funcionalidade criada.
	 */
	private static FuncionalidadeSolicitacao create(String id, String name) {
		FuncionalidadeSolicitacao result = new FuncionalidadeSolicitacao(id, name);
		addToMap(id, result);
		return result;
	}

	/**
	   * Construtor
	   *
	   * @param id   identificador da funcionalidade.
	   * @param nome nome da funcionalidade.
	   */
	  protected FuncionalidadeSolicitacao(String id, String nome) {
	    super(id, nome);
	  }

}