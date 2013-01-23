package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.IdentityHashMap;

import com.roscopeco.scratch.io.ObjectTable;


public class IdentityDictionary extends Dictionary {
  public IdentityDictionary(ObjectTable table, DataInputStream dis)
      throws IOException, UnsupportedTypeException {
    super(new IdentityHashMap<ScratchObject, ScratchObject>(), table, dis);
    logln("----> Is an IdentityDictionary");
  }
}
