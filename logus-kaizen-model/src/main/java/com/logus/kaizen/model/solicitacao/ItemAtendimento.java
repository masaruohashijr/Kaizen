package com.logus.kaizen.model.solicitacao;

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

import com.logus.core.model.persistence.Assignable;
import com.logus.kaizen.model.TableNames;
import com.logus.kaizen.model.apoio.atendimento.Atendimento;
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
@Table(name = ItemAtendimento.TB_ITEM_ATENDIMENTO)
public class ItemAtendimento implements Assignable<ItemAtendimento>, TableNames {

	/**
	 * Identificador único gerado automaticamente pelo Sistema.
	 */
	@Id
	@TableGenerator(name = "seq_item_atendimento", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_item_atendimento")
	@Column(name = "seq_item_atendimento")
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "seq_atendimento", referencedColumnName = "seq_atendimento", nullable = false)
	@NotNull(message = KaizenTranslator.ITEM_ATENDIMENTO_OBRIGATORIO)
	private Atendimento atendimento;

	@Column(name = "dat_inicio_vigencia", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date dataInicioVigencia;

	@Column(name = "dat_fim_vigencia", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Null
	private Date dataFimVigencia;

	/**
	 * Solicitação.
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "seq_solicitacao", referencedColumnName = "seq_solicitacao", nullable = false)
	@NotNull(message = KaizenTranslator.SOLICITACAO_OBRIGATORIA)
	private Solicitacao solicitacao;

	/**
	 * Construtor.
	 */
	public ItemAtendimento() {
		super();
	}

	/**
	 * Construtor.
	 *
	 * @param object objeto cujas propriedades inicializarão o novo objeto
	 *               construído.
	 */
	public ItemAtendimento(ItemAtendimento object) {
		super();
		assignFrom(object);
	}

	@Override
	public ItemAtendimento assignFrom(ItemAtendimento itemAtendimento) {
		this.id = itemAtendimento.id;
		this.atendimento = itemAtendimento.atendimento;
		this.solicitacao = itemAtendimento.solicitacao;
		this.dataInicioVigencia = itemAtendimento.dataInicioVigencia;
		this.dataFimVigencia = itemAtendimento.dataFimVigencia;
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
		if (!(object instanceof ItemAtendimento)) {
			return false;
		}
		return Objects.equals(this.id, ((ItemAtendimento) object).id);
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

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	@Override
	public String toString() {
		return String.format("%s", atendimento);
	}

	public Long getId() {
		return id;
	}

	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}

	public Date getDataInicioVigencia() {
		return dataInicioVigencia;
	}

	public void setDataInicioVigencia(Date dataInicioVigencia) {
		this.dataInicioVigencia = dataInicioVigencia;
	}

	public Date getDataFimVigencia() {
		return dataFimVigencia;
	}

	public void setDataFimVigencia(Date dataFimVigencia) {
		this.dataFimVigencia = dataFimVigencia;
	}

}
