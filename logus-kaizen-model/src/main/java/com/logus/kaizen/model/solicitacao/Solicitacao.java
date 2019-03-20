/**
 *
 */
package com.logus.kaizen.model.solicitacao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Enumerated;
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

import com.logus.core.model.persistence.Assignable;
import com.logus.core.model.persistence.CollectionSynchronizer;
import com.logus.kaizen.model.TableNames;
import com.logus.kaizen.model.apoio.atendimento.Atendimento;
import com.logus.kaizen.model.apoio.biblioteca.Biblioteca;
import com.logus.kaizen.model.apoio.produto.Produto;
import com.logus.kaizen.model.apoio.projeto.Projeto;
import com.logus.kaizen.model.apoio.resolucao.Resolucao;
import com.logus.kaizen.model.apoio.tipomondai.TipoMondai;
import com.logus.kaizen.model.chronos.Chronos;
import com.logus.kaizen.model.kotae.plano.Plano;
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
@Table(name = Solicitacao.TB_SOLICITACAO)
public class Solicitacao implements Assignable<Solicitacao>, TableNames {

	boolean chronosAtivo = false;

	@Id
	@TableGenerator(name = "seq_solicitacao", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_solicitacao")
	@Column(name = "seq_solicitacao")
	private Long id;

	@Column(name = "seq_mondai")
	private Long idMondai;

	@ManyToOne
	@JoinColumn(name = "seq_projeto", referencedColumnName = "seq_projeto", nullable = true)
	@Null
	private Projeto projeto;

	@ManyToOne
	@JoinColumn(name = "seq_atendimento", referencedColumnName = "seq_atendimento", nullable = true)
	@Null
	private Atendimento atendimento;

	@ManyToOne
	@JoinColumn(name = "seq_resolucao", referencedColumnName = "seq_resolucao", nullable = true)
	@Null
	private Resolucao resolucao;

	@ManyToOne
	@JoinColumn(name = "seq_tipo_mondai", referencedColumnName = "seq_tipo_mondai", nullable = false)
	@NotNull(message = KaizenTranslator.SOLICITACAO_TIPO_MONDAI_OBRIGATORIO)
	private TipoMondai tipoMondai;

	@Column(name = "chave_jira", length = 10, nullable = true)
	@Size(min = 0, max = 10, message = KaizenTranslator.SOLICITACAO_TAMANHO_MONDAI)
	@Null
	private String chaveJira;

	@Column(name = "tit_mondai", length = 120, nullable = false)
	@Size(min = 1, max = 120, message = KaizenTranslator.SOLICITACAO_TAMANHO_TITULO_MONDAI)
	@NotNull(message = KaizenTranslator.SOLICITACAO_TITULO_MONDAI_OBRIGATORIO)
	private String tituloMondai;

	@Column(name = "dsc_solicitacao", length = 4000, nullable = true)
	@Size(min = 0, max = 4000, message = KaizenTranslator.SOLICITACAO_TAMANHO_DESCRICAO)
	@Null
	private String descricao;

	@Column(name = "cod_solicitante", length = 100, nullable = false)
	@Size(min = 1, max = 100, message = KaizenTranslator.SOLICITACAO_CODIGO_SOLICITANTE_TAMANHO)
	@NotNull(message = KaizenTranslator.SOLICITACAO_CODIGO_SOLICITANTE_OBRIGATORIO)
	private String codigoSolicitante;

	@Column(name = "cod_responsavel_atual", length = 100, nullable = true)
	@Size(min = 0, max = 100, message = KaizenTranslator.SOLICITACAO_CODIGO_RESPONSAVEL_TAMANHO)
	@Null
	private String codigoResponsavelAtual;

	@Column(name = "dat_commit", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Null
	private Date dataCommit;

	@Column(name = "dat_solicitacao", nullable = true)
	@Null
	private Date dataSolicitacao;

	@Column(name = "dat_ficar_pronto", nullable = true)
	@Null
	private Date dataFicarPronto;

	@Column(name = "flg_ativo", nullable = false)
	private boolean ativo = Boolean.TRUE;

	/**
	 * Produto.
	 */
	@ManyToOne
	@JoinColumn(name = "seq_produto", referencedColumnName = "seq_produto", nullable = true)
	@Null(message = KaizenTranslator.SOLICITACAO_PRODUTO_OBRIGATORIO)
	private Produto produto;

	/**
	 * Versão
	 */
	@Column(name = "val_versao", length = 50, nullable = true)
	@Size(min = 0, max = 50, message = KaizenTranslator.SOLICITACAO_VERSAO_TAMANHO)
	@Null
	private String versao;

	/**
	 * Repositório
	 */
	@Column(name = "nom_repositorio", length = 50, nullable = true)
	@Enumerated
	@Size(min = 0, max = 50, message = KaizenTranslator.SOLICITACAO_REPOSITORIO_TAMANHO)
	@Null
	private RepositorioEnum repositorio;

	@OneToMany(mappedBy = "solicitacao", targetEntity = ItemSolicitacao.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Size(min = 0)
	private Collection<ItemSolicitacao> itensSolicitacao = new ArrayList<ItemSolicitacao>();

	@OneToMany(mappedBy = "solicitacao", targetEntity = ItemAtendimento.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Size(min = 0)
	private Collection<ItemAtendimento> itensAtendimento = new ArrayList<ItemAtendimento>();

	@OneToMany(mappedBy = "solicitacao", targetEntity = Chronos.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Size(min = 0)
	private Collection<Chronos> togurus = new ArrayList<Chronos>();

	/**
	 * Bibliotecas
	 */
	@ManyToMany
	@JoinTable(name = TableNames.TB_SOLICITACAO_BIBLIOTECA, joinColumns = @JoinColumn(name = "seq_solicitacao", referencedColumnName = "seq_solicitacao", nullable = false), inverseJoinColumns = @JoinColumn(name = "seq_biblioteca"))
	private Collection<Biblioteca> bibliotecas = new HashSet<>();

	@ManyToOne(optional = true)
	@JoinColumn(name = "seq_plano", referencedColumnName = "seq_plano", nullable = true)
	private Plano plano;

	/**
	 * Comentario
	 */
	@OneToMany(mappedBy = "solicitacao", targetEntity = Comentario.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Size(min = 0)
	private Collection<Comentario> comentarios = new ArrayList<Comentario>();

	/**
	 * Solicitações
	 */
	@JoinTable(name = TableNames.TB_SOLICITACAO_DEPENDENCIA, joinColumns = {
			@JoinColumn(name = "seq_solicitacao", referencedColumnName = "seq_solicitacao", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "seq_dependencia", referencedColumnName = "seq_solicitacao", nullable = false) })
	@ManyToMany
	private Collection<Solicitacao> dependencias = new ArrayList<Solicitacao>();

	@ManyToOne(optional = true)
	@JoinColumn(name = "seq_dependencia", referencedColumnName = "seq_solicitacao", nullable = true)
	private Solicitacao dependencia;

	public Solicitacao() {
	}

	public Solicitacao(Solicitacao solicitacao) {
		assignFrom(solicitacao);
	}

	private String chaveMondai;

	public String getChaveMondai() {
		if (null != projeto) {
			chaveMondai = projeto.getPrefixoMondai() + "-" + idMondai;
		}
		return chaveMondai;
	}

	public void setChaveMondai(String chaveMondai) {
		this.chaveMondai = chaveMondai;
	}

	public String getChaveJira() {
		return chaveJira;
	}

	public void setChaveJira(String chaveJira) {
		this.chaveJira = chaveJira;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.Assignable#assignFrom(java.lang.Object)
	 */
	@Override
	public Solicitacao assignFrom(Solicitacao object) {
		this.id = object.id;
		this.tituloMondai = object.tituloMondai;
		this.idMondai = object.idMondai;
		this.descricao = object.descricao;
		this.dataSolicitacao = object.dataSolicitacao;
		this.dataCommit = object.dataCommit;
		this.dataFicarPronto = object.dataFicarPronto;
		this.produto = object.produto;
		this.projeto = object.projeto;
		this.tipoMondai = object.tipoMondai;
		this.atendimento = object.atendimento;
		this.resolucao = object.resolucao;
		this.ativo = object.ativo;
		this.codigoSolicitante = object.codigoSolicitante;
		this.codigoResponsavelAtual = object.codigoResponsavelAtual;
		this.versao = object.versao;
		this.repositorio = object.repositorio;
		this.plano = object.plano;
		this.chaveMondai = object.chaveMondai;
		CollectionSynchronizer.synchronize(object.bibliotecas, this.bibliotecas,
				biblioteca -> biblioteca.setSolicitacao(this));
		CollectionSynchronizer.synchronize(object.itensSolicitacao, this.itensSolicitacao,
				item -> item.setSolicitacao(this));
		CollectionSynchronizer.synchronize(object.itensAtendimento, this.itensAtendimento,
				item -> item.setSolicitacao(this));
		CollectionSynchronizer.synchronize(object.togurus, this.togurus, item -> item.setSolicitacao(this));
		CollectionSynchronizer.synchronize(object.comentarios, this.comentarios,
				comentario -> comentario.setSolicitacao(this));
		CollectionSynchronizer.synchronize(object.dependencias, this.dependencias,
				solicitacao -> solicitacao.setDependencia(this));

		chaveMondai = projeto.getPrefixoMondai() + "-" + idMondai;

		return this;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (id == null) {
			return this == object;
		}
		if (!(object instanceof Solicitacao)) {
			return false;
		}
		return Objects.equals(this.id, ((Solicitacao) object).id);
	}

	@Override
	public int hashCode() {
		if (id == null) {
			return super.hashCode();
		}
		return Objects.hashCode(id);
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

	public String getTituloMondai() {
		return tituloMondai;
	}

	public void setTituloMondai(String tituloMondai) {
		this.tituloMondai = tituloMondai;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Collection<ItemSolicitacao> getItensSolicitacao() {
		return itensSolicitacao;
	}

	public void setItensSolicitacao(Collection<ItemSolicitacao> itensSolicitacao) {
		this.itensSolicitacao = itensSolicitacao;
	}

	public Collection<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Collection<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Date getDataCommit() {
		return this.dataCommit;
	}

	public void setDataCommit(Date dataCommit) {
		this.dataCommit = dataCommit;
	}

	public Date getDataSolicitacao() {
		return this.dataSolicitacao;
	}

	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public String getCodigoSolicitante() {
		return codigoSolicitante;
	}

	public void setCodigoSolicitante(String nomeSolicitante) {
		this.codigoSolicitante = nomeSolicitante;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public RepositorioEnum getRepositorio() {
		return repositorio;
	}

	public void setRepositorio(RepositorioEnum repositorio) {
		this.repositorio = repositorio;
	}

	@Override
	public String toString() {
		return projeto.getPrefixoMondai() + "-" + idMondai + " " + tituloMondai;
	}

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	public Collection<Biblioteca> getBibliotecas() {
		return bibliotecas;
	}

	public void setBibliotecas(Collection<Biblioteca> bibliotecas) {
		this.bibliotecas = bibliotecas;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataFicarPronto() {
		return dataFicarPronto;
	}

	public void setDataFicarPronto(Date dataFicarPronto) {
		this.dataFicarPronto = dataFicarPronto;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public TipoMondai getTipoMondai() {
		return tipoMondai;
	}

	public void setTipoMondai(TipoMondai tipoMondai) {
		this.tipoMondai = tipoMondai;
	}

	public Collection<ItemAtendimento> getItensAtendimento() {
		return itensAtendimento;
	}

	public void setItensAtendimento(Collection<ItemAtendimento> itensAtendimento) {
		this.itensAtendimento = itensAtendimento;
	}

	public Resolucao getResolucao() {
		return resolucao;
	}

	public void setResolucao(Resolucao resolucao) {
		this.resolucao = resolucao;
	}

	public String getCodigoResponsavelAtual() {
		return codigoResponsavelAtual;
	}

	public void setCodigoResponsavelAtual(String codigoResponsavelAtual) {
		this.codigoResponsavelAtual = codigoResponsavelAtual;
	}

	public Long getIdMondai() {
		return idMondai;
	}

	public void setIdMondai(Long idMondai) {
		this.idMondai = idMondai;
	}

	public Collection<Chronos> getTogurus() {
		return togurus;
	}

	public void setTogurus(Collection<Chronos> togurus) {
		this.togurus = togurus;
	}

	public Collection<Solicitacao> getDependencias() {
		return dependencias;
	}

	public void setDependencias(Collection<Solicitacao> dependencias) {
		this.dependencias = dependencias;
	}

	public Solicitacao getDependencia() {
		return dependencia;
	}

	public void setDependencia(Solicitacao dependencia) {
		this.dependencia = dependencia;
	}

	public boolean isChronosAtivo() {
		return chronosAtivo;
	}

	public void setChronosAtivo(boolean chronosAtivo) {
		this.chronosAtivo = chronosAtivo;
	}

}
