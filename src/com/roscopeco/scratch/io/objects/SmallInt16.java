package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

public class SmallInt16 extends SmallInt {
  private final short value;
  
  SmallInt16(DataInputStream dis) throws IOException {
    value = dis.readShort();
    logf("IMMEDIATE: SmallInt16(%d)\n", value);
  }
  
  public int value() {
    return value;
  }
}
