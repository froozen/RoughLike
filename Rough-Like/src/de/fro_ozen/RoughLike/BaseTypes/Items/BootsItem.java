package de.fro_ozen.RoughLike.BaseTypes.Items;

import java.io.IOException;

import javax.imageio.ImageIO;

import de.fro_ozen.RoughLike.BaseTypes.Entitys.Player;

public class BootsItem extends ArmorItem{
	public BootsItem(){
		try {
			icon = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Icons/boots.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		name = "Normal Boots";
		defense = 1;
	}
	public void use(Player p){
		if(p.equip.boots != null){
			p.invent.content.add(p.equip.boots);
			p.equip.boots = null;
		}
		p.equip.boots = this;
		p.invent.content.remove(this);
	}
}