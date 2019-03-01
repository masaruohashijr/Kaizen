/**
 *
 */
package com.logus.kaizen.model.apoio.produto;

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
@Table(name = Produto.TABLE_NAME)
public class Produto implements Assignable<Produto> {

	public static final String TABLE_NAME = "KZ_PRODUTO";

	@Id
	@TableGenerator(name = "seq_produto", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_produto")
	@Column(name = "seq_produto")
	private Long id;

	@Column(name = "nom_produto", length = 100, nullable = false)
	@Size(min = 1, max = 100, message = KaizenTranslator.PRODUTO_TAMANHO_NOME)
	@NotNull(message = KaizenTranslator.PRODUTO_NOME_OBRIGATORIO)
	private String nome;

	@Column(name = "dsc_biblioteca", length = 1200, nullable = true)
	@Size(min = 0, max = 1200, message = KaizenTranslator.PRODUTO_TAMANHO_DESCRICAO)
	@Null
	private String descricao;

	@Column(name = "flg_ativo", nullable = false)
	private boolean ativo = Boolean.TRUE;

	public Produto() {
	}

	public Produto(Produto produto) {
		assignFrom(produto);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.Assignable#assignFrom(java.lang.Object)
	 */
	@Override
	public Produto assignFrom(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.ativo = produto.isAtivo();
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
