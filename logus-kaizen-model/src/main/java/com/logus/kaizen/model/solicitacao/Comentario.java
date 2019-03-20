package com.logus.kaizen.model.solicitacao;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.logus.core.model.persistence.Assignable;
import com.logus.kaizen.model.TableNames;
import com.logus.kaizen.model.apoio.funcao.Funcao;
import com.logus.kaizen.model.apoio.projeto.Papel;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.kaizen.model.util.YokaiListener;

@Entity
@EntityListeners(YokaiListener.class)
@Table(name = TableNames.TB_COMENTARIO)
public class Comentario implements Assignable<Comentario>, TableNames {

	@Id
	@TableGenerator(name = "seq_comentario", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_comentario")
	@Column(name = "seq_comentario")
	private Long id;

	@Column(name = "cod_autor", length = 4000, nullable = false)
	@Size(min = 1, max = 4000, message = KaizenTranslator.COMENTARIO_AUTOR_TAMANHO)
	@NotNull(message = KaizenTranslator.COMENTARIO_AUTOR_OBRIGATORIO)
	private String autor;

	@Column(name = "dat_criacao", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull(message = KaizenTranslator.COMENTARIO_DATA_CRIACAO_OBRIGATORIA)
	private Date dataCriacao;

	@Column(name = "dat_atualizacao", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Null(message = KaizenTranslator.COMENTARIO_DATA_ATUALIZACAO_OBRIGATORIA)
	private Date dataAtualizacao;

	@Column(name = "txt_comentario", length = 4000, nullable = false)
	@Size(min = 1, max = 4000, message = KaizenTranslator.COMENTARIO_TAMANHO)
	@NotNull(message = KaizenTranslator.COMENTARIO_OBRIGATORIO)
	private String comentario;

	@ManyToOne(optional = false)
	@JoinColumn(name = "seq_solicitacao", referencedColumnName = "seq_solicitacao", nullable = false)
	@NotNull(message = KaizenTranslator.SOLICITACAO_OBRIGATORIA)
	private Solicitacao solicitacao;

	@ManyToOne(optional = true)
	@JoinColumn(name = "seq_papel", referencedColumnName = "seq_papel", nullable = true)
	@Null
	private Papel papel;

	@ManyToOne(optional = true)
	@JoinColumn(name = "seq_funcao", referencedColumnName = "seq_funcao", nullable = true)
	@Null
	private Funcao funcao;

	@Column(name = "flg_ativo", nullable = false)
	private boolean ativo = Boolean.TRUE;

	public Comentario() {
	}

	public Comentario(Comentario comentario) {
		super();
		assignFrom(comentario);
	}

	@Override
	public Comentario assignFrom(Comentario comentario) {
		this.ativo = comentario.isAtivo();
		this.autor = comentario.getAutor();
		this.id = comentario.getId();
		this.dataCriacao = comentario.getDataCriacao();
		this.dataAtualizacao = comentario.getDataAtualizacao();
		this.solicitacao = comentario.getSolicitacao();
		this.comentario = comentario.getComentario();
		this.funcao = comentario.getFuncao();
		this.papel = comentario.getPapel();
		return this;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Papel getPapel() {
		return papel;
	}

	public void setPapel(Papel papel) {
		this.papel = papel;
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	@Override
	public String toString() {
		return " de "+getAutor();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (id == null) {
			return this == object;
		}
		if (!(object instanceof Comentario)) {
			return false;
		}
		return Objects.equals(this.id, ((Comentario) object).id);
	}

	@Override
	public int hashCode() {
		if (id == null) {
			return super.hashCode();
		}
		return Objects.hashCode(id);
	}


}
