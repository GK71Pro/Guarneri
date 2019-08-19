package com.gkaraffa.guarneri.view;

import com.gkaraffa.cremona.common.Pitch;
import com.gkaraffa.cremona.common.PitchCollection;
import com.gkaraffa.cremona.theoretical.ToneCollection;
import com.gkaraffa.guarneri.instrument.InstrumentModel;

public class CSVInstrumentViewFactory extends InstrumentViewFactory {

  public CSVInstrumentViewFactory() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public View renderInstrumentView(InstrumentModel instrumentModel) {
    int rowCount = this.determineRowCount(instrumentModel);
    int columnCount = this.determineColumnCount(instrumentModel);
    StringBuilder sB = new StringBuilder();

    for (int index = 0; index <= columnCount; index++) {
      Pitch[] currentRow = instrumentModel.getRow(index);
      for (int subindex = 0; subindex < rowCount; subindex++) {
        sB.append(formatCSVCell(currentRow[subindex]));
      }
      sB.setLength(sB.length() - 2);
      sB.append("\n");
    }

    String viewString = sB.toString();
    View outputView = new View(viewString, viewString.getBytes());

    return outputView;
  }

  @Override
  public View renderFilteredInstrumentView(InstrumentModel instrumentModel,
      ToneCollection toneCollection) {
    int rowCount = this.determineRowCount(instrumentModel);
    int columnCount = this.determineColumnCount(instrumentModel);
    StringBuilder sB = new StringBuilder();

    for (int index = 0; index <= columnCount; index++) {
      Pitch[] currentRow = instrumentModel.getFilteredRow(index, toneCollection);
      for (int subindex = 0; subindex < rowCount; subindex++) {
        sB.append(formatCSVCell(currentRow[subindex]));
      }
      sB.setLength(sB.length() - 2);
      sB.append("\n");
    }

    String viewString = sB.toString();
    View outputView = new View(viewString, viewString.getBytes());

    return outputView;
  }

  @Override
  public View renderFilteredInstrumentView(InstrumentModel instrumentModel,
      PitchCollection pitchCollection) {
    int rowCount = this.determineRowCount(instrumentModel);
    int columnCount = this.determineColumnCount(instrumentModel);
    StringBuilder sB = new StringBuilder();

    for (int index = 0; index <= columnCount; index++) {
      Pitch[] currentRow = instrumentModel.getFilteredRow(index, pitchCollection);
      for (int subindex = 0; subindex < rowCount; subindex++) {
        sB.append(formatCSVCell(currentRow[subindex]));
      }
      sB.setLength(sB.length() - 2);
      sB.append("\n");
    }

    String viewString = sB.toString();
    View outputView = new View(viewString, viewString.getBytes());

    return outputView;
  }

  private String formatCSVCell(Pitch pitch) {
    StringBuilder sB = new StringBuilder();

    if (pitch != null) {
      sB.append(pitch);
    }

    sB.append(", ");

    return sB.toString();
  }

}
