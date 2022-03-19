package com.gkaraffa.guarneri.experimental;

import java.util.ArrayList;

import com.gkaraffa.cremona.theoretical.TonalSpectrum;
import com.gkaraffa.cremona.theoretical.Tone;
import com.gkaraffa.cremona.theoretical.ToneCollection;

public class Keyboard {
  private final int numberOfKeys;
  private final Tone startingTone;
  private final ArrayList<Key> keyList = new ArrayList<Key>();

  public Keyboard() {
    this.numberOfKeys = 88;
    this.startingTone = Tone.A;
    buildInstrument();
  }

  public Keyboard(int numberOfKeys, Tone startingTone) {
    this.numberOfKeys = numberOfKeys;
    this.startingTone = startingTone;
    buildInstrument();
  }

  @Override
  public String toString() {
    StringBuilder builderEbony = new StringBuilder("|");
    StringBuilder builderIvory = new StringBuilder("|");
    String spacer = "     ";

    for (Key key : keyList) {
      if (key.ivory) {
        builderEbony.append(spacer);
        builderIvory.append("  " + key.tone.getText() + "  ");
      }
      else {
        builderEbony.append(key.tone.getText());
        builderIvory.append(spacer);
      }
      builderIvory.append("|");
      builderEbony.append("|");
    }

    return (builderEbony.toString() + "\n" + builderIvory.toString());
  }

  public String toCSV() {
    StringBuilder builderEbony = new StringBuilder();
    StringBuilder builderIvory = new StringBuilder();

    for (Key key : keyList) {
      if (key.ivory) {
        builderIvory.append(key.tone.getText());
      }
      else {
        builderEbony.append(key.tone.getText());
      }
      builderIvory.append(",");
      builderEbony.append(",");
    }

    builderEbony.deleteCharAt(builderEbony.length() - 1);

    return (builderEbony.toString() + "\n" + builderIvory.toString() + "\n");
  }

  private void buildInstrument() {
    Tone tone = startingTone;
    keyList.add(toneToKey(tone));

    for (int i = 1; i <= numberOfKeys - 1; i++) {
      tone = TonalSpectrum.traverseDistance(tone, 1);
      keyList.add(toneToKey(tone));
    }
  }

  private Key toneToKey(Tone tone) {
    Key key = new Key();
    key.tone = tone;

    if (tone.getText().length() > 1) {
      key.ivory = false;
    }
    else {
      key.ivory = true;
    }

    return (key);
  }

  class Key {
    public Tone tone;
    public boolean ivory;
  }
}
