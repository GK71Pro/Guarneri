package com.gkaraffa.guarneri.view.analytic.old;

import com.gkaraffa.cremona.theoretical.ToneCollection;
import com.gkaraffa.cremona.theoretical.ToneGroupObject;
import com.gkaraffa.cremona.theoretical.scale.Scale;
import com.gkaraffa.guarneri.view.ViewCell;
import com.gkaraffa.guarneri.view.ViewQuery;
import com.gkaraffa.guarneri.view.ViewTable;
import com.gkaraffa.guarneri.view.old.ViewFactory;

public abstract class VerticalAnalyticViewFactory extends ViewFactory {

  public VerticalAnalyticViewFactory() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public ViewTable createView() {
    throw new UnsupportedOperationException(
        "Cannot create VerticalAnalyticViewFactory without parameters.");
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
    ViewCell[][] modelCells = populateCells(headerArray, toneCollection);
    int columnWidths[] = this.generateColumnWidths(modelCells);
    String message = this.validate(modelCells, columnWidths);

    if (message != null) {
      throw new IllegalArgumentException(message);
    }

    ViewTable generatedTable = new ViewTable(modelCells, columnWidths);
    
    return generatedTable;

    /*
    int depth = headerArray.length;
    int breadth = toneCollection.getSize();
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
    */
    // return null;
  }

  protected abstract String[] createHeaderArray();

  protected abstract ViewCell[][] populateCells(String headerArray[], ToneCollection toneCollection);

}
