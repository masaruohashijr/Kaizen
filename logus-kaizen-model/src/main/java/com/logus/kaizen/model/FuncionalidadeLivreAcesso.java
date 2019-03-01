package com.logus.kaizen.model;

import com.logus.core.model.aut.Funcionalidade;

/**
 * @author Masaru Ohashi Júnior
 * @version 1.0.0
 * @since 1 de mar de 2019
 */
public class FuncionalidadeLivreAcesso extends Funcionalidade {

	public static final FuncionalidadeLivreAcesso FUNC_LIVRE_ACESSO = create("FUNC_LIVRE_ACESSO",
			"Funcionalidade de Livre Acesso");

	private static FuncionalidadeLivreAcesso create(String id, String name) {
		FuncionalidadeLivreAcesso result = new FuncionalidadeLivreAcesso(id, name);
		addToMap(id, result);
		return result;
	}

	protected FuncionalidadeLivreAcesso(String id, String nome) {
		super(id, nome);
	}

}
