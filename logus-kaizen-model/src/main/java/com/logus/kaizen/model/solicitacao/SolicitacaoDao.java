/**
 *
 */
package com.logus.kaizen.model.solicitacao;

import java.util.Collection;

import com.logus.core.model.persistence.DataAccessObject;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public interface SolicitacaoDao extends DataAccessObject<Solicitacao>{
    Collection<Solicitacao> loadSolicitacoes();
}
