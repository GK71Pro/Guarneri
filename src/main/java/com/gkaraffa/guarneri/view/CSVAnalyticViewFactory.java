package com.gkaraffa.guarneri.view;

import java.util.List;

import com.gkaraffa.guarneri.analysis.AnalyticCell;
import com.gkaraffa.guarneri.analysis.HeaderCell;
import com.gkaraffa.guarneri.analysis.Analytic;

public class CSVAnalyticViewFactory extends AnalyticViewFactory {

  @Override
  public View renderView(Analytic analytic) {
    StringBuilder sB = new StringBuilder();
    
    sB.append(this.renderHeader(analytic));
    sB.append(this.renderBody(analytic));
    sB.append("\n");
    
    String viewString = sB.toString();
    View outputView = new View(viewString, viewString.getBytes());
    
    return outputView;
  }

  private String renderHeader(Analytic analytic) {
    StringBuilder sB = new StringBuilder();

    sB.append(renderTitles(analytic) + "\n");

    return sB.toString();
  }

  private String renderTitles(Analytic analytic) {
    List<HeaderCell> headerCells = analytic.getHeaderRow();
    int numCols = analytic.getColumnCount();
    StringBuilder sB = new StringBuilder();

    for (int index = 0; index < numCols; index++) {
      sB.append(headerCells.get(index));
      sB.append(", ");
    }

    sB.setLength(sB.length() - 2);

    return sB.toString();
  }
  
  private String renderBody(Analytic analytic) {
    StringBuilder sB = new StringBuilder();
    int numRows = analytic.getRowCount();
    
    for(int index = 0; index < numRows; index++) {
      List<AnalyticCell> analyticCells = analytic.getAnalyticRow(index);
      
      for(AnalyticCell analyticCell: analyticCells) {
        sB.append(analyticCell);
        sB.append(", ");
      }
      
      sB.setLength(sB.length() - 2);
      sB.append("\n");
    }
    
    return sB.toString();
  }
}
