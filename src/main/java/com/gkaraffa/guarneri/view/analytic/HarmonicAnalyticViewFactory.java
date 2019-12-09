package com.gkaraffa.guarneri.view.analytic;

import com.gkaraffa.cremona.theoretical.ToneCollection;
import com.gkaraffa.guarneri.view.ViewCell;
import com.gkaraffa.guarneri.view.analytic.old.VerticalAnalyticViewFactory;

public class HarmonicAnalyticViewFactory extends VerticalAnalyticViewFactory {

  public HarmonicAnalyticViewFactory() {
    // TODO Auto-generated constructor stub
  }

  @Override
  protected String[] createHeaderArray() {
    String[] headerArray = new String[6];
    
    headerArray[0] = "Degree";
    headerArray[1] = "Tone";
    headerArray[2] = "Triad";
    headerArray[3] = "Seventh Chord";
    headerArray[4] = "Secondary Dominant";
    headerArray[5] = "Parallel Major/Minor";
    
    return headerArray;
  }

  @Override
  protected ViewCell[][] populateCells(String[] headerArray, ToneCollection toneCollection) {
    int depth = headerArray.length;
    int breadth = toneCollection.getSize();
    ViewCell[][] modelCells = new ViewCell[depth][breadth];

    // TODO Auto-generated method stub
    return null;
  }

}
