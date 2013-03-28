package de.fro_ozen.RoughLike.BaseTypes.Items;

import java.io.IOException;

import javax.imageio.ImageIO;

import de.fro_ozen.RoughLike.BaseTypes.Entitys.Player;

public class HelmetItem extends ArmorItem{
	public HelmetItem(){
		try {
			icon = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Icons/helmet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		name = "Normal Helmet";
		defense = 2;
	}
	public void use(Player p){
		if(p.equip.helmet != null){
			p.invent.content.add(p.equip.helmet);
			p.equip.helmet = null;
		}
		p.equip.helmet = this;
		p.invent.content.remove(this);
	}
}
