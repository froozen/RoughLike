package de.fro_ozen.GUI.elements;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import de.fro_ozen.RoughLike.GameMechanics.KeyInput;

public abstract class GMenu extends BaseElement{
	private ArrayList<BaseElement> Elements; //All the GElements of the Menu
	public String triggerKey; //Key that opens the Menu
	
	//Calls the drawMe() method of all GElements
	public void drawMe(Graphics g){
		g.drawImage(img, box.x, box.y, null);
		for(BaseElement e:Elements){
			e.drawMe(g);
		}
	}
	
	//Adds a GElement to Elements
	protected void add(BaseElement e){
		if(Elements == null)Elements = new ArrayList<BaseElement>();
		Elements.add(e);
	}
	
	//Calls the checkMe() method of all GElements
	public void checkMe(){
		for(BaseElement e:Elements){
			e.checkMe();
		}
	}
	
	//Creates the background Image
	public void setBounds(int x, int y, int width, int height) {
		box = new Rectangle(x, y, width, height);
		img = createBaseImage(width, height, 130, 0, 65, 30, 2);
	}
	
	//Calls the setShift() method of all GElements
	public void setShift(){
		for(BaseElement e:Elements){
			e.setShift(box.x, box.y);
		}
	}
}