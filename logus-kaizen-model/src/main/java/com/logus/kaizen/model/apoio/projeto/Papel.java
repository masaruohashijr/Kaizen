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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.logus.core.model.persistence.Assignable;
import com.logus.core.model.persistence.CollectionSynchronizer;
import com.logus.kaizen.model.TableNames;
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
@Table(name = Papel.TB_PAPEL)
public class Papel implements Assignable<Papel>, TableNames{

	@Id
	@TableGenerator(name = "seq_papel", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_papel")
	@Column(name = "seq_papel")
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "seq_projeto", referencedColumnName = "seq_projeto", nullable = false)
	private Projeto projeto;

	@Column(name = "nom_papel", length = 50, nullable = false)
	@Size(max = 50, min = 1, message = KaizenTranslator.PROJETO_PAPEL_TAMANHO_NOME)
	@NotNull(message = KaizenTranslator.PROJETO_PAPEL_NOME_OBRIGATORIO)
	private String nome;

	@Column(name = "dsc_papel", length = 1200, nullable = true)
	@Size(min = 0, max = 1200, message = KaizenTranslator.PROJETO_PAPEL_TAMANHO_DESCRICAO)
	@Null
	private String descricao;

	@Column(name = "flg_ativo", nullable = false)
	@NotNull
	private boolean ativo = Boolean.TRUE;

	@OneToMany(mappedBy = "papel", targetEntity = ItemPapel.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<ItemPapel> itensPapel = new ArrayList<ItemPapel>();

	@Override
	public Papel assignFrom(Papel papel) {
		this.id = papel.getId();
		this.nome = papel.getNome();
		this.descricao = papel.getDescricao();
		this.projeto = papel.getProjeto();
		this.ativo = papel.isAtivo();
		CollectionSynchronizer.synchronize(papel.itensPapel, this.itensPapel,
				itemPapel -> itemPapel.setPapel(this));
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
		if (!(object instanceof Papel)) {
			return false;
		}
		return Objects.equals(this.id, ((Papel) object).id);
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

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Collection<ItemPapel> getItensPapel() {
		return itensPapel;
	}

	public void setItensPapel(Collection<ItemPapel> itensPapel) {
		this.itensPapel = itensPapel;
	}

}
