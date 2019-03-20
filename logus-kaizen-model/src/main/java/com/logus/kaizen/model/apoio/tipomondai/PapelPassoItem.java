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
import com.logus.kaizen.model.apoio.processo.Passo;
import com.logus.kaizen.model.apoio.processo.Processo;
import com.logus.kaizen.model.apoio.projeto.Papel;
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
@Table(name = PapelPassoItem.TB_PAPEL_PASSO_ITEM)

public class PapelPassoItem extends AbstractAtribuicaoPassoItem implements Assignable<PapelPassoItem>, TableNames {

	@Id
	@TableGenerator(name = "seq_papel_passo", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_papel_passo")
	@Column(name = "seq_papel_passo")
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "seq_tipo_mondai_projeto", referencedColumnName = "seq_tipo_mondai_projeto", nullable = false)
	private TipoMondaiProjeto tipoMondaiProjeto;

	@ManyToOne(optional = false)
	@JoinColumn(name = "seq_papel", referencedColumnName = "seq_papel", nullable = false)
	private Papel papel;

	@ManyToOne(optional = false)
	@JoinColumn(name = "seq_passo", referencedColumnName = "seq_passo", nullable = false)
	private Passo passo;

	@Column(name = "dsc_papel_passo_item", length = 1200, nullable = true)
	@Size(min = 0, max = 1200, message = KaizenTranslator.PAPEL_PASSO_ITEM_TAMANHO_DESCRICAO)
	@Null
	private String descricao;

	@Column(name = "flg_ativo", nullable = false)
	private boolean ativo = Boolean.TRUE;

	public PapelPassoItem() {
	}

	public PapelPassoItem(PapelPassoItem papelPasso) {
		assignFrom(papelPasso);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.Assignable#assignFrom(java.lang.Object)
	 */
	@Override
	public PapelPassoItem assignFrom(PapelPassoItem papelPasso) {
		this.id = papelPasso.getId();
		this.papel = papelPasso.getPapel();
		this.passo = papelPasso.getPasso();
		this.tipoMondaiProjeto = papelPasso.getTipoMondaiProjeto();
		this.descricao = papelPasso.getDescricao();
		this.ativo = papelPasso.isAtivo();
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
		if (!(object instanceof PapelPassoItem)) {
			return false;
		}
		return Objects.equals(this.id, ((PapelPassoItem) object).id);
	}

	@Override
	public int hashCode() {
		if (id == null) {
			return super.hashCode();
		}
		return Objects.hashCode(id);
	}

	public TipoMondaiProjeto getTipoMondaiProjeto() {
		return tipoMondaiProjeto;
	}

	public void setTipoMondaiProjeto(TipoMondaiProjeto tipoMondaiProjeto) {
		this.tipoMondaiProjeto = tipoMondaiProjeto;
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

	public Papel getPapel() {
		return papel;
	}

	public void setPapel(Papel papel) {
		this.papel = papel;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return this.papel + " - "+this.passo;
	}

	@Override
	public String getAtribuicao() {
		return papel.getNome();
	}
}
