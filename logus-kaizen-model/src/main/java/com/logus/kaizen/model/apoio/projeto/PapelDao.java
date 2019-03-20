package com.logus.kaizen.model.apoio.projeto;

import java.util.Collection;

import com.logus.core.model.persistence.DataAccessObject;

/**
 * Criei uma interface pai, para evitar a repetição do método Collection<T>
 * loadAll, e também para agilizar o desenvolvimento.
 *
 * @author Masaru Ohashi Júnior
 * @version 1.0.0
 * @since 1 de mar de 2019
 *
 */
public interface PapelDao extends DataAccessObject<Papel> {

	Collection<Papel> loadPapeisDoProjeto(Projeto projeto);
}
