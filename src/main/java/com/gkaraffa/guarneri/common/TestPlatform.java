package com.gkaraffa.guarneri.common;

import com.gkaraffa.cremona.helper.ChordHelper;
import com.gkaraffa.cremona.helper.ScaleHelper;
import com.gkaraffa.cremona.theoretical.chord.Chord;
import com.gkaraffa.cremona.theoretical.scale.Scale;
import com.gkaraffa.guarneri.outputform.GridTextOutputFormFactory;
import com.gkaraffa.guarneri.outputform.OutputForm;
import com.gkaraffa.guarneri.outputform.OutputFormFactory;
import com.gkaraffa.guarneri.outputform.TabularTextOutputFormFactory;
import com.gkaraffa.guarneri.view.ViewFactory;
import com.gkaraffa.guarneri.view.ViewQuery;
import com.gkaraffa.guarneri.view.ViewTable;
import com.gkaraffa.guarneri.view.analytic.RomanNumeralAnalyticViewFactory;
import com.gkaraffa.guarneri.view.instrument.GuitarViewFactory;

public class TestPlatform {

  public TestPlatform() {}

  public static void main(String[] args) {
    ScaleHelper helper = ScaleHelper.getInstance();
    Scale scale = helper.getScale("C", "Ionian");
    ViewFactory modelFactory = new RomanNumeralAnalyticViewFactory();
    ViewTable modelTable = modelFactory.createView(new ViewQuery(scale));
    OutputFormFactory viewFactory = new TabularTextOutputFormFactory();
    OutputForm view = viewFactory.renderView(modelTable);
    
    System.out.println(view.toString());
    
    
    ChordHelper chordHelper = ChordHelper.getInstance();
    Chord chord = chordHelper.getChord("G", "major");
    modelFactory = new GuitarViewFactory();
    modelTable = modelFactory.createView(new ViewQuery(chord));
    viewFactory = new GridTextOutputFormFactory();
    view = viewFactory.renderView(modelTable);
    
    System.out.println(view.toString());
  }


}
