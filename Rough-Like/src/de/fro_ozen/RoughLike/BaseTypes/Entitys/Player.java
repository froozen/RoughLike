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
	public boolean dead;
	public Inventory inventory;
	public void compute() {
		if(free){
			if(MouseInput.leftClicked || MouseInput.rightClicked){
				if(MouseInput.leftClicked){
					if(System.currentTimeMillis()-lastAttackTime>attackCooldown && equipment.mainHand != null){
						equipment.mainHand.attack();
					}
				}
				if(MouseInput.rightClicked){
					if(equipment.offHand != null){
						equipment.offHand.attack();
					}
				}
			}
			else{
				lastDirection = direction;
				moving = false;
				if(KeyInput.isPressed("down")){y+=speed*timeSinceLastFrame;direction = 1; moving = true;}
				if(KeyInput.isPressed("left")){x-=speed*timeSinceLastFrame;direction = 2; moving = true;}
				if(KeyInput.isPressed("right")){x+=speed*timeSinceLastFrame;direction = 3; moving = true;}
				if(KeyInput.isPressed("up")){y-=speed*timeSinceLastFrame;direction = 4; moving = true;}

				if(y<0)y=0;
				if(y>600-height)y=600-height;
				if(x<0)x=0;
				if(x>800-width)x=800-width;


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
		if(equipment.helmet != null)helmetSprite = createCharacterSprite(equipment.helmet.overlaySpriteLocation);
		if(equipment.chestPlate != null)armorSprite = createCharacterSprite(equipment.chestPlate.overlaySpriteLocation);
		if(equipment.gloves != null)glovesSprite = createCharacterSprite(equipment.gloves.overlaySpriteLocation);
		if(equipment.boots != null)bootsSprite = createCharacterSprite(equipment.boots.overlaySpriteLocation);
		if(equipment.trousers != null)trousersSprite = createCharacterSprite(equipment.trousers.overlaySpriteLocation);
		calcAniframe();
		refreshSprite();
		updateBoxes();
		nextStep();
		checkFloatingTexts();
		regenerateHP();
	}
	public Player(int x, int y){
		equipment = new EquipSet();
		attackCooldown = 300;
		attackBox = new Rectangle();
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
		inventory = new Inventory();
		equipment.mainHand = new Sword(levels.Level);
		equipment.offHand = new HealSpell();
		equipment.chestPlate = new ChestPlateItem();
		equipment.helmet = new HelmetItem();
		equipment.boots = new BootsItem();
		equipment.gloves = new GlovesItem();
		equipment.trousers = new TrousersItem();
		inventory.addItem(new Gun(levels.Level));
		equipment.refreshOverdef();
	}
	public void pickUp(BaseItem item){
		inventory.addItem(item);
	}
	public void drawMe(Graphics g) {
		g.drawImage(sprite, (int)x, (int)y, null);
		
		if(equipment.chestPlate != null)g.drawImage(armorSprite, (int)x, (int)y, null);
		if(equipment.helmet != null)g.drawImage(helmetSprite, (int)x, (int)y, null);
		if(equipment.boots != null)g.drawImage(bootsSprite, (int)x, (int)y, null);
		if(equipment.gloves != null)g.drawImage(glovesSprite, (int)x, (int)y, null);
		if(equipment.trousers != null)g.drawImage(trousersSprite, (int)x, (int)y, null);
		
		g.setColor(Color.red);
		for(FloatingText dmgnum:floatingText){
			dmgnum.drawMe(g);
		}
	}
	public int computeDamage() {
		if(equipment.mainHand!=null)return equipment.mainHand.computeDamage();
		else return 5;
	}
	public void kill() {
		dead =true;
	}
}