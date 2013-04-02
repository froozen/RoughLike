package de.fro_ozen.RoughLike.BaseTypes.Spells;

import java.util.ArrayList;

import de.fro_ozen.RoughLike.BaseTypes.Items.OffHandItem;

public abstract class BaseSpell extends OffHandItem{
	public int MPcost;
	
	public abstract String getEffectDescription();

	public ArrayList<String> getInformation() {
		ArrayList<String> description = new ArrayList<String>();
		description.add("MP-Cost: "+MPcost);
		description.add(getEffectDescription());
		return description;
	}

}
