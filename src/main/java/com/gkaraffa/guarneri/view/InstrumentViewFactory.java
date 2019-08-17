package com.gkaraffa.guarneri.view;

import com.gkaraffa.cremona.common.PitchCollection;
import com.gkaraffa.cremona.theoretical.ToneCollection;
import com.gkaraffa.guarneri.instrument.InstrumentModel;

public abstract class InstrumentViewFactory {
  public abstract View renderInstrumentView(InstrumentModel instrumentModel);
  public abstract View renderFilteredInstrumentView(InstrumentModel instrumentModel, ToneCollection toneCollection);
  public abstract View renderFilteredInstrumentView(InstrumentModel instrumentModel, PitchCollection pitchCollection);
}
