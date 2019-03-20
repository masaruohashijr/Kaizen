package com.logus.kaizen.model.apoio.transicao;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.logus.core.model.persistence.Assignable;
import com.logus.core.model.persistence.CollectionSynchronizer;
import com.logus.kaizen.model.TableNames;
import com.logus.kaizen.model.apoio.processo.Passo;
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
@Table(name = Transicao.TB_TRANSICAO)
public class Transicao implements Assignable<Transicao>, TableNames {

	@Id
	@TableGenerator(name = "seq_transicao", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_transicao")
	@Column(name = "seq_transicao")
	private Long id;

	@Column(name = "nom_transicao", length = 50, nullable = false)
	@Size(max = 50, min = 1, message = KaizenTranslator.TRANSICAO_TAMANHO_NOME)
	@NotNull(message = KaizenTranslator.TRANSICAO_NOME_OBRIGATORIO)
	private String nome;

	@Column(name = "dsc_transicao", length = 1200, nullable = true)
	@Size(min = 0, max = 1200, message = KaizenTranslator.TRANSICAO_TAMANHO_DESCRICAO)
	@Null
	private String descricao;

	@OneToMany(mappedBy = "transicao", targetEntity = Passo.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Size(min = 0)
	private Collection<Passo> passos = new ArrayList<Passo>();

	@Column(name = "flg_ativo", nullable = false)
	@NotNull
	private boolean ativo = Boolean.TRUE;

	@Override
	public Transicao assignFrom(Transicao transicao) {
		this.id = transicao.getId();
		this.nome = transicao.getNome();
		this.descricao = transicao.getDescricao();
		CollectionSynchronizer.synchronize(transicao.getPassos(), this.passos, passo -> passo.setTransicao(this));
		this.ativo = transicao.isAtivo();
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
		if (!(object instanceof Transicao)) {
			return false;
		}
		return Objects.equals(this.id, ((Transicao) object).id);
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

	public Collection<Passo> getPassos() {
		return passos;
	}

	public void setPassos(Collection<Passo> passos) {
		this.passos = passos;
	}

}
