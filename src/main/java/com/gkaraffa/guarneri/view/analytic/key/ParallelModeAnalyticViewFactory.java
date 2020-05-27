package com.gkaraffa.guarneri.view.analytic.key;

import java.util.ArrayList;
import java.util.List;

import com.gkaraffa.cremona.helper.ScaleHelper;
import com.gkaraffa.cremona.theoretical.TonalSpectrum;
import com.gkaraffa.cremona.theoretical.Tone;
import com.gkaraffa.cremona.theoretical.chord.Chord;
import com.gkaraffa.cremona.theoretical.scale.DiatonicScale;
import com.gkaraffa.cremona.theoretical.scale.Scale;
import com.gkaraffa.guarneri.view.ViewCell;
import com.gkaraffa.guarneri.view.ViewTableBuilder;
import com.gkaraffa.guarneri.view.analytic.scale.RomanNumeral;

public class ParallelModeAnalyticViewFactory extends KeyAnalyticViewFactory {
  private ScaleHelper scaleHelper = null;
  private int scaleLimit = 7;

  public ParallelModeAnalyticViewFactory() {
    this.scaleHelper = ScaleHelper.getInstance();

  }

  @Override
  protected String[] applyHeaderArray() {
    return new String[] {"Ionian Degree", "Ionian Chord", "Dorian Degree", "Dorian Chord",
        "Phrygian Degree", "Phrygian Chord", "Lydian Degree", "Lydian Chord", "Mixolydian Degree",
        "Mixolydian Chord", "Aeolian Degree", "Aeolian Chord", "Locrian Degree", "Locrian Chord"};
  }

  @Override
  protected void buildHeader(ViewTableBuilder vtBuild, String[] headerArray) {
    int counter = 0;

    for (String selection : headerArray) {
      vtBuild.insertCell(0, counter, new ViewCell(selection));
      counter++;
    }
  }

  @Override
  protected void buildBody(ViewTableBuilder vtBuild, Tone key) {
    int rowLimit = 7;
    Scale baseScale = this.selectScale(key, 0);
    List<RomanNumeral> baseRomanNumerals = this.buildRomanNumerals(baseScale);

    this.buildBasePair(vtBuild, baseScale, baseRomanNumerals);
    
    for (int index = 0; index < rowLimit; index++) {
      this.buildRowPair(vtBuild, baseScale, baseRomanNumerals, key, index);
    }
  }



  private void buildBasePair(ViewTableBuilder vtBuild, Scale baseScale,
      List<RomanNumeral> baseRomanNumerals) {

    for (int index = 0; index < this.scaleLimit; index++) {
      vtBuild.insertCell(index + 1, 0, new ViewCell(baseRomanNumerals.get(index).getText()));
    }

    for (int index = 0; index < this.scaleLimit; index++) {
      vtBuild.insertCell(index + 1, 1,
          new ViewCell(baseRomanNumerals.get(index).getChord().getAbbrev()));
    }

  }

  private void buildRowPair(ViewTableBuilder vtBuild, Scale baseScale,
      List<RomanNumeral> baseRomanNumerals, Tone key, int modalOffset) {
    Scale currentScale = this.selectScale(key, modalOffset);
    List<RomanNumeral> currentRomanNumerals = this.buildRomanNumerals(currentScale);
    int tableOffset = (modalOffset * 2);
    
    for (int index = 0; index < this.scaleLimit; index++) {
      Chord baseChord = baseRomanNumerals.get(index).getChord();
      RomanNumeral currentRomanNumeral = currentRomanNumerals.get(index);
      Chord currentChord = currentRomanNumeral.getChord();
      String modifiedCurrentRoman = this.determineParallelRomanNumeralText(baseChord, currentChord, currentRomanNumeral);
      
      vtBuild.insertCell(index + 1, tableOffset, new ViewCell(modifiedCurrentRoman));
      vtBuild.insertCell(index + 1, tableOffset + 1, new ViewCell(currentRomanNumeral.getChord().getAbbrev()));

    }
  }

  private Scale selectScale(Tone key, int modalOffset) {

    switch (modalOffset) {
      case 0:
        return this.scaleHelper.getScale(key.toString(), "IONIAN");

      case 1:
        return this.scaleHelper.getScale(key.toString(), "DORIAN");

      case 2:
        return this.scaleHelper.getScale(key.toString(), "PHRYGIAN");

      case 3:
        return this.scaleHelper.getScale(key.toString(), "LYDIAN");

      case 4:
        return this.scaleHelper.getScale(key.toString(), "MIXOLYDIAN");

      case 5:
        return this.scaleHelper.getScale(key.toString(), "AEOLIAN");

      case 6:
        return this.scaleHelper.getScale(key.toString(), "LOCRIAN");

      default:
        return null;
    }
  }

  private List<RomanNumeral> buildRomanNumerals(Scale baseScale) {
    List<RomanNumeral> romanNumerals = new ArrayList<RomanNumeral>();

    for (int index = 0; index < this.scaleLimit; index++) {
      romanNumerals.add(RomanNumeral.createRomanNumeral((DiatonicScale) baseScale, index, 4));
    }

    return romanNumerals;
  }
  
  private String determineParallelRomanNumeralText(Chord primaryChord, Chord parallelChord, RomanNumeral parallelRomanNumeral) {
    Tone primaryTone = primaryChord.getTonic();
    Tone parallelTone = parallelChord.getTonic();
    
    if (primaryTone.equals(parallelTone)) {
      return parallelRomanNumeral.getText();
    }
    
    if (TonalSpectrum.traverseDistance(primaryTone, 1).equals(parallelTone)) {
      //sharp
      return "#" + parallelRomanNumeral.getText();
    }
    
    if (TonalSpectrum.traverseDistance(parallelTone, 1).equals(primaryTone)) {
      //flat
      return "b" + parallelRomanNumeral.getText();
    }
    
    throw new IllegalArgumentException("Failure in conversion");
  }
}
