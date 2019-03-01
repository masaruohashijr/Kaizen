package com.logus.kaizen.model.translation;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.logus.core.model.translation.AbstractMapTranslator;
import com.logus.core.model.translation.Translator;

/**
 * Tradutor de mensagens específicas do Sistema de Controle de Custos. Serve
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
public class KaizenTranslatorEnUs extends AbstractMapTranslator {

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
	public static final String APOIO = "APOIO";

	// Apoio
	public static final String SOLICITANTE = "SOLICITANTE";
	public static final String PRODUTO = "PRODUTO";
	public static final String CLIENTE = "CLIENTE";
	public static final String AMBIENTE = "AMBIENTE";
	public static final String STATUS = "STATUS";
	public static final String PRIORIZACAO = "PRIORIZACAO";
	public static final String BIBLIOTECA = "BIBLIOTECA";

	// Solicitação de Deploy
	public static final String SOLICITACAO_NOME_OBRIGATORIO = "SOLICITACAO_NOME_OBRIGATORIO";
	public static final String SOLICITACAO_TAMANHO_NOME = "SOLICITACAO_TAMANHO_NOME";
	public static final String SOLICITACAO_PLURAL = "SOLICITACAO_PLURAL";

	// Cliente
	public static final String CLIENTE_NOME_OBRIGATORIO = "CLIENTE_NOME_OBRIGATORIO";
	public static final String CLIENTE_TAMANHO_NOME = "CLIENTE_TAMANHO_NOME";
	public static final String CLIENTE_PLURAL = "CLIENTE_PLURAL";

	// Produto
	public static final String PRODUTO_NOME_OBRIGATORIO = "PRODUTO_NOME_OBRIGATORIO";
	public static final String PRODUTO_TAMANHO_NOME = "PRODUTO_TAMANHO_NOME";
	public static final String PRODUTO_PLURAL = "PRODUTO_PLURAL";

	// Ambiente
	public static final String AMBIENTE_NOME_OBRIGATORIO = "AMBIENTE_NOME_OBRIGATORIO";
	public static final String AMBIENTE_TAMANHO_NOME = "AMBIENTE_TAMANHO_NOME";
	public static final String AMBIENTE_PLURAL = "AMBIENTE_PLURAL";

	// Status
	public static final String STATUS_NOME_OBRIGATORIO = "STATUS_NOME_OBRIGATORIO";
	public static final String STATUS_TAMANHO_NOME = "STATUS_TAMANHO_NOME";
	public static final String STATUS_PLURAL = "STATUS_PLURAL";

	// Priorização
	public static final String PRIORIZACAO_NOME_OBRIGATORIO = "PRIORIZACAO_NOME_OBRIGATORIO";
	public static final String PRIORIZACAO_TAMANHO_NOME = "PRIORIZACAO_TAMANHO_NOME";
	public static final String PRIORIZACAO_PLURAL = "PRIORIZACAO_PLURAL";

	// Biblioteca
	public static final String BIBLIOTECA_NOME_OBRIGATORIO = "BIBLIOTECA_NOME_OBRIGATORIO";
	public static final String BIBLIOTECA_TAMANHO_NOME = "BIBLIOTECA_TAMANHO_NOME";
	public static final String BIBLIOTECA_PLURAL = "BIBLIOTECA_PLURAL";

	/**
	 * Construtor.
	 */
	protected KaizenTranslatorEnUs() {
		super();
		// --------------------------------
		// Gerais
		// --------------------------------

		put(NOME_SISTEMA, "Controle de Deploys 3.0");
		put(CODIGO, "Código");
		put(NOME, "Nome");
		put(ATIVO, "Ativo");
		put(COMPONENTES, "Componentes");

		// --------------------------------
		// Menu
		// --------------------------------

		put(APOIO,"Apoio");

		// --------------------------------
		// Solicitação
		// --------------------------------

		put(SOLICITACAO, "Solicitação");
		put(SOLICITACAO_PLURAL, "Solicitações");
		put(SOLICITACAO_NOME_OBRIGATORIO, "Nome da solicitação deve ser informada.");
		put(SOLICITACAO_TAMANHO_NOME, "O nome da solicitação deve ter entre {0} e {1} caracteres.");

		// --------------------------------
		//
		// --------------------------------

		put(CLIENTE, "Cliente");
		put(CLIENTE_PLURAL, "Clientes");
		put(CLIENTE_NOME_OBRIGATORIO, "Nome do cliente deve ser informado.");
		put(CLIENTE_TAMANHO_NOME, "O nome do cliente deve ter entre {0} e {1} caracteres.");

		put(PRODUTO, "Produto");
		put(PRODUTO_PLURAL, "Produtos");
		put(PRODUTO_NOME_OBRIGATORIO, "Nome do produto deve ser informado.");
		put(PRODUTO_TAMANHO_NOME, "O nome do produto deve ter entre {0} e {1} caracteres.");

		put(AMBIENTE, "Ambiente");
		put(AMBIENTE_PLURAL, "Ambientes");
		put(AMBIENTE_NOME_OBRIGATORIO, "Nome do ambiente deve ser informado.");
		put(AMBIENTE_TAMANHO_NOME, "O nome do ambiente deve ter entre {0} e {1} caracteres.");

		put(STATUS, "Status");
		put(STATUS_PLURAL, "Status");
		put(STATUS_NOME_OBRIGATORIO, "Nome do status deve ser informado.");
		put(STATUS_TAMANHO_NOME, "O nome do status deve ter entre {0} e {1} caracteres.");

		put(PRIORIZACAO, "Priorização");
		put(PRIORIZACAO_PLURAL, "Priorizações");
		put(PRIORIZACAO_NOME_OBRIGATORIO, "Nome da priorização deve ser informado.");
		put(PRIORIZACAO_TAMANHO_NOME, "O nome da priorização deve ter entre {0} e {1} caracteres.");

		put(BIBLIOTECA, "Ambiente");
		put(BIBLIOTECA_PLURAL, "Ambientes");
		put(BIBLIOTECA_NOME_OBRIGATORIO, "Nome do ambiente deve ser informado.");
		put(BIBLIOTECA_TAMANHO_NOME, "O nome do ambiente deve ter entre {0} e {1} caracteres.");

	}

	/**
	 * @param locale {@link Locale} para o qual será retornado o {@link Translator}.
	 * @return um {@link Translator} para o {@link Locale} informado.
	 */
	public static Translator getInstance(Locale locale) {
		Translator instance = instances.get(locale);
		if (instance == null) {
			if (PT_BR.equals(locale)) {
				instance = new KaizenTranslatorEnUs();
			} else if (EN_US.equals(locale)) {
				instance = new KaizenTranslatorEnUs();
			} else {
				instance = new KaizenTranslatorEnUs();
			}
			instances.put(locale, instance);
		}
		return instance;

	}
}
