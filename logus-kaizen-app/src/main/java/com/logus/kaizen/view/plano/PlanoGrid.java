/**
 *
 */
package com.logus.kaizen.view.plano;

import com.logus.kaizen.model.plano.Plano;
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
		addColumn(Plano::getId).setFlexGrow(1).setHeader("Id");
		addColumn(Plano::getReferencia).setFlexGrow(1).setHeader("Referência");
		addColumn(c -> TM.translate(c.isAtivo() ? CoreTranslator.YES : CoreTranslator.NO))
				.setHeader(TM.translate(KaizenTranslator.ATIVO)).setFlexGrow(0);
	}

}
