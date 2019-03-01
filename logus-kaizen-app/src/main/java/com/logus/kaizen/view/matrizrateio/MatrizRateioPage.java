package com.logus.kaizen.view.matrizrateio;

import java.util.Collection;

import com.logus.kaizen.model.centrocusto.CustosDataService;
import com.logus.kaizen.model.matrizrateio.MatrizRateio;
import com.logus.kaizen.model.translation.KaizenTranslator;
import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.form.BeanForm;
import com.logus.core.view.list.BeanGrid;
import com.logus.core.view.list.ListViewPage;
import com.logus.kaizen.app.KaizenAbstractPage;
import com.logus.kaizen.app.KaizenMainLayout;
import com.vaadin.flow.router.Route;

/**
 * {@link ListViewPage} de {@link MatrizRateio}.
 *
 * @author Walace Zloccowick Maia
 */
@SuppressWarnings("serial")
@Route(value = "matrizrateio", layout = KaizenMainLayout.class)
public class MatrizRateioPage
  extends KaizenAbstractPage<MatrizRateio> {

  /**
   * Construtor.
   */
  public MatrizRateioPage() {
    super();
    setEditWindowWidth(600);
    setEditWindowHeight(500);
  }

  @Override
  protected BeanGrid<MatrizRateio> createGrid() {
    return new MatrizRateioGrid();
  }

  @Override
  protected BeanForm<MatrizRateio> createForm(MatrizRateio object) {
    return new MatrizRateioForm(object);
  }

  @Override
  protected void deleteObject(MatrizRateio obj)
    throws Exception {
    CustosDataService.get().getMatrizRateioDao().delete(obj);
  }

  @Override
  protected void insertObject(MatrizRateio obj)
    throws Exception {
    CustosDataService.get().getMatrizRateioDao().insert(obj);

  }

  @Override
  protected void updateObject(MatrizRateio obj)
    throws Exception {
    CustosDataService.get().getMatrizRateioDao().update(obj);

  }

  @Override
  protected Object getNomeEntidade() {
    return TM.translate(KaizenTranslator.MATRIZ_RATEIO);
  }

  @Override
  protected Object getArtigoEntidade() {
    return TM.translate(CoreTranslator.ART_FEM_SING);
  }

  @Override
  protected Collection<MatrizRateio> loadAll() {
    return CustosDataService.get().getMatrizRateioDao().loadAll();
  }

  @Override
  protected String getNomeEntidadePlural() {
    return TM.translate(KaizenTranslator.MATRIZ_RATEIO_PLURAL);
  }

}
