package com.roscopeco.scratch.io;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.roscopeco.scratch.io.objects.Dictionary;
import com.roscopeco.scratch.io.objects.Form;
import com.roscopeco.scratch.io.objects.ScratchObject;
import com.roscopeco.scratch.io.objects.ScratchStageMorph;

public final class ScratchProject {
  private static final String SIGNATURE = "ScratchV02";
  
  /**
   * Read a scratch project from the specified named file.
   * 
   * @param filename The filename to read from.
   * @return A new ScratchProject.
   * 
   * @throws FileNotFoundException
   * @throws IOException
   * @throws ScratchFormatException
   */
  public static ScratchProject readProject(String filename) 
  throws FileNotFoundException, IOException, ScratchFormatException {
    return readProject(filename, false);
  }
  
  /**
   * Read a scratch project from the specified named file.
   * 
   * @param filename The filename to read from.
   * @param preCacheMedia if true, Java media objects will be pre-cached.
   * 
   * @return A new ScratchProject.
   * 
   * @throws FileNotFoundException
   * @throws IOException
   * @throws ScratchFormatException
   */
  public static ScratchProject readProject(String filename, boolean preCacheMedia) 
  throws FileNotFoundException, IOException, ScratchFormatException {
    return readProject(new File(filename), preCacheMedia);
  }
  
  /**
   * Read a scratch project from the specified File. 
   * 
   * @param file The file to read from.
   * @return A new ScratchProject.
   * 
   * @throws FileNotFoundException
   * @throws IOException
   * @throws ScratchFormatException
   */
  public static ScratchProject readProject(File file) 
  throws FileNotFoundException, IOException, ScratchFormatException {
    return readProject(file, false);
  }
  
  /**
   * Read a scratch project from the specified File. 
   * 
   * @param file The file to read from.
   * @param preCacheMedia if true, Java media objects will be pre-cached.
   * 
   * @return A new ScratchProject.
   * 
   * @throws FileNotFoundException
   * @throws IOException
   * @throws ScratchFormatException
   */
  public static ScratchProject readProject(File file, boolean preCacheMedia) 
  throws FileNotFoundException, IOException, ScratchFormatException {
    InputStream strm = new FileInputStream(file);
    try {
      return readProject(new FileInputStream(file), preCacheMedia);
    } finally {
      strm.close();
    }
  }

  /**
   * Read a scratch project from the specified InputStream.
   * 
   * @param stream The stream. Note that this will NOT be closed after reading!
   * @return A new ScratchProject.
   * 
   * @throws IOException
   * @throws ScratchFormatException
   */
  public static ScratchProject readProject(InputStream stream) 
  throws IOException, ScratchFormatException {        
    return readProject(stream, false);
  }
  
  /**
   * Read a scratch project from the specified InputStream.
   * 
   * @param stream The stream. Note that this will NOT be closed after reading!
   * @param preCacheMedia if true, Java media objects will be pre-cached.
   * 
   * @return A new ScratchProject.
   * 
   * @throws IOException
   * @throws ScratchFormatException
   */
  public static ScratchProject readProject(InputStream stream, boolean preCacheMedia) 
  throws IOException, ScratchFormatException {        
    DataInputStream dis = new DataInputStream(stream);
    
    byte[] signature = new byte[10];
        
    dis.read(signature);
    if (!SIGNATURE.equals(new String(signature))) {
      dis.close();
      throw new ScratchFormatException("Not a scratch file");
    }

    // skip infoSize uint32, since we're gonna read it all anyway...
    dis.skip(4);
    
    ObjectTable infoTable = ObjectTable.readObjectTable(dis);
    ObjectTable contentTable = ObjectTable.readObjectTable(dis);
    
    // Resolve tables
    infoTable.resolve();
    contentTable.resolve();
    
    if (preCacheMedia) {
      // Create Java images (TODO sounds later?)
      for (ScratchObject o : infoTable) {
        if (o instanceof Form) ((Form)o).getImage();
      }
    }
    
    return new ScratchProject(infoTable, contentTable);
  }

  private final ObjectTable infoTable;
  private final ObjectTable contentTable;
  
  ScratchProject(ObjectTable infoTable, ObjectTable contentTable) {
    this.infoTable = infoTable;
    this.contentTable = contentTable;    
  }
  
  public ObjectTable getInfoTable() {
    return infoTable;
  }
  
  public ObjectTable getContentTable() {
    return contentTable;
  }
  
  public Dictionary getInfoDictionary() {
    // Dictionary *should* always be first in the infoTable.
    return (Dictionary)infoTable.get(1);    
  }
  
  public ScratchStageMorph getStage() {
    // Stage *should* always be first in the contentTable.
    return (ScratchStageMorph)contentTable.get(1);
  }
}
