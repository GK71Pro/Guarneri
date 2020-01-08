package com.gkaraffa.guarneri.view;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class ViewTableBuilder {
  private HashMap<CoordinatePair, ViewCell> hashMap = new HashMap<CoordinatePair, ViewCell>();

  public ViewTableBuilder() {}

  public void insertCell(int xCoord, int yCoord, ViewCell viewCell) {
    hashMap.put(new CoordinatePair(xCoord, yCoord), viewCell);
  }

  public ViewTable compileTable() {
    CoordinatePair extents = establishExtents();
    ViewCell[][] cellArray = instantiateCells(extents);
    int columnWidths[] = new int[extents.xCoord + 1];

    for (int yIndex = 0; yIndex <= extents.yCoord; yIndex++) {
      for (int xIndex = 0; xIndex <= extents.xCoord; xIndex++) {
        ViewCell fetchedCell = hashMap.get(new CoordinatePair(xIndex, yIndex));

        // ViewTable assembly
        if (fetchedCell == null) {
          fetchedCell = new ViewCell("");
        }
        cellArray[yIndex][xIndex] = fetchedCell;

        // ColumnWidths tracking
        int currentCellSize = fetchedCell.getCellText().length();
        if (currentCellSize > columnWidths[xIndex]) {
          columnWidths[xIndex] = currentCellSize;
        }
      }
    }

    return new ViewTable(cellArray, columnWidths);
  }

  public void clear() {
    // null out hashMap member and attempt garbage collection
    this.hashMap = null;
    System.gc();

    // create new HashMap object and assign reference
    this.hashMap = new HashMap<CoordinatePair, ViewCell>();
  }

  private CoordinatePair establishExtents() {
    int xMax = 0;
    int yMax = 0;

    // convert hashMap into CoordinatePair array
    Set<CoordinatePair> coordinateSet = this.hashMap.keySet();
    CoordinatePair[] coordinateArray =
        coordinateSet.toArray(new CoordinatePair[coordinateSet.size()]);

    // establish extends
    for (CoordinatePair coordinateKey : coordinateArray) {
      if (coordinateKey.xCoord > xMax) {
        xMax = coordinateKey.xCoord;
      }

      if (coordinateKey.yCoord > yMax) {
        yMax = coordinateKey.yCoord;
      }
    }

    // create new CoordinatePair with extents and return
    return new CoordinatePair(xMax, yMax);
  }

  private ViewCell[][] instantiateCells(CoordinatePair extents) {
    ViewCell[][] cellArray = new ViewCell[extents.yCoord + 1][extents.xCoord + 1];

    return cellArray;
  }

  class CoordinatePair {
    private int xCoord = 0;
    private int yCoord = 0;

    public CoordinatePair(int xCoord, int yCoord) {
      this.xCoord = xCoord;
      this.yCoord = yCoord;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }

      if ((obj == null) || !(obj instanceof CoordinatePair)) {
        return false;
      }

      CoordinatePair cKey = (CoordinatePair) obj;

      return xCoord == cKey.xCoord && yCoord == cKey.yCoord;
    }

    @Override
    public int hashCode() {
      return Objects.hash(xCoord, yCoord);
    }
  }


}
