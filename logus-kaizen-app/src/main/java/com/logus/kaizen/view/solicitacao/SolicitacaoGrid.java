/**
 *
 */
package com.logus.kaizen.view.solicitacao;

import com.logus.kaizen.model.solicitacao.Solicitacao;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.list.BeanGrid;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class SolicitacaoGrid extends BeanGrid<Solicitacao> {

	/**
	 * Número serial
	 */
	private static final long serialVersionUID = -5792111538275287597L;

	/**
	 * Construtor responsável por criar a tabela contendo as solicitações
	 * persistidas
	 */
	public SolicitacaoGrid() {
		addColumn(Solicitacao::getIssue).setWidth("1").setHeader(TM.translate(KaizenTranslator.SOLICITACAO_ISSUE));
		addColumn(Solicitacao::getTituloIssue).setWidth("4")
				.setHeader(TM.translate(KaizenTranslator.SOLICITACAO_TITULO_ISSUE));
		addColumn(Solicitacao::getNomeSolicitante).setWidth("3")
				.setHeader(TM.translate(KaizenTranslator.SOLICITACAO_NOME_SOLICITANTE));
		addColumn(Solicitacao::getStrDataSolicitacao).setWidth("1")
				.setHeader(TM.translate(KaizenTranslator.SOLICITACAO_DATA_SOLICITACAO));
		addColumn(c -> TM.translate(c.isAtivo() ? CoreTranslator.YES : CoreTranslator.NO))
				.setHeader(TM.translate(KaizenTranslator.ATIVO)).setFlexGrow(0);
	}

}
