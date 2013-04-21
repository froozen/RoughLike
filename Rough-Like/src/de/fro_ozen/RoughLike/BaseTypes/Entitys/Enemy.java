package de.fro_ozen.RoughLike.BaseTypes.Entitys;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import de.fro_ozen.RoughLike.BaseTypes.Items.HelmetItem;
import de.fro_ozen.RoughLike.BaseTypes.Items.Potion;
import de.fro_ozen.RoughLike.BaseTypes.Items.Sword;
import de.fro_ozen.RoughLike.BaseTypes.Misc.BaseStats;
import de.fro_ozen.RoughLike.BaseTypes.Misc.EquipSet;

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
			
			if(equip.helmet != null)helmetSprite = createCharacterSprite(equip.helmet.overlaySpriteLocation);
			if(equip.chestPlate != null)armorSprite = createCharacterSprite(equip.chestPlate.overlaySpriteLocation);
			if(equip.gloves != null)glovesSprite = createCharacterSprite(equip.gloves.overlaySpriteLocation);
			if(equip.boots != null)bootsSprite = createCharacterSprite(equip.boots.overlaySpriteLocation);
			if(equip.trousers != null)trousersSprite = createCharacterSprite(equip.trousers.overlaySpriteLocation);
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
		equip = new EquipSet();
		equip.helmet = new HelmetItem();
		equip.mainHand = new Sword(prey.levels.Level);
		equip.refreshOverdef();
		generateDrop();
	}
	private void updateReachbox(){
		reachbox = new Rectangle((int)x-20, (int) (y-20), sizex+40, sizey+40);
	}
	private void generateDrop(){
		double randomNumber = Math.random();
		if(equip.helmet != null || equip.chestPlate != null || equip.trousers != null || equip.boots != null || equip.gloves != null){
			if(randomNumber<0.35)drop = equip.mainHand;
			else{
				int equipNumber = 0;
				if(equip.helmet != null)equipNumber++;
				if(equip.chestPlate != null)equipNumber++;
				if(equip.trousers != null)equipNumber++;
				if(equip.boots != null)equipNumber++;
				if(equip.gloves != null)equipNumber++;
				double dropRate = 1/equipNumber;
				int itemNumber = 1;
				
				randomNumber = Math.random();
				if(equip.helmet != null && drop == null){
					if(randomNumber<dropRate*itemNumber)drop = equip.helmet;
					else itemNumber++;
				}
				if(equip.chestPlate != null && drop == null){
					if(randomNumber<dropRate*itemNumber)drop = equip.chestPlate;
					else itemNumber++;
				}
				if(equip.trousers != null && drop == null){
					if(randomNumber<dropRate*itemNumber)drop = equip.trousers;
					else itemNumber++;
				}
				if(equip.boots != null && drop == null){
					if(randomNumber<dropRate*itemNumber)drop = equip.boots;
					else itemNumber++;
				}
				if(equip.gloves != null && drop == null){
					if(randomNumber<dropRate*itemNumber)drop = equip.gloves;
					else itemNumber++;
				}
			}
		}
		else drop = equip.mainHand;
		
		randomNumber = Math.random();
		if(randomNumber<0.6)drop = new Potion(25);
		else if(randomNumber<0.7)drop = null;
	}
	@Override
	public void drawMe(Graphics g) {
		g.drawImage(sprite, (int)x, (int)y, null);
		
		if(equip.chestPlate != null)g.drawImage(armorSprite, (int)x, (int)y, null);
		if(equip.helmet != null)g.drawImage(helmetSprite, (int)x, (int)y, null);
		if(equip.boots != null)g.drawImage(bootsSprite, (int)x, (int)y, null);
		if(equip.gloves != null)g.drawImage(glovesSprite, (int)x, (int)y, null);
		if(equip.trousers != null)g.drawImage(trousersSprite, (int)x, (int)y, null);
		
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
		if(equip.mainHand != null)return equip.mainHand.computeDamage();
		else return 15;
	}
	@Override
	public void kill() {
		remove = true;
		prey.levels.addExp(15);
	}
}