package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

import com.roscopeco.scratch.io.ObjectTable;


public class ColorForm extends Form {
  private ScratchObject colorMap;
  
  ColorForm(ObjectTable table, DataInputStream dis) throws IOException, UnsupportedTypeException {
    super(table, dis);
    
    logf("  --> Reading colorMap\n");
    colorMap = ScratchObject.read(table, dis);
    logf("  <-- Got it\n");
  }
  
  @Override
  public ScratchObject resolve(ObjectTable table) {
    colorMap = colorMap.resolve(table);
    return super.resolve(table);
  }

  public ScratchObject colorMap() {
    guardResolved();
    return colorMap;
  }
}
