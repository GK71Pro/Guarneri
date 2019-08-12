package com.gkaraffa.guarneri.view;

import com.gkaraffa.guarneri.analysis.TabularAnalytic;

public abstract class AnalyticViewFactory {
  public abstract AnalyticView renderView(TabularAnalytic tabularAnalytic);
}
