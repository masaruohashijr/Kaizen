package com.logus.kaizen.model.solicitacao;
/**
 *
 * @author Roberto Araújo
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public enum RepositorioEnum {
	SIAFE_RIO("SIAFE-RIO"),
	TRUNK("trunk");

	private String nome;

	private RepositorioEnum(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return "" + getNome();
	}


}
