package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

import com.roscopeco.scratch.io.ObjectTable;


// TODO this adds no fields; could probably be removed for our purposes?
public class RectangleMorph extends BorderedMorph {
  protected static final int FIRST_SUBCLASS_FIELD = BorderedMorph.FIRST_SUBCLASS_FIELD;
  
  public RectangleMorph(ObjectTable table, DataInputStream dis)
      throws IOException, UnsupportedTypeException {
    super(table, dis);
  }
}
