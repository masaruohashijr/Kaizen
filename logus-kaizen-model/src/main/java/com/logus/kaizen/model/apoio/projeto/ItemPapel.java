package com.logus.kaizen.model.apoio.projeto;

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

/**
 * Para uma matriz de rateio estática, representa o percentual a ser alocado a
 * uma dada matriz.
 *
 */
@Entity
@EntityListeners(YokaiListener.class)
@Table(name = ItemPapel.TB_ITEM_PAPEL)
public class ItemPapel implements Assignable<ItemPapel>, TableNames {

	/**
	 * Identificador único gerado automaticamente pelo Sistema.
	 */
	@Id
	@TableGenerator(name = "seq_item_papel", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_item_papel")
	@Column(name = "seq_item_papel")
	private Long id;

	/**
	 * Matriz de rateio à qual este item pertence.
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "seq_papel", referencedColumnName = "seq_papel", nullable = false)
	private Papel papel;

	/**
	 * Colaborador
	 */
	@Column(name = "cod_usuario", length = 50, nullable = false)
	@Size(min = 1, max = 50, message = KaizenTranslator.ITEM_PAPEL_CODIGO_RESPONSAVEL_TAMANHO)
	@NotNull(message = KaizenTranslator.ITEM_PAPEL_CODIGO_RESPONSAVEL_OBRIGATORIO)
	private String codigoUsuario;

	/**
	 * Construtor.
	 */
	public ItemPapel() {
		super();
	}

	/**
	 * Construtor.
	 *
	 * @param object objeto cujas propriedades inicializarão o novo objeto
	 *               construído.
	 */
	public ItemPapel(ItemPapel object) {
		super();
		assignFrom(object);
	}

	@Override
	public ItemPapel assignFrom(ItemPapel object) {
		this.id = object.id;
		this.papel = object.papel;
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
		if (!(object instanceof ItemPapel)) {
			return false;
		}
		return Objects.equals(this.codigoUsuario, ((ItemPapel) object).codigoUsuario);
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

	public Papel getPapel() {
		return papel;
	}

	public void setPapel(Papel papel) {
		this.papel = papel;
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
