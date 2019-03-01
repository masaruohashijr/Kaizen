/**
 *
 */
package com.logus.kaizen.model.apoio.biblioteca;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.core.model.persistence.Assignable;
/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
@Entity
@Table(name = Biblioteca.TABLE_BIBLIOTECA)

public class Biblioteca implements Assignable<Biblioteca> {
	public static final String TABLE_BIBLIOTECA = "KZ_BIBLIOTECA";

	@Id
	@TableGenerator(name = "seq_biblioteca", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_biblioteca")
	@Column(name = "seq_biblioteca")
	private Long id;

	@Column(name = "nom_biblioteca", length = 100, nullable = false)
	@Size(min = 1, max = 100, message = KaizenTranslator.BIBLIOTECA_TAMANHO_NOME)
	@NotNull(message = KaizenTranslator.BIBLIOTECA_NOME_OBRIGATORIO)
	private String nome;

	@Column(name = "dsc_biblioteca", length = 1200, nullable = true)
	@Size(min = 0, max = 1200, message = KaizenTranslator.BIBLIOTECA_TAMANHO_DESCRICAO)
	@Null
	private String descricao;

	@Column(name = "flg_ativo", nullable = false)
	private boolean ativo = Boolean.TRUE;

	public Biblioteca() {
	}

	public Biblioteca(Biblioteca biblioteca) {
		assignFrom(biblioteca);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.Assignable#assignFrom(java.lang.Object)
	 */
	@Override
	public Biblioteca assignFrom(Biblioteca biblioteca) {
		this.id = biblioteca.getId();
		this.nome = biblioteca.getNome();
		this.descricao = biblioteca.getDescricao();
		this.ativo = biblioteca.isAtivo();
		return this;
	}

	/**
	 * @return {@link #ativo}
	 */
	public boolean isAtivo() {
		return ativo;
	}

	/**
	 * @param ativo atualiza {@link #ativo}.
	 */

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	/**
	 * @return {@link #nome}
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome atualiza {@link #nome}.
	 */

	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return {@link #id}
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id atualiza {@link #id}.
	 */

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (id == null) {
			return this == object;
		}
		if (!(object instanceof Biblioteca)) {
			return false;
		}
		return Objects.equals(this.id, ((Biblioteca) object).id);
	}

	@Override
	public int hashCode() {
		if (id == null) {
			return super.hashCode();
		}
		return Objects.hashCode(id);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
