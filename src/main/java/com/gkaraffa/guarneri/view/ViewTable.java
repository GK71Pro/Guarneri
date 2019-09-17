package com.gkaraffa.guarneri.view;

public class ViewTable {
  private ViewCell[][] modelCells;
  private int[] columnWidths;

  public ViewTable(ViewCell[][] modelCells, int[] columnWidths) {
    this.modelCells = modelCells;
    this.columnWidths = columnWidths;
  }

  public ViewCell getCell(int row, int column) throws IllegalArgumentException {
    if (!isInBounds(row, column)) {
      throw new IllegalArgumentException("Request is outside of table boundary");
    }

    return modelCells[row][column];
  }

  public ViewCell[] getColumn(int column) throws IllegalArgumentException {
    if (!isInBounds(0, column)) {
      throw new IllegalArgumentException("Request is outside of table boundary");
    }

    int rowCount = modelCells.length;
    ViewCell[] selectColumn = new ViewCell[rowCount];

    for (int index = 0; index < rowCount; index++) {
      selectColumn[index] = modelCells[index][column];
    }

    return selectColumn;
  }

  public ViewCell[] getRow(int row) throws IllegalArgumentException {
    if (!isInBounds(row, 0)) {
      throw new IllegalArgumentException("Request is outside of table boundary");
    }

    return modelCells[row];
  }

  public int getRowCount() {
    return modelCells.length;
  }

  public int getColumnCount() throws IllegalArgumentException {
    if (!isInBounds(0, 0)) {
      throw new IllegalArgumentException("Request is outside of table boundary");
    }

    return modelCells[0].length;
  }

  public int getColumnWidth(int column) throws IllegalArgumentException {
    if (!isInBounds(0, column)) {
      throw new IllegalArgumentException("Request is outside of table boundary");
    }

    return this.columnWidths[column];
  }

  private boolean isInBounds(int row, int column) {
    int rowCount = modelCells.length;

    if ((rowCount == 0) || (row > rowCount - 1)) {
      return false;
    }

    if (column > modelCells[0].length - 1) {
      return false;
    }

    return true;
  }


}
