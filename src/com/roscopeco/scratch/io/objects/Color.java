package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;

public class Color extends ScratchObject {
  private final int color;    /* 2 bit pad; 10 bit r; 10 bit g; 10 bit b */
  
  Color(DataInputStream dis) throws IOException {
    color = dis.readInt();
    logf("Color is: 0x%08x\n", color);
  }
  
  public int color() {
    return color;
  }  
  
  public int red() {
    return (color >> 22) & 0xFF;
  }
  
  public int green() {
    return (color >> 12) & 0xFF;
  }
  
  public int blue() {
    return (color >> 2) & 0xFF;
  }
  
  public int alpha() {
    return 255;
  }
}
