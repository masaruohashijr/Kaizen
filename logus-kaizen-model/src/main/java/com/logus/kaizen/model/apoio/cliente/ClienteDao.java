/**
 *
 */
package com.logus.kaizen.model.apoio.cliente;

import java.util.Collection;

import com.logus.core.model.persistence.DataAccessObject;

/**
 * @author Masaru Ohashi Júnior
 * @version 1.0.0
 * @since 5 de fev de 2019
 */
public interface ClienteDao extends DataAccessObject<Cliente>{
    Collection<Cliente> loadClientes();
}
