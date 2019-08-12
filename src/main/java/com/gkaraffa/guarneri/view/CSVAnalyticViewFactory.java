package com.gkaraffa.guarneri.view;

import java.util.List;

import com.gkaraffa.guarneri.analysis.AnalyticCell;
import com.gkaraffa.guarneri.analysis.HeaderCell;
import com.gkaraffa.guarneri.analysis.TabularAnalytic;

public class CSVAnalyticViewFactory extends AnalyticViewFactory {

  @Override
  public AnalyticView renderView(TabularAnalytic tabularAnalytic) {
    StringBuilder sB = new StringBuilder();
    
    sB.append(this.renderHeader(tabularAnalytic));
    sB.append(this.renderBody(tabularAnalytic));
    sB.append("\n");
    
    String viewString = sB.toString();
    AnalyticView outputView = new AnalyticView(viewString, viewString.getBytes());
    
    return outputView;
  }

  private String renderHeader(TabularAnalytic tabularAnalytic) {
    StringBuilder sB = new StringBuilder();

    sB.append(renderTitles(tabularAnalytic) + "\n");

    return sB.toString();
  }

  private String renderTitles(TabularAnalytic tabularAnalytic) {
    List<HeaderCell> headerCells = tabularAnalytic.getHeaderRow();
    int numCols = tabularAnalytic.getColumnCount();
    StringBuilder sB = new StringBuilder();

    for (int index = 0; index < numCols; index++) {
      sB.append(headerCells.get(index));
      sB.append(", ");
    }

    sB.setLength(sB.length() - 2);

    return sB.toString();
  }
  
  private String renderBody(TabularAnalytic tabularAnalytic) {
    StringBuilder sB = new StringBuilder();
    int numRows = tabularAnalytic.getRowCount();
    
    for(int index = 0; index < numRows; index++) {
      List<AnalyticCell> analyticCells = tabularAnalytic.getAnalyticRow(index);
      
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
