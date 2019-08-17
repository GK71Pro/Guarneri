package com.gkaraffa.guarneri.view;

import com.gkaraffa.guarneri.analysis.Analytic;

public abstract class AnalyticViewFactory {
  public abstract View renderView(Analytic analytic);
}
