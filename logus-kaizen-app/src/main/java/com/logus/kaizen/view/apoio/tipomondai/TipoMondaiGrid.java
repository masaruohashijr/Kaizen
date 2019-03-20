/**
 *
 */
package com.logus.kaizen.view.apoio.tipomondai;

import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.list.BeanGrid;
import com.logus.kaizen.model.apoio.tipomondai.TipoMondai;
import com.logus.kaizen.model.translation.KaizenTranslator;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class TipoMondaiGrid extends BeanGrid<TipoMondai> {

	/**
	 * Número serial
	 */
	private static final long serialVersionUID = -5792111538275287597L;

	/**
	 * Construtor responsável por criar a tabela contendo os produtos persistidos
	 */
	public TipoMondaiGrid() {
		addColumn(TipoMondai::getNome).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.NOME)).setSortable(true);
		addColumn(TipoMondai::getDescricao).setFlexGrow(1)
				.setHeader(TM.translate(KaizenTranslator.TIPO_MONDAI_DESCRICAO)).setSortable(true);
		addColumn(c -> TM.translate(c.isAtivo() ? CoreTranslator.YES : CoreTranslator.NO))
				.setHeader(TM.translate(KaizenTranslator.ATIVO)).setFlexGrow(0).setSortable(true);
	}

}
