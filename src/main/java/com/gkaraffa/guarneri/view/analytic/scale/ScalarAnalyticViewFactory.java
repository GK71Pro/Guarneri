package com.gkaraffa.guarneri.view.analytic.scale;

import com.gkaraffa.cremona.theoretical.ToneGroupObject;
import com.gkaraffa.cremona.theoretical.scale.Scale;
import com.gkaraffa.guarneri.view.ViewFactory;
import com.gkaraffa.guarneri.view.ViewQuery;
import com.gkaraffa.guarneri.view.ViewTable;
import com.gkaraffa.guarneri.view.ViewTableBuilder;

public abstract class ScalarAnalyticViewFactory implements ViewFactory {
  abstract protected String[] applyHeaderArray();

  abstract protected void buildHeader(ViewTableBuilder vtBuild, String[] headerArray);

  abstract protected void buildBody(ViewTableBuilder vtBuild, Scale queryScale);

  @Override
  public ViewTable createView(ViewQuery viewQuery) {
    ViewTableBuilder vtBuild = new ViewTableBuilder();
    Scale queryScale = null;

    queryScale = this.verifyAndInterpretQuery(viewQuery);
    this.buildHeader(vtBuild, applyHeaderArray());
    this.buildBody(vtBuild, queryScale);

    return vtBuild.compileTable();
  }

  @Override
  public ViewTable createView() {
    throw new UnsupportedOperationException();
  }
  
  protected Scale verifyAndInterpretQuery(ViewQuery viewQuery) {
    ToneGroupObject tGO = (ToneGroupObject) viewQuery.getCriteria("Scale");
    
    if (tGO instanceof Scale) {
      return (Scale) tGO;
      //  this.queryScale = (Scale) tGO;
    }
    else {
      throw new IllegalArgumentException("ScalarAnalytics require ViewQuery containing a Scale");
    }
  }
}
