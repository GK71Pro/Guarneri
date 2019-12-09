package com.gkaraffa.guarneri.view.analytic.old;

import com.gkaraffa.cremona.theoretical.ToneCollection;
import com.gkaraffa.cremona.theoretical.ToneGroupObject;
import com.gkaraffa.cremona.theoretical.scale.Scale;
import com.gkaraffa.guarneri.view.ViewCell;
import com.gkaraffa.guarneri.view.ViewQuery;
import com.gkaraffa.guarneri.view.ViewTable;
import com.gkaraffa.guarneri.view.old.ViewFactory;

public abstract class HorizontalAnalyticViewFactory extends ViewFactory {

  @Override
  public ViewTable createView() {
    throw new UnsupportedOperationException(
        "Cannot create HorizontalAnalyticViewFactory without parameters.");
  }
  
  @Override
  public ViewTable createView(ViewQuery viewQuery) {
    ToneGroupObject toneGroupObject = viewQuery.getToneGroupObject();
    
    if (toneGroupObject instanceof Scale) {
      Scale scale = (Scale) toneGroupObject;
      return this.generateByScale(scale);
    }
    else {
      throw new UnsupportedOperationException("Unsupported Query");
    }
  }
  
  private ViewTable generateByScale(Scale scale) {
    String headerArray[] = createHeaderArray();
    ToneCollection toneCollection = scale.getToneCollection();
    int depth = toneCollection.getSize() + 1;
    int breadth = headerArray.length;
    ViewCell[][] modelCells = new ViewCell[depth][breadth];

    modelCells[0] = this.generateHeaders(headerArray);

    for (int rowCounter = 1; rowCounter < depth; rowCounter++) {
      modelCells[rowCounter] = createModelRow(scale, toneCollection, breadth, rowCounter);
    }

    int columnWidths[] = this.generateColumnWidths(modelCells);

    String message = this.validate(modelCells, columnWidths);
    if (message != null) {
      throw new IllegalArgumentException(message);
    }

    ViewTable generatedTable = new ViewTable(modelCells, columnWidths);

    return generatedTable;
  }

  protected abstract String[] createHeaderArray();

  protected abstract ViewCell[] createModelRow(Scale scale, ToneCollection toneCollection,
      int breadth, int rowCounter);

  private ViewCell[] generateHeaders(String[] headerStrings) {
    ViewCell[] headerCells = new ViewCell[headerStrings.length];
    int counter = 0;

    for (String header : headerStrings) {
      headerCells[counter] = new ViewCell(header);
      counter++;
    }

    return headerCells;
  }
  

}
