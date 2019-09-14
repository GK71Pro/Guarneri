package com.gkaraffa.guarneri.model.instrument;

public abstract class OldInstrumentModelFactory {

  public OldInstrumentModelFactory() {}

  public abstract OldInstrumentModel createInstrumentModel();

  public abstract OldInstrumentModel createInstrumentModel(OldInstrumentModelParameterObject iMPO);

}