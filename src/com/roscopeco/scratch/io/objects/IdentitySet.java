package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

import com.roscopeco.scratch.io.ObjectTable;


public class IdentitySet<T extends ScratchObject> extends Set<T> {

  @SuppressWarnings("unchecked")
  public IdentitySet(ObjectTable table, DataInputStream dis)
      throws IOException, UnsupportedTypeException {
    super(new IdentitySetImpl(), table, dis);
    logln("----> Is an IdentitySet");
  }
}
