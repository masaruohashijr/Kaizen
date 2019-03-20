/**
 *
 */
package com.logus.kaizen.model.kotae.configuracao;

import java.util.Collection;

import com.logus.core.model.persistence.DataAccessObject;
import com.logus.kaizen.model.kotae.configuracao.KotaeConfiguracao.TipoKotae;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public interface KotaeConfiguracaoDao extends DataAccessObject<KotaeConfiguracao>{
    Collection<KotaeConfiguracao> loadConfiguracoes();
	Collection<KotaeConfiguracao> loadConfiguracaoByTipo(TipoKotae toguru);
}
