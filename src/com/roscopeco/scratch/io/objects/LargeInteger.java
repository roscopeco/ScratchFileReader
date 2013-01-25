package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;

public abstract class LargeInteger extends Numeric {
  protected static BigInteger readBigInteger(DataInputStream dis, boolean negate) 
  throws IOException {
    int size = dis.readUnsignedShort();
    logf("LargeInteger: Size %d byte(s)\n", size);
    byte[] buf = new byte[size];
    dis.read(buf);
    if (!negate) {
      return new BigInteger(buf);
    } else {
      return new BigInteger(buf).negate();
    }
  }
  
  private final BigInteger value;
  
  protected LargeInteger(BigInteger value) {
    this.value = value;
  }
  
  public BigInteger value() {
    return value;
  }

  @Override
  public int intValue() {
    return value.intValue();
  }
  
  @Override
  public long longValue() {
    return value.longValue();
  }

  @Override
  public double floatValue() {
    return value.doubleValue();
  }
}
