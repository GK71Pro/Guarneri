package com.gkaraffa.guarneri.common;

import com.gkaraffa.guarneri.outputform.OutputForm;
import com.gkaraffa.guarneri.outputform.OutputFormFactory;
import com.gkaraffa.guarneri.outputform.TabularTextOutputFormFactory;
import com.gkaraffa.guarneri.view.ViewCell;
import com.gkaraffa.guarneri.view.ViewTable;
import com.gkaraffa.guarneri.view.ViewTableBuilder;

public class TestPlatform {

  public TestPlatform() {}
  
  public static void main(String[] args) {
    ViewTableBuilder vtBuild = new ViewTableBuilder();
    vtBuild.insertCell(0, 0, new ViewCell("Cell 0,0"));
    vtBuild.insertCell(1, 0, new ViewCell("Cell 1,0"));
    vtBuild.insertCell(2, 0, new ViewCell("Cell 2,0"));
    vtBuild.insertCell(0, 1, new ViewCell("Cell 0,1"));
    vtBuild.insertCell(1, 1, new ViewCell("Cell 1,1"));
    vtBuild.insertCell(2, 1, new ViewCell("Cell 2,1"));
    vtBuild.insertCell(3, 2, new ViewCell("Cell 3,2"));

    ViewTable viewTable = vtBuild.compileTable();
    OutputFormFactory viewFactory = new TabularTextOutputFormFactory();
    OutputForm view = viewFactory.renderView(viewTable);
    System.out.println(view.toString());

  }

  /*
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
  */


}
