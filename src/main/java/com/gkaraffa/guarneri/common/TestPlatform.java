package com.gkaraffa.guarneri.common;

import com.gkaraffa.cremona.helper.ScaleHelper;
import com.gkaraffa.cremona.theoretical.scale.Scale;
import com.gkaraffa.guarneri.outputform.InstrumentTextOutputFormFactory;
import com.gkaraffa.guarneri.outputform.OutputForm;
import com.gkaraffa.guarneri.outputform.OutputFormFactory;
import com.gkaraffa.guarneri.outputform.TabularTextOutputFormFactory;
import com.gkaraffa.guarneri.view.ViewFactory;
import com.gkaraffa.guarneri.view.ViewQuery;
import com.gkaraffa.guarneri.view.ViewTable;
import com.gkaraffa.guarneri.view.analytic.scale.IntervalAnalyticViewFactory;
import com.gkaraffa.guarneri.view.analytic.scale.ReharmonizationOptionsAnalyticViewFactory;
import com.gkaraffa.guarneri.view.analytic.scale.RomanNumeralAnalyticViewFactory;
import com.gkaraffa.guarneri.view.analytic.scale.StepPatternAnalyticFactory;
import com.gkaraffa.guarneri.view.instrument.GuitarViewFactory;

public class TestPlatform {

  public TestPlatform() {}
  
  public static void main(String[] args) {
    ScaleHelper helper = ScaleHelper.getInstance();
    Scale scale = helper.getScale("C", "Ionian");
    ViewFactory viewFactory = new StepPatternAnalyticFactory();
    ViewTable viewTable = viewFactory.createView(new ViewQuery(scale));
    OutputFormFactory formFactory = new TabularTextOutputFormFactory();
    OutputForm form = formFactory.renderView(viewTable);
    System.out.println(form.toString());
    
    viewFactory = new RomanNumeralAnalyticViewFactory();
    viewTable = viewFactory.createView(new ViewQuery(scale));
    formFactory = new TabularTextOutputFormFactory();
    form = formFactory.renderView(viewTable);
    System.out.println(form.toString());

    viewFactory = new IntervalAnalyticViewFactory();
    viewTable = viewFactory.createView(new ViewQuery(scale));
    formFactory = new TabularTextOutputFormFactory();
    form = formFactory.renderView(viewTable);
    System.out.println(form.toString());
    
    viewFactory = new GuitarViewFactory();
    viewTable = viewFactory.createView(new ViewQuery(scale));
    formFactory = new InstrumentTextOutputFormFactory();
    form = formFactory.renderView(viewTable);
    System.out.println(form.toString());

    viewFactory = new ReharmonizationOptionsAnalyticViewFactory();
    viewTable = viewFactory.createView(new ViewQuery(scale));
    formFactory = new TabularTextOutputFormFactory();
    form = formFactory.renderView(viewTable);
    System.out.println(form.toString());
  }
}
