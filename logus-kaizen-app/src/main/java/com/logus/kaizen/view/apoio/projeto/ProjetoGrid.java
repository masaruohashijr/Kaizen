/**
 *
 */
package com.logus.kaizen.view.apoio.projeto;

import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.list.BeanGrid;
import com.logus.kaizen.model.apoio.projeto.Projeto;
import com.logus.kaizen.model.translation.KaizenTranslator;

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
		addColumn(Projeto::getCliente).setFlexGrow(2).setHeader(TM.translate(KaizenTranslator.CLIENTE))
				.setSortable(true);
		addColumn(Projeto::getNome).setFlexGrow(3).setHeader(TM.translate(KaizenTranslator.NOME)).setSortable(true);
		addColumn(Projeto::getPrefixoMondai).setFlexGrow(1)
				.setHeader(TM.translate(KaizenTranslator.PROJETO_CHAVE_MONDAI)).setSortable(true);
		addColumn(Projeto::getPrefixoJira).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.PROJETO_CHAVE_JIRA))
				.setSortable(true);
		addColumn(c -> TM.translate(c.isAtivo() ? CoreTranslator.YES : CoreTranslator.NO))
				.setHeader(TM.translate(KaizenTranslator.ATIVO)).setFlexGrow(0).setSortable(true);
	}

}
