package com.gkaraffa.guarneri.common;

import com.gkaraffa.cremona.helper.Helper;
import com.gkaraffa.cremona.theoretical.scale.Scale;
import com.gkaraffa.guarneri.outputform.GridTextOutputFormFactory;
import com.gkaraffa.guarneri.outputform.OutputForm;
import com.gkaraffa.guarneri.outputform.OutputFormFactory;
import com.gkaraffa.guarneri.outputform.TabularTextOutputFormFactory;
import com.gkaraffa.guarneri.view.ViewFactory;
import com.gkaraffa.guarneri.view.ViewTable;
import com.gkaraffa.guarneri.view.analytic.RomanNumeralAnalyticViewFactory;
import com.gkaraffa.guarneri.view.instrument.GuitarViewFactory;

public class TestPlatform {

  public TestPlatform() {}

  public static void main(String[] args) {
    Helper helper = Helper.getInstance();
    Scale scale = helper.getScale("C", "Ionian");
    ViewFactory modelFactory = new RomanNumeralAnalyticViewFactory();
    ViewTable modelTable = modelFactory.createModel(scale);
    OutputFormFactory viewFactory = new TabularTextOutputFormFactory();
    OutputForm view = viewFactory.renderView(modelTable);

    System.out.println(view.toString());


    modelFactory = new GuitarViewFactory();
    modelTable = modelFactory.createModel();
    viewFactory = new GridTextOutputFormFactory();
    view = viewFactory.renderView(modelTable);

    System.out.println(view.toString());

  }


}
