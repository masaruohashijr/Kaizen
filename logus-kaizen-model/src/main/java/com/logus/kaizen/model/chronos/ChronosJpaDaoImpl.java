/**
 *
 */
package com.logus.kaizen.model.chronos;

import java.util.Collection;

import com.logus.core.model.aut.login.LoginManager;
import com.logus.core.model.persistence.jpa.AbstractJpaDao;
import com.logus.kaizen.model.TableNames;
import com.logus.kaizen.model.apoio.ApoioDataServiceImpl;
import com.logus.kaizen.model.apoio.atendimento.Atendimento;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class ChronosJpaDaoImpl extends AbstractJpaDao<Chronos> implements ChronosDao {

	/**
	 * @param emf
	 */
	public ChronosJpaDaoImpl() {
		super(ApoioDataServiceImpl.getEMF());
	}

	private static ChronosJpaDaoImpl instance;

	public static ChronosJpaDaoImpl getInstance() {
		if (instance == null) {
			instance = new ChronosJpaDaoImpl();
		}
		return instance;
	}

	@Override
	public Collection<Chronos> loadTogurus() {
		return loadCollection("*", TableNames.TB_TOGURU, "flg_ativo = ?", "seq_toguru desc", Boolean.TRUE);
	}

	@Override
	public Collection<Chronos> loadTogurusByResponsavel(String codigoResponsavel) {
		return loadCollectionByFilter("cod_responsavel = ? and flg_ativo = ? and dat_fim is null", codigoResponsavel,
				Boolean.TRUE);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#assignTo(java.lang.
	 * Object, java.lang.Object)
	 */
	@Override
	public void assignTo(Chronos source, Chronos dest) {
		dest.assignFrom(source);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#getTableName()
	 */
	@Override
	public String getTableName() {
		return TableNames.TB_TOGURU;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#updateEnabled()
	 */
	@Override
	protected boolean updateEnabled() {
		return LoginManager.getAccessControl().hasAccess(FuncionalidadeChronos.FUNC_TOGURU_CADASTRAR.getId());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logus.core.model.persistence.jpa.AbstractJpaDao#queryEnabled()
	 */
	@Override
	protected boolean queryEnabled() {
		return LoginManager.getAccessControl().hasAccess(FuncionalidadeChronos.FUNC_TOGURU_CONSULTAR.getId());
	}

	@Override
	public Collection<Chronos> loadTogurusOrdenadoPorInicio() {
		return loadCollection("*", TableNames.TB_TOGURU, "flg_ativo = ?", "dat_inicio desc", Boolean.TRUE);
	}

	@Override
	public Chronos loadUltimoToguruDoResponsavel(Atendimento atendimentoOrigem, String codigoUsuario) {
		Chronos toguru = null;
		Collection<Chronos> collection = loadCollection("*", TableNames.TB_TOGURU,
				"cod_responsavel = ? and seq_atendimento = ?", "dat_inicio desc", codigoUsuario,
				atendimentoOrigem.getId());
		Object[] array = collection.toArray();
		if (array.length > 0) {
			toguru = (Chronos) array[0];
		}
		return toguru;
	}

}
