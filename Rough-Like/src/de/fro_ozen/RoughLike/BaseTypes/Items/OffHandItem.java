package de.fro_ozen.RoughLike.BaseTypes.Items;

import de.fro_ozen.RoughLike.BaseTypes.Entitys.Player;

public abstract class OffHandItem extends BaseItem{
	public abstract void attack();
	public String getCompareString(){
		return this.getClass()+name;
	}
	public void use(Player p){
		if(p.equip.offHand != null){
			p.invent.content.add(p.equip.offHand);
			p.equip.offHand = null;
		}
		p.equip.offHand = this;
		p.invent.content.remove(this);
	}
	public void unequip(Player p){
		p.invent.content.add(this);
		p.equip.offHand = null;
	}
}
