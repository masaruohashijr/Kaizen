/**
 *
 */
package com.logus.kaizen.model.auditoria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.logus.common.base.Strings;
import com.logus.core.model.persistence.Assignable;
import com.logus.core.model.persistence.CollectionSynchronizer;
import com.logus.kaizen.model.TableNames;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
@Entity
@Table(name = GrupoMudanca.TB_GRUPO_MUDANCA)
public class GrupoMudanca implements Assignable<GrupoMudanca>, TableNames {

	/**
	 * Id
	 */
	@Id
	@TableGenerator(name = "seq_grupo", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_grupo")
	@Column(name = "seq_grupo")
	private Long id;

	/**
	 * Autor
	 */
	@Column(name = "cod_autor", length = 50, nullable = false)
	@Size(min = 1, max = 50)
	@NotNull
	private String autor;

	/**
	 * Nome da Entidade
	 */
	@Column(name = "nom_entidade", length = 1500, nullable = false)
	@Size(min = 1, max = 150)
	@NotNull
	private String entidade;

	/**
	 * Id da Entidade
	 */
	@Column(name = "seq_entidade", nullable = false)
	@NotNull
	private Long idEntidade;

	/**
	 * Data Mudança
	 */
	@Column(name = "dat_mudanca", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date dataMudanca;

	/**
	 * Ativo
	 */
	@Column(name = "flg_ativo", nullable = false)
	private boolean ativo = Boolean.TRUE;

	@OneToMany(mappedBy = "grupo", targetEntity = ItemMudanca.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Size(min = 0)
	private Collection<ItemMudanca> itensMudanca = new ArrayList<ItemMudanca>();

	public GrupoMudanca() {
	}

	public GrupoMudanca(GrupoMudanca grupo) {
		assignFrom(grupo);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.Assignable#assignFrom(java.lang.Object)
	 */

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

	@Override
	public GrupoMudanca assignFrom(GrupoMudanca obj) {
		this.id = obj.getId();
		this.autor = obj.getAutor();
		this.dataMudanca = obj.getDataMudanca();
		this.ativo = obj.isAtivo();
		this.idEntidade = obj.getIdEntidade();
		this.entidade = obj.getEntidade();
		CollectionSynchronizer.synchronize(obj.itensMudanca, this.itensMudanca,
				item -> item.setGrupo(this));
		return this;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Date getDataMudanca() {
		return dataMudanca;
	}

	public void setDataMudanca(Date dataMudanca) {
		this.dataMudanca = dataMudanca;
	}

	public String getEntidade() {
		return entidade;
	}

	public String getNomeEntidade() {
		if(null!=entidade) {
			return entidade.substring(entidade.lastIndexOf(".")+1,entidade.length());
		} else {
			return Strings.emptyIfNull(entidade);
		}
	}

	public void setEntidade(String entidade) {
		this.entidade = entidade;
	}

	public Collection<ItemMudanca> getItensMudanca() {
		return itensMudanca;
	}

	public void setItensMudanca(Collection<ItemMudanca> itensMudanca) {
		this.itensMudanca = itensMudanca;
	}

	@Override
	public String toString() {
		return entidade;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (id == null) {
			return this == object;
		}
		if (!(object instanceof GrupoMudanca)) {
			return false;
		}
		return Objects.equals(this.id, ((GrupoMudanca) object).id);
	}

	@Override
	public int hashCode() {
		if (id == null) {
			return super.hashCode();
		}
		return Objects.hashCode(id);
	}

	public Long getIdEntidade() {
		return idEntidade;
	}

	public void setIdEntidade(Long idEntidade) {
		this.idEntidade = idEntidade;
	}


}
