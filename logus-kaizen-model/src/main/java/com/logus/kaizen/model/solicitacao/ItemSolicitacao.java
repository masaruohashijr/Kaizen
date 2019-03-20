package com.logus.kaizen.model.solicitacao;

import java.text.SimpleDateFormat;
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
import com.logus.kaizen.model.TableNames;
import com.logus.kaizen.model.apoio.ambiente.Ambiente;
import com.logus.kaizen.model.apoio.atendimento.Atendimento;
import com.logus.kaizen.model.apoio.urgencia.Urgencia;
import com.logus.kaizen.model.kotae.plano.Plano;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.kaizen.model.util.YokaiListener;

/**
 * Para uma matriz de rateio estática, representa o percentual a ser alocado a
 * uma dada matriz.
 *
 * @author Masaru Ohashi Júnior
 * @version 1.0.0
 * @since 1 de mar de 2019
 *
 */
@Entity
@EntityListeners(YokaiListener.class)
@Table(name = ItemSolicitacao.TB_ITEM_SOLICITACAO)
public class ItemSolicitacao implements Assignable<ItemSolicitacao>, TableNames {

	@ManyToOne(optional = false)
	@JoinColumn(name = "seq_ambiente", referencedColumnName = "seq_ambiente", nullable = false)
	@NotNull(message = KaizenTranslator.ITEM_SOLICITACAO_AMBIENTE_OBRIGATORIO)
	private Ambiente ambiente;

	@ManyToOne(optional = true)
	@JoinColumn(name = "seq_urgencia", referencedColumnName = "seq_urgencia", nullable = true)
	@Null
	private Urgencia urgencia;

	@ManyToOne(optional = true)
	@JoinColumn(name = "seq_atendimento", referencedColumnName = "seq_atendimento", nullable = true)
	@Null
	private Atendimento atendimento;

//	@ManyToOne(optional = true)
//	@JoinColumn(name = "seq_plano", referencedColumnName = "seq_plano", nullable = true)
//	private Plano plano;

	/**
	 * Identificador único gerado automaticamente pelo Sistema.
	 */
	@Id
	@TableGenerator(name = "seq_item_solicitacao", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_item_solicitacao")
	@Column(name = "seq_item_solicitacao")
	private Long id;

	@Column(name = "dat_ultimo_atendimento", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Null
	private Date dataUltimoAtendimento;

	@Column(name = "cod_responsavel", nullable = true, length = 100)
	@Size(min = 0, max = 50, message = KaizenTranslator.ITEM_SOLICITACAO_RESPONSAVEL_TAMANHO)
	@Null
	private String codigoResponsavel;

	/**
	 * Solicitação à qual este item pertence.
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "seq_solicitacao", referencedColumnName = "seq_solicitacao", nullable = false)
	@NotNull(message = KaizenTranslator.SOLICITACAO_OBRIGATORIA)
	private Solicitacao solicitacao;

	@Column(name = "solicitado", nullable = false)
	private boolean solicitado = Boolean.FALSE;

	/**
	 * Construtor.
	 */
	public ItemSolicitacao() {
		super();
	}

	/**
	 * Construtor.
	 *
	 * @param object objeto cujas propriedades inicializarão o novo objeto
	 *               construído.
	 */
	public ItemSolicitacao(ItemSolicitacao object) {
		super();
		assignFrom(object);
	}

	@Override
	public ItemSolicitacao assignFrom(ItemSolicitacao object) {
		this.id = object.id;
		this.codigoResponsavel = object.codigoResponsavel;
		this.ambiente = object.ambiente;
		this.urgencia = object.urgencia;
		this.atendimento = object.atendimento;
		this.solicitacao = object.solicitacao;
		this.dataUltimoAtendimento = object.dataUltimoAtendimento;
		this.solicitado = object.solicitado;
//		this.plano = object.getPlano();
		return this;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (id == null) {
			return this == object;
		}
		if (!(object instanceof ItemSolicitacao)) {
			return false;
		}
		return Objects.equals(this.id, ((ItemSolicitacao) object).id);
	}

	@Override
	public int hashCode() {
		if (id == null) {
			return super.hashCode();
		}
		return Objects.hashCode(id);
	}

	/**
	 * @param id atualiza {@link #id}.
	 */

	public void setId(Long id) {
		this.id = id;
	}

	public Ambiente getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(Ambiente ambiente) {
		this.ambiente = ambiente;
	}

	public Urgencia getUrgencia() {
		return urgencia;
	}

	public void setUrgencia(Urgencia urgencia) {
		this.urgencia = urgencia;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}

	public Long getId() {
		return id;
	}

	public Date getDataUltimoAtendimento() {
		return dataUltimoAtendimento;
	}

	public String getStrDataUltimoAtendimento() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String str = sdf.format(dataUltimoAtendimento);
		return str;
	}

	public void setDataUltimoAtendimento(Date dataUltimoAtendimento) {
		this.dataUltimoAtendimento = dataUltimoAtendimento;
	}

	@Override
	public String toString() {
		if(null != ambiente) {
			return String.format("%s : %s", ambiente.getCliente(), ambiente);
		} else {
			return String.format("%s", urgencia);
		}
	}

	public String getCodigoResponsavel() {
		return codigoResponsavel;
	}

	public void setCodigoResponsavel(String codigoResponsavel) {
		this.codigoResponsavel = codigoResponsavel;
	}

	public boolean isSolicitado() {
		return solicitado;
	}

	public void setSolicitado(boolean solicitado) {
		this.solicitado = solicitado;
	}

//	public Plano getPlano() {
//		return plano;
//	}
//
//	public void setPlano(Plano plano) {
//		this.plano = plano;
//	}



}
