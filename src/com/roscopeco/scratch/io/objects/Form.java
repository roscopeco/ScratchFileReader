package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

import com.roscopeco.scratch.io.ObjectTable;


public class Form extends ScratchObject {
  private ScratchObject width, height, depth, privOfs, bitmap;
  
  Form(ObjectTable table, DataInputStream dis) throws IOException, UnsupportedTypeException {
    width = ScratchObject.read(table, dis);
    height = ScratchObject.read(table, dis);
    depth = ScratchObject.read(table, dis);
    privOfs = ScratchObject.read(table, dis);
    bitmap = ScratchObject.read(table, dis);
    
    logf("Form: %sx%s; %sbpp\n", width, height, depth);
  }
  
  @Override
  public ScratchObject resolve(ObjectTable table) {
    super.resolve(table);
    width = width.resolve(table);
    height = height.resolve(table);
    depth = depth.resolve(table);
    privOfs = privOfs.resolve(table);
    bitmap = bitmap.resolve(table);
    return this;
  }

  public SmallInt width() {
    guardResolved();
    return (SmallInt)width;
  }

  public SmallInt height() {
    guardResolved();
    return (SmallInt)height;
  }

  public SmallInt depth() {
    guardResolved();
    return (SmallInt)depth;
  }

  public ScratchObject privOfs() {
    guardResolved();
    return privOfs;
  }

  public ByteArray bitmap() {
    guardResolved();
    return (ByteArray)bitmap;
  }
}
