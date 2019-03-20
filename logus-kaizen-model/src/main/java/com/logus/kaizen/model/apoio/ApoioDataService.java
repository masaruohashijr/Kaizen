/**
 *
 */
package com.logus.kaizen.model.apoio;

import com.logus.core.model.persistence.DataAccessObject;
import com.logus.kaizen.model.apoio.ambiente.AmbienteDao;
import com.logus.kaizen.model.apoio.atendimento.AtendimentoDao;
import com.logus.kaizen.model.apoio.biblioteca.BibliotecaDao;
import com.logus.kaizen.model.apoio.cliente.ClienteDao;
import com.logus.kaizen.model.apoio.funcao.FuncaoDao;
import com.logus.kaizen.model.apoio.funcao.ItemFuncaoDao;
import com.logus.kaizen.model.apoio.processo.ProcessoDao;
import com.logus.kaizen.model.apoio.produto.ProdutoDao;
import com.logus.kaizen.model.apoio.projeto.ItemPapelDao;
import com.logus.kaizen.model.apoio.projeto.PapelDao;
import com.logus.kaizen.model.apoio.projeto.ProjetoDao;
import com.logus.kaizen.model.apoio.resolucao.ResolucaoDao;
import com.logus.kaizen.model.apoio.tipomondai.TipoMondaiDao;
import com.logus.kaizen.model.apoio.tipomondai.TipoMondaiProjetoDao;
import com.logus.kaizen.model.apoio.transicao.TransicaoDao;
import com.logus.kaizen.model.apoio.urgencia.UrgenciaDao;
import com.logus.kaizen.model.auditoria.GrupoMudancaDao;
import com.logus.kaizen.model.auditoria.ItemMudancaDao;
import com.logus.kaizen.model.chronos.ChronosDao;
import com.logus.kaizen.model.kotae.configuracao.KotaeConfiguracaoDao;
import com.logus.kaizen.model.kotae.plano.PlanoDao;
import com.logus.kaizen.model.solicitacao.ComentarioDao;
import com.logus.kaizen.model.solicitacao.ItemSolicitacaoDao;
import com.logus.kaizen.model.solicitacao.SolicitacaoDao;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public abstract class ApoioDataService {

	/**
	 * Singleton.
	 */
	private static ApoioDataService instance;

	/**
	 * @return a instância concreta da fábrica de DAO's.
	 */
	public static ApoioDataService get() {
		if (instance == null) {
			instance = new ApoioDataServiceImpl();
		}
		return instance;
	}

	public abstract BibliotecaDao getBibliotecaDao();

	public abstract ProdutoDao getProdutoDao();

	public abstract FuncaoDao getFuncaoDao();

	public abstract ItemFuncaoDao getItemFuncaoDao();

	public abstract PapelDao getPapelDao();

	public abstract ClienteDao getClienteDao();

	public abstract UrgenciaDao getUrgenciaDao();

	public abstract AtendimentoDao getAtendimentoDao();

	public abstract SolicitacaoDao getSolicitacaoDao();

	public abstract PlanoDao getPlanoDao();

	public abstract AmbienteDao getAmbienteDao();

	public abstract ProcessoDao getProcessoDao();

	public abstract ProjetoDao getProjetoDao();

	public abstract TransicaoDao getTransicaoDao();

	public abstract ResolucaoDao getResolucaoDao();

	public abstract TipoMondaiDao getTipoMondaiDao();

	public abstract TipoMondaiProjetoDao getTipoMondaiProjetoDao();

	public abstract ChronosDao geToguruDao();

	public abstract KotaeConfiguracaoDao getKotaeConfiguracaoDao();

	public abstract ChronosDao getToguruDao();

	public abstract ItemPapelDao getItemPapelDao();

	public abstract ComentarioDao getComentariosDao();

	public abstract ItemSolicitacaoDao getItemSolicitacaoDao();

	public abstract DataAccessObject<?> getDao(Object object);

	public abstract ItemMudancaDao getItemMudancaDao();

	public abstract GrupoMudancaDao getGrupoMudancaDao();
}
