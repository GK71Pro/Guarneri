package com.gkaraffa.guarneri.model;

import com.gkaraffa.cremona.theoretical.chord.Chord;
import com.gkaraffa.cremona.theoretical.scale.Scale;

public abstract class ModelFactory {
  public abstract ModelTable createModel();

  public abstract ModelTable createModel(Scale scale);

  public abstract ModelTable createModel(Chord chord);

  protected ModelCell[] generateHeaders(String[] headerStrings) {
    ModelCell[] headerCells = new ModelCell[headerStrings.length];
    int counter = 0;

    for (String header : headerStrings) {
      headerCells[counter] = new ModelCell(header);
      counter++;
    }

    return headerCells;
  }

  protected int[] generateColumnWidths(ModelCell[][] modelCells) {
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

  protected String validate(ModelCell[][] modelCells) {
    if (!validateArrayPopulated(modelCells)) {
      return new String("Array is not populated.");
    }

    if (!validateArrayBalanced(modelCells)) {
      return new String("Arrays are not balanced.");
    }

    return null;
  }


  private boolean validateArrayPopulated(ModelCell[][] modelCells) {
    if (modelCells.length == 0) {
      return false;
    }

    if (modelCells[0].length == 0) {
      return false;
    }

    return true;
  }

  private boolean validateArrayBalanced(ModelCell[][] modelCells) {
    int extent = modelCells[0].length;

    for (ModelCell[] modelRow : modelCells) {
      if (modelRow.length != extent) {
        return false;
      }
    }

    return true;
  }

  /*
  private boolean validateContentAndWidthSync(ModelCell[][] modelCells, int[] columnWidths) {
    int actualColumns = modelCells[0].length;
    
    if (actualColumns != columnWidths.length) {
      return false;
    }
    
    return true;
  }
  */
}
