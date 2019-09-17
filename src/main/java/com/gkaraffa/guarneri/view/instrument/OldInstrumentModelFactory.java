package com.gkaraffa.guarneri.view.instrument;

public abstract class OldInstrumentModelFactory {

  public OldInstrumentModelFactory() {}

  public abstract OldInstrumentModel createInstrumentModel();

  public abstract OldInstrumentModel createInstrumentModel(OldInstrumentModelParameterObject iMPO);

}