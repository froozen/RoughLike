package de.fro_ozen.GUI.elements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import de.fro_ozen.RoughLike.GameMechanics.MouseInput;

public class GButton extends BaseElement{
	private String text; //Text displayed on the Button
	private GButtonListener listener; //Listener of the Button
	
	//Changes value of listener
	public void setButtonListener(GButtonListener listener){
		this.listener = listener;
	}
	
	//Changes the value of text
	public void setText(String text){
		this.text = text;
	}
	
	//Checks wether the Button was clicked and runs the ButtonListeners activate method
	public void checkMe(){
		if(MouseInput.leftClicked){
			if(box.contains(MouseInput.mousePoint)){
				if(listener != null){
					listener.activate(this);
				}
			}
		}
	}
	
	//Draws the Button and its text
	public void drawMe(Graphics g){
		g.drawImage(img, box.x, box.y, null);
		g.setColor(Color.black);
		if(text!=null){
			g.setFont(g.getFont().deriveFont(Font.PLAIN, fontheight));
			g.drawString(text, box.x+5, box.y+(box.height/2)+fontheight/2-2);
		}
	}
	
	//Creates the Image of the Button
	public void setBounds(int x, int y, int width, int height) {
		box = new Rectangle(x, y, width, height);
		img = createBaseImage(width, height, 0, 0, 65, 30, 4);
	}
}