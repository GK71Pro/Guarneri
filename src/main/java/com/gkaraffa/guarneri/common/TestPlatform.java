package com.gkaraffa.guarneri.common;

import com.gkaraffa.cremona.helper.ScaleHelper;
import com.gkaraffa.cremona.theoretical.scale.Scale;
import com.gkaraffa.guarneri.outputform.InstrumentTextOutputFormFactory;
import com.gkaraffa.guarneri.outputform.OutputForm;
import com.gkaraffa.guarneri.outputform.OutputFormFactory;
import com.gkaraffa.guarneri.outputform.TabularTextOutputFormFactory;
import com.gkaraffa.guarneri.view.ViewFactory;
import com.gkaraffa.guarneri.view.ViewQuery;
import com.gkaraffa.guarneri.view.ViewQueryBuilder;
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
    ViewQueryBuilder viewQueryBuilder = new ViewQueryBuilder();
    Scale scale = helper.getScale("C", "Ionian");
    
    viewQueryBuilder.insertCriteria("Scale", scale);
    ViewQuery scaleViewQuery = viewQueryBuilder.compileViewQuery();
    
    viewQueryBuilder.clear();
    viewQueryBuilder.insertCriteria("ToneGroupObject", scale);
    ViewQuery tgViewQuery = viewQueryBuilder.compileViewQuery();
    
    ViewFactory viewFactory = new StepPatternAnalyticFactory();
    
    ViewTable viewTable = viewFactory.createView(scaleViewQuery);
    OutputFormFactory formFactory = new TabularTextOutputFormFactory();
    OutputForm form = formFactory.renderView(viewTable);
    System.out.println(form.toString());
    
    viewFactory = new RomanNumeralAnalyticViewFactory();
    viewTable = viewFactory.createView(scaleViewQuery);
    formFactory = new TabularTextOutputFormFactory();
    form = formFactory.renderView(viewTable);
    System.out.println(form.toString());

    viewFactory = new IntervalAnalyticViewFactory();
    viewTable = viewFactory.createView(scaleViewQuery);
    formFactory = new TabularTextOutputFormFactory();
    form = formFactory.renderView(viewTable);
    System.out.println(form.toString());
    
    viewFactory = new GuitarViewFactory();
    viewTable = viewFactory.createView(tgViewQuery);
    formFactory = new InstrumentTextOutputFormFactory();
    form = formFactory.renderView(viewTable);
    System.out.println(form.toString());

    viewFactory = new ReharmonizationOptionsAnalyticViewFactory();
    viewTable = viewFactory.createView(scaleViewQuery);
    formFactory = new TabularTextOutputFormFactory();
    form = formFactory.renderView(viewTable);
    System.out.println(form.toString());
    
    viewFactory = new GuitarViewFactory();
    scale = helper.getScale("A", "Melodic Minor");
    viewTable = viewFactory.createView(tgViewQuery);
    form = formFactory.renderView(viewTable);
    System.out.println(form.toString());
  }
}
