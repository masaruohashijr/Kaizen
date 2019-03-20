/**
 *
 */
package com.logus.kaizen.model.solicitacao;

import java.util.Collection;

import com.logus.core.model.aut.login.LoginManager;
import com.logus.core.model.persistence.jpa.AbstractJpaDao;
import com.logus.kaizen.model.apoio.ApoioDataServiceImpl;
import com.logus.kaizen.model.apoio.funcao.ItemFuncao;
import com.logus.kaizen.model.apoio.projeto.ItemPapel;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class ComentarioJpaDaoImpl extends AbstractJpaDao<Comentario> implements ComentarioDao {

	/**
	 * @param emf
	 */
	public ComentarioJpaDaoImpl() {
		super(ApoioDataServiceImpl.getEMF());
	}

	private static ComentarioJpaDaoImpl instance;

	public static ComentarioJpaDaoImpl getInstance() {
		if (instance == null) {
			instance = new ComentarioJpaDaoImpl();
		}
		return instance;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#assignTo(java.lang.
	 * Object, java.lang.Object)
	 */
	@Override
	public void assignTo(Comentario source, Comentario dest) {
		dest.assignFrom(source);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#getTableName()
	 */
	@Override
	public String getTableName() {
		return Comentario.TB_COMENTARIO;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#updateEnabled()
	 */
	@Override
	protected boolean updateEnabled() {
		return LoginManager.getAccessControl().hasAccess(FuncionalidadeSolicitacao.FUNC_SOLICITACAO_CADASTRAR.getId());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#queryEnabled()
	 */
	@Override
	protected boolean queryEnabled() {
		return LoginManager.getAccessControl().hasAccess(FuncionalidadeSolicitacao.FUNC_SOLICITACAO_CONSULTAR.getId());
	}

	@Override
	public Collection<Comentario> loadComentariosAutorizados(Solicitacao solicitacao, Collection<ItemFuncao> itensFuncoesUsuario,
			Collection<ItemPapel> itensPapeisProjetoUsuario) {
		String INpapeisProjetoUsuario = "";
		for (ItemPapel itemPapel : itensPapeisProjetoUsuario) {
			INpapeisProjetoUsuario += itemPapel.getPapel().getId() + ", ";
		}
		String INfuncoesUsuario = "";
		for (ItemFuncao itemFuncao : itensFuncoesUsuario) {
			INfuncoesUsuario += itemFuncao.getFuncao().getId() + ", ";
		}

		INpapeisProjetoUsuario = (INpapeisProjetoUsuario.indexOf(",")>-1)?INpapeisProjetoUsuario.substring(0, INpapeisProjetoUsuario.lastIndexOf(",")):INpapeisProjetoUsuario;
		INfuncoesUsuario = (INfuncoesUsuario.indexOf(",")>-1)?INfuncoesUsuario.substring(0, INfuncoesUsuario.lastIndexOf(",")):INfuncoesUsuario;

		StringBuilder sql = new StringBuilder("flg_ativo = ? and seq_solicitacao = ?");
		String sqlFuncaoPapelNULL = "(seq_funcao is null and  seq_papel is null)";
		boolean hasINPapeis = INpapeisProjetoUsuario.length() > 0;
		boolean hasINFuncoes = INfuncoesUsuario.length() > 0;
		boolean hasIN = hasINPapeis || hasINFuncoes;
		if(hasIN) {
			sql.append(" and ( ").append(sqlFuncaoPapelNULL);
		} else {
			sql.append(" and " +sqlFuncaoPapelNULL);
		}
		if(hasINFuncoes) {
			sql.append(" or seq_funcao in (").append(INfuncoesUsuario).append(") ");
		}
		if(hasINPapeis) {
			sql.append(" or seq_papel in (").append(INpapeisProjetoUsuario).append(") ");
		}
		if(hasINFuncoes || hasINPapeis) {
			sql.append(" )");
		}
		return loadCollectionByFilter(sql.toString(), Boolean.TRUE, new Long(solicitacao.getId()));
	}

}
