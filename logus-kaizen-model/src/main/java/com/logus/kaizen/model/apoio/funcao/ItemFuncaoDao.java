/**
 *
 */
package com.logus.kaizen.model.apoio.funcao;

import java.util.Collection;

import com.logus.core.model.persistence.DataAccessObject;

/**
 * @author Masaru Ohashi Júnior
 * @version 1.0.0
 * @since 1 de mar de 2019
 */
public interface ItemFuncaoDao extends DataAccessObject<ItemFuncao>{
    Collection<ItemFuncao> loadItensFuncoesPorUsuario(String codigoUsuario);
}
