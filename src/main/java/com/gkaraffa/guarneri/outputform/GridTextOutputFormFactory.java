package com.gkaraffa.guarneri.outputform;

import com.gkaraffa.guarneri.view.ViewCell;
import com.gkaraffa.guarneri.view.ViewTable;

public class GridTextOutputFormFactory extends TextOutputFormFactory {

  @Override
  protected String renderBody(int fieldSpace, ViewTable modelTable) {
    StringBuilder sB = new StringBuilder();
    int numRows = modelTable.getRowCount();
    String bar = this.renderBar(fieldSpace);

    sB.append(bar + "\n");
    for (int index = 0; index < numRows; index++) {
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
      sB.append(bar + "\n");
    }

    return sB.toString();
  }

  @Override
  public OutputForm renderView(ViewTable modelTable) {
    StringBuilder sB = new StringBuilder();
    int fieldSpace = determineFieldSpace(modelTable);

    sB.append(this.renderBody(fieldSpace, modelTable));
    sB.append("\n");

    String viewString = sB.toString();
    OutputForm outputView = new OutputForm(viewString, viewString.getBytes());

    return outputView;
  }

}
