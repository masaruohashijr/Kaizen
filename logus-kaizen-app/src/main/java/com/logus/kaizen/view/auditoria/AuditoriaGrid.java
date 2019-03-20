/**
 *
 */
package com.logus.kaizen.view.auditoria;

import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.model.util.Formats;
import com.logus.core.view.list.BeanGrid;
import com.logus.kaizen.model.auditoria.ItemMudanca;
import com.logus.kaizen.model.translation.KaizenTranslator;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class AuditoriaGrid extends BeanGrid<ItemMudanca> {

	/**
	 * Número serial
	 */
	private static final long serialVersionUID = -5792111538275287597L;

	/**
	 * Construtor responsável por criar a tabela contendo os status persistidos
	 */
	public AuditoriaGrid() {
		addColumn(ItemMudanca::getNomeEntidade).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.NOME))
				.setSortable(true);
		addColumn(ItemMudanca::getAutor).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.AUDITORIA_AUTOR))
				.setSortable(true);
		addColumn(data -> (null != data.getDataMudanca()) ? Formats.getDateTimeFormat().format(data.getDataMudanca()) : "")
				.setFlexGrow(3).setSortable(true).setHeader(TM.translate(KaizenTranslator.AUDITORIA_DATA_MUDANCA));
		addColumn(ItemMudanca::getNomeCampo).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.AUDITORIA_NOME_CAMPO)).setSortable(true);
		addColumn(ItemMudanca::getValorAntigo).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.AUDITORIA_VALOR_ANTIGO)).setSortable(true);
		addColumn(ItemMudanca::getValorNovo).setFlexGrow(1).setHeader(TM.translate(KaizenTranslator.AUDITORIA_VALOR_NOVO)).setSortable(true);
		addColumn(c -> TM.translate(c.isAtivo() ? CoreTranslator.YES : CoreTranslator.NO))
				.setHeader(TM.translate(KaizenTranslator.ATIVO)).setFlexGrow(0).setSortable(true);
	}

}
