/**
 *
 */
package com.logus.kaizen.model.kotae.configuracao;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import com.logus.core.model.translation.TM;
import com.logus.kaizen.model.TableNames;
import com.logus.kaizen.model.apoio.processo.Processo;
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
@Table(name = KotaeConfiguracao.TB_KOTAE_CONFIG)

public class KotaeConfiguracao implements Assignable<KotaeConfiguracao>, TableNames {

	public enum TipoKotae {
		PLANO,
		TOGURU;
		@Override
		public String toString() {
			return TM.translate(name());
		}
	}

	@Id
	@TableGenerator(name = "seq_kotae_config", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_kotae_config")
	@Column(name = "seq_kotae_config")
	private Long id;

	@Column(name = "dsc_kotae_config", length = 1200, nullable = true)
	@Size(min = 0, max = 1200, message = KaizenTranslator.KOTAE_TAMANHO_DESCRICAO)
	@Null
	private String descricao;

	@JoinColumn(name = "seq_processo", referencedColumnName = "seq_processo", nullable = true)
	@ManyToOne(optional = true)
	private Processo processo;

	@Column(name = "flg_ativo", nullable = false)
	private boolean ativo = Boolean.TRUE;

	@Enumerated(EnumType.STRING)
	@Column(name = "tip_kotae", nullable = false, unique = true)
	@NotNull(message = KaizenTranslator.KOTAE_TIPO_OBRIGATORIO)
	private TipoKotae tipoKotae;

	public KotaeConfiguracao() {
	}

	public KotaeConfiguracao(KotaeConfiguracao planoConfiguracao) {
		assignFrom(planoConfiguracao);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.Assignable#assignFrom(java.lang.Object)
	 */
	@Override
	public KotaeConfiguracao assignFrom(KotaeConfiguracao kotaeConfiguracao) {
		this.id = kotaeConfiguracao.getId();
		this.descricao = kotaeConfiguracao.getDescricao();
		this.ativo = kotaeConfiguracao.isAtivo();
		this.processo = kotaeConfiguracao.getProcesso();
		this.tipoKotae = kotaeConfiguracao.tipoKotae;
		return this;
	}

	public TipoKotae getTipoKotae() {
		return tipoKotae;
	}

	public void setTipoKotae(TipoKotae tipoKotae) {
		this.tipoKotae = tipoKotae;
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
		if (!(object instanceof KotaeConfiguracao)) {
			return false;
		}
		return Objects.equals(this.id, ((KotaeConfiguracao) object).id);
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

	@Override
	public String toString() {
		return tipoKotae.toString();
	}

}
