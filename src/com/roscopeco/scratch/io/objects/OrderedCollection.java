package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Iterator;

import com.roscopeco.scratch.io.ObjectTable;


public class OrderedCollection<T extends ScratchObject> extends ScratchObject
implements Iterable<T> {
  final T[] data;
  
  @SuppressWarnings("unchecked")
  OrderedCollection(ObjectTable table, DataInputStream dis) throws IOException, UnsupportedTypeException {
    int arrLen = dis.readInt();

    logf("OrderedCollection has %d entries\n", arrLen);
    data = (T[])new ScratchObject[arrLen];
    
    logln("--> READING COLLECTION ELEMENTS");
    
    for (int i = 0; i < arrLen; i++) {
      data[i] = (T)ScratchObject.read(table, dis);      
    }
    
    logln("<-- FINSIHED READING COLLECTION");
  }

  @SuppressWarnings("unchecked")
  @Override
  public ScratchObject resolve(ObjectTable table) {
    super.resolve(table);
    for (int i = 0; i < data.length; i++) {
      data[i] = (T)data[i].resolve(table);
    }
    return this;
  }
  
  public T[] data() {
    guardResolved();
    return data;
  }
  
  public int size() {
    guardResolved();
    return data.length;
  }
  
  public T get(int index) {
    guardResolved();
    return data[index];
  }

  @Override
  public Iterator<T> iterator() {
    guardResolved();
    return new Iterator<T>() {
      int i = 0;
      
      @Override
      public boolean hasNext() {
        return i < data.length;
      }

      @Override
      public T next() {
        return data[i++];
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException("Cannot remove items from collection iterator");
      }      
    };
  }
}
