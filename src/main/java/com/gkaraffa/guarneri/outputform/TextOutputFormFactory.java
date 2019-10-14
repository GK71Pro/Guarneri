package com.gkaraffa.guarneri.outputform;

import com.gkaraffa.guarneri.view.ViewCell;
import com.gkaraffa.guarneri.view.ViewTable;

public abstract class TextOutputFormFactory extends OutputFormFactory {
  protected abstract String renderBody(int fieldSpace, ViewTable modelTable);

  protected int determineFieldSpace(ViewTable modelTable) {
    int fieldCount = modelTable.getColumnCount();
    int fieldSpace = fieldCount + 1;

    for (int index = 0; index < fieldCount; index++) {
      fieldSpace = fieldSpace + modelTable.getColumnWidth(index) + 2;
    }

    return fieldSpace;
  }

  protected String renderHeader(int fieldSpace, ViewTable modelTable) {
    StringBuilder sB = new StringBuilder();
    String bar = renderBar(fieldSpace);

    sB.append(bar + "\n");
    sB.append(renderTitles(modelTable) + "\n");
    sB.append(bar + "\n");

    return sB.toString();
  }

  protected String renderFoot(int fieldSpace) {
    return (renderBar(fieldSpace) + "\n");
  }

  protected String renderBar(int fieldSpace) {
    StringBuilder sB = new StringBuilder();

    for (int index = 0; index < fieldSpace; index++) {
      sB.append('-');
    }

    return sB.toString();
  }

  protected String renderTitles(ViewTable modelTable) {
    ViewCell[] headerCells = modelTable.getRow(0);
    int numCols = modelTable.getColumnCount();
    StringBuilder sB = new StringBuilder();

    for (int index = 0; index < numCols; index++) {
      sB.append("| ");
      sB.append(
          String.format(createFormatString(modelTable.getColumnWidth(index)), headerCells[index]));
      sB.append(" ");
    }

    sB.append("|");

    return sB.toString();
  }

  protected String createFormatString(int columnMax) {
    StringBuilder sB = new StringBuilder();

    sB.append("%-");
    sB.append(columnMax);
    sB.append(".");
    sB.append(columnMax);
    sB.append("s");

    return sB.toString();
  }

}
