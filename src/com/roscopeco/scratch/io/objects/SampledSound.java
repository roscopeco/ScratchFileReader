package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

import com.roscopeco.scratch.io.ObjectTable;


public class SampledSound extends ScratchUserObject {
  protected static final int FIRST_SUBCLASS_FIELD = ScratchUserObject.FIRST_SUBCLASS_FIELD + 8;
  public SampledSound(ObjectTable table, DataInputStream dis)
  throws IOException, UnsupportedTypeException {
    super(table, dis);
  }

  public ScratchObject envelopes() {
    guardResolved();
    return fields[ScratchUserObject.FIRST_SUBCLASS_FIELD];
  }

  public ScratchObject scaledVol() {
    guardResolved();
    return fields[ScratchUserObject.FIRST_SUBCLASS_FIELD + 1];
  }

  public ScratchObject initialCount() {
    guardResolved();
    return fields[ScratchUserObject.FIRST_SUBCLASS_FIELD + 2];
  }

  public ScratchObject samples() {
    guardResolved();
    return fields[ScratchUserObject.FIRST_SUBCLASS_FIELD + 3];
  }

  public ScratchObject originalSamplingRate() {
    guardResolved();
    return fields[ScratchUserObject.FIRST_SUBCLASS_FIELD + 4];
  }

  public ScratchObject samplesSize() {
    guardResolved();
    return fields[ScratchUserObject.FIRST_SUBCLASS_FIELD + 5];
  }

  public ScratchObject scaledIncrement() {
    guardResolved();
    return fields[ScratchUserObject.FIRST_SUBCLASS_FIELD + 6];
  }

  public ScratchObject scaledInitialIndex() {
    guardResolved();
    return fields[ScratchUserObject.FIRST_SUBCLASS_FIELD + 7];
  }
}
