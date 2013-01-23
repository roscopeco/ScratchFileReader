package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

import com.roscopeco.scratch.io.ObjectTable;


public class ScratchStageMorph extends ScriptableScratchMorph {
  protected static final int FIRST_SUBCLASS_FIELD = ScriptableScratchMorph.FIRST_SUBCLASS_FIELD + 9;
  
  ScratchStageMorph(ObjectTable table, DataInputStream dis) 
  throws IOException, UnsupportedTypeException {
    super(table, dis);
  }
  
  public Float zoom() {
    guardResolved();
    return (Float)fields[ScriptableScratchMorph.FIRST_SUBCLASS_FIELD];
  }

  public SmallInt hPan() {
    guardResolved();
    return (SmallInt)fields[ScriptableScratchMorph.FIRST_SUBCLASS_FIELD + 1];
  }

  public SmallInt vPan() {
    guardResolved();
    return (SmallInt)fields[ScriptableScratchMorph.FIRST_SUBCLASS_FIELD + 2];
  }

  // TODO actual type
  public ScratchObject obsoleteSavedState() {
    guardResolved();
    return fields[ScriptableScratchMorph.FIRST_SUBCLASS_FIELD + 3];
  }

  @SuppressWarnings("unchecked")
  public OrderedCollection<ScratchSpriteMorph> sprites() {
    guardResolved();
    return (OrderedCollection<ScratchSpriteMorph>)fields[ScriptableScratchMorph.FIRST_SUBCLASS_FIELD + 4];
  }

  public SmallInt volume() {
    guardResolved();
    return (SmallInt)fields[ScriptableScratchMorph.FIRST_SUBCLASS_FIELD + 5];
  }

  public SmallInt tempoBPM() {
    guardResolved();
    return (SmallInt)fields[ScriptableScratchMorph.FIRST_SUBCLASS_FIELD + 6];
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
