package com.logus.kaizen.model.apoio.ambiente;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.logus.core.model.persistence.Assignable;
import com.logus.kaizen.model.TableNames;
import com.logus.kaizen.model.apoio.cliente.Cliente;
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
@Table(name = Ambiente.TB_AMBIENTE)
public class Ambiente implements Assignable<Ambiente>, TableNames {

	@Id
	@TableGenerator(name = "seq_ambiente", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_ambiente")
	@Column(name = "seq_ambiente")
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "seq_cliente", referencedColumnName = "seq_cliente", nullable = false)
	private Cliente cliente;

	@Column(name = "host", nullable = true, length = 100)
	@Size(min = 0, max = 100, message = KaizenTranslator.AMBIENTE_HOST_TAMANHO_NOME)
	@Null
	private String host = new String();

	@Column(name = "acronimo", nullable = true, length = 100)
	@Size(min = 0, max = 6, message = KaizenTranslator.AMBIENTE_ACRONIMO_TAMANHO_NOME)
	@Null
	private String acronimo = new String();

	@Column(name = "nom_ambiente", nullable = false, length = 100)
	@Size(min = 1, max = 100, message = KaizenTranslator.AMBIENTE_TAMANHO_NOME)
	@NotNull(message = KaizenTranslator.AMBIENTE_NOME_OBRIGATORIO)
	private String nome;

	@Column(name = "dsc_ambiente", length = 1200, nullable = true)
	@Size(min = 0, max = 1200, message = KaizenTranslator.AMBIENTE_TAMANHO_DESCRICAO)
	@Null
	private String descricao;

	@Column(name = "flg_ativo", nullable = false)
	private boolean ativo = Boolean.TRUE;

	/**
	 * Construtor.
	 */
	public Ambiente() {
		super();
	}

	/**
	 * Construtor.
	 *
	 * @param object objeto cujas propriedades inicializarão o novo objeto
	 *               construído.
	 */
	public Ambiente(Ambiente object) {
		super();
		assignFrom(object);
	}

	@Override
	public Ambiente assignFrom(Ambiente object) {
		this.id = object.getId();
		this.host = object.getHost();
		this.nome = object.getNome();
		this.acronimo = object.getAcronimo();
		this.cliente = object.getCliente();
		this.descricao = object.getDescricao();
		this.ativo = object.isAtivo();
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
		if (!(object instanceof Ambiente)) {
			return false;
		}
		return Objects.equals(this.id, ((Ambiente) object).id);
	}

	@Override
	public int hashCode() {
		if (id == null) {
			return super.hashCode();
		}
		return Objects.hashCode(id);
	}

	/**
	 * @param id atualiza {@link #id}.
	 */

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return String.format("%s - %s", cliente, nome);
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public String getAcronimo() {
		return acronimo;
	}

	public void setAcronimo(String acronimo) {
		this.acronimo = acronimo;
	}

}
