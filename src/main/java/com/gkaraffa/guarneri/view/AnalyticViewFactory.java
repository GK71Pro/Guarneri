package com.gkaraffa.guarneri.view;

import com.gkaraffa.guarneri.model.analytic.Analytic;

public abstract class AnalyticViewFactory {
  public abstract View renderView(Analytic analytic);
}
