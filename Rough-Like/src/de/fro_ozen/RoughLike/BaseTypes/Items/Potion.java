package de.fro_ozen.RoughLike.BaseTypes.Items;

import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import de.fro_ozen.RoughLike.BaseTypes.Entitys.Player;

public class Potion extends BaseItem{
	private int amount;
	private String healStat;

	public Potion(int amount){
		stackAble = true;
		healStat = "HP";
		this.amount = amount;
		name = "HP-Potion";
		try {
			icon = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Icons/potion.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Potion(int amount, String healStat){
		stackAble = true;
		this.healStat = healStat;
		this.amount = amount;
		name = healStat+"-Potion";
		if(healStat.equals("MP")){
			try {
				icon = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Icons/mppotion.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			try {
				icon = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Icons/potion.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			healStat = "HP";
		}
	}
	public String getCompareString() {
		return this.getClass()+name+amount+healStat;
	}

	@Override
	public boolean use(Player p) {
		if(healStat.equals("MP")){
			if(p.stats.mp.real != p.stats.mp.max){
				p.stats.mp.real += amount;
				if(p.stats.mp.real>p.stats.mp.max)p.stats.mp.real=p.stats.mp.max;
				return true;
			}
		}
		else if(p.stats.hp.real != p.stats.hp.max){
			p.inflictDamage(-amount, 5);
			return true;
		}
		return false;
	}
	@Override
	public ArrayList<String> getInformation(){
		ArrayList<String> list = new ArrayList<String>();
		list.add("Heals: +"+amount+" "+healStat);
		return list;
	}

}
