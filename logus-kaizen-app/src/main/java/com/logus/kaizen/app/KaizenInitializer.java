package com.logus.kaizen.app;

import java.util.Locale;

import com.logus.core.model.aut.login.AccessControl;
import com.logus.core.model.aut.login.LoginManager;
import com.logus.core.model.translation.AbstractMapTranslator;
import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.model.translation.Translator;
import com.logus.core.view.app.AbstractInitializer;
import com.logus.core.view.app.LegacyAccessControl;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;

/**
 * Inicializa RequestHandlers, tradutores, listeners, conexões com o banco, etc.
 * Esta classe deve ser registrada segundo os padrões definidos para o Vaadin
 * para um {@link VaadinServiceInitListener}. O arquivo
 * resources/META-INF/services/com.vaadin.flow.server.VaadinServiceInitListener
 * deve ser criado e nele inserido o nome qualificado para esta classe.
 *
 * @author Masaru Ohashi Júnior
 * @version 1.0.0
 * @since 1 de mar de 2019
 *
 */
@SuppressWarnings("serial")
public class KaizenInitializer extends AbstractInitializer {

	private Locale locale = LoginManager.getLocale();

	@Override
	public void serviceInit(ServiceInitEvent event) {
		super.serviceInit(event);
		LegacyAccessControl.initDataBaseConnection();
	}

	@Override
	protected Class<? extends Component> getLoginViewClass() {
		return KaizenLoginComponent.class;
	}

	@Override
	protected AbstractMapTranslator getAppTranslator() {
		return (AbstractMapTranslator) KaizenTranslator.getInstance(locale);
	}

	@Override
	protected Translator getAppAdminTranslator() {
		return KaizenTranslator.getInstance(PT_BR);
	}

	@Override
	protected AccessControl getAccessControl() {
		return LegacyAccessControl.get();
	}

	@Override
	protected void initTranslator(ServiceInitEvent event) {
		event.addRequestHandler((session, request, response) -> {
			// Mensagens para o usuário
			TM.setTranslators(CoreTranslator.getInstance(LoginManager.getLocale()), getAppTranslator(),
					KaizenTranslator.getInstance(locale));
			// Mensagens de log para o administrador do sistema.
			TM.setAdminTranslators(CoreTranslator.getInstance(PT_BR), getAppAdminTranslator());
			return false;
		});
	}
}
