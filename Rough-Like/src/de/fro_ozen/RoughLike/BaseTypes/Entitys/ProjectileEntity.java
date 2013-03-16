package de.fro_ozen.RoughLike.BaseTypes.Entitys;

import java.awt.Graphics;

public abstract class ProjectileEntity extends BaseEntity{
	protected double movex, movey;
	public BaseEntity source;
	public int deliverDamage(){
		return 15;
	}
}