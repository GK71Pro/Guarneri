package com.gkaraffa.guarneri.view.analytic.key;

import com.gkaraffa.cremona.theoretical.Tone;
import com.gkaraffa.guarneri.view.ViewTableBuilder;

public class ParallelModeAnalyticViewFactory extends VerticalKeyAnalyticViewFactory {

  public ParallelModeAnalyticViewFactory() {
    // TODO Auto-generated constructor stub
  }

  @Override
  protected void buildRow(ViewTableBuilder vtBuild, Tone key, int index) {
    // TODO Auto-generated method stub

  }

  @Override
  protected String[] applyHeaderArray() {
    return new String[] {"Ionian Degree", "Ionian Chord", "Dorian Degree", "Dorian Chord",
        "Phrygian Degree", "Phrygian Chord", "Lydian Degree", "Lydian Chord", "Mixolydian Degree",
        "Mixolydian Chord", "Aeolian Degree", "Aeolian Chord", "Locrian Degree", "Locrian Chord"};
  }
}
