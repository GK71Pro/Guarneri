package com.gkaraffa.guarneri.view.instrument;

import com.gkaraffa.cremona.common.Pitch;
import com.gkaraffa.guarneri.instrument.GuitarModelFactory;
import com.gkaraffa.guarneri.instrument.InstrumentModel;
import com.gkaraffa.guarneri.instrument.InstrumentModelFactory;
import com.gkaraffa.guarneri.view.ViewCell;

public class GuitarViewFactoryOld extends InstrumentViewFactoryOld {

  @Override
  protected InstrumentModel createInstrumentModel() {
    InstrumentModelFactory instrumentModelFactory = new GuitarModelFactory();
    InstrumentModel instrumentModel = instrumentModelFactory.createInstrumentModel();

    return instrumentModel;
  }

  @Override
  protected ViewCell[] createModelRow(Pitch[] rowPitches) {
    ViewCell[] modelRow = new ViewCell[rowPitches.length];
    int counter = 0;

    for (Pitch pitch : rowPitches) {
      if (pitch == null) {
        modelRow[counter] = new ViewCell("");
      }
      else {
        modelRow[counter] = new ViewCell(pitch.getText());
      }
      counter++;
    }

    return modelRow;
  }
}