package com.gkaraffa.guarneri.outputform;

import com.gkaraffa.guarneri.view.ViewCell;
import com.gkaraffa.guarneri.view.ViewTable;

public class CSVOutputFormFactory extends OutputFormFactory {

  @Override
  public OutputForm renderView(ViewTable modelTable) {
    StringBuilder sB = new StringBuilder();

    sB.append(this.renderHeader(modelTable));
    sB.append(this.renderBody(modelTable));
    sB.append("\n");

    String viewString = sB.toString();
    OutputForm outputView = new OutputForm(viewString, viewString.getBytes());

    return outputView;
  }

  private String renderHeader(ViewTable modelTable) {
    StringBuilder sB = new StringBuilder();

    sB.append(renderTitles(modelTable) + "\n");

    return sB.toString();
  }

  private String renderTitles(ViewTable modelTable) {
    ViewCell[] headerCells = modelTable.getRow(0);
    StringBuilder sB = new StringBuilder();

    for (ViewCell headerCell : headerCells) {
      sB.append(headerCell);
      sB.append(", ");
    }

    sB.setLength(sB.length() - 2);

    return sB.toString();
  }

  private String renderBody(ViewTable modelTable) {
    StringBuilder sB = new StringBuilder();
    int numRows = modelTable.getRowCount();

    for (int index = 1; index < numRows; index++) {
      ViewCell[] modelCells = modelTable.getRow(index);

      for (ViewCell modelCell : modelCells) {
        sB.append(modelCell);
        sB.append(", ");
      }

      sB.setLength(sB.length() - 2);
      sB.append("\n");
    }

    return sB.toString();
  }
}
