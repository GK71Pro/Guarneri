package com.gkaraffa.guarneri.outputform;

import com.gkaraffa.guarneri.view.ViewCell;
import com.gkaraffa.guarneri.view.ViewTable;

public class TextOutputFormFactory extends OutputFormFactory {

  @Override
  public OutputForm renderView(ViewTable modelTable) {
    StringBuilder sB = new StringBuilder();
    int fieldSpace = determineFieldSpace(modelTable);

    sB.append(this.renderHeader(fieldSpace, modelTable));
    sB.append(this.renderBody(fieldSpace, modelTable));
    sB.append(this.renderFoot(fieldSpace));
    sB.append("\n");

    String viewString = sB.toString();
    OutputForm outputView = new OutputForm(viewString, viewString.getBytes());

    return outputView;
  }

  private int determineFieldSpace(ViewTable modelTable) {
    int fieldCount = modelTable.getColumnCount();
    int fieldSpace = fieldCount + 1;

    for (int index = 0; index < fieldCount; index++) {
      fieldSpace = fieldSpace + modelTable.getColumnWidth(index) + 2;
    }

    return fieldSpace;
  }

  private String renderHeader(int fieldSpace, ViewTable modelTable) {
    StringBuilder sB = new StringBuilder();

    sB.append(renderBar(fieldSpace) + "\n");
    sB.append(renderTitles(modelTable) + "\n");
    sB.append(renderBar(fieldSpace) + "\n");

    return sB.toString();
  }

  private String renderBody(int fieldSpace, ViewTable modelTable) {
    StringBuilder sB = new StringBuilder();
    int numRows = modelTable.getRowCount();

    for (int index = 1; index < numRows; index++) {
      ViewCell[] modelCells = modelTable.getRow(index);
      int columnCount = 0;

      for (ViewCell modelCell : modelCells) {
        sB.append("| ");
        sB.append(
            String.format(createFormatString(modelTable.getColumnWidth(columnCount)), modelCell));
        sB.append(" ");
        columnCount++;
      }
      sB.append("|\n");
    }

    return sB.toString();
  }

  private String renderFoot(int fieldSpace) {
    return (renderBar(fieldSpace) + "\n");
  }

  private String renderBar(int fieldSpace) {
    StringBuilder sB = new StringBuilder();

    for (int index = 0; index < fieldSpace; index++) {
      sB.append('-');
    }

    return sB.toString();
  }

  private String renderTitles(ViewTable modelTable) {
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

  private String createFormatString(int columnMax) {
    StringBuilder sB = new StringBuilder();

    sB.append("%-");
    sB.append(columnMax);
    sB.append(".");
    sB.append(columnMax);
    sB.append("s");

    return sB.toString();
  }
}
