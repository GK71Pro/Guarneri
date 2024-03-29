package com.gkaraffa.guarneri.instrument;

import java.util.ArrayList;

import com.gkaraffa.cremona.common.Pitch;
import com.gkaraffa.cremona.theoretical.TonalSpectrum;
import com.gkaraffa.cremona.theoretical.Tone;

public class GuitarModelFactory extends InstrumentModelFactory {

  public GuitarModelFactory() {
    super();
  }

  @Override
  public InstrumentModel createInstrumentModel() {
    int fretCount = 25;
    ArrayList<ArrayList<Pitch>> guitarStrings = new ArrayList<ArrayList<Pitch>>();
    
    guitarStrings.add(createStringNotes(new Pitch(Tone.E, 2), fretCount));
    guitarStrings.add(createStringNotes(new Pitch(Tone.A, 2), fretCount));
    guitarStrings.add(createStringNotes(new Pitch(Tone.D, 3), fretCount));
    guitarStrings.add(createStringNotes(new Pitch(Tone.G, 3), fretCount));
    guitarStrings.add(createStringNotes(new Pitch(Tone.B, 3), fretCount));
    guitarStrings.add(createStringNotes(new Pitch(Tone.E, 4), fretCount));

    return new GuitarModel(guitarStrings, fretCount);
  }

  @Override
  public InstrumentModel createInstrumentModel(InstrumentModelParameterObject iMPO) {
    int fretCount;
    ArrayList<Pitch> parmOpenStringPitches = null;

    if (iMPO instanceof GuitarModelParameterObject) {
      GuitarModelParameterObject gMPO = (GuitarModelParameterObject) iMPO;
      fretCount = gMPO.getNumberOfFrets();
      parmOpenStringPitches = gMPO.getOpenStringPitches();
    }
    else {
      throw new IllegalArgumentException();
    }

    // build
    ArrayList<ArrayList<Pitch>> guitarStrings = new ArrayList<ArrayList<Pitch>>();
    int stringCount = parmOpenStringPitches.size();
    for (int index = 0; index < stringCount; index++) {
      guitarStrings.add(createStringNotes(parmOpenStringPitches.get(index), fretCount));
    }
    
    return new GuitarModel(guitarStrings, fretCount);
  }

  private ArrayList<Pitch> createStringNotes(Pitch pitch, int numberFrets) {
    ArrayList<Pitch> stringPitches = new ArrayList<Pitch>();
    int index = 0;

    Pitch nextPitch = pitch;
    do {
      stringPitches.add(nextPitch);
      
      if (nextPitch.getTone() == Tone.B) {
        nextPitch = new Pitch(TonalSpectrum.traverseDistance(nextPitch.getTone(), 1), nextPitch.getRange() + 1);
      }
      else {
        nextPitch = new Pitch(TonalSpectrum.traverseDistance(nextPitch.getTone(), 1), nextPitch.getRange());        
      }
      
      index++;
    } while (index <= numberFrets);

    return stringPitches;
  }
}
