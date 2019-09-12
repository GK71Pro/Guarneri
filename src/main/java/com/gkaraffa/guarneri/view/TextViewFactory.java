package com.gkaraffa.guarneri.view;

import com.gkaraffa.guarneri.model.ModelCell;
import com.gkaraffa.guarneri.model.ModelTable;

public class TextViewFactory extends ViewFactory {

  @Override
  public View renderView(ModelTable modelTable) {
    StringBuilder sB = new StringBuilder();
    int fieldSpace = determineFieldSpace(modelTable);

    sB.append(this.renderHeader(fieldSpace, modelTable));
    sB.append(this.renderBody(fieldSpace, modelTable));
    sB.append(this.renderFoot(fieldSpace));
    sB.append("\n");

    String viewString = sB.toString();
    View outputView = new View(viewString, viewString.getBytes());

    return outputView;
  }

  private int determineFieldSpace(ModelTable modelTable) {
    int fieldCount = modelTable.getColumnCount();
    int fieldSpace = fieldCount + 1;

    for (int index = 0; index < fieldCount; index++) {
      fieldSpace = fieldSpace + modelTable.getColumnWidth(index) + 2;
    }

    return fieldSpace;
  }

  private String renderHeader(int fieldSpace, ModelTable modelTable) {
    StringBuilder sB = new StringBuilder();

    sB.append(renderBar(fieldSpace) + "\n");
    sB.append(renderTitles(modelTable) + "\n");
    sB.append(renderBar(fieldSpace) + "\n");

    return sB.toString();
  }

  private String renderBody(int fieldSpace, ModelTable modelTable) {
    StringBuilder sB = new StringBuilder();
    int numRows = modelTable.getRowCount();

    for (int index = 1; index < numRows; index++) {
      ModelCell[] modelCells = modelTable.getRow(index);
      int columnCount = 0;

      for (ModelCell modelCell : modelCells) {
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

  private String renderTitles(ModelTable modelTable) {
    ModelCell[] headerCells = modelTable.getRow(0);
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
