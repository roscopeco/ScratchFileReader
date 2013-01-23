package com.roscopeco.scratch.io.objects;

public class True extends ScratchBool {
  public static final True instance = new True();
  
  private True() {}

  @Override
  protected boolean value() {
    return true;
  } 
}
