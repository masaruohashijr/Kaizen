package com.logus.kaizen.model.processo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.logus.core.model.persistence.Assignable;
import com.logus.core.model.persistence.CollectionSynchronizer;
import com.logus.kaizen.model.apoio.tipomondai.TipoMondaiProjeto;
import com.logus.kaizen.model.translation.KaizenTranslator;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
@Entity
@Table(name = Processo.TABLE_NAME)
public class Processo implements Assignable<Processo> {

	public static final String TABLE_NAME = "KZ_PROCESSO";

	@Id
	@TableGenerator(name = "seq_processo", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_processo")
	@Column(name = "seq_processo")
	private Long id;

	@Column(name = "nom_processo", length = 50, nullable = false)
	@Size(max = 50, min = 1, message = KaizenTranslator.PROCESSO_TAMANHO_NOME)
	@NotNull(message = KaizenTranslator.PROCESSO_NOME_OBRIGATORIO)
	private String nome;

	@Column(name = "dsc_processo", length = 1200, nullable = true)
	@Size(min = 0, max = 1200, message = KaizenTranslator.PROCESSO_TAMANHO_DESCRICAO)
	@Null
	private String descricao;

	@OneToMany(mappedBy = "processo", targetEntity = Passo.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Size(min = 0)
	private Collection<Passo> passos = new ArrayList<Passo>();

	@OneToMany(mappedBy = "processo", targetEntity = TipoMondaiProjeto.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Size(min = 0)
	private Collection<TipoMondaiProjeto> tiposMondaiProjeto = new ArrayList<TipoMondaiProjeto>();

	@Column(name = "flg_ativo", nullable = false)
	@NotNull
	private boolean ativo = Boolean.TRUE;

	/**
	 * Construtor.
	 */
	public Processo() {
		super();
	}

	/**
	 * Construtor.
	 *
	 * @param object objeto cujas propriedades inicializarão o novo objeto
	 *               construído.
	 */
	public Processo(Processo object) {
		super();
		assignFrom(object);
	}

	@Override
	public Processo assignFrom(Processo processo) {
		this.id = processo.getId();
		this.nome = processo.getNome();
		this.descricao = processo.getDescricao();
		CollectionSynchronizer.synchronize(processo.passos, this.passos, passo -> passo.setProcesso(this));
		CollectionSynchronizer.synchronize(processo.tiposMondaiProjeto, this.tiposMondaiProjeto, tiposMondaiProjeto -> tiposMondaiProjeto.setProcesso(this));
		this.ativo = processo.isAtivo();
		return this;
	}

	@Override
	public int hashCode() {
		if (id == null) {
			return super.hashCode();
		}
		return Objects.hashCode(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (id == null) {
			return this == obj;
		}
		if (!(obj instanceof Processo)) {
			return false;
		}
		return Objects.equals(this.id, ((Processo) obj).id);
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

	public Collection<Passo> getPassos() {
		return passos;
	}

	public void setPassos(Collection<Passo> passos) {
		this.passos = passos;
	}

	public Collection<TipoMondaiProjeto> getTiposMondaiProjeto() {
		return tiposMondaiProjeto;
	}

	public void setTiposMondaiProjeto(Collection<TipoMondaiProjeto> tiposMondaiProjeto) {
		this.tiposMondaiProjeto = tiposMondaiProjeto;
	}



}
