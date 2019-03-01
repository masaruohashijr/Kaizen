/**
 *
 */
package com.logus.kaizen.model.apoio.cliente;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
import com.logus.kaizen.model.apoio.ambiente.Ambiente;
import com.logus.kaizen.model.translation.KaizenTranslator;
/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
@Entity
@Table(name = Cliente.TABLE_NAME)
public class Cliente implements Assignable<Cliente> {

	public static final String TABLE_NAME = "KZ_CLIENTE";

	@Id
	@TableGenerator(name = "seq_cliente", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_cliente")
	@Column(name = "seq_cliente")
	private Long id;

	@Column(name = "nom_cliente", length = 100, nullable = false)
	@Size(min = 1, max = 100, message = KaizenTranslator.CLIENTE_TAMANHO_NOME)
	@NotNull(message = KaizenTranslator.CLIENTE_NOME_OBRIGATORIO)
	private String nome;

	@Column(name = "dsc_cliente", length = 1200, nullable = true)
	@Size(min = 0, max = 1200, message = KaizenTranslator.CLIENTE_TAMANHO_DESCRICAO)
	@Null
	private String descricao;

	@OneToMany(mappedBy = "cliente", targetEntity = Ambiente.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<Ambiente> ambientes = new ArrayList<Ambiente>();

	@Column(name = "flg_ativo", nullable = false)
	private boolean ativo = Boolean.TRUE;

	public Cliente() {
	}

	public Cliente(Cliente cliente) {
		super();
		assignFrom(cliente);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.Assignable#assignFrom(java.lang.Object)
	 */
	@Override
	public Cliente assignFrom(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.descricao = cliente.getDescricao();
		CollectionSynchronizer.synchronize(cliente.ambientes, this.ambientes, ambiente -> ambiente.setCliente(this));
		this.ativo = cliente.isAtivo();
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
	 * @return {@link #nome}
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome atualiza {@link #nome}.
	 */

	public void setNome(String nome) {
		this.nome = nome;
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
	public String toString() {
		return nome;
	}

	public Collection<Ambiente> getAmbientes() {
		return ambientes;
	}

	public String getStrAmbientes() {
		StringBuilder sb = new StringBuilder();
		for (Iterator<Ambiente> iterator = ambientes.iterator(); iterator.hasNext();) {
			Ambiente ambiente = (Ambiente) iterator.next();
			sb.append(ambiente.getNome().concat(", "));
		}
		String s = "";
		if (sb.indexOf(",") > -1) {
			s = sb.substring(0, sb.lastIndexOf(","));
		}
		return s;
	}

	public void setAmbientes(Collection<Ambiente> ambientes) {
		this.ambientes = ambientes;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (id == null) {
			return this == object;
		}
		if (!(object instanceof Cliente)) {
			return false;
		}
		return Objects.equals(this.id, ((Cliente) object).id);
	}

	@Override
	public int hashCode() {
		if (id == null) {
			return super.hashCode();
		}
		return Objects.hashCode(id);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
