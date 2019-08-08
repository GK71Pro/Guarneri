package com.gkaraffa.guarneri.analysis;

public class AnalyticCell {
  private String analyticText = null;
  
  public AnalyticCell() {}
  
  public AnalyticCell(String analyticText) {
    this.analyticText = analyticText;
  }
  
  public String getAnalyticText() {
    return this.analyticText;
  }
  
  @Override
  public String toString() {
    return analyticText;
  }
  
}
