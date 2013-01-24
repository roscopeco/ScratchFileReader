package com.roscopeco.scratch.io.objects;

import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.io.DataInputStream;
import java.io.IOException;
import java.sql.Ref;

import com.roscopeco.scratch.io.ObjectTable;


public class ColorForm extends Form {
  private ScratchObject colorMap;
  
  ColorForm(ObjectTable table, DataInputStream dis) throws IOException, UnsupportedTypeException {
    super(table, dis);
    
    logf("  --> Reading colorMap\n");
    colorMap = ScratchObject.read(table, dis);
    logf("  <-- Got it\n");
  }
  
  @Override
  public ScratchObject resolve(ObjectTable table) {
    colorMap = colorMap.resolve(table);
    return super.resolve(table);
  }

  @SuppressWarnings("unchecked")
  public OrderedCollection<Color> colorMap() {
    guardResolved();
    return (OrderedCollection<Color>)colorMap;
  }
  
  protected ColorModel createImageColorModel(int depth) {
    if (colorMap == null) {
      return super.createImageColorModel(depth);
    } else {
      return customColorMap(depth, colorMap());
      //throw new UnsupportedOperationException("Color mapped forms are not yet supported");
    }
  }

  IndexColorModel customColorMap(int paramInt, OrderedCollection<Color> map) {
    logln("Creating IndexColorModel...");
    byte[] bytes = new byte[4 * map.size()];
    int i = 0;
    for (Color color : map) {
      bytes[(i++)] = ((byte) color.red());
      bytes[(i++)] = ((byte) color.green());
      bytes[(i++)] = ((byte) color.blue());
      bytes[(i++)] = ((byte) color.alpha());
    }
    return new IndexColorModel(paramInt, map.size(),
        bytes, 0, true, 0);
  }
}
