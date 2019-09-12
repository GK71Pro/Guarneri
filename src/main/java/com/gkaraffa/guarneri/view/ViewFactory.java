package com.gkaraffa.guarneri.view;

import com.gkaraffa.guarneri.model.ModelTable;

public abstract class ViewFactory {
  public abstract View renderView(ModelTable modelTable);
}
