package de.fro_ozen.RoughLike.BaseTypes.Entitys;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import de.fro_ozen.RoughLike.BaseTypes.Items.BaseItem;
import de.fro_ozen.RoughLike.BaseTypes.Items.BootsItem;
import de.fro_ozen.RoughLike.BaseTypes.Items.ChestPlateItem;
import de.fro_ozen.RoughLike.BaseTypes.Items.GlovesItem;
import de.fro_ozen.RoughLike.BaseTypes.Items.Gun;
import de.fro_ozen.RoughLike.BaseTypes.Items.HelmetItem;
import de.fro_ozen.RoughLike.BaseTypes.Items.Inventory;
import de.fro_ozen.RoughLike.BaseTypes.Items.Sword;
import de.fro_ozen.RoughLike.BaseTypes.Items.TrousersItem;
import de.fro_ozen.RoughLike.BaseTypes.Misc.BaseStats;
import de.fro_ozen.RoughLike.BaseTypes.Misc.EquipSet;
import de.fro_ozen.RoughLike.BaseTypes.Misc.Leveling;
import de.fro_ozen.RoughLike.BaseTypes.Spells.HealSpell;
import de.fro_ozen.RoughLike.GameMechanics.KeyInput;
import de.fro_ozen.RoughLike.GameMechanics.MouseInput;

public class Player extends BattleEntity{
	public Leveling levels;
	public GunBullet bull;
	public boolean dead, addBullet;
	public Inventory invent;
	public void compute() {
		if(free){
			if(MouseInput.leftClicked || MouseInput.rightClicked){
				if(MouseInput.leftClicked){
					if(System.currentTimeMillis()-lastAttack>atkcooldown && equip.mainHand != null){
						equip.mainHand.attack();
					}
				}
				if(MouseInput.rightClicked){
					if(equip.offHand != null){
						equip.offHand.attack();
					}
				}
			}
			else{
				lastdir = dir;
				moving = false;
				if(KeyInput.isPressed("down")){y+=speed*timeSinceLastFrame;dir = 1; moving = true;}
				if(KeyInput.isPressed("left")){x-=speed*timeSinceLastFrame;dir = 2; moving = true;}
				if(KeyInput.isPressed("right")){x+=speed*timeSinceLastFrame;dir = 3; moving = true;}
				if(KeyInput.isPressed("up")){y-=speed*timeSinceLastFrame;dir = 4; moving = true;}

				if(y<0)y=0;
				if(y>600-sizey)y=600-sizey;
				if(x<0)x=0;
				if(x>800-sizex)x=800-sizex;


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
		if(equip.helmet != null)helmetSprite = createCharacterSprite(equip.helmet.overlaySpriteLocation);
		if(equip.chestPlate != null)armorSprite = createCharacterSprite(equip.chestPlate.overlaySpriteLocation);
		if(equip.gloves != null)glovesSprite = createCharacterSprite(equip.gloves.overlaySpriteLocation);
		if(equip.boots != null)bootsSprite = createCharacterSprite(equip.boots.overlaySpriteLocation);
		if(equip.trousers != null)trousersSprite = createCharacterSprite(equip.trousers.overlaySpriteLocation);
		calcAniframe();
		refreshSprite();
		updateBoxes();
		nextStep();
		checkFloatingTexts();
		regenHP();
	}
	public Player(int x, int y){
		equip = new EquipSet();
		atkcooldown = 300;
		attackbox = new Rectangle();
		this.x = x;
		this.y = y;
		speed = 200;
		stats = new BaseStats();
		stats.hp.max = 200;
		stats.hp.real = stats.hp.max;
		stats.hpregen = 0.5;
		stats.mpregen = 0.5;
		stats.mp.max = 130;
		stats.mp.real = stats.mp.max;
		levels = new Leveling();
		constructorHelp("Sprites/Chars/player.png");
		moving = false;
		invent = new Inventory();
		equip.mainHand = new Sword(levels.Level);
		equip.offHand = new HealSpell();
		equip.chestPlate = new ChestPlateItem();
		equip.helmet = new HelmetItem();
		equip.boots = new BootsItem();
		equip.gloves = new GlovesItem();
		equip.trousers = new TrousersItem();
		invent.addItem(new Gun(levels.Level));
		equip.refreshOverdef();
	}
	public void pickUp(BaseItem item){
		invent.addItem(item);
	}
	public void drawMe(Graphics g) {
		g.drawImage(sprite, (int)x, (int)y, null);
		
		if(equip.chestPlate != null)g.drawImage(armorSprite, (int)x, (int)y, null);
		if(equip.helmet != null)g.drawImage(helmetSprite, (int)x, (int)y, null);
		if(equip.boots != null)g.drawImage(bootsSprite, (int)x, (int)y, null);
		if(equip.gloves != null)g.drawImage(glovesSprite, (int)x, (int)y, null);
		if(equip.trousers != null)g.drawImage(trousersSprite, (int)x, (int)y, null);
		
		g.setColor(Color.red);
		for(FloatingText dmgnum:damnumbers){
			dmgnum.drawMe(g);
		}
	}
	public int computeDamage() {
		if(equip.mainHand!=null)return equip.mainHand.computeDamage();
		else return 5;
	}
	public void kill() {
		dead =true;
	}
}