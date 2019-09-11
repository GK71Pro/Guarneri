package com.gkaraffa.guarneri.model.analytic;

import com.gkaraffa.cremona.theoretical.RomanNumeral;
import com.gkaraffa.cremona.theoretical.Tone;
import com.gkaraffa.cremona.theoretical.ToneCollection;
import com.gkaraffa.cremona.theoretical.chord.Chord;
import com.gkaraffa.cremona.theoretical.scale.DiatonicScale;
import com.gkaraffa.cremona.theoretical.scale.Scale;
import com.gkaraffa.guarneri.model.ModelCell;

public class RomanNumeralAnalyticModelFactory extends AnalyticModelFactory {

  @Override
  protected String[] createHeaderArray() {
    return new String[] {"Degree", "Chord", "Root Spelling"};
  }

  @Override
  protected ModelCell[] createModelRow(Scale scale, ToneCollection toneCollection, int breadth,
      int rowCounter) {

    if (!(scale instanceof DiatonicScale)) {
      throw new IllegalArgumentException("Must be a diatonic scale");
    }

    int scalePosition = rowCounter - 1;
    ModelCell[] modelCells = new ModelCell[breadth];
    RomanNumeral romanNumeral =
        RomanNumeral.createRomanNumeral((DiatonicScale) scale, scalePosition, 4);

    modelCells[0] = new ModelCell(romanNumeral.getText());
    modelCells[1] = new ModelCell(romanNumeral.getChord().getText());
    modelCells[2] = new ModelCell(getSpellingString(romanNumeral.getChord()));

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
