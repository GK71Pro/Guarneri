package com.gkaraffa.guarneri.instrument;

import com.gkaraffa.cremona.common.Pitch;
import com.gkaraffa.cremona.common.PitchCollection;
import com.gkaraffa.cremona.theoretical.ToneCollection;

public interface InstrumentColumn {
  public Pitch[] getColumn(int column);
  public Pitch[] getFilteredColumn(int column, ToneCollection toneFilter);
  public Pitch[] getFilteredColumn(int column, PitchCollection pitchFilter);
}
