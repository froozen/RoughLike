package de.fro_ozen.RoughLike.BaseTypes.Items;

import java.util.ArrayList;

import de.fro_ozen.RoughLike.BaseTypes.Entitys.Player;

public class ItemStack extends BaseItem{
	public BaseItem item;
	public int number;
	public ItemStack(BaseItem item){
		this.item = item;
		number = 1;
		name = item.name;
		icon = item.icon;
	}
	@Override
	public String getCompareString() {
		return null;
	}
	@Override
	public boolean use(Player p) {
		if(item.use(p)){
			number--;
			if(number<1){
				return true;
			}
		}
		return false;
	}
	@Override
	public ArrayList<String> getInformation() {
		return item.getInformation();
	}
}