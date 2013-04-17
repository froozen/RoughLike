package de.fro_ozen.RoughLike.BaseTypes.Items;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import de.fro_ozen.RoughLike.BaseTypes.Entitys.Player;

public abstract class BaseItem {
	public String name;
	public BufferedImage icon;
	public boolean stackAble;
	
	public abstract boolean use(Player p);
	public abstract String getCompareString();
	public abstract ArrayList<String> getInformation();
}