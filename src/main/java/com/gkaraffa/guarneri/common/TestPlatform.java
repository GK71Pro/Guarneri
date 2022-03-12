package com.gkaraffa.guarneri.common;

import java.util.ArrayList;
import java.util.List;

import com.gkaraffa.cremona.helper.ScaleHelper;
import com.gkaraffa.cremona.theoretical.ToneGroupObject;
import com.gkaraffa.guarneri.outputform.CSVOutputFormFactory;
import com.gkaraffa.guarneri.outputform.OutputForm;
import com.gkaraffa.guarneri.outputform.OutputFormFactory;
import com.gkaraffa.guarneri.view.ViewFactory;
import com.gkaraffa.guarneri.view.ViewQuery;
import com.gkaraffa.guarneri.view.ViewQueryBuilder;
import com.gkaraffa.guarneri.view.ViewTable;
import com.gkaraffa.guarneri.view.instrument.GuitarViewFactory;

public class TestPlatform {
  private ScaleHelper helper;
  private ViewQueryBuilder viewQueryBuilder;

  public TestPlatform() {
    this.helper = ScaleHelper.getInstance();
    this.viewQueryBuilder = new ViewQueryBuilder();

  }

  public static void main(String[] args) {
    TestPlatform testPlatform = new TestPlatform();

    testPlatform.run();
  }

  public void run() {
    ToneGroupObject toneGroupObject = this.helper.getScale("C", "Ionian");
    ViewQuery toneGroupObjectQuery = createToneGroupViewQuery(toneGroupObject);

    List<OutputForm> forms = new ArrayList<>();

    //forms.add(createTabularOutputForm(new StepPatternAnalyticFactory(), scaleQuery));
    //forms.add(createTabularOutputForm(new RomanNumeralAnalyticViewFactory(), scaleQuery));
    //forms.add(createTabularOutputForm(new IntervalAnalyticViewFactory(), scaleQuery));
    //forms.add(createCSVOutputForm(new ParallelModeAnalyticViewFactory(), keyQuery));
    //forms.add(createTabularOutputForm(new ReharmonizationOptionsAnalyticViewFactory(), scaleQuery));
    forms.add(createCSVOutputForm(new GuitarViewFactory(), toneGroupObjectQuery));

    for(OutputForm form: forms) {
      System.out.println(form.toString());
    }

  }

  /*
  private ViewQuery createScaleViewQuery(String sKey, String sScale) {
    Scale scale = this.helper.getScale(sKey, sScale);

    this.viewQueryBuilder.clear();
    this.viewQueryBuilder.insertCriteria("Scale", scale);

    return viewQueryBuilder.compileViewQuery();
  }

  private ViewQuery createKeyViewQuery(Tone tKey) {
    this.viewQueryBuilder.clear();
    this.viewQueryBuilder.insertCriteria("Key", tKey);

    return viewQueryBuilder.compileViewQuery();
  }
  */

  private ViewQuery createToneGroupViewQuery(ToneGroupObject toneGroupObject) {
    this.viewQueryBuilder.clear();
    this.viewQueryBuilder.insertCriteria("ToneGroupObject", toneGroupObject);

    return viewQueryBuilder.compileViewQuery();
  }

  /*
  private OutputForm createTabularOutputForm(ViewFactory viewFactory, ViewQuery viewQuery) {
    ViewTable viewTable = viewFactory.createView(viewQuery);
    OutputFormFactory formFactory = new TabularTextOutputFormFactory();

    return formFactory.renderView(viewTable);

  }
  */

  private OutputForm createCSVOutputForm(ViewFactory viewFactory, ViewQuery viewQuery) {
    ViewTable viewTable = viewFactory.createView(viewQuery);
    OutputFormFactory formFactory = new CSVOutputFormFactory();

    return formFactory.renderView(viewTable);

  }

}
