package com.gkaraffa.guarneri.view;

import com.gkaraffa.cremona.theoretical.ToneGroupObject;

public class ViewQuery {
  private ToneGroupObject toneGroupObject;

  public ViewQuery(ToneGroupObject toneGroupObject) {
    this.toneGroupObject = toneGroupObject;
  }

  public ToneGroupObject getToneGroupObject() {
    return toneGroupObject;
  }
}
