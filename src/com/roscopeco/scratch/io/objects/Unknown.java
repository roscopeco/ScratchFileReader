package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

import com.roscopeco.scratch.io.ObjectTable;


public final class Unknown extends ScratchUserObject {
  final int clsId;
  
  Unknown(int clsId, ObjectTable table, DataInputStream dis) throws IOException,
      UnsupportedTypeException {
    super(table, dis);
    warnf("WARN: Creating unknown object (classId 0x%02x)\n", clsId);;
    this.clsId = clsId;
  }
  
  public int classId() {
    return clsId;
  }
  
  public int fieldCount() {
    guardResolved();
    return fields.length;
  }
  
  public ScratchObject get(int index) {
    guardResolved();
    return fields[index];
  }
}
