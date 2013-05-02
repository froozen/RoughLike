package de.fro_ozen.RoughLike.BaseTypes.Items;

import java.io.IOException;

import javax.imageio.ImageIO;

import de.fro_ozen.RoughLike.BaseTypes.Entitys.Player;

public class HelmetItem extends ArmorItem{
	public HelmetItem(){
		overlaySpriteLocation = "Sprites/Equipment/helmetSprite.png";
		try {
			icon = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Icons/helmet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		name = "Normal Helmet";
		defense = 2;
	}
	public boolean use(Player p){
		if(p.equipment.helmet != null){
			p.invent.content.add(p.equipment.helmet);
			p.equipment.helmet = null;
		}
		p.equipment.helmet = this;
		p.equipment.refreshOverdef();
		return true;
	}
}
