package de.fro_ozen.RoughLike.BaseTypes.Items;

import java.io.IOException;

import javax.imageio.ImageIO;

import de.fro_ozen.RoughLike.BaseTypes.Entitys.Player;

public class TrousersItem extends ArmorItem{
	public TrousersItem(){
		overlaySpriteLocation = "Sprites/Equipment/trousersSprite.png";
		try {
			icon = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Icons/trousers.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		name = "Normal Trousers";
		defense = 3;
	}
	public boolean use(Player p){
		if(p.equipment.trousers != null){
			p.invent.content.add(p.equipment.trousers);
			p.equipment.trousers = null;
		}
		p.equipment.trousers = this;
		p.equipment.refreshOverdef();
		return true;
	}
}
