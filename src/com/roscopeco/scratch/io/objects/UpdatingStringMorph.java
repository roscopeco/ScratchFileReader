package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

import com.roscopeco.scratch.io.ObjectTable;


 // TODO adds nothing - merge with StringMorph?
public class UpdatingStringMorph extends StringMorph {
  protected static final int FIRST_SUBCLASS_FIELD = StringMorph.FIRST_SUBCLASS_FIELD;

  public UpdatingStringMorph(ObjectTable table, DataInputStream dis)
      throws IOException, UnsupportedTypeException {
    super(table, dis);
    logln("--> UPDATING STRING MORPH\n");
  }

}
