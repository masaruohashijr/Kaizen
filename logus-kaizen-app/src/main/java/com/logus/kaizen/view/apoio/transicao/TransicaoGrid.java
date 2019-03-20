/**
 *
 */
package com.logus.kaizen.view.apoio.transicao;

import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.list.BeanGrid;
import com.logus.kaizen.model.apoio.transicao.Transicao;
import com.logus.kaizen.model.translation.KaizenTranslator;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class TransicaoGrid extends BeanGrid<Transicao> {

	/**
	 * Número serial
	 */
	private static final long serialVersionUID = -5792111538275287597L;

	/**
	 * Construtor responsável por criar a tabela contendo as transicoes persistidas
	 */
	public TransicaoGrid() {
//		addColumn(Transicao::getId).setFlexGrow(0).setHeader(TM.translate(KaizenTranslator.CODIGO)).setSortable(true);
		addColumn(Transicao::getNome).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.NOME)).setSortable(true);
		addColumn(Transicao::getDescricao).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.TRANSICAO_DESCRICAO)).setSortable(true);
		addColumn(c -> TM.translate(c.isAtivo() ? CoreTranslator.YES : CoreTranslator.NO))
				.setHeader(TM.translate(KaizenTranslator.ATIVO)).setFlexGrow(0).setSortable(true);
	}

}
