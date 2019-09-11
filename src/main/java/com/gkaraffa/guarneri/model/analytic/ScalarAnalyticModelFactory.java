package com.gkaraffa.guarneri.model.analytic;

import com.gkaraffa.cremona.theoretical.TonalSpectrum;
import com.gkaraffa.cremona.theoretical.ToneCollection;
import com.gkaraffa.cremona.theoretical.chord.Chord;
import com.gkaraffa.cremona.theoretical.scale.Scale;
import com.gkaraffa.guarneri.model.ModelCell;
import com.gkaraffa.guarneri.model.ModelFactory;
import com.gkaraffa.guarneri.model.ModelTable;

public class ScalarAnalyticModelFactory extends ModelFactory {

  @Override
  public ModelTable createModel() {
    throw new UnsupportedOperationException(
        "Cannot create IntervalAnalyticModel without parameters.");
  }

  @Override
  public ModelTable createModel(Scale scale) {
    String headerArray[] = new String[] {"Degree", "Tone", "Distance To Next"};
    ToneCollection toneCollection = scale.getToneCollection();
    int depth = toneCollection.getSize() + 1;
    int breadth = headerArray.length;
    ModelCell[][] modelCells = new ModelCell[depth][breadth];

    modelCells[0] = this.generateHeaders(headerArray);

    for (int rowCounter = 1; rowCounter < depth; rowCounter++) {
      int scalePosition = rowCounter - 1;

      modelCells[rowCounter][0] = new ModelCell(Integer.toString(rowCounter));
      modelCells[rowCounter][1] = new ModelCell(toneCollection.getTone(scalePosition).toString());
      modelCells[rowCounter][2] = new ModelCell(getSteps(TonalSpectrum.measureDistance(
          toneCollection.getTone(scalePosition), toneCollection.getTone(scalePosition + 1))));
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

  private static String getSteps(int steps) {
    StringBuilder sB = new StringBuilder();
    int wholeSteps = steps / 2;
    int halfSteps = steps % 2;

    if (halfSteps > 0) {
      sB.append("H");
    }

    for (int index = 0; index < wholeSteps; index++) {
      sB.append("W");
    }

    return sB.toString();
  }
}
