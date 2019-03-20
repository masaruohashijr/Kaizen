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

import com.logus.core.model.persistence.Assignable;
import com.logus.core.model.persistence.CollectionSynchronizer;
import com.logus.kaizen.model.TableNames;
import com.logus.kaizen.model.apoio.processo.Processo;
import com.logus.kaizen.model.apoio.projeto.Projeto;
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
@Table(name = TipoMondaiProjeto.TB_TIPO_MONDAI_PROJETO)

public class TipoMondaiProjeto implements Assignable<TipoMondaiProjeto>, TableNames {

	@Id
	@TableGenerator(name = "seq_tipo_mondai_projeto", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_tipo_mondai_projeto")
	@Column(name = "seq_tipo_mondai_projeto")
	private Long id;

	@JoinColumn(name = "seq_processo", referencedColumnName = "seq_processo", nullable = false)
	@ManyToOne(optional = false)
	private Processo processo;

	@ManyToOne(optional = false)
	@JoinColumn(name = "seq_tipo_mondai", referencedColumnName = "seq_tipo_mondai", nullable = false)
	@NotNull(message = KaizenTranslator.SOLICITACAO_TIPO_MONDAI_OBRIGATORIO)
	private TipoMondai tipoMondai;

	@ManyToOne(optional = false)
	@JoinColumn(name = "seq_projeto", referencedColumnName = "seq_projeto", nullable = false)
	private Projeto projeto;

	@OneToMany(mappedBy = "tipoMondaiProjeto", targetEntity = PapelPassoItem.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<PapelPassoItem> papeisPassosItens = new ArrayList<PapelPassoItem>();

	@Column(name = "flg_ativo", nullable = false)
	private boolean ativo = Boolean.TRUE;

	public TipoMondaiProjeto() {
	}

	public TipoMondaiProjeto(TipoMondaiProjeto tipoMondaiProjeto) {
		assignFrom(tipoMondaiProjeto);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.Assignable#assignFrom(java.lang.Object)
	 */
	@Override
	public TipoMondaiProjeto assignFrom(TipoMondaiProjeto tipoMondaiProjeto) {
		this.id = tipoMondaiProjeto.getId();
		this.ativo = tipoMondaiProjeto.isAtivo();
		this.processo = tipoMondaiProjeto.getProcesso();
		this.projeto = tipoMondaiProjeto.getProjeto();
		this.tipoMondai = tipoMondaiProjeto.getTipoMondai();
		CollectionSynchronizer.synchronize(tipoMondaiProjeto.papeisPassosItens, this.papeisPassosItens, papelPassoItem -> papelPassoItem.setTipoMondaiProjeto(this));
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
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (id == null) {
			return this == object;
		}
		if (!(object instanceof TipoMondaiProjeto)) {
			return false;
		}
		return Objects.equals(this.id, ((TipoMondaiProjeto) object).id);
	}

	@Override
	public int hashCode() {
		if (id == null) {
			return super.hashCode();
		}
		return Objects.hashCode(id);
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public TipoMondai getTipoMondai() {
		return tipoMondai;
	}

	public void setTipoMondai(TipoMondai tipoMondai) {
		this.tipoMondai = tipoMondai;
	}

	public Collection<PapelPassoItem> getPapeisPassosItens() {
		return papeisPassosItens;
	}

	public void setPapeisPassosItens(Collection<PapelPassoItem> papeisPassosItens) {
		this.papeisPassosItens = papeisPassosItens;
	}

	@Override
	public String toString() {
		return ((null!=this.tipoMondai)?this.tipoMondai:"") + " - "+((null!=this.processo)?this.processo:"");
	}


}
