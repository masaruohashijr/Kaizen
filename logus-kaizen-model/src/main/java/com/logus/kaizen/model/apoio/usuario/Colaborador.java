package com.logus.kaizen.model.apoio.usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.logus.kaizen.model.apoio.funcao.Funcao;
import com.logus.kaizen.model.apoio.projeto.Papel;
import com.logus.kaizen.model.apoio.projeto.Projeto;

public class Colaborador {

	private String codigo;
	private String nome;
	private String email;
	private Collection<Funcao> funcoes = new ArrayList<>();
	private Map<Projeto, Collection<Papel>> papeisProjetos = new HashMap<>();

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<Funcao> getFuncoes() {
		return funcoes;
	}

	public void setFuncoes(Collection<Funcao> funcoes) {
		this.funcoes = funcoes;
	}

	public Map<Projeto, Collection<Papel>> getPapeisProjetos() {
		return papeisProjetos;
	}

	public void setPapeisProjetos(Map<Projeto, Collection<Papel>> papeisProjetos) {
		this.papeisProjetos = papeisProjetos;
	}

}
