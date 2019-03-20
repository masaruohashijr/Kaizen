/**
 *
 */
package com.logus.kaizen.model.apoio.urgencia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.logus.core.model.persistence.Assignable;
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
@Table(name = Urgencia.TB_URGENCIA)
public class Urgencia implements Assignable<Urgencia>, TableNames {

	@Id
	@TableGenerator(name = "seq_urgencia", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_urgencia")
	@Column(name = "seq_urgencia")
	private Long id;

	@Column(name = "nom_urgencia", length = 100, nullable = false)
	@Size(min = 1, max = 100, message = KaizenTranslator.URGENCIA_TAMANHO_NOME)
	@NotNull(message = KaizenTranslator.URGENCIA_NOME_OBRIGATORIO)
	private String nome;


	@Column(name = "dsc_urgencia", length = 1200, nullable = true)
	@Size(min = 0, max = 1200, message = KaizenTranslator.URGENCIA_TAMANHO_DESCRICAO)
	@Null
	private String descricao;

	@Column(name = "flg_ativo", nullable = false)
	private boolean ativo = Boolean.TRUE;

	public Urgencia() {
	}

	public Urgencia(Urgencia urgencia) {
		assignFrom(urgencia);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.Assignable#assignFrom(java.lang.Object)
	 */
	@Override
	public Urgencia assignFrom(Urgencia urgencia) {
		this.id = urgencia.getId();
		this.nome = urgencia.getNome();
		this.descricao = urgencia.getDescricao();
		this.ativo = urgencia.isAtivo();
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
