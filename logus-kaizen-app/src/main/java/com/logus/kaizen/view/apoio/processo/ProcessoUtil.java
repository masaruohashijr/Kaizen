package com.logus.kaizen.view.apoio.processo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.logus.kaizen.model.apoio.atendimento.Atendimento;
import com.logus.kaizen.model.apoio.funcao.Funcao;
import com.logus.kaizen.model.apoio.funcao.ItemFuncao;
import com.logus.kaizen.model.apoio.processo.Passo;
import com.logus.kaizen.model.apoio.processo.Processo;
import com.logus.kaizen.model.apoio.projeto.ItemPapel;
import com.logus.kaizen.model.apoio.projeto.Papel;
import com.logus.kaizen.model.apoio.tipomondai.AbstractAtribuicaoPassoItem;
import com.logus.kaizen.model.apoio.tipomondai.FuncaoPassoItem;
import com.logus.kaizen.model.apoio.tipomondai.PapelPassoItem;
import com.logus.kaizen.model.apoio.tipomondai.TipoMondai;
import com.logus.kaizen.model.apoio.tipomondai.TipoMondaiProjeto;
import com.logus.kaizen.model.chronos.Chronos;
import com.logus.kaizen.model.kotae.plano.Plano;
import com.logus.kaizen.model.solicitacao.Solicitacao;

public class ProcessoUtil {

	public static Collection<AbstractAtribuicaoPassoItem> getProximosPassosItens(Solicitacao selected,
			Collection<ItemPapel> itensPapeisProjetoUsuario, Collection<ItemFuncao> itensFuncoesUsuario,
			String codigoUsuario) {
		TipoMondai tipoMondai = selected.getTipoMondai();
		Processo processo = tipoMondai.getProcesso();
		Collection<Passo> passos = new ArrayList<>();
		Collection<AbstractAtribuicaoPassoItem> atribuicoesPassoItem = new ArrayList<AbstractAtribuicaoPassoItem>();
		Set<Passo> passosSet = new HashSet<>();
		Set<Funcao> funcoesUsuariosSet = createFuncoesSet(itensFuncoesUsuario);
		Set<Papel> papeisProjetoUsuarioSet = createPapeisSet(itensPapeisProjetoUsuario);
		TipoMondaiProjeto tipoMondaiProjeto = null;
		if (null == processo) {
			Collection<TipoMondaiProjeto> tiposMondaiProjeto = selected.getProjeto().getTiposMondaiProjeto();
			for (TipoMondaiProjeto tipo : tiposMondaiProjeto) {
				if (tipo.getTipoMondai().equals(tipoMondai)) {
					tipoMondaiProjeto = tipo;
					processo = tipo.getProcesso();
				}
			}
		} else {
			passos = processo.getPassos();
			for (Passo passo : passos) {
				Atendimento atendimentoOrigem = passo.getAtendimentoOrigem();
				if (selected.getAtendimento().equals(atendimentoOrigem) && !passosSet.contains(passo)) {
					atribuicoesPassoItem.add(passo);
					passosSet.add(passo);
				}
			}
		}
		Collection<FuncaoPassoItem> funcaoPassoItems = tipoMondai.getFuncoesPassos();
		for (FuncaoPassoItem funcaoPassoItem : funcaoPassoItems) {
			Atendimento atendimentoOrigem = funcaoPassoItem.getAtendimentoOrigemPasso();
			Funcao funcaoNecessaria = funcaoPassoItem.getFuncao();
			// Se o Atendimento Origem for igual, armazenar os passos.
			if (selected.getAtendimento().equals(atendimentoOrigem) && funcoesUsuariosSet.contains(funcaoNecessaria)) {
				passosSet.add(funcaoPassoItem.getPasso());
				atribuicoesPassoItem.add(funcaoPassoItem);
			}
		}
		if (null != tipoMondaiProjeto) {
			passos = processo.getPassos();
			for (Passo passo : passos) {
				Atendimento atendimentoOrigem = passo.getAtendimentoOrigem();
				if (selected.getAtendimento().equals(atendimentoOrigem) && !passosSet.contains(passo)) {
					passosSet.add(passo);
					atribuicoesPassoItem.add(passo);
				}
			}
			Collection<PapelPassoItem> papeisPassosItens = tipoMondaiProjeto.getPapeisPassosItens();
			for (PapelPassoItem papelPassoItem : papeisPassosItens) {
				Atendimento atendimentoOrigem = papelPassoItem.getAtendimentoOrigemPasso();
				Papel papelNecessario = papelPassoItem.getPapel();
				// Se o Atendimento Origem for igual, armazenar os passos.
				if (selected.getAtendimento().equals(atendimentoOrigem)){
					if(!papeisProjetoUsuarioSet.contains(papelNecessario)) {
						passosSet.remove(papelPassoItem.getPasso());
						atribuicoesPassoItem.remove(papelPassoItem.getPasso());
					}
				}
			}
		}
		return atribuicoesPassoItem;
	}

	private static Set<Papel> createPapeisSet(Collection<ItemPapel> itensPapeisProjetoUsuario) {
		HashSet<Papel> papeisSet = new HashSet<Papel>();
		itensPapeisProjetoUsuario = (null == itensPapeisProjetoUsuario) ? new ArrayList<>() : itensPapeisProjetoUsuario;
		for (ItemPapel itemPapel : itensPapeisProjetoUsuario) {
			papeisSet.add(itemPapel.getPapel());
		}
		return papeisSet;
	}

	private static Set<Funcao> createFuncoesSet(Collection<ItemFuncao> itensFuncoesUsuario) {
		HashSet<Funcao> funcoesSet = new HashSet<Funcao>();
		itensFuncoesUsuario = (null == itensFuncoesUsuario) ? new ArrayList<>() : itensFuncoesUsuario;
		for (ItemFuncao itemFuncao : itensFuncoesUsuario) {
			funcoesSet.add(itemFuncao.getFuncao());
		}
		return funcoesSet;
	}

	public static AbstractAtribuicaoPassoItem getPrimeiroAtendimento(Solicitacao object) {
		Processo processo = object.getTipoMondai().getProcesso();
		Atendimento atendimentoOrigem = null;
		AbstractAtribuicaoPassoItem atribuicaoPassoItem = null;
		if (null != processo) {
			Collection<Passo> passos = object.getTipoMondai().getProcesso().getPassos();
			for (Passo passo : passos) {
				if (null == atendimentoOrigem || atendimentoOrigem.equals(passo.getAtendimentoDestino())) {
					atendimentoOrigem = passo.getAtendimentoOrigem();
					atribuicaoPassoItem = passo;
				}
			}
		} else {
			Collection<TipoMondaiProjeto> tiposMondaiProjeto = object.getTipoMondai().getTiposMondaiProjeto();
			for (TipoMondaiProjeto tipoMondaiProjeto : tiposMondaiProjeto) {
				if (tipoMondaiProjeto.getProjeto().equals(object.getProjeto())) {
					processo = tipoMondaiProjeto.getProcesso();
					Collection<Passo> passos = processo.getPassos();
					for (Passo passo : passos) {
						if (null == atendimentoOrigem || atendimentoOrigem.equals(passo.getAtendimentoDestino())) {
							atendimentoOrigem = passo.getAtendimentoOrigem();
							atribuicaoPassoItem = passo;
						}
					}
					break;
				}
			}
		}
		return atribuicaoPassoItem;
	}

	public static Collection<AbstractAtribuicaoPassoItem> getProximosPassosItens(Plano selected) {
		Processo processo = selected.getConfiguracao().getProcesso();
		Set<Passo> passosSet = new HashSet<>();
		Collection<AbstractAtribuicaoPassoItem> atribuicoesPassoItem = new ArrayList<AbstractAtribuicaoPassoItem>();
		Collection<Passo> passos = processo.getPassos();
		for (Passo passo : passos) {
			Atendimento atendimentoOrigem = passo.getAtendimentoOrigem();
			if (selected.getAtendimento().equals(atendimentoOrigem) && !passosSet.contains(passo)) {
				atribuicoesPassoItem.add(passo);
			}
		}
		return atribuicoesPassoItem;
	}

	public static AbstractAtribuicaoPassoItem getPrimeiroAtendimento(Plano selected) {
		Processo processo = selected.getConfiguracao().getProcesso();
		Atendimento atendimentoOrigem = null;
		AbstractAtribuicaoPassoItem atribuicaoPassoItem = null;
		if (null != processo) {
			Collection<Passo> passos = processo.getPassos();
			for (Passo passo : passos) {
				if (null == atendimentoOrigem || atendimentoOrigem.equals(passo.getAtendimentoDestino())) {
					atendimentoOrigem = passo.getAtendimentoOrigem();
					atribuicaoPassoItem = passo;
				}
			}
		}
		return atribuicaoPassoItem;
	}

	public static Collection<AbstractAtribuicaoPassoItem> getProximosPassosItens(Chronos selected) {
		Processo processo = selected.getConfiguracao().getProcesso();
		Set<Passo> passosSet = new HashSet<>();
		Collection<AbstractAtribuicaoPassoItem> atribuicoesPassoItem = new ArrayList<AbstractAtribuicaoPassoItem>();
		Collection<Passo> passos = processo.getPassos();
		for (Passo passo : passos) {
			Atendimento atendimentoOrigem = passo.getAtendimentoOrigem();
			if (selected.getAtendimento().equals(atendimentoOrigem) && !passosSet.contains(passo)) {
				atribuicoesPassoItem.add(passo);
			}
		}
		return atribuicoesPassoItem;
	}

	public static AbstractAtribuicaoPassoItem getPrimeiroAtendimento(Chronos selected) {
		Processo processo = selected.getConfiguracao().getProcesso();
		Atendimento atendimentoOrigem = null;
		AbstractAtribuicaoPassoItem atribuicaoPassoItem = null;
		if (null != processo) {
			Collection<Passo> passos = processo.getPassos();
			for (Passo passo : passos) {
				if (null == atendimentoOrigem || atendimentoOrigem.equals(passo.getAtendimentoDestino())) {
					atendimentoOrigem = passo.getAtendimentoOrigem();
					atribuicaoPassoItem = passo;
				}
			}
		}
		return atribuicaoPassoItem;
	}

	public static AbstractAtribuicaoPassoItem getUltimoPassoItem(Chronos selected) {
		Processo processo = selected.getConfiguracao().getProcesso();
		Atendimento atendimentoFinal = null;
		AbstractAtribuicaoPassoItem atribuicaoPassoItem = null;
		if (null != processo) {
			Collection<Passo> passos = processo.getPassos();
			for (Passo passo : passos) {
				if (null == atendimentoFinal || atendimentoFinal.equals(passo.getAtendimentoDestino())) {
					atendimentoFinal = passo.getAtendimentoDestino();
					atribuicaoPassoItem = passo;
				}
			}
		}
		return atribuicaoPassoItem;
	}

}
