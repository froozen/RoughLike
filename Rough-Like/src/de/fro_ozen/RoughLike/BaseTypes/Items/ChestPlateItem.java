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
	public boolean use(Player p){
		if(p.equipment.chestPlate != null){
			p.inventory.content.add(p.equipment.chestPlate);
			p.equipment.chestPlate = null;
		}
		p.equipment.chestPlate = this;
		p.equipment.refreshOverdef();
		return true;
	}
}