/**
 *
 */
package com.logus.kaizen.view.atendimento;

import com.logus.kaizen.model.apoio.atendimento.Atendimento;
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
public class AtendimentoGrid extends BeanGrid<Atendimento> {

	/**
	 * Número serial
	 */
	private static final long serialVersionUID = -5792111538275287597L;

	/**
	 * Construtor responsável por criar a tabela contendo os status persistidos
	 */
	public AtendimentoGrid() {
		addColumn(Atendimento::getId).setFlexGrow(0).setHeader(TM.translate(KaizenTranslator.CODIGO));
		addColumn(Atendimento::getTitulo).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.NOME));
		addColumn(c -> TM.translate(c.isAtivo() ? CoreTranslator.YES : CoreTranslator.NO))
				.setHeader(TM.translate(KaizenTranslator.ATIVO)).setFlexGrow(0);
	}

}
