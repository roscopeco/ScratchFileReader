package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

public class TranslucentColor extends Color {
  private final byte alpha;
  
  public TranslucentColor(DataInputStream dis) throws IOException {
    super(dis);
    alpha = dis.readByte();
    logf("  --> Alpha is %d\n", alpha);
  }
  
  public byte alpha() {
    return alpha;
  }
}
