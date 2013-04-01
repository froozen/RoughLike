package de.fro_ozen.RoughLike.BaseTypes.Items;

import java.io.IOException;

import javax.imageio.ImageIO;

import de.fro_ozen.RoughLike.BaseTypes.Entitys.Player;

public class ChestPlateItem extends ArmorItem{
	public ChestPlateItem(){
		overlaySpriteLocation = "Sprites/Equipment/armorSprite.png";
		try {
			icon = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Icons/armor.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		name = "Normal Armor";
		defense = 5;
	}
	public void use(Player p){
		if(p.equip.chestPlate != null){
			p.invent.content.add(p.equip.chestPlate);
			p.equip.chestPlate = null;
		}
		p.equip.chestPlate = this;
		p.invent.content.remove(this);
		p.equip.refreshOverdef();
	}
}
