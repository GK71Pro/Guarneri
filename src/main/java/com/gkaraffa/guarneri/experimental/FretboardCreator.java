package com.gkaraffa.guarneri.experimental;

import com.gkaraffa.cremona.common.Pitch;
import com.gkaraffa.cremona.common.PitchCollection;
import com.gkaraffa.cremona.theoretical.ToneCollection;
import com.gkaraffa.guarneri.model.instrument.OldGuitarModel;

public class FretboardCreator {
  private static final String fret =
      "---------------------------------------------------------------\n";

  public FretboardCreator() {}

  public static String createFretboardText(OldGuitarModel guitarModel) {
    StringBuilder sB = new StringBuilder();
    int numStrings = guitarModel.getMaxWidth();
    int numFrets = guitarModel.getMaxLength();

    sB.append(FretboardCreator.fret);

    for (int index = 0; index <= numFrets; index++) {
      Pitch[] currentFret = guitarModel.getRow(index);

      sB.append(formatTextFretNumber(Integer.toString(index)));

      for (int subindex = 0; subindex < numStrings; subindex++) {
        sB.append(formatTextCell(currentFret[subindex]));
      }

      sB.append("\n" + FretboardCreator.fret);
    }

    return (sB.toString());
  }

  public static String createFretboardCSV(OldGuitarModel guitarModel) {
    StringBuilder sB = new StringBuilder();
    int numStrings = guitarModel.getMaxWidth();
    int numFrets = guitarModel.getMaxLength();

    for (int index = 0; index <= numFrets; index++) {
      Pitch[] currentFret = guitarModel.getRow(index);

      sB.append(formatCSVFretNumber(Integer.toString(index)));

      for (int subindex = 0; subindex < numStrings; subindex++) {
        sB.append(formatCSVCell(currentFret[subindex]));
      }

      sB.deleteCharAt(sB.length() - 1);
      sB.append("\n");
    }

    return (sB.toString());
  }

  public static String createFormattedFretboardText(OldGuitarModel guitarModel,
      ToneCollection toneCollection) {
    StringBuilder sB = new StringBuilder();
    int numStrings = guitarModel.getMaxWidth();
    int numFrets = guitarModel.getMaxLength();

    sB.append(FretboardCreator.fret);

    for (int index = 0; index <= numFrets; index++) {
      Pitch[] currentFret = guitarModel.getFilteredRow(index, toneCollection);

      sB.append(formatTextFretNumber(Integer.toString(index)));

      for (int subindex = 0; subindex < numStrings; subindex++) {
        sB.append(formatTextCell(currentFret[subindex]));
      }

      sB.append("\n" + FretboardCreator.fret);
    }

    return (sB.toString());
  }

  public static String createFormattedFretboardCSV(OldGuitarModel guitarModel,
      ToneCollection toneCollection) {
    StringBuilder sB = new StringBuilder();
    int numStrings = guitarModel.getMaxWidth();
    int numFrets = guitarModel.getMaxLength();

    for (int index = 0; index <= numFrets; index++) {
      Pitch[] currentFret = guitarModel.getFilteredRow(index, toneCollection);

      sB.append(formatCSVFretNumber(Integer.toString(index)));

      for (int subindex = 0; subindex < numStrings; subindex++) {
        sB.append(formatCSVCell(currentFret[subindex]));
      }

      sB.deleteCharAt(sB.length() - 1);
      sB.append("\n");
    }

    return (sB.toString());
  }

  public static String createFormattedFretboardText(OldGuitarModel guitarModel,
      PitchCollection pitchCollection) {
    StringBuilder sB = new StringBuilder();
    int numStrings = guitarModel.getMaxWidth();
    int numFrets = guitarModel.getMaxLength();

    sB.append(FretboardCreator.fret);

    for (int index = 0; index <= numFrets; index++) {
      Pitch[] currentFret = guitarModel.getFilteredRow(index, pitchCollection);

      sB.append(formatTextFretNumber(Integer.toString(index)));

      for (int subindex = 0; subindex < numStrings; subindex++) {
        sB.append(formatTextCell(currentFret[subindex]));
      }

      sB.append("\n" + FretboardCreator.fret);
    }

    return (sB.toString());
  }

  public static String createFormattedFretboardCSV(OldGuitarModel guitarModel,
      PitchCollection pitchCollection) {
    StringBuilder sB = new StringBuilder();
    int numStrings = guitarModel.getMaxWidth();
    int numFrets = guitarModel.getMaxLength();

    for (int index = 0; index <= numFrets; index++) {
      Pitch[] currentFret = guitarModel.getFilteredRow(index, pitchCollection);

      sB.append(formatCSVFretNumber(Integer.toString(index)));

      for (int subindex = 0; subindex < numStrings; subindex++) {
        sB.append(formatCSVCell(currentFret[subindex]));
      }

      sB.deleteCharAt(sB.length() - 1);
      sB.append("\n");
    }

    return (sB.toString());
  }


  private static String formatTextFretNumber(String fretNumber) {
    StringBuilder sB = new StringBuilder();

    if (fretNumber.length() == 1) {
      sB.insert(0, " ");
    }

    sB.append(fretNumber);
    sB.append("|");

    return sB.toString();
  }

  private static String formatCSVFretNumber(String fretNumber) {
    StringBuilder sB = new StringBuilder();

    sB.append(fretNumber);
    sB.append(",");

    return sB.toString();
  }

  private static String formatTextCell(Pitch pitch) {
    StringBuilder sB = new StringBuilder();

    if (pitch == null) {
      sB.append("   ");
    }
    else {
      sB.append(pitch.getText());
    }

    if (sB.length() == 3) {
      sB.insert(0, "   ");
      sB.append("   ");
    }
    else {
      sB.insert(0, " ");
      sB.append(" ");
    }

    sB.append("|");

    return sB.toString();
  }


  private static String formatCSVCell(Pitch pitch) {
    StringBuilder sB = new StringBuilder();

    if (pitch == null) {
      sB.append(" ");
    }
    else {
      sB.append(pitch.getText());
    }

    sB.append(",");

    return sB.toString();
  }
}
