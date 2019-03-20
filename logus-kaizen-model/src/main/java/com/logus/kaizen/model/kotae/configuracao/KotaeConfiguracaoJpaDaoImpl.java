/**
 *
 */
package com.logus.kaizen.model.kotae.configuracao;

import java.util.Collection;

import com.logus.core.model.aut.login.LoginManager;
import com.logus.core.model.persistence.jpa.AbstractJpaDao;
import com.logus.kaizen.model.TableNames;
import com.logus.kaizen.model.apoio.ApoioDataServiceImpl;
import com.logus.kaizen.model.kotae.configuracao.KotaeConfiguracao.TipoKotae;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class KotaeConfiguracaoJpaDaoImpl extends AbstractJpaDao<KotaeConfiguracao> implements KotaeConfiguracaoDao {

	/**
	 * @param emf
	 */
	public KotaeConfiguracaoJpaDaoImpl() {
		super(ApoioDataServiceImpl.getEMF());
	}

	private static KotaeConfiguracaoJpaDaoImpl instance;

	public static KotaeConfiguracaoJpaDaoImpl getInstance() {
		if (instance == null) {
			instance = new KotaeConfiguracaoJpaDaoImpl();
		}
		return instance;
	}

	@Override
	public Collection<KotaeConfiguracao> loadConfiguracoes() {
		return loadCollectionByFilter("flg_ativo = ?", Boolean.TRUE);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#assignTo(java.lang.
	 * Object, java.lang.Object)
	 */
	@Override
	public void assignTo(KotaeConfiguracao source, KotaeConfiguracao dest) {
		dest.assignFrom(source);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#getTableName()
	 */
	@Override
	public String getTableName() {
		return TableNames.TB_KOTAE_CONFIG;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#updateEnabled()
	 */
	@Override
	protected boolean updateEnabled() {
		return LoginManager.getAccessControl().hasAccess(FuncionalidadeKotaeConfig.FUNC_KOTAE_CONFIG_CADASTRAR.getId());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#queryEnabled()
	 */
	@Override
	protected boolean queryEnabled() {
		return LoginManager.getAccessControl().hasAccess(FuncionalidadeKotaeConfig.FUNC_KOTAE_CONFIG_CONSULTAR.getId());
	}

	@Override
	public Collection<KotaeConfiguracao> loadConfiguracaoByTipo(TipoKotae tipoKotae) {
		Collection<KotaeConfiguracao> results = loadCollectionByFilter("tip_kotae = ?", tipoKotae.name());
		return results;
	}

}
