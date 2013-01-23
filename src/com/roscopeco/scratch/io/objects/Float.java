package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

public class Float extends ScratchObject {
  final double value;
  
  Float(DataInputStream dis) throws IOException {
    value = dis.readDouble();
    logf("IMMEDIATE: Float(%f)\n", value);    
  }
  
  public double value() {
    return value;
  }
  
  @Override
  public String toString() {
    return String.format("Float(%f)", value);
  }

}
