package com.gkaraffa.guarneri.view;

public class ViewCell {
  private String cellText = null;

  public ViewCell() {}

  public ViewCell(String cellText) {
    this.cellText = cellText;
  }

  public String getCellText() {
    return cellText;
  }

  @Override
  public String toString() {
    return cellText;
  }
}
