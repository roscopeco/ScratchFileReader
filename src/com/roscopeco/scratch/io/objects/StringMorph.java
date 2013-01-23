package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

import com.roscopeco.scratch.io.ObjectTable;


public class StringMorph extends Morph {
  protected static final int FIRST_SUBCLASS_FIELD = Morph.FIRST_SUBCLASS_FIELD + 3;

  public StringMorph(ObjectTable table, DataInputStream dis)
      throws IOException, UnsupportedTypeException {
    super(table, dis);
  }
  
  public ScratchObject fontWithSize() {
    guardResolved();
    return fields[Morph.FIRST_SUBCLASS_FIELD];
  }

  public ScratchObject emphasis() {
    guardResolved();
    return fields[Morph.FIRST_SUBCLASS_FIELD + 1];
  }

  public ScratchObject contents() {
    guardResolved();
    return fields[Morph.FIRST_SUBCLASS_FIELD + 2];
  }
}
