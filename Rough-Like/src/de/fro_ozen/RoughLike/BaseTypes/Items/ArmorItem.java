package de.fro_ozen.RoughLike.BaseTypes.Items;

import java.util.ArrayList;

import de.fro_ozen.RoughLike.BaseTypes.Entitys.Player;

public abstract class ArmorItem extends BaseItem{
	public int defense;
	public String getCompareString(){
		return this.getClass()+name+defense;
	}
	public ArrayList<String> getInformation(){
		ArrayList<String> list = new ArrayList<String>();
		list.add("Defnse: +"+defense);
		return list;
	}
	public void unequip(Player p){
		p.invent.content.add(this);
		if(this instanceof HelmetItem)p.equip.helmet = null;
		else if(this instanceof ChestPlateItem)p.equip.chestPlate = null;
		else if(this instanceof GlovesItem)p.equip.gloves = null;
		else if(this instanceof BootsItem)p.equip.boots = null;
		p.equip.refreshOverdef();
	}
}
