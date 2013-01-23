package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

import com.roscopeco.scratch.io.ObjectTable;


public class Array<T extends ScratchObject> extends OrderedCollection<T> {
  Array(ObjectTable table, DataInputStream dis) throws IOException, UnsupportedTypeException {
    super(table, dis);
    logln("----> Is an Array");
  }
}
