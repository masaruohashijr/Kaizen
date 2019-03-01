/**
 *
 */
package com.logus.kaizen.model.apoio.atendimento;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

import com.logus.kaizen.model.processo.Passo;
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
@Table(name = Atendimento.TABLE_NAME)
public class Atendimento implements Assignable<Atendimento> {

	public static final String TABLE_NAME = "KZ_ATENDIMENTO";

	@Id
	@TableGenerator(name = "seq_atendimento", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_atendimento")
	@Column(name = "seq_atendimento")
	private Long id;

	@Column(name = "nom_atendimento", length = 100, nullable = false)
	@Size(min = 1, max = 100, message = KaizenTranslator.ATENDIMENTO_TAMANHO_TITULO)
	@NotNull(message = KaizenTranslator.ATENDIMENTO_TITULO_OBRIGATORIO)
	private String titulo;

	@Column(name = "dsc_atendimento", length = 1200, nullable = true)
	@Size(min = 0, max = 1200, message = KaizenTranslator.ATENDIMENTO_TAMANHO_TITULO)
	@Null
	private String descricao;

	@OneToMany(mappedBy = "atendimentoDestino", targetEntity = Passo.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Size(min = 0)
	private Collection<Passo> passosAtendimentosDestino = new ArrayList<Passo>();

	@OneToMany(mappedBy = "atendimentoOrigem", targetEntity = Passo.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Size(min = 0)
	private Collection<Passo> passosAtendimentosOrigem = new ArrayList<Passo>();

	@Column(name = "flg_ativo", nullable = false)
	private boolean ativo = Boolean.TRUE;

	public Atendimento() {
	}

	public Atendimento(Atendimento atendimento) {
		assignFrom(atendimento);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.Assignable#assignFrom(java.lang.Object)
	 */
	@Override
	public Atendimento assignFrom(Atendimento atendimento) {
		this.id = atendimento.getId();
		this.titulo = atendimento.getTitulo();
		this.descricao = atendimento.getDescricao();
		this.passosAtendimentosOrigem = atendimento.getPassosAtendimentosOrigem();
		this.passosAtendimentosDestino = atendimento.getPassosAtendimentosDestino();
		this.ativo = atendimento.isAtivo();
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
	 * @return {@link #titulo}
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo atualiza {@link #titulo}.
	 */

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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



	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Collection<Passo> getPassosAtendimentosDestino() {
		return passosAtendimentosDestino;
	}

	public void setPassosAtendimentosDestino(Collection<Passo> passosAtendimentosDestino) {
		this.passosAtendimentosDestino = passosAtendimentosDestino;
	}

	public Collection<Passo> getPassosAtendimentosOrigem() {
		return passosAtendimentosOrigem;
	}

	public void setPassosAtendimentosOrigem(Collection<Passo> passosAtendimentosOrigem) {
		this.passosAtendimentosOrigem = passosAtendimentosOrigem;
	}

	@Override
	public String toString() {
		return titulo;
	}
}
