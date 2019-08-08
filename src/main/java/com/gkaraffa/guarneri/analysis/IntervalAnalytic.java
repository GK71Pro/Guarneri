package com.gkaraffa.guarneri.analysis;

import com.gkaraffa.cremona.theoretical.Interval;
import com.gkaraffa.cremona.theoretical.IntervalNumber;
import com.gkaraffa.cremona.theoretical.scale.Scale;

public class IntervalAnalytic extends TabularAnalytic {

  private IntervalAnalytic(HeaderCell[] headerRowCells, HeaderCell[] headerColumnCells,
      AnalyticCell[][] analyticCells) {
    this.headerRow = headerRowCells;
    this.analyticCells = analyticCells;
  }

  public static IntervalAnalytic createIntervalAnalytic(Scale scale) {
    int scaleSize = scale.getToneCollection().getSize();
    int maxOffset = scaleSize - 1;
    HeaderCell[] headerColumnCells = new HeaderCell[scaleSize];
    HeaderCell[] headerRowCells = new HeaderCell[] {new HeaderCell("Abbrev"),
        new HeaderCell("Function"), new HeaderCell("Interval"), new HeaderCell("Tone")};
    AnalyticCell[][] analyticCells = new AnalyticCell[scaleSize][headerRowCells.length];
    
    for (int index = 0; index <= maxOffset; index++) {
      headerColumnCells[index] = new HeaderCell("");
      Interval interval = scale.getIntervalPattern().getIntervalByLocation(index);
      IntervalNumber intervalNumber = interval.getIntervalNumber();

      analyticCells[index][0] = new AnalyticCell(intervalNumber.getAbbrev());
      analyticCells[index][1] = new AnalyticCell(intervalNumber.getDiatonicFunction());
      analyticCells[index][2] = new AnalyticCell(interval.getText());
      analyticCells[index][3] =
          new AnalyticCell(scale.getToneCollection().getTone(index).getText());
    }

    return new IntervalAnalytic(headerRowCells, headerColumnCells, analyticCells);
  }
}
