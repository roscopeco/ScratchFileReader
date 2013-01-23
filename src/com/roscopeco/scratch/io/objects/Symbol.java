package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

// TODO this is identical to CharString... Merge them?
public class Symbol extends ScratchObject {
  private final String sym;
  
  Symbol(DataInputStream dis) throws IOException {
    int len = dis.readInt();
    byte[] buf = new byte[len];
    dis.read(buf);
    sym = new String(buf);
    
    logln("Symbol value: `:" + sym + "'");
  }
  
  public String symbol() {
    return sym;
  }
  
  @Override
  public String toString() {
    return ":" + symbol();
  }
}
