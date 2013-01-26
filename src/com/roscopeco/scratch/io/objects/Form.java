/* Portions Copyright (c) 2009 Massachusetts Institute of Technology */

package com.roscopeco.scratch.io.objects;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.io.ByteArrayInputStream;
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
  
  private BufferedImage imageCache;
  
  /*
   * This is broken out into a separate method so that ColorForm 
   * can override to create custom model.
   */
  protected ColorModel createImageColorModel(int depth) {
    return squeakColorMap(depth);
  }
  
  public BufferedImage getImage() {
    if (imageCache != null) {
      return imageCache;
    } else {
      guardResolved();
      
      MemoryImageSource src;
      
      int width = width().value();
      int height = height().value();
      int depth = depth().value();

      int[] pixels;
      try {
        pixels = decodePixels(bitmap().bytes);
      } catch (IOException e) { /* do nothing - should never happen */
        throw new RuntimeException(e);
      }
      
      if (depth <= 8) {
        src = new MemoryImageSource(width, height, createImageColorModel(depth), 
            rasterToByteRaster(pixels, width, height, depth),
            0, width);
      } else if (depth == 16) {
        src = new MemoryImageSource(width, height, raster16to32(pixels, width, height), 0, width);
      } else if (depth == 32) {
        src = new MemoryImageSource(width, height, rasterAddAlphaTo32(pixels), 0, width);        
      } else {
        throw new IllegalArgumentException("Unsupported bit depth: " + depth + " bpp");
      }
      
      int[] rawPix = new int[width * height];
      PixelGrabber grabber = new PixelGrabber(src, 0, 0, width, height, rawPix, 0, width);
      
      try {
        grabber.grabPixels();
      } catch (InterruptedException e) { /* do nothing */ }
      
      BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      img.getRaster().setDataElements(0,  0,  width, height, rawPix);
      return imageCache = img;
    }
  }

  /* *****************************************************************************************************
   * Code below is taken from the Scratch Java Player source. 
   * Copyright (c) 2009 Massachusetts Institute of Technology
   */
  static final byte[] squeakColors = {
    -1, -1, -1, 0, 0, 0, -1, -1, -1, -128, -128, -128, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, -1, -1,
    -1, -1, 0, -1, 0, -1, 32, 32, 32, 64, 64, 64, 96, 96, 96, -97, -97, -97, -65, -65, -65, -33, -33, -33,
    8, 8, 8, 16, 16, 16, 24, 24, 24, 40, 40, 40, 48, 48, 48, 56, 56, 56, 72, 72, 72, 80, 80, 80,
    88, 88, 88, 104, 104, 104, 112, 112, 112, 120, 120, 120, -121, -121, -121, -113, -113, -113, -105, -105, -105, -89, -89, -89,
    -81, -81, -81, -73, -73, -73, -57, -57, -57, -49, -49, -49, -41, -41, -41, -25, -25, -25, -17, -17, -17, -9, -9, -9,
    0, 0, 0, 0, 51, 0, 0, 102, 0, 0, -103, 0, 0, -52, 0, 0, -1, 0, 0, 0, 51, 0, 51, 51,
    0, 102, 51, 0, -103, 51, 0, -52, 51, 0, -1, 51, 0, 0, 102, 0, 51, 102, 0, 102, 102, 0, -103, 102,
    0, -52, 102, 0, -1, 102, 0, 0, -103, 0, 51, -103, 0, 102, -103, 0, -103, -103, 0, -52, -103, 0, -1, -103,
    0, 0, -52, 0, 51, -52, 0, 102, -52, 0, -103, -52, 0, -52, -52, 0, -1, -52, 0, 0, -1, 0, 51, -1,
    0, 102, -1, 0, -103, -1, 0, -52, -1, 0, -1, -1, 51, 0, 0, 51, 51, 0, 51, 102, 0, 51, -103, 0,
    51, -52, 0, 51, -1, 0, 51, 0, 51, 51, 51, 51, 51, 102, 51, 51, -103, 51, 51, -52, 51, 51, -1, 51,
    51, 0, 102, 51, 51, 102, 51, 102, 102, 51, -103, 102, 51, -52, 102, 51, -1, 102, 51, 0, -103, 51, 51, -103,
    51, 102, -103, 51, -103, -103, 51, -52, -103, 51, -1, -103, 51, 0, -52, 51, 51, -52, 51, 102, -52, 51, -103, -52,
    51, -52, -52, 51, -1, -52, 51, 0, -1, 51, 51, -1, 51, 102, -1, 51, -103, -1, 51, -52, -1, 51, -1, -1,
    102, 0, 0, 102, 51, 0, 102, 102, 0, 102, -103, 0, 102, -52, 0, 102, -1, 0, 102, 0, 51, 102, 51, 51,
    102, 102, 51, 102, -103, 51, 102, -52, 51, 102, -1, 51, 102, 0, 102, 102, 51, 102, 102, 102, 102, 102, -103, 102,
    102, -52, 102, 102, -1, 102, 102, 0, -103, 102, 51, -103, 102, 102, -103, 102, -103, -103, 102, -52, -103, 102, -1, -103,
    102, 0, -52, 102, 51, -52, 102, 102, -52, 102, -103, -52, 102, -52, -52, 102, -1, -52, 102, 0, -1, 102, 51, -1,
    102, 102, -1, 102, -103, -1, 102, -52, -1, 102, -1, -1, -103, 0, 0, -103, 51, 0, -103, 102, 0, -103, -103, 0,
    -103, -52, 0, -103, -1, 0, -103, 0, 51, -103, 51, 51, -103, 102, 51, -103, -103, 51, -103, -52, 51, -103, -1, 51,
    -103, 0, 102, -103, 51, 102, -103, 102, 102, -103, -103, 102, -103, -52, 102, -103, -1, 102, -103, 0, -103, -103, 51, -103,
    -103, 102, -103, -103, -103, -103, -103, -52, -103, -103, -1, -103, -103, 0, -52, -103, 51, -52, -103, 102, -52, -103, -103, -52,
    -103, -52, -52, -103, -1, -52, -103, 0, -1, -103, 51, -1, -103, 102, -1, -103, -103, -1, -103, -52, -1, -103, -1, -1,
    -52, 0, 0, -52, 51, 0, -52, 102, 0, -52, -103, 0, -52, -52, 0, -52, -1, 0, -52, 0, 51, -52, 51, 51,
    -52, 102, 51, -52, -103, 51, -52, -52, 51, -52, -1, 51, -52, 0, 102, -52, 51, 102, -52, 102, 102, -52, -103, 102,
    -52, -52, 102, -52, -1, 102, -52, 0, -103, -52, 51, -103, -52, 102, -103, -52, -103, -103, -52, -52, -103, -52, -1, -103,
    -52, 0, -52, -52, 51, -52, -52, 102, -52, -52, -103, -52, -52, -52, -52, -52, -1, -52, -52, 0, -1, -52, 51, -1,
    -52, 102, -1, -52, -103, -1, -52, -52, -1, -52, -1, -1, -1, 0, 0, -1, 51, 0, -1, 102, 0, -1, -103, 0,
    -1, -52, 0, -1, -1, 0, -1, 0, 51, -1, 51, 51, -1, 102, 51, -1, -103, 51, -1, -52, 51, -1, -1, 51,
    -1, 0, 102, -1, 51, 102, -1, 102, 102, -1, -103, 102, -1, -52, 102, -1, -1, 102, -1, 0, -103, -1, 51, -103,
    -1, 102, -103, -1, -103, -103, -1, -52, -103, -1, -1, -103, -1, 0, -52, -1, 51, -52, -1, 102, -52, -1, -103, -52,
    -1, -52, -52, -1, -1, -52, -1, 0, -1, -1, 51, -1, -1, 102, -1, -1, -103, -1, -1, -52, -1, -1, -1, -1
  };

  IndexColorModel squeakColorMap(int depth) {
    int transparentIndex = (depth == 1) ? -1 : 0;  // zero is the transparent index except for depth = 1
    return new IndexColorModel(depth, 256, squeakColors, 0, false, transparentIndex);
  }
  
  int [] decodePixels(Object data) throws IOException {
    /*  Decompress Squeak pixel data run-encoded as a byte[]. (If the argument
      is an int[] then it is unencoded pixel words; just return it.)
      The run encoding is a sequence of pairs, {N D}*, where N is the
      run-length * 4 with a 2-bit data code in the least significant
      two bits. D, the data for the run, depends on the data code...
        0 skip N words, D is absent (not currently used, I believe)
        1 N copies of a word with all 4 bytes = D (1 byte)
        2 N copies of word D (4 bytes)
        3 D is N words of literal data (4N bytes)
    */
    if (data instanceof int[]) return (int[]) data;

    DataInputStream s = new DataInputStream(new ByteArrayInputStream((byte []) data));
    int n = decodeInt(s);
    int [] result = new int[n];
    int i = 0;

    while ((s.available() > 0) & (i < n)) {
      int runLengthAndCode = decodeInt(s);
      int runLength = runLengthAndCode >> 2;
      int code = runLengthAndCode & 3;
      int b, w;

      switch (code) {
      case 0:
        i += runLength;
        break;
      case 1:
        b = s.readUnsignedByte();
        w = (b << 24) | (b << 16) | (b << 8) | b;
        for (int j = 0; j < runLength; j++) result[i++] = w;
        break;
      case 2:
        w = s.readInt();
        for (int j = 0; j < runLength; j++) result[i++] = w;
        break;
      case 3:
        for (int j = 0; j < runLength; j++) {
          w = (s.readUnsignedByte()) << 24;
          w |= (s.readUnsignedByte()) << 16;
          w |= (s.readUnsignedByte()) << 8;
          w |= s.readUnsignedByte();
          result[i++] = w;
        }
        break;
      }
    }
    return result;
  }
  
  int decodeInt(DataInputStream s) throws IOException {
    /*  Decode an integer as follows...
        0-223   0-223
        224-254   (0-30)*256 + next byte (0-7935)
        255   next 4 bytes as big-endian integer
    */

    int count = s.readUnsignedByte();
    if (count <= 223) return count;
    if (count <= 254) return ((count - 224) * 256) + (s.readUnsignedByte());
    return s.readInt();
  }

  int[]  rasterAddAlphaTo32(int[] raster32)  {
    // Add alpha to all non-zero pixel values of the given 32-bit raster
    for (int i = 0; i < raster32.length; i++) {
      int pix = raster32[i];
      if (pix != 0) raster32[i] = 0xFF000000 | pix;
    }
    return raster32;
  }

  int[] raster16to32(int[] raster16, int w, int h)  {
    // Convert from 16-bit to 32-bit direct color
    int shift, pix, r, g, b, pix32;
    int[] raster32 = new int[w * h];
    int span = (w + 1) / 2;
    for (int y = 0; y < h; y++) {
      shift = 16;
      for (int x = 0; x < w; x++) {
        pix = ((raster16[(y * span) + (x / 2)]) >> shift) & 0xFFFF;
        r = ((pix >> 10) & 0x1F) << 3;
        g = ((pix >> 5) & 0x1F) << 3;
        b = (pix & 0x1F) << 3;
        pix32 = ((r + g + b) == 0) ? 0 : (0xFF000000 | (r << 16) | (g << 8) | b);
        raster32[(y * w) + x] = pix32;
        shift = (shift == 16) ? 0 : 16;
      }
    }
    return raster32;
  }

  byte[] rasterToByteRaster(int [] raster, int w, int h, int depth) {
    byte[] byteRaster = new byte[w * h];
    int span = raster.length / h;
    int mask = (1 << depth) - 1;
    int pixelsPerWord = 32 / depth;

    for (int y = 0; y < h; y++) {
      for (int x = 0; x < w; x++) {
        int word = raster[(y * span) + (x / pixelsPerWord)];
        int shift = depth * (pixelsPerWord - (x % pixelsPerWord) - 1);
        byteRaster[(y * w) + x] = (byte) ((word >> shift) & mask);
      }
    }
    return byteRaster;
  }
}