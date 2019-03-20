/**
 *
 */
package com.logus.kaizen.model.apoio.atendimento;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

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
import com.logus.kaizen.model.apoio.processo.Passo;
import com.logus.kaizen.model.solicitacao.ItemSolicitacao;
import com.logus.kaizen.model.solicitacao.Solicitacao;
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
@Table(name = Atendimento.TB_ATENDIMENTO)
public class Atendimento implements Assignable<Atendimento>, TableNames {

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

	@OneToMany(mappedBy = "atendimento", targetEntity = Solicitacao.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Size(min = 0)
	private Collection<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();

	@OneToMany(mappedBy = "atendimento", targetEntity = ItemSolicitacao.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Size(min = 0)
	private Collection<ItemSolicitacao> itensSolicitacoes = new ArrayList<ItemSolicitacao>();

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
		CollectionSynchronizer.synchronize(atendimento.getPassosAtendimentosOrigem(), this.passosAtendimentosOrigem, passo -> passo.setAtendimentoOrigem(this));
		CollectionSynchronizer.synchronize(atendimento.getPassosAtendimentosDestino(), this.passosAtendimentosDestino, passo -> passo.setAtendimentoDestino(this));
		CollectionSynchronizer.synchronize(atendimento.getSolicitacoes(), this.solicitacoes, solicitacao -> solicitacao.setAtendimento(this));
		CollectionSynchronizer.synchronize(atendimento.getItensSolicitacoes(), this.itensSolicitacoes, itemSolicitacao -> itemSolicitacao.setAtendimento(this));
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

	public Collection<Solicitacao> getSolicitacoes() {
		return solicitacoes;
	}

	public void setSolicitacoes(Collection<Solicitacao> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}

	@Override
	public String toString() {
		return titulo;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (id == null) {
			return this == object;
		}
		if (!(object instanceof Atendimento)) {
			return false;
		}
		return Objects.equals(this.id, ((Atendimento) object).id);
	}

	@Override
	public int hashCode() {
		if (id == null) {
			return super.hashCode();
		}
		return Objects.hashCode(id);
	}

	public Collection<ItemSolicitacao> getItensSolicitacoes() {
		return itensSolicitacoes;
	}

	public void setItensSolicitacoes(Collection<ItemSolicitacao> itensSolicitacoes) {
		this.itensSolicitacoes = itensSolicitacoes;
	}

}
