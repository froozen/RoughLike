package de.fro_ozen.RoughLike.BaseTypes.Entitys;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import de.fro_ozen.RoughLike.BaseTypes.Items.BaseItem;
import de.fro_ozen.RoughLike.BaseTypes.Misc.BaseStats;
import de.fro_ozen.RoughLike.BaseTypes.Misc.EquipSet;
import de.fro_ozen.RoughLike.BaseTypes.Simple.VariablePair;

public abstract class BattleEntity extends CharacterEntity{
	public BaseItem drop; //Item dropped upon death (Will be changed)
	public BaseStats stats; //Stats of the BattleEntity
	public int atkdir; //Directiion of the attack
	public long lastAttack; //When the last attack was
	protected int atkcooldown; //How long it takes until the next attack
	public ArrayList<FloatingText> damnumbers = new ArrayList<FloatingText>(); //FloatingTexts of this BattleEntity
	public boolean attacking; //Wether this BattleEntity is attacking
	public Rectangle attackbox; //The itbox of the attack of this BattleEntity
	public EquipSet equip; //Equipment of the BattleEntity
	protected BufferedImage helmetSprite, armorSprite, bootsSprite, glovesSprite, trousersSprite; //Sprites of the Equipment
	
	public abstract void kill();
	public abstract int computeDamage();

	
	void checkFloatingTexts(){
		ArrayList<FloatingText> removers = new ArrayList<FloatingText>();
		for(FloatingText dmgnum:damnumbers){
			dmgnum.compute();
			if(dmgnum.remove)removers.add(dmgnum);
		}
		for(FloatingText dmgnum:removers){
			damnumbers.remove(dmgnum);
		}
	}
	
	public boolean isHitting(BaseEntity e){
		return attackbox.intersects(e.colission);
	}
	
	protected void regenHP(){
		if(stats.hp.real<stats.hp.max)stats.hp.real += (double)stats.hpregen*timeSinceLastFrame;
		if(stats.hp.real>stats.hp.max)stats.hp.real = stats.hp.max;
	}
	
	public void inflictDamage(int dmg, int pushdir) {
		if(dmg>0){
			dmg-=equip.overdef;
			if(dmg<0)dmg = 0;
		}
		stats.hp.real-=dmg;
		if(stats.hp.real >= stats.hp.max)stats.hp.real = stats.hp.max;
		FloatingText dmgnum = new FloatingText((int)x+(sizex/2), (int)y-10, dmg, 1000);
		damnumbers.add(dmgnum);
		free = false;
		forcedir = pushdir;
		forceway = dmg;
	}
	
	public class Stats{
		public VariablePair hp;
		public VariablePair mp;
		public double hpregen, mpregen;
		public Stats(){
			hp = new VariablePair();
			mp = new VariablePair();
		}
	}
}
