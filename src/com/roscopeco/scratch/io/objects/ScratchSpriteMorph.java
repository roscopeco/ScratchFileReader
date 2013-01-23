package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

import com.roscopeco.scratch.io.ObjectTable;


public class ScratchSpriteMorph extends ScriptableScratchMorph {
  protected static final int FIRST_SUBCLASS_FIELD = ScriptableScratchMorph.FIRST_SUBCLASS_FIELD + 9;
  
  public ScratchSpriteMorph(ObjectTable table, DataInputStream dis)
      throws IOException, UnsupportedTypeException {
    super(table, dis);
  }
  
  public SmallInt visibility() {
    guardResolved();
    return (SmallInt)fields[ScriptableScratchMorph.FIRST_SUBCLASS_FIELD];
  }

  public Point scalePoint() {
    guardResolved();
    return (Point)fields[ScriptableScratchMorph.FIRST_SUBCLASS_FIELD + 1];
  }

  public Float rotationDegrees() {
    guardResolved();
    return (Float)fields[ScriptableScratchMorph.FIRST_SUBCLASS_FIELD + 2];
  }

  public Symbol rotationStyle() {
    guardResolved();
    return (Symbol)fields[ScriptableScratchMorph.FIRST_SUBCLASS_FIELD + 3];
  }

  public SmallInt volume() {
    guardResolved();
    return (SmallInt)fields[ScriptableScratchMorph.FIRST_SUBCLASS_FIELD + 4];
  }

  public SmallInt tempoBPM() {
    guardResolved();
    return (SmallInt)fields[ScriptableScratchMorph.FIRST_SUBCLASS_FIELD + 5];
  }

  public ScratchBool draggable() {
    guardResolved();
    return (ScratchBool)fields[ScriptableScratchMorph.FIRST_SUBCLASS_FIELD + 6];
  }

  public Dictionary sceneStates() {
    guardResolved();
    return (Dictionary)fields[ScriptableScratchMorph.FIRST_SUBCLASS_FIELD + 7];
  }

  public Dictionary lists() {
    guardResolved();
    return (Dictionary)fields[ScriptableScratchMorph.FIRST_SUBCLASS_FIELD + 8];
  }
}
