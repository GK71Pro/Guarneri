package com.gkaraffa.guarneri.model.analytic;

import com.gkaraffa.cremona.theoretical.TonalSpectrum;
import com.gkaraffa.cremona.theoretical.ToneCollection;
import com.gkaraffa.cremona.theoretical.scale.Scale;
import com.gkaraffa.guarneri.model.ModelCell;

public class ScalarAnalyticModelFactory extends AnalyticModelFactory {

  @Override
  protected String[] createHeaderArray() {
    return new String[] {"Degree", "Tone", "Distance To Next"};
  }

  @Override
  protected ModelCell[] createModelRow(Scale scale, ToneCollection toneCollection, int breadth,
      int rowCounter) {
    int scalePosition = rowCounter - 1;
    ModelCell[] modelCells = new ModelCell[breadth];

    modelCells[0] = new ModelCell(Integer.toString(rowCounter));
    modelCells[1] = new ModelCell(toneCollection.getTone(scalePosition).toString());
    modelCells[2] =
        new ModelCell(getSteps(TonalSpectrum.measureDistance(toneCollection.getTone(scalePosition),
            toneCollection.getTone(scalePosition + 1))));

    return modelCells;
  }

  private String getSteps(int steps) {
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
