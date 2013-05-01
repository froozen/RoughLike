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
						if((movex)>0)dir=3;
						else dir=2;
					}
					else{
						if((movey)>0)dir =1;
						else dir=4;
					}
					x += movex*timeSinceLastFrame*speed;
					y += movey*timeSinceLastFrame*speed;
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
			checkFloatingTexts();
			
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
		stats.hp.max =(int) (50 * Math.pow(1.1, GameLoop.player.levels.Level));
		stats.hp.real = stats.hp.max;
		speed = 100;
		moving = true;
		this.prey = prey;
		constructorHelp("Sprites/Chars/zombie.png");
		equip = new EquipSet();
		generateEquipment();
		equip.mainHand = new Sword(prey.levels.Level);
		equip.refreshOverdef();
		generateDrop();
		generateExpDrop();
	}
	private void updateReachbox(){
		reachbox = new Rectangle((int)x-20, (int) (y-20), sizex+40, sizey+40);
	}
	private void generateEquipment(){
		if(prey.levels.Level>1)equip.helmet = new HelmetItem();
		if(prey.levels.Level>3)equip.boots = new BootsItem();
		if(prey.levels.Level>3)equip.gloves = new GlovesItem();
		if(prey.levels.Level>6)equip.trousers = new TrousersItem();
		if(prey.levels.Level>8)equip.chestPlate = new ChestPlateItem();
	}
	private void generateDrop(){
		double randomNumber = Math.random();
		if(equip.helmet != null || equip.chestPlate != null || equip.trousers != null || equip.boots != null || equip.gloves != null){
			if(randomNumber<0.4){
				randomNumber = Math.random();
				if(randomNumber<0.75)drop = equip.mainHand;
				else drop = new Gun(prey.levels.Level);
			}
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
		else{
			randomNumber = Math.random();
			if(randomNumber<0.75)drop = equip.mainHand;
			else drop = new Gun(prey.levels.Level);
		}
		
		randomNumber = Math.random();
		if(randomNumber<0.4){
			randomNumber = Math.random();
			if(randomNumber<0.4)drop = new Potion(20, "MP");
			else drop = new Potion(25);
		}
		else if(randomNumber<0.7)drop = null;
	}
	private void generateExpDrop(){
		expDrop = GameLoop.player.levels.Level*3 + 12;
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
		for(FloatingText dmgnum:damnumbers){
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
		prey.levels.addExp(expDrop);
	}
}