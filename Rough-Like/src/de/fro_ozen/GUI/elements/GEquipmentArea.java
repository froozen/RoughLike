package de.fro_ozen.GUI.elements;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import de.fro_ozen.RoughLike.GameMechanics.GameLoop;

public class GEquipmentArea extends BaseElement{
	private ArrayList<GItemField> fields;
	private final int fieldsize = 31;
	private GItemField mainHand, offHand, helmet, chestPlate, boots, gloves, trousers;

	//Calls the checkMe() method of the ItemFields
	public void checkMe() {
		for(GItemField field:fields)field.checkMe();

		//Keeps the Items in the ItemFields up-to-date
		if(GameLoop.player.equipment.mainHand != mainHand.display)mainHand.display = GameLoop.player.equipment.mainHand;
		if(GameLoop.player.equipment.offHand != offHand.display)offHand.display = GameLoop.player.equipment.offHand;
		if(GameLoop.player.equipment.helmet != helmet.display)helmet.display = GameLoop.player.equipment.helmet;
		if(GameLoop.player.equipment.chestPlate != chestPlate.display)chestPlate.display = GameLoop.player.equipment.chestPlate;
		if(GameLoop.player.equipment.boots != boots.display)boots.display = GameLoop.player.equipment.boots;
		if(GameLoop.player.equipment.gloves != gloves.display)gloves.display = GameLoop.player.equipment.gloves;
		if(GameLoop.player.equipment.trousers != trousers.display)trousers.display = GameLoop.player.equipment.trousers;
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
		mainHand.setDisplayItem(GameLoop.player.equipment.mainHand);

		offHand = new GItemField();
		offHand.setBounds(box.x+2*fieldsize, box.y+fieldsize, 0, 0);
		offHand.setDisplayItem(GameLoop.player.equipment.offHand);

		helmet = new GItemField();
		helmet.setBounds(box.x+fieldsize, box.y, 0, 0);
		helmet.setDisplayItem(GameLoop.player.equipment.helmet);

		chestPlate = new GItemField();
		chestPlate.setBounds(box.x+fieldsize, box.y+fieldsize, 0, 0);
		chestPlate.setDisplayItem(GameLoop.player.equipment.chestPlate);

		trousers = new GItemField();
		trousers.setBounds(box.x+fieldsize, box.y+2*fieldsize, 0, 0);
		trousers.setDisplayItem(GameLoop.player.equipment.trousers);
		
		boots = new GItemField();
		boots.setBounds(box.x+fieldsize, box.y+3*fieldsize, 0, 0);
		boots.setDisplayItem(GameLoop.player.equipment.boots);

		gloves = new GItemField();
		gloves.setBounds(box.x+2*fieldsize, box.y+2*fieldsize, 0, 0);
		gloves.setDisplayItem(GameLoop.player.equipment.gloves);

		fields.add(trousers);
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