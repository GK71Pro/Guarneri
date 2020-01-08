package com.gkaraffa.guarneri.view.instrument;

import com.gkaraffa.cremona.common.Pitch;
import com.gkaraffa.cremona.theoretical.ToneCollection;
import com.gkaraffa.cremona.theoretical.ToneGroupObject;
import com.gkaraffa.cremona.theoretical.chord.Chord;
import com.gkaraffa.cremona.theoretical.scale.Scale;
import com.gkaraffa.guarneri.instrument.InstrumentModel;
import com.gkaraffa.guarneri.view.ViewCell;
import com.gkaraffa.guarneri.view.ViewFactory;
import com.gkaraffa.guarneri.view.ViewQuery;
import com.gkaraffa.guarneri.view.ViewTable;
import com.gkaraffa.guarneri.view.ViewTableBuilder;

public abstract class InstrumentViewFactory implements ViewFactory {
  
  @Override
  public ViewTable createView() {
    return this.generateViewTable(null);
  }

  @Override
  public ViewTable createView(ViewQuery viewQuery) {
    ToneCollection toneCollection = this.verifyAndInterpretQuery(viewQuery);
    return this.generateViewTable(toneCollection);
  }

  private ToneCollection verifyAndInterpretQuery(ViewQuery viewQuery) {
    ToneGroupObject toneGroupObject = viewQuery.getToneGroupObject();
    ToneCollection toneCollection = null;

    if ((toneGroupObject instanceof Scale) || (toneGroupObject instanceof Chord)) {
      toneCollection = toneGroupObject.getToneCollection();
    }
    else {
      throw new IllegalArgumentException("Unsupported Query");
    }
    
    return toneCollection;
  }

  private ViewTable generateViewTable(ToneCollection toneCollection) {
    ViewTableBuilder vtBuild = new ViewTableBuilder();
    InstrumentModel instrumentModel = createInstrumentModel();
    int rowLength = instrumentModel.getMaxLength();
    
    for (int rowCounter = 0; rowCounter < rowLength; rowCounter++) {
      if (toneCollection != null) {
        this.insertRowPitches(vtBuild, rowCounter, instrumentModel.getFilteredRow(rowCounter, toneCollection));
      }
      else {
        this.insertRowPitches(vtBuild, rowCounter, instrumentModel.getRow(rowCounter));
      }
    }

    ViewTable generatedTable = vtBuild.compileTable();

    return generatedTable;
  }

  private void insertRowPitches(ViewTableBuilder vtBuild, int rowIndex, Pitch[] rowPitches) {
    int counter = 0;
    
    for (Pitch pitch: rowPitches) {
      ViewCell viewCell = null;
      
      if (pitch == null) {
        viewCell = new ViewCell("");
      }
      else {
        viewCell = new ViewCell(pitch.getText());
      }
      
      vtBuild.insertCell(counter, rowIndex, viewCell); 
      counter++;
    }
  }
  
  protected abstract InstrumentModel createInstrumentModel();
}
