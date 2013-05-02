package de.fro_ozen.RoughLike.BaseTypes.Entitys;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class GunBullet extends ProjectileEntity{
	private int atk, atkgap;
	Random r;
	public GunBullet(int targetx, int targety, int sourcex, int sourcey, BaseEntity source, int atk, int atkgap){
		r = new Random();
		this.source = source;
		movex = targetx-sourcex;
		movey = targety-sourcey;
		if(!(movex==0||movey==0)){
			double complway = (Math.abs(movex)+Math.abs(movey));
			movex = (Math.abs(movex)/complway)*(movex/Math.abs(movex));
			movey = (Math.abs(movey)/complway)*(movey/Math.abs(movey));
		}
		else{
			if(movex==0)movey=movey/Math.abs(movey);
			else movex = movex/Math.abs(movex);
		}
		x=sourcex;
		y= sourcey;
		this.atk = atk;
		this.atkgap = atkgap;


		speed=400;
		width = 4;
		height = 4;
		constructorHelp();
		
		if(Math.abs(movex)>Math.abs(movey)){
			if((movex)>0)direction=3;
			else direction=2;
		}
		else{
			if((movey)>0)direction =1;
			else direction=4;
		}
	}
	public int deliverDamage(){
		if(atkgap != 0)return atk + r.nextInt(atkgap);
		else return atk;
	}
	public void compute() {
		x+=movex*speed*timeSinceLastFrame;
		y+=movey*speed*timeSinceLastFrame;
		if(x<0 && y<0 || x>800 && y>600)remove = true;

		updateBoxes();
	}

	@Override
	public void drawMe(Graphics g) {
		g.setColor(Color.black);
		g.fillRect((int)x, (int)y, width, height);
	}

}
