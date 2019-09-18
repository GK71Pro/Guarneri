package com.gkaraffa.guarneri.view.instrument;

import com.gkaraffa.guarneri.instrument.InstrumentModel;
import com.gkaraffa.guarneri.view.ViewFactory;

public abstract class InstrumentViewFactory extends ViewFactory{
  private InstrumentModel instrumentModel = null;
  
  public InstrumentViewFactory(InstrumentModel instrumentModel) {
    this.instrumentModel = instrumentModel;
  }
}
