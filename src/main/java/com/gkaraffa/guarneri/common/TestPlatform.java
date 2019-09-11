package com.gkaraffa.guarneri.common;

import com.gkaraffa.cremona.helper.Helper;
import com.gkaraffa.cremona.theoretical.scale.Scale;
import com.gkaraffa.guarneri.model.ModelFactory;
import com.gkaraffa.guarneri.model.ModelTable;
import com.gkaraffa.guarneri.model.analytic.IntervalAnalyticModelFactory;
import com.gkaraffa.guarneri.model.analytic.ScalarAnalyticModelFactory;

public class TestPlatform {

  public TestPlatform() {}

  public static void main(String[] args) {
    Helper helper = Helper.getInstance();
    Scale scale = helper.getScale("C", "Ionian");

    //ModelFactory modelFactory = new IntervalAnalyticModelFactory();
    ModelFactory modelFactory = new ScalarAnalyticModelFactory();
    ModelTable modelTable = modelFactory.createModel(scale);

    int depth = modelTable.getRowCount();
    int breadth = modelTable.getColumnCount();
    for (int rowIndex = 0; rowIndex < depth; rowIndex++) {
      for (int columnIndex = 0; columnIndex < breadth; columnIndex++) {
        System.out.print(modelTable.getCell(rowIndex, columnIndex) + ", ");
      }
      System.out.println();
    }
    
    for (int columnIndex = 0; columnIndex < breadth; columnIndex++) {
      System.out.print(modelTable.getColumnWidth(columnIndex) + ", ");
    }
    System.out.println();
  }
  

}
