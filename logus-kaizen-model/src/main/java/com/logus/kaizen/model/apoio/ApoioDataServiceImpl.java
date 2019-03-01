/**
 *
 */
package com.logus.kaizen.model.apoio;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.logus.kaizen.model.apoio.ambiente.AmbienteDao;
import com.logus.kaizen.model.apoio.ambiente.AmbienteJpaDaoImpl;
import com.logus.kaizen.model.apoio.atendimento.AtendimentoDao;
import com.logus.kaizen.model.apoio.atendimento.AtendimentoJpaDaoImpl;
import com.logus.kaizen.model.apoio.biblioteca.BibliotecaDao;
import com.logus.kaizen.model.apoio.biblioteca.BibliotecaJpaDaoImpl;
import com.logus.kaizen.model.apoio.cliente.ClienteDao;
import com.logus.kaizen.model.apoio.cliente.ClienteJpaDaoImpl;
import com.logus.kaizen.model.apoio.produto.ProdutoDao;
import com.logus.kaizen.model.apoio.produto.ProdutoJpaDaoImpl;
import com.logus.kaizen.model.apoio.tipomondai.TipoMondaiDao;
import com.logus.kaizen.model.apoio.tipomondai.TipoMondaiJpaDaoImpl;
import com.logus.kaizen.model.apoio.urgencia.UrgenciaDao;
import com.logus.kaizen.model.apoio.urgencia.UrgenciaJpaDaoImpl;
import com.logus.kaizen.model.plano.PlanoDao;
import com.logus.kaizen.model.plano.PlanoJpaDaoImpl;
import com.logus.kaizen.model.processo.ProcessoDao;
import com.logus.kaizen.model.processo.ProcessoJpaDaoImpl;
import com.logus.kaizen.model.projeto.ProjetoDao;
import com.logus.kaizen.model.projeto.ProjetoJpaDaoImpl;
import com.logus.kaizen.model.resolucao.ResolucaoDao;
import com.logus.kaizen.model.resolucao.ResolucaoJpaDaoImpl;
import com.logus.kaizen.model.solicitacao.SolicitacaoDao;
import com.logus.kaizen.model.solicitacao.SolicitacaoJpaDaoImpl;
import com.logus.kaizen.model.transicao.TransicaoDao;
import com.logus.kaizen.model.transicao.TransicaoJpaDaoImpl;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class ApoioDataServiceImpl extends ApoioDataService {

	/**
	 * Nome da persistence-unit JPA.
	 */
	private static final String PERSISTENCE_UNIT_NAME = "Kaizen_Persistence_Unit";

	/**
	 * Singleton para factory de gerência de Entidades.
	 */
	private static EntityManagerFactory emf;

	/**
	 * @return {@link #emf}.
	 */
	public static EntityManagerFactory getEMF() {
		if (emf == null) {
			emf = (EntityManagerFactory) Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		}
		return emf;
	}

	@Override
	public ProdutoDao getProdutoDao() {
		return ProdutoJpaDaoImpl.getInstance();
	}

	@Override
	public ClienteDao getClienteDao() {
		return ClienteJpaDaoImpl.getInstance();
	}

	@Override
	public UrgenciaDao getUrgenciaDao() {
		return UrgenciaJpaDaoImpl.getInstance();
	}

	@Override
	public AtendimentoDao getAtendimentoDao() {
		return AtendimentoJpaDaoImpl.getInstance();
	}

	@Override
	public BibliotecaDao getBibliotecaDao() {
		return BibliotecaJpaDaoImpl.getInstance();
	}

	@Override
	public SolicitacaoDao getSolicitacaoDao() {
		return SolicitacaoJpaDaoImpl.getInstance();
	}

	@Override
	public PlanoDao getPlanoDao() {
		return PlanoJpaDaoImpl.getInstance();
	}

	@Override
	public AmbienteDao getAmbienteDao() {
		return AmbienteJpaDaoImpl.getInstance();
	}

	@Override
	public ProcessoDao getProcessoDao() {
		return ProcessoJpaDaoImpl.getInstance();
	}

	@Override
	public ProjetoDao getProjetoDao() {
		return ProjetoJpaDaoImpl.getInstance();
	}

	@Override
	public TransicaoDao getTransicaoDao() {
		return TransicaoJpaDaoImpl.getInstance();
	}

	@Override
	public ResolucaoDao getResolucaoDao() {
		return ResolucaoJpaDaoImpl.getInstance();
	}

	@Override
	public TipoMondaiDao getTipoMondaiDao() {
		return TipoMondaiJpaDaoImpl.getInstance();
	}

}
