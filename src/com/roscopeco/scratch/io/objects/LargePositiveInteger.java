package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

public class LargePositiveInteger extends LargeInteger {

  public LargePositiveInteger(DataInputStream dis) throws IOException {
    super(readBigInteger(dis, false));
    logf("LargePositiveInteger: value = %s\n", value().toString());
  }
  
}
