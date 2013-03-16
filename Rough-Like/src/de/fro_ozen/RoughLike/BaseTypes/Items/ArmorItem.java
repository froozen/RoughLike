package de.fro_ozen.RoughLike.BaseTypes.Items;

import java.util.ArrayList;

import de.fro_ozen.RoughLike.BaseTypes.Entitys.Player;

public abstract class ArmorItem extends BaseItem{
	public abstract void unequip(Player p);
	public int defense;
	public String getCompareString(){
		return this.getClass()+name+defense;
	}
	public ArrayList<String> getInformation(){
		ArrayList<String> list = new ArrayList<String>();
		list.add(name);
		list.add("Defnse: +"+defense);
		return list;
	}
}
