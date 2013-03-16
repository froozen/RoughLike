package de.fro_ozen.RoughLike.GameMechanics.Menus;

import de.fro_ozen.GUI.elements.GItemArea;
import de.fro_ozen.GUI.elements.GItemField;
import de.fro_ozen.GUI.elements.GItemFieldListener;
import de.fro_ozen.GUI.elements.GMenu;
import de.fro_ozen.GUI.elements.GTextArea;
import de.fro_ozen.RoughLike.BaseTypes.Entitys.Player;
import de.fro_ozen.RoughLike.GameMechanics.GameLoop;

public class InventoryMenu extends GMenu{
	private GItemArea inventory;
	private GTextArea descriptions;
	private Player owner;
	
	public InventoryMenu(Player player){
		owner= player;
		triggerKey = "inventory";
		
		setBounds(100,105,600,390);
		
		descriptions = new GTextArea();
		descriptions.setBounds(425, 200, 165, 180);
		
		inventory = new GItemArea();
		inventory.setDisplayItems(owner.invent.content);
		inventory.setBounds(10, 10, 405, 380);
		inventory.setItemFieldListener(new InventoryFieldHandler());
		inventory.setDescriptionArea(descriptions);
		
		add(descriptions);
		add(inventory);
		setShift();
	}
	
	private class InventoryFieldHandler extends GItemFieldListener{

		@Override
		public void activate(GItemField source) {
			if(source.display != null)source.display.use(GameLoop.player);
		}
		
	}
}