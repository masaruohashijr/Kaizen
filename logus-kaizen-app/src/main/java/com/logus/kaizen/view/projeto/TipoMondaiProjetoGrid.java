/**
 *
 */
package com.logus.kaizen.view.projeto;

import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.list.BeanGrid;
import com.logus.kaizen.model.apoio.tipomondai.TipoMondaiProjeto;
import com.logus.kaizen.model.translation.KaizenTranslator;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class TipoMondaiProjetoGrid extends BeanGrid<TipoMondaiProjeto> {

	/**
	 * Número serial
	 */
	private static final long serialVersionUID = -5792111538275287597L;

	/**
	 * Construtor responsável por criar a tabela contendo os produtos persistidos
	 */
	public TipoMondaiProjetoGrid() {
		addColumn(TipoMondaiProjeto::getProjeto).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.PROJETO));
		addColumn(TipoMondaiProjeto::getProcesso).setFlexGrow(1)
				.setHeader(TM.translate(KaizenTranslator.PROCESSO));
		addColumn(c -> TM.translate(c.isAtivo() ? CoreTranslator.YES : CoreTranslator.NO))
				.setHeader(TM.translate(KaizenTranslator.ATIVO)).setFlexGrow(0);
	}

}
