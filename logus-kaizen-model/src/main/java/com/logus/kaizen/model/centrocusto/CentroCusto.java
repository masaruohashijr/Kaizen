package com.logus.kaizen.model.centrocusto;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.logus.core.model.persistence.Assignable;
import com.logus.kaizen.model.translation.CustosTranslator;

/**
 * Centros de Custo são informações sobre para as quais se deseja apurar o
 * custo, podendo variar conforme o propósito da análise.
 *
 * @author Walace Zloccowick Maia
 */
@Entity
@Table(name = CentroCusto.TABLE_NAME)
public class CentroCusto
  implements Assignable<CentroCusto> {

  /**
   * Nome da tabela onde esta entidade será persistida.
   */
  public static final String TABLE_NAME = "CST_CENTRO_CUSTO";

  /**
   * Identificador único gerado automaticamente pelo Sistema.
   */
  @Id
  @TableGenerator(name = "seq_centro_custo", initialValue = 1,
                  allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.TABLE,
                  generator = "seq_centro_custo")
  @Column(name = "seq_centro_custo")
  private Long id;

  /**
   * Nome do centro de custo.
   */
  @Column(name = "nom_centro_custo", nullable = false, length = 100)
  @Size(min = 1, max = 100,
        message = CustosTranslator.CENTRO_CUSTO_TAMANHO_NOME)
  @NotNull(message = CustosTranslator.CENTRO_CUSTO_NOME_OBRIGATORIO)
  private String nome;

  /**
   * Determina se o Centro de custo está disponível para apropriações e
   * referências em outras estruturas de dados.
   */
  @Column(name = "flg_ativo", nullable = false)
  private Boolean ativo = Boolean.TRUE;

  /**
   * @return {@link #ativo}
   */
  public Boolean getAtivo() {
    return ativo;
  }

  /**
   * @return {@code true} se o Centro de Custo estiver ativo.
   */
  public Boolean isAtivo() {
    return Boolean.TRUE.equals(ativo);
  }

  /**
   * @param ativo atualiza {@link #ativo}.
   */

  public void setAtivo(Boolean ativo) {
    this.ativo = ativo;
  }

  /**
   * Construtor.
   */
  public CentroCusto() {
    super();
  }

  /**
   * Construtor.
   *
   * @param object objeto cujas propriedades inicializarão o novo objeto
   *               construído.
   */
  public CentroCusto(CentroCusto object) {
    super();
    assignFrom(object);
  }

  @Override
  public CentroCusto assignFrom(CentroCusto object) {
    this.nome = object.nome;
    this.id = object.id;
    this.ativo = object.ativo;
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
    if (!(object instanceof CentroCusto)) {
      return false;
    }
    return Objects.equals(this.id, ((CentroCusto) object).id);
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

  @Override
  public String toString() {
    return nome;
  }

}
