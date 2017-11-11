package com.tapiadevelopmentinc.snake;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DrawImg extends JPanel {
	  public DrawImg(Graphics g, Image img1) {
		  Graphics2D g2 = (Graphics2D) g;
		  	if(img1 != null){
		    g2.drawImage(img1, (Game.width + Game.adjusterX-468-6)/2 , 30, (ImageObserver) this);
		    g2.finalize();
		  	}
		  }
}
