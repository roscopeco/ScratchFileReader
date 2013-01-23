package com.roscopeco.scratch.io.objects;

import com.roscopeco.scratch.io.ScratchFormatException;

public class UnsupportedTypeException extends ScratchFormatException {
  private static final long serialVersionUID = -8047750401388389894L;
  private final int clsId;

  public UnsupportedTypeException(int clsId) {
    super(String.format("Unsupported class id: 0x%02x", clsId));
    this.clsId = clsId;
  }
  
  public int getClsId() {
    return clsId;
  }
}
