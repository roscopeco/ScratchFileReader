package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

import com.roscopeco.scratch.io.ObjectTable;


public class SoundMedia extends ScratchMedia {
  protected static final int FIRST_SUBCLASS_FIELD = ScratchMedia.FIRST_SUBCLASS_FIELD + 6;

  public SoundMedia(ObjectTable table, DataInputStream dis) throws IOException,
      UnsupportedTypeException {
    super(table, dis);
  }
  
  public ScratchObject originalSound() {
    guardResolved();
    return fields[ScratchMedia.FIRST_SUBCLASS_FIELD];
  }

  public ScratchObject volume() {
    guardResolved();
    return fields[ScratchMedia.FIRST_SUBCLASS_FIELD + 1];
  }

  public ScratchObject balance() {
    guardResolved();
    return fields[ScratchMedia.FIRST_SUBCLASS_FIELD + 2];
  }

  public ScratchObject compressedSampleRate() {
    guardResolved();
    return fields[ScratchMedia.FIRST_SUBCLASS_FIELD + 3];
  }

  public ScratchObject compressedBitsPerSample() {
    guardResolved();
    return fields[ScratchMedia.FIRST_SUBCLASS_FIELD + 4];
  }

  public ScratchObject compressedData() {
    guardResolved();
    return fields[ScratchMedia.FIRST_SUBCLASS_FIELD + 5];
  }
}
