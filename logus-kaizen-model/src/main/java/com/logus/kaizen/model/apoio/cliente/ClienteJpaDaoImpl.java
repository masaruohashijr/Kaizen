/**
 *
 */
package com.logus.kaizen.model.apoio.cliente;

import java.util.Collection;

import com.logus.core.model.aut.login.LoginManager;
import com.logus.core.model.persistence.jpa.AbstractJpaDao;
import com.logus.kaizen.model.TableNames;
import com.logus.kaizen.model.apoio.ApoioDataServiceImpl;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class ClienteJpaDaoImpl extends AbstractJpaDao<Cliente> implements ClienteDao {

	/**
	 * @param emf
	 */
	public ClienteJpaDaoImpl() {
		super(ApoioDataServiceImpl.getEMF());
	}

	private static ClienteJpaDaoImpl instance;

	public static ClienteJpaDaoImpl getInstance() {
		if (instance == null) {
			instance = new ClienteJpaDaoImpl();
		}
		return instance;
	}

	@Override
	public Collection<Cliente> loadClientes() {
		return loadCollection("seq_cliente, nom_cliente, flg_ativo", TableNames.TB_CLIENTE, "flg_ativo = ?", "nom_cliente",
				Boolean.TRUE);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#assignTo(java.lang.
	 * Object, java.lang.Object)
	 */
	@Override
	public void assignTo(Cliente source, Cliente dest) {
		dest.assignFrom(source);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#getTableName()
	 */
	@Override
	public String getTableName() {
		return TableNames.TB_CLIENTE;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#updateEnabled()
	 */
	@Override
	protected boolean updateEnabled() {
		return LoginManager.getAccessControl().hasAccess(FuncionalidadeCliente.FUNC_CLIENTE_CADASTRAR.getId());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#queryEnabled()
	 */
	@Override
	protected boolean queryEnabled() {
		return LoginManager.getAccessControl().hasAccess(FuncionalidadeCliente.FUNC_CLIENTE_CONSULTAR.getId());
	}

}
