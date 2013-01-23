package com.roscopeco.scratch.io.objects;

public class False extends ScratchBool {
  public static final False instance = new False();
  
  private False() {}

  @Override
  protected boolean value() {
    return false;
  } 
}
