package com.gkaraffa.guarneri.instrument;

public abstract class InstrumentModelFactory {

  public InstrumentModelFactory() {}

  public abstract InstrumentModel createInstrumentModel();

  public abstract InstrumentModel createInstrumentModel(InstrumentModelParameterObject iMPO);

}