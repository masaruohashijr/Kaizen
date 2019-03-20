package com.logus.kaizen.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.logus.core.model.aut.Funcionalidade;
import com.logus.core.model.aut.login.LoginManager;
import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.app.Application;
import com.logus.core.view.app.LegacyAbstractApp;
import com.logus.core.view.app.layouts.MenuItem;
import com.logus.kaizen.model.FuncionalidadeLivreAcesso;
import com.logus.kaizen.model.apoio.ambiente.FuncionalidadeAmbiente;
import com.logus.kaizen.model.apoio.atendimento.FuncionalidadeAtendimento;
import com.logus.kaizen.model.apoio.biblioteca.FuncionalidadeBiblioteca;
import com.logus.kaizen.model.apoio.cliente.FuncionalidadeCliente;
import com.logus.kaizen.model.apoio.funcao.FuncionalidadeFuncao;
import com.logus.kaizen.model.apoio.processo.FuncionalidadeProcesso;
import com.logus.kaizen.model.apoio.produto.FuncionalidadeProduto;
import com.logus.kaizen.model.apoio.projeto.FuncionalidadeProjeto;
import com.logus.kaizen.model.apoio.resolucao.FuncionalidadeResolucao;
import com.logus.kaizen.model.apoio.tipomondai.FuncionalidadeTipoMondai;
import com.logus.kaizen.model.apoio.transicao.FuncionalidadeTransicao;
import com.logus.kaizen.model.apoio.urgencia.FuncionalidadeUrgencia;
import com.logus.kaizen.model.kotae.configuracao.FuncionalidadeKotaeConfig;
import com.logus.kaizen.model.kotae.plano.FuncionalidadePlano;
import com.logus.kaizen.model.solicitacao.FuncionalidadeSolicitacao;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.kaizen.view.apoio.ambiente.AmbientePage;
import com.logus.kaizen.view.apoio.atendimento.AtendimentoPage;
import com.logus.kaizen.view.apoio.biblioteca.BibliotecaPage;
import com.logus.kaizen.view.apoio.cliente.ClientePage;
import com.logus.kaizen.view.apoio.funcao.FuncaoPage;
import com.logus.kaizen.view.apoio.processo.ProcessoPage;
import com.logus.kaizen.view.apoio.produto.ProdutoPage;
import com.logus.kaizen.view.apoio.projeto.ProjetoPage;
import com.logus.kaizen.view.apoio.resolucao.ResolucaoPage;
import com.logus.kaizen.view.apoio.tipomondai.TipoMondaiPage;
import com.logus.kaizen.view.apoio.transicao.TransicaoPage;
import com.logus.kaizen.view.apoio.urgencia.UrgenciaPage;
import com.logus.kaizen.view.auditoria.AuditoriaPage;
import com.logus.kaizen.view.chronos.ChronosPage;
import com.logus.kaizen.view.kotae.configuracao.KotaeConfiguracaoPage;
import com.logus.kaizen.view.kotae.plano.PlanoPage;
import com.logus.kaizen.view.mondai.SolicitacaoPage;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class ValidatedKaizenApp extends LegacyAbstractApp implements Application {
	private static final String APP_VERSION = "0.1.0";
	/*
	 * ROTEIRO
	 * =======
	 *
	 * 0.1.0 CRUDS de Apoio (Tipo de Mondai, Processo, Projeto, Cliente, Ambiente, etc.)
	 * 		 Descrição de Mondai e Controle de Abas a partir do Tipo de Mondai informado.
	 * 		 Em Projeto criar uma Aba para Tipos de Mondai do Projeto, permitindo associar um Processo a cada Tipo.
	 * 		 Em Biblioteca incluir a Descrição.
	 * 		 Em Ambiente incluir a Descrição.
	 * 		 Em Cliente incluir a Descrição.
	 * 		 Em Plano incluir a Descrição.
	 * 		 Em Funcao incluir a Descrição.
	 * 		 Em Urgência incluir a Descrição.
	 * 		 Data para ficar pronto: 01/03/19 OK
	 *
	 * 0.2.0 Planejar Build com Processo instanciado.
	 * 		 Data para ficar pronto: 02/03/19 => 07/03/19
	 *
	 * 0.3.0 Comentários.
	 * 		 RICH TEXT AREA para Comentários.
	 * 		 Visibilidade de Comentários.
	 * 		 Data para ficar pronto: 03/03/19 => 11/03/19
	 *
	 * 0.4.0 Auditoria.
	 * 		 Data para ficar pronto: 04/03/19 => 12/03/19
	 *
	 * 0.5.0 E-mail.
	 * 		 Data para ficar pronto: 05/03/19 => 13/03/19
	 *
	 * 0.6.0 Em Tipo de Mondai criar uma Aba para detalhes intitulada de Campos Customizados.
	 * 		 Permitir associar os Campos Customizados a Abas de Mondai,
	 * 		 informar qual a Obrigatoriedade e se há Domínios de Preenchimento.
	 * 		 Data para ficar pronto: 06/03/19 => 14/03/19
	 *
	 * 0.7.0 Hierarquia de Mondai e Hierarquia de Comentários.
	 * 		 Data para ficar pronto: 07/03/19 => 15/03/19
	 *
	 * 0.8.0 Acompanhadores de Mondai
	 * 		 Data para ficar pronto: 08/03/19 => 18/03/19
	 *
	 * 0.9.0 Tags de Mondai
	 * 		 Data para ficar pronto: 09/03/19 => 19/03/19
	 *
	 * 0.10.0 Links de Mondai
	 * 		 Data para ficar pronto: 10/03/19 => 20/03/19
	 *
	 * 0.11.0 Integração com o JIRA
	 * 		 Data para ficar pronto: 11/03/19 => 21/03/19
	 *
	 * 0.12.0 Exception Handler com Envio de E-mails
	 * 		 Data para ficar pronto: 12/03/19 => 22/03/19
	 *
	 * 0.13.0 Importação da Planilha Controle de Deploys
	 * 		 Data para ficar pronto: 13/03/19 => 23/03/19
	 *
	 * TESTES
	 * ======
	 *
	 * 1.0.0 VERSÃO FINAL
	 * 		 Data para ficar pronto: 15/03/19 => 25/03/19
	 *
	 * Apresentar à Equipe.
	 */
	private static final String APP_LOGO_LIGHT = "frontend/images/logus2.png";
	private static final String APP_LOGO_DARK = "frontend/images/logus3.png";

	private static List<KaizenMenuItem> cdMenuItems = new ArrayList<>();
	private Map<String, MenuItem> memoria;

	static {

		TM.setAdminTranslator(KaizenTranslator.getInstance(null));

		KaizenMenuItem solicitacao = new KaizenMenuItem(TM.translate(KaizenTranslator.MONDAI),
				VaadinIcon.EXCLAMATION_CIRCLE_O.create(), SolicitacaoPage.class, FuncionalidadeSolicitacao.class, null);
		cdMenuItems.add(solicitacao);
		KaizenMenuItem chronos = new KaizenMenuItem(TM.translate(KaizenTranslator.CHRONOS),
				VaadinIcon.CLOCK.create(), ChronosPage.class, null, null);
		cdMenuItems.add(chronos);
		KaizenMenuItem plano = new KaizenMenuItem(TM.translate(KaizenTranslator.PLANO), VaadinIcon.COMPILE.create(),
				PlanoPage.class, FuncionalidadePlano.class, null);
		cdMenuItems.add(plano);
		KaizenMenuItem auditoria = new KaizenMenuItem(TM.translate(KaizenTranslator.AUDITORIA),
				VaadinIcon.VAADIN_H.create(), AuditoriaPage.class, null, null);
		cdMenuItems.add(auditoria);
		/**
		 * Apoio
		 */
		KaizenMenuItem apoioParent = new KaizenMenuItem(TM.translate(KaizenTranslator.APOIO), VaadinIcon.COG_O.create(),
				PaginaEmBranco.class, null, null);
		cdMenuItems.add(apoioParent);
		cdMenuItems.add(new KaizenMenuItem(TM.translate(KaizenTranslator.AMBIENTE), VaadinIcon.AREA_SELECT.create(),
				AmbientePage.class, FuncionalidadeAmbiente.class, apoioParent));
		cdMenuItems.add(new KaizenMenuItem(TM.translate(KaizenTranslator.ATENDIMENTO), VaadinIcon.CHECK_CIRCLE_O.create(),
				AtendimentoPage.class, FuncionalidadeAtendimento.class, apoioParent));
		cdMenuItems.add(new KaizenMenuItem(TM.translate(KaizenTranslator.BIBLIOTECA), VaadinIcon.BOOK.create(),
				BibliotecaPage.class, FuncionalidadeBiblioteca.class, apoioParent));
		cdMenuItems.add(new KaizenMenuItem(TM.translate(KaizenTranslator.CLIENTE), VaadinIcon.USERS.create(),
				ClientePage.class, FuncionalidadeCliente.class, apoioParent));
		KaizenMenuItem configuracaoKotae = new KaizenMenuItem(TM.translate(KaizenTranslator.CONFIGURACAO), VaadinIcon.COGS.create(),
				KotaeConfiguracaoPage.class, FuncionalidadeKotaeConfig.class, apoioParent);
		cdMenuItems.add(configuracaoKotae);
		cdMenuItems.add(new KaizenMenuItem(TM.translate(KaizenTranslator.FUNCAO), VaadinIcon.WRENCH.create(),
				FuncaoPage.class, FuncionalidadeFuncao.class, apoioParent));
		cdMenuItems.add(new KaizenMenuItem(TM.translate(KaizenTranslator.PROCESSO), VaadinIcon.REFRESH.create(),
				ProcessoPage.class, FuncionalidadeProcesso.class, apoioParent));
		cdMenuItems.add(new KaizenMenuItem(TM.translate(KaizenTranslator.PRODUTO), VaadinIcon.COFFEE.create(),
				ProdutoPage.class, FuncionalidadeProduto.class, apoioParent));
		cdMenuItems.add(new KaizenMenuItem(TM.translate(KaizenTranslator.PROJETO), VaadinIcon.PENCIL.create(),
				ProjetoPage.class, FuncionalidadeProjeto.class, apoioParent));
		cdMenuItems.add(new KaizenMenuItem(TM.translate(KaizenTranslator.RESOLUCAO), VaadinIcon.CHECK_CIRCLE.create(),
				ResolucaoPage.class, FuncionalidadeResolucao.class, apoioParent));
		cdMenuItems.add(new KaizenMenuItem(TM.translate(KaizenTranslator.TIPO_MONDAI), VaadinIcon.OPTIONS.create(),
				TipoMondaiPage.class, FuncionalidadeTipoMondai.class, apoioParent));
		cdMenuItems.add(new KaizenMenuItem(TM.translate(KaizenTranslator.TRANSICAO), VaadinIcon.TRAIN.create(),
				TransicaoPage.class, FuncionalidadeTransicao.class, apoioParent));
		cdMenuItems.add(new KaizenMenuItem(TM.translate(KaizenTranslator.URGENCIA), VaadinIcon.EXCLAMATION_CIRCLE.create(),
				UrgenciaPage.class, FuncionalidadeUrgencia.class, apoioParent));
//		cdMenuItems.add(new KaizenMenuItem(TM.translate(KaizenTranslator.CENTRO_CUSTO), VaadinIcon.BULLETS.create(),
//				CentroCustoPage.class, null, apoioParent));
//		cdMenuItems.add(new KaizenMenuItem(TM.translate(KaizenTranslator.MATRIZ_RATEIO), VaadinIcon.BULLETS.create(),
//				MatrizRateioPage.class, null, apoioParent));
		/**
		 * Sair
		 */
		cdMenuItems.add(new KaizenMenuItem(TM.translate(CoreTranslator.LOGIN_EXIT), VaadinIcon.SIGN_OUT.create(),
				KaizenLoginComponent.class, null, null));

	}

	@Override
	public void createMenuItens(MenuItemCreator menuItemCreator) {
		memoria = new HashMap<String, MenuItem>();
		for (KaizenMenuItem d : cdMenuItems) {
			if (hasAccess(d)) {
				KaizenMenuItem p = d.getParent();
				MenuItem pMI = null;
				if (null != p && !memoria.isEmpty()) {
					pMI = memoria.get(p.getCaption() + p.getProvider() + p.getIcon());
				}
				MenuItem menuItem = menuItemCreator.createMenuItem(d.getCaption(), d.getIcon(), d.getProvider(), pMI);
				memoria.put(d.getCaption() + d.getProvider() + d.getIcon(), menuItem);
			}
		}

	}

	private boolean hasAccess(KaizenMenuItem dividaMenuItem) {
		Set<Funcionalidade> permissoesNecessarias = dividaMenuItem.getPermissoesNecessarias();
		if (null != permissoesNecessarias) {
			if (permissoesNecessarias.isEmpty()) {
				return true;
			}
			Iterator<Funcionalidade> iter = permissoesNecessarias.iterator();
			while (iter.hasNext()) {
				Funcionalidade f = iter.next();
				if (LoginManager.getAccessControl().hasAccess(f.getId())
						|| f.getId().equals(FuncionalidadeLivreAcesso.FUNC_LIVRE_ACESSO.getId()) || true) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public String getCaption() {
		return TM.translate(KaizenTranslator.NOME_SISTEMA);
	}

	@Override
	public String getImagePath() {
		if (KaizenMainLayout.class.isAnnotationPresent(Theme.class)) {
			Theme a = KaizenMainLayout.class.getAnnotation(Theme.class);
			if (a.variant().equals(Lumo.DARK)) {
				return APP_LOGO_LIGHT;
			} else {
				return APP_LOGO_DARK;
			}
		}
		return APP_LOGO_DARK;
	}

	@Override
	public String getVersion() {
		return APP_VERSION;
	}

//	@Override
//	public String getSquareImagePath() {
//		return null;
//	}

}
