package com.gkaraffa.guarneri.model.analytic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public abstract class Analytic
    implements AnalyticRow, AnalyticColumn, HeaderRow {
  private HashMap<Integer, List<AnalyticCell>> rowMap = new HashMap<Integer, List<AnalyticCell>>();
  private HashMap<Integer, List<AnalyticCell>> columnMap = new HashMap<Integer, List<AnalyticCell>>();
  protected HeaderCell[] headerRow;
  protected AnalyticCell[][] analyticCells;

  public int getRowCount() {
    return analyticCells.length;
  }
  
  public int getColumnCount() {
    return analyticCells[0].length;
  }
  
  @Override
  public List<AnalyticCell> getAnalyticRow(int row) {
    
    //check map cache
    List<AnalyticCell> analyticList = rowMap.get(row);
    if (analyticList != null) {
      return analyticList;
    }
    
    analyticList = new ArrayList<AnalyticCell>();    
    AnalyticCell[] analyticRow = analyticCells[row];
    
    for (AnalyticCell analyticCell: analyticRow) {
      analyticList.add(analyticCell);
    }
    
    rowMap.put(row, analyticList);

    return analyticList;
  }
  
  @Override
  public List<AnalyticCell> getAnalyticColumn(int column) {
    //check map cache
    List<AnalyticCell> analyticList = columnMap.get(column);
    if (analyticList != null) {
      return analyticList;
    }

    analyticList = new ArrayList<AnalyticCell>();
    
    for(AnalyticCell[] analyticRow: analyticCells) {
      analyticList.add(analyticRow[column]);
    }
    
    columnMap.put(column, analyticList);
    
    return analyticList;
  }

  @Override
  public List<HeaderCell> getHeaderRow() {
    return Arrays.asList(headerRow);
  }
}
