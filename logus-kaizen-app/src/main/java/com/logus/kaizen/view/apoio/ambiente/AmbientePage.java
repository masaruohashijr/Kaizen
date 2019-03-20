/**
 *
 */
package com.logus.kaizen.view.apoio.ambiente;

import java.util.Collection;

import com.logus.core.model.dialog.DialogButtonType;
import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.form.BeanForm;
import com.logus.core.view.list.BeanGrid;
import com.logus.kaizen.app.KaizenAbstractPage;
import com.logus.kaizen.app.KaizenMainLayout;
import com.logus.kaizen.model.apoio.ApoioDataService;
import com.logus.kaizen.model.apoio.ambiente.Ambiente;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.vaadin.flow.router.Route;
/**
*
* @author Masaru Ohashi Júnior
* @since 1 de mar de 2019
* @version 1.0.0
*
*/
@Route(value = "ambiente", layout = KaizenMainLayout.class)
public class AmbientePage extends KaizenAbstractPage<Ambiente>{

  public AmbientePage() {
		super(DialogButtonType.SAVE);
	}

/**
   *
   */
  private static final long serialVersionUID = -99267169911263536L;

  /* (non-Javadoc)
   * @see com.logus.core.view.list.ListViewPage#loadAll()
   */
  @Override
  protected Collection<Ambiente> loadAll() {
    return ApoioDataService.get().getAmbienteDao().loadAll();
  }

  /**
   * Método responsável por criar a tabela contendo todos os ambiente
   * persistidos no banco
   * @return BeanGrid<Status>
   */
  @Override
  protected BeanGrid<Ambiente> createGrid() {
    return new AmbienteGrid();
  }

  /* (non-Javadoc)
   * @see com.logus.core.view.list.AbstractListEditor#createForm(java.lang.Object)
   */
  @Override
  protected BeanForm<Ambiente> createForm(Ambiente ambiente) {
    return new AmbienteForm(ambiente);
  }

  /* (non-Javadoc)
   * @see com.logus.core.view.list.AbstractListEditor#deleteObject(java.lang.Object)
   */
  @Override
  protected void deleteObject(Ambiente ambiente)
    throws Exception {
    ApoioDataService.get().getAmbienteDao().delete(ambiente);
  }

  /* (non-Javadoc)
   * @see com.logus.core.view.list.AbstractListEditor#insertObject(java.lang.Object)
   */
  @Override
  protected void insertObject(Ambiente ambiente)
    throws Exception {
    ApoioDataService.get().getAmbienteDao().insert(ambiente);
  }

  /* (non-Javadoc)
   * @see com.logus.core.view.list.AbstractListEditor#updateObject(java.lang.Object)
   */
  @Override
  protected void updateObject(Ambiente ambiente)
    throws Exception {
    ApoioDataService.get().getAmbienteDao().update(ambiente);
  }

  /* (non-Javadoc)
   * @see com.logus.core.view.list.AbstractListEditor#getNomeEntidade()
   */
  @Override
  protected Object getNomeEntidade() {
    return TM.translate(KaizenTranslator.AMBIENTE);
  }

  /* (non-Javadoc)
   * @see com.logus.core.view.list.AbstractListEditor#getNomeEntidadePlural()
   */
  @Override
  protected String getNomeEntidadePlural() {
    return TM.translate(KaizenTranslator.AMBIENTE_PLURAL);
  }

  /* (non-Javadoc)
   * @see com.logus.core.view.list.AbstractListEditor#getArtigoEntidade()
   */
  @Override
  protected Object getArtigoEntidade() {
    return TM.translate(CoreTranslator.ART_MAS_SING);
  }

}
