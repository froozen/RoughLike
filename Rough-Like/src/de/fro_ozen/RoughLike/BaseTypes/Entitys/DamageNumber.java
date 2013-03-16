package de.fro_ozen.RoughLike.BaseTypes.Entitys;

import java.awt.Color;
import java.awt.Graphics;

public class DamageNumber extends BaseEntity{
	private int dmg;
	private long startTime, duration;
	public void compute() {
		if((System.currentTimeMillis()- startTime)>duration)remove = true;
		y-=35*timeSinceLastFrame;
	}
	public DamageNumber(double x, double y, int dmg, long duration){
		this.x = x;
		this.y = y;
		this.dmg = dmg;
		this.duration = duration;
		startTime = System.currentTimeMillis();
	}
	public void drawMe(Graphics g) {
		g.setColor(Color.red);
		g.drawString("-"+dmg, (int)x, (int)y);
	}
	
}