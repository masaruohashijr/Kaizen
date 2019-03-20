/**
 *
 */
package com.logus.kaizen.model.apoio;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.logus.core.model.persistence.DataAccessObject;
import com.logus.kaizen.model.apoio.ambiente.AmbienteDao;
import com.logus.kaizen.model.apoio.ambiente.AmbienteJpaDaoImpl;
import com.logus.kaizen.model.apoio.atendimento.AtendimentoDao;
import com.logus.kaizen.model.apoio.atendimento.AtendimentoJpaDaoImpl;
import com.logus.kaizen.model.apoio.biblioteca.BibliotecaDao;
import com.logus.kaizen.model.apoio.biblioteca.BibliotecaJpaDaoImpl;
import com.logus.kaizen.model.apoio.cliente.ClienteDao;
import com.logus.kaizen.model.apoio.cliente.ClienteJpaDaoImpl;
import com.logus.kaizen.model.apoio.funcao.FuncaoDao;
import com.logus.kaizen.model.apoio.funcao.FuncaoJpaDaoImpl;
import com.logus.kaizen.model.apoio.funcao.ItemFuncaoDao;
import com.logus.kaizen.model.apoio.funcao.ItemFuncaoJpaDaoImpl;
import com.logus.kaizen.model.apoio.processo.ProcessoDao;
import com.logus.kaizen.model.apoio.processo.ProcessoJpaDaoImpl;
import com.logus.kaizen.model.apoio.produto.ProdutoDao;
import com.logus.kaizen.model.apoio.produto.ProdutoJpaDaoImpl;
import com.logus.kaizen.model.apoio.projeto.ItemPapelDao;
import com.logus.kaizen.model.apoio.projeto.ItemPapelJpaDaoImpl;
import com.logus.kaizen.model.apoio.projeto.PapelDao;
import com.logus.kaizen.model.apoio.projeto.PapelJpaDaoImpl;
import com.logus.kaizen.model.apoio.projeto.ProjetoDao;
import com.logus.kaizen.model.apoio.projeto.ProjetoJpaDaoImpl;
import com.logus.kaizen.model.apoio.resolucao.ResolucaoDao;
import com.logus.kaizen.model.apoio.resolucao.ResolucaoJpaDaoImpl;
import com.logus.kaizen.model.apoio.tipomondai.TipoMondaiDao;
import com.logus.kaizen.model.apoio.tipomondai.TipoMondaiJpaDaoImpl;
import com.logus.kaizen.model.apoio.tipomondai.TipoMondaiProjetoDao;
import com.logus.kaizen.model.apoio.tipomondai.TipoMondaiProjetoJpaDaoImpl;
import com.logus.kaizen.model.apoio.transicao.TransicaoDao;
import com.logus.kaizen.model.apoio.transicao.TransicaoJpaDaoImpl;
import com.logus.kaizen.model.apoio.urgencia.UrgenciaDao;
import com.logus.kaizen.model.apoio.urgencia.UrgenciaJpaDaoImpl;
import com.logus.kaizen.model.auditoria.GrupoMudancaDao;
import com.logus.kaizen.model.auditoria.GrupoMudancaJpaDaoImpl;
import com.logus.kaizen.model.auditoria.ItemMudancaDao;
import com.logus.kaizen.model.auditoria.ItemMudancaJpaDaoImpl;
import com.logus.kaizen.model.chronos.ChronosDao;
import com.logus.kaizen.model.chronos.ChronosJpaDaoImpl;
import com.logus.kaizen.model.kotae.configuracao.KotaeConfiguracaoDao;
import com.logus.kaizen.model.kotae.configuracao.KotaeConfiguracaoJpaDaoImpl;
import com.logus.kaizen.model.kotae.plano.PlanoDao;
import com.logus.kaizen.model.kotae.plano.PlanoJpaDaoImpl;
import com.logus.kaizen.model.solicitacao.ComentarioDao;
import com.logus.kaizen.model.solicitacao.ComentarioJpaDaoImpl;
import com.logus.kaizen.model.solicitacao.ItemSolicitacaoDao;
import com.logus.kaizen.model.solicitacao.ItemSolicitacaoJpaDaoImpl;
import com.logus.kaizen.model.solicitacao.SolicitacaoDao;
import com.logus.kaizen.model.solicitacao.SolicitacaoJpaDaoImpl;

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

	public DataAccessObject<?> getDao(Object object) {
		DataAccessObject<?> daoObject = null;
		try {
			String nomeEntidade = object.getClass().getTypeName();
			nomeEntidade = nomeEntidade.substring(nomeEntidade.lastIndexOf(".") + 1, nomeEntidade.length());
			String nomeMetodo = "get" + nomeEntidade + "Dao";
			Method metodo = ApoioDataServiceImpl.class.getMethod(nomeMetodo);
			System.out.println(nomeEntidade);
			daoObject = (DataAccessObject<?>) metodo.invoke(this);
			System.out.println(daoObject);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
		}
		return daoObject;
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

	@Override
	public ChronosDao getToguruDao() {
		return ChronosJpaDaoImpl.getInstance();
	}

	@Override
	public FuncaoDao getFuncaoDao() {
		return FuncaoJpaDaoImpl.getInstance();
	}

	@Override
	public TipoMondaiProjetoDao getTipoMondaiProjetoDao() {
		return TipoMondaiProjetoJpaDaoImpl.getInstance();
	}

	@Override
	public KotaeConfiguracaoDao getKotaeConfiguracaoDao() {
		return KotaeConfiguracaoJpaDaoImpl.getInstance();
	}

	@Override
	public ChronosDao geToguruDao() {
		return ChronosJpaDaoImpl.getInstance();
	}

	@Override
	public PapelDao getPapelDao() {
		return PapelJpaDaoImpl.getInstance();
	}

	@Override
	public ItemFuncaoDao getItemFuncaoDao() {
		return ItemFuncaoJpaDaoImpl.getInstance();
	}

	@Override
	public ItemPapelDao getItemPapelDao() {
		return ItemPapelJpaDaoImpl.getInstance();
	}

	@Override
	public ComentarioDao getComentariosDao() {
		return ComentarioJpaDaoImpl.getInstance();
	}

	@Override
	public ItemSolicitacaoDao getItemSolicitacaoDao() {
		return ItemSolicitacaoJpaDaoImpl.getInstance();
	}

	@Override
	public ItemMudancaDao getItemMudancaDao() {
		return ItemMudancaJpaDaoImpl.getInstance();
	}

	@Override
	public GrupoMudancaDao getGrupoMudancaDao() {
		return GrupoMudancaJpaDaoImpl.getInstance();
	}

}
