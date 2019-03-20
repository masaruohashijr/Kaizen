/**
 *
 */
package com.logus.kaizen.model.chronos;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.logus.core.model.persistence.Assignable;
import com.logus.core.model.util.Formats;
import com.logus.kaizen.model.TableNames;
import com.logus.kaizen.model.apoio.atendimento.Atendimento;
import com.logus.kaizen.model.kotae.configuracao.KotaeConfiguracao;
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
@Table(name = Chronos.TB_TOGURU)
public class Chronos implements Assignable<Chronos>, TableNames {

	/**
	 * Id
	 */
	@Id
	@TableGenerator(name = "seq_toguru", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_toguru")
	@Column(name = "seq_toguru")
	private Long id;

	/**
	 * Responsável
	 */
	@Column(name = "cod_responsavel", length = 50, nullable = false)
	@Size(min = 1, max = 50, message = KaizenTranslator.CHRONOS_RESPONSAVEL_TAMANHO)
	@NotNull(message = KaizenTranslator.CHRONOS_RESPONSAVEL_OBRIGATORIO)
	private String codigoResponsavel;

	/**
	 * Horário Início
	 */
	@Column(name = "dat_inicio", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull(message = KaizenTranslator.CHRONOS_DATA_INICIO_OBRIGATORIA)
	private Date dataInicio;

	/**
	 * Horário Fim
	 */
	@Column(name = "dat_fim", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Null
	private Date dataFim;

	/**
	 * Ativo
	 */
	@Column(name = "flg_ativo", nullable = false)
	private boolean ativo = Boolean.TRUE;

	/**
	 * Solicitação
	 */
	@ManyToOne
	@JoinColumn(name = "seq_solicitacao", referencedColumnName = "seq_solicitacao", nullable = true)
	@Null
	private Solicitacao solicitacao;

	/**
	 * Kotae Configuração
	 */
	@ManyToOne
	@JoinColumn(name = "seq_kotae_config", referencedColumnName = "seq_kotae_config", nullable = true)
	@Null
	private KotaeConfiguracao configuracao;

	/**
	 * Atendimento
	 */
	@ManyToOne(optional = true)
	@JoinColumn(name = "seq_atendimento", referencedColumnName = "seq_atendimento", nullable = true)
	@Null
	private Atendimento atendimento;


	public Chronos() {
	}

	public Chronos(Chronos toguru) {
		assignFrom(toguru);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.Assignable#assignFrom(java.lang.Object)
	 */
	@Override
	public Chronos assignFrom(Chronos toguru) {
		this.id = toguru.id;
		this.ativo = toguru.ativo;
		this.codigoResponsavel = toguru.codigoResponsavel;
		this.dataInicio = toguru.dataInicio;
		this.dataFim = toguru.dataFim;
		this.solicitacao = toguru.solicitacao;
		this.configuracao = toguru.configuracao;
		this.atendimento = toguru.atendimento;
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

	public String getCodigoResponsavel() {
		return codigoResponsavel;
	}

	public void setCodigoResponsavel(String codigoResponsavel) {
		this.codigoResponsavel = codigoResponsavel;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}

	@Override
	public String toString() {
		return "[ " + Formats.getDateTimeFormat().format(dataInicio) + " " + solicitacao + " ]";
	}

	public KotaeConfiguracao getConfiguracao() {
		return configuracao;
	}

	public void setConfiguracao(KotaeConfiguracao configuracao) {
		this.configuracao = configuracao;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (id == null) {
			return this == object;
		}
		if (!(object instanceof Chronos)) {
			return false;
		}
		return Objects.equals(this.id, ((Chronos) object).id);
	}

	@Override
	public int hashCode() {
		if (id == null) {
			return super.hashCode();
		}
		return Objects.hashCode(id);
	}


	public Chronos ativo(Boolean ativo) {
		this.ativo = ativo.booleanValue();
		return this;
	}
	public Chronos configuracao(KotaeConfiguracao configuracao) {
		this.configuracao = configuracao;
		return this;
	}
	public Chronos atendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
		return this;
	}
	public Chronos codigoResponsavel(String codigoResponsavel) {
		this.codigoResponsavel = codigoResponsavel;
		return this;
	}
	public Chronos dataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
		return this;
	}
	public Chronos solicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
		return this;
	}
}
