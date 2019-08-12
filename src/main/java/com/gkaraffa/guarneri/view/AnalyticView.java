package com.gkaraffa.guarneri.view;

public class AnalyticView {
  private String viewString;
  private byte[] byteArray;

  public AnalyticView(String viewString, byte[] byteArray) {
    this.viewString = viewString;
    this.byteArray = byteArray;
  }
  
  public byte[] getByteArray() {
    return this.byteArray;
  }
  
  public String toString() {
    return this.viewString;
  }
}
