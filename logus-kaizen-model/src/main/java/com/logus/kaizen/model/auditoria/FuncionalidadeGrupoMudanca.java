package com.logus.kaizen.model.auditoria;

import com.logus.core.model.aut.Funcionalidade;
/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class FuncionalidadeGrupoMudanca extends Funcionalidade {

	/**
	 * Cadastro de grupos de mudança.
	 */
	public static final FuncionalidadeGrupoMudanca FUNC_GRUPO_MUDANCA_CADASTRAR = create("FUNC_GRUPO_MUDANCA_CADASTRAR","Cadastrar Grupos de Mudança");
	/**
	 * Consulta de grupos de mudança.
	 */
	public static final FuncionalidadeGrupoMudanca FUNC_GRUPO_MUDANCA_CONSULTAR = create("FUNC_GRUPO_MUDANCA_CONSULTAR","Consultar Grupos de Mudança");

	/**
	 * Cria uma nova funcionalidade.
	 *
	 * @param id   identificador da nova funcionalidade.
	 * @param name nome da nova funcionalidade.
	 * @return a funcionalidade criada.
	 */
	private static FuncionalidadeGrupoMudanca create(String id, String name) {
		FuncionalidadeGrupoMudanca result = new FuncionalidadeGrupoMudanca(id, name);
		addToMap(id, result);
		return result;
	}

	/**
	   * Construtor
	   *
	   * @param id   identificador da funcionalidade.
	   * @param nome nome da funcionalidade.
	   */
	  protected FuncionalidadeGrupoMudanca(String id, String nome) {
	    super(id, nome);
	  }

}