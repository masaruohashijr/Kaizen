package com.logus.kaizen.app;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.logus.core.model.aut.Funcionalidade;
import com.logus.core.view.app.Application.ComponentProvider;
import com.logus.core.view.app.layouts.MenuItem;
import com.logus.kaizen.model.FuncionalidadeLivreAcesso;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.Icon;
/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class KaizenMenuItem extends MenuItem {

	private KaizenMenuItem parent;
	private Set<Funcionalidade> permissoesNecessarias = new HashSet<Funcionalidade>();
	private List<KaizenMenuItem> filhos = new ArrayList<KaizenMenuItem>();

	public KaizenMenuItem(String caption, Icon icon, Class<? extends Component> clazz,
			Class<? extends Funcionalidade> fClazz, KaizenMenuItem parent) {
		this.permissoesNecessarias = buildPermissoesNecessarias(fClazz);
		this.parent = parent;
		setCaption(caption);
		setIcon(icon);
		setProvider(() -> {
			try {
				if(null != clazz) {
					return clazz.newInstance();
				}
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			return null;
		});
		addParentChildren(parent);
	}

	private void addParentChildren(KaizenMenuItem dmiParent) {
		if(null != this.parent) {
			this.parent.getFilhos().add(this);
		}
	}

	private Set<Funcionalidade> buildPermissoesNecessarias(Class<? extends Funcionalidade> fClazz) {
		if (null != fClazz) {
			Field[] declaredFields = fClazz.getDeclaredFields();
			try {
				for (Field declaredField : declaredFields) {
					Funcionalidade funcionalidade = (Funcionalidade) declaredField.get(null);
					permissoesNecessarias.add(funcionalidade);
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		} else {
			permissoesNecessarias.add(FuncionalidadeLivreAcesso.FUNC_LIVRE_ACESSO);
		}
		return permissoesNecessarias;
	}

	/**
	 * @return {@link #caption}
	 */
	public String getCaption() {
		return super.getCaption();
	}

	/**
	 * @return {@link #icon}
	 */
	public Icon getIcon() {
		return super.getIcon();
	}

	/**
	 * @return {@link #provider}
	 */
	public ComponentProvider getProvider() {
		return super.getProvider();
	}

	/**
	 * @return {@link #children}
	 */
	public List<KaizenMenuItem> getFilhos() {
		return filhos;
	}
	public List<MenuItem> getChildren() {
		return super.getChildren();
	}

	public Set<Funcionalidade> getPermissoesNecessarias() {
		if (null != filhos && !filhos.isEmpty()) {
			for (KaizenMenuItem d : filhos) {
				permissoesNecessarias.addAll(d.getPermissoesNecessarias());
			}
		}
		return permissoesNecessarias;
	}

	public KaizenMenuItem getParent() {
		return this.parent;
	}

}
