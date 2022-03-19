package com.gkaraffa.guarneri.view.analytic.scale;

import com.gkaraffa.cremona.theoretical.TonalSpectrum;
import com.gkaraffa.cremona.theoretical.ToneCollection;
import com.gkaraffa.cremona.theoretical.scale.Scale;
import com.gkaraffa.guarneri.view.ViewCell;
import com.gkaraffa.guarneri.view.ViewTableBuilder;

public class StepPatternAnalyticFactory extends HorizontalScalarAnalyticViewFactory {

  @Override
  protected String[] applyHeaderArray() {
    return new String[] {"Degree", "Tone", "Distance To Next"};
  }
  
  @Override
  protected void buildRowFromToneCollection(ViewTableBuilder vtBuild, Scale scale,
      int collectionPosition) {
    int yIndex = collectionPosition + 1;
    ToneCollection toneCollection = scale.getToneCollection();

    vtBuild.insertCell(0, yIndex, new ViewCell(Integer.toString(yIndex)));
    vtBuild.insertCell(1, yIndex,
        new ViewCell(toneCollection.getTone(collectionPosition).toString()));
    vtBuild.insertCell(2, yIndex,
        new ViewCell(
            getSteps(TonalSpectrum.measureDistance(toneCollection.getTone(collectionPosition),
                toneCollection.getTone(collectionPosition + 1)))));
    
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
