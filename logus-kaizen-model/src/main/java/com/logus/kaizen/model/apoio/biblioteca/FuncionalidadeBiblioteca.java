package com.logus.kaizen.model.apoio.biblioteca;

import com.logus.core.model.aut.Funcionalidade;
/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class FuncionalidadeBiblioteca extends Funcionalidade {

	/**
	 * Cadastro de bibliotecas.
	 */
	public static final FuncionalidadeBiblioteca FUNC_BIBLIOTECA_CADASTRAR = create("FUNC_BIBLIOTECA_CADASTRAR","Cadastrar Biblioteca");
	/**
	 * Consulta de bibliotecas.
	 */
	public static final FuncionalidadeBiblioteca FUNC_BIBLIOTECA_CONSULTAR = create("FUNC_BIBLIOTECA_CONSULTAR","Consultar Biblioteca");

	/**
	 * Cria uma nova funcionalidade.
	 *
	 * @param id   identificador da nova funcionalidade.
	 * @param name nome da nova funcionalidade.
	 * @return a funcionalidade criada.
	 */
	private static FuncionalidadeBiblioteca create(String id, String name) {
		FuncionalidadeBiblioteca result = new FuncionalidadeBiblioteca(id, name);
		addToMap(id, result);
		return result;
	}

	/**
	   * Construtor
	   *
	   * @param id   identificador da funcionalidade.
	   * @param nome nome da funcionalidade.
	   */
	  protected FuncionalidadeBiblioteca(String id, String nome) {
	    super(id, nome);
	  }

}