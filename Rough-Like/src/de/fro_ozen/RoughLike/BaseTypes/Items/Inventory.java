package de.fro_ozen.RoughLike.BaseTypes.Items;

import java.util.ArrayList;

import de.fro_ozen.RoughLike.BaseTypes.Entitys.Player;

public class Inventory {
	public ArrayList<BaseItem> content;
	public Inventory(){
		content = new ArrayList<BaseItem>();
	}
	public void addItem(BaseItem item){
		boolean stackexists = false;
		if(item.stackAble){
			for(BaseItem item2:content){
				if(!stackexists){
					if(item2 instanceof ItemStack){
						ItemStack stack = (ItemStack)item2;
						if(stack.item.getCompareString().equals(item.getCompareString())){
							stack.number++;
							stackexists = true;
						}
					}
				}
			}
			if(!stackexists){
				ItemStack s = new ItemStack(item);
				content.add(s);
			}
		}
		else{
			content.add(item);
		}
	}
	public String toString(){
		String outstring = "";
		for(BaseItem item:content){
			outstring+=item+"\n";
		}
		return outstring;
	}
}