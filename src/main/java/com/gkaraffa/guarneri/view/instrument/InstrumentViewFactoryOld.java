package com.gkaraffa.guarneri.view.instrument;

import com.gkaraffa.cremona.common.Pitch;
import com.gkaraffa.cremona.theoretical.ToneCollection;
import com.gkaraffa.cremona.theoretical.ToneGroupObject;
import com.gkaraffa.cremona.theoretical.chord.Chord;
import com.gkaraffa.cremona.theoretical.scale.Scale;
import com.gkaraffa.guarneri.instrument.InstrumentModel;
import com.gkaraffa.guarneri.view.ViewCell;
import com.gkaraffa.guarneri.view.ViewFactoryOld;
import com.gkaraffa.guarneri.view.ViewQuery;
import com.gkaraffa.guarneri.view.ViewTable;

public abstract class InstrumentViewFactoryOld extends ViewFactoryOld {
  @Override
  public ViewTable createView() {
    return this.generateViewTable(null);
  }

  @Override
  public ViewTable createView(ViewQuery viewQuery) {
    return this.generateViewTable(viewQuery);
  }

  private ViewTable generateViewTable(ViewQuery viewQuery) {
    boolean runByQuery = false;
    ToneCollection toneCollection = null;

    if (viewQuery != null) {
      runByQuery = true;
      toneCollection = this.convertViewQueryToToneCollection(viewQuery);
    }

    InstrumentModel instrumentModel = createInstrumentModel();

    int length = instrumentModel.getMaxLength();
    int breadth = instrumentModel.getMaxWidth();

    ViewCell[][] modelCells = new ViewCell[length][breadth];

    for (int rowCounter = 0; rowCounter < length; rowCounter++) {
      if (runByQuery) {
        modelCells[rowCounter] =
            createModelRow(instrumentModel.getFilteredRow(rowCounter, toneCollection));
      }
      else {
        modelCells[rowCounter] = createModelRow(instrumentModel.getRow(rowCounter));
      }
    }

    int columnWidths[] = this.generateColumnWidths(modelCells);

    String message = this.validate(modelCells, columnWidths);
    if (message != null) {
      throw new IllegalArgumentException(message);
    }

    ViewTable generatedTable = new ViewTable(modelCells, columnWidths);

    return generatedTable;
  }

  private ToneCollection convertViewQueryToToneCollection(ViewQuery viewQuery) {
    ToneGroupObject toneGroupObject = viewQuery.getToneGroupObject();

    if ((toneGroupObject instanceof Scale) || (toneGroupObject instanceof Chord)) {
      ToneCollection toneCollection = toneGroupObject.getToneCollection();
      return toneCollection;
    }
    else {
      throw new IllegalArgumentException("Unsupported Query");
    }
  }

  protected abstract InstrumentModel createInstrumentModel();

  protected abstract ViewCell[] createModelRow(Pitch[] rowPitches);

}
