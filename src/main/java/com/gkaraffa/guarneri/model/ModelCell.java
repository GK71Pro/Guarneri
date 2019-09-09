package com.gkaraffa.guarneri.model;

public class ModelCell {
  private String cellText = null;

  public ModelCell() {}

  public ModelCell(String cellText) {
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
