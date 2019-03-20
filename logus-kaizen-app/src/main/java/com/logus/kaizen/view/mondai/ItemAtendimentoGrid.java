package com.logus.kaizen.view.mondai;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.logus.common.base.Strings;
import com.logus.core.model.translation.TM;
import com.logus.core.model.util.Formats;
import com.logus.core.view.list.BeanGrid;
import com.logus.kaizen.model.solicitacao.ItemAtendimento;
import com.logus.kaizen.model.translation.KaizenTranslator;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
public class ItemAtendimentoGrid extends BeanGrid<ItemAtendimento> {

	/**
	 * Construtor.
	 */
	public ItemAtendimentoGrid() {
		super();
		addColumn(ItemAtendimento::getAtendimento).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.ATENDIMENTO))
				.setSortable(true);
		addColumn(data -> Formats.getDateTimeFormat().format(data.getDataInicioVigencia())).setFlexGrow(1)
				.setHeader(TM.translate(KaizenTranslator.ITEM_ATENDIMENTO_DATA_INICIO_VIGENCIA)).setSortable(true);
		addColumn(data -> {
			Date dataFimVigencia = data.getDataFimVigencia();
			return (null != dataFimVigencia) ? Formats.getDateTimeFormat().format(dataFimVigencia) : "";
		}).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.ITEM_ATENDIMENTO_DATA_FIM_VIGENCIA))
				.setSortable(true);
		addColumn(data -> {
			Date inicio = data.getDataInicioVigencia();
			long tempoIni = inicio.getTime();
			long tempoFim = System.currentTimeMillis();
			if (null != data.getDataFimVigencia()) {
				tempoFim = data.getDataFimVigencia().getTime();
			} else {
				return "";
			}
			long diferenca = tempoFim - tempoIni;
			return getDuracao(diferenca);
		}).setFlexGrow(1).setHeader("Duração").setSortable(true);

	}

	private String getDuracao(long diferenca) {
		long segundos = TimeUnit.MILLISECONDS.toSeconds(diferenca);
		long s = segundos % 60;
		long h = segundos / 60;
		long m = h % 60;
		h = h / 60;
		return Strings.completa(Long.toString(h), "0", 2, true) + ":" + Strings.completa(Long.toString(m), "0", 2, true)
				+ ":" + Strings.completa(Long.toString(s), "0", 2, true);
	}

}
