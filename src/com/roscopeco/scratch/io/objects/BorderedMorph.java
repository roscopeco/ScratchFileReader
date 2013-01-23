package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

import com.roscopeco.scratch.io.ObjectTable;


public class BorderedMorph extends Morph {
  protected static final int FIRST_SUBCLASS_FIELD = Morph.FIRST_SUBCLASS_FIELD + 2;
  
  public BorderedMorph(ObjectTable table, DataInputStream dis)
      throws IOException, UnsupportedTypeException {
    super(table, dis);
  }
  
  public ScratchObject borderWidth() {
    guardResolved();
    return fields[Morph.FIRST_SUBCLASS_FIELD];
  }

  public ScratchObject borderColor() {
    guardResolved();
    return fields[Morph.FIRST_SUBCLASS_FIELD + 1];
  }
}
