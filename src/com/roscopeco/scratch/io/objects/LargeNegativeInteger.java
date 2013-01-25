package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

public class LargeNegativeInteger extends LargeInteger {
  public LargeNegativeInteger(DataInputStream dis) throws IOException {
    super(readBigInteger(dis, true));
    logf("LargeNegativeInteger: value = %s\n", value().toString());
  }
}
