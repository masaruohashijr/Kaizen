package com.logus.kaizen.model.apoio.processo;

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
import com.logus.kaizen.model.apoio.atendimento.Atendimento;
import com.logus.kaizen.model.apoio.resolucao.Resolucao;
import com.logus.kaizen.model.apoio.tipomondai.AbstractAtribuicaoPassoItem;
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
@Table(name = Passo.TB_PASSO)
public class Passo extends AbstractAtribuicaoPassoItem implements Assignable<Passo>, TableNames {

	@Id
	@TableGenerator(name = "seq_passo", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_passo")
	@Column(name = "seq_passo")
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "seq_processo", referencedColumnName = "seq_processo", nullable = false)
	@NotNull(message = KaizenTranslator.PASSO_PROCESSO_OBRIGATORIO)
	private Processo processo;

	@ManyToOne(optional = false)
	@JoinColumn(name = "seq_transicao", referencedColumnName = "seq_transicao", nullable = false)
	@NotNull(message = KaizenTranslator.PASSO_TRANSICAO_OBRIGATORIO)
	private Transicao transicao;

	@ManyToOne(optional = true)
	@JoinColumn(name = "seq_resolucao", referencedColumnName = "seq_resolucao", nullable = true)
	private Resolucao resolucao;

	@ManyToOne(optional = false)
	@JoinColumn(name = "seq_atendimento_destino", referencedColumnName = "seq_atendimento", nullable = false)
	@NotNull(message = KaizenTranslator.PASSO_ATENDIMENTO_DESTINO_OBRIGATORIO)
	private Atendimento atendimentoDestino;

	@ManyToOne(optional = false)
	@JoinColumn(name = "seq_atendimento_origem", referencedColumnName = "seq_atendimento", nullable = false)
	@NotNull(message = KaizenTranslator.PASSO_ATENDIMENTO_ORIGEM_OBRIGATORIO)
	private Atendimento atendimentoOrigem;

	@Column(name = "nom_passo", length = 50, nullable = false)
	@Size(max = 50, min = 1, message = KaizenTranslator.PASSO_TAMANHO_NOME)
	@NotNull(message = KaizenTranslator.PASSO_NOME_OBRIGATORIO)
	private String nome;

	@Column(name = "dsc_passo", length = 1200, nullable = true)
	@Size(min = 0, max = 1200, message = KaizenTranslator.PASSO_TAMANHO_DESCRICAO)
	@Null
	private String descricao;

	@Column(name = "flg_ativo", nullable = false)
	@NotNull
	private boolean ativo = Boolean.TRUE;

	@Override
	public Passo assignFrom(Passo passo) {
		this.id = passo.getId();
		this.processo = passo.getProcesso();
		this.transicao = passo.getTransicao();
		this.resolucao = passo.getResolucao();
		this.atendimentoOrigem = passo.getAtendimentoOrigem();
		this.atendimentoDestino = passo.getAtendimentoDestino();
		this.nome = passo.getNome();
		this.descricao = passo.getDescricao();
		this.ativo = passo.isAtivo();
		return this;
	}

	@Override
	public int hashCode() {
		if (id == null) {
			return super.hashCode();
		}
		return Objects.hashCode(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (id == null) {
			return this == obj;
		}
		if (!(obj instanceof Passo)) {
			return false;
		}
		return Objects.equals(this.id, ((Passo) obj).id);
	}

	@Override
	public String toString() {
		return "" + getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public Transicao getTransicao() {
		return transicao;
	}

	public void setTransicao(Transicao transicao) {
		this.transicao = transicao;
	}

	public Atendimento getAtendimentoDestino() {
		return atendimentoDestino;
	}

	public void setAtendimentoDestino(Atendimento atendimentoDestino) {
		this.atendimentoDestino = atendimentoDestino;
	}

	public Atendimento getAtendimentoOrigem() {
		return atendimentoOrigem;
	}

	public void setAtendimentoOrigem(Atendimento atendimentoOrigem) {
		this.atendimentoOrigem = atendimentoOrigem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Resolucao getResolucao() {
		return resolucao;
	}

	public void setResolucao(Resolucao resolucao) {
		this.resolucao = resolucao;
	}

	@Override
	public String getAtribuicao() {
		return String.valueOf("");
	}

}
