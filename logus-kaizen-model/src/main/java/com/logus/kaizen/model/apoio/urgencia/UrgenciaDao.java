/**
 *
 */
package com.logus.kaizen.model.apoio.urgencia;

import java.util.Collection;

import com.logus.core.model.persistence.DataAccessObject;

/**
 * @author Masaru Ohashi Júnior
 * @version 1.0.0
 * @since 1 de mar de 2019
 */

public interface UrgenciaDao extends DataAccessObject<Urgencia> {
	Collection<Urgencia> loadUrgencias();
}
