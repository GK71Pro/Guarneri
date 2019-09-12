package com.gkaraffa.guarneri.view;

import com.gkaraffa.guarneri.model.ModelCell;
import com.gkaraffa.guarneri.model.ModelTable;

public class CSVViewFactory extends ViewFactory {

  @Override
  public View renderView(ModelTable modelTable) {
    StringBuilder sB = new StringBuilder();

    sB.append(this.renderHeader(modelTable));
    sB.append(this.renderBody(modelTable));
    sB.append("\n");

    String viewString = sB.toString();
    View outputView = new View(viewString, viewString.getBytes());

    return outputView;
  }

  private String renderHeader(ModelTable modelTable) {
    StringBuilder sB = new StringBuilder();

    sB.append(renderTitles(modelTable) + "\n");

    return sB.toString();
  }

  private String renderTitles(ModelTable modelTable) {
    ModelCell[] headerCells = modelTable.getRow(0);
    StringBuilder sB = new StringBuilder();

    for (ModelCell headerCell : headerCells) {
      sB.append(headerCell);
      sB.append(", ");
    }

    sB.setLength(sB.length() - 2);

    return sB.toString();
  }

  private String renderBody(ModelTable modelTable) {
    StringBuilder sB = new StringBuilder();
    int numRows = modelTable.getRowCount();

    for (int index = 1; index < numRows; index++) {
      ModelCell[] modelCells = modelTable.getRow(index);

      for (ModelCell modelCell : modelCells) {
        sB.append(modelCell);
        sB.append(", ");
      }

      sB.setLength(sB.length() - 2);
      sB.append("\n");
    }

    return sB.toString();
  }
}
