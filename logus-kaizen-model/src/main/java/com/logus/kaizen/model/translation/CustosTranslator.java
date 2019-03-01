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
public class CustosTranslator extends AbstractMapTranslator {

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

	// Dívida Pública
	public static final String CENTRO_CUSTO = "CENTRO_CUSTO";
	public static final String CENTRO_CUSTO_NOME_OBRIGATORIO = "CENTRO_CUSTO_NOME_OBRIGATORIO";
	public static final String CENTRO_CUSTO_TAMANHO_NOME = "CENTRO_CUSTO_TAMANHO_NOME";
	public static final String CENTRO_CUSTO_PLURAL = "CENTRO_CUSTO_PLURAL";

	/**
	 * Construtor.
	 */
	protected CustosTranslator() {
		super();

		put(CENTRO_CUSTO, "Centro de Custo");
		put(CENTRO_CUSTO_PLURAL, "Centros de Custo");
		put(CENTRO_CUSTO_NOME_OBRIGATORIO, "Nome do Centro de Custo deve ser informado.");
		put(CENTRO_CUSTO_TAMANHO_NOME, "O nome do Centro de Custo deve ter entre {0} e {1} caracteres.");
	}

	/**
	 * @param locale {@link Locale} para o qual será retornado o {@link Translator}.
	 * @return um {@link Translator} para o {@link Locale} informado.
	 */
	public static Translator getInstance(Locale locale) {
		Translator instance = instances.get(locale);
		if (instance == null) {
			if (PT_BR.equals(locale)) {
				instance = new CustosTranslator();
			} else if (EN_US.equals(locale)) {
				instance = new KaizenTranslatorEnUs();
			} else {
				instance = new CustosTranslator();
			}
			instances.put(locale, instance);
		}
		return instance;

	}
}
