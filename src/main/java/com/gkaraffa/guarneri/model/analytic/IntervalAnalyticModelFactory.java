package com.gkaraffa.guarneri.model.analytic;

import com.gkaraffa.cremona.theoretical.Interval;
import com.gkaraffa.cremona.theoretical.IntervalNumber;
import com.gkaraffa.cremona.theoretical.ToneCollection;
import com.gkaraffa.cremona.theoretical.scale.Scale;
import com.gkaraffa.guarneri.model.ModelCell;

public class IntervalAnalyticModelFactory extends AnalyticModelFactory {

  @Override
  protected String[] createHeaderArray() {
    return new String[] {"Abbrev", "Function", "Interval", "Tone"};
  }

  @Override
  protected ModelCell[] createModelRow(Scale scale, ToneCollection toneCollection, int breadth, int rowCounter) {
    int scalePosition = rowCounter - 1;
    Interval interval = scale.getIntervalPattern().getIntervalByLocation(scalePosition);
    IntervalNumber intervalNumber = interval.getIntervalNumber();
    ModelCell[] modelCells = new ModelCell[breadth];
    
    modelCells[0] = new ModelCell(intervalNumber.getAbbrev());
    modelCells[1] = new ModelCell(intervalNumber.getDiatonicFunction());
    modelCells[2] = new ModelCell(interval.getText());
    modelCells[3] = new ModelCell(scale.getToneCollection().getTone(scalePosition).getText());
    
    return modelCells;
  }
}
