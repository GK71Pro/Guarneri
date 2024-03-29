package com.gkaraffa.guarneri.instrument;

import com.gkaraffa.cremona.common.Pitch;
import com.gkaraffa.cremona.common.PitchCollection;
import com.gkaraffa.cremona.theoretical.ToneCollection;

public interface InstrumentRow {
  public Pitch[] getRow(int row);
  public Pitch[] getFilteredRow(int row, ToneCollection toneFilter);
  public Pitch[] getFilteredRow(int row, PitchCollection pitchFilter);
}
