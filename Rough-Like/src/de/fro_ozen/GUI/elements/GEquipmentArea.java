package de.fro_ozen.GUI.elements;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import de.fro_ozen.RoughLike.GameMechanics.GameLoop;

public class GEquipmentArea extends BaseElement{
	private ArrayList<GItemField> fields;
	private final int fieldsize = 31;
	private GItemField mainHand, offHand, helmet, chestPlate, boots, gloves;

	//Calls the checkMe() method of the ItemFields
	public void checkMe() {
		for(GItemField field:fields)field.checkMe();

		//Keeps the Items in the ItemFields up-to-date
		if(GameLoop.player.equip.mainHand != mainHand.display)mainHand.display = GameLoop.player.equip.mainHand;
		if(GameLoop.player.equip.offHand != offHand.display)offHand.display = GameLoop.player.equip.offHand;
		if(GameLoop.player.equip.helmet != helmet.display)helmet.display = GameLoop.player.equip.helmet;
		if(GameLoop.player.equip.chestPlate != chestPlate.display)chestPlate.display = GameLoop.player.equip.chestPlate;
		if(GameLoop.player.equip.boots != boots.display)boots.display = GameLoop.player.equip.boots;
		if(GameLoop.player.equip.gloves != gloves.display)gloves.display = GameLoop.player.equip.gloves;
	}

	public void setBounds(int x, int y, int width, int height) {
		box = new Rectangle();
		box.x = x;
		box.y = y;
		box.height = height;
		box.width = width;

		fields = new ArrayList<GItemField>();

		mainHand = new GItemField();
		mainHand.setBounds(box.x, box.y+fieldsize, 0, 0);
		mainHand.setDisplayItem(GameLoop.player.equip.mainHand);

		offHand = new GItemField();
		offHand.setBounds(box.x+2*fieldsize, box.y+fieldsize, 0, 0);
		offHand.setDisplayItem(GameLoop.player.equip.offHand);

		helmet = new GItemField();
		helmet.setBounds(box.x+fieldsize, box.y, 0, 0);
		helmet.setDisplayItem(GameLoop.player.equip.helmet);

		chestPlate = new GItemField();
		chestPlate.setBounds(box.x+fieldsize, box.y+fieldsize, 0, 0);
		chestPlate.setDisplayItem(GameLoop.player.equip.chestPlate);

		boots = new GItemField();
		boots.setBounds(box.x+fieldsize, box.y+2*fieldsize, 0, 0);
		boots.setDisplayItem(GameLoop.player.equip.boots);

		gloves = new GItemField();
		gloves.setBounds(box.x+2*fieldsize, box.y+2*fieldsize, 0, 0);
		gloves.setDisplayItem(GameLoop.player.equip.gloves);

		fields.add(mainHand);
		fields.add(offHand);
		fields.add(helmet);
		fields.add(chestPlate);
		fields.add(boots);
		fields.add(gloves);
	}

	//Calls the setShift() method of the ItemFields
	public void setShift(int posxshift, int posyshift){
		for(GItemField field:fields)field.setShift(posxshift, posyshift);
	}

	//Calls the setItemFieldListener() method of the ItemFields
	public void setItemFieldListener(GItemFieldListener listener){
		for(GItemField field:fields)field.setItemFieldListener(listener);
	}

	//Calls the drawMe() method of the ItemFields
	public void drawMe(Graphics g){
		for(GItemField field:fields)field.drawMe(g);
	}
}