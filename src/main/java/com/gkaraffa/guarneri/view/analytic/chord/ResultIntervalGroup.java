package com.gkaraffa.guarneri.view.analytic.chord;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import com.gkaraffa.cremona.theoretical.Interval;
import com.gkaraffa.cremona.theoretical.Quality;
import com.gkaraffa.cremona.theoretical.TonalSpectrum;
import com.gkaraffa.cremona.theoretical.Tone;

public class ResultIntervalGroup {
  private List<Interval> intervals;

  static public ResultIntervalGroup generateResultIntervalGroup(Tone parentTone, Tone childTone) {
    int distance = TonalSpectrum.measureDistance(parentTone, childTone);
    List<Interval> intervals = Interval.halfStepsToIntervalList(distance);

    return new ResultIntervalGroup(intervals);
  }

  public ResultIntervalGroup(List<Interval> intervals) {
    this.intervals = intervals;
  }

  public List<Interval> getIntervals() {
    return intervals;
  }

  @Override
  public String toString() {
    StringBuilder sBuilder = new StringBuilder();
    Set<Quality> qualitySet = EnumSet.of(Quality.MAJOR, Quality.MINOR, Quality.PERFECT);
    
    for (Interval interval: intervals) {
      sBuilder.append("\t");
      if (qualitySet.contains(interval.getIntervalQuality())) {
        sBuilder.append("*** ");
      }
      
      sBuilder.append(interval + "\n");
    }

    return sBuilder.toString();
  }
}
