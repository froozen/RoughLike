package de.fro_ozen.RoughLike.BaseTypes.Items;

import java.io.IOException;

import javax.imageio.ImageIO;

import de.fro_ozen.RoughLike.BaseTypes.Entitys.Player;

public class BootsItem extends ArmorItem{
	public BootsItem(){
		overlaySpriteLocation = "Sprites/Equipment/shoesSprite.png";
		try {
			icon = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Icons/boots.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		name = "Normal Boots";
		defense = 1;
	}
	public boolean use(Player p){
		if(p.equipment.boots != null){
			p.inventory.content.add(p.equipment.boots);
			p.equipment.boots = null;
		}
		p.equipment.boots = this;
		p.equipment.refreshOverdef();
		return true;
	}
}