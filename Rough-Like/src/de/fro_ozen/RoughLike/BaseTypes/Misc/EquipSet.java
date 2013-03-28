package de.fro_ozen.RoughLike.BaseTypes.Misc;

import java.util.HashMap;

import de.fro_ozen.RoughLike.BaseTypes.Items.BaseItem;
import de.fro_ozen.RoughLike.BaseTypes.Items.BaseWeapon;
import de.fro_ozen.RoughLike.BaseTypes.Items.BootsItem;
import de.fro_ozen.RoughLike.BaseTypes.Items.ChestPlateItem;
import de.fro_ozen.RoughLike.BaseTypes.Items.GlovesItem;
import de.fro_ozen.RoughLike.BaseTypes.Items.HelmetItem;
import de.fro_ozen.RoughLike.BaseTypes.Items.OffHandItem;


public class EquipSet {
	public BaseWeapon mainHand;
	public OffHandItem offHand;
	public ChestPlateItem chestPlate;
	public HelmetItem helmet;
	public BootsItem boots;
	public GlovesItem gloves;
	public int overdef;
	
	public void refreshOverdef(){
		overdef = 0;
		if(chestPlate != null)overdef+=chestPlate.defense;
		if(helmet != null)overdef+=helmet.defense;
		if(boots != null)overdef+=boots.defense;
		if(gloves != null)overdef+=gloves.defense;
	}
}