package com.gkaraffa.guarneri.view.instrument;

import com.gkaraffa.cremona.common.Pitch;
import com.gkaraffa.cremona.theoretical.ToneCollection;
import com.gkaraffa.cremona.theoretical.chord.Chord;
import com.gkaraffa.cremona.theoretical.scale.Scale;
import com.gkaraffa.guarneri.instrument.InstrumentModel;
import com.gkaraffa.guarneri.view.ViewCell;
import com.gkaraffa.guarneri.view.ViewFactory;
import com.gkaraffa.guarneri.view.ViewTable;

public abstract class InstrumentViewFactory extends ViewFactory {
  @Override
  public ViewTable createModel() {
    InstrumentModel instrumentModel = createInstrumentModel();

    int length = instrumentModel.getMaxLength();
    int breadth = instrumentModel.getMaxWidth();
    
    ViewCell[][] modelCells = new ViewCell[length][breadth];

    for (int rowCounter = 0; rowCounter < length; rowCounter++) {
      modelCells[rowCounter] = createModelRow(instrumentModel.getRow(rowCounter));
    }
    
    int columnWidths[] = this.generateColumnWidths(modelCells);

    String message = this.validate(modelCells, columnWidths);
    if (message != null) {
      throw new IllegalArgumentException(message);
    }

    ViewTable generatedTable = new ViewTable(modelCells, columnWidths);

    return generatedTable;
  }

  @Override
  public ViewTable createModel(Scale scale) {
    InstrumentModel instrumentModel = createInstrumentModel();

    return null;
  }

  @Override
  public ViewTable createModel(Chord chord) {
    InstrumentModel instrumentModel = createInstrumentModel();

    return null;
  }
  
  protected abstract InstrumentModel createInstrumentModel();
  protected abstract ViewCell[] createModelRow(Pitch[] rowPitches);

}
