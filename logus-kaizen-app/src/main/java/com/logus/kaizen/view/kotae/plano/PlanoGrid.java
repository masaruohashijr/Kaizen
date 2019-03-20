/**
 *
 */
package com.logus.kaizen.view.kotae.plano;

import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.list.BeanGrid;
import com.logus.kaizen.model.kotae.plano.Plano;
import com.logus.kaizen.model.translation.KaizenTranslator;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class PlanoGrid extends BeanGrid<Plano> {

	/**
	 * Número serial
	 */
	private static final long serialVersionUID = -5792111538275287597L;

	/**
	 * Construtor responsável por criar a tabela contendo as solicitações
	 * persistidas
	 */
	public PlanoGrid() {
		addColumn(Plano::getChaveBuild).setFlexGrow(0).setHeader("Id").setSortable(true);
		addColumn(Plano::getReferencia).setFlexGrow(3).setHeader("Referência").setSortable(true);
		addColumn(Plano::getAtendimento).setFlexGrow(1).setHeader("Atendimento").setSortable(true);
		addColumn(Plano::getResolucao).setFlexGrow(1).setHeader("Resolução").setSortable(true);
		addColumn(c -> TM.translate(c.isAtivo() ? CoreTranslator.YES : CoreTranslator.NO))
				.setHeader(TM.translate(KaizenTranslator.ATIVO)).setFlexGrow(0).setSortable(true);
	}

}
