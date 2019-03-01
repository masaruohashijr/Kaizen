/**
 *
 */
package com.logus.kaizen.view.projeto;

import com.logus.kaizen.model.projeto.Projeto;
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
public class ProjetoGrid extends BeanGrid<Projeto> {

	/**
	 * Número serial
	 */
	private static final long serialVersionUID = -5792111538275287597L;

	/**
	 * Construtor responsável por criar a tabela contendo os projetos persistidos
	 */
	public ProjetoGrid() {
		addColumn(Projeto::getId).setFlexGrow(0).setHeader(TM.translate(KaizenTranslator.CODIGO));
		addColumn(Projeto::getChaveMondai).setFlexGrow(1)
				.setHeader(TM.translate(KaizenTranslator.PROJETO_CHAVE_MONDAI));
		addColumn(Projeto::getChaveJira).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.PROJETO_CHAVE_JIRA));
		addColumn(Projeto::getNome).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.NOME));
		addColumn(Projeto::getCliente).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.CLIENTE));
		addColumn(c -> TM.translate(c.isAtivo() ? CoreTranslator.YES : CoreTranslator.NO))
				.setHeader(TM.translate(KaizenTranslator.ATIVO)).setFlexGrow(0);
	}

}
