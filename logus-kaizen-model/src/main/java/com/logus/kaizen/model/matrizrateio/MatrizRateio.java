package com.logus.kaizen.model.matrizrateio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.core.model.persistence.Assignable;
import com.logus.core.model.persistence.CollectionSynchronizer;
import com.logus.core.model.translation.TM;

/**
 * Matrizes de rateio estabelecem percentuais que serão usados para ratear
 * despesas por centros de custos. Podem ser definidas de forma estática ou a
 * partir de uma cláusula SQL que dinamicamente irá estabelecer os percentuais
 * para cada centro de custo.
 *
 * @author Walace Zloccowick Maia
 */
@Entity
@Table(name = MatrizRateio.TABLE_NAME)
public class MatrizRateio
  implements Assignable<MatrizRateio> {

  /**
   * Determina a forma como os dados de rateio serão produzidos.
   */
  public enum TipoMatriz {
    /**
     * Os percentuais serão obtidos a partir de uma cláusula SQL.
     *
     * @see MatrizRateio#sql
     */
    DINAMICA,
    /**
     * Os percentuais serão previamente informados pelo usuário.
     */
    ESTATICA;

    @Override
    public String toString() {
      return TM.translate(name());
    }
  }

  /**
   * Nome da tabela na qual esta entidade será persistida.
   */
  public static final String TABLE_NAME = "CST_MATRIZ_RATEIO";

  /**
   * Identificador único gerado automaticamente pelo Sistema.
   */
  @Id
  @TableGenerator(name = "seq_matriz_rateio", initialValue = 1,
                  allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.TABLE,
                  generator = "seq_matriz_rateio")
  @Column(name = "seq_matriz_rateio")
  private Long id;

  /**
   * Itens do rateio, quando o rateio é do tipo estático.
   */
  @OneToMany(mappedBy = "matrizRateio", targetEntity = ItemRateio.class,
             fetch = FetchType.LAZY, cascade = CascadeType.ALL,
             orphanRemoval = true)
  private Collection<ItemRateio> itensRateio = new ArrayList<ItemRateio>();

  /**
   * Nome da matriz de rateio.
   */
  @Column(name = "nom_matriz_rateio", nullable = false, length = 100)
  @Size(min = 1, max = 100,
        message = KaizenTranslator.MATRIZ_RATEIO_TAMANHO_NOME)
  @NotNull(message = KaizenTranslator.MATRIZ_RATEIO_NOME_OBRIGATORIO)
  private String nome;

  /**
   * Cláusula SQL que para matrizes do tipo {@link TipoMatriz#DINAMICA} irá
   * produzir os percentuais por centro de custo.
   */
  @Lob
  @Column(name = "sql_rateio")
  private String sql;

  /**
   * Tipo da matriz de rateio.
   */
  @Enumerated(EnumType.STRING)
  @Column(name = "tip_matriz", nullable = false)
  @NotNull(message = KaizenTranslator.MATRIZ_RATEIO_TIPO_OBRIGATORIO)
  private TipoMatriz tipo;

  /**
   * Construtor.
   */
  public MatrizRateio() {
    super();
  }

  /**
   * Construtor.
   *
   * @param object objeto cujas propriedades inicializarão o novo objeto
   *               construído.
   */
  public MatrizRateio(MatrizRateio object) {
    super();
    assignFrom(object);
  }

  @Override
  public MatrizRateio assignFrom(MatrizRateio object) {
    this.id = object.id;
    this.nome = object.nome;
    this.sql = object.sql;
    this.tipo = object.tipo;
    CollectionSynchronizer.synchronize(object.itensRateio, this.itensRateio,
                                       item -> item.setMatrizRateio(this));
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
    if (!(object instanceof MatrizRateio)) {
      return false;
    }
    return Objects.equals(this.id, ((MatrizRateio) object).id);
  }

  @Override
  public int hashCode() {
    if (id == null) {
      return super.hashCode();
    }
    return Objects.hashCode(id);
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

  /**
   * @return {@link #itensRateio}
   */
  public Collection<ItemRateio> getItensRateio() {
    return itensRateio;
  }

  /**
   * @param itensRateio atualiza {@link #itensRateio}.
   */

  public void setItensRateio(Collection<ItemRateio> itensRateio) {
    this.itensRateio = itensRateio;
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
   * @return {@link #sql}
   */
  public String getSql() {
    return sql;
  }

  /**
   * @param sql atualiza {@link #sql}.
   */

  public void setSql(String sql) {
    this.sql = sql;
  }

  /**
   * @return {@link #tipo}
   */
  public TipoMatriz getTipo() {
    return tipo;
  }

  /**
   * @param tipo atualiza {@link #tipo}.
   */
  public void setTipo(TipoMatriz tipo) {
    this.tipo = tipo;
  }

  @Override
  public String toString() {
    return nome;
  }

}
