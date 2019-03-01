package com.logus.kaizen.model.matrizrateio;

import java.math.BigDecimal;
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
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.logus.kaizen.model.centrocusto.CentroCusto;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.core.model.persistence.Assignable;

/**
 * Para uma matriz de rateio estática, representa o percentual a ser alocado a
 * uma dada matriz.
 *
 * @author Walace Zloccowick Maia
 */
@Entity
@Table(name = ItemRateio.TABLE_NAME)
public class ItemRateio implements Assignable<ItemRateio> {

	/**
	 * Nome da tabela onde esta entidade será persistida.
	 */
	public static final String TABLE_NAME = "CST_ITEM_RATEIO";

	/**
	 * Centro de custo ao qual o rateio se refere.
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "seq_centro_custo", referencedColumnName = "seq_centro_custo", nullable = false)
	@NotNull(message = KaizenTranslator.ITEM_RATEIO_DIVIDA_OBRIGATORIO)
	private CentroCusto centroCusto;

	/**
	 * Identificador único gerado automaticamente pelo Sistema.
	 */
	@Id
	@TableGenerator(name = "seq_item_rateio", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_item_rateio")
	@Column(name = "seq_item_rateio")
	private Long id;

	/**
	 * Matriz de rateio à qual este item pertence.
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "seq_matriz_rateio", referencedColumnName = "seq_matriz_rateio", nullable = false)
	private MatrizRateio matrizRateio;

	/**
	 * Percentual definido para o rateio.
	 */
	@Column(name = "pct_rateio", nullable = false, precision = 20, scale = 5)
	@DecimalMax(value = "100.0", message = KaizenTranslator.ITEM_RATEIO_PERCENTUAL_MENOR_100)
	@DecimalMin(value = "0.00001", message = KaizenTranslator.ITEM_RATEIO_PERCENTUAL_MAIOR_0)
	@NotNull(message = KaizenTranslator.ITEM_RATEIO_PERCENTUAL_OBRIGATORIO)
	private BigDecimal percentual = BigDecimal.ZERO;

	/**
	 * Construtor.
	 */
	public ItemRateio() {
		super();
	}

	/**
	 * Construtor.
	 *
	 * @param object objeto cujas propriedades inicializarão o novo objeto
	 *               construído.
	 */
	public ItemRateio(ItemRateio object) {
		super();
		assignFrom(object);
	}

	@Override
	public ItemRateio assignFrom(ItemRateio object) {
		this.id = object.id;
		this.percentual = object.percentual;
		this.centroCusto = object.centroCusto;
		this.matrizRateio = object.matrizRateio;
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
		if (!(object instanceof ItemRateio)) {
			return false;
		}
		return Objects.equals(this.id, ((ItemRateio) object).id);
	}

	public CentroCusto getCentroCusto() {
		return centroCusto;
	}

	public void setCentroCusto(CentroCusto centroCusto) {
		this.centroCusto = centroCusto;
	}

	/**
	 * @return {@link #matrizRateio}
	 */
	public MatrizRateio getMatrizRateio() {
		return matrizRateio;
	}

	/**
	 * @return {@link #percentual}
	 */
	public BigDecimal getPercentual() {
		return percentual;
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

	/**
	 * @param matrizRateio atualiza {@link #matrizRateio}.
	 */

	public void setMatrizRateio(MatrizRateio matrizRateio) {
		this.matrizRateio = matrizRateio;
	}

	/**
	 * @param percentual atualiza {@link #percentual}.
	 */

	public void setPercentual(BigDecimal percentual) {
		this.percentual = percentual;
	}

	@Override
	public String toString() {
		return String.format("%s : %s", centroCusto, percentual);
	}

}
