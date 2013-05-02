package de.fro_ozen.RoughLike.BaseTypes.Entitys;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import de.fro_ozen.RoughLike.BaseTypes.Items.BaseItem;
import de.fro_ozen.RoughLike.BaseTypes.Misc.BaseStats;
import de.fro_ozen.RoughLike.BaseTypes.Misc.EquipSet;
import de.fro_ozen.RoughLike.BaseTypes.Simple.VariablePair;

public abstract class BattleEntity extends CharacterEntity{
	public BaseItem dropItem; //Item dropped upon death (Will be changed)
	public BaseStats stats; //Stats of the BattleEntity
	public int atkDirection; //Directiion of the attack
	public long lastAttackTime; //When the last attack was
	protected int attackCooldown; //How long it takes until the next attack
	public ArrayList<FloatingText> floatingText = new ArrayList<FloatingText>(); //FloatingTexts of this BattleEntity
	public boolean attacking; //Wether this BattleEntity is attacking
	public Rectangle attackBox; //The itbox of the attack of this BattleEntity
	public EquipSet equipment; //Equipment of the BattleEntity
	protected BufferedImage helmetSprite, armorSprite, bootsSprite, glovesSprite, trousersSprite; //Sprites of the Equipment
	
	public abstract void kill();
	public abstract int computeDamage();

	
	void checkFloatingTexts(){
		ArrayList<FloatingText> removers = new ArrayList<FloatingText>();
		for(FloatingText dmgnum:floatingText){
			dmgnum.compute();
			if(dmgnum.remove)removers.add(dmgnum);
		}
		for(FloatingText dmgnum:removers){
			floatingText.remove(dmgnum);
		}
	}
	
	public boolean isHitting(BaseEntity e){
		return attackBox.intersects(e.colissionBox);
	}
	
	protected void regenerateHP(){
		if(stats.hp.real<stats.hp.max)stats.hp.real += (double)stats.hpregen*timeSinceLastFrame;
		if(stats.hp.real>stats.hp.max)stats.hp.real = stats.hp.max;
	}
	
	public void inflictDamage(int damage, int pushDirection) {
		if(damage>0){
			damage-=equipment.overdef;
			if(damage<0)damage = 0;
		}
		stats.hp.real-=damage;
		if(stats.hp.real >= stats.hp.max)stats.hp.real = stats.hp.max;
		FloatingText dmgnum = new FloatingText((int)x+(width/2), (int)y-10, damage, 1000);
		floatingText.add(dmgnum);
		free = false;
		forcedDirection = pushDirection;
		forceWay = damage;
	}
}
