package com.gkaraffa.guarneri.view.analytic;

import com.gkaraffa.cremona.theoretical.RomanNumeral;
import com.gkaraffa.cremona.theoretical.Tone;
import com.gkaraffa.cremona.theoretical.ToneCollection;
import com.gkaraffa.cremona.theoretical.chord.Chord;
import com.gkaraffa.cremona.theoretical.scale.DiatonicScale;
import com.gkaraffa.cremona.theoretical.scale.Scale;
import com.gkaraffa.guarneri.view.ViewCell;

public class RomanNumeralAnalyticViewFactory extends AnalyticViewFactory {

  @Override
  protected String[] createHeaderArray() {
    return new String[] {"Degree", "Chord", "Root Spelling"};
  }

  @Override
  protected ViewCell[] createModelRow(Scale scale, ToneCollection toneCollection, int breadth,
      int rowCounter) {

    if (!(scale instanceof DiatonicScale)) {
      throw new IllegalArgumentException("Must be a diatonic scale");
    }

    int scalePosition = rowCounter - 1;
    ViewCell[] modelCells = new ViewCell[breadth];
    RomanNumeral romanNumeral =
        RomanNumeral.createRomanNumeral((DiatonicScale) scale, scalePosition, 4);

    modelCells[0] = new ViewCell(romanNumeral.getText());
    modelCells[1] = new ViewCell(romanNumeral.getChord().getText());
    modelCells[2] = new ViewCell(getSpellingString(romanNumeral.getChord()));

    return modelCells;
  }

  private String getSpellingString(Chord chord) {
    StringBuilder sB = new StringBuilder();

    ToneCollection chordTones = chord.getToneCollection();
    for (Tone tone : chordTones) {
      sB.append(tone);
      sB.append("->");
    }

    int size = sB.length();
    sB.delete((size - 2), size);

    return sB.toString();
  }
}
