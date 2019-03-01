/**
 *
 */
package com.logus.kaizen.view.produto;

import com.logus.kaizen.model.apoio.produto.Produto;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.list.BeanGrid;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class ProdutoGrid extends BeanGrid<Produto> {

	/**
	 * Número serial
	 */
	private static final long serialVersionUID = -5792111538275287597L;

	/**
	 * Construtor responsável por criar a tabela contendo os produtos persistidos
	 */
	public ProdutoGrid() {
		addColumn(Produto::getId).setFlexGrow(0).setHeader(TM.translate(KaizenTranslator.CODIGO));
		addColumn(Produto::getNome).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.NOME));
		addColumn(Produto::getDescricao).setFlexGrow(2).setHeader(TM.translate(KaizenTranslator.PRODUTO_DESCRICAO));
		addColumn(c -> TM.translate(c.isAtivo() ? CoreTranslator.YES : CoreTranslator.NO))
				.setHeader(TM.translate(KaizenTranslator.ATIVO)).setFlexGrow(0);
	}

}
