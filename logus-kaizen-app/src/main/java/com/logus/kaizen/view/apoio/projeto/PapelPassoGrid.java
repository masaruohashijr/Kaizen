/**
 *
 */
package com.logus.kaizen.view.apoio.projeto;

import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.list.BeanGrid;
import com.logus.kaizen.model.apoio.tipomondai.PapelPassoItem;
import com.logus.kaizen.model.translation.KaizenTranslator;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class PapelPassoGrid extends BeanGrid<PapelPassoItem> {

	/**
	 * Número serial
	 */
	private static final long serialVersionUID = -5792111538275287597L;

	/**
	 * Construtor responsável por criar a tabela contendo os papéis dos passos persistidos
	 */
	public PapelPassoGrid() {
		addColumn(PapelPassoItem::getTransicaoPasso).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.PASSO_TRANSICAO)).setSortable(true);
		addColumn(PapelPassoItem::getAtendimentoOrigemPasso).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.PASSO_ATENDIMENTO_ORIGEM)).setSortable(true);
		addColumn(PapelPassoItem::getAtendimentoDestinoPasso).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.PASSO_ATENDIMENTO_DESTINO)).setSortable(true);
		addColumn(PapelPassoItem::getProcessoPasso).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.PROCESSO)).setSortable(true);
		addColumn(PapelPassoItem::getPapel).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.PROJETO_PAPEL)).setSortable(true);
		addColumn(c -> TM.translate(c.isAtivo() ? CoreTranslator.YES : CoreTranslator.NO))
				.setHeader(TM.translate(KaizenTranslator.ATIVO)).setFlexGrow(0).setSortable(true);
	}

}
