package com.gkaraffa.guarneri.model.instrument;

import java.util.ArrayList;

import com.gkaraffa.cremona.common.Pitch;
import com.gkaraffa.cremona.common.PitchCollection;
import com.gkaraffa.cremona.theoretical.ToneCollection;

public class OldGuitarModel extends OldInstrumentModel implements OldInstrumentRow, OldInstrumentColumn {
  private ArrayList<ArrayList<Pitch>> strings = new ArrayList<ArrayList<Pitch>>();

  public OldGuitarModel(ArrayList<ArrayList<Pitch>> strings, int fretCount) {
    super();
    this.strings = strings;
    this.maxLength = fretCount;
    this.maxWidth = strings.size();
  }


  @Override
  public Pitch[] getRow(int row) {
    if (row > this.maxLength) {
      throw new IllegalArgumentException();
    }

    int numStrings = strings.size();
    Pitch[] targetPitchRow = new Pitch[numStrings];

    for (int index = 0; index < numStrings; index++) {
      targetPitchRow[index] = strings.get(index).get(row);
    }

    return targetPitchRow;
  }

  @Override
  public Pitch[] getFilteredRow(int row, ToneCollection toneFilter) {
    if (row > this.maxLength) {
      throw new IllegalArgumentException();
    }

    int numStrings = strings.size();
    Pitch[] targetPitchRow = new Pitch[numStrings];

    for (int index = 0; index < numStrings; index++) {
      Pitch pitch = strings.get(index).get(row);

      if (toneFilter.contains(pitch.getTone())) {
        targetPitchRow[index] = pitch;
      }
      else {
        targetPitchRow[index] = null;
      }
    }

    return targetPitchRow;
  }

  @Override
  public Pitch[] getFilteredRow(int row, PitchCollection pitchFilter) {
    if (row > this.maxLength) {
      throw new IllegalArgumentException();
    }

    int numStrings = strings.size();
    Pitch[] targetPitchRow = new Pitch[numStrings];

    for (int index = 0; index < numStrings; index++) {
      Pitch pitch = strings.get(index).get(row);

      if (pitchFilter.contains(pitch)) {
        targetPitchRow[index] = pitch;
      }
      else {
        targetPitchRow[index] = null;
      }
    }

    return targetPitchRow;
  }

  @Override
  public Pitch[] getColumn(int column) {
    if (column > (strings.size() - 1)) {
      throw new IllegalArgumentException();
    }
    
    Pitch[] targetPitchColumn = new Pitch[this.maxLength];
    targetPitchColumn = strings.get(column).toArray(targetPitchColumn);
    
    return targetPitchColumn;
  }

  @Override
  public Pitch[] getFilteredColumn(int column, ToneCollection toneFilter) {
    if (column > (strings.size() - 1)) {
      throw new IllegalArgumentException();
    }
    
    Pitch[] targetPitchColumn = new Pitch[this.maxLength];
    ArrayList<Pitch> tempPtr = strings.get(column);
    
    for (int index = 0; index < this.maxLength; index++) {
      Pitch currentPitch = tempPtr.get(index);
      
      if (toneFilter.contains(currentPitch.getTone())){
        targetPitchColumn[index] = currentPitch;
      }
      else {
        targetPitchColumn[index] = null;
      }
    }

    return targetPitchColumn;
  }

  @Override
  public Pitch[] getFilteredColumn(int column, PitchCollection pitchFilter) {
    if (column > (strings.size() - 1)) {
      throw new IllegalArgumentException();
    }
    
    Pitch[] targetPitchColumn = new Pitch[this.maxLength];
    ArrayList<Pitch> tempPtr = strings.get(column);
    
    for (int index = 0; index < this.maxLength; index++) {
      Pitch currentPitch = tempPtr.get(index);
      
      if (pitchFilter.contains(currentPitch)){
        targetPitchColumn[index] = currentPitch;
      }
      else {
        targetPitchColumn[index] = null;
      }
    }

    return targetPitchColumn;
  }

}
