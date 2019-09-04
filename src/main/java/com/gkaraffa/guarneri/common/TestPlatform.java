package com.gkaraffa.guarneri.common;

import com.gkaraffa.cremona.helper.Helper;
import com.gkaraffa.cremona.theoretical.scale.Scale;
import com.gkaraffa.guarneri.model.instrument.GuitarModel;
import com.gkaraffa.guarneri.model.instrument.GuitarModelFactory;
import com.gkaraffa.guarneri.model.instrument.InstrumentModelFactory;
import com.gkaraffa.guarneri.view.CSVInstrumentViewFactory;
import com.gkaraffa.guarneri.view.InstrumentViewFactory;
import com.gkaraffa.guarneri.view.TextInstrumentViewFactory;
import com.gkaraffa.guarneri.view.View;

public class TestPlatform {

  public TestPlatform() {}

  public static void main(String[] args) {
    Helper helper = Helper.getInstance();
    Scale scale = helper.getScale("C", "Ionian");
    InstrumentModelFactory iMF = new GuitarModelFactory();
    GuitarModel guitar = (GuitarModel) iMF.createInstrumentModel();
    InstrumentViewFactory iVF = new CSVInstrumentViewFactory();
    View view = iVF.renderFilteredInstrumentView(guitar, scale.getToneCollection());
    
    System.out.println(view.toString());
  }

}
