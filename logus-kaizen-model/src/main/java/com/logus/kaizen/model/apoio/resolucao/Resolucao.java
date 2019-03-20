package com.logus.kaizen.model.apoio.resolucao;

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
@Table(name = Resolucao.TB_RESOLUCAO)
public class Resolucao implements Assignable<Resolucao>, TableNames{

	@Id
	@TableGenerator(name = "seq_resolucao", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_resolucao")
	@Column(name = "seq_resolucao")
	private Long id;

	@Column(name = "nom_resolucao", length = 50, nullable = false)
	@Size(max = 50, min = 1, message = KaizenTranslator.RESOLUCAO_TAMANHO_NOME)
	@NotNull(message = KaizenTranslator.RESOLUCAO_NOME_OBRIGATORIO)
	private String nome;

	@Column(name = "dsc_resolucao", length = 1200, nullable = true)
	@Size(min = 0, max = 1200, message = KaizenTranslator.RESOLUCAO_TAMANHO_DESCRICAO)
	@Null
	private String descricao;

	@OneToMany(mappedBy = "resolucao", targetEntity = Passo.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Size(min = 0)
	private Collection<Passo> passos = new ArrayList<Passo>();

	@Column(name = "flg_ativo", nullable = false)
	@NotNull
	private boolean ativo = Boolean.TRUE;

	@Override
	public Resolucao assignFrom(Resolucao resolucao) {
		this.id = resolucao.getId();
		this.nome = resolucao.getNome();
		this.descricao = resolucao.getDescricao();
		CollectionSynchronizer.synchronize(resolucao.getPassos(), this.passos, passo -> passo.setResolucao(this));
		this.ativo = resolucao.isAtivo();
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
		if (!(object instanceof Resolucao)) {
			return false;
		}
		return Objects.equals(this.id, ((Resolucao) object).id);
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
		return getNome();
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
