package com.gkaraffa.guarneri.view;

import com.gkaraffa.cremona.theoretical.chord.Chord;
import com.gkaraffa.cremona.theoretical.scale.Scale;

public abstract class ViewFactory {
  public abstract ViewTable createModel();

  public abstract ViewTable createModel(Scale scale);

  public abstract ViewTable createModel(Chord chord);

  protected int[] generateColumnWidths(ViewCell[][] modelCells) {
    int breadth = modelCells[0].length;
    int columnWidths[] = new int[breadth];

    for (int rowIndex = 0; rowIndex < modelCells.length; rowIndex++) {
      for (int columnIndex = 0; columnIndex < breadth; columnIndex++) {
        int currentCellSize = modelCells[rowIndex][columnIndex].getCellText().length();

        if (currentCellSize > columnWidths[columnIndex]) {
          columnWidths[columnIndex] = currentCellSize;
        }
      }
    }

    return columnWidths;
  }

  protected String validate(ViewCell[][] modelCells, int[] columnWidths) {
    if (!validateArrayPopulated(modelCells)) {
      return new String("Array is not populated.");
    }

    if (!validateArrayBalanced(modelCells)) {
      return new String("Arrays are not balanced.");
    }

    if (!validateContentAndWidthSync(modelCells, columnWidths)) {
      return new String("Arrays are not balanced.");
    }
    return null;
  }


  private boolean validateArrayPopulated(ViewCell[][] modelCells) {
    if (modelCells.length == 0) {
      return false;
    }

    if (modelCells[0].length == 0) {
      return false;
    }

    return true;
  }

  private boolean validateArrayBalanced(ViewCell[][] modelCells) {
    int extent = modelCells[0].length;

    for (ViewCell[] modelRow : modelCells) {
      if (modelRow.length != extent) {
        return false;
      }
    }

    return true;
  }

  
  private boolean validateContentAndWidthSync(ViewCell[][] modelCells, int[] columnWidths) {
    int actualColumns = modelCells[0].length;
    
    if (actualColumns != columnWidths.length) {
      return false;
    }
    
    return true;
  }
  
}
