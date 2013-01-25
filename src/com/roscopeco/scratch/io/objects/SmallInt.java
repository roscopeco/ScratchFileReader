package com.roscopeco.scratch.io.objects;

public abstract class SmallInt extends Numeric {
  public abstract int value();
  
  @Override
  public String toString() {
    return "" + value();
  }

  @Override
  public int intValue() {
    return value();
  }
  
  @Override
  public long longValue() {
    return value();
  }

  @Override
  public double floatValue() {
    return new Integer(value()).doubleValue();
  }
}
