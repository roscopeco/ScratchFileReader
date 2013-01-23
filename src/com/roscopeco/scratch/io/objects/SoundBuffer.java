package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

public class SoundBuffer extends ScratchObject {
  private final byte[] bytes;
  
  SoundBuffer(DataInputStream dis) throws IOException {
    int size = dis.readInt();
    bytes = new byte[size * 2];
    dis.read(bytes);
    
    logf("Read SoundBuffer: %d byte(s)\n", size * 2);
  }
  
  public byte[] bytes() {
    return bytes;
  }
  
  @Override
  public String toString() {
    return Arrays.toString(bytes);
  }
}
