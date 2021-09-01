package com.gkaraffa.guarneri.view.analytic.chord;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.gkaraffa.cremona.theoretical.Tone;
import com.gkaraffa.cremona.theoretical.ToneGroupObject;

public class ToneAnalyticFacet {
  private Tone parentTone;
  private List<Tone> childTones;
  private List<ResultIntervalGroup> resultIntervalGroups;

  static public List<ToneAnalyticFacet> createToneAnalyticFacets(ToneGroupObject toneGroupObject){
    List<Tone> tones = toneGroupObject.getToneCollection().getListCopy();

    // remove duplicates
    Set<Tone> toneSet = new TreeSet<>();

    toneSet.addAll(tones);
    tones.clear();

    for(Tone tone: toneSet) {
      tones.add(tone);
    }

    int toneCount = tones.size();
    List<ToneAnalyticFacet> toneAnalyticFacets = new ArrayList<>(toneCount);

    for (int index = 0; index < toneCount; index++) {
      List<Tone> childTones = new ArrayList<>(toneCount - 1);

      int j = index + 1;
      while(true) {
        if (j > (toneCount - 1)) {
          j -= toneCount;
        }

        if (j == index) {
          break;
        }

        childTones.add(tones.get(j));
        j++;
      }

      toneAnalyticFacets.add(createToneAnalyticFacet(tones.get(index), childTones));
    }

    return toneAnalyticFacets;
  }

  static public ToneAnalyticFacet createToneAnalyticFacet(Tone parentTone, List<Tone> childTones) {
    int childCount = childTones.size();
    int childExtent = childCount - 1;
    List<ResultIntervalGroup> resultIntervalGroups = new ArrayList<>(childCount);

    for (int index = 0; index <= childExtent; index++) {
      resultIntervalGroups.add(ResultIntervalGroup.generateResultIntervalGroup(parentTone, childTones.get(index)));
    }

    return new ToneAnalyticFacet(parentTone, childTones, resultIntervalGroups);
  }

  public ToneAnalyticFacet(Tone parentTone, List<Tone> childTones, List<ResultIntervalGroup> resultIntervalGroups) {
    this.parentTone = parentTone;
    this.childTones = childTones;
    this.resultIntervalGroups = resultIntervalGroups;
  }


  public Tone getParentTone() {
    return parentTone;
  }


  public List<Tone> getChildTones() {
    return childTones;
  }

  public List<ResultIntervalGroup> getIntervalAnalyticFacets() {
    return resultIntervalGroups;
  }

  @Override
  public String toString() {
    StringBuilder sBuilder = new StringBuilder();

    sBuilder.append("Facet: " + parentTone + "(");
    for (Tone childTone : childTones) {
      sBuilder.append(childTone + ",");
    }

    sBuilder.setLength(sBuilder.length() - 1);
    sBuilder.append(") \n");
    sBuilder.append("Parent: " + parentTone + " \n");


    for (int index = 0; index < resultIntervalGroups.size(); index++) {
      sBuilder.append("Child: " + childTones.get(index) + " \n");
      sBuilder.append(resultIntervalGroups.get(index) + "\n");
    }

    return sBuilder.toString();
  }
}
