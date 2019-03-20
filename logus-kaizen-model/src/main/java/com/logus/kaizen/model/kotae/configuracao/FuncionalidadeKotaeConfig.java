package com.logus.kaizen.model.kotae.configuracao;

import com.logus.core.model.aut.Funcionalidade;
/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class FuncionalidadeKotaeConfig extends Funcionalidade {

	/**
	 * Cadastro de configurações de Kotae.
	 */
	public static final FuncionalidadeKotaeConfig FUNC_KOTAE_CONFIG_CADASTRAR = create("FUNC_KOTAE_CONFIG_CADASTRAR","Cadastrar Configuração de Kotae");
	/**
	 * Consulta de configurações de Kotae.
	 */
	public static final FuncionalidadeKotaeConfig FUNC_KOTAE_CONFIG_CONSULTAR = create("FUNC_KOTAE_CONFIG_CONSULTAR","Consultar Configuração de Kotae");

	/**
	 * Cria uma nova funcionalidade.
	 *
	 * @param id   identificador da nova funcionalidade.
	 * @param name nome da nova funcionalidade.
	 * @return a funcionalidade criada.
	 */
	private static FuncionalidadeKotaeConfig create(String id, String name) {
		FuncionalidadeKotaeConfig result = new FuncionalidadeKotaeConfig(id, name);
		addToMap(id, result);
		return result;
	}

	/**
	   * Construtor
	   *
	   * @param id   identificador da funcionalidade.
	   * @param nome nome da funcionalidade.
	   */
	  protected FuncionalidadeKotaeConfig(String id, String nome) {
	    super(id, nome);
	  }

}