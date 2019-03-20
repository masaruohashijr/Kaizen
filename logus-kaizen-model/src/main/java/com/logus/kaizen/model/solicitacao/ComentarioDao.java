/**
 *
 */
package com.logus.kaizen.model.solicitacao;

import java.util.Collection;

import com.logus.core.model.persistence.DataAccessObject;
import com.logus.kaizen.model.apoio.funcao.ItemFuncao;
import com.logus.kaizen.model.apoio.projeto.ItemPapel;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public interface ComentarioDao extends DataAccessObject<Comentario>{
	Collection<Comentario> loadComentariosAutorizados(Solicitacao solicitacao, Collection<ItemFuncao> itensFuncoesUsuario,
			Collection<ItemPapel> itensPapeisProjetoUsuario);
}
