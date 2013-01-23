package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

import com.roscopeco.scratch.io.ObjectTable;

public class Morph extends ScratchUserObject {
  protected static final int FIRST_SUBCLASS_FIELD = ScratchUserObject.FIRST_SUBCLASS_FIELD + 6;
  
  Morph(ObjectTable table, DataInputStream dis) throws IOException, UnsupportedTypeException {
    super(table, dis);
  }
  
  public Rectangle bounds() {
    guardResolved();
    return (Rectangle)fields[ScratchUserObject.FIRST_SUBCLASS_FIELD];
  }

  // TODO proper type
  public ScratchObject owner() {
    guardResolved();
    return fields[ScratchUserObject.FIRST_SUBCLASS_FIELD + 1];
  }

  @SuppressWarnings("unchecked")
  public Array<ScratchObject> submorph() {
    guardResolved();
    return (Array<ScratchObject>)fields[ScratchUserObject.FIRST_SUBCLASS_FIELD + 2];
  }

  public Color color() {
    guardResolved();
    return (Color)fields[ScratchUserObject.FIRST_SUBCLASS_FIELD + 3];
  }

  public SmallInt flags() {
    guardResolved();
    return (SmallInt)fields[ScratchUserObject.FIRST_SUBCLASS_FIELD + 4];
  }

  // TODO proper type (probably Dictionary?)
  public ScratchObject properties() {
    guardResolved();
    return fields[ScratchUserObject.FIRST_SUBCLASS_FIELD + 5];
  }
}
