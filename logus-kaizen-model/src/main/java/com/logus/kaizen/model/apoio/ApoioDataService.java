/**
 *
 */
package com.logus.kaizen.model.apoio;

import com.logus.kaizen.model.apoio.ambiente.AmbienteDao;
import com.logus.kaizen.model.apoio.atendimento.AtendimentoDao;
import com.logus.kaizen.model.apoio.biblioteca.BibliotecaDao;
import com.logus.kaizen.model.apoio.cliente.ClienteDao;
import com.logus.kaizen.model.apoio.produto.ProdutoDao;
import com.logus.kaizen.model.apoio.tipomondai.TipoMondaiDao;
import com.logus.kaizen.model.apoio.urgencia.UrgenciaDao;
import com.logus.kaizen.model.plano.PlanoDao;
import com.logus.kaizen.model.processo.ProcessoDao;
import com.logus.kaizen.model.projeto.ProjetoDao;
import com.logus.kaizen.model.resolucao.ResolucaoDao;
import com.logus.kaizen.model.solicitacao.SolicitacaoDao;
import com.logus.kaizen.model.transicao.TransicaoDao;
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

}
