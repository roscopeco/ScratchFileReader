package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

import com.roscopeco.scratch.io.ObjectTable;


public class ScratchMedia extends ScratchUserObject {
  protected static final int FIRST_SUBCLASS_FIELD = ScratchUserObject.FIRST_SUBCLASS_FIELD + 1;
  
  public ScratchMedia(ObjectTable table, DataInputStream dis) 
  throws IOException, UnsupportedTypeException {
    super(table, dis);
  }

  public ScratchString mediaName() {
    guardResolved();
    return (ScratchString)fields[ScratchUserObject.FIRST_SUBCLASS_FIELD];
  }
}
