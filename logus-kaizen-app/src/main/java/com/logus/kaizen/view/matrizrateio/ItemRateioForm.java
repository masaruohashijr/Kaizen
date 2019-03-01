package com.logus.kaizen.view.matrizrateio;

import com.logus.kaizen.model.centrocusto.CentroCusto;
import com.logus.kaizen.model.centrocusto.CustosDataService;
import com.logus.kaizen.model.matrizrateio.ItemRateio;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.kaizen.model.translation.CustosTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.form.BeanForm;
import com.vaadin.flow.component.Component;

/**
 * {@link BeanForm} para a edição de um {@link ItemRateio}.
 *
 * @author Walace Zloccowick Maia
 */
@SuppressWarnings("serial")
public class ItemRateioForm extends BeanForm<ItemRateio> {

	/**
	 * Construtor.
	 *
	 * @param object objeto a ser editado por este formulário.
	 */
	public ItemRateioForm(ItemRateio object) {
		super(object);
		add(focus(fullWidth(createCentroCustoComboBox())));
		add(createPercentField());
	}

	/**
	 * Cria o componente que permite selecionar o {@link CentroCusto}.
	 *
	 * @return o componente criado.
	 */
	private Component createCentroCustoComboBox() {
		return createBeanComboBox(TM.translate(CustosTranslator.CENTRO_CUSTO), "centroCusto",
				CustosDataService.get().getCentroCustoDao().loadAll());
	}

	/**
	 * @return o componente criado.
	 */
	private Component createPercentField() {
		return createDecimalField(TM.translate(KaizenTranslator.ITEM_RATEIO_PERCENTUAL), "percentual");
	}

}
