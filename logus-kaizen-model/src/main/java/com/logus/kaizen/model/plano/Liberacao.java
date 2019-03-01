package com.logus.kaizen.model.plano;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
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

import com.logus.kaizen.model.apoio.ambiente.Ambiente;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.core.model.persistence.Assignable;

/**
 *
 * @author Masaru Ohashi Júnior
 * @version 1.0.0
 * @since 1 de mar de 2019
 */
@Entity
@Table(name = Liberacao.TABLE_NAME)
public class Liberacao implements Assignable<Liberacao> {

	/**
	 * Nome da tabela onde esta entidade será persistida.
	 */
	public static final String TABLE_NAME = "KZ_LIBERACAO";

	@ManyToOne(optional = false)
	@JoinColumn(name = "seq_ambiente", referencedColumnName = "seq_ambiente", nullable = false)
	@NotNull(message = KaizenTranslator.LIBERACAO_AMBIENTE_OBRIGATORIO)
	private Ambiente ambiente;

	@ManyToOne(optional = false)
	@JoinColumn(name = "seq_plano", referencedColumnName = "seq_plano", nullable = false)
	@NotNull(message = KaizenTranslator.LIBERACAO_PLANO_OBRIGATORIO)
	private Plano plano;

	/**
	 * Identificador único gerado automaticamente pelo Sistema.
	 */
	@Id
	@TableGenerator(name = "seq_liberacao", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_liberacao")
	@Column(name = "seq_liberacao")
	private Long id;

	@Column(name = "dat_liberacao", nullable = true)
	@Temporal(TemporalType.DATE)
	@Null
	private Date dataLiberacao;

	@Column(name = "nom_responsavel_gcm", nullable = true, length = 100)
	@Size(min = 0, max = 100, message = KaizenTranslator.LIBERACAO_RESPONSAVEL_TAMANHO)
	@Null
	private String responsavel;

	@Column(name = "ref_liberacao", nullable = true, length = 50)
	@Size(min = 0, max = 50, message = KaizenTranslator.LIBERACAO_REFERENCIA_TAMANHO)
	@Null
	private String referencia;

	@Column(name = "versao", nullable = true, length = 50)
	@Size(min = 0, max = 50, message = KaizenTranslator.LIBERACAO_VERSAO_TAMANHO)
	@Null
	private String versao;

	/**
	 * Construtor.
	 */
	public Liberacao() {
		super();
	}

	/**
	 * Construtor.
	 *
	 * @param object objeto cujas propriedades inicializarão o novo objeto
	 *               construído.
	 */
	public Liberacao(Liberacao object) {
		super();
		assignFrom(object);
	}

	@Override
	public Liberacao assignFrom(Liberacao object) {
		this.id = object.id;
		this.plano = object.plano;
		this.referencia = object.referencia;
		this.versao = object.versao;
		this.ambiente = object.ambiente;
		this.responsavel = object.responsavel;
		this.dataLiberacao = object.dataLiberacao;
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
		if (!(object instanceof Liberacao)) {
			return false;
		}
		return Objects.equals(this.id, ((Liberacao) object).id);
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

	public Long getId() {
		return id;
	}

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	public Date getDataLiberacao() {
		return dataLiberacao;
	}

	public void setDataLiberacao(Date dataLiberacao) {
		this.dataLiberacao = dataLiberacao;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	@Override
	public String toString() {
		if (null != ambiente) {
			return String.format("%s - %s", ambiente.getCliente(), ambiente);
		} else {
			return String.format("%s", dataLiberacao);
		}
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

}
