package com.roscopeco.scratch;

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

import com.roscopeco.scratch.io.ScratchProject;
import com.roscopeco.scratch.io.objects.ColorForm;
import com.roscopeco.scratch.io.objects.Dictionary;
import com.roscopeco.scratch.io.objects.Form;
import com.roscopeco.scratch.io.objects.ScratchObject;
import com.roscopeco.scratch.io.objects.ScratchSpriteMorph;
import com.roscopeco.scratch.io.objects.ScratchStageMorph;
import com.roscopeco.scratch.io.objects.ScratchString;

public class ReadProject {
  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      System.out.println("Usage: ReadProject <filename>");
      System.exit(1);
    }
    
    //ScratchObject.logStrm = System.out;
    //ScratchObject.warnStrm = System.err;
    
    ScratchProject project = ScratchProject.readProject(args[0]);    
    final Dictionary info = project.getInfoDictionary();    
    
    for (ScratchObject o : info.keySet()) {
      System.out.printf("%s => %s\n", o, info.get(o));
    }
    
    System.out.printf("Thumbnail is %sx%s pixels (%s bpp)\n",
        ((ColorForm)info.get(ScratchString.fromString("thumbnail"))).width(),
        ((ColorForm)info.get(ScratchString.fromString("thumbnail"))).height(),
        ((ColorForm)info.get(ScratchString.fromString("thumbnail"))).depth());       
    
    final ScratchStageMorph stage = project.getStage();
    
    for (ScratchSpriteMorph sprite : stage.sprites()) {
      System.out.printf("Sprite `%s'; Costume: %s (%s byte(s))", sprite.objName(), 
          sprite.costume().mediaName(), sprite.costume().form().bitmap().size());
      System.out.printf(" %dx%dpx (%dbpp)\n", 
          sprite.costume().form().width().value(), sprite.costume().form().height().value(),
          sprite.costume().form().depth().value());    
    }
    
    final JFrame frm = new JFrame(args[0]);
    frm.setSize(500, 200);
    frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frm.add(new JComponent() {
      @Override
      protected void paintComponent(Graphics g) {
        Form thumb = ((Form)info.get(ScratchString.fromString("thumbnail")));
        g.drawImage(thumb.getImage(), 0, 0, null);

        int x = thumb.width().value();
        int y = 0;
        int tallest = thumb.height().value();
        
        for (ScratchSpriteMorph sprite : stage.sprites()) {
          Form f = sprite.costume().form();
          
          if (f.height().value() > tallest) {
            tallest = f.height().value();
          }
          
          if (x > frm.getWidth() - f.width().value()) {
            x = 0;
            y = y + tallest;
            tallest = 0;
          }
          
          g.drawImage(f.getImage(), x, y, null);

          x = x + f.width().value();          
        }        
      }
    });
    frm.setVisible(true);
 }
}