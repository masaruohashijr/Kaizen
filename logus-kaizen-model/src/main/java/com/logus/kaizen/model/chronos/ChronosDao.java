/**
 *
 */
package com.logus.kaizen.model.chronos;

import java.util.Collection;

import com.logus.core.model.persistence.DataAccessObject;
import com.logus.kaizen.model.apoio.atendimento.Atendimento;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public interface ChronosDao extends DataAccessObject<Chronos>{
    Collection<Chronos> loadTogurus();

	Collection<Chronos> loadTogurusByResponsavel(String codigoResponsavel);

	Collection<Chronos> loadTogurusOrdenadoPorInicio();

	Chronos loadUltimoToguruDoResponsavel(Atendimento atendimentoOrigem, String codigoUsuario);
}
