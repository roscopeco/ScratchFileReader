package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

import com.roscopeco.scratch.io.ObjectTable;


public class WatcherMorph extends AlignmentMorph {
  protected static final int FIRST_SUBCLASS_FIELD = AlignmentMorph.FIRST_SUBCLASS_FIELD + 10;

  public WatcherMorph(ObjectTable table, DataInputStream dis)
      throws IOException, UnsupportedTypeException {
    super(table, dis);
    logln("==> WatcherMorph");
  }
  
  public ScratchObject titleMorph() {
    guardResolved();
    return fields[AlignmentMorph.FIRST_SUBCLASS_FIELD];
  }

  public ScratchObject readout() {
    guardResolved();
    return fields[AlignmentMorph.FIRST_SUBCLASS_FIELD + 1];
  }

  public ScratchObject readoutFrame() {
    guardResolved();
    return fields[AlignmentMorph.FIRST_SUBCLASS_FIELD + 2];
  }

  public ScratchObject scratchSlider() {
    guardResolved();
    return fields[AlignmentMorph.FIRST_SUBCLASS_FIELD + 3];
  }

  public ScratchObject watcher() {
    guardResolved();
    return fields[AlignmentMorph.FIRST_SUBCLASS_FIELD + 4];
  }

  public ScratchObject isSpriteSpecific() {
    guardResolved();
    return fields[AlignmentMorph.FIRST_SUBCLASS_FIELD + 5];
  }

  public ScratchObject unused() {
    guardResolved();
    return fields[AlignmentMorph.FIRST_SUBCLASS_FIELD + 6];
  }

  public ScratchObject sliderMin() {
    guardResolved();
    return fields[AlignmentMorph.FIRST_SUBCLASS_FIELD + 7];
  }

  public ScratchObject sliderMax() {
    guardResolved();
    return fields[AlignmentMorph.FIRST_SUBCLASS_FIELD + 8];
  }

  public ScratchObject isLarge() {
    guardResolved();
    return fields[AlignmentMorph.FIRST_SUBCLASS_FIELD + 9];
  }
}
