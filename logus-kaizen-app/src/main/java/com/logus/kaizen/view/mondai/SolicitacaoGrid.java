/**
 *
 */
package com.logus.kaizen.view.mondai;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.commons.lang3.StringUtils;

import com.logus.core.model.aut.login.LoginManager;
import com.logus.core.model.translation.TM;
import com.logus.core.model.util.Formats;
import com.logus.core.view.dialog.NotificationDialog;
import com.logus.core.view.list.BeanGrid;
import com.logus.kaizen.model.apoio.ApoioDataService;
import com.logus.kaizen.model.apoio.atendimento.Atendimento;
import com.logus.kaizen.model.apoio.processo.Passo;
import com.logus.kaizen.model.apoio.tipomondai.AbstractAtribuicaoPassoItem;
import com.logus.kaizen.model.chronos.Chronos;
import com.logus.kaizen.model.kotae.configuracao.KotaeConfiguracao;
import com.logus.kaizen.model.kotae.configuracao.KotaeConfiguracao.TipoKotae;
import com.logus.kaizen.model.solicitacao.Solicitacao;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.kaizen.view.apoio.processo.ProcessoUtil;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;

import logus.security.model.UserVO;

/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class SolicitacaoGrid extends BeanGrid<Solicitacao> {

	/**
	 * Número serial
	 */
	private static final long serialVersionUID = -5792111538275287597L;
	private String codigoUsuario;
	private Atendimento atendimentoOrigem;
	private Chronos toguruAtual;
	private KotaeConfiguracao configuracao;
	private HashSet<Button> botoes = new HashSet<>();
	private HashMap<String, Button> mapaBotoes = new HashMap<String, Button>();

	/**
	 * Construtor responsável por criar a tabela contendo as solicitações
	 * persistidas
	 */
	public SolicitacaoGrid() {
		UserVO user = LoginManager.getAccessControl().getUser();
		if (null != user) {
			codigoUsuario = user.getCodigo();
		}
		if (null == configuracao) {
			Collection<KotaeConfiguracao> configuracoes = ApoioDataService.get().getKotaeConfiguracaoDao()
					.loadConfiguracaoByTipo(TipoKotae.TOGURU);
			if (configuracoes.size() > 0) {
				configuracao = (KotaeConfiguracao) configuracoes.toArray()[0];
			}
		}
		if (null != configuracao) {
			toguruAtual = new Chronos();
			toguruAtual.setConfiguracao(configuracao);
			AbstractAtribuicaoPassoItem primeiroAtendimento = ProcessoUtil.getPrimeiroAtendimento(toguruAtual);
			atendimentoOrigem = ((Passo) primeiroAtendimento).getAtendimentoOrigem();
			toguruAtual = ApoioDataService.get().getToguruDao().loadUltimoToguruDoResponsavel(atendimentoOrigem,
					codigoUsuario);
		}
		addColumn(Solicitacao::getChaveMondai).setFlexGrow(0).setSortable(true)
				.setHeader(TM.translate(KaizenTranslator.SOLICITACAO_MONDAI));
		addColumn(Solicitacao::getTituloMondai).setFlexGrow(4).setSortable(true)
				.setHeader(TM.translate(KaizenTranslator.SOLICITACAO_TITULO_MONDAI));
//		addColumn(data -> StringUtils.capitalize(data.getCodigoSolicitante())).setFlexGrow(1).setSortable(true)
//				.setHeader(TM.translate(KaizenTranslator.SOLICITACAO_CODIGO_SOLICITANTE));
//		addColumn(data -> StringUtils.capitalize(data.getCodigoResponsavelAtual())).setFlexGrow(1).setSortable(true)
//				.setHeader(TM.translate(KaizenTranslator.SOLICITACAO_CODIGO_RESPONSAVEL));
		addColumn(Solicitacao::getAtendimento).setFlexGrow(1).setSortable(true)
				.setHeader(TM.translate(KaizenTranslator.ATENDIMENTO));
		addColumn(Solicitacao::getResolucao).setFlexGrow(1).setSortable(true)
				.setHeader(TM.translate(KaizenTranslator.RESOLUCAO));
		addColumn(
				data -> (null != data.getDataFicarPronto()) ? Formats.getDateFormat().format(data.getDataFicarPronto())
						: "").setFlexGrow(3).setSortable(true)
								.setHeader(TM.translate(KaizenTranslator.SOLICITACAO_DATA_FICAR_PRONTO));
		addColumn(data -> (null != data.getDataSolicitacao())
				? Formats.getDateTimeFormat().format(data.getDataSolicitacao())
				: "").setFlexGrow(3).setSortable(true)
						.setHeader(TM.translate(KaizenTranslator.SOLICITACAO_DATA_SOLICITACAO));
		addComponentColumn(item -> createChronos(item)).setFlexGrow(2).setHeader(TM.translate(KaizenTranslator.CHRONOS))
				.setSortable(false);
	}

	private Button createChronos(Solicitacao item) {
		Button button = new Button();
		botoes.add(button);
		mapaBotoes.put(item.getChaveMondai(), button);
		button.setText("Início");
		button.getElement().setAttribute("theme", "primary");
		if (null != toguruAtual) {
			Long id = toguruAtual.getSolicitacao().getId();
			if (item.getId().equals(id)) {
				button.setText("Término");
				button.getElement().getStyle().set("backgroundColor", "red");
			}
		}
		button.addClickListener(e -> {
			if ("Início".equals(button.getText())) {
				iniciarRelogio(item);
				button.setText("Término");
				button.getElement().getStyle().set("backgroundColor", "red");
			} else {
				terminarRelogio(item);
				button.setText("Início");
				button.getElement().getStyle().remove("backgroundColor");
			}
			resetBotoes(button);
		});
		button.getElement().getStyle().set("width", "120px");
		button.setIcon(VaadinIcon.CLOCK.create());
		return button;
	}

	private void resetBotoes(Button bAtual) {
		for (Button b : botoes) {
			if (!b.equals(bAtual) && !b.getText().equals("Início")) {
				b.setText("Início");
				b.getElement().getStyle().remove("backgroundColor");
			}
		}
	}

	private void terminarRelogio(Solicitacao item) {
		if (null == configuracao) {
			Collection<KotaeConfiguracao> configuracoes = ApoioDataService.get().getKotaeConfiguracaoDao()
					.loadConfiguracaoByTipo(TipoKotae.TOGURU);
			configuracao = (KotaeConfiguracao) configuracoes.toArray()[0];
		}
		toguruAtual.setConfiguracao(configuracao);
		AbstractAtribuicaoPassoItem primeiroAtendimento = ProcessoUtil.getPrimeiroAtendimento(toguruAtual);
		Atendimento atendimentoDestino = ((Passo) primeiroAtendimento).getAtendimentoDestino();
		toguruAtual.setAtendimento(atendimentoDestino);
		toguruAtual.setDataFim(new Date(System.currentTimeMillis()));
		ApoioDataService.get().getToguruDao().update(toguruAtual);
		NotificationDialog.showInfo(TM.translate(KaizenTranslator.CHRONOS_TERMINADO),
				"Atividade em " + item.getChaveMondai() + " concluída.");
	}

	private void iniciarRelogio(Solicitacao item) {
		Chronos toguru = new Chronos();
		if (codigoUsuario == null) {
			codigoUsuario = LoginManager.getAccessControl().getUser().getCodigo();
		}
		Date dataInicio = new Date(System.currentTimeMillis());
		toguru = toguru.configuracao(configuracao);
		if (null == atendimentoOrigem) {
			AbstractAtribuicaoPassoItem primeiroAtendimento = ProcessoUtil.getPrimeiroAtendimento(toguru);
			atendimentoOrigem = ((Passo) primeiroAtendimento).getAtendimentoOrigem();
		}
		toguru = toguru.ativo(Boolean.TRUE).codigoResponsavel(codigoUsuario).dataInicio(dataInicio).solicitacao(item);
		toguru = toguru.atendimento(atendimentoOrigem);
		ApoioDataService.get().getToguruDao().insert(toguru);
		encerraDemaisAtividadesResponsavel(toguru);
		NotificationDialog.showInfo(TM.translate(KaizenTranslator.CHRONOS_INICIADO),
				item.getChaveMondai() + " em andamento.");
	}

	private void encerraDemaisAtividadesResponsavel(Chronos selected) {
		AbstractAtribuicaoPassoItem ultimaAtribuicaoPassoItem = ProcessoUtil.getUltimoPassoItem(selected);
		Passo ultimoPasso = null;
		if(ultimaAtribuicaoPassoItem instanceof Passo) {
			ultimoPasso = (Passo) ultimaAtribuicaoPassoItem;
		} else {
			ultimoPasso = ultimaAtribuicaoPassoItem.getPasso();
		}
		Collection<Chronos> togurusAbertosResponsavel = ApoioDataService.get().geToguruDao()
				.loadTogurusByResponsavel(selected.getCodigoResponsavel());
		for (Chronos toguru : togurusAbertosResponsavel) {
			if (!selected.equals(toguru) && !toguru.getAtendimento().equals(ultimoPasso.getAtendimentoDestino())) {
				toguru.setDataFim(new Date(System.currentTimeMillis()));
				toguru.setAtendimento(ultimoPasso.getAtendimentoDestino());
				ApoioDataService.get().getToguruDao().update(toguru);
			}
		}
	}

	public HashMap<String, Button> getMapaBotoes() {
		return mapaBotoes;
	}

}
