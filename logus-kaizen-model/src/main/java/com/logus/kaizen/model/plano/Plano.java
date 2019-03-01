/**
 *
 */
package com.logus.kaizen.model.plano;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.logus.kaizen.model.apoio.atendimento.Atendimento;
import com.logus.kaizen.model.solicitacao.Solicitacao;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.core.model.persistence.Assignable;
import com.logus.core.model.persistence.CollectionSynchronizer;
/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
@Entity
@Table(name = Plano.TABLE_PLANO)
public class Plano implements Assignable<Plano> {

	public static final String TABLE_PLANO = "KZ_PLANO";

	/**
	 * Id
	 */
	@Id
	@TableGenerator(name = "seq_plano", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_plano")
	@Column(name = "seq_plano")
	private Long id;

	/**
	 * Referência
	 */
	@Column(name = "ref_plano", nullable = false, length = 50)
	@Size(min = 1, max = 50)
	@NotNull
	private String referencia;

	/**
	 * Versão
	 */
	@Column(name = "versao", length = 50, nullable = true)
	@Size(min = 0, max = 50, message = KaizenTranslator.PLANO_VERSAO_TAMANHO)
	@Null
	private String versao;

	/**
	 * Descrição
	 */
	@Column(name = "dsc_plano", length = 1200, nullable = true)
	@Size(min = 0, max = 1200, message = KaizenTranslator.PLANO_TAMANHO_DESCRICAO)
	@Null
	private String descricao;

	/**
	 * GCM
	 */
	@Column(name = "nom_gcm", length = 100, nullable = false)
	@Size(min = 1, max = 100, message = KaizenTranslator.PLANO_NOME_GCM_TAMANHO)
	@NotNull(message = KaizenTranslator.PLANO_NOME_GCM_OBRIGATORIO)
	private String nomGcm;

	/**
	 * Data Planejamento
	 */
	@Column(name = "dat_planejamento", nullable = false)
	@Temporal(TemporalType.DATE)
	@NotNull(message = KaizenTranslator.PLANO_DATA_PLANEJAMENTO_OBRIGATORIA)
	private Date dataPlanejamento;

	/**
	 * Data de Integração
	 */
	@Column(name = "dat_integracao", nullable = true)
	@Temporal(TemporalType.DATE)
	@Null
	private Date dataIntegracao;

	/**
	 * Bug fixes
	 */
	@Column(name = "txt_bug_fixes", nullable = true, length = 6000)
	@Size(min = 0, max = 6000)
	@Null
	private String bugFixes;

	/**
	 * Release Notes
	 */
	@Column(name = "txt_release_notes", nullable = true, length = 6000)
	@Size(min = 0, max = 6000)
	@Null
	private String releaseNotes;

	/**
	 * Ativo
	 */
	@Column(name = "flg_ativo", nullable = false)
	private boolean ativo = Boolean.TRUE;

	/**
	 * Atendimento
	 */
	@ManyToOne(optional = true)
	@JoinColumn(name = "seq_atendimento", referencedColumnName = "seq_atendimento", nullable = true)
	@Null
	private Atendimento atendimento;

	/**
	 * Solicitações
	 */
	@ManyToMany
	@JoinTable(name = "kz_plano_solicitacao", joinColumns = @JoinColumn(name = "seq_plano"),
	inverseJoinColumns = @JoinColumn(name = "seq_solicitacao"))
	private Collection<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();

	/**
	 * Liberações
	 */
	@OneToMany(mappedBy = "plano", targetEntity = Liberacao.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Size(min = 0)
	private Collection<Liberacao> liberacoes = new ArrayList<Liberacao>();

	/**
	 * SimpleDateFormat
	 */
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Plano() {
	}

	public Plano(Plano plano) {
		assignFrom(plano);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.Assignable#assignFrom(java.lang.Object)
	 */
	@Override
	public Plano assignFrom(Plano plano) {
		this.id = plano.id;
		this.referencia = plano.referencia;
		this.versao = plano.versao;
		this.descricao = plano.descricao;
		this.ativo = plano.ativo;
		this.atendimento = plano.atendimento;
		this.dataIntegracao = plano.dataIntegracao;
		this.dataPlanejamento = plano.dataPlanejamento;
		this.nomGcm =plano.nomGcm;
		this.solicitacoes = plano.solicitacoes;
		this.releaseNotes = plano.releaseNotes;
		this.bugFixes = plano.bugFixes;
		CollectionSynchronizer.synchronize(plano.liberacoes, this.liberacoes,
				liberacao -> liberacao.setPlano(this));
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

	public String getNomGcm() {
		return nomGcm;
	}

	public void setNomGcm(String nomGcm) {
		this.nomGcm = nomGcm;
	}

	public Date getDataPlanejamento() {
		return dataPlanejamento;
	}

	public String getStrDataPlanejamento() {
		return sdf.format(dataPlanejamento);
	}

	public void setDataPlanejamento(Date dataPlanejamento) {
		this.dataPlanejamento = dataPlanejamento;
	}

	public Date getDataIntegracao() {
		return dataIntegracao;
	}

	public String getStrDataIntegracao() {
		return sdf.format(dataIntegracao);
	}

	public void setDataIntegracao(Date dataIntegracao) {
		this.dataIntegracao = dataIntegracao;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getBugFixes() {
		return bugFixes;
	}

	public void setBugFixes(String bugFixes) {
		this.bugFixes = bugFixes;
	}

	public String getReleaseNotes() {
		return releaseNotes;
	}

	public void setReleaseNotes(String releaseNotes) {
		this.releaseNotes = releaseNotes;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public Collection<Solicitacao> getSolicitacoes() {
		return solicitacoes;
	}

	public void setSolicitacoes(Collection<Solicitacao> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}

	public Collection<Liberacao> getLiberacoes() {
		return liberacoes;
	}

	public void setLiberacoes(Collection<Liberacao> liberacoes) {
		this.liberacoes = liberacoes;
	}

	@Override
	public String toString() {
		return "Ref.: "+referencia + " Versão: " + versao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
