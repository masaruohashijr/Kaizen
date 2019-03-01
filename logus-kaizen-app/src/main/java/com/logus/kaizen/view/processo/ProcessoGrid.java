/**
 *
 */
package com.logus.kaizen.view.processo;

import com.logus.kaizen.model.processo.Processo;
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
public class ProcessoGrid extends BeanGrid<Processo> {

	/**
	 * Número serial
	 */
	private static final long serialVersionUID = -5792111538275287597L;

	/**
	 * Construtor responsável por criar a tabela contendo os processos persistidos
	 */
	public ProcessoGrid() {
		addColumn(Processo::getId).setFlexGrow(0).setHeader(TM.translate(KaizenTranslator.CODIGO));
		addColumn(Processo::getNome).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.NOME));
		addColumn(Processo::getDescricao).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.PROCESSO_DESCRICAO));
		addColumn(c -> TM.translate(c.isAtivo() ? CoreTranslator.YES : CoreTranslator.NO))
				.setHeader(TM.translate(KaizenTranslator.ATIVO)).setFlexGrow(0);
	}

}
