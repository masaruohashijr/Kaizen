package com.logus.kaizen.app;

import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.logus.core.view.app.Application;
import com.logus.core.view.app.layouts.LogusAppLayoutJohannes;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

/**
 * Definição da apresentação da aplicação. Aqui são definidos o
 * {@link RouterLayout} por herança e o {@link Theme} através de anotação. Todas
 * as páginas da aplicação serão vinculadas a este layout.
 *
 * @author Masaru Ohashi Júnior
 * @version 1.0.0
 * @since 1 de mar de 2019
 */

@SuppressWarnings("serial")
@HtmlImport("styles/shared-styles.html")
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
public class KaizenMainLayout extends LogusAppLayoutJohannes {

	private static Behaviour behaviour;

	/**
	 * Construtor.
	 */
	public KaizenMainLayout() {
		super.setBehaviour(KaizenMainLayout.behaviour);
//		getElement().getStyle().set("overflow", "auto");
	}

	@Override
	protected Application getApplication() {
		return new ValidatedKaizenApp();
	}

	public static void setCurrentBehaviour(Behaviour behaviour) {
		KaizenMainLayout.behaviour = behaviour;
	}
}
