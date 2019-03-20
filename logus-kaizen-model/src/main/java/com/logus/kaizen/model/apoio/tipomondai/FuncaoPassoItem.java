/**
 *
 */
package com.logus.kaizen.model.apoio.tipomondai;

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
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.logus.core.model.persistence.Assignable;
import com.logus.kaizen.model.TableNames;
import com.logus.kaizen.model.apoio.atendimento.Atendimento;
import com.logus.kaizen.model.apoio.funcao.Funcao;
import com.logus.kaizen.model.apoio.processo.Passo;
import com.logus.kaizen.model.apoio.processo.Processo;
import com.logus.kaizen.model.apoio.transicao.Transicao;
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
@Table(name = FuncaoPassoItem.TB_FUNCAO_PASSO_ITEM)

public class FuncaoPassoItem extends AbstractAtribuicaoPassoItem implements Assignable<FuncaoPassoItem>, TableNames {

	@Id
	@TableGenerator(name = "seq_funcao_passo", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_funcao_passo")
	@Column(name = "seq_funcao_passo")
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "seq_tipo_mondai", referencedColumnName = "seq_tipo_mondai", nullable = false)
	private TipoMondai tipoMondai;

	@ManyToOne(optional = false)
	@JoinColumn(name = "seq_funcao", referencedColumnName = "seq_funcao", nullable = false)
	private Funcao funcao;

	@ManyToOne(optional = false)
	@JoinColumn(name = "seq_passo", referencedColumnName = "seq_passo", nullable = false)
	private Passo passo;

	@Column(name = "dsc_funcao_passo_item", length = 1200, nullable = true)
	@Size(min = 0, max = 1200, message = KaizenTranslator.PAPEL_PASSO_ITEM_TAMANHO_DESCRICAO)
	@Null
	private String descricao;

	@Column(name = "flg_ativo", nullable = false)
	private boolean ativo = Boolean.TRUE;

	public FuncaoPassoItem() {
		super();
	}

	public FuncaoPassoItem(FuncaoPassoItem funcaoPasso) {
		assignFrom(funcaoPasso);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.Assignable#assignFrom(java.lang.Object)
	 */
	@Override
	public FuncaoPassoItem assignFrom(FuncaoPassoItem funcaoPasso) {
		this.id = funcaoPasso.getId();
		this.funcao = funcaoPasso.getFuncao();
		this.passo = funcaoPasso.getPasso();
		this.tipoMondai = funcaoPasso.getTipoMondai();
		this.descricao = funcaoPasso.getDescricao();
		this.ativo = funcaoPasso.isAtivo();
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
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (id == null) {
			return this == object;
		}
		if (!(object instanceof FuncaoPassoItem)) {
			return false;
		}
		return Objects.equals(this.id, ((FuncaoPassoItem) object).id);
	}

	@Override
	public int hashCode() {
		if (id == null) {
			return super.hashCode();
		}
		return Objects.hashCode(id);
	}

	public TipoMondai getTipoMondai() {
		return tipoMondai;
	}

	public void setTipoMondai(TipoMondai tipoMondai) {
		this.tipoMondai = tipoMondai;
	}

	public Passo getPasso() {
		return passo;
	}

	public void setPasso(Passo passo) {
		this.passo = passo;
	}

	public Transicao getTransicaoPasso() {
		if(null != passo) {
			return passo.getTransicao();
		}
		return null;
	}
	public Processo getProcessoPasso() {
		if(null != passo) {
			return passo.getProcesso();
		}
		return null;
	}
	public Atendimento getAtendimentoOrigemPasso() {
		return (null != passo) ? passo.getAtendimentoOrigem() : null;
	}
	public Atendimento getAtendimentoDestinoPasso() {
		return (null != passo) ? passo.getAtendimentoDestino() : null;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	@Override
	public String toString() {
		return this.funcao + " - "+this.passo;
	}

	@Override
	public String getAtribuicao() {
		return funcao.getNome();
	}
}
