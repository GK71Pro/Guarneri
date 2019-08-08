package com.gkaraffa.guarneri.analysis;

import com.gkaraffa.cremona.theoretical.TonalSpectrum;
import com.gkaraffa.cremona.theoretical.ToneCollection;
import com.gkaraffa.cremona.theoretical.scale.Scale;

public class ScalarAnalytic extends TabularAnalytic {

  public ScalarAnalytic(HeaderCell[] headerRowCells, HeaderCell[] headerColumnCells,
      AnalyticCell[][] analyticCells) {
    this.headerRow = headerRowCells;
    this.analyticCells = analyticCells;
  }

  public static ScalarAnalytic createScalarAnalytic(Scale scale) {
    ToneCollection toneCollection = scale.getToneCollection();
    int scaleSize = toneCollection.getSize();
    int maxOffset = scaleSize - 1;
    HeaderCell[] headerColumnCells = new HeaderCell[scaleSize];
    HeaderCell[] headerRowCells = new HeaderCell[] {new HeaderCell("Degree"),
        new HeaderCell("Tone"), new HeaderCell("Distance To Next")};
    AnalyticCell[][] analyticCells = new AnalyticCell[scaleSize][headerRowCells.length];

    for (int index = 0; index <= maxOffset; index++) {
      analyticCells[index][0] = new AnalyticCell(Integer.toString(index + 1));
      analyticCells[index][1] = new AnalyticCell(toneCollection.getTone(index).toString());
      /*
      analyticCells[index][2] = new AnalyticCell(Integer.toString(TonalSpectrum
          .measureDistance(toneCollection.getTone(index), toneCollection.getTone(index + 1))));
       */
      analyticCells[index][2] = new AnalyticCell(getSteps(TonalSpectrum
          .measureDistance(toneCollection.getTone(index), toneCollection.getTone(index + 1))));
    }
    

    return new ScalarAnalytic(headerRowCells, headerColumnCells, analyticCells);
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
