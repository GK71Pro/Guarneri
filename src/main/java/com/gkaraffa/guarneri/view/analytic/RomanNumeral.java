package com.gkaraffa.guarneri.view.analytic;

import com.gkaraffa.cremona.theoretical.Interval;
import com.gkaraffa.cremona.theoretical.chord.Chord;
import com.gkaraffa.cremona.theoretical.chord.ChordFactory;
import com.gkaraffa.cremona.theoretical.scale.DiatonicScale;

public class RomanNumeral {
  private Interval interval;
  private Chord chord;
  private String text;


  public RomanNumeral(Interval interval, Chord chord, String text) {
    this.interval = interval;
    this.chord = chord;
    this.text = text;
  }

  public Interval getInterval() {
    return interval;
  }

  public Chord getChord() {
    return chord;
  }

  public String getText() {
    return text;
  }
  
  @Override
  public String toString() {
    return text;
  }

  public static RomanNumeral createRomanNumeral(DiatonicScale diatonicScale, int position, int toneCount) {
    // get interval
    Interval interval = diatonicScale.getIntervalPattern().getIntervalByLocation(position);

    // get chordNomenclature
    // create chord
    ChordFactory chordFactory = new ChordFactory();
    Chord chord = chordFactory.createChordFromHarmonizableScale(diatonicScale,
        diatonicScale.getToneCollection().getTone(position), toneCount);

    String text = evaluate(interval, chord);

    return new RomanNumeral(interval, chord, text);

  }

  private static String evaluate(Interval interval, Chord chord)
      throws IllegalArgumentException {
    String raw = null;

    switch (interval.getIntervalNumber()) {
      case FIRST:
        raw = "I";
        break;
      case SECOND:
        raw = "II";
        break;
      case THIRD:
        raw = "III";
        break;
      case FOURTH:
        raw = "IV";
        break;
      case FIFTH:
        raw = "V";
        break;
      case SIXTH:
        raw = "VI";
        break;
      case SEVENTH:
        raw = "VII";
        break;
      default:
        throw new IllegalArgumentException("Inapplicable IntervalNumber");
    }

    switch (chord.getChordNomenclature()) {
      case MAJOR:
      case MAJOR_SEVENTH:
      case DOMINANT_SEVENTH:
        return raw;
      case MINOR:
      case MINOR_SEVENTH:
      case MINOR_MAJOR_SEVENTH:
        return raw.toLowerCase();
      case DIMINISHED:
      case HALF_DIMINISHED_SEVENTH:
      case DIMINISHED_SEVENTH:
        return raw.toLowerCase() + "°";
      case AUGMENTED:
      case AUGMENTED_MAJOR_SEVENTH:
        return raw + "+";
      default:
        throw new IllegalArgumentException("Inapplicable ChordNomenclature");
    }
  }

}
