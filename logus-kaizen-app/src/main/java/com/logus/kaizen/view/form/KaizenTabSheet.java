package com.logus.kaizen.view.form;

import java.lang.reflect.Field;

import com.logus.core.view.form.TabSheet;
import com.vaadin.flow.component.tabs.Tabs;

public class KaizenTabSheet extends TabSheet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public Tabs getTabs(KaizenTabSheet object) {
		@SuppressWarnings("unchecked")
		Class<Object> superclass = (Class<Object>) object.getClass().getSuperclass();
		Tabs tabs = null;
		try {
			Field fieldTabs = superclass.getDeclaredField("tabs");
			fieldTabs.setAccessible(true);
			tabs = (Tabs) fieldTabs.get(object);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return tabs;
	}
}
