package com.gkaraffa.guarneri.analysis;

public class HeaderCell {
  private String headerText = null;

  public HeaderCell() {}

  public HeaderCell(String headerText) {
    this.headerText = headerText;
  }

  public String getHeaderText() {
    return this.headerText;
  }

  @Override
  public String toString() {
    return headerText;
  }
}
