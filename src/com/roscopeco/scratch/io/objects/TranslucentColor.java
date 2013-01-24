package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

public class TranslucentColor extends Color {
  private final int alpha;
  
  public TranslucentColor(DataInputStream dis) throws IOException {
    super(dis);
    alpha = dis.readUnsignedByte();
    logf("  --> Alpha is %d\n", alpha);
  }
  
  @Override
  public int alpha() {
    return alpha;
  }
}
