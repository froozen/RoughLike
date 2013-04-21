package de.fro_ozen.RoughLike.BaseTypes.Entitys;

import java.awt.Color;
import java.awt.Graphics;

public class FloatingText extends BaseEntity{
	private String text;
	private long startTime, duration;
	private Color color;
	public void compute() {
		if((System.currentTimeMillis()- startTime)>duration)remove = true;
		y-=35*timeSinceLastFrame;
	}
	public FloatingText(double x, double y, int dmg, long duration){
		this.x = x;
		this.y = y;
		this.text = Math.abs(dmg)+"";
		
		if(dmg>-1)color = Color.RED;
		else color = Color.green;
		this.duration = duration;
		
		startTime = System.currentTimeMillis();
	}
	public FloatingText(double x, double y, String text, long duration){
		this.x = x;
		this.y = y;
		this.text = text;
		
		color = Color.black;
		this.duration = duration;
		
		startTime = System.currentTimeMillis();
	}
	public FloatingText(double x, double y, String text, long duration, Color color){
		this.x = x;
		this.y = y;
		this.text = text;
		
		this.color = color;
		this.duration = duration;
		
		startTime = System.currentTimeMillis();
	}
	public void drawMe(Graphics g) {
		g.setColor(color);
		g.drawString(text, (int)x, (int)y);
	}
	
}