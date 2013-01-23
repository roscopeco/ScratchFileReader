package com.roscopeco.scratch.io.objects;

public abstract class ScratchString extends ScratchObject {
  public abstract String string();
  
  public static ScratchString fromString(String s) {
    return new CharString(s);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof ScratchString) {
      return string().equals(((ScratchString)obj).string());
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return string().hashCode();
  }
}
