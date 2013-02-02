package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

import com.roscopeco.scratch.io.ObjectTable;


public class Rectangle extends ScratchObject {
  private ScratchObject x1, y1, x2, y2;

  public Rectangle(ObjectTable table, DataInputStream dis)
  throws IOException, UnsupportedTypeException {
    logln("Reading rectangle");
    
    x1 = ScratchObject.read(table, dis);
    y1 = ScratchObject.read(table, dis);
    x2 = ScratchObject.read(table, dis);
    y2 = ScratchObject.read(table, dis);
    
    logln("Read rectangle");
  }
  
  public Numeric x1() {
    guardResolved();
    return (Numeric)x1;
  }

  public Numeric y1() {
    guardResolved();
    return (Numeric)y1;
  }

  public Numeric x2() {
    guardResolved();
    return (Numeric)x2;
  }

  public Numeric y2() {
    guardResolved();
    return (Numeric)y2;
  }

  @Override
  public ScratchObject resolve(ObjectTable table) {
    super.resolve(table);
    x1 = x1.resolve(table);
    y1 = y1.resolve(table);
    x2 = x2.resolve(table);
    y2 = y2.resolve(table);
    return this;
  }
}
