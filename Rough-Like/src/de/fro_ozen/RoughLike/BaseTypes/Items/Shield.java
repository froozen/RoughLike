package de.fro_ozen.RoughLike.BaseTypes.Items;

import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Shield extends OffHandItem{
	public void attack() {
		
	}
	public Shield(){
		try {
			icon = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Icons/shield.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		name = "Normal Shield";
	}
	public ArrayList<String> getInformation(){
		ArrayList<String> list = new ArrayList<String>();
		list.add("Useless");
		return list;
	}
}