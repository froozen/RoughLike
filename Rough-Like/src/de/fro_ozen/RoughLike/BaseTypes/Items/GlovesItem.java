package de.fro_ozen.RoughLike.BaseTypes.Items;

import java.io.IOException;

import javax.imageio.ImageIO;

import de.fro_ozen.RoughLike.BaseTypes.Entitys.Player;

public class GlovesItem extends ArmorItem{
	public GlovesItem(){
		overlaySpriteLocation = "Sprites/Equipment/glovesSprite.png";
		try {
			icon = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Icons/gloves.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		name = "Normal gloves";
		defense = 1;
	}
	public boolean use(Player p){
		if(p.equip.gloves != null){
			p.invent.content.add(p.equip.gloves);
			p.equip.gloves = null;
		}
		p.equip.gloves = this;
		p.equip.refreshOverdef();
		return true;
	}
}
