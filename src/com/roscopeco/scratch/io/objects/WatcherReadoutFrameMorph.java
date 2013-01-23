package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

import com.roscopeco.scratch.io.ObjectTable;


// TODO this is allegedly unused, according to the docs, but has been seen in the wild?
public class WatcherReadoutFrameMorph extends BorderedMorph {
  protected static final int FIRST_SUBCLASS_FIELD = BorderedMorph.FIRST_SUBCLASS_FIELD;
  
  public WatcherReadoutFrameMorph(ObjectTable table, DataInputStream dis)
      throws IOException, UnsupportedTypeException {
    super(table, dis);
    logln("!!! WatcherReadoutFrameMorph (unused?)");
  }

}
