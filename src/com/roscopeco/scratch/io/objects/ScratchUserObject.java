package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

import com.roscopeco.scratch.io.ObjectTable;


public abstract class ScratchUserObject extends ScratchObject {
  protected static final int FIRST_SUBCLASS_FIELD = 0;
  
  protected int version;
  protected ScratchObject[] fields;
  
  protected ScratchUserObject(ObjectTable table, DataInputStream dis)
  throws IOException, UnsupportedTypeException {
    version = dis.readUnsignedByte();
    int fieldCount = dis.readUnsignedByte();
    
    logf("==> Reading user obj; Ver %d; %d field(s)\n", version, fieldCount);

    fields = new ScratchObject[fieldCount];
    
    for (int i = 0; i < fieldCount; i++) {
      fields[i] = ScratchObject.read(table, dis);
    }
  }
  
  @Override
  public ScratchObject resolve(ObjectTable table) {
    if (!isResolved()) {
      super.resolve(table);
      for (int i = 0; i < fields.length; i++) {
        fields[i] = fields[i].resolve(table);
      }
    }
    return this;
  }
}
