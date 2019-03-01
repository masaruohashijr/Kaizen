/**
 *
 */
package com.logus.kaizen.model.apoio.tipomondai;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.logus.kaizen.model.processo.Processo;
import com.logus.kaizen.model.projeto.Projeto;
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
@Table(name = TipoMondaiProjeto.NOME_TABELA_TIPO_MONDAI_PROJETO)

public class TipoMondaiProjeto implements Assignable<TipoMondaiProjeto> {
	public static final String NOME_TABELA_TIPO_MONDAI_PROJETO = "KZ_TIPO_MONDAI_PROJETO";

	@Id
	@TableGenerator(name = "seq_tipo_mondai_projeto", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_tipo_mondai_projeto")
	@Column(name = "seq_tipo_mondai_projeto")
	private Long id;

	@JoinColumn(name = "seq_processo",
			    referencedColumnName = "seq_processo", nullable = false)
	@ManyToOne(optional = false)
	private Processo processo;

	@ManyToOne(optional = false)
	@JoinColumn(name = "seq_tipo_mondai", referencedColumnName = "seq_tipo_mondai", nullable = false)
	private TipoMondai tipoMondai;

	@ManyToOne(optional = false)
	@JoinColumn(name = "seq_projeto", referencedColumnName = "seq_projeto", nullable = false)
	private Projeto projeto;

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
}
