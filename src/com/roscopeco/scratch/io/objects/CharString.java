package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

public class CharString extends ScratchString {
  private final String string;
  
  CharString(String string) {
    this.string = string;
  }
  
  CharString(DataInputStream dis) throws IOException {
    int len = dis.readInt();
    byte[] buf = new byte[len];
    dis.read(buf);
    string = new String(buf);
    
    logln("String value: `" + string + "'");
  }
  
  public String string() {
    return string;
  }
  
  @Override
  public String toString() {
    return string();
  }
}
