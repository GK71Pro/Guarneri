package com.gkaraffa.guarneri.common;

import com.gkaraffa.cremona.helper.Helper;
import com.gkaraffa.cremona.theoretical.scale.Scale;
import com.gkaraffa.guarneri.outputform.OutputForm;
import com.gkaraffa.guarneri.outputform.OutputFormFactory;
import com.gkaraffa.guarneri.outputform.TextOutputFormFactory;
import com.gkaraffa.guarneri.view.ViewFactory;
import com.gkaraffa.guarneri.view.ViewTable;
import com.gkaraffa.guarneri.view.analytic.RomanNumeralAnalyticViewFactory;

public class TestPlatform {

  public TestPlatform() {}

  public static void main(String[] args) {
    Helper helper = Helper.getInstance();
    Scale scale = helper.getScale("C", "Ionian");
    ViewFactory modelFactory = new RomanNumeralAnalyticViewFactory();
    ViewTable modelTable = modelFactory.createModel(scale);
    OutputFormFactory viewFactory = new TextOutputFormFactory();
    OutputForm view = viewFactory.renderView(modelTable);

    System.out.println(view.toString());
  }


}
