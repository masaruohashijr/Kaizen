package com.logus.kaizen.model.apoio.projeto;

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
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.logus.core.model.persistence.Assignable;
import com.logus.core.model.persistence.CollectionSynchronizer;
import com.logus.kaizen.model.TableNames;
import com.logus.kaizen.model.apoio.cliente.Cliente;
import com.logus.kaizen.model.apoio.tipomondai.TipoMondaiProjeto;
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
@Table(name = Projeto.TB_PROJETO)
public class Projeto implements Assignable<Projeto>, TableNames{

	@Id
	@TableGenerator(name = "seq_projeto", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_projeto")
	@Column(name = "seq_projeto")
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "seq_cliente", referencedColumnName = "seq_cliente", nullable = false)
	private Cliente cliente;

	@Column(name = "pref_mondai", length = 10, nullable = false, unique = true)
	@Size(min = 1, max = 10, message = KaizenTranslator.PROJETO_TAMANHO_MONDAI)
	@NotNull(message = KaizenTranslator.PROJETO_MONDAI_OBRIGATORIO)
	private String prefixoMondai;

	@Column(name = "seq_mondai")
	private Long idMondai;

	@Column(name = "pref_jira", length = 10, nullable = true, unique = true)
	@Size(min = 0, max = 10, message = KaizenTranslator.PROJETO_CHAVE_JIRA)
	@Null
	private String prefixoJira;

	@Column(name = "nom_projeto", length = 50, nullable = false)
	@Size(max = 50, min = 1, message = KaizenTranslator.PROJETO_TAMANHO_NOME)
	@NotNull(message = KaizenTranslator.PROJETO_NOME_OBRIGATORIO)
	private String nome;

	@Column(name = "dsc_projeto", length = 1200, nullable = true)
	@Size(min = 0, max = 1200, message = KaizenTranslator.PROJETO_TAMANHO_DESCRICAO)
	@Null
	private String descricao;

	@Column(name = "flg_ativo", nullable = false)
	@NotNull
	private boolean ativo = Boolean.TRUE;

	@Version
	private Long versao;

	@OneToMany(mappedBy = "projeto", targetEntity = Papel.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Size(min = 0)
	private Collection<Papel> papeis = new ArrayList<Papel>();

	@OneToMany(mappedBy = "projeto", targetEntity = TipoMondaiProjeto.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Size(min = 0)
	private Collection<TipoMondaiProjeto> tiposMondaiProjeto = new ArrayList<TipoMondaiProjeto>();

	@OneToMany(mappedBy = "projeto", targetEntity = Solicitacao.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Size(min = 0)
	private Collection<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();



	public Projeto() {
		this.idMondai = (long) 0;
	}

	@Override
	public Projeto assignFrom(Projeto projeto) {
		this.id = projeto.getId();
		this.idMondai = projeto.getIdMondai();
		this.nome = projeto.getNome();
		this.cliente = projeto.getCliente();
		this.prefixoJira= projeto.getPrefixoJira();
		this.prefixoMondai = projeto.getPrefixoMondai();
		this.descricao = projeto.getDescricao();
		this.ativo = projeto.isAtivo();
		this.versao = projeto.versao;
		CollectionSynchronizer.synchronize(projeto.papeis, this.papeis, papel -> papel.setProjeto(this));
		CollectionSynchronizer.synchronize(projeto.tiposMondaiProjeto, this.tiposMondaiProjeto, tipoMondaiProjeto -> tipoMondaiProjeto.setProjeto(this));
		CollectionSynchronizer.synchronize(projeto.solicitacoes, this.solicitacoes, solicitacao -> solicitacao.setProjeto(this));
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
		if (!(object instanceof Projeto)) {
			return false;
		}
		return Objects.equals(this.id, ((Projeto) object).id);
	}

	@Override
	public int hashCode() {
		if (id == null) {
			return super.hashCode();
		}
		return Objects.hashCode(id);
	}

	@Override
	public String toString() {
		return "" + getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public String getPrefixoMondai() {
		return prefixoMondai;
	}

	public void setPrefixoMondai(String prefixoMondai) {
		this.prefixoMondai = prefixoMondai;
	}

	public String getPrefixoJira() {
		return prefixoJira;
	}

	public void setPrefixoJira(String prefixoJira) {
		this.prefixoJira = prefixoJira;
	}

	public Collection<Solicitacao> getSolicitacoes() {
		return solicitacoes;
	}

	public void setSolicitacoes(Collection<Solicitacao> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}

	public void setTiposMondaiProjeto(Collection<TipoMondaiProjeto> tiposMondaiProjeto) {
		this.tiposMondaiProjeto = tiposMondaiProjeto;
	}

	public Collection<Papel> getPapeis() {
		return papeis;
	}

	public void setPapeis(Collection<Papel> papeis) {
		this.papeis = papeis;
	}

	public Collection<TipoMondaiProjeto> getTiposMondaiProjeto() {
		return tiposMondaiProjeto;
	}

	public void setTiposMondai(Collection<TipoMondaiProjeto> tiposMondaiProjeto) {
		this.tiposMondaiProjeto = tiposMondaiProjeto;
	}

	public Long getIdMondai() {
		return idMondai;
	}

	public void setIdMondai(Long idMondai) {
		this.idMondai = idMondai;
	}
}
