package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

import com.roscopeco.scratch.io.ObjectTable;


 // TODO adds nothing - merge with StringMorph? Also, allegedly unused according to docs, but has been seen in the wild.
public class MultilineStringMorph extends StringMorph {
  protected static final int FIRST_SUBCLASS_FIELD = StringMorph.FIRST_SUBCLASS_FIELD;

  public MultilineStringMorph(ObjectTable table, DataInputStream dis)
      throws IOException, UnsupportedTypeException {
    super(table, dis);
    logf("!!! MULTILINE STRING MORPH\n");
  }

}
