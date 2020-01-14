package com.gkaraffa.guarneri.view.analytic.scale;

import com.gkaraffa.cremona.theoretical.Tone;
import com.gkaraffa.cremona.theoretical.ToneCollection;
import com.gkaraffa.cremona.theoretical.chord.Chord;
import com.gkaraffa.cremona.theoretical.chord.ChordFactory;
import com.gkaraffa.cremona.theoretical.scale.DiatonicScale;
import com.gkaraffa.cremona.theoretical.scale.Scale;
import com.gkaraffa.guarneri.view.ViewCell;
import com.gkaraffa.guarneri.view.ViewTableBuilder;

public class ReharmonizationOptionsViewFactory extends VerticalScalarAnalyticViewFactory {


  @Override
  protected void buildColumnFromToneCollection(ViewTableBuilder vtBuild, Scale scale,
      int collectionPosition) {
    int xIndex = collectionPosition + 1;
    RomanNumeral romanNumeral =
        RomanNumeral.createRomanNumeral((DiatonicScale) scale, collectionPosition, 4);
    Chord primaryChord = romanNumeral.getChord();

    vtBuild.insertCell(xIndex, 0, new ViewCell(romanNumeral.getText()));
    vtBuild.insertCell(xIndex, 1, new ViewCell(primaryChord.getText()));

    if ((primaryChord.getChordNomenclature().getText().contains("Diminished"))
        || (collectionPosition == 0)) {
      vtBuild.insertCell(xIndex, 2, new ViewCell(""));
    }
    else {
      vtBuild.insertCell(xIndex, 2,
          new ViewCell(this.getSecondaryDominantChord(primaryChord).getText()));
    }

    Chord parallel = this.getParallelMajorMinor(primaryChord);
    if (parallel == null) {
      vtBuild.insertCell(xIndex,  3, new ViewCell(""));
    }
    else {
      vtBuild.insertCell(xIndex, 3, new ViewCell(parallel.getText()));
    }
  }

  @Override
  protected String[] applyHeaderArray() {
    return new String[] {"Degree", "Chord", "Secondary Dominant", "Parallel Major/Minor"};
  }

  /*
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
  */

  private Chord getSecondaryDominantChord(Chord primaryChord) {
    ChordFactory chordFactory = new ChordFactory();
    Tone secondaryTonic = primaryChord.getToneCollection().getTone(2);

    return chordFactory.createChordFromIntervalPattern(Chord.DOMINANT_SEVENTH_CHORD_PATTERN,
        secondaryTonic);
  }

  private Chord getParallelMajorMinor(Chord primaryChord) {
    ChordFactory chordFactory = new ChordFactory();

    if ((primaryChord.getChordNomenclature().getText().contains("Major"))
        || (primaryChord.getChordNomenclature().getText().contains("Dominant"))) {
      return chordFactory.createChordFromIntervalPattern(Chord.MINOR_SEVENTH_CHORD_PATTERN,
          primaryChord.getToneCollection().getTone(0));
    }
    
    if ((primaryChord.getChordNomenclature().getText().contains("Minor"))
        && (!primaryChord.getChordNomenclature().getText().contains("Major"))) {
      return chordFactory.createChordFromIntervalPattern(Chord.MAJOR_SEVENTH_CHORD_PATTERN,
          primaryChord.getToneCollection().getTone(0));
    }
    
    return null;
  }
}
