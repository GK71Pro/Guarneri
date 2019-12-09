package com.gkaraffa.guarneri.view.analytic;

import com.gkaraffa.cremona.theoretical.TonalSpectrum;
import com.gkaraffa.cremona.theoretical.ToneCollection;
import com.gkaraffa.cremona.theoretical.scale.Scale;
import com.gkaraffa.guarneri.view.ViewCell;
import com.gkaraffa.guarneri.view.analytic.old.HorizontalAnalyticViewFactory;

public class ScalarAnalyticViewFactory extends HorizontalAnalyticViewFactory {

  @Override
  protected String[] createHeaderArray() {
    return new String[] {"Degree", "Tone", "Distance To Next"};
  }

  @Override
  protected ViewCell[] createModelRow(Scale scale, ToneCollection toneCollection, int breadth,
      int rowCounter) {
    int scalePosition = rowCounter - 1;
    ViewCell[] modelCells = new ViewCell[breadth];

    modelCells[0] = new ViewCell(Integer.toString(rowCounter));
    modelCells[1] = new ViewCell(toneCollection.getTone(scalePosition).toString());
    modelCells[2] =
        new ViewCell(getSteps(TonalSpectrum.measureDistance(toneCollection.getTone(scalePosition),
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
