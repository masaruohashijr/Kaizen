package com.logus.kaizen.view.processo;

import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.list.BeanGrid;
import com.logus.kaizen.model.processo.Passo;
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
		addColumn(Passo::getId).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.CODIGO));
		addColumn(Passo::getNome).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.NOME));
		addColumn(Passo::getTransicao).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.PASSO_TRANSICAO));
		addColumn(Passo::getAtendimentoOrigem).setFlexGrow(1)
				.setHeader(TM.translate(KaizenTranslator.PASSO_ATENDIMENTO_ORIGEM));
		addColumn(Passo::getAtendimentoDestino).setFlexGrow(1)
				.setHeader(TM.translate(KaizenTranslator.PASSO_ATENDIMENTO_DESTINO));
		addColumn(c -> TM.translate(c.isAtivo() ? CoreTranslator.YES : CoreTranslator.NO))
				.setHeader(TM.translate(KaizenTranslator.ATIVO)).setFlexGrow(0);
	}

}
