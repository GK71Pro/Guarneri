package com.gkaraffa.guarneri.instrument;

import java.util.ArrayList;

import com.gkaraffa.cremona.common.Pitch;
import com.gkaraffa.cremona.common.PitchCollection;
import com.gkaraffa.cremona.theoretical.ToneCollection;

public class GuitarModel extends InstrumentModel implements InstrumentRow, InstrumentColumn {
  private ArrayList<ArrayList<Pitch>> strings = new ArrayList<ArrayList<Pitch>>();
  private int fretCount;
  private int stringCount;

  public GuitarModel(ArrayList<ArrayList<Pitch>> strings, int fretCount) {
    super();
    this.strings = strings;
    this.stringCount = strings.size();
    this.fretCount = fretCount;
  }

  public int getFretCount() {
    return fretCount;
  }

  public int getStringCount() {
    return stringCount;
  }

  public Pitch[] getRow(int row) {
    if (row > fretCount) {
      throw new IllegalArgumentException();
    }

    int numStrings = strings.size();
    Pitch[] targetPitchRow = new Pitch[numStrings];

    for (int index = 0; index < numStrings; index++) {
      targetPitchRow[index] = strings.get(index).get(row);
    }

    return targetPitchRow;
  }

  public Pitch[] getFilteredRow(int row, ToneCollection toneFilter) {
    if (row > fretCount) {
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

  public Pitch[] getFilteredRow(int row, PitchCollection pitchFilter) {
    if (row > fretCount) {
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

  public Pitch[] getColumn(int column) {
    if (column > (strings.size() - 1)) {
      throw new IllegalArgumentException();
    }
    
    Pitch[] targetPitchColumn = new Pitch[fretCount];
    targetPitchColumn = strings.get(column).toArray(targetPitchColumn);
    
    return targetPitchColumn;
  }

  public Pitch[] getFilteredColumn(int column, ToneCollection toneFilter) {
    if (column > (strings.size() - 1)) {
      throw new IllegalArgumentException();
    }
    
    Pitch[] targetPitchColumn = new Pitch[fretCount];
    ArrayList<Pitch> tempPtr = strings.get(column);
    
    for (int index = 0; index < fretCount; index++) {
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

  public Pitch[] getFilteredColumn(int column, PitchCollection pitchFilter) {
    if (column > (strings.size() - 1)) {
      throw new IllegalArgumentException();
    }
    
    Pitch[] targetPitchColumn = new Pitch[fretCount];
    ArrayList<Pitch> tempPtr = strings.get(column);
    
    for (int index = 0; index < fretCount; index++) {
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
