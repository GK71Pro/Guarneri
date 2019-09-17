package com.gkaraffa.guarneri.outputform;

public class OutputForm {
  private String viewString;
  private byte[] byteArray;

  public OutputForm(String viewString, byte[] byteArray) {
    this.viewString = viewString;
    this.byteArray = byteArray;
  }

  public byte[] getByteArray() {
    return this.byteArray;
  }

  @Override
  public String toString() {
    return this.viewString;
  }
}
