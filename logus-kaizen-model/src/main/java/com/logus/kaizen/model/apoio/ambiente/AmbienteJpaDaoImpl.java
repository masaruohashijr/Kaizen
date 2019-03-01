package com.logus.kaizen.model.apoio.ambiente;

import java.util.Collection;

import com.logus.kaizen.model.apoio.ApoioDataServiceImpl;
import com.logus.core.model.aut.login.LoginManager;
import com.logus.core.model.persistence.jpa.AbstractJpaDao;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class AmbienteJpaDaoImpl extends AbstractJpaDao<Ambiente> implements AmbienteDao {

	public static AmbienteJpaDaoImpl instance;

	public static AmbienteJpaDaoImpl getInstance() {
		if (instance == null) {
			instance = new AmbienteJpaDaoImpl();
		}
		return instance;
	}

	public AmbienteJpaDaoImpl() {
		super(ApoioDataServiceImpl.getEMF());
	}

	@Override
	public void assignTo(Ambiente source, Ambiente dest) {
		dest.assignFrom(source);
	}

	@Override
	public String getTableName() {
		return Ambiente.TABLE_NAME;
	}

	@Override
	protected boolean queryEnabled() {
		return LoginManager.getAccessControl().hasAccess(FuncionalidadeAmbiente.FUNC_AMBIENTE_CONSULTAR.getId());
	}

	@Override
	protected boolean updateEnabled() {
		return LoginManager.getAccessControl().hasAccess(FuncionalidadeAmbiente.FUNC_AMBIENTE_CADASTRAR.getId());
	}

	@Override
	public Collection<Ambiente> loadAmbientes() {
		return loadCollectionByFilter("flg_ativo", Boolean.TRUE);
	}

}
