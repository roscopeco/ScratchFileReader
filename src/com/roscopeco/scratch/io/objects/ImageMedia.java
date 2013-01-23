package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

import com.roscopeco.scratch.io.ObjectTable;


public class ImageMedia extends ScratchMedia {
  protected static final int FIRST_SUBCLASS_FIELD = ScratchMedia.FIRST_SUBCLASS_FIELD + 5;
  
  public ImageMedia(ObjectTable table, DataInputStream dis) throws IOException,
      UnsupportedTypeException {
    super(table, dis);
  }
  
  public Form form() {
    guardResolved();
    return (Form)fields[ScratchMedia.FIRST_SUBCLASS_FIELD];
  }

  public Point rotationCenter() {
    guardResolved();
    return (Point)fields[ScratchMedia.FIRST_SUBCLASS_FIELD + 1];
  }

  // TODO actual type
  public ScratchObject textBox() {
    guardResolved();
    return fields[ScratchMedia.FIRST_SUBCLASS_FIELD + 2];
  }

  // TODO actual type (probably bytearray?)
  public ScratchObject jpegBytes() {
    guardResolved();
    return fields[ScratchMedia.FIRST_SUBCLASS_FIELD + 3];
  }

  public Form compositeForm() {
    guardResolved();
    return (Form)fields[ScratchMedia.FIRST_SUBCLASS_FIELD + 4];
  }
}
