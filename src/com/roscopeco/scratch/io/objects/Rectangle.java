package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

import com.roscopeco.scratch.io.ObjectTable;


public class Rectangle extends ScratchObject {
  private ScratchObject x, y, w, h;

  public Rectangle(ObjectTable table, DataInputStream dis)
  throws IOException, UnsupportedTypeException {
    logln("Reading rectangle");
    
    x = ScratchObject.read(table, dis);
    y = ScratchObject.read(table, dis);
    w = ScratchObject.read(table, dis);
    h = ScratchObject.read(table, dis);
    
    logln("Read rectangle");
  }
  
  public ScratchObject x() {
    guardResolved();
    return x;
  }

  public ScratchObject y() {
    guardResolved();
    return y;
  }

  public ScratchObject w() {
    guardResolved();
    return w;
  }

  public ScratchObject h() {
    guardResolved();
    return h;
  }

  @Override
  public ScratchObject resolve(ObjectTable table) {
    super.resolve(table);
    x = x.resolve(table);
    y = y.resolve(table);
    w = w.resolve(table);
    h = h.resolve(table);
    return this;
  }
}
