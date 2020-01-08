package com.gkaraffa.guarneri.view.analytic.scale;

import com.gkaraffa.cremona.theoretical.Tone;
import com.gkaraffa.cremona.theoretical.ToneCollection;
import com.gkaraffa.cremona.theoretical.chord.Chord;
import com.gkaraffa.cremona.theoretical.scale.DiatonicScale;
import com.gkaraffa.cremona.theoretical.scale.Scale;
import com.gkaraffa.guarneri.view.ViewCell;
import com.gkaraffa.guarneri.view.ViewTableBuilder;

public class RomanNumeralAnalyticViewFactory extends HorizontalScalarAnalyticViewFactory {

  @Override
  protected String[] applyHeaderArray() {
    return new String[] {"Degree", "Chord", "Root Spelling"};
  }
  
  @Override
  protected void buildRowFromToneCollection(ViewTableBuilder vtBuild, Scale scale,
      int collectionPosition) {
    int yIndex = collectionPosition + 1;
    
    RomanNumeral romanNumeral = RomanNumeral.createRomanNumeral((DiatonicScale) scale, collectionPosition, 4);

    vtBuild.insertCell(0, yIndex, new ViewCell(romanNumeral.getText()));
    vtBuild.insertCell(1, yIndex, new ViewCell(romanNumeral.getChord().getText()));
    vtBuild.insertCell(2, yIndex, new ViewCell(getSpellingString(romanNumeral.getChord())));
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
