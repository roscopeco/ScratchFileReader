package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

import com.roscopeco.scratch.io.ObjectTable;



public class ScriptableScratchMorph extends Morph {
  protected static final int FIRST_SUBCLASS_FIELD = Morph.FIRST_SUBCLASS_FIELD + 6;
  
  public ScriptableScratchMorph(ObjectTable table, DataInputStream dis) 
  throws IOException, UnsupportedTypeException {
    super(table, dis);
  }

  public ScratchString objName() {
    guardResolved();
    return (ScratchString)fields[Morph.FIRST_SUBCLASS_FIELD];
  }

  public Dictionary vars() {
    guardResolved();
    return (Dictionary)fields[Morph.FIRST_SUBCLASS_FIELD + 1];
  }

  @SuppressWarnings("unchecked")
  public Array<ScratchObject> blocksBin() {
    guardResolved();
    return (Array<ScratchObject>)fields[Morph.FIRST_SUBCLASS_FIELD + 2];
  }

  public ScratchBool isClone() {
    guardResolved();
    return (ScratchBool)fields[Morph.FIRST_SUBCLASS_FIELD + 3];
  }

  @SuppressWarnings("unchecked")
  public OrderedCollection<ScratchObject> media() {
    guardResolved();
    return (OrderedCollection<ScratchObject>)fields[Morph.FIRST_SUBCLASS_FIELD + 4];
  }

  public ImageMedia costume() {
    guardResolved();
    return (ImageMedia)fields[Morph.FIRST_SUBCLASS_FIELD + 5];
  }
}
