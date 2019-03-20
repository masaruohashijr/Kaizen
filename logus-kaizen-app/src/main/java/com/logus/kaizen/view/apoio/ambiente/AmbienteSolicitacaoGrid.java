package com.logus.kaizen.view.apoio.ambiente;

import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.list.BeanGrid;
import com.logus.kaizen.model.apoio.ambiente.Ambiente;
import com.logus.kaizen.model.translation.KaizenTranslator;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
public class AmbienteSolicitacaoGrid extends BeanGrid<Ambiente> {

	/**
	 * Construtor.
	 */
	public AmbienteSolicitacaoGrid() {
		super();
		addColumn(Ambiente::getCliente).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.CLIENTE)).setSortable(true);
		addColumn(Ambiente::getAcronimo).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.AMBIENTE_ACRONIMO)).setSortable(true);
		addColumn(Ambiente::getNome).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.AMBIENTE)).setSortable(true);
		addColumn(Ambiente::getHost).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.AMBIENTE_HOST)).setSortable(true);
		addColumn(c -> TM.translate(c.isAtivo() ? CoreTranslator.YES : CoreTranslator.NO))
				.setHeader(TM.translate(KaizenTranslator.ATIVO)).setFlexGrow(0).setSortable(true);
	}

}
