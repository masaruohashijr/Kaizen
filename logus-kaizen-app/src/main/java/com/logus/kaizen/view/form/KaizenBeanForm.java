package com.logus.kaizen.view.form;

import com.logus.core.view.form.BeanForm;
import com.logus.kaizen.app.KaizenListSelector;

public abstract class KaizenBeanForm<T> extends BeanForm<Object> {

	protected KaizenBeanForm(Object object) {
		super(object);
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1695946933650725950L;

	public abstract KaizenListSelector getKaizenListSelector();

}
