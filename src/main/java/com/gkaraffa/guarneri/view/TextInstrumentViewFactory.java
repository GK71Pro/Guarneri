package com.gkaraffa.guarneri.view;

import com.gkaraffa.cremona.common.Pitch;
import com.gkaraffa.cremona.common.PitchCollection;
import com.gkaraffa.cremona.theoretical.ToneCollection;
import com.gkaraffa.guarneri.model.instrument.InstrumentModel;

public class TextInstrumentViewFactory extends InstrumentViewFactory {
  private static final int cellSpacing = 10;

  public TextInstrumentViewFactory() {}

  @Override
  public View renderInstrumentView(InstrumentModel instrumentModel) {
    int rowCount = this.determineRowCount(instrumentModel);
    int columnCount = this.determineColumnCount(instrumentModel);
    String horizontalRule = this.createHorizontalRule(rowCount);
    StringBuilder sB = new StringBuilder();

    sB.append(horizontalRule);
    sB.append("\n");

    for (int index = 0; index <= columnCount; index++) {
      Pitch[] currentRow = instrumentModel.getRow(index);
      sB.append(this.formatTextRowNumber(index));
      for (int subindex = 0; subindex < rowCount; subindex++) {
        sB.append(formatTextCell(currentRow[subindex]));
      }
      sB.append("\n");
      sB.append(horizontalRule);
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
    String horizontalRule = this.createHorizontalRule(rowCount);
    StringBuilder sB = new StringBuilder();

    sB.append(horizontalRule);
    sB.append("\n");

    for (int index = 0; index <= columnCount; index++) {
      Pitch[] currentRow = instrumentModel.getFilteredRow(index, toneCollection);
      sB.append(this.formatTextRowNumber(index));
      for (int subindex = 0; subindex < rowCount; subindex++) {
        sB.append(formatTextCell(currentRow[subindex]));
      }
      sB.append("\n");
      sB.append(horizontalRule);
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
    String horizontalRule = this.createHorizontalRule(rowCount);
    StringBuilder sB = new StringBuilder();

    sB.append(horizontalRule);
    sB.append("\n");

    for (int index = 0; index <= columnCount; index++) {
      Pitch[] currentRow = instrumentModel.getFilteredRow(index, pitchCollection);
      sB.append(this.formatTextRowNumber(index));
      for (int subindex = 0; subindex < rowCount; subindex++) {
        sB.append(formatTextCell(currentRow[subindex]));
      }
      sB.append("\n");
      sB.append(horizontalRule);
      sB.append("\n");
    }

    String viewString = sB.toString();
    View outputView = new View(viewString, viewString.getBytes());
    
    return outputView;
  }

  private String createHorizontalRule(int rowCount) {
    StringBuilder sB = new StringBuilder();

    int calculatedSize = (rowCount * cellSpacing) + 1;

    for (int index = 0; index < calculatedSize; index++) {
      sB.append('-');
    }

    return sB.toString();
  }

  private String formatTextRowNumber(int rowNumber) {
    StringBuilder sB = new StringBuilder();

    sB.append("|");

    return sB.toString();
  }
  
  private String formatTextCell(Pitch pitch) {
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
}
