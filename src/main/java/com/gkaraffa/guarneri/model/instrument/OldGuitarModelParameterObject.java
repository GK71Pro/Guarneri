package com.gkaraffa.guarneri.model.instrument;

import java.util.ArrayList;

import com.gkaraffa.cremona.common.Pitch;

public class OldGuitarModelParameterObject extends OldInstrumentModelParameterObject {
  private int numberOfFrets;
  private ArrayList<Pitch> openStringPitches = new ArrayList<Pitch>();

  public OldGuitarModelParameterObject() {
    super();
  }
    
  public int getNumberOfFrets() {
    return numberOfFrets;
  }

  public void setNumberOfFrets(int numberOfFrets) {
    this.numberOfFrets = numberOfFrets;
  }

  public ArrayList<Pitch> getOpenStringPitches() {
    return openStringPitches;
  }

  public void setOpenStringPitches(ArrayList<Pitch> openStringPitches) {
    this.openStringPitches = openStringPitches;
  }
}