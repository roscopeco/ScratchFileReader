package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;

import com.roscopeco.scratch.io.ObjectTable;


/**
 * Base-class for all Scratch objects.
 */
public abstract class ScratchObject {
  public static PrintStream logStrm;
  public static PrintStream warnStrm;
  public static void logln(String s) {
    if (logStrm != null) {
      logStrm.println(s);      
    }
  }
  
  public static void logf(String format, Object... args) {
    if (logStrm != null) {
      logStrm.printf(format, args);
    }
  }
  
  public static void warnln(String s) {
    if (warnStrm != null) {
      warnStrm.println(s);      
    }
  }
  
  public static void warnf(String format, Object... args) {
    if (warnStrm != null) {
      warnStrm.printf(format, args);
    }
  }
  
  public static ScratchObject read(ObjectTable table, DataInputStream dis) 
  throws IOException, UnsupportedTypeException {
    return read(table, dis, false);    
  }

  /*
   * The 'toplevel' arg determines whether or not we store References in the table,
   * as opposed to just inlining them. Basically, this should only be done when at
   * the top level (i.e. not reading an object's fields).
   */
  @SuppressWarnings("rawtypes")
  public static ScratchObject read(ObjectTable table, DataInputStream dis, boolean toplevel) 
  throws IOException, UnsupportedTypeException {
    int clsId = dis.readUnsignedByte();

    logf("Reading object of type 0x%02x (%d)\n", clsId, clsId);
    
    if (clsId < 0x64) {
      // immed or fixed-format, default will throw exception.
      switch (clsId) {
      // Immediate values - not added to object table
      case 0x01: return Nil.instance;
      case 0x02: return True.instance;
      case 0x03: return False.instance;
      case 0x04: return new SmallInt32(dis);
      case 0x05: return new SmallInt16(dis);
      case 0x06: return new LargePositiveInteger(dis);
      case 0x07: return new LargeNegativeInteger(dis);
      case 0x08: return new Float(dis);
      
      // Fixed values - added to table
      case 0x09: return table.add(new CharString(dis));
      case 0x0a: return table.add(new Symbol(dis));
      case 0x0b: return table.add(new ByteArray(dis));
      case 0x0c: return table.add(new SoundBuffer(dis));
      case 0x0d: return table.add(new Bitmap(dis));
      case 0x0e: return table.add(new UTF8(dis));
      case 0x14: return table.add(new Array(table, dis));
      case 0x15: return table.add(new OrderedCollection(table, dis));
      case 0x16: return table.add(new Set(table, dis));
      case 0x17: return table.add(new IdentitySet(table, dis));
      case 0x18: return table.add(new Dictionary(table, dis));
      case 0x19: return table.add(new IdentityDictionary(table, dis));
      case 0x1e: return table.add(new Color(dis));
      case 0x1f: return table.add(new TranslucentColor(dis));
      case 0x20: return table.add(new Point(table, dis));
      case 0x21: return table.add(new Rectangle(table, dis));
      case 0x22: return table.add(new Form(table, dis));
      case 0x23: return table.add(new ColorForm(table, dis));
      
      // Refs are treated specially - they only get added to the table
      // if we're at the top level (i.e. not reading an object's
      // fields).
      case 0x63: 
        Reference ref = new Reference(dis);
        if (toplevel) {
          table.add(ref);
        }
        return ref;

      default: throw new UnsupportedTypeException(clsId);
      }
    } else {
      // User object - default will read fields into an 'Unknown'
      switch (clsId) {    
      // User objects - added to table
      case 0x64: return table.add(new Morph(table, dis));
      case 0x65: return table.add(new BorderedMorph(table, dis));
      case 0x66: return table.add(new RectangleMorph(table, dis));
      case 0x68: return table.add(new AlignmentMorph(table, dis));
      case 0x69: return table.add(new StringMorph(table, dis));
      case 0x6a: return table.add(new UpdatingStringMorph(table, dis));
      case 0x6d: return table.add(new SampledSound(table, dis));
      case 0x7a: return table.add(new ScriptableScratchMorph(table, dis));
      case 0x7c: return table.add(new ScratchSpriteMorph(table, dis));
      case 0x7d: return table.add(new ScratchStageMorph(table, dis));
      case 0x9b: return table.add(new WatcherMorph(table, dis));
      case 0xa1: return table.add(new ScratchMedia(table, dis));
      case 0xa2: return table.add(new ImageMedia(table, dis));
      case 0xa4: return table.add(new SoundMedia(table, dis));
      case 0xab: return table.add(new MultilineStringMorph(table, dis));
      case 0xad: return table.add(new WatcherReadoutFrameMorph(table, dis));
      default: return table.add(new Unknown(clsId, table, dis));
      }
    }
  }
  
  ScratchObject() { }
  
  private boolean resolved = false;
  
  public ScratchObject resolve(ObjectTable table) {
    resolved = true;
    return this;
  }  
  
  protected boolean isResolved() {
    return resolved;
  }
  
  protected void guardResolved() {
    if (!resolved) {
      throw new IllegalStateException("Cannot perform operation on unresolved object");
    }    
  }
  
  
}
