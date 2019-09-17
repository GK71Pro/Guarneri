package com.gkaraffa.guarneri.outputform;

import com.gkaraffa.guarneri.view.ViewTable;

public abstract class OutputFormFactory {
  public abstract OutputForm renderView(ViewTable modelTable);
}
