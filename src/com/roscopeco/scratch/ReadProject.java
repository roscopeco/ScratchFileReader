package com.roscopeco.scratch;

import com.roscopeco.scratch.io.ScratchProject;
import com.roscopeco.scratch.io.objects.ColorForm;
import com.roscopeco.scratch.io.objects.Dictionary;
import com.roscopeco.scratch.io.objects.ScratchObject;
import com.roscopeco.scratch.io.objects.ScratchSpriteMorph;
import com.roscopeco.scratch.io.objects.ScratchStageMorph;
import com.roscopeco.scratch.io.objects.ScratchString;

public class ReadProject {
  public static int[] byteToIntArray(byte[] bytes) {
    int[] ints = new int[bytes.length / 4];
    System.out.println(bytes.length);
    System.out.println(bytes.length / 4);
    int ip = 0;
    for (int i = 0; i < ints.length; i++) {
      ints[ip++] = (bytes[i++] << 24) | (bytes[i++] << 16) | (bytes[i++] << 8) | bytes[i];       
    }
    return ints;    
  }
  
  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      System.out.println("Usage: ReadProject <filename>");
      System.exit(1);
    }
    
    //ScratchObject.logStrm = System.out;
    //ScratchObject.warnStrm = System.err;
    
    ScratchProject project = ScratchProject.readProject(args[0]);    
    Dictionary info = project.getInfoDictionary();    
    
    for (ScratchObject o : info.keySet()) {
      System.out.printf("%s => %s\n", o, info.get(o));
    }
    
    System.out.printf("Thumbnail is %sx%s pixels (%s bpp)\n",
        ((ColorForm)info.get(ScratchString.fromString("thumbnail"))).width(),
        ((ColorForm)info.get(ScratchString.fromString("thumbnail"))).height(),
        ((ColorForm)info.get(ScratchString.fromString("thumbnail"))).depth());       
    
    ScratchStageMorph stage = project.getStage();
    
    for (ScratchSpriteMorph sprite : stage.sprites()) {
      System.out.printf("Sprite `%s'; Costume: %s (%s byte(s))", sprite.objName(), 
          sprite.costume().mediaName(), sprite.costume().form().bitmap().size());
      System.out.printf(" %dx%dpx (%dbpp)\n", 
          sprite.costume().form().width().value(), sprite.costume().form().height().value(),
          sprite.costume().form().depth().value());    
    }    
 }
}