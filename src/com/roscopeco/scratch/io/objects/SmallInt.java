package com.roscopeco.scratch.io.objects;

public abstract class SmallInt extends ScratchObject {
  public abstract int value();
  
  @Override
  public String toString() {
    return "" + value();
  }
}
