package com.gkaraffa.guarneri.old;

import com.gkaraffa.cremona.theoretical.Interval;
import com.gkaraffa.cremona.theoretical.IntervalNumber;
import com.gkaraffa.cremona.theoretical.TonalSpectrum;
import com.gkaraffa.cremona.theoretical.Tone;
import com.gkaraffa.cremona.theoretical.chord.Chord;
import com.gkaraffa.cremona.theoretical.chord.ChordFactory;
import com.gkaraffa.cremona.theoretical.scale.DiatonicScale;
import com.gkaraffa.cremona.theoretical.scale.DiatonicScaleFactory;
import com.gkaraffa.cremona.theoretical.scale.Scale;
import com.gkaraffa.cremona.theoretical.scale.ScaleFactory;
import com.gkaraffa.guarneri.view.ViewCell;
import com.gkaraffa.guarneri.view.ViewQuery;
import com.gkaraffa.guarneri.view.ViewTable;
import com.gkaraffa.guarneri.view.ViewTableBuilder;
import com.gkaraffa.guarneri.view.analytic.scale.RomanNumeral;
import com.gkaraffa.guarneri.view.analytic.scale.VerticalScalarAnalyticViewFactory;

public class ReharmonizationOptionsAnalyticViewFactory extends VerticalScalarAnalyticViewFactory {

  @Override
  public ViewTable createView(ViewQuery viewQuery) {
    ViewTableBuilder vtBuild = new ViewTableBuilder();
    Scale queryScale = null;
    Scale parallelScale = null;

    queryScale = this.verifyAndInterpretQuery(viewQuery);
    parallelScale = this.createParallelScale(queryScale);

    this.buildHeader(vtBuild, applyHeaderArray());
    this.buildBody(vtBuild, queryScale, parallelScale);

    return vtBuild.compileTable();
  }

  protected void buildBody(ViewTableBuilder vtBuild, Scale primaryScale, Scale parallelScale) {
    int collectionSize = primaryScale.getToneCollection().getSize();

    for (int collectionPosition = 0; collectionPosition < collectionSize; collectionPosition++) {
      buildColumnFromToneCollection(vtBuild, primaryScale, parallelScale, collectionPosition);
    }
  }

  protected void buildColumnFromToneCollection(ViewTableBuilder vtBuild, Scale primaryScale,
      Scale parallelScale, int collectionPosition) {
    int xIndex = collectionPosition + 1;

    RomanNumeral primaryRomanNumeral =
        RomanNumeral.createRomanNumeral((DiatonicScale) primaryScale, collectionPosition, 4);
    RomanNumeral parallelRomanNumeral =
        RomanNumeral.createRomanNumeral((DiatonicScale) parallelScale, collectionPosition, 4);

    Chord primaryChord = primaryRomanNumeral.getChord();
    Chord parallelChord = parallelRomanNumeral.getChord();

    vtBuild.insertCell(xIndex, 0, new ViewCell(primaryRomanNumeral.getText()));
    vtBuild.insertCell(xIndex, 1, new ViewCell(primaryChord.getAbbrev()));

    if ((primaryChord.getIntervalPattern()
        .getIntervalByIntervalNumber(IntervalNumber.FIFTH) == Interval.DIMINISHED_FIFTH)
        || (collectionPosition == 0)) {
      vtBuild.insertCell(xIndex, 2, new ViewCell(""));
    }
    else {
      vtBuild.insertCell(xIndex, 2,
          new ViewCell(this.getSecondaryDominantChord(primaryChord).getAbbrev()));
    }

    vtBuild.insertCell(xIndex, 3, new ViewCell(
        determineParallelRomanNumeralText(primaryChord, parallelChord, parallelRomanNumeral)));
    vtBuild.insertCell(xIndex, 4, new ViewCell(parallelChord.getAbbrev()));
  }

  @Override
  protected String[] applyHeaderArray() {
    return new String[] {"Primary Degree", "Primary Chord", "Secondary Dominant", "Parallel Degree",
        "Parallel Chord"};
  }

  @Override
  protected Scale verifyAndInterpretQuery(ViewQuery viewQuery) {
    Scale scale = super.verifyAndInterpretQuery(viewQuery);

    if (scale instanceof DiatonicScale) {
      return scale;
    }
    else {
      throw new IllegalArgumentException("ScalarAnalytics require ViewQuery containing a Scale");
    }
  }

  private Scale createParallelScale(Scale primaryScale) {
    ScaleFactory scaleFactory = new DiatonicScaleFactory();
    Scale parallelScale =
        scaleFactory.createScale(DiatonicScale.AEOLIAN_PATTERN, primaryScale.getKey());

    return parallelScale;
  }

  private Chord getSecondaryDominantChord(Chord primaryChord) {
    ChordFactory chordFactory = new ChordFactory();
    Tone secondaryTonic = primaryChord.getToneCollection().getTone(2);

    return chordFactory.createChordFromIntervalPattern(Chord.DOMINANT_SEVENTH_CHORD_PATTERN,
        secondaryTonic);
  }

  private String determineParallelRomanNumeralText(Chord primaryChord, Chord parallelChord,
      RomanNumeral parallelRomanNumeral) {
    Tone primaryTone = primaryChord.getTonic();
    Tone parallelTone = parallelChord.getTonic();

    if (primaryTone.equals(parallelTone)) {
      return parallelRomanNumeral.getText();
    }

    if (TonalSpectrum.traverseDistance(primaryTone, 1).equals(parallelTone)) {
      // sharp
      return "#" + parallelRomanNumeral.getText();
    }

    if (TonalSpectrum.traverseDistance(parallelTone, 1).equals(primaryTone)) {
      // flat
      return "b" + parallelRomanNumeral.getText();
    }

    throw new IllegalArgumentException("Failure in conversion");
  }

  @Override
  protected void buildColumnFromToneCollection(ViewTableBuilder vtBuild, Scale scale,
      int collectionPosition) {
    throw new UnsupportedOperationException("not supported");

  }
}
