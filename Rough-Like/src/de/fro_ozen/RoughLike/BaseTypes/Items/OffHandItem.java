package de.fro_ozen.RoughLike.BaseTypes.Items;

import de.fro_ozen.RoughLike.BaseTypes.Entitys.Player;

public abstract class OffHandItem extends BaseItem{
	public abstract void attack();
	public String getCompareString(){
		return this.getClass()+name;
	}
	public boolean use(Player p){
		if(p.equipment.offHand != null){
			p.invent.content.add(p.equipment.offHand);
			p.equipment.offHand = null;
		}
		p.equipment.offHand = this;
		return true;
	}
	public void unequip(Player p){
		p.invent.content.add(this);
		p.equipment.offHand = null;
	}
}
