package com.gkaraffa.guarneri.model.instrument;

public abstract class InstrumentModelFactory {

  public InstrumentModelFactory() {}

  public abstract InstrumentModel createInstrumentModel();

  public abstract InstrumentModel createInstrumentModel(InstrumentModelParameterObject iMPO);

}