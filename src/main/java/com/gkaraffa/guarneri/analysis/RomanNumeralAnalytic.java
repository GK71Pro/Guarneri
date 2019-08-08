package com.gkaraffa.guarneri.analysis;

import com.gkaraffa.cremona.theoretical.RomanNumeral;
import com.gkaraffa.cremona.theoretical.Tone;
import com.gkaraffa.cremona.theoretical.ToneCollection;
import com.gkaraffa.cremona.theoretical.chord.Chord;
import com.gkaraffa.cremona.theoretical.scale.DiatonicScale;

public class RomanNumeralAnalytic extends TabularAnalytic {

  private RomanNumeralAnalytic(HeaderCell[] headerRowCells, HeaderCell[] headerColumnCells,
      AnalyticCell[][] analyticCells) {
    this.headerRow = headerRowCells;
    this.analyticCells = analyticCells;
  }
  
  public static RomanNumeralAnalytic createRomanNumeralAnalytic(DiatonicScale scale) {
    int scaleSize = scale.getToneCollection().getSize();
    int maxOffset = scaleSize - 1;
    HeaderCell[] headerColumnCells = new HeaderCell[scaleSize];
    HeaderCell[] headerRowCells = new HeaderCell[] {new HeaderCell("Degree"),
        new HeaderCell("Chord"), new HeaderCell("Root Spelling")};
    AnalyticCell[][] analyticCells = new AnalyticCell[scaleSize][headerRowCells.length];

    for (int index = 0; index <= maxOffset; index++) {
      headerColumnCells[index] = new HeaderCell("");
      RomanNumeral romanNumeral = RomanNumeral.createRomanNumeral(scale, index, 4);
      
      analyticCells[index][0] = new AnalyticCell(romanNumeral.getText());
      analyticCells[index][1] = new AnalyticCell(romanNumeral.getChord().getText());
      analyticCells[index][2] = new AnalyticCell(getSpellingString(romanNumeral.getChord()));
    }

    return new RomanNumeralAnalytic(headerRowCells, headerColumnCells, analyticCells);
  }
  
  private static String getSpellingString(Chord chord) {
    StringBuilder sB = new StringBuilder();

    ToneCollection chordTones = chord.getToneCollection();
    for(Tone tone: chordTones) {
      sB.append(tone);
      sB.append("->");
    }
    
    int size = sB.length();
    sB.delete((size - 2), size);
    
    return sB.toString();
  }
}
