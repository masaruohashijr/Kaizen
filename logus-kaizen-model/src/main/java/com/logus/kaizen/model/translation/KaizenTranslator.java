package com.logus.kaizen.model.translation;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.logus.kaizen.model.matrizrateio.MatrizRateio.TipoMatriz;
import com.logus.core.model.translation.AbstractMapTranslator;
import com.logus.core.model.translation.Translator;

/**
 * Tradutor de mensagens específicas do Sistema de Controle de Deploys. Serve
 * como ponto de partida para traduções e como factory destas traduções.
 * Centraliza as constantes que definem as mensagens a serem traduzidas para os
 * diversos {@link Locale}. Esta instância será responsável para a tradução para
 * o Português, que será mais frequente, evitando-se na maioria dos casos a
 * necessidade de abrir dois arquivos para a tradução.
 *
 * @author Masaru Ohashi Júnior
 * @version 1.0.0
 * @since 1 de mar de 2019
 */
@SuppressWarnings("javadoc")
public class KaizenTranslator extends AbstractMapTranslator {

	/**
	 * Português brasileiro.
	 */
	private static Locale PT_BR = new Locale("PT", "BR");
	/**
	 * Inglês americano.
	 */
	private static Locale EN_US = new Locale("EN", "US");

	/**
	 * Mapa de tradutores indexados por {@link Locale}.
	 */
	private static Map<Locale, Translator> instances = new HashMap<Locale, Translator>();

	public static final String CODIGO = "CODIGO";
	public static final String NOME = "NOME";
	public static final String NOME_SISTEMA = "NOME_SISTEMA";
	public static final String COMPONENTES = "COMPONENTES";
	public static final String ATIVO = "ATIVO";

	// Menu Inicial
	public static final String SOLICITACAO = "SOLICITACAO";
	public static final String DEPLOY = "DEPLOY";
	public static final String APOIO = "APOIO";
	public static final String PLANO = "PLANO";
	public static final String MONDAI = "MONDAI";

	// Apoio
	public static final String PRODUTO = "PRODUTO";
	public static final String CLIENTE = "CLIENTE";
	public static final String AMBIENTE = "AMBIENTE";
	public static final String ATENDIMENTO = "ATENDIMENTO";
	public static final String URGENCIA = "URGENCIA";
	public static final String BIBLIOTECA = "BIBLIOTECA";
	public static final String PROCESSO = "PROCESSO";
	public static final String PROJETO = "PROJETO";
	public static final String TRANSICAO = "TRANSICAO";
	public static final String RESOLUCAO = "RESOLUCAO";

	// Solicitação
	public static final String SOLICITACAO_TITULO_ISSUE_OBRIGATORIO = "SOLICITACAO_TITULO_ISSUE_OBRIGATORIO";
	public static final String SOLICITACAO_TAMANHO_TITULO_ISSUE = "SOLICITACAO_TAMANHO_TITULO_ISSUE";
	public static final String SOLICITACAO_TAMANHO_NOME_SOLICITANTE = "SOLICITACAO_TAMANHO_NOME_SOLICITANTE";
	public static final String SOLICITACAO_ISSUE_OBRIGATORIO = "SOLICITACAO_ISSUE_OBRIGATORIO";
	public static final String SOLICITACAO_PLURAL = "SOLICITACAO_PLURAL";
	public static final String SOLICITACAO_TAMANHO_ISSUE = "SOLICITACAO_TAMANHO_ISSUE";
	public static final String SOLICITACAO_ISSUE = "SOLICITACAO_ISSUE";
	public static final String SOLICITACAO_TITULO_ISSUE = "SOLICITACAO_TITULO_ISSUE";
	public static final String SOLICITACAO_DATA_COMMIT = "SOLICITACAO_DATA_COMMIT";
	public static final String SOLICITACAO_DATA_SOLICITACAO = "SOLICITACAO_DATA_SOLICITACAO";
	public static final String SOLICITACAO_BIBLIOTECAS_ALTERADAS = "SOLICITACAO_BIBLIOTECAS_ALTERADAS";
	public static final String SOLICITACAO_VERSAO = "SOLICITACAO_VERSAO";
	public static final String SOLICITACAO_VERSAO_TAMANHO = "SOLICITACAO_VERSAO_TAMANHO";
	public static final String SOLICITACAO_REPOSITORIO = "SOLICITACAO_REPOSITORIO";
	public static final String SOLICITACAO_REPOSITORIO_TAMANHO = "SOLICITACAO_REPOSITORIO_TAMANHO";
	public static final String SOLICITACAO_NOME_SOLICITANTE = "SOLICITACAO_NOME_SOLICITANTE";
	public static final String SOLICITACAO_NOME_SOLICITANTE_TAMANHO = "SOLICITACAO_NOME_SOLICITANTE_TAMANHO";

	public static final String SOLICITACAO_CLIENTE_OBRIGATORIO = "SOLICITACAO_CLIENTE_OBRIGATORIO";
	public static final String SOLICITACAO_PRODUTO_OBRIGATORIO = "SOLICITACAO_PRODUTO_OBRIGATORIO";
	public static final String SOLICITACAO_AMBIENTE_OBRIGATORIO = "SOLICITACAO_AMBIENTE_OBRIGATORIO";
	public static final String SOLICITACAO_ATENDIMENTO_OBRIGATORIO = "SOLICITACAO_ATENDIMENTO_OBRIGATORIO";
	public static final String SOLICITACAO_URGENCIA_OBRIGATORIA = "SOLICITACAO_URGENCIA_OBRIGATORIA";
	public static final String SOLICITACAO_DATA_COMMIT_OBRIGATORIA = "SOLICITACAO_DATA_COMMIT_OBRIGATORIA";
	public static final String SOLICITACAO_NOME_SOLICITANTE_OBRIGATORIO = "SOLICITACAO_NOME_SOLICITANTE_OBRIGATORIO";

	public static final String ITEM_SOLICITACAO = "ITEM_MATRIZ_SOLICITACAO";
	public static final String ITEM_SOLICITACAO_PLURAL = "ITEM_SOLICITACAO_PLURAL";
	public static final String ITEM_SOLICITACAO_DATA_ULTIMO_ATENDIMENTO = "ITEM_SOLICITACAO_DATA_ULTIMO_ATENDIMENTO";
	public static final String ITEM_SOLICITACAO_PERCENTUAL = "PERCENTUAL";
	public static final String ITEM_SOLICITACAO_PERCENTUAL_MENOR_100 = "ITEM_SOLICITACAO_PERCENTUAL_MENOR_100";
	public static final String ITEM_SOLICITACAO_OBRIGATORIO = "ITEM_SOLICITACAO_OBRIGATORIO";
	public static final String ITEM_SOLICITACAO_AMBIENTE_OBRIGATORIO = "ITEM_SOLICITACAO_AMBIENTE_OBRIGATORIO";
	public static final String ITEM_SOLICITACAO_URGENCIA_OBRIGATORIA = "ITEM_SOLICITACAO_URGENCIA_OBRIGATORIA";
	public static final String ITEM_SOLICITACAO_ATENDIMENTO_OBRIGATORIO = "ITEM_SOLICITACAO_ATENDIMENTO_OBRIGATORIO";
	public static final String ITEM_SOLICITACAO_GCM = "ITEM_SOLICITACAO_GCM";
	public static final String ITEM_SOLICITACAO_GCM_TAMANHO = "ITEM_SOLICITACAO_GCM_TAMANHO";
	public static final String ITEM_SOLICITACAO_PERCENTUAL_OBRIGATORIO = "ITEM_SOLICITACAO_PERCENTUAL_OBRIGATORIO";
	public static final String ITEM_SOLICITACAO_PERCENTUAL_MAIOR_0 = "ITEM_SOLICITACAO_PERCENTUAL_MAIOR_0";

	// Plano
	public static final String PLANO_PLURAL = "PLANO_PLURAL";
	public static final String PLANO_DESCRICAO = "PLANO_DESCRICAO";
	public static final String PLANO_TAMANHO_DESCRICAO = "PLANO_TAMANHO_DESCRICAO";
	public static final String PLANO_DATA_PLANEJAMENTO = "PLANO_DATA_PLANEJAMENTO";
	public static final String PLANO_DATA_INTEGRACAO = "PLANO_DATA_INTEGRACAO";
	public static final String PLANO_BIBLIOTECAS_ALTERADAS = "PLANO_BIBLIOTECAS_ALTERADAS";
	public static final String PLANO_VERSAO = "PLANO_VERSAO";
	public static final String PLANO_VERSAO_TAMANHO = "PLANO_VERSAO_TAMANHO";
	public static final String PLANO_REFERENCIA = "PLANO_REFERENCIA";
	public static final String PLANO_REFERENCIA_TAMANHO = "PLANO_REFERENCIA_TAMANHO";
	public static final String PLANO_NOME_GCM = "PLANO_NOME_GCM";
	public static final String PLANO_NOME_GCM_TAMANHO = "PLANO_NOME_GCM_TAMANHO";
	public static final String PLANO_ATENDIMENTO = "PLANO_ATENDIMENTO";

	public static final String PLANO_CLIENTE_OBRIGATORIO = "PLANO_CLIENTE_OBRIGATORIO";
	public static final String PLANO_PRODUTO_OBRIGATORIO = "PLANO_PRODUTO_OBRIGATORIO";
	public static final String PLANO_AMBIENTE_OBRIGATORIO = "PLANO_AMBIENTE_OBRIGATORIO";
	public static final String PLANO_ATENDIMENTO_OBRIGATORIO = "PLANO_ATENDIMENTO_OBRIGATORIO";
	public static final String PLANO_URGENCIA_OBRIGATORIA = "PLANO_URGENCIA_OBRIGATORIA";
	public static final String PLANO_DATA_PLANEJAMENTO_OBRIGATORIA = "PLANO_DATA_COMMIT_OBRIGATORIA";
	public static final String PLANO_NOME_GCM_OBRIGATORIO = "PLANO_NOME_SOLICITANTE_OBRIGATORIO";

	// Cliente
	public static final String CLIENTE_NOME_OBRIGATORIO = "CLIENTE_NOME_OBRIGATORIO";
	public static final String CLIENTE_OBRIGATORIO = "CLIENTE_OBRIGATORIO";
	public static final String CLIENTE_TAMANHO_NOME = "CLIENTE_TAMANHO_NOME";
	public static final String CLIENTE_PLURAL = "CLIENTE_PLURAL";
	public static final String CLIENTE_CHAVE_JIRA = "CLIENTE_CHAVE_JIRA";
	public static final String CLIENTE_AMBIENTES = "CLIENTE_AMBIENTES";
	public static final String CLIENTE_TAMANHO_CHAVE_JIRA = "CLIENTE_TAMANHO_CHAVE_JIRA";
	public static final String CLIENTE_TAMANHO_DESCRICAO = "CLIENTE_TAMANHO_DESCRICAO";
	public static final String CLIENTE_DESCRICAO = "CLIENTE_DESCRICAO";

	// Produto
	public static final String PRODUTO_NOME_OBRIGATORIO = "PRODUTO_NOME_OBRIGATORIO";
	public static final String PRODUTO_TAMANHO_NOME = "PRODUTO_TAMANHO_NOME";
	public static final String PRODUTO_PLURAL = "PRODUTO_PLURAL";
	public static final String PRODUTO_DESCRICAO = "PRODUTO_DESCRICAO";
	public static final String PRODUTO_TAMANHO_DESCRICAO = "PRODUTO_TAMANHO_DESCRICAO";

	// Ambiente
	public static final String AMBIENTE_OBRIGATORIO = "AMBIENTE_OBRIGATORIO";
	public static final String AMBIENTE_NOME_OBRIGATORIO = "AMBIENTE_NOME_OBRIGATORIO";
	public static final String AMBIENTE_HOST = "AMBIENTE_HOST";
	public static final String AMBIENTE_HOST_TAMANHO_NOME = "AMBIENTE_HOST_TAMANHO_NOME";
	public static final String AMBIENTE_TAMANHO_NOME = "AMBIENTE_TAMANHO_NOME";
	public static final String AMBIENTE_PLURAL = "AMBIENTE_PLURAL";
	public static final String AMBIENTE_TAMANHO_DESCRICAO = "AMBIENTE_TAMANHO_DESCRICAO";
	public static final String AMBIENTE_DESCRICAO = "AMBIENTE_DESCRICAO";

	// Atendimento
	public static final String ATENDIMENTO_TITULO = "ATENDIMENTO_TITULO";
	public static final String ATENDIMENTO_DESCRICAO_OBRIGATORIA = "ATENDIMENTO_DESCRICAO_OBRIGATORIA";
	public static final String ATENDIMENTO_TAMANHO_DESCRICAO = "ATENDIMENTO_TAMANHO_DESCRICAO";
	public static final String ATENDIMENTO_TITULO_OBRIGATORIO = "ATENDIMENTO_TITULO_OBRIGATORIO";
	public static final String ATENDIMENTO_TAMANHO_TITULO = "ATENDIMENTO_TAMANHO_TITULO";
	public static final String ATENDIMENTO_PLURAL = "ATENDIMENTO_PLURAL";
	public static final String ATENDIMENTO_DESCRICAO = "ATENDIMENTO_DESCRICAO";
	public static final String ATENDIMENTO_DESTINO = "ATENDIMENTO_DESTINO";
	public static final String ATENDIMENTO_ORIGEM = "ATENDIMENTO_ORIGEM";

	// Processo
	public static final String PROCESSO_DESCRICAO_OBRIGATORIA = "PROCESSO_DESCRICAO_OBRIGATORIA";
	public static final String PROCESSO_TAMANHO_DESCRICAO = "PROCESSO_TAMANHO_DESCRICAO";
	public static final String PROCESSO_NOME_OBRIGATORIO = "PROCESSO_NOME_OBRIGATORIO";
	public static final String PROCESSO_TAMANHO_NOME = "PROCESSO_TAMANHO_NOME";
	public static final String PROCESSO_PLURAL = "PROCESSO_PLURAL";
	public static final String PROCESSO_DESCRICAO = "PROCESSO_DESCRICAO";
	public static final String PROCESSO_PASSO = "PROCESSO_PASSO";
	public static final String PROCESSO_PASSO_PLURAL = "PROCESSO_PASSO_PLURAL";

	// Projeto
	public static final String PROJETO_DESCRICAO_OBRIGATORIA = "PROJETO_DESCRICAO_OBRIGATORIA";
	public static final String PROJETO_TAMANHO_DESCRICAO = "PROJETO_TAMANHO_DESCRICAO";
	public static final String PROJETO_NOME_OBRIGATORIO = "PROJETO_NOME_OBRIGATORIO";
	public static final String PROJETO_TAMANHO_NOME = "PROJETO_TAMANHO_NOME";
	public static final String PROJETO_PLURAL = "PROJETO_PLURAL";
	public static final String PROJETO_DESCRICAO = "PROJETO_DESCRICAO";
	public static final String PROJETO_TAMANHO_MONDAI = "PROJETO_TAMANHO_MONDAI";
	public static final String PROJETO_CHAVE_JIRA = "PROJETO_CHAVE_JIRA";
	public static final String PROJETO_CHAVE_MONDAI = "PROJETO_CHAVE_MONDAI";
	public static final String PROJETO_TAMANHO_ISSUE = "PROJETO_TAMANHO_ISSUE";
	public static final String PROJETO_MONDAI_OBRIGATORIO = "PROJETO_MONDAI_OBRIGATORIO";
	public static final String PROJETO_PAPEL = "PROJETO_PAPEL";
	public static final String PROJETO_PAPEL_PLURAL = "PROJETO_PAPEL_PLURAL";

	// Transição
	public static final String TRANSICAO_DESCRICAO_OBRIGATORIA = "TRANSICAO_DESCRICAO_OBRIGATORIA";
	public static final String TRANSICAO_TAMANHO_DESCRICAO = "TRANSICAO_TAMANHO_DESCRICAO";
	public static final String TRANSICAO_NOME_OBRIGATORIO = "TRANSICAO_NOME_OBRIGATORIO";
	public static final String TRANSICAO_TAMANHO_NOME = "TRANSICAO_TAMANHO_NOME";
	public static final String TRANSICAO_PLURAL = "TRANSICAO_PLURAL";
	public static final String TRANSICAO_DESCRICAO = "TRANSICAO_DESCRICAO";

	// Resolução
	public static final String RESOLUCAO_DESCRICAO_OBRIGATORIA = "RESOLUCAO_DESCRICAO_OBRIGATORIA";
	public static final String RESOLUCAO_TAMANHO_DESCRICAO = "RESOLUCAO_TAMANHO_DESCRICAO";
	public static final String RESOLUCAO_NOME_OBRIGATORIO = "RESOLUCAO_NOME_OBRIGATORIO";
	public static final String RESOLUCAO_TAMANHO_NOME = "RESOLUCAO_TAMANHO_NOME";
	public static final String RESOLUCAO_PLURAL = "RESOLUCAO_PLURAL";
	public static final String RESOLUCAO_DESCRICAO = "RESOLUCAO_DESCRICAO";

	// Urgência
	public static final String URGENCIA_NOME_OBRIGATORIO = "URGENCIA_NOME_OBRIGATORIO";
	public static final String URGENCIA_TAMANHO_NOME = "URGENCIA_TAMANHO_NOME";
	public static final String URGENCIA_PLURAL = "URGENCIA_PLURAL";
	public static final String URGENCIA_DESCRICAO = "URGENCIA_DESCRICAO";
	public static final String URGENCIA_TAMANHO_DESCRICAO = "URGENCIA_TAMANHO_DESCRICAO";

	// Biblioteca
	public static final String BIBLIOTECA_NOME_OBRIGATORIO = "BIBLIOTECA_NOME_OBRIGATORIO";
	public static final String BIBLIOTECA_TAMANHO_NOME = "BIBLIOTECA_TAMANHO_NOME";
	public static final String BIBLIOTECA_PLURAL = "BIBLIOTECA_PLURAL";
	public static final String BIBLIOTECA_TAMANHO_DESCRICAO = "BIBLIOTECA_TAMANHO_DESCRICAO";
	public static final String BIBLIOTECA_DESCRICAO = "BIBLIOTECA_DESCRICAO";

	// Tipo Mondai
	public static final String TIPO_MONDAI = "TIPO_MONDAI";
	public static final String TIPO_MONDAI_NOME_OBRIGATORIO = "TIPO_MONDAI_NOME_OBRIGATORIO";
	public static final String TIPO_MONDAI_TAMANHO_NOME = "TIPO_MONDAI_TAMANHO_NOME";
	public static final String TIPO_MONDAI_PLURAL = "TIPO_MONDAI_PLURAL";
	public static final String TIPO_MONDAI_TAMANHO_DESCRICAO = "TIPO_MONDAI_TAMANHO_DESCRICAO";
	public static final String TIPO_MONDAI_DESCRICAO = "TIPO_MONDAI_DESCRICAO";

	// Tipo Mondai Projeto
	public static final String TIPO_MONDAI_PROJETO = "TIPO_MONDAI_PROJETO";
	public static final String TIPO_MONDAI_PROJETO_PLURAL = "TIPO_MONDAI_PROJETO_PLURAL";

	// Matriz de Rateio
	public static final String MATRIZ_RATEIO = "MATRIZ_RATEIO";
	public static final String MATRIZ_RATEIO_PLURAL = "MATRIZ_RATEIO_PLURAL";
	public static final String MATRIZ_RATEIO_TIPO = "MATRIZ_RATEIO_TIPO";
	public static final String MATRIZ_RATEIO_PRINCIPAL = "MATRIZ_RATEIO_PRINCIPAL";
	public static final String MATRIZ_RATEIO_NOME_OBRIGATORIO = "MATRIZ_RATEIO_NOME_OBRIGATORIO";
	public static final String MATRIZ_RATEIO_TAMANHO_NOME = "MATRIZ_RATEIO_TAMANHO_NOME";
	public static final String MATRIZ_RATEIO_PELO_MENOS_UM_ITEM = "MATRIZ_RATEIO_PELO_MENOS_UM_ITEM";
	public static final String MATRIZ_RATEIO_TIPO_OBRIGATORIO = "MATRIZ_RATEIO_TIPO_OBRIGATORIO";
	public static final String MATRIZ_RATEIO_SQL_RATEIO = "MATRIZ_RATEIO_SQL_RATEIO";
	public static final String MATRIZ_RATEIO_SQL_OBRIGATORIO = "MATRIZ_RATEIO_SQL_OBRIGATORIO";
	public static final String MATRIZ_RATEIO_ITENS_REPETIDOS = "MATRIZ_RATEIO_ITENS_REPETIDOS";
	public static final String MATRIZ_RATEIO_TOTALIZAR_100 = "MATRIZ_RATEIO_TOTALIZAR_100";
	public static final String ITEM_RATEIO = "ITEM_MATRIZ_RATEIO";
	public static final String ITEM_RATEIO_PLURAL = "ITEM_RATEIO_PLURAL";
	public static final String ITEM_RATEIO_PERCENTUAL = "PERCENTUAL";
	public static final String ITEM_RATEIO_PERCENTUAL_MENOR_100 = "ITEM_RATEIO_PERCENTUAL_MENOR_100";
	public static final String ITEM_RATEIO_DIVIDA_OBRIGATORIO = "ITEM_RATEIO_DIVIDA_OBRIGATORIO";
	public static final String ITEM_RATEIO_PERCENTUAL_OBRIGATORIO = "ITEM_RATEIO_PERCENTUAL_OBRIGATORIO";
	public static final String ITEM_RATEIO_PERCENTUAL_MAIOR_0 = "ITEM_RATEIO_PERCENTUAL_MAIOR_0";

	// Centros de Custos
	public static final String CENTRO_CUSTO = "CENTRO_CUSTO";
	public static final String CENTRO_CUSTO_NOME_OBRIGATORIO = "CENTRO_CUSTO_NOME_OBRIGATORIO";
	public static final String CENTRO_CUSTO_TAMANHO_NOME = "CENTRO_CUSTO_TAMANHO_NOME";
	public static final String CENTRO_CUSTO_PLURAL = "CENTRO_CUSTO_PLURAL";

	// Liberação
	public static final String LIBERACAO = "LIBERACAO";
	public static final String LIBERACAO_AMBIENTE_OBRIGATORIO = "LIBERACAO_AMBIENTE_OBRIGATORIO";
	public static final String LIBERACAO_PLANO_OBRIGATORIO = "LIBERACAO_PLANO_OBRIGATORIO";
	public static final String LIBERACAO_VERSAO = "LIBERACAO_VERSAO";
	public static final String LIBERACAO_REFERENCIA = "LIBERACAO_REFERENCIA";
	public static final String LIBERACAO_RESPONSAVEL = "LIBERACAO_RESPONSAVEL";
	public static final String LIBERACAO_DATA = "LIBERACAO_DATA";
	public static final String LIBERACAO_RESPONSAVEL_TAMANHO = "LIBERACAO_RESPONSAVEL_TAMANHO";
	public static final String LIBERACAO_REFERENCIA_TAMANHO = "LIBERACAO_REFERENCIA_TAMANHO";
	public static final String LIBERACAO_VERSAO_TAMANHO = "LIBERACAO_VERSAO_TAMANHO";

	// Passo
	public static final String PASSO_TAMANHO_NOME = "PASSO_TAMANHO_NOME";
	public static final String PASSO_NOME_OBRIGATORIO = "PASSO_NOME_OBRIGATORIO";
	public static final String PASSO_TAMANHO_DESCRICAO = "PASSO_TAMANHO_DESCRICAO";
	public static final String PASSO_DESCRICAO = "PASSO_DESCRICAO";
	public static final String PASSO_TRANSICAO = "PASSO_TRANSICAO";
	public static final String PASSO_ATENDIMENTO_ORIGEM = "PASSO_ATENDIMENTO_ORIGEM";
	public static final String PASSO_ATENDIMENTO_DESTINO = "PASSO_ATENDIMENTO_DESTINO";

	/**
	 * Construtor.
	 */
	protected KaizenTranslator() {
		super();
		// --------------------------------
		// Gerais
		// --------------------------------

		put(NOME_SISTEMA, "Kaizen");
		put(CODIGO, "Código");
		put(NOME, "Nome");
		put(ATIVO, "Ativo");
		put(COMPONENTES, "Componentes");

		// --------------------------------
		// Menu
		// --------------------------------

		put(APOIO, "Apoio");

		// --------------------------------
		// Plano
		// --------------------------------

		put(DEPLOY, "Deploy");
		put(PLANO, "Plano de Builds");
		put(PLANO_PLURAL, "Planos de Builds");
		put(PLANO_DESCRICAO, "Descrição");
		put(PLANO_TAMANHO_DESCRICAO, "A Descrição deve possuir entre {0} e {1} caracteres.");
		put(PLANO_DATA_PLANEJAMENTO, "Data do Planejamento");
		put(PLANO_DATA_INTEGRACAO, "Data da Integração");
		put(PLANO_VERSAO, "Versão");
		put(PLANO_VERSAO_TAMANHO, "A Versão deve possuir entre {0} e {1} caracteres.");
		put(PLANO_ATENDIMENTO, "Atendimento");
		put(PLANO_ATENDIMENTO_OBRIGATORIO, "Informe o Atendimento do Plano de Builds.");
		put(PLANO_REFERENCIA, "Referência");
		put(PLANO_REFERENCIA_TAMANHO, "A Referência deve possuir entre {0} e {1} caracteres.");
		put(PLANO_NOME_GCM, "Quem é o Responsável GCM ?");
		put(PLANO_DATA_PLANEJAMENTO_OBRIGATORIA, "Informe a Data do Planejamento de Builds.");
		put(PLANO_NOME_GCM_TAMANHO, "O Nome do Responsável GCM deve possuir entre {0} e {1} caracteres.");
		put(PLANO_NOME_GCM_OBRIGATORIO, "Informe o Nome do Responsável GCM");

		// --------------------------------
		// Solicitação
		// --------------------------------

		put(MONDAI, "Mondai");
		put(SOLICITACAO, "Solicitação");
		put(SOLICITACAO_PLURAL, "Solicitações");
		put(SOLICITACAO_ISSUE, "Issue");
		put(SOLICITACAO_TITULO_ISSUE, "Sumário");
		put(SOLICITACAO_DATA_COMMIT, "Data do Commit");
		put(SOLICITACAO_DATA_SOLICITACAO, "Solicitado Em");
		put(SOLICITACAO_BIBLIOTECAS_ALTERADAS, "Bibliotecas alteradas");
		put(SOLICITACAO_VERSAO, "Versão");
		put(SOLICITACAO_REPOSITORIO, "Implementado Em");
		put(SOLICITACAO_NOME_SOLICITANTE, "Solicitante");
		put(SOLICITACAO_TITULO_ISSUE_OBRIGATORIO, "Título da issue deve ser informado.");
		put(SOLICITACAO_TAMANHO_TITULO_ISSUE, "O título da issue deve possuir entre {0} e {1} caracteres.");
		put(SOLICITACAO_TAMANHO_NOME_SOLICITANTE, "O Nome do solicitante deve possuir entre {0} e {1} caracteres.");
		put(SOLICITACAO_ISSUE_OBRIGATORIO, "A chave da ISSUE deve ser informada.");
		put(SOLICITACAO_TAMANHO_ISSUE, "A issue deve possuir entre {0} e {1} caracteres.");
		put(SOLICITACAO_CLIENTE_OBRIGATORIO, "Informe o cliente da solicitação.");
		put(SOLICITACAO_PRODUTO_OBRIGATORIO, "Informe o produto da solicitação.");
		put(SOLICITACAO_AMBIENTE_OBRIGATORIO, "Informe o ambiente da solicitação.");
		put(SOLICITACAO_ATENDIMENTO_OBRIGATORIO, "Informe o status do atendimento da solicitação.");
		put(SOLICITACAO_URGENCIA_OBRIGATORIA, "Informe a urgência da solicitação.");
		put(SOLICITACAO_DATA_COMMIT_OBRIGATORIA, "Informe a data de commit da solicitação.");
		put(SOLICITACAO_VERSAO_TAMANHO, "A Versão deve possuir entre {0} e {1} caracteres.");
		put(SOLICITACAO_REPOSITORIO_TAMANHO, "O Repositório deve possuir entre {0} e {1} caracteres.");
		put(SOLICITACAO_NOME_SOLICITANTE_TAMANHO, "O Nome do Solicitante deve possuir entre {0} e {1} caracteres.");
		put(SOLICITACAO_NOME_SOLICITANTE_OBRIGATORIO, "Informe o Nome do Solicitante");

		// --------------------------------
		// Item da Solicitação
		// --------------------------------
		put(ITEM_SOLICITACAO, "Item da Solicitação");
		put(ITEM_SOLICITACAO_PLURAL, "Itens da Solicitação");
		put(ITEM_SOLICITACAO_DATA_ULTIMO_ATENDIMENTO, "Data do Último Atendimento");
		put(ITEM_SOLICITACAO_PERCENTUAL, "Percentual");
		put(ITEM_SOLICITACAO_OBRIGATORIO, "O centro de custo do item de rateio deve ser informado.");
		put(ITEM_SOLICITACAO_AMBIENTE_OBRIGATORIO, "O ambiente do item da solicitação deve ser informado.");
		put(ITEM_SOLICITACAO_URGENCIA_OBRIGATORIA, "A urgência do item da solicitação deve ser informada.");
		put(ITEM_SOLICITACAO_ATENDIMENTO_OBRIGATORIO, "O atendimento do item da solicitação deve ser informado.");
		put(ITEM_SOLICITACAO_PERCENTUAL_OBRIGATORIO, "O percentual do item de rateio deve ser informado.");
		put(ITEM_SOLICITACAO_PERCENTUAL_MENOR_100, "O percentual do item da matriz de rateio não pode exceder 100.");
		put(ITEM_SOLICITACAO_PERCENTUAL_MAIOR_0, "O percentual do item da matriz de rateio deve ser maior que 0.");
		put(ITEM_SOLICITACAO_GCM, "Responsável GCM");
		put(ITEM_SOLICITACAO_GCM_TAMANHO, "O Nome do Responsavel GCM deve possuir entre {0} e {1} caracteres.");

		// --------------------------------
		// Cliente
		// --------------------------------
		put(CLIENTE, "Cliente");
		put(CLIENTE_PLURAL, "Clientes");
		put(CLIENTE_DESCRICAO, "Descrição");
		put(CLIENTE_OBRIGATORIO, "O Cliente deve ser informado.");
		put(CLIENTE_NOME_OBRIGATORIO, "O Nome do Cliente deve ser informado.");
		put(CLIENTE_CHAVE_JIRA, "Chave do Projeto no Jira");
		put(CLIENTE_AMBIENTES, "Ambientes Disponíveis");
		put(CLIENTE_TAMANHO_NOME, "O Nome do Cliente deve possuir entre {0} e {1} caracteres.");
		put(CLIENTE_TAMANHO_DESCRICAO, "A Descrição do Cliente deve possuir entre {0} e {1} caracteres.");
		put(CLIENTE_TAMANHO_CHAVE_JIRA, "A Chave no Jira deve possuir entre {0} e {1} caracteres.");

		// --------------------------------
		// Produto
		// --------------------------------
		put(PRODUTO, "Produto");
		put(PRODUTO_PLURAL, "Produtos");
		put(PRODUTO_DESCRICAO, "Descrição");
		put(PRODUTO_NOME_OBRIGATORIO, "O Nome do Produto deve ser informado.");
		put(PRODUTO_TAMANHO_NOME, "O Nome do Produto deve possuir entre {0} e {1} caracteres.");

		// --------------------------------
		// Ambiente
		// --------------------------------
		put(AMBIENTE, "Ambiente");
		put(AMBIENTE_PLURAL, "Ambientes");
		put(AMBIENTE_DESCRICAO, "Descrição");
		put(AMBIENTE_OBRIGATORIO, "O Ambiente deve ser informado.");
		put(AMBIENTE_NOME_OBRIGATORIO, "O Nome do Ambiente deve ser informado.");
		put(AMBIENTE_HOST, "Host");
		put(AMBIENTE_HOST_TAMANHO_NOME, "O Nome do Host do Ambiente deve possuir entre {0} e {1} caracteres.");
		put(AMBIENTE_TAMANHO_NOME, "O Nome do Ambiente deve possuir entre {0} e {1} caracteres.");
		put(AMBIENTE_TAMANHO_DESCRICAO, "A Descrição do Ambiente deve possuir entre {0} e {1} caracteres.");

		// --------------------------------
		// Atendimento
		// --------------------------------
		put(ATENDIMENTO, "Atendimento");
		put(ATENDIMENTO_DESTINO, "Atendimento Destino");
		put(ATENDIMENTO_ORIGEM, "Atendimento Origem");
		put(ATENDIMENTO_DESCRICAO, "Descrição");
		put(ATENDIMENTO_PLURAL, "Atendimentos");
		put(ATENDIMENTO_TITULO, "Título");
		put(ATENDIMENTO_TITULO_OBRIGATORIO, "O Título do Atendimento deve ser informado.");
		put(ATENDIMENTO_TAMANHO_TITULO, "O Título do Atendimento deve possuir entre {0} e {1} caracteres.");
		put(ATENDIMENTO_DESCRICAO_OBRIGATORIA, "A Descrição do Atendimento deve ser informada.");
		put(ATENDIMENTO_TAMANHO_DESCRICAO, "O Nome da Descrição deve possuir entre {0} e {1} caracteres.");

		// --------------------------------
		// Processo
		// --------------------------------
		put(PROCESSO, "Processo");
		put(PROCESSO_DESCRICAO, "Descrição");
		put(PROCESSO_PASSO, "Passo");
		put(PROCESSO_PASSO_PLURAL, "Passos");
		put(PROCESSO_PLURAL, "Processos");
		put(PROCESSO_NOME_OBRIGATORIO, "Nome do Processo deve ser informado.");
		put(PROCESSO_TAMANHO_NOME, "O Nome do Processo deve possuir entre {0} e {1} caracteres.");
		put(PROCESSO_DESCRICAO_OBRIGATORIA, "A Descrição do Processo deve ser informada.");
		put(PROCESSO_TAMANHO_DESCRICAO, "O Nome da Processo deve possuir entre {0} e {1} caracteres.");

		// --------------------------------
		// Passo
		// --------------------------------
		put(PASSO_TRANSICAO, "Transição");
		put(PASSO_DESCRICAO, "Descrição");
		put(PASSO_TAMANHO_DESCRICAO, "A Descrição do Passo deve possuir entre {0} e {1} caracteres.");
		put(PASSO_ATENDIMENTO_DESTINO, "Atendimento Destino");
		put(PASSO_ATENDIMENTO_ORIGEM, "Atendimento Origem");
		put(PASSO_TAMANHO_NOME, "O Nome do Passo deve possuir entre {0} e {1} caracteres.");
		put(PASSO_NOME_OBRIGATORIO, "O Nome do Passo deve ser informado.");

		// --------------------------------
		// Projeto
		// --------------------------------
		put(PROJETO, "Projeto");
		put(PROJETO_CHAVE_JIRA, "Chave Jira");
		put(PROJETO_CHAVE_MONDAI, "Chave Mondai");
		put(PROJETO_DESCRICAO, "Descrição");
		put(PROJETO_PLURAL, "Projetos");
		put(PROJETO_NOME_OBRIGATORIO, "Nome do Projeto deve ser informado.");
		put(PROJETO_TAMANHO_NOME, "O Nome do Projeto deve possuir entre {0} e {1} caracteres.");
		put(PROJETO_DESCRICAO_OBRIGATORIA, "A Descrição do Projeto deve ser informada.");
		put(PROJETO_TAMANHO_DESCRICAO, "O Nome da Projeto deve possuir entre {0} e {1} caracteres.");
		put(PROJETO_MONDAI_OBRIGATORIO, "A Chave Mondai do Projeto deve ser informado.");
		put(PROJETO_TAMANHO_MONDAI, "A Chave Mondai do Projeto deve possuir entre {0} e {1} caracteres.");
		put(PROJETO_TAMANHO_ISSUE, "A Issue do Projeto deve possuir entre {0} e {1} caracteres.");
		put(PROJETO_PAPEL, "Passo");
		put(PROJETO_PAPEL_PLURAL, "Papeis");

		// --------------------------------
		// Transição
		// --------------------------------
		put(TRANSICAO, "Transição");
		put(TRANSICAO_DESCRICAO, "Descrição");
		put(TRANSICAO_PLURAL, "Transições");
		put(TRANSICAO_NOME_OBRIGATORIO, "Nome da Transição deve ser informado.");
		put(TRANSICAO_TAMANHO_NOME, "O Nome da Transição deve possuir entre {0} e {1} caracteres.");
		put(TRANSICAO_DESCRICAO_OBRIGATORIA, "A Descrição do Transição deve ser informada.");
		put(TRANSICAO_TAMANHO_DESCRICAO, "O Nome da Transição deve possuir entre {0} e {1} caracteres.");

		// --------------------------------
		// Projeto
		// --------------------------------
		put(RESOLUCAO, "Resolução");
		put(RESOLUCAO_DESCRICAO, "Descrição");
		put(RESOLUCAO_PLURAL, "Resoluções");
		put(RESOLUCAO_NOME_OBRIGATORIO, "O Nome da Resolução deve ser informado.");
		put(RESOLUCAO_TAMANHO_NOME, "O Nome da Resolução deve possuir entre {0} e {1} caracteres.");
		put(RESOLUCAO_DESCRICAO_OBRIGATORIA, "A Descrição da Resolução deve ser informada.");
		put(RESOLUCAO_TAMANHO_DESCRICAO, "O Nome da Resolução deve possuir entre {0} e {1} caracteres.");

		// --------------------------------
		// Urgência
		// --------------------------------
		put(URGENCIA, "Urgência");
		put(URGENCIA_PLURAL, "Urgências");
		put(URGENCIA_DESCRICAO, "Descrição");
		put(URGENCIA_NOME_OBRIGATORIO, "Nome da Urgência deve ser informado.");
		put(URGENCIA_TAMANHO_NOME, "O Nome da Urgência deve possuir entre {0} e {1} caracteres.");

		// --------------------------------
		// Biblioteca
		// --------------------------------
		put(BIBLIOTECA, "Biblioteca");
		put(BIBLIOTECA_PLURAL, "Bibliotecas");
		put(BIBLIOTECA_DESCRICAO, "Descrição");
		put(BIBLIOTECA_NOME_OBRIGATORIO, "Nome da Biblioteca deve ser informado.");
		put(BIBLIOTECA_TAMANHO_NOME, "O Nome da Biblioteca deve possuir entre {0} e {1} caracteres.");
		put(BIBLIOTECA_TAMANHO_DESCRICAO, "A Descrição da Biblioteca deve possuir entre {0} e {1} caracteres.");

		// --------------------------------
		// Tipo de Mondai
		// --------------------------------
		put(TIPO_MONDAI, "Tipo de Mondai");
		put(TIPO_MONDAI_PLURAL, "Tipos de Mondai");
		put(TIPO_MONDAI_DESCRICAO, "Descrição");
		put(TIPO_MONDAI_NOME_OBRIGATORIO, "Nome do Tipo de Mondai deve ser informado.");
		put(TIPO_MONDAI_TAMANHO_NOME, "O Nome do Tipo de Mondai deve possuir entre {0} e {1} caracteres.");
		put(TIPO_MONDAI_TAMANHO_DESCRICAO, "A Descrição do Tipo de Mondai deve possuir entre {0} e {1} caracteres.");

		// --------------------------------
		// Tipo de Mondai do Projeto
		// --------------------------------
		put(TIPO_MONDAI_PROJETO, "Tipo de Mondai do Projeto");
		put(TIPO_MONDAI_PROJETO_PLURAL, "Tipos de Mondai do Projeto");

		// --------------------------------
		// Liberação
		// --------------------------------
		put(LIBERACAO, "Liberação");
		put(LIBERACAO_RESPONSAVEL, "Responsável pela Liberação");
		put(LIBERACAO_DATA, "Data da Liberação");
		put(LIBERACAO_VERSAO, "Versão");
		put(LIBERACAO_RESPONSAVEL, "Responsável GCM");
		put(LIBERACAO_REFERENCIA, "Referência");

		// --------------------------------
		// Matriz de rateio
		// --------------------------------

		put(MATRIZ_RATEIO, "Matriz de Rateio");
		put(MATRIZ_RATEIO_PLURAL, "Matrizes de Rateio");
		put(MATRIZ_RATEIO_PRINCIPAL, "Principal");
		put(MATRIZ_RATEIO_SQL_RATEIO, "SQL");
		put(MATRIZ_RATEIO_TIPO, "Tipo");
		put(TipoMatriz.DINAMICA.name(), "Dinâmica");
		put(TipoMatriz.ESTATICA.name(), "Estática");
		put(MATRIZ_RATEIO_NOME_OBRIGATORIO, "Nome da matriz de rateio deve ser informado.");
		put(MATRIZ_RATEIO_TAMANHO_NOME, "O nome da matriz de rateio deve possuir entre {0} e {1} caracteres.");
		put(MATRIZ_RATEIO_PELO_MENOS_UM_ITEM, "Pelo menos um item de rateio deve ser informado.");
		put(MATRIZ_RATEIO_TIPO_OBRIGATORIO, "Tipo de matriz deve ser informado.");

		put(ITEM_RATEIO, "Item de Rateio");
		put(ITEM_RATEIO_PLURAL, "Itens de Rateio");
		put(ITEM_RATEIO_PERCENTUAL, "Percentual");
		put(ITEM_RATEIO_DIVIDA_OBRIGATORIO, "O centro de custo do item de rateio deve ser informado.");
		put(ITEM_RATEIO_PERCENTUAL_OBRIGATORIO, "O percentual do item de rateio deve ser informado.");
		put(ITEM_RATEIO_PERCENTUAL_MENOR_100, "O percentual do item da matriz de rateio não pode exceder 100.");
		put(ITEM_RATEIO_PERCENTUAL_MAIOR_0, "O percentual do item da matriz de rateio deve ser maior que 0.");
		put(MATRIZ_RATEIO_SQL_OBRIGATORIO, "Claúsula SQL deve ser informada para a matriz de rateio.");
		put(MATRIZ_RATEIO_ITENS_REPETIDOS,
				"Não pode haver dois itens de rateio para o mesmo centro de custo. Há mais de um item para o centro de custo \"{0}\".");
		put(MATRIZ_RATEIO_TOTALIZAR_100, "A soma dos percentuais dos itens da matriz de rateio deve ser 100.");

		put(CENTRO_CUSTO, "Centro de Custo");
		put(CENTRO_CUSTO_PLURAL, "Centros de Custo");
		put(CENTRO_CUSTO_NOME_OBRIGATORIO, "Nome do Centro de Custo deve ser informado.");
		put(CENTRO_CUSTO_TAMANHO_NOME, "O nome do Centro de Custo deve possuir entre {0} e {1} caracteres.");
	}

	/**
	 * @param locale {@link Locale} para o qual será retornado o {@link Translator}.
	 * @return um {@link Translator} para o {@link Locale} informado.
	 */
	public static Translator getInstance(Locale locale) {
		Translator instance = instances.get(locale);
		if (instance == null) {
			if (PT_BR.equals(locale)) {
				instance = new KaizenTranslator();
			} else if (EN_US.equals(locale)) {
				instance = new KaizenTranslatorEnUs();
			} else {
				instance = new KaizenTranslator();
			}
			instances.put(locale, instance);
		}
		return instance;

	}
}
