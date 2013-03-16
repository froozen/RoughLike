package de.fro_ozen.RoughLike.BaseTypes.Items;

import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import de.fro_ozen.RoughLike.BaseTypes.Entitys.Player;

public class Potion extends BaseItem{
	private int amount;
	public Potion(int amount){
		stackAble = true;
		this.amount = amount;
		name = "Health-Potion";
		try {
			icon = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Icons/potion.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getCompareString() {
		return this.getClass()+name+amount;
	}

	@Override
	public void use(Player p) {
		p.stats.hp.real += amount;
		if(p.stats.hp.real>p.stats.hp.max)p.stats.hp.real = p.stats.hp.max;
	}
	@Override
	public ArrayList<String> getInformation(){
		ArrayList<String> list = new ArrayList<String>();
		list.add(name);
		list.add("Heals: +"+amount);
		return list;
	}

}
