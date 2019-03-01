package com.logus.kaizen.app;

import java.util.Collection;

import com.google.common.base.Strings;
import com.logus.core.view.form.ListSelector;
import com.logus.core.view.list.BeanGrid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;

/**
 * @author Masaru Ohashi Júnior
 * @version 1.0.0
 * @since 1 de mar de 2019
 *
 */
public class KaizenListSelector<T> extends ListSelector<T> {

	/**
	 *
	 */
	private static final long serialVersionUID = -2607427029496667613L;

	private BeanGrid<T> grid;

	public KaizenListSelector(String caption, boolean hasFilter, BeanGrid<T> grid, SelectionMode mode,
			Collection<T> allObjects, Collection<T> selectedObjects) {
		super(caption, grid, mode, allObjects, selectedObjects);
		this.grid = grid;
		int posFilter = 0;
		if (!Strings.isNullOrEmpty(caption)) {
			posFilter = 1;
		}
		if (!hasFilter) {
			removeFilter(posFilter);
		} else {
			getElement().getChild(posFilter);
		}
	}

	private void removeFilter(int posFilter) {
		getElement().removeChild(posFilter);
	}

	public BeanGrid<T> getGrid() {
		return grid;
	}

}
