package de.fro_ozen.GUI.elements;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.text.StyledEditorKit.FontSizeAction;

public class GLabel extends BaseElement{
	private String text; //The text displayed by the Label
	
	//Draws the text
	public void drawMe(Graphics g){
		if(text != null){
			applyTextStandarts(g);
			g.drawString(text, box.x, box.y+fontheight);
		}
	}
	
	//UNUSED
	public void checkMe() {}
	
	//Creates the basic colission-box
	public void setBounds(int x, int y, int width, int height) {
		box = new Rectangle(x, y, width, height);
	}
	
	//Changes the value of text
	public  void setText(String text){
		this.text = text;
	}
}