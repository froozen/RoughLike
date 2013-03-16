package de.fro_ozen.RoughLike.BaseTypes.Entitys;

import java.awt.Rectangle;
import java.util.ArrayList;

import de.fro_ozen.RoughLike.BaseTypes.Items.BaseItem;
import de.fro_ozen.RoughLike.BaseTypes.Misc.BaseStats;
import de.fro_ozen.RoughLike.BaseTypes.Simple.VariablePair;

public abstract class BattleEntity extends CharacterEntity{
	public BaseItem drop; //Item dropped upon death (Will be changed)
	public boolean drops; //Wether item is dropped upon
	public BaseStats stats; //Stats of the BattleEntity
	public int atkdir; //Directiion of the attack
	public long lastAttack; //When the last attack was
	protected int atkcooldown; //How long it takes until the next attack
	public ArrayList<DamageNumber> damnumbers = new ArrayList<DamageNumber>(); //DamageNumbers of this BattleEntity
	public boolean attacking; //Wether tihis BattleEntity is attacking
	public Rectangle attackbox; //The itbox of the attack of this BattleEntity
	
	public abstract void kill();
	public abstract int computeDamage();

	
	void checkDamageNumbers(){
		ArrayList<DamageNumber> removers = new ArrayList<DamageNumber>();
		for(DamageNumber dmgnum:damnumbers){
			dmgnum.compute();
			if(dmgnum.remove)removers.add(dmgnum);
		}
		for(DamageNumber dmgnum:removers){
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
		stats.hp.real-=dmg;
		DamageNumber dmgnum = new DamageNumber((int)x+(sizex/2), (int)y-10, dmg, 1000);
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
