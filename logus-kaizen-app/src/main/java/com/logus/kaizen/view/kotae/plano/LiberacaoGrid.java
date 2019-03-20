package com.logus.kaizen.view.kotae.plano;

import com.logus.core.model.translation.TM;
import com.logus.core.model.util.Formats;
import com.logus.core.view.list.BeanGrid;
import com.logus.kaizen.model.kotae.plano.Liberacao;
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
		addColumn(Liberacao::getAmbiente).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.AMBIENTE)).setSortable(true);
		addColumn(data -> (null!=data.getDataAtualizacao())?Formats.getDateFormat().format(data.getDataAtualizacao()):"").setFlexGrow(1).setSortable(true)
        .setHeader(TM.translate(KaizenTranslator.LIBERACAO_DATA));
		addColumn(Liberacao::getCodigoResponsavel).setFlexGrow(1)
				.setHeader(TM.translate(KaizenTranslator.LIBERACAO_RESPONSAVEL)).setSortable(true);
		addColumn(Liberacao::getVersao).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.LIBERACAO_VERSAO)).setSortable(true);
		addColumn(Liberacao::getReferencia).setFlexGrow(0)
				.setHeader(TM.translate(KaizenTranslator.LIBERACAO_REFERENCIA)).setSortable(true);
	}

}
