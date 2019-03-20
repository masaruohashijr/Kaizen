package com.logus.kaizen.model.apoio.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.logus.core.model.persistence.Assignable;
import com.logus.kaizen.model.TableNames;
import com.logus.kaizen.model.apoio.urgencia.Urgencia;

@Entity
@Table(schema = "security", name = Urgencia.TB_USUARIO)
public class Usuario implements Assignable<Usuario>, TableNames{

	@Id
	@Column(name = "cod_usuario", nullable = false, length = 20)
	@Size(min = 1, max = 20)
	@NotNull
	private String codigo;

	@Column(name = "nom_usuario", length = 50, nullable = false)
	@Size(min = 1, max = 100)
	@NotNull
	private String nome;

	@Column(name = "dsc_email", length = 200, nullable = false)
	@Size(min = 1, max = 200)
	@NotNull
	private String email;

	@Column(name = "flg_ativo", nullable = false)
	private boolean ativo = Boolean.TRUE;


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

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public Usuario assignFrom(Usuario usuario) {
		this.codigo = usuario.getCodigo();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.ativo = usuario.isAtivo();
		return this;
	}

	@Override
	public String toString() {
		return codigo;
	}
}
