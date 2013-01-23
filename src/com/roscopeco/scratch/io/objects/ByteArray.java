package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

public class ByteArray extends ScratchObject {
  protected final byte[] bytes;
  
  /* for subclass use */
  ByteArray(byte[] bytes) {
    this.bytes = bytes;
  }
  
  ByteArray(DataInputStream dis) throws IOException {
    int size = dis.readInt();
    bytes = new byte[size];
    dis.read(bytes);
    
    logf("Read ByteArray: %d byte(s)\n", size);
  }
  
  public byte[] bytes() {
    return bytes;
  }
  
  public int size() {
    return bytes.length;
  }
  
  @Override
  public String toString() {
    return String.format("ByteArray[%d bytes]", bytes.length);
  }
}
