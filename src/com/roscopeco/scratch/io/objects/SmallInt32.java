package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

public class SmallInt32 extends SmallInt {
  private final int value;
  
  SmallInt32(DataInputStream dis) throws IOException {
    value = dis.readInt();
    logf("IMMEDIATE: SmallInt32(%d)\n", value);
  }
  
  public int value() {
    return value;
  }  
}
