/**
 *
 */
package com.logus.kaizen.view.apoio.cliente;

import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.list.BeanGrid;
import com.logus.kaizen.model.apoio.cliente.Cliente;
import com.logus.kaizen.model.translation.KaizenTranslator;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class ClienteGrid extends BeanGrid<Cliente> {

	/**
	 * Número serial
	 */
	private static final long serialVersionUID = -5792111538275287597L;

	/**
	 * Construtor responsável por criar a tabela contendo os clientes persistidos
	 */
	public ClienteGrid() {
		addColumn(Cliente::getNome).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.CLIENTE_CHAVE_JIRA)).setSortable(true);
		addColumn(Cliente::getStrAmbientes).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.CLIENTE_AMBIENTES)).setSortable(true);
		addColumn(c -> TM.translate(c.isAtivo() ? CoreTranslator.YES : CoreTranslator.NO))
				.setHeader(TM.translate(KaizenTranslator.ATIVO)).setFlexGrow(0).setSortable(true);
	}

}
