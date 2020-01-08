package com.gkaraffa.guarneri.view;

public interface ViewFactory {
  public ViewTable createView();
  public ViewTable createView(ViewQuery viewQuery);
}
