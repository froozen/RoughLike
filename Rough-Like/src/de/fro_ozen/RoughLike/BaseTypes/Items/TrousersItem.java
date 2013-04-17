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
		if(p.equip.trousers != null){
			p.invent.content.add(p.equip.trousers);
			p.equip.trousers = null;
		}
		p.equip.trousers = this;
		p.equip.refreshOverdef();
		return true;
	}
}
