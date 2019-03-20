package com.logus.kaizen.view.mondai;

import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.model.util.Formats;
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
		addColumn(ItemSolicitacao::getSolicitacao).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.MONDAI)).setSortable(true);
		addColumn(ItemSolicitacao::getAmbiente).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.AMBIENTE)).setSortable(true);
		addColumn(ItemSolicitacao::getUrgencia).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.URGENCIA)).setSortable(true);
		addColumn(ItemSolicitacao::getAtendimento).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.ATENDIMENTO)).setSortable(true);
//		addColumn(e -> Formats.getDateTimeFormat().format(e.getDataUltimoAtendimento())).setFlexGrow(1)
//				.setHeader(TM.translate(KaizenTranslator.ITEM_SOLICITACAO_DATA_ULTIMO_ATENDIMENTO));
		addColumn(ItemSolicitacao::getCodigoResponsavel).setFlexGrow(1)
				.setHeader(TM.translate(KaizenTranslator.ITEM_SOLICITACAO_RESPONSAVEL)).setSortable(true);
//		addColumn(c -> TM.translate(c.isSolicitado() ? CoreTranslator.YES : CoreTranslator.NO))
//		.setHeader(TM.translate(KaizenTranslator.ITEM_SOLICITACAO_SOLICITADO)).setFlexGrow(0).setSortable(true);
	}

}
