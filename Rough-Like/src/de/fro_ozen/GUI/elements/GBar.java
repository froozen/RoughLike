package de.fro_ozen.GUI.elements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import de.fro_ozen.RoughLike.BaseTypes.Simple.VariablePair;

public class GBar extends BaseElement{
	private VariablePair display; //The VariablePair to display
	private boolean text; //Wether the two Numbers of the VariablePair should be displayed
	private BufferedImage bar; //The Image of the Bar that will be drawn
	private int color; //The xpos to cut out the bar, which represents the color (UNUSED)
	private final int colorlength = 25;//Length of one Colorpart in the UISet
	
	//Postitons of the Colors
	public final static int GREEN = 0;
	public final static int LIGHTBLUE = 1;
	public final static int RED = 2;
	public final static int ORANGE = 3;
	public final static int YELLOW = 4;
	public final static int BLUE = 5;
	
	//Refreshs the Bar
	public void checkMe() {
		bar = (BufferedImage) createUIPart(computeBarLength(), box.height-2*borderwidth, color, 60, 25, 26, 0);
	}
	
	//Changes the value of text
	public void enableText(boolean text){
		this.text = text;
	}
	
	//Draws the Bar, its background and the text
	public void drawMe(Graphics g){
		g.setColor(Color.green);
		g.fillRect(box.x, box.y, box.width, box.height);
		if(img != null){
			g.drawImage(img, box.x, box.y, null);
		}
		if(bar != null){
			g.drawImage(bar, box.x+borderwidth, box.y+borderwidth, null);
		}
		if(text){
			applyTextStandarts(g);
			g.drawString(display.real+"/"+display.max, box.x+5, box.y+(box.height/2)+fontheight/2-2);
		}
	}
	
	//Changes the Value of display
	public void setDisplayVariablePair(VariablePair display){
		this.display = display;
	}
	
	//Returns the new length of the Bar Image
	private int computeBarLength(){
		return (int) ((box.width-2*borderwidth)*((double)display.real/(double)display.max));
	}
	
	//Creates the Bar background
	public void setBounds(int x, int y, int width, int height) {
		box = new Rectangle(x, y, width, height);
		img = createBaseImage(width, height, 206, 0, 65, 30, 3);
	}
	
	public void setColor(int colorid){
		this.color = color*colorlength;
	}
}