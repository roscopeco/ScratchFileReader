package com.roscopeco.scratch.io.objects;

import com.roscopeco.scratch.io.ObjectTable;


public class Nil extends ScratchObject {
  public static final Nil instance = new Nil();
  
  private Nil() {}

  @Override
  public ScratchObject resolve(ObjectTable table) {
    return null;
  } 
}
