package com.gkaraffa.guarneri.common;

import com.gkaraffa.cremona.helper.Helper;
import com.gkaraffa.cremona.theoretical.scale.Scale;
import com.gkaraffa.guarneri.model.ModelFactory;
import com.gkaraffa.guarneri.model.ModelTable;
import com.gkaraffa.guarneri.model.analytic.RomanNumeralAnalyticModelFactory;
import com.gkaraffa.guarneri.view.TextViewFactory;
import com.gkaraffa.guarneri.view.View;
import com.gkaraffa.guarneri.view.ViewFactory;

public class TestPlatform {

  public TestPlatform() {}

  public static void main(String[] args) {
    Helper helper = Helper.getInstance();
    Scale scale = helper.getScale("C", "Ionian");
    ModelFactory modelFactory = new RomanNumeralAnalyticModelFactory();
    ModelTable modelTable = modelFactory.createModel(scale);
    ViewFactory viewFactory = new TextViewFactory();
    View view = viewFactory.renderView(modelTable);

    System.out.println(view.toString());
  }


}
