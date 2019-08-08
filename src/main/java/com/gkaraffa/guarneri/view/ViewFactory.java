package com.gkaraffa.guarneri.view;

import com.gkaraffa.guarneri.analysis.TabularAnalytic;

public abstract class ViewFactory {
  public abstract View renderView(TabularAnalytic tabularAnalytic);
}
