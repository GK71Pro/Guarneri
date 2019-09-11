package com.gkaraffa.guarneri.model.analytic;

import com.gkaraffa.cremona.theoretical.ToneCollection;
import com.gkaraffa.cremona.theoretical.chord.Chord;
import com.gkaraffa.cremona.theoretical.scale.Scale;
import com.gkaraffa.guarneri.model.ModelCell;
import com.gkaraffa.guarneri.model.ModelFactory;
import com.gkaraffa.guarneri.model.ModelTable;

public abstract class AnalyticModelFactory extends ModelFactory {

  @Override
  public ModelTable createModel() {
    throw new UnsupportedOperationException(
        "Cannot create IntervalAnalyticModel without parameters.");
  }

  @Override
  public ModelTable createModel(Scale scale) {
    String headerArray[] = createHeaderArray();
    ToneCollection toneCollection = scale.getToneCollection();
    int depth = toneCollection.getSize() + 1;
    int breadth = headerArray.length;
    ModelCell[][] modelCells = new ModelCell[depth][breadth];

    modelCells[0] = this.generateHeaders(headerArray);
    
    for (int rowCounter = 1; rowCounter < depth; rowCounter++) {
      modelCells[rowCounter] = createModelRow();
      
    }
    
    int columnWidths[] = this.generateColumnWidths(modelCells);

    ModelTable generatedTable = new ModelTable(modelCells, columnWidths);

    return generatedTable;
  }

  @Override
  public ModelTable createModel(Chord chord) {
    throw new UnsupportedOperationException(
        "Cannot create IntervalAnalyticModel with chord as a parameter.");
  }
  
  protected abstract String[] createHeaderArray();
  protected abstract ModelCell[] createModelRow();

}
