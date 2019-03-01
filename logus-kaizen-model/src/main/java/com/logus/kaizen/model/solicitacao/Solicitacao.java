/**
 *
 */
package com.logus.kaizen.model.solicitacao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

import com.logus.kaizen.model.apoio.biblioteca.Biblioteca;
import com.logus.kaizen.model.apoio.produto.Produto;
import com.logus.kaizen.model.plano.Plano;
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
@Table(name = Solicitacao.TABLE_SOLICITACAO)
public class Solicitacao implements Assignable<Solicitacao> {

	public static final String TABLE_SOLICITACAO = "KZ_SOLICITACAO";

	@Id
	@TableGenerator(name = "seq_solicitacao", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_solicitacao")
	@Column(name = "seq_solicitacao")
	private Long id;

	@Column(name = "issue", length = 10, nullable = false)
	@Size(min = 1, max = 10, message = KaizenTranslator.SOLICITACAO_TAMANHO_ISSUE)
	@NotNull(message = KaizenTranslator.SOLICITACAO_ISSUE_OBRIGATORIO)
	private String issue;

	@Column(name = "tit_issue", length = 100, nullable = false)
	@Size(min = 1, max = 100, message = KaizenTranslator.SOLICITACAO_TAMANHO_TITULO_ISSUE)
	@NotNull(message = KaizenTranslator.SOLICITACAO_TITULO_ISSUE_OBRIGATORIO)
	private String tituloIssue;

	@Column(name = "nom_solicitante", length = 100, nullable = false)
	@Size(min = 1, max = 100, message = KaizenTranslator.SOLICITACAO_NOME_SOLICITANTE_TAMANHO)
	@NotNull(message = KaizenTranslator.SOLICITACAO_NOME_SOLICITANTE_OBRIGATORIO)
	private String nomeSolicitante;

	@Column(name = "dat_commit", nullable = true)
	@Temporal(TemporalType.DATE)
	@NotNull(message = KaizenTranslator.SOLICITACAO_DATA_COMMIT_OBRIGATORIA)
	private Date dataCommit;

	@Column(name = "dat_solicitacao", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataSolicitacao;

	@Column(name = "flg_ativo", nullable = false)
	private boolean ativo = Boolean.TRUE;

	/**
	 * Produto.
	 */
	@ManyToOne
	@JoinColumn(name = "seq_produto", referencedColumnName = "seq_produto", nullable = false)
	@NotNull(message = KaizenTranslator.SOLICITACAO_CLIENTE_OBRIGATORIO)
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

	/**
	 * Bibliotecas
	 */
	@ManyToMany
	@JoinTable(name = "kz_solicitacao_biblioteca", joinColumns = @JoinColumn(name = "seq_solicitacao"),
	inverseJoinColumns = @JoinColumn(name = "seq_biblioteca"))
	private Collection<Biblioteca> bibliotecas = new HashSet<>();

	@ManyToOne(optional = true)
	@JoinColumn(name = "seq_plano", referencedColumnName = "seq_plano", nullable = true)
	private Plano plano;

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Solicitacao() {
	}

	public Solicitacao(Solicitacao solicitacao) {
		assignFrom(solicitacao);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.Assignable#assignFrom(java.lang.Object)
	 */
	@Override
	public Solicitacao assignFrom(Solicitacao object) {
		this.id = object.id;
		this.issue = object.issue;
		this.tituloIssue = object.tituloIssue;
		this.dataSolicitacao = object.dataSolicitacao;
		this.dataCommit = object.dataCommit;
		this.produto = object.produto;
		this.ativo = object.ativo;
		this.nomeSolicitante = object.nomeSolicitante;
		this.versao = object.versao;
		this.repositorio = object.repositorio;
		this.plano = object.plano;
		this.bibliotecas = object.bibliotecas;
		CollectionSynchronizer.synchronize(object.itensSolicitacao, this.itensSolicitacao,
				item -> item.setSolicitacao(this));
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

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getTituloIssue() {
		return tituloIssue;
	}

	public void setTituloIssue(String tituloIssue) {
		this.tituloIssue = tituloIssue;
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

	public Date getDataCommit() {
		return this.dataCommit;
	}

	public void setDataCommit(Date dataCommit) {
		this.dataCommit = dataCommit;
	}

	public String getStrDataSolicitacao() {
		return sdf.format(dataSolicitacao);
	}

	public Date getDataSolicitacao() {
		return this.dataSolicitacao;
	}

	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public String getStrDataCommit() {
		return sdf.format(dataCommit);
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
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
		return issue + " " + tituloIssue;
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


}
