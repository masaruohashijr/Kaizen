/**
 *
 */
package com.logus.kaizen.view.mondai;

import com.logus.core.model.translation.TM;
import com.logus.core.model.util.Formats;
import com.logus.core.view.list.BeanGrid;
import com.logus.kaizen.model.solicitacao.Comentario;
import com.logus.kaizen.model.translation.KaizenTranslator;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class ComentarioGrid extends BeanGrid<Comentario> {

	/**
	 * Número serial
	 */
	private static final long serialVersionUID = -5792111538275287597L;

	/**
	 * Construtor responsável por criar a tabela contendo as solicitações
	 * persistidas
	 */
	public ComentarioGrid() {
		addColumn(Comentario::getComentario).setWidth("55%").setHeader(TM.translate(KaizenTranslator.COMENTARIO))
				.setSortable(true);
		addColumn(Comentario::getAutor).setWidth("15%").setHeader(TM.translate(KaizenTranslator.COMENTARIO_AUTOR))
				.setSortable(true);
		addColumn(e -> Formats.getDateTimeFormat().format(e.getDataCriacao())).setWidth("15%")
				.setHeader(TM.translate(KaizenTranslator.COMENTARIO_DATA_CRIACAO));
		addColumn(
				e -> (null != e.getDataAtualizacao()) ? Formats.getDateTimeFormat().format(e.getDataAtualizacao()) : "")
						.setWidth("15%").setHeader(TM.translate(KaizenTranslator.COMENTARIO_DATA_ATUALIZACAO));
	}

}
