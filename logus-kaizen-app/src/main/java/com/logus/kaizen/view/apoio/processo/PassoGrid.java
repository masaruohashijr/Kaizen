package com.logus.kaizen.view.apoio.processo;

import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.list.BeanGrid;
import com.logus.kaizen.model.apoio.processo.Passo;
import com.logus.kaizen.model.translation.KaizenTranslator;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
public class PassoGrid extends BeanGrid<Passo> {

	/**
	 * Construtor.
	 */
	public PassoGrid() {
		super();
		addColumn(Passo::getNome).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.NOME)).setSortable(true);
		addColumn(Passo::getTransicao).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.PASSO_TRANSICAO)).setSortable(true);
		addColumn(Passo::getAtendimentoOrigem).setFlexGrow(1)
				.setHeader(TM.translate(KaizenTranslator.PASSO_ATENDIMENTO_ORIGEM)).setSortable(true);
		addColumn(Passo::getAtendimentoDestino).setFlexGrow(1)
				.setHeader(TM.translate(KaizenTranslator.PASSO_ATENDIMENTO_DESTINO)).setSortable(true);
		addColumn(Passo::getResolucao).setFlexGrow(1)
				.setHeader(TM.translate(KaizenTranslator.PASSO_RESOLUCAO)).setSortable(true);
		addColumn(c -> TM.translate(c.isAtivo() ? CoreTranslator.YES : CoreTranslator.NO))
				.setHeader(TM.translate(KaizenTranslator.ATIVO)).setFlexGrow(0).setSortable(true);
	}

}
