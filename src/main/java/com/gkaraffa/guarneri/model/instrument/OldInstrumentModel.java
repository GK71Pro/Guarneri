package com.gkaraffa.guarneri.model.instrument;

public abstract class OldInstrumentModel implements OldInstrumentRow, OldInstrumentColumn {
  protected int maxWidth;
  protected int maxLength;

  public OldInstrumentModel() {}

  public int getMaxWidth() {
    return maxWidth;
  }

  public int getMaxLength() {
    return maxLength;
  }
}
