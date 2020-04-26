package com.gkaraffa.guarneri.view.analytic.scale;

import com.gkaraffa.cremona.theoretical.ToneGroupObject;
import com.gkaraffa.cremona.theoretical.scale.Scale;
import com.gkaraffa.guarneri.view.ViewFactory;
import com.gkaraffa.guarneri.view.ViewQuery;
import com.gkaraffa.guarneri.view.ViewTable;
import com.gkaraffa.guarneri.view.ViewTableBuilder;

public abstract class ScalarAnalyticViewFactory implements ViewFactory {
  private Scale queryScale = null;

  abstract protected String[] applyHeaderArray();

  abstract protected void buildHeader(ViewTableBuilder vtBuild, String[] headerArray);

  abstract protected void buildBody(ViewTableBuilder vtBuild, Scale queryScale);

  @Override
  public ViewTable createView(ViewQuery viewQuery) {
    ViewTableBuilder vtBuild = new ViewTableBuilder();

    this.verifyAndInterpretQuery(viewQuery);
    this.buildHeader(vtBuild, applyHeaderArray());
    this.buildBody(vtBuild, this.queryScale);

    return vtBuild.compileTable();
  }

  @Override
  public ViewTable createView() {
    throw new UnsupportedOperationException();
  }
  
  public Scale getQueryScale() {
    return this.queryScale;
  }

  protected void verifyAndInterpretQuery(ViewQuery viewQuery) {
    ToneGroupObject tGO = (ToneGroupObject) viewQuery.getCriteria("Scale");
    
    if (tGO instanceof Scale) {
      this.queryScale = (Scale) tGO;
    }
    else {
      throw new IllegalArgumentException("ScalarAnalytics require ViewQuery containing a Scale");
    }
  }
}
