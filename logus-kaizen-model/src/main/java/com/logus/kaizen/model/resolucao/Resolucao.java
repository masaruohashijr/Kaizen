package com.logus.kaizen.model.resolucao;

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
@Table(name = Resolucao.TABLE_NAME)
public class Resolucao implements Assignable<Resolucao>{

	public static final String TABLE_NAME = "KZ_RESOLUCAO";

	@Id
	@TableGenerator(name = "seq_resolucao", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_resolucao")
	@Column(name = "seq_resolucao")
	private Long id;

	@Column(name = "nom_resolucao", length = 50, nullable = false)
	@Size(max = 50, min = 1, message = KaizenTranslator.RESOLUCAO_TAMANHO_NOME)
	@NotNull(message = KaizenTranslator.RESOLUCAO_NOME_OBRIGATORIO)
	private String nome;

	@Column(name = "dsc_resolucao", length = 1200, nullable = true)
	@Size(min = 0, max = 1200, message = KaizenTranslator.RESOLUCAO_TAMANHO_DESCRICAO)
	@Null
	private String descricao;

	@Column(name = "flg_ativo", nullable = false)
	@NotNull
	private boolean ativo = Boolean.TRUE;

	@Override
	public Resolucao assignFrom(Resolucao resolucao) {
		this.id = resolucao.getId();
		this.nome = resolucao.getNome();
		this.descricao = resolucao.getDescricao();
		this.ativo = resolucao.isAtivo();
		return this;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "" + getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



}
