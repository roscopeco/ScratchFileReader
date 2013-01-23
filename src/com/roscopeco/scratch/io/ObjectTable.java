package com.roscopeco.scratch.io;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.roscopeco.scratch.io.objects.Reference;
import com.roscopeco.scratch.io.objects.ScratchObject;

public class ObjectTable implements Iterable<ScratchObject> {
  private static final ScratchObject[] EMPTYARRAY = new ScratchObject[0];
  private static final String OBJTABLE_SIG = "ObjS\u0001Stch\u0001";

  static ObjectTable readObjectTable(DataInputStream dis) throws IOException, ScratchFormatException {
    byte[] signature = new byte[10];
    
    dis.read(signature);
    if (!OBJTABLE_SIG.equals(new String(signature))) {
      dis.close();
      throw new ScratchFormatException("Info ObjectTable not found");
    }
    
    int infoObjCount = dis.readInt();
    ObjectTable table = new ObjectTable();
    
    for (int i = 0; i < infoObjCount; i++) {
      ScratchObject.read(table, dis, true);
    }
    
    return table;
  }

  private ArrayList<ScratchObject> table = new ArrayList<ScratchObject>();  
  private ScratchObject[] frozenTable;
  
  public int size() {
    if (frozenTable != null) {
      return frozenTable.length;
    } else {
      return table.size();
    }
  }
  
  public ScratchObject add(ScratchObject o) {
    if (frozenTable != null) {
      throw new IllegalStateException("ObjectTable cannot be modified once resolved");
    }
    
    table.add(o);
    return o;    
  }
  
  /**
   * Note: Indices are 1 based!
   */
  public ScratchObject get(int index) {
    try {
      if (frozenTable != null) {
        return frozenTable[index - 1];      
      } else {
        return table.get(index - 1);
      }      
    } catch (ArrayIndexOutOfBoundsException e) {      
      throw new IndexOutOfBoundsException(String.format("Index out of bounds: %d; must be 1..%d", index, size()));
    } catch (IndexOutOfBoundsException e) {      
      throw new IndexOutOfBoundsException(String.format("Index out of bounds: %d; must be 1..%d", index, size()));
    }
  }
    
  public void resolve() {
    if (frozenTable != null) {
      throw new IllegalStateException("ObjectTable already resolved");
    }
    
    // Resolve references
    for (int i = 0; i < table.size(); i++) {
      table.set(i, table.get(i).resolve(this));
    }
    
    ArrayList<ScratchObject> newTable = new ArrayList<ScratchObject>();
    
    // Compress table (remove references)
    for (ScratchObject o : table) {
      if (!(o instanceof Reference)) {
        newTable.add(o);
      }
    }
    
    frozenTable = newTable.toArray(EMPTYARRAY);
    table = null;
  }
  
  private class ObjectIterator implements Iterator<ScratchObject> {
    int i = 0;
    private ObjectIterator() {
      if (frozenTable == null) {
        throw new UnsupportedOperationException("Cannot iterate unresolved ObjectTable");
      }
    }

    @Override
    public boolean hasNext() {
      return i < frozenTable.length;
    }

    @Override
    public ScratchObject next() {
      return frozenTable[i++];      
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException("Cannot remove objects from ObjectTable");
    }
  }

  @Override
  public Iterator<ScratchObject> iterator() {
    return new ObjectIterator();
  }
}
