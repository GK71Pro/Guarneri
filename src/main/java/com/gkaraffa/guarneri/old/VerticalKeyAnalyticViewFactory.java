package com.gkaraffa.guarneri.old;

import com.gkaraffa.cremona.theoretical.Tone;
import com.gkaraffa.guarneri.view.ViewCell;
import com.gkaraffa.guarneri.view.ViewTableBuilder;
import com.gkaraffa.guarneri.view.analytic.key.KeyAnalyticViewFactory;

public abstract class VerticalKeyAnalyticViewFactory extends KeyAnalyticViewFactory {

  @Override
  protected void buildHeader(ViewTableBuilder vtBuild, String[] headerArray) {
    int counter = 0;

    for (String selection : headerArray) {
      vtBuild.insertCell(0, counter, new ViewCell(selection));
      counter++;
    }    
  }

  @Override
  protected void buildBody(ViewTableBuilder vtBuild, Tone key) {
    int limit = 7;
    
    for (int index = 0; index < limit; index++) {
      buildRow(vtBuild, key, index);
    }
  }

  abstract protected void buildRow(ViewTableBuilder vtBuild, Tone key, int index);
}
