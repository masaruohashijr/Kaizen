package com.logus.kaizen.app;

import com.logus.core.model.dialog.DialogButtonType;
import com.logus.core.view.list.ListViewPage;

/**
 * Ponto de partida para criação de páginas da aplicação, centralizando
 * eventuais aspectos comuns.
 *
 * @author Masaru Ohashi Júnior
 * @version 1.0.0
 * @since 1 de mar de 2019
 *
 */
@SuppressWarnings("serial")
public abstract class KaizenAbstractPage<T extends Object>
  extends ListViewPage<T> {

	public KaizenAbstractPage(DialogButtonType dialogButtonType) {
		super(dialogButtonType);
	}

}
