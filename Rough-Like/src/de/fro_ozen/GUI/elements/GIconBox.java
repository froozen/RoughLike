package de.fro_ozen.GUI.elements;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class GIconBox extends BaseElement{
	private BufferedImage icon;//The icon that is show in the Box
	
	//Unused
	public void checkMe() {}

	public void setBounds(int x, int y, int width, int height) {
		loadUIset();
		img = uiset.getSubimage(0, 30, 28, 28);
		box = new Rectangle(x, y, 28, 28);
	}
	
	public void drawMe(Graphics g){
		g.drawImage(img, box.x, box.y, null);
		if(icon != null)g.drawImage(icon, box.x+2, box.y+2, null);
	}
	
	//Changes the Value of icon
	public void setIcon(BufferedImage icon){
		this.icon = icon;
	}
}
