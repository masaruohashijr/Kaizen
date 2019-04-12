/**
 *
 */
package com.logus.kaizen.view.chronos;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

import com.logus.common.base.Strings;
import com.logus.core.model.translation.TM;
import com.logus.core.model.util.Formats;
import com.logus.core.view.list.BeanGrid;
import com.logus.kaizen.model.chronos.Chronos;
import com.logus.kaizen.model.translation.KaizenTranslator;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class ChronosGrid extends BeanGrid<Chronos> {

	/**
	 * Número serial
	 */
	private static final long serialVersionUID = -5792111538275287597L;

	/**
	 * Construtor responsável por criar a tabela contendo as solicitações
	 * persistidas
	 */
	public ChronosGrid() {
		addColumn(data -> StringUtils.capitalize(data.getCodigoResponsavel())).setFlexGrow(1).setHeader("Responsável")
				.setSortable(true);
		addColumn(data -> (null != data.getSolicitacao()) ? data.getSolicitacao() : data.getTituloChronos()).setFlexGrow(3).setHeader("Atividade")
				.setSortable(true);
		addColumn(Chronos::getAtendimento).setFlexGrow(1).setHeader("Status").setSortable(true);
		addColumn(
				data -> (null != data.getDataInicio()) ? Formats.getDateTimeFormat().format(data.getDataInicio()) : "")
						.setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.CHRONOS_DATA_INICIO)).setSortable(true);
		addColumn(data -> (null != data.getDataFim()) ? Formats.getDateTimeFormat().format(data.getDataFim()) : "")
				.setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.CHRONOS_DATA_FIM)).setSortable(true);
		addColumn(data -> {
			Date inicio = data.getDataInicio();
			long tempoIni = inicio.getTime();
			long tempoFim = System.currentTimeMillis();
			if (null != data.getDataFim()) {
				tempoFim = data.getDataFim().getTime();
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
