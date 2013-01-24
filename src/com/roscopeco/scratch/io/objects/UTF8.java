package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

public class UTF8 extends ScratchString {
  private final String string;
  
  UTF8(DataInputStream dis) throws IOException {
    int size = dis.readInt();
    byte[] buf = new byte[size];
    dis.read(buf);
    string = new String(buf, "UTF-8");
    logf("UTF8 string is `%s'\n", string);
  }
  
  public String string() {
    return string;
  }
  
  @Override
  public String toString() {
    return string();
  }

}
