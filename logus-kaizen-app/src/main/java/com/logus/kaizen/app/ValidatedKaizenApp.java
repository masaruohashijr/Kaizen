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
import com.logus.kaizen.model.apoio.produto.FuncionalidadeProduto;
import com.logus.kaizen.model.apoio.tipomondai.FuncionalidadeTipoMondai;
import com.logus.kaizen.model.apoio.tipomondai.TipoMondai;
import com.logus.kaizen.model.apoio.urgencia.FuncionalidadeUrgencia;
import com.logus.kaizen.model.plano.FuncionalidadePlano;
import com.logus.kaizen.model.processo.FuncionalidadeProcesso;
import com.logus.kaizen.model.projeto.FuncionalidadeProjeto;
import com.logus.kaizen.model.resolucao.FuncionalidadeResolucao;
import com.logus.kaizen.model.solicitacao.FuncionalidadeSolicitacao;
import com.logus.kaizen.model.transicao.FuncionalidadeTransicao;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.kaizen.view.ambiente.AmbientePage;
import com.logus.kaizen.view.atendimento.AtendimentoPage;
import com.logus.kaizen.view.biblioteca.BibliotecaPage;
import com.logus.kaizen.view.cliente.ClientePage;
import com.logus.kaizen.view.plano.PlanoPage;
import com.logus.kaizen.view.processo.ProcessoPage;
import com.logus.kaizen.view.produto.ProdutoPage;
import com.logus.kaizen.view.projeto.ProjetoPage;
import com.logus.kaizen.view.resolucao.ResolucaoPage;
import com.logus.kaizen.view.solicitacao.SolicitacaoPage;
import com.logus.kaizen.view.tipomondai.TipoMondaiPage;
import com.logus.kaizen.view.transicao.TransicaoPage;
import com.logus.kaizen.view.urgencia.UrgenciaPage;
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
	 * 		 Em Produto incluir a Descrição.
	 * 		 Em Urgência incluir a Descrição.
	 * 		 Data para ficar pronto: 01/03/19
	 *
	 * 0.2.0 Planejar Build com Processo instanciado.
	 * 		 Data para ficar pronto: 01/03/19
	 *
	 * 0.3.0 Comentários.
	 * 		 RICH TEXT AREA para Comentários.
	 * 		 Visibilidade de Comentários.
	 * 		 Data para ficar pronto: 03/03/19
	 *
	 * 0.4.0 Auditoria.
	 * 		 Data para ficar pronto: 04/03/19
	 *
	 * 0.5.0 E-mail.
	 * 		 Data para ficar pronto: 05/03/19
	 *
	 * 0.6.0 Em Tipo de Mondai criar uma Aba para detalhes intitulada de Campos Customizados.
	 * 		 Permitir associar os Campos Customizados a Abas de Mondai,
	 * 		 informar qual a Obrigatoriedade e se há Domínios de Preenchimento.
	 * 		 Data para ficar pronto: 06/03/19
	 *
	 * 0.7.0 Hierarquia de Mondai e Hierarquia de Comentários.
	 * 		 Data para ficar pronto: 07/03/19
	 *
	 * 0.8.0 Acompanhadores de Mondai
	 * 		 Data para ficar pronto: 08/03/19
	 *
	 * 0.9.0 Tags de Mondai
	 * 		 Data para ficar pronto: 09/03/19
	 *
	 * 0.10.0 Links de Mondai
	 * 		 Data para ficar pronto: 10/03/19
	 *
	 * 0.11.0 Integração com o JIRA
	 * 		 Data para ficar pronto: 11/03/19
	 *
	 * 0.12.0 Exception Handler com Envio de E-mails
	 * 		 Data para ficar pronto: 12/03/19
	 *
	 * 0.13.0 Importação da Planilha Controle de Deploys
	 * 		 Data para ficar pronto: 13/03/19
	 *
	 * TESTES
	 * ======
	 *
	 * 1.0.0 VERSÃO FINAL
	 * 		 Data para ficar pronto: 15/03/19
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
				VaadinIcon.FORM.create(), SolicitacaoPage.class, FuncionalidadeSolicitacao.class, null);
		cdMenuItems.add(solicitacao);
		KaizenMenuItem atendimentosParent = new KaizenMenuItem(TM.translate(KaizenTranslator.ATENDIMENTO_PLURAL),
				VaadinIcon.TABLE.create(), PaginaEmBranco.class, null, null);
		cdMenuItems.add(atendimentosParent);
		KaizenMenuItem plano = new KaizenMenuItem(TM.translate(KaizenTranslator.PLANO), VaadinIcon.FORM.create(),
				PlanoPage.class, FuncionalidadePlano.class, atendimentosParent);
		cdMenuItems.add(plano);
		/**
		 * Apoio
		 */
		KaizenMenuItem apoioParent = new KaizenMenuItem(TM.translate(KaizenTranslator.APOIO), VaadinIcon.TABLE.create(),
				PaginaEmBranco.class, null, null);
		cdMenuItems.add(apoioParent);
		cdMenuItems.add(new KaizenMenuItem(TM.translate(KaizenTranslator.AMBIENTE), VaadinIcon.TABLE.create(),
				AmbientePage.class, FuncionalidadeAmbiente.class, apoioParent));
		cdMenuItems.add(new KaizenMenuItem(TM.translate(KaizenTranslator.ATENDIMENTO), VaadinIcon.TABLE.create(),
				AtendimentoPage.class, FuncionalidadeAtendimento.class, apoioParent));
		cdMenuItems.add(new KaizenMenuItem(TM.translate(KaizenTranslator.CLIENTE), VaadinIcon.TABLE.create(),
				ClientePage.class, FuncionalidadeCliente.class, apoioParent));
		cdMenuItems.add(new KaizenMenuItem(TM.translate(KaizenTranslator.BIBLIOTECA), VaadinIcon.TABLE.create(),
				BibliotecaPage.class, FuncionalidadeBiblioteca.class, apoioParent));
		cdMenuItems.add(new KaizenMenuItem(TM.translate(KaizenTranslator.PROCESSO), VaadinIcon.TABLE.create(),
				ProcessoPage.class, FuncionalidadeProcesso.class, apoioParent));
		cdMenuItems.add(new KaizenMenuItem(TM.translate(KaizenTranslator.PRODUTO), VaadinIcon.TABLE.create(),
				ProdutoPage.class, FuncionalidadeProduto.class, apoioParent));
		cdMenuItems.add(new KaizenMenuItem(TM.translate(KaizenTranslator.PROJETO), VaadinIcon.TABLE.create(),
				ProjetoPage.class, FuncionalidadeProjeto.class, apoioParent));
		cdMenuItems.add(new KaizenMenuItem(TM.translate(KaizenTranslator.RESOLUCAO), VaadinIcon.TABLE.create(),
				ResolucaoPage.class, FuncionalidadeResolucao.class, apoioParent));
		cdMenuItems.add(new KaizenMenuItem(TM.translate(KaizenTranslator.TIPO_MONDAI), VaadinIcon.TABLE.create(),
				TipoMondaiPage.class, FuncionalidadeTipoMondai.class, apoioParent));
		cdMenuItems.add(new KaizenMenuItem(TM.translate(KaizenTranslator.TRANSICAO), VaadinIcon.TABLE.create(),
				TransicaoPage.class, FuncionalidadeTransicao.class, apoioParent));
		cdMenuItems.add(new KaizenMenuItem(TM.translate(KaizenTranslator.URGENCIA), VaadinIcon.TABLE.create(),
				UrgenciaPage.class, FuncionalidadeUrgencia.class, apoioParent));
//		cdMenuItems.add(new KaizenMenuItem(TM.translate(KaizenTranslator.CENTRO_CUSTO), VaadinIcon.BULLETS.create(),
//				CentroCustoPage.class, null, apoioParent));
//		cdMenuItems.add(new KaizenMenuItem(TM.translate(KaizenTranslator.MATRIZ_RATEIO), VaadinIcon.BULLETS.create(),
//				MatrizRateioPage.class, null, apoioParent));
		/**
		 * Sair
		 */
		cdMenuItems.add(new KaizenMenuItem(TM.translate(CoreTranslator.LOGIN_EXIT), VaadinIcon.EXIT.create(),
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
						|| f.getId().equals(FuncionalidadeLivreAcesso.FUNC_LIVRE_ACESSO.getId())) {
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

}
