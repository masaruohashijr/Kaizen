/**
 *
 */
package com.logus.kaizen.model.apoio.funcao;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.logus.core.model.persistence.Assignable;
import com.logus.core.model.persistence.CollectionSynchronizer;
import com.logus.kaizen.model.TableNames;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.kaizen.model.util.YokaiListener;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
@Entity
@EntityListeners(YokaiListener.class)
@Table(name = Funcao.TB_FUNCAO)
public class Funcao implements Assignable<Funcao>, TableNames {

	@Id
	@TableGenerator(name = "seq_funcao", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_funcao")
	@Column(name = "seq_funcao")
	private Long id;

	@Column(name = "nom_funcao", length = 100, nullable = false)
	@Size(min = 1, max = 100, message = KaizenTranslator.FUNCAO_TAMANHO_NOME)
	@NotNull(message = KaizenTranslator.FUNCAO_NOME_OBRIGATORIO)
	private String nome;

	@Column(name = "dsc_funcao", length = 1200, nullable = true)
	@Size(min = 0, max = 1200, message = KaizenTranslator.FUNCAO_TAMANHO_DESCRICAO)
	@Null
	private String descricao;

	@Column(name = "flg_ativo", nullable = false)
	private boolean ativo = Boolean.TRUE;

	@OneToMany(mappedBy = "funcao", targetEntity = ItemFuncao.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<ItemFuncao> itensFuncao = new ArrayList<ItemFuncao>();

	public Funcao() {
	}

	public Funcao(Funcao funcao) {
		assignFrom(funcao);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.Assignable#assignFrom(java.lang.Object)
	 */
	@Override
	public Funcao assignFrom(Funcao funcao) {
		this.id = funcao.getId();
		this.nome = funcao.getNome();
		this.descricao = funcao.getDescricao();
		this.ativo = funcao.isAtivo();
		CollectionSynchronizer.synchronize(funcao.itensFuncao, this.itensFuncao,
				itemFuncao -> itemFuncao.setFuncao(this));
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

	public Collection<ItemFuncao> getItensFuncao() {
		return itensFuncao;
	}

	public void setItensFuncao(Collection<ItemFuncao> itensFuncao) {
		this.itensFuncao = itensFuncao;
	}
}
