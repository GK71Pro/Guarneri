package com.gkaraffa.guarneri.view;

import com.gkaraffa.cremona.theoretical.chord.Chord;
import com.gkaraffa.cremona.theoretical.scale.Scale;

public abstract class ViewFactory {
  public abstract ViewTable createView();

  public abstract ViewTable createView(ViewQuery viewQuery);

  protected int[] generateColumnWidths(ViewCell[][] viewCells) {
    int breadth = viewCells[0].length;
    int columnWidths[] = new int[breadth];

    for (int rowIndex = 0; rowIndex < viewCells.length; rowIndex++) {
      for (int columnIndex = 0; columnIndex < breadth; columnIndex++) {
        int currentCellSize = viewCells[rowIndex][columnIndex].getCellText().length();

        if (currentCellSize > columnWidths[columnIndex]) {
          columnWidths[columnIndex] = currentCellSize;
        }
      }
    }

    return columnWidths;
  }

  protected String validate(ViewCell[][] viewCells, int[] columnWidths) {
    if (!validateArrayPopulated(viewCells)) {
      return new String("Array is not populated.");
    }

    if (!validateArrayBalanced(viewCells)) {
      return new String("Arrays are not balanced.");
    }

    if (!validateContentAndWidthSync(viewCells, columnWidths)) {
      return new String("Arrays are not balanced.");
    }
    return null;
  }


  private boolean validateArrayPopulated(ViewCell[][] viewCells) {
    if (viewCells.length == 0) {
      return false;
    }

    if (viewCells[0].length == 0) {
      return false;
    }

    return true;
  }

  private boolean validateArrayBalanced(ViewCell[][] viewCells) {
    int extent = viewCells[0].length;

    for (ViewCell[] modelRow : viewCells) {
      if (modelRow.length != extent) {
        return false;
      }
    }

    return true;
  }

  
  private boolean validateContentAndWidthSync(ViewCell[][] viewCells, int[] columnWidths) {
    int actualColumns = viewCells[0].length;
    
    if (actualColumns != columnWidths.length) {
      return false;
    }
    
    return true;
  }
  
}
