/**
 *
 */
package com.logus.kaizen.view.apoio.projeto;

import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.list.BeanGrid;
import com.logus.kaizen.model.apoio.tipomondai.FuncaoPassoItem;
import com.logus.kaizen.model.translation.KaizenTranslator;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class FuncaoPassoGrid extends BeanGrid<FuncaoPassoItem> {

	/**
	 * Número serial
	 */
	private static final long serialVersionUID = -5792111538275287597L;

	/**
	 * Construtor responsável por criar a tabela contendo os papéis dos passos persistidos
	 */
	public FuncaoPassoGrid() {
		addColumn(FuncaoPassoItem::getTransicaoPasso).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.PASSO_TRANSICAO)).setSortable(true);
		addColumn(FuncaoPassoItem::getAtendimentoOrigemPasso).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.PASSO_ATENDIMENTO_ORIGEM)).setSortable(true);
		addColumn(FuncaoPassoItem::getAtendimentoDestinoPasso).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.PASSO_ATENDIMENTO_DESTINO)).setSortable(true);
		addColumn(FuncaoPassoItem::getProcessoPasso).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.PROCESSO)).setSortable(true);
		addColumn(FuncaoPassoItem::getFuncao).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.FUNCAO)).setSortable(true);
		addColumn(c -> TM.translate(c.isAtivo() ? CoreTranslator.YES : CoreTranslator.NO))
				.setHeader(TM.translate(KaizenTranslator.ATIVO)).setFlexGrow(0).setSortable(true);
	}

}
