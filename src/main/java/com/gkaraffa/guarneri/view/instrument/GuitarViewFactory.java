package com.gkaraffa.guarneri.view.instrument;

import com.gkaraffa.guarneri.instrument.GuitarModelFactory;
import com.gkaraffa.guarneri.instrument.InstrumentModel;
import com.gkaraffa.guarneri.instrument.InstrumentModelFactory;

public class GuitarViewFactory extends InstrumentViewFactory {

  @Override
  protected InstrumentModel createInstrumentModel() {
    InstrumentModelFactory instrumentModelFactory = new GuitarModelFactory();
    InstrumentModel instrumentModel = instrumentModelFactory.createInstrumentModel();

    return instrumentModel;
  }

}
