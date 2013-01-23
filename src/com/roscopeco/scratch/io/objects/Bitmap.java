package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

public class Bitmap extends ByteArray {
  
  Bitmap(DataInputStream dis) throws IOException {
    super(new byte[dis.readInt() * 4]);
    dis.read(bytes);    
    logf("Read Bitmap: %d byte(s)\n", bytes.length);
  }
  
  @Override
  public String toString() {
    return String.format("Bitmap[%d bytes]", bytes.length);
  }
}
