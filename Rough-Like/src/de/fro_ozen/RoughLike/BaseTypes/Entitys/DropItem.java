package de.fro_ozen.RoughLike.BaseTypes.Entitys;

import java.awt.Color;
import java.awt.Graphics;

import de.fro_ozen.RoughLike.BaseTypes.Items.BaseItem;

public class DropItem extends BaseEntity{
	public BaseItem item;
	private boolean up;
	private double waywent;
	private double iconX, iconY;
	public DropItem(int x, int y, BaseItem item){
		this.item = item;
		this.x = x + 3;
		iconX = x;
		this.y = y + 2;
		iconY = y - 13;
		sizex = 18;
		sizey = 10; 
		up = true;
		speed = 15;
		constructorHelp();
	}
	public void compute() {
		if(up){
			iconY-=speed*timeSinceLastFrame;
			waywent+= speed*timeSinceLastFrame;
		}
		else{
			iconY+= speed*timeSinceLastFrame;
			waywent+=speed*timeSinceLastFrame;
		}
		if(waywent>15){
			if(up)up=false;
			else up=true;
			
			waywent=0;
		}
		updateBoxes();
	}

	@Override
	public void drawMe(Graphics g) {
		g.setColor(Color.gray);
		g.fillOval((int)x, (int)y, sizex, sizey);
		g.drawImage(item.icon, (int)iconX, (int)iconY, null);
	}
	
}
