/**
 *
 */
package com.logus.kaizen.model.apoio.tipomondai;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.logus.core.model.persistence.Assignable;
import com.logus.core.model.persistence.CollectionSynchronizer;
import com.logus.kaizen.model.TableNames;
import com.logus.kaizen.model.apoio.processo.Processo;
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
@Table(name = TipoMondai.TB_TIPO_MONDAI)

public class TipoMondai implements Assignable<TipoMondai>, TableNames {

	@Id
	@TableGenerator(name = "seq_tipo_mondai", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_tipo_mondai")
	@Column(name = "seq_tipo_mondai")
	private Long id;

	@Column(name = "nom_tipo_mondai", length = 100, nullable = false)
	@Size(min = 1, max = 100, message = KaizenTranslator.TIPO_MONDAI_TAMANHO_NOME)
	@NotNull(message = KaizenTranslator.TIPO_MONDAI_NOME_OBRIGATORIO)
	private String nome;

	@Column(name = "dsc_tipo_mondai", length = 1200, nullable = true)
	@Size(min = 0, max = 1200, message = KaizenTranslator.TIPO_MONDAI_TAMANHO_DESCRICAO)
	@Null
	private String descricao;

	@OneToMany(mappedBy = "tipoMondai", targetEntity = TipoMondaiProjeto.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Size(min = 0)
	private Collection<TipoMondaiProjeto> tiposMondaiProjeto = new ArrayList<TipoMondaiProjeto>();

	@JoinColumn(name = "seq_processo", referencedColumnName = "seq_processo", nullable = true)
	@ManyToOne(optional = true)
	private Processo processo;

	@OneToMany(mappedBy = "tipoMondai", targetEntity = FuncaoPassoItem.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<FuncaoPassoItem> funcoesPassos = new ArrayList<FuncaoPassoItem>();

	@OneToMany(mappedBy = "tipoMondai", targetEntity = Solicitacao.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Size(min = 0)
	private Collection<Solicitacao> solicitacao = new ArrayList<Solicitacao>();

	@Column(name = "flg_ativo", nullable = false)
	private boolean ativo = Boolean.TRUE;

	public TipoMondai() {
	}

	public TipoMondai(TipoMondai tipoMondai) {
		assignFrom(tipoMondai);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.Assignable#assignFrom(java.lang.Object)
	 */
	@Override
	public TipoMondai assignFrom(TipoMondai tipoMondai) {
		this.id = tipoMondai.getId();
		this.nome = tipoMondai.getNome();
		this.descricao = tipoMondai.getDescricao();
		this.ativo = tipoMondai.isAtivo();
		this.processo = tipoMondai.getProcesso();
		CollectionSynchronizer.synchronize(tipoMondai.tiposMondaiProjeto, this.tiposMondaiProjeto, tipoMondaiProjeto -> tipoMondaiProjeto.setTipoMondai(this));
		CollectionSynchronizer.synchronize(tipoMondai.solicitacao, this.solicitacao, solicitacao -> solicitacao.setTipoMondai(this));
		return this;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
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

	public Collection<Solicitacao> getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Collection<Solicitacao> solicitacao) {
		this.solicitacao = solicitacao;
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
		if (!(object instanceof TipoMondai)) {
			return false;
		}
		return Objects.equals(this.id, ((TipoMondai) object).id);
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

	public Collection<TipoMondaiProjeto> getTiposMondaiProjeto() {
		return tiposMondaiProjeto;
	}

	public void setTiposMondaiProjeto(Collection<TipoMondaiProjeto> tiposMondaiProjeto) {
		this.tiposMondaiProjeto = tiposMondaiProjeto;
	}

	public Collection<FuncaoPassoItem> getFuncoesPassos() {
		return funcoesPassos;
	}

	public void setFuncoesPassos(Collection<FuncaoPassoItem> funcoesPassos) {
		this.funcoesPassos = funcoesPassos;
	}


}
