package com.logus.kaizen.model.apoio.usuario;

import com.logus.core.model.aut.Funcionalidade;
/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class FuncionalidadeUsuario extends Funcionalidade {

	/**
	 * Cadastro de usuarios.
	 */
	public static final FuncionalidadeUsuario FUNC_USUARIO_CADASTRAR = create("FUNC_USUARIO_CADASTRAR","Cadastrar Usuario");

	/**
	 * Consulta de usuarios.
	 */
	public static final FuncionalidadeUsuario FUNC_USUARIO_CONSULTAR = create("FUNC_USUARIO_CONSULTAR","Consultar Usuario");

	/**
	 * Cria uma nova funcionalidade.
	 *
	 * @param id   identificador da nova funcionalidade.
	 * @param name nome da nova funcionalidade.
	 * @return a funcionalidade criada.
	 */
	private static FuncionalidadeUsuario create(String id, String name) {
		FuncionalidadeUsuario result = new FuncionalidadeUsuario(id, name);
		addToMap(id, result);
		return result;
	}

	/**
	   * Construtor
	   *
	   * @param id   identificador da funcionalidade.
	   * @param nome nome da funcionalidade.
	   */
	  protected FuncionalidadeUsuario(String id, String nome) {
	    super(id, nome);
	  }

}