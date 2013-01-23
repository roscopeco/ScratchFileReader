package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

import com.roscopeco.scratch.io.ObjectTable;


public class Reference extends ScratchObject {
  private final int refIdx;

  Reference(DataInputStream dis) throws IOException {
    //byte[] buf = new byte[3];
    //dis.read(buf);
    int[] buf = new int[3];
    buf[0] = dis.readUnsignedByte();
    buf[1] = dis.readUnsignedByte();
    buf[2] = dis.readUnsignedByte();
    
    refIdx = buf[0] << 16 | buf[1] << 8 | buf[2];
    
    logf("Reference type points to entry %d\n", refIdx);
  }
  
  public int refIdx() {
    return refIdx;
  }

  @Override
  public ScratchObject resolve(ObjectTable table) {
    return table.get(refIdx);
  }
  
  @Override
  public String toString() {
    return "com.roscopeco.scratch.io.objects.Reference(->"+refIdx+")";
  }
}
