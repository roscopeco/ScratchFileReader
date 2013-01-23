package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

import com.roscopeco.scratch.io.ObjectTable;


public class AlignmentMorph extends RectangleMorph {
  protected static final int FIRST_SUBCLASS_FIELD = RectangleMorph.FIRST_SUBCLASS_FIELD + 5;
  
  public AlignmentMorph(ObjectTable table, DataInputStream dis)
      throws IOException, UnsupportedTypeException {
    super(table, dis);
  }
  
  public ScratchObject orientation() {
    guardResolved();
    return fields[RectangleMorph.FIRST_SUBCLASS_FIELD];
  }

  public ScratchObject centering() {
    guardResolved();
    return fields[RectangleMorph.FIRST_SUBCLASS_FIELD + 1];
  }

  public ScratchObject hResizing() {
    guardResolved();
    return fields[RectangleMorph.FIRST_SUBCLASS_FIELD + 2];
  }

  public ScratchObject vResizing() {
    guardResolved();
    return fields[RectangleMorph.FIRST_SUBCLASS_FIELD + 3];
  }

  public ScratchObject inset() {
    guardResolved();
    return fields[RectangleMorph.FIRST_SUBCLASS_FIELD + 4];
  }
}
