package de.fro_ozen.RoughLike.BaseTypes.Items;

import java.util.ArrayList;
import java.util.Random;

import de.fro_ozen.RoughLike.BaseTypes.Entitys.Player;
import de.fro_ozen.RoughLike.GameMechanics.GameLoop;

public abstract class BaseWeapon extends BaseItem{
	Random ran = new Random();
	public int atk , atkgap, mag, maggap;
	
	public abstract void attack();
	
	
	public String getCompareString(){
		return this.getClass()+name+atk+atkgap+mag+maggap;
	}
	public ArrayList<String> getInformation(){
		ArrayList<String> list = new ArrayList<String>();
		list.add(name);
		list.add("Damage: "+atk+"~"+(atk+atkgap));
		return list;
	}
	public void use(Player p){
		if(GameLoop.player.equip.mainHand != null){
			GameLoop.player.invent.content.add(GameLoop.player.equip.mainHand);
			GameLoop.player.equip.mainHand = null;
		}
		GameLoop.player.equip.mainHand = this;
		GameLoop.player.invent.content.remove(this);
	}
	public void unequip(Player p){
		p.invent.content.add(this);
		p.equip.mainHand = null;
	}

	public int computeDamage(){
		if(atkgap!=0)return atk+ran.nextInt(atkgap);
		else return atk;
	}
}