package de.fro_ozen.RoughLike.GameMechanics.Menus;

import de.fro_ozen.GUI.elements.GEquipmentArea;
import de.fro_ozen.GUI.elements.GItemArea;
import de.fro_ozen.GUI.elements.GItemField;
import de.fro_ozen.GUI.elements.GItemFieldListener;
import de.fro_ozen.GUI.elements.GMenu;
import de.fro_ozen.GUI.elements.GTextArea;
import de.fro_ozen.RoughLike.BaseTypes.Entitys.Player;
import de.fro_ozen.RoughLike.BaseTypes.Items.ArmorItem;
import de.fro_ozen.RoughLike.BaseTypes.Items.BaseWeapon;
import de.fro_ozen.RoughLike.BaseTypes.Items.OffHandItem;
import de.fro_ozen.RoughLike.GameMechanics.GameLoop;

public class InventoryMenu extends GMenu{
	private GItemArea inventory;
	private GTextArea descriptions;
	private GEquipmentArea equipment;
	
	public InventoryMenu(Player player){
		triggerKey = "inventory";
		
		setBounds(100,105,600,390);
		
		descriptions = new GTextArea();
		descriptions.setBounds(425, 200, 165, 180);
		
		inventory = new GItemArea();
		inventory.setDisplayItems(GameLoop.player.invent.content);
		inventory.setBounds(10, 10, 405, 380);
		inventory.setItemFieldListener(new InventoryFieldHandler());
		inventory.setDescriptionArea(descriptions);
		
		equipment = new GEquipmentArea();
		equipment.setBounds(425, 10, 0, 0);
		equipment.setDescriptionArea(descriptions);
		equipment.setItemFieldListener(new EquipmentFieldHandler());
		
		add(equipment);
		add(descriptions);
		add(inventory);
		setShift();
	}
	
	private class InventoryFieldHandler extends GItemFieldListener{
		public void activate(GItemField source) {
			if(source.display != null)source.display.use(GameLoop.player);
		}
	}
	private class EquipmentFieldHandler extends GItemFieldListener{
		public void activate(GItemField source) {
			if(source.display != null){
				if(source.display instanceof ArmorItem)((ArmorItem)source.display).unequip(GameLoop.player);
				else if(source.display instanceof BaseWeapon)((BaseWeapon)source.display).unequip(GameLoop.player);
				else if(source.display instanceof OffHandItem)((OffHandItem)source.display).unequip(GameLoop.player);
			}
		}
	}
}