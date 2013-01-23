package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;

public class LargeNegativeInteger extends ScratchObject {
  private final BigInteger value;

  public LargeNegativeInteger(DataInputStream dis) throws IOException {
    int size = dis.readUnsignedShort();
    logf("LargePositiveInteger: Size %d byte(s)\n", size);
    byte[] buf = new byte[size];
    dis.read(buf);
    value = new BigInteger(buf).negate();
    logf("LargePositiveInteger: value = %s\n", value.toString());
  }
  
  public BigInteger value() {
    return value;
  }
}
