package de.fro_ozen.RoughLike.BaseTypes.Entitys;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import de.fro_ozen.RoughLike.BaseTypes.Items.BootsItem;
import de.fro_ozen.RoughLike.BaseTypes.Items.ChestPlateItem;
import de.fro_ozen.RoughLike.BaseTypes.Items.GlovesItem;
import de.fro_ozen.RoughLike.BaseTypes.Items.Gun;
import de.fro_ozen.RoughLike.BaseTypes.Items.HelmetItem;
import de.fro_ozen.RoughLike.BaseTypes.Items.Potion;
import de.fro_ozen.RoughLike.BaseTypes.Items.Sword;
import de.fro_ozen.RoughLike.BaseTypes.Items.TrousersItem;
import de.fro_ozen.RoughLike.BaseTypes.Misc.BaseStats;
import de.fro_ozen.RoughLike.BaseTypes.Misc.EquipSet;
import de.fro_ozen.RoughLike.GameMechanics.GameLoop;

public class Enemy extends BattleEntity{
	Rectangle reachbox;
	Player prey;
	double HPbarLength;
	private int expDrop;
	
	public void compute() {
		if(stats.hp.real>0){
			updateReachbox();
			if(free){
				if(reachbox.intersects(prey.colissionBox)){
					if(Math.abs(prey.x-x)>Math.abs(prey.y-y)){
						if((prey.x-x)>0)direction=3;
						else direction=2;
					}
					else{
						if((prey.y-y)>0)direction =1;
						else direction=4;
					}
					if(System.currentTimeMillis()-lastAttackTime>attackCooldown){
						lastAttackTime = System.currentTimeMillis();
						attacking = true;
						if(direction == 1)attackBox = new Rectangle((int)x, (int)y+height, width, 20);
						if(direction == 2)attackBox = new Rectangle((int)x-width, (int)y+(height/2), 20, width);
						if(direction == 3)attackBox = new Rectangle((int)x+width, (int)y+(height/2), 20, width);
						if(direction == 4)attackBox = new Rectangle((int)x, (int)y-30, width, 20);
						atkDirection = direction;
					}
				}
				else{
					lastDirection = direction;
					double movex = prey.x-x;
					double movey = prey.y-y;
					if(!(movex==0||movey==0)){
						double complway = (Math.abs(movex)+Math.abs(movey));
						movex = (Math.abs(movex)/complway)*(movex/Math.abs(movex));
						movey = (Math.abs(movey)/complway)*(movey/Math.abs(movey));
					}
					else{
						if(movex==0)movey=movey/Math.abs(movey);
						else movex = movex/Math.abs(movex);
					}

					if(Math.abs(movex)>Math.abs(movey)){
						if((movex)>0)direction=3;
						else direction=2;
					}
					else{
						if((movey)>0)direction =1;
						else direction=4;
					}
					x += movex*timeSinceLastFrame*speed;
					y += movey*timeSinceLastFrame*speed;
				}
			}
			else{
				double travelway = speed*timeSinceLastFrame;
				if(forcedDirection == 1){y+=travelway; direction=4;}
				if(forcedDirection == 2){x-=travelway; direction = 3;}
				if(forcedDirection == 3){x+=travelway; direction = 2;}
				if(forcedDirection == 4){y-=travelway; direction = 1;}
				forceWay -=travelway;
				if(forceWay<0)free = true;
			}
			calcAniframe();
			refreshSprite();
			updateBoxes();
			nextStep();
			checkFloatingTexts();
			
			if(equipment.helmet != null)helmetSprite = createCharacterSprite(equipment.helmet.overlaySpriteLocation);
			if(equipment.chestPlate != null)armorSprite = createCharacterSprite(equipment.chestPlate.overlaySpriteLocation);
			if(equipment.gloves != null)glovesSprite = createCharacterSprite(equipment.gloves.overlaySpriteLocation);
			if(equipment.boots != null)bootsSprite = createCharacterSprite(equipment.boots.overlaySpriteLocation);
			if(equipment.trousers != null)trousersSprite = createCharacterSprite(equipment.trousers.overlaySpriteLocation);
		}
	}
	public Enemy(int x, int y, Player prey){
		attackCooldown = 2000;
		attackBox = new Rectangle();
		this.x = x;
		this.y = y;
		stats = new BaseStats();
		stats.hp.max =(int) (50 * Math.pow(1.1, GameLoop.player.levels.Level));
		stats.hp.real = stats.hp.max;
		speed = 100;
		moving = true;
		this.prey = prey;
		constructorHelp("Sprites/Chars/zombie.png");
		equipment = new EquipSet();
		generateEquipment();
		equipment.mainHand = new Sword(prey.levels.Level);
		equipment.refreshOverdef();
		generateDrop();
		generateExpDrop();
	}
	private void updateReachbox(){
		reachbox = new Rectangle((int)x-20, (int) (y-20), width+40, height+40);
	}
	private void generateEquipment(){
		if(prey.levels.Level>1)equipment.helmet = new HelmetItem();
		if(prey.levels.Level>3)equipment.boots = new BootsItem();
		if(prey.levels.Level>3)equipment.gloves = new GlovesItem();
		if(prey.levels.Level>6)equipment.trousers = new TrousersItem();
		if(prey.levels.Level>8)equipment.chestPlate = new ChestPlateItem();
	}
	private void generateDrop(){
		double randomNumber = Math.random();
		if(equipment.helmet != null || equipment.chestPlate != null || equipment.trousers != null || equipment.boots != null || equipment.gloves != null){
			if(randomNumber<0.4){
				randomNumber = Math.random();
				if(randomNumber<0.75)dropItem = equipment.mainHand;
				else dropItem = new Gun(prey.levels.Level);
			}
			else{
				int equipNumber = 0;
				if(equipment.helmet != null)equipNumber++;
				if(equipment.chestPlate != null)equipNumber++;
				if(equipment.trousers != null)equipNumber++;
				if(equipment.boots != null)equipNumber++;
				if(equipment.gloves != null)equipNumber++;
				double dropRate = 1/equipNumber;
				int itemNumber = 1;
				
				randomNumber = Math.random();
				if(equipment.helmet != null && dropItem == null){
					if(randomNumber<dropRate*itemNumber)dropItem = equipment.helmet;
					else itemNumber++;
				}
				if(equipment.chestPlate != null && dropItem == null){
					if(randomNumber<dropRate*itemNumber)dropItem = equipment.chestPlate;
					else itemNumber++;
				}
				if(equipment.trousers != null && dropItem == null){
					if(randomNumber<dropRate*itemNumber)dropItem = equipment.trousers;
					else itemNumber++;
				}
				if(equipment.boots != null && dropItem == null){
					if(randomNumber<dropRate*itemNumber)dropItem = equipment.boots;
					else itemNumber++;
				}
				if(equipment.gloves != null && dropItem == null){
					if(randomNumber<dropRate*itemNumber)dropItem = equipment.gloves;
					else itemNumber++;
				}
			}
		}
		else{
			randomNumber = Math.random();
			if(randomNumber<0.75)dropItem = equipment.mainHand;
			else dropItem = new Gun(prey.levels.Level);
		}
		
		randomNumber = Math.random();
		if(randomNumber<0.4){
			randomNumber = Math.random();
			if(randomNumber<0.4)dropItem = new Potion(20, "MP");
			else dropItem = new Potion(25);
		}
		else if(randomNumber<0.7)dropItem = null;
	}
	private void generateExpDrop(){
		expDrop = GameLoop.player.levels.Level*3 + 12;
	}
	@Override
	public void drawMe(Graphics g) {
		g.drawImage(sprite, (int)x, (int)y, null);
		
		if(equipment.chestPlate != null)g.drawImage(armorSprite, (int)x, (int)y, null);
		if(equipment.helmet != null)g.drawImage(helmetSprite, (int)x, (int)y, null);
		if(equipment.boots != null)g.drawImage(bootsSprite, (int)x, (int)y, null);
		if(equipment.gloves != null)g.drawImage(glovesSprite, (int)x, (int)y, null);
		if(equipment.trousers != null)g.drawImage(trousersSprite, (int)x, (int)y, null);
		
		g.setColor(Color.red);
		g.fillRect((int)(x+(width/2)-20), (int)y-10, 40, 5);
		g.setColor(Color.green);
		HPbarLength =((double)stats.hp.real/(double)stats.hp.max)*40;
		g.fillRect((int)(x+(width/2)-20), (int)y-10, (int)HPbarLength, 5);
		for(FloatingText dmgnum:floatingText){
			dmgnum.drawMe(g);
		}
	}
	@Override
	public int computeDamage() {
		if(equipment.mainHand != null)return equipment.mainHand.computeDamage() / 2;
		else return 15;
	}
	@Override
	public void kill() {
		remove = true;
		prey.levels.addExp(expDrop);
	}
}