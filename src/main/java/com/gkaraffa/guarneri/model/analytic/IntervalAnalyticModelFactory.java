package com.gkaraffa.guarneri.model.analytic;

import com.gkaraffa.cremona.theoretical.Interval;
import com.gkaraffa.cremona.theoretical.IntervalNumber;
import com.gkaraffa.cremona.theoretical.ToneCollection;
import com.gkaraffa.cremona.theoretical.chord.Chord;
import com.gkaraffa.cremona.theoretical.scale.Scale;
import com.gkaraffa.guarneri.model.ModelCell;
import com.gkaraffa.guarneri.model.ModelFactory;
import com.gkaraffa.guarneri.model.ModelTable;

public class IntervalAnalyticModelFactory extends ModelFactory {

  @Override
  public ModelTable createModel() {
    throw new UnsupportedOperationException(
        "Cannot create IntervalAnalyticModel without parameters.");
  }

  @Override
  public ModelTable createModel(Scale scale) {
    String headerArray[] = new String[] {"Abbrev", "Function", "Interval", "Tone"};
    ToneCollection toneCollection = scale.getToneCollection();
    int depth = toneCollection.getSize() + 1;
    int breadth = headerArray.length;
    ModelCell[][] modelCells = new ModelCell[depth][breadth];

    modelCells[0] = this.generateHeaders(headerArray);

    for (int rowCounter = 1; rowCounter < depth; rowCounter++) {
      int scalePosition = rowCounter - 1;
      Interval interval = scale.getIntervalPattern().getIntervalByLocation(scalePosition);
      IntervalNumber intervalNumber = interval.getIntervalNumber();

      modelCells[rowCounter][0] = new ModelCell(intervalNumber.getAbbrev());
      modelCells[rowCounter][1] = new ModelCell(intervalNumber.getDiatonicFunction());
      modelCells[rowCounter][2] = new ModelCell(interval.getText());
      modelCells[rowCounter][3] =
          new ModelCell(scale.getToneCollection().getTone(scalePosition).getText());
    }

    int columnWidths[] = this.generateColumnWidths(modelCells);

    ModelTable generatedTable = new ModelTable(modelCells, columnWidths);

    return generatedTable;
  }

  @Override
  public ModelTable createModel(Chord chord) {
    throw new UnsupportedOperationException(
        "Cannot create IntervalAnalyticModel with chord as a parameter.");
  }

}