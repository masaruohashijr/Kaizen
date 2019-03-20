/**
 *
 */
package com.logus.kaizen.view.apoio.funcao;

import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.list.BeanGrid;
import com.logus.kaizen.model.apoio.funcao.Funcao;
import com.logus.kaizen.model.translation.KaizenTranslator;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class FuncaoGrid extends BeanGrid<Funcao> {

	/**
	 * Número serial
	 */
	private static final long serialVersionUID = -5792111538275287597L;

	/**
	 * Construtor responsável por criar a tabela contendo os produtos persistidos
	 */
	public FuncaoGrid() {
//		addColumn(Funcao::getId).setFlexGrow(0).setHeader(TM.translate(KaizenTranslator.CODIGO)).setSortable(true);
		addColumn(Funcao::getNome).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.NOME)).setSortable(true);
		addColumn(Funcao::getDescricao).setFlexGrow(2).setHeader(TM.translate(KaizenTranslator.PRODUTO_DESCRICAO)).setSortable(true);
		addColumn(c -> TM.translate(c.isAtivo() ? CoreTranslator.YES : CoreTranslator.NO))
				.setHeader(TM.translate(KaizenTranslator.ATIVO)).setFlexGrow(0).setSortable(true);
	}

}
