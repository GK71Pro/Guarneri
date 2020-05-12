package com.gkaraffa.guarneri.view.analytic.key;

import com.gkaraffa.cremona.theoretical.Tone;
import com.gkaraffa.guarneri.view.ViewFactory;
import com.gkaraffa.guarneri.view.ViewQuery;
import com.gkaraffa.guarneri.view.ViewTable;
import com.gkaraffa.guarneri.view.ViewTableBuilder;

public abstract class KeyAnalyticViewFactory implements ViewFactory {
  abstract protected String[] applyHeaderArray();

  abstract protected void buildHeader(ViewTableBuilder vtBuild, String[] headerArray);

  abstract protected void buildBody(ViewTableBuilder vtBuild, Tone key);

  @Override
  public ViewTable createView(ViewQuery viewQuery) {
    ViewTableBuilder vtBuild = new ViewTableBuilder();
    Tone queryKey = null;

    queryKey = this.verifyAndInterpretQuery(viewQuery);
    this.buildHeader(vtBuild, applyHeaderArray());
    this.buildBody(vtBuild, queryKey);

    return vtBuild.compileTable();
  }

  @Override
  public ViewTable createView() {
    throw new UnsupportedOperationException();
  }

  private Tone verifyAndInterpretQuery(ViewQuery viewQuery) {
    Tone tone = (Tone) viewQuery.getCriteria("Key");

    if (tone instanceof Tone) {
      return tone;
    }
    else {
      throw new IllegalArgumentException("ScalarAnalytics require ViewQuery containing a Key");
    }
  }
}
