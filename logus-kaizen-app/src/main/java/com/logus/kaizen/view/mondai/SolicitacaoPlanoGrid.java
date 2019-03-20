/**
 *
 */
package com.logus.kaizen.view.mondai;

import org.apache.commons.lang3.StringUtils;

import com.logus.core.model.translation.TM;
import com.logus.core.model.util.Formats;
import com.logus.core.view.list.BeanGrid;
import com.logus.kaizen.model.solicitacao.Solicitacao;
import com.logus.kaizen.model.translation.KaizenTranslator;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class SolicitacaoPlanoGrid extends BeanGrid<Solicitacao> {

	/**
	 * Número serial
	 */
	private static final long serialVersionUID = -5792111538275287597L;

	/**
	 * Construtor responsável por criar a tabela contendo as solicitações
	 * persistidas
	 */
	public SolicitacaoPlanoGrid() {
		addColumn(Solicitacao::getChaveMondai).setFlexGrow(0).setSortable(true)
				.setHeader(TM.translate(KaizenTranslator.SOLICITACAO_MONDAI));
		addColumn(Solicitacao::getTituloMondai).setFlexGrow(4).setSortable(true)
				.setHeader(TM.translate(KaizenTranslator.SOLICITACAO_TITULO_MONDAI));
		addColumn(data -> (null != data.getDataCommit())
				? Formats.getDateFormat().format(data.getDataCommit())
				: "").setFlexGrow(3).setSortable(true)
						.setHeader(TM.translate(KaizenTranslator.SOLICITACAO_DATA_COMMIT));
		addColumn(data -> StringUtils.capitalize(data.getCodigoResponsavelAtual())).setFlexGrow(1).setSortable(true)
				.setHeader(TM.translate(KaizenTranslator.SOLICITACAO_CODIGO_RESPONSAVEL));
		addColumn(Solicitacao::getAtendimento).setFlexGrow(1).setSortable(true)
				.setHeader(TM.translate(KaizenTranslator.ATENDIMENTO));
		addColumn(
				data -> (null != data.getDataFicarPronto()) ? Formats.getDateFormat().format(data.getDataFicarPronto())
						: "").setFlexGrow(3).setSortable(true)
								.setHeader(TM.translate(KaizenTranslator.SOLICITACAO_DATA_FICAR_PRONTO));
	}

}
