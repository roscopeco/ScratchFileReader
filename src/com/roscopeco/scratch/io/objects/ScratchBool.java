package com.roscopeco.scratch.io.objects;

public abstract class ScratchBool extends ScratchObject {  
  public static final ScratchBool TRUE = True.instance;
  public static final ScratchBool FALSE = False.instance;
  
  ScratchBool() { }
  protected abstract boolean value();
}
