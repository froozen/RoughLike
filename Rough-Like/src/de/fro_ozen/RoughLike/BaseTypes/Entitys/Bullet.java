package de.fro_ozen.RoughLike.BaseTypes.Entitys;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet extends ProjectileEntity{
	public Bullet(int targetx, int targety, int sourcex, int sourcey, BaseEntity source){
		this.source = source;
		movex = targetx-sourcex;
		movey = targety-sourcey;
		double complway = (Math.abs(movex)+Math.abs(movey));
		movex = (Math.abs(movex)/complway)*(movex/Math.abs(movex));
		movey = (Math.abs(movey)/complway)*(movey/Math.abs(movey));
		
		x=sourcex;
		y= sourcey;
		
		if(Math.abs(movex)>Math.abs(movey)){
			if((movex)>0)dir=3;
			else dir=2;
		}
		else{
			if((movey)>0)dir =1;
			else dir=4;
		}
		
		speed=400;
		sizex = 10;
		sizey = 10;
		constructorHelp("Sprites/Icons/sol.png");
	}
	public void compute() {
		x+=movex*speed*timeSinceLastFrame;
		y+=movey*speed*timeSinceLastFrame;
		if(x<0 && y<0 || x>800 && y>600)remove = true;
		
		updateBoxes();
	}
	public void drawMe(Graphics g) {
//		g.setColor(Color.black);
//		g.fillRect((int)x, (int)y, sizex, sizey);
		g.drawImage(sprite, (int)x, (int)y, null);
	}
}
