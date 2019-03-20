/**
 *
 */
package com.logus.kaizen.model.apoio.atendimento;

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
public class AtendimentoJpaDaoImpl extends AbstractJpaDao<Atendimento> implements AtendimentoDao {

	/**
	 * @param emf
	 */
	public AtendimentoJpaDaoImpl() {
		super(ApoioDataServiceImpl.getEMF());
	}

	private static AtendimentoJpaDaoImpl instance;

	public static AtendimentoJpaDaoImpl getInstance() {
		if (instance == null) {
			instance = new AtendimentoJpaDaoImpl();
		}
		return instance;
	}

	@Override
	public Collection<Atendimento> loadAtendimentos() {
		return loadCollection("seq_atendimento, nom_atendimento, dsc_atendimento, flg_ativo", TableNames.TB_ATENDIMENTO,
				"flg_ativo = ?", "nom_atendimento", Boolean.TRUE);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#assignTo(java.lang.
	 * Object, java.lang.Object)
	 */
	@Override
	public void assignTo(Atendimento source, Atendimento dest) {
		dest.assignFrom(source);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#getTableName()
	 */
	@Override
	public String getTableName() {
		return TableNames.TB_ATENDIMENTO;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#updateEnabled()
	 */
	@Override
	protected boolean updateEnabled() {
		return LoginManager.getAccessControl().hasAccess(FuncionalidadeAtendimento.FUNC_ATENDIMENTO_CADASTRAR.getId());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#queryEnabled()
	 */
	@Override
	protected boolean queryEnabled() {
		return LoginManager.getAccessControl().hasAccess(FuncionalidadeAtendimento.FUNC_ATENDIMENTO_CONSULTAR.getId());
	}

}
