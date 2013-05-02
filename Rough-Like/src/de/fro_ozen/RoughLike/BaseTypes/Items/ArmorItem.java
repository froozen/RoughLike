package de.fro_ozen.RoughLike.BaseTypes.Items;

import java.util.ArrayList;

import de.fro_ozen.RoughLike.BaseTypes.Entitys.Player;

public abstract class ArmorItem extends BaseItem{
	public int defense;
	public String overlaySpriteLocation;
	public String getCompareString(){
		return this.getClass()+name+defense;
	}
	public ArrayList<String> getInformation(){
		ArrayList<String> list = new ArrayList<String>();
		list.add("Defense: +"+defense);
		return list;
	}
	public void unequip(Player p){
		p.invent.content.add(this);
		if(this instanceof HelmetItem)p.equipment.helmet = null;
		else if(this instanceof ChestPlateItem)p.equipment.chestPlate = null;
		else if(this instanceof GlovesItem)p.equipment.gloves = null;
		else if(this instanceof BootsItem)p.equipment.boots = null;
		else if(this instanceof TrousersItem)p.equipment.trousers = null;
		p.equipment.refreshOverdef();
	}
}
