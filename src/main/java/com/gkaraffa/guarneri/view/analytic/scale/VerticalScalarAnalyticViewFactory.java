package com.gkaraffa.guarneri.view.analytic.scale;

import com.gkaraffa.cremona.theoretical.scale.Scale;
import com.gkaraffa.guarneri.view.ViewCell;
import com.gkaraffa.guarneri.view.ViewTableBuilder;

public abstract class VerticalScalarAnalyticViewFactory extends ScalarAnalyticViewFactory {

  @Override
  protected void buildHeader(ViewTableBuilder vtBuild, String[] headerArray) {
    int counter = 0;

    for (String selection : headerArray) {
      vtBuild.insertCell(0, counter, new ViewCell(selection));
      counter++;
    }    
  }

  @Override
  protected void buildBody(ViewTableBuilder vtBuild, Scale queryScale) {
    int collectionSize = queryScale.getToneCollection().getSize();
    
    for (int collectionPosition = 0; collectionPosition < collectionSize; collectionPosition++) {
      buildColumnFromToneCollection(vtBuild, queryScale, collectionPosition);
    }
  }

  abstract protected void buildColumnFromToneCollection(ViewTableBuilder vtBuild, Scale scale,
      int collectionPosition);  
}
