package com.gkaraffa.guarneri.view;

import java.util.HashMap;
import java.util.Objects;

public class ViewTableBuilder {
  private int xExtentIndex = 0;
  private int yExtentIndex = 0;
  private HashMap<CoordinateKey, ViewCell> hashMap = new HashMap<CoordinateKey, ViewCell>();

  public ViewTableBuilder() {}
  
  public void insertCell(int xCoord, int yCoord, ViewCell viewCell) {
    this.alignExtents(xCoord, yCoord);
    hashMap.put(new CoordinateKey(xCoord, yCoord), viewCell);
  }
  
  public ViewTable compileTable() {
    ViewCell[][] cellArray = instantiateCells();
    
    for (int yIndex = 0; yIndex <= yExtentIndex; yIndex++) {
      for (int xIndex = 0; xIndex <= xExtentIndex; xIndex++) {
        ViewCell fetchedCell = hashMap.get(new CoordinateKey(xIndex, yIndex));
        
        if (fetchedCell == null) {
          fetchedCell = new ViewCell("");
        }
        
        cellArray[yIndex][xIndex] = fetchedCell;
      }
    }
    
    int[] columnWidths = this.generateColumnWidths(cellArray);
    
    return new ViewTable(cellArray, columnWidths);
  }
  
  public void clear() {
    this.xExtentIndex = 0;
    this.yExtentIndex = 0;
    this.hashMap = new HashMap<CoordinateKey, ViewCell>();
  }
  
  private ViewCell[][] instantiateCells(){
    ViewCell[][] cellArray = new ViewCell[yExtentIndex + 1][xExtentIndex + 1];
    
    return cellArray;
  }

  private void alignExtents(int xCoord, int yCoord) {
    if (xCoord > xExtentIndex) {
      xExtentIndex = xCoord;
    }
    
    if (yCoord > yExtentIndex) {
      yExtentIndex = yCoord;
    }
  }
  
  private int[] generateColumnWidths(ViewCell[][] viewCells) {
    int columnWidths[] = new int[this.xExtentIndex + 1];

    for (int yIndex = 0; yIndex <= this.yExtentIndex; yIndex++) {
      for (int xIndex = 0; xIndex <= this.xExtentIndex; xIndex++) {
        int currentCellSize = viewCells[yIndex][xIndex].getCellText().length();

        if (currentCellSize > columnWidths[xIndex]) {
          columnWidths[xIndex] = currentCellSize;
        }
      }
    }

    return columnWidths;
  }
  
  class CoordinateKey {
    private int xCoord = 0;
    private int yCoord = 0;

    public CoordinateKey(int xCoord, int yCoord) {
      this.xCoord = xCoord;
      this.yCoord = yCoord;
    }
    
    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }

      if ((obj == null) || !(obj instanceof CoordinateKey)) {
        return false;
      }

      CoordinateKey cKey = (CoordinateKey) obj;
      
      return xCoord == cKey.xCoord &&
          yCoord == cKey.yCoord;
    }

    @Override
    public int hashCode() {
      return Objects.hash(xCoord, yCoord);
    }
  }
  
}
