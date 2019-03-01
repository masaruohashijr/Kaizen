package com.logus.kaizen.model.apoio.tipomondai;

import com.logus.core.model.aut.Funcionalidade;
/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class FuncionalidadeTipoMondai extends Funcionalidade {

	/**
	 * Cadastro de tipos de mondai.
	 */
	public static final FuncionalidadeTipoMondai FUNC_TIPO_MONDAI_CADASTRAR = create("FUNC_TIPO_MONDAI_CADASTRAR","Cadastrar Tipo de Mondai");
	/**
	 * Consulta de tipos de mondai.
	 */
	public static final FuncionalidadeTipoMondai FUNC_TIPO_MONDAI_CONSULTAR = create("FUNC_TIPO_MONDAI_CONSULTAR","Consultar Tipo de Mondai");

	/**
	 * Cria uma nova funcionalidade.
	 *
	 * @param id   identificador da nova funcionalidade.
	 * @param name nome da nova funcionalidade.
	 * @return a funcionalidade criada.
	 */
	private static FuncionalidadeTipoMondai create(String id, String name) {
		FuncionalidadeTipoMondai result = new FuncionalidadeTipoMondai(id, name);
		addToMap(id, result);
		return result;
	}

	/**
	   * Construtor
	   *
	   * @param id   identificador da funcionalidade.
	   * @param nome nome da funcionalidade.
	   */
	  protected FuncionalidadeTipoMondai(String id, String nome) {
	    super(id, nome);
	  }

}