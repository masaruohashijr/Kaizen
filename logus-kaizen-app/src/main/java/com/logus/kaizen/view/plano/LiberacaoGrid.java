package com.logus.kaizen.view.plano;

import com.logus.core.model.translation.TM;
import com.logus.core.view.list.BeanGrid;
import com.logus.kaizen.model.plano.Liberacao;
import com.logus.kaizen.model.translation.KaizenTranslator;

/**
*
* @author Masaru Ohashi Júnior
* @since 1 de mar de 2019
* @version 1.0.0
*
*/
@SuppressWarnings("serial")
public class LiberacaoGrid extends BeanGrid<Liberacao> {

	/**
	 * Construtor.
	 */
	public LiberacaoGrid() {
		super();
		addColumn(Liberacao::getAmbiente).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.AMBIENTE));
		addColumn(Liberacao::getDataLiberacao).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.LIBERACAO_DATA));
		addColumn(Liberacao::getResponsavel).setFlexGrow(1)
				.setHeader(TM.translate(KaizenTranslator.LIBERACAO_RESPONSAVEL));
		addColumn(Liberacao::getVersao).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.LIBERACAO_VERSAO));
		addColumn(Liberacao::getReferencia).setFlexGrow(0)
				.setHeader(TM.translate(KaizenTranslator.LIBERACAO_REFERENCIA));
	}

}
