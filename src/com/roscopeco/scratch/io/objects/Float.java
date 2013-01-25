package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

public class Float extends Numeric {
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
    return String.format("%f", value);
  }

  @Override
  public int intValue() {
    return new Double(value).intValue();
  }

  @Override
  public long longValue() {
    return new Double(value).longValue();
  }
  @Override
  public double floatValue() {
    return value;
  }
}
