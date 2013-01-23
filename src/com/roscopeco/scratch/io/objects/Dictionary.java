package com.roscopeco.scratch.io.objects;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.roscopeco.scratch.io.ObjectTable;


public class Dictionary extends ScratchObject {
  private Map<ScratchObject, ScratchObject> dict;
  
  Dictionary(ObjectTable table, DataInputStream dis)
  throws IOException, UnsupportedTypeException {
    this(new HashMap<ScratchObject, ScratchObject>(), table, dis);
  }
  
  Dictionary(Map<ScratchObject, ScratchObject> dict, ObjectTable table, DataInputStream dis) 
  throws IOException, UnsupportedTypeException {
    this.dict = dict;
    int dictLen = dis.readInt();

    logf("Dictionary has %d entries\n", dictLen);
    
    logln("--> READING DICTIONARY ELEMENTS");
    
    for (int i = 0; i < dictLen; i++) {
      ScratchObject key = ScratchObject.read(table, dis);
      ScratchObject val = ScratchObject.read(table, dis);
      dict.put(key, val);
    }
    
    logln("<-- FINSIHED READING DICTIONARY");
  }
  
  public Set<ScratchObject> keySet() {
    guardResolved();
    return dict.keySet();
  }
  
  public ScratchObject get(ScratchObject key) {
    guardResolved();
    return dict.get(key);
  }

  @Override
  public ScratchObject resolve(ObjectTable table) {
    super.resolve(table);
    
    HashMap<ScratchObject, ScratchObject> newDict = new HashMap<ScratchObject, ScratchObject>();
    
    for (ScratchObject key : dict.keySet()) {
      ScratchObject newKey = key.resolve(table);
      ScratchObject newVal = dict.get(key).resolve(table);      
      newDict.put(newKey, newVal);
    }
    dict = newDict;
    
    return this;
  }
}
