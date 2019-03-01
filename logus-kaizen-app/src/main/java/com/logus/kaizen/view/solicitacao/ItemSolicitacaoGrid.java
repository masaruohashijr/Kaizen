package com.logus.kaizen.view.solicitacao;

import com.logus.core.model.translation.TM;
import com.logus.core.view.list.BeanGrid;
import com.logus.kaizen.model.solicitacao.ItemSolicitacao;
import com.logus.kaizen.model.translation.KaizenTranslator;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
public class ItemSolicitacaoGrid extends BeanGrid<ItemSolicitacao> {

	/**
	 * Construtor.
	 */
	public ItemSolicitacaoGrid() {
		super();
		addColumn(ItemSolicitacao::getAmbiente).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.AMBIENTE));
		addColumn(ItemSolicitacao::getUrgencia).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.URGENCIA));
		addColumn(ItemSolicitacao::getAtendimento).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.ATENDIMENTO));
		addColumn(ItemSolicitacao::getDataUltimoAtendimento).setFlexGrow(1)
				.setHeader(TM.translate(KaizenTranslator.ITEM_SOLICITACAO_DATA_ULTIMO_ATENDIMENTO));
		addColumn(ItemSolicitacao::getGcm).setFlexGrow(1)
				.setHeader(TM.translate(KaizenTranslator.ITEM_SOLICITACAO_GCM));
	}

}
