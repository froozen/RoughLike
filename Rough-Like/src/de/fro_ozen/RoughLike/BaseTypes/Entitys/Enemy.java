package de.fro_ozen.RoughLike.BaseTypes.Entitys;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import de.fro_ozen.RoughLike.BaseTypes.Items.Potion;
import de.fro_ozen.RoughLike.BaseTypes.Items.Sword;
import de.fro_ozen.RoughLike.BaseTypes.Misc.BaseStats;

public class Enemy extends BattleEntity{
	Rectangle reachbox;
	Player prey;
	double HPbarLength;
	public void compute() {
		if(stats.hp.real>0){
			updateReachbox();
			if(free){
				if(reachbox.intersects(prey.colission)){
					if(Math.abs(prey.x-x)>Math.abs(prey.y-y)){
						if((prey.x-x)>0)dir=3;
						else dir=2;
					}
					else{
						if((prey.y-y)>0)dir =1;
						else dir=4;
					}
					if(System.currentTimeMillis()-lastAttack>atkcooldown){
						lastAttack = System.currentTimeMillis();
						attacking = true;
						if(dir == 1)attackbox = new Rectangle((int)x, (int)y+sizey, sizex, 20);
						if(dir == 2)attackbox = new Rectangle((int)x-sizex, (int)y+(sizey/2), 20, sizex);
						if(dir == 3)attackbox = new Rectangle((int)x+sizex, (int)y+(sizey/2), 20, sizex);
						if(dir == 4)attackbox = new Rectangle((int)x, (int)y-30, sizex, 20);
						atkdir = dir;
					}
				}
				else{
					lastdir = dir;
					if(prey.y>y){y+=speed*timeSinceLastFrame; dir=1;}
					if(prey.x<x){x-=speed*timeSinceLastFrame; dir = 2;}
					if(prey.x>x){x+=speed*timeSinceLastFrame; dir = 3;}
					if(prey.y<y){y-=speed*timeSinceLastFrame; dir = 4;}

					if(Math.abs(prey.x-x)>Math.abs(prey.y-y)){
						if((prey.x-x)>0)dir=3;
						else dir=2;
					}
					else{
						if((prey.y-y)>0)dir =1;
						else dir=4;
					}
				}
			}
			else{
				double travelway = speed*timeSinceLastFrame;
				if(forcedir == 1){y+=travelway; dir=4;}
				if(forcedir == 2){x-=travelway; dir = 3;}
				if(forcedir == 3){x+=travelway; dir = 2;}
				if(forcedir == 4){y-=travelway; dir = 1;}
				forceway -=travelway;
				if(forceway<0)free = true;
			}
			calcAniframe();
			refreshSprite();
			updateBoxes();
			nextStep();
			checkDamageNumbers();
		}
	}
	public Enemy(int x, int y, Player prey){
		atkcooldown = 2000;
		attackbox = new Rectangle();
		this.x = x;
		this.y = y;
		stats = new BaseStats();
		stats.hp.max =200;
		stats.hp.real = stats.hp.max;
		speed = 100;
		moving = true;
		this.prey = prey;
		constructorHelp("Sprites/Chars/zombie.png");

	}
	private void updateReachbox(){
		reachbox = new Rectangle((int)x-20, (int) (y-20), sizex+40, sizey+40);
	}
	@Override
	public void drawMe(Graphics g) {
		g.drawImage(sprite, (int)x, (int)y, null);
		g.setColor(Color.red);
		g.fillRect((int)(x+(sizex/2)-20), (int)y-10, 40, 5);
		g.setColor(Color.green);
		HPbarLength =((double)stats.hp.real/(double)stats.hp.max)*40;
		g.fillRect((int)(x+(sizex/2)-20), (int)y-10, (int)HPbarLength, 5);
		for(DamageNumber dmgnum:damnumbers){
			dmgnum.drawMe(g);
		}

	}
	@Override
	public int computeDamage() {
		return (15 - prey.equip.overdef);
	}
	@Override
	public void kill() {
		drop = new Potion(25);
		drops=true;
		remove = true;
		prey.levels.addExp(25);
	}

}
