/**
 *
 */
package com.logus.kaizen.model.apoio;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.logus.kaizen.model.apoio.usuario.UsuarioDao;
import com.logus.kaizen.model.apoio.usuario.UsuarioJpaDaoImpl;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class UsuarioDataServiceImpl extends UsuarioDataService {

	/**
	 * Nome da persistence-unit JPA.
	 */
	private static final String SECURITY_PERSISTENCE_UNIT_NAME = "Security_Persistence_Unit";

	/**
	 * Singleton para factory de gerência de Entidades.
	 */
	private static EntityManagerFactory emf;

	/**
	 * @return {@link #emf}.
	 */
	public static EntityManagerFactory getEMF() {
		if (emf == null) {
			emf = (EntityManagerFactory) Persistence.createEntityManagerFactory(SECURITY_PERSISTENCE_UNIT_NAME);
		}
		return emf;
	}

	@Override
	public UsuarioDao getUsuarioDao() {
		return UsuarioJpaDaoImpl.getInstance();
	}



}
