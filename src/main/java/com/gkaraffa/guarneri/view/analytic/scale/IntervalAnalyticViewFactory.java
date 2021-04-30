package com.gkaraffa.guarneri.view.analytic.scale;

import com.gkaraffa.cremona.theoretical.Interval;
import com.gkaraffa.cremona.theoretical.IntervalNumber;
import com.gkaraffa.cremona.theoretical.scale.Scale;
import com.gkaraffa.guarneri.view.ViewCell;
import com.gkaraffa.guarneri.view.ViewTableBuilder;

public class IntervalAnalyticViewFactory extends HorizontalScalarAnalyticViewFactory {

  @Override
  protected void buildRowFromToneCollection(ViewTableBuilder vtBuild, Scale scale,
      int collectionPosition) {
    int yIndex = collectionPosition + 1;
    Interval interval = scale.getIntervalPattern().getIntervalByLocation(collectionPosition);
    IntervalNumber intervalNumber = interval.getIntervalNumber();

    vtBuild.insertCell(0, yIndex, new ViewCell(intervalNumber.getAbbrev()));
    vtBuild.insertCell(1, yIndex, new ViewCell(intervalNumber.getDiatonicFunction()));
    vtBuild.insertCell(2, yIndex, new ViewCell(intervalNumber.getText()));
    vtBuild.insertCell(3, yIndex, new ViewCell(scale.getToneCollection().getTone(collectionPosition).getText()));
  }

  @Override
  protected String[] applyHeaderArray() {
    return new String[] {"Abbrev", "Function", "Interval", "Tone"};
  }

  /*
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
  */
}
