package com.logus.kaizen.app;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ResourceBundle;

import com.logus.core.model.aut.login.LoginException;
import com.logus.core.model.aut.login.LoginManager;
import com.logus.core.view.app.LoginComponent;
import com.logus.kaizen.view.mondai.SolicitacaoPage;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
/**
 *
 * @author Masaru Ohashi Júnior
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
@Route(value = "login")
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
public class KaizenLoginComponent extends LoginComponent {
	/**
	 * Construtor.
	 */
	public KaizenLoginComponent() {
		super(LoginManager.getAccessControl(), () -> {
			UI.getCurrent().navigate(SolicitacaoPage.class);
		});
		try {
			new ConfigurarLoginUtil().configurar(this);
		} catch (LoginException e) {
			System.err.println(e.getMessage());
		}
	}

	private class ConfigurarLoginUtil {

		private static final String PROPERTIES_LOGIN = "properties/login";
		private static final String LOGIN_USERNAME = "login.username";
		private static final String LOGIN_PASSORD = "login.password";

		public void configurar(Object objectParm) throws LoginException {
			ResourceBundle properties = ResourceBundle.getBundle(PROPERTIES_LOGIN);
			Class<?> superclass = objectParm.getClass().getSuperclass();
			Field[] declaredFields = superclass.getDeclaredFields();
			for (Field field : declaredFields) {
				String fieldName = field.getName();
				if ("username".equals(fieldName)) {
					try {
						Class<?> clazz = field.getType();
						field.setAccessible(true);
						Object obj = field.get(objectParm);
						Method method = clazz.getMethod("setValue", String.class);
						String login = properties.getString(LOGIN_USERNAME);
						if(login.contains("${login.username}")) {
							login = "masaru";
						}
						method.invoke(obj, login);
					} catch (NoSuchMethodException | SecurityException | IllegalAccessException
							| IllegalArgumentException | InvocationTargetException e) {
						throw new LoginException("Campo username inválido.", e);
					}
				}
				if ("password".equals(fieldName)) {
					try {
						Class<?> clazz = field.getType();
						field.setAccessible(true);
						Object obj = field.get(objectParm);
						Method method = clazz.getMethod("setValue", String.class);
						String password = properties.getString(LOGIN_PASSORD);
						if(password.contains("${login.password}")) {
							password = "senha123";
						}
						method.invoke(obj, password);
					} catch (NoSuchMethodException | SecurityException | IllegalAccessException
							| IllegalArgumentException | InvocationTargetException e) {
						throw new LoginException("Campo password inválido.", e);
					}
				}
			}
		}

	}
/*
	@Override
	public String getDescription() {
		return "TESTE DESCRIÇÃO";
	}

	@Override
	public String getImageBackgroundPath() {
		return "";
	}

	@Override
	public String getImagePath() {
		return "";
	}

	@Override
	public String getTitle() {
		return "TESTE";
	}
*/
}
