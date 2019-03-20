package com.logus.kaizen.view.kotae.configuracao;

import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.list.BeanGrid;
import com.logus.kaizen.model.kotae.configuracao.KotaeConfiguracao;
import com.logus.kaizen.model.translation.KaizenTranslator;

public class KotaeConfiguracaoGrid extends BeanGrid<KotaeConfiguracao> {

	/**
	 * Número serial
	 */
	private static final long serialVersionUID = -5792111538275287597L;

	/**
	 * Construtor responsável por criar a tabela contendo as solicitações
	 * persistidas
	 */
	public KotaeConfiguracaoGrid() {
		addColumn(KotaeConfiguracao::getId).setFlexGrow(0).setSortable(true).setHeader(TM.translate(KaizenTranslator.CODIGO));
		addColumn(KotaeConfiguracao::getTipoKotae).setFlexGrow(1).setSortable(true).setHeader(TM.translate(KaizenTranslator.KOTAE_TIPO));
		addColumn(KotaeConfiguracao::getProcesso).setFlexGrow(1).setSortable(true).setHeader(TM.translate(KaizenTranslator.PROCESSO));
		addColumn(c -> TM.translate(c.isAtivo() ? CoreTranslator.YES : CoreTranslator.NO))
				                           .setHeader(TM.translate(KaizenTranslator.ATIVO)).setSortable(true).setFlexGrow(0);
	}

}
