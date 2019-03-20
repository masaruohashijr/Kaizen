package com.logus.kaizen.model.apoio.funcao;


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
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;

import com.logus.core.model.persistence.Assignable;
import com.logus.kaizen.model.TableNames;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.kaizen.model.util.YokaiListener;

@Entity
@EntityListeners(YokaiListener.class)
@Table(name = ItemFuncao.TB_ITEM_FUNCAO)
public class ItemFuncao implements Assignable<ItemFuncao>, TableNames {

	/**
	 * Identificador único gerado automaticamente pelo Sistema.
	 */
	@Id
	@TableGenerator(name = "seq_item_funcao", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_item_funcao")
	@Column(name = "seq_item_funcao")
	private Long id;

	/**
	 * Matriz de rateio à qual este item pertence.
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "seq_funcao", referencedColumnName = "seq_funcao", nullable = false)
	private Funcao funcao;

	/**
	 * Colaborador
	 */
	@Column(name = "cod_usuario", length = 50, nullable = false)
	@Size(min = 1, max = 50, message = KaizenTranslator.ITEM_FUNCAO_CODIGO_USUARIO_TAMANHO)
	@NotNull(message = KaizenTranslator.ITEM_FUNCAO_CODIGO_USUARIO_OBRIGATORIO)
	private String codigoUsuario;

	/**
	 * Construtor.
	 */
	public ItemFuncao() {
		super();
	}

	/**
	 * Construtor.
	 *
	 * @param object objeto cujas propriedades inicializarão o novo objeto
	 *               construído.
	 */
	public ItemFuncao(ItemFuncao object) {
		super();
		assignFrom(object);
	}

	@Override
	public ItemFuncao assignFrom(ItemFuncao object) {
		this.id = object.id;
		this.funcao = object.funcao;
		this.codigoUsuario = object.codigoUsuario;
		return this;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (codigoUsuario == null) {
			return this == object;
		}
		if (!(object instanceof ItemFuncao)) {
			return false;
		}
		return Objects.equals(this.codigoUsuario, ((ItemFuncao) object).codigoUsuario);
	}

	@Override
	public int hashCode() {
		if (codigoUsuario == null) {
			return super.hashCode();
		}
		return Objects.hashCode(codigoUsuario);
	}

	/**
	 * @param id atualiza {@link #id}.
	 */

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return StringUtils.capitalize(codigoUsuario);
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	public Long getId() {
		return id;
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

}
