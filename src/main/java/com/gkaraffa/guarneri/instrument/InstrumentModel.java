package com.gkaraffa.guarneri.instrument;

public abstract class InstrumentModel implements InstrumentRow, InstrumentColumn {
  protected int maxWidth;
  protected int maxLength;

  public InstrumentModel() {}

  public int getMaxWidth() {
    return maxWidth;
  }

  public int getMaxLength() {
    return maxLength;
  }
}
