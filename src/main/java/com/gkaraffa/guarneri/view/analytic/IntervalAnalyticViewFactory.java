package com.gkaraffa.guarneri.view.analytic;

import com.gkaraffa.cremona.theoretical.Interval;
import com.gkaraffa.cremona.theoretical.IntervalNumber;
import com.gkaraffa.cremona.theoretical.ToneCollection;
import com.gkaraffa.cremona.theoretical.scale.Scale;
import com.gkaraffa.guarneri.view.ViewCell;

public class IntervalAnalyticViewFactory extends AnalyticViewFactory {

  @Override
  protected String[] createHeaderArray() {
    return new String[] {"Abbrev", "Function", "Interval", "Tone"};
  }

  @Override
  protected ViewCell[] createModelRow(Scale scale, ToneCollection toneCollection, int breadth,
      int rowCounter) {
    int scalePosition = rowCounter - 1;
    Interval interval = scale.getIntervalPattern().getIntervalByLocation(scalePosition);
    IntervalNumber intervalNumber = interval.getIntervalNumber();
    ViewCell[] modelCells = new ViewCell[breadth];

    modelCells[0] = new ViewCell(intervalNumber.getAbbrev());
    modelCells[1] = new ViewCell(intervalNumber.getDiatonicFunction());
    modelCells[2] = new ViewCell(interval.getText());
    modelCells[3] = new ViewCell(scale.getToneCollection().getTone(scalePosition).getText());

    return modelCells;
  }
}
