package com.gkaraffa.guarneri.view;

import java.util.List;

import com.gkaraffa.guarneri.analysis.AnalyticCell;
import com.gkaraffa.guarneri.analysis.HeaderCell;
import com.gkaraffa.guarneri.analysis.Analytic;

public class TextAnalyticViewFactory extends AnalyticViewFactory {
  @Override
  public View renderView(Analytic analytic) {
    StringBuilder sB = new StringBuilder();
    int[] columnMaxs = determineColumnMaxs(analytic);
    int fieldSpace = determineFieldSpace(columnMaxs);

    sB.append(this.renderHeader(columnMaxs, fieldSpace, analytic));
    sB.append(this.renderBody(columnMaxs, fieldSpace, analytic));
    sB.append(this.renderFoot(fieldSpace));
    sB.append("\n");

    String viewString = sB.toString();
    View outputView = new View(viewString, viewString.getBytes());

    return outputView;
  }

  private int[] determineColumnMaxs(Analytic analytic) {
    int numCols = analytic.getColumnCount();
    int[] columnMaxs = new int[numCols];

    for (int index = 0; index < numCols; index++) {
      int maxLen = 0;

      // check all rows
      List<AnalyticCell> analyticCells = analytic.getAnalyticColumn(index);
      for (AnalyticCell analyticCell : analyticCells) {
        int currentLength = analyticCell.getAnalyticText().length();
        if (currentLength > maxLen) {
          maxLen = currentLength;
        }
      }

      // check header
      int headerLength = analytic.getHeaderRow().get(index).getHeaderText().length();
      if (headerLength > maxLen) {
        maxLen = headerLength;
      }

      columnMaxs[index] = maxLen;
    }

    return columnMaxs;
  }

  private int determineFieldSpace(int[] columnMaxs) {
    int fieldCount = columnMaxs.length;
    int fieldSpace = fieldCount + 1;

    for (int columnMax : columnMaxs) {
      fieldSpace = fieldSpace + columnMax + 2;
    }

    return fieldSpace;
  }

  private String renderHeader(int[] columnMaxs, int fieldSpace, Analytic analytic) {
    StringBuilder sB = new StringBuilder();

    sB.append(renderBar(fieldSpace) + "\n");
    sB.append(renderTitles(columnMaxs, analytic) + "\n");
    sB.append(renderBar(fieldSpace) + "\n");

    return sB.toString();
  }

  private String renderBody(int[] columnMaxs, int fieldSpace, Analytic analytic) {
    StringBuilder sB = new StringBuilder();
    int numRows = analytic.getRowCount();

    for (int index = 0; index < numRows; index++) {
      List<AnalyticCell> analyticCells = analytic.getAnalyticRow(index);
      int columnCount = 0;

      for (AnalyticCell analyticCell : analyticCells) {
        sB.append("| ");
        sB.append(String.format(createFormatString(columnMaxs[columnCount]), analyticCell));
        sB.append(" ");
        columnCount++;
      }
      sB.append("|\n");
    }

    return sB.toString();
  }

  private String renderFoot(int fieldSpace) {
    return (renderBar(fieldSpace) + "\n");
  }

  private String renderBar(int fieldSpace) {
    StringBuilder sB = new StringBuilder();

    for (int index = 0; index < fieldSpace; index++) {
      sB.append('-');
    }

    return sB.toString();
  }

  private String renderTitles(int[] columnMaxs, Analytic analytic) {
    List<HeaderCell> headerCells = analytic.getHeaderRow();
    int numCols = analytic.getColumnCount();
    StringBuilder sB = new StringBuilder();

    for (int index = 0; index < numCols; index++) {
      sB.append("| ");
      sB.append(String.format(createFormatString(columnMaxs[index]), headerCells.get(index)));
      sB.append(" ");
    }

    sB.append("|");

    return sB.toString();
  }

  private String createFormatString(int columnMax) {
    StringBuilder sB = new StringBuilder();

    sB.append("%-");
    sB.append(columnMax);
    sB.append(".");
    sB.append(columnMax);
    sB.append("s");

    return sB.toString();
  }
}
