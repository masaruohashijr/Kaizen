package com.logus.kaizen.model.projeto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.logus.core.model.persistence.Assignable;
import com.logus.kaizen.model.translation.KaizenTranslator;
/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
@Entity
@Table(name = Papel.TABLE_NAME)
public class Papel implements Assignable<Papel>{

	public static final String TABLE_NAME = "KZ_PAPEL";

	@Id
	@TableGenerator(name = "seq_papel", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_papel")
	@Column(name = "seq_papel")
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "seq_projeto", referencedColumnName = "seq_projeto", nullable = false)
	private Projeto projeto;

	@Column(name = "nom_papel", length = 50, nullable = false)
	@Size(max = 50, min = 1, message = KaizenTranslator.PROJETO_TAMANHO_NOME)
	@NotNull(message = KaizenTranslator.PROJETO_NOME_OBRIGATORIO)
	private String nome;

	@Column(name = "dsc_papel", length = 1200, nullable = true)
	@Size(min = 0, max = 1200, message = KaizenTranslator.PROJETO_TAMANHO_DESCRICAO)
	@Null
	private String descricao;

	@Column(name = "flg_ativo", nullable = false)
	@NotNull
	private boolean ativo = Boolean.TRUE;

	@Override
	public Papel assignFrom(Papel papel) {
		this.id = papel.getId();
		this.nome = papel.getNome();
		this.descricao = papel.getDescricao();
		this.projeto = papel.getProjeto();
		this.ativo = papel.isAtivo();
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

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

}
