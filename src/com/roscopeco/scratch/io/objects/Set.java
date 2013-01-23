package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import com.roscopeco.scratch.io.ObjectTable;


public class Set<T extends ScratchObject> extends ScratchObject
implements Iterable<T>{
  private java.util.Set<ScratchObject> set;
  
  Set(ObjectTable table, DataInputStream dis)
  throws IOException, UnsupportedTypeException {
    this(new HashSet<ScratchObject>(), table, dis);
  }
  
  Set(java.util.Set<ScratchObject> set, ObjectTable table, DataInputStream dis)
  throws IOException, UnsupportedTypeException {
    this.set = set;
    int arrLen = dis.readInt();

    logf("Set has %d entries\n", arrLen);
    logln("--> READING COLLECTION ELEMENTS");
    
    for (int i = 0; i < arrLen; i++) {
      set.add(ScratchObject.read(table, dis));      
    }
    
    logln("<-- FINSIHED READING COLLECTION");
  }

  @Override
  public ScratchObject resolve(ObjectTable table) {
    super.resolve(table);
    HashSet<ScratchObject> newSet = new HashSet<ScratchObject>();
    
    for (ScratchObject o : set) {
      newSet.add(o.resolve(table));      
    }
    
    set = newSet;
    return this;
  }
  
  public int size() {
    guardResolved();
    return set.size();
  }
  
  public boolean contains(ScratchObject o) {
    guardResolved();
    return set.contains(o);
  }

  @Override
  public Iterator<T> iterator() {
    guardResolved();
    return new Iterator<T>() {
      Iterator<ScratchObject> iter = set.iterator();

      @Override
      public boolean hasNext() {
        return iter.hasNext();
      }

      @SuppressWarnings("unchecked")
      @Override
      public T next() {
        return (T)iter.next();
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException("Cannot remove items from set iterator");
      }
    };
  }
}
