package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

import com.roscopeco.scratch.io.ObjectTable;


public class Point extends ScratchObject {
  private ScratchObject x, y;

  public Point(ObjectTable table, DataInputStream dis)
  throws IOException, UnsupportedTypeException {
    logln("Reading point");
    
    x = ScratchObject.read(table, dis);
    y = ScratchObject.read(table, dis);
    
    logln("Read point");
  }
  
  public ScratchObject x() {
    guardResolved();
    return x;
  }

  public ScratchObject y() {
    guardResolved();
    return y;
  }

  @Override
  public ScratchObject resolve(ObjectTable table) {
    super.resolve(table);
    x = x.resolve(table);
    y = y.resolve(table);
    return this;
  }
}
