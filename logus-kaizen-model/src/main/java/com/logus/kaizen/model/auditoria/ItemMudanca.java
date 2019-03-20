/**
 *
 */
package com.logus.kaizen.model.auditoria;

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

import com.logus.common.base.Strings;
import com.logus.core.model.persistence.Assignable;
import com.logus.kaizen.model.TableNames;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
@Entity
@Table(name = ItemMudanca.TB_ITEM_MUDANCA)
public class ItemMudanca implements Assignable<ItemMudanca>, TableNames {

	/**
	 * Id
	 */
	@Id
	@TableGenerator(name = "seq_item_grupo", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_item_grupo")
	@Column(name = "seq_item_grupo")
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
	 * Nome do Campo
	 */
	@Column(name = "nom_campo", length = 1500, nullable = false)
	@Size(min = 1, max = 150)
	@NotNull
	private String nomeCampo;

	/**
	 * Valor Antigo
	 */
	@Column(name = "valor_antigo", length = 1500, nullable = false)
	@Size(min = 1, max = 150)
	@NotNull
	private String valorAntigo;

	/**
	 * Valor Novo
	 */
	@Column(name = "valor_novo", length = 1500, nullable = false)
	@Size(min = 1, max = 150)
	@NotNull
	private String valorNovo;

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

	@ManyToOne(optional = true)
	@JoinColumn(name = "seq_grupo", referencedColumnName = "seq_grupo", nullable = true)
	@Null
	private GrupoMudanca grupo;

	private Long idEntidade;

	public ItemMudanca() {
	}

	public ItemMudanca(ItemMudanca grupo) {
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
	public ItemMudanca assignFrom(ItemMudanca obj) {
		this.id = obj.getId();
		this.autor = obj.getAutor();
		this.dataMudanca = obj.getDataMudanca();
		this.ativo = obj.isAtivo();
		this.entidade = obj.getEntidade();
		this.idEntidade = obj.getIdEntidade();
		this.nomeCampo = obj.getNomeCampo();
		this.valorAntigo = obj.getValorAntigo();
		this.valorNovo = obj.getValorNovo();
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

	@Override
	public String toString() {
		return getNomeEntidade();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (id == null) {
			return this == object;
		}
		if (!(object instanceof ItemMudanca)) {
			return false;
		}
		return Objects.equals(this.id, ((ItemMudanca) object).id);
	}

	@Override
	public int hashCode() {
		if (id == null) {
			return super.hashCode();
		}
		return Objects.hashCode(id);
	}

	public GrupoMudanca getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoMudanca grupo) {
		this.grupo = grupo;
	}

	public void setIdEntidade(Long idEntidade) {
		this.idEntidade = idEntidade;
	}

	public String getNomeCampo() {
		return nomeCampo;
	}

	public void setNomeCampo(String nomeCampo) {
		this.nomeCampo = nomeCampo;
	}

	public String getValorAntigo() {
		return valorAntigo;
	}

	public void setValorAntigo(String valorAntigo) {
		this.valorAntigo = valorAntigo;
	}

	public String getValorNovo() {
		return valorNovo;
	}

	public void setValorNovo(String valorNovo) {
		this.valorNovo = valorNovo;
	}

	public Long getIdEntidade() {
		return idEntidade;
	}


}
