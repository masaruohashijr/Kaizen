package com.logus.kaizen.view.centrocusto;

import java.util.Collection;

import com.logus.kaizen.model.centrocusto.CentroCusto;
import com.logus.kaizen.model.centrocusto.CustosDataService;
import com.logus.kaizen.model.translation.CustosTranslator;
import com.logus.core.model.translation.CoreTranslator;
import com.logus.core.model.translation.TM;
import com.logus.core.view.form.BeanForm;
import com.logus.core.view.list.BeanGrid;
import com.logus.core.view.list.ListViewPage;
import com.logus.kaizen.app.KaizenAbstractPage;
import com.logus.kaizen.app.KaizenMainLayout;
import com.vaadin.flow.router.Route;

/**
 * {@link ListViewPage} para {@link CentroCusto}.
 *
 * @author Walace Zloccowick Maia
 */
@SuppressWarnings("serial")
@Route(value = "centrocusto", layout = KaizenMainLayout.class)
public class CentroCustoPage
  extends KaizenAbstractPage<CentroCusto> {

  /**
   * Construtor.
   */
  public CentroCustoPage() {
    super();
    setEditWindowHeight(-1);
    setEditWindowWidth(400);
  }

  @Override
  protected BeanGrid<CentroCusto> createGrid() {
    return new CentroCustoGrid();
  }

  @Override
  protected BeanForm<CentroCusto> createForm(CentroCusto object) {
    return new CentroCustoForm(object);
  }

  @Override
  protected void deleteObject(CentroCusto obj)
    throws Exception {
    CustosDataService.get().getCentroCustoDao().delete(obj);
  }

  @Override
  protected void insertObject(CentroCusto obj)
    throws Exception {
    CustosDataService.get().getCentroCustoDao().insert(obj);

  }

  @Override
  protected void updateObject(CentroCusto obj)
    throws Exception {
    CustosDataService.get().getCentroCustoDao().update(obj);

  }

  @Override
  protected Object getNomeEntidade() {
    return TM.translate(CustosTranslator.CENTRO_CUSTO);
  }

  @Override
  protected Object getArtigoEntidade() {
    return TM.translate(CoreTranslator.ART_MAS_SING);
  }

  @Override
  protected Collection<CentroCusto> loadAll() {
    return CustosDataService.get().getCentroCustoDao().loadAll();
  }

  @Override
  protected String getNomeEntidadePlural() {
    return TM.translate(CustosTranslator.CENTRO_CUSTO_PLURAL);
  }

}
