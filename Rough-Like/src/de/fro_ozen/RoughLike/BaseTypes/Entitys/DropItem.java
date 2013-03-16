package de.fro_ozen.RoughLike.BaseTypes.Entitys;

import java.awt.Graphics;

import de.fro_ozen.RoughLike.BaseTypes.Items.BaseItem;

public class DropItem extends BaseEntity{
	public BaseItem item;
	private boolean up;
	private double waywent;
	public DropItem(int x, int y, BaseItem item){
		this.item = item;
		this.x = x;
		this.y = y;
		up = true;
		speed = 15;
		constructorHelp(item.icon);
	}
	public void compute() {
		if(up){
			y-=speed*timeSinceLastFrame;
			waywent+= speed*timeSinceLastFrame;
		}
		else{
			y+= speed*timeSinceLastFrame;
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
		g.drawImage(item.icon, (int)x, (int)y, null);
	}
	
}
