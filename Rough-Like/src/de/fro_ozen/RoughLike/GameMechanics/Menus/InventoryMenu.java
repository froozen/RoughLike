package de.fro_ozen.RoughLike.GameMechanics.Menus;

import de.fro_ozen.GUI.elements.GBar;
import de.fro_ozen.GUI.elements.GEquipmentArea;
import de.fro_ozen.GUI.elements.GIconBox;
import de.fro_ozen.GUI.elements.GItemArea;
import de.fro_ozen.GUI.elements.GItemField;
import de.fro_ozen.GUI.elements.GItemFieldListener;
import de.fro_ozen.GUI.elements.GLabel;
import de.fro_ozen.GUI.elements.GMenu;
import de.fro_ozen.GUI.elements.GTextArea;
import de.fro_ozen.RoughLike.BaseTypes.Entitys.Player;
import de.fro_ozen.RoughLike.BaseTypes.Items.ArmorItem;
import de.fro_ozen.RoughLike.BaseTypes.Items.BaseItem;
import de.fro_ozen.RoughLike.BaseTypes.Items.BaseWeapon;
import de.fro_ozen.RoughLike.BaseTypes.Items.ItemStack;
import de.fro_ozen.RoughLike.BaseTypes.Items.OffHandItem;
import de.fro_ozen.RoughLike.GameMechanics.GameLoop;
import de.fro_ozen.RoughLike.GameMechanics.HUD;
import de.fro_ozen.RoughLike.GameMechanics.KeyInput;

public class InventoryMenu extends GMenu{
	private GItemArea inventory;
	private GTextArea descriptions;
	private GEquipmentArea equipment;
	private GBar hpBar, mpBar;
	private BaseItem selectedItem;
	private GIconBox selectedItemIcon;
	private GLabel selectedItemName;

	public InventoryMenu(Player player){
		triggerKey = "inventory";

		setBounds(100,105,600,390);

		hpBar = new GBar();
		hpBar.setBounds(425, 10, 165, 20);
		hpBar.setDisplayVariablePair(GameLoop.player.stats.hp);
		hpBar.setColor(GBar.RED);
		hpBar.enableText(true);
		hpBar.setVariableName("HP");

		mpBar = new GBar();
		mpBar.setBounds(425, 35, 165, 20);
		mpBar.setDisplayVariablePair(GameLoop.player.stats.mp);
		mpBar.setColor(GBar.LIGHTBLUE);
		mpBar.enableText(true);
		mpBar.setVariableName("MP");

		descriptions = new GTextArea();
		descriptions.setBounds(425, 225, 165, 160);

		inventory = new GItemArea();
		inventory.setDisplayItems(GameLoop.player.inventory.content);
		inventory.setBounds(10, 10, 405, 380);
		inventory.setItemFieldListener(new InventoryFieldHandler());

		equipment = new GEquipmentArea();
		equipment.setBounds(468, 60, 0, 0);
		equipment.setItemFieldListener(new EquipmentFieldHandler());
		
		selectedItemIcon = new GIconBox();
		selectedItemIcon.setBounds(425, 195, 0, 0);
		
		selectedItemName = new GLabel();
		selectedItemName.setBounds(455, 195, 0, 0);

		add(selectedItemIcon);
		add(selectedItemName);
		add(hpBar);
		add(mpBar);
		add(equipment);
		add(descriptions);
		add(inventory);
		setShift();
	}

	private class InventoryFieldHandler extends GItemFieldListener{
		public void leftClicked(GItemField source) {
			boolean shortcut = false;
			for(int i = 0; i<10; i++){
				if(KeyInput.isPressed(i+"")){
					ItemShortCutBar.items[i]=source.display;
					shortcut = true;
					ItemShortCutBar.lastTimeAssigned = System.currentTimeMillis();
				}
			}
			
			if(source.display != null && !shortcut){
				if(source.display.use(GameLoop.player))GameLoop.player.inventory.content.remove(source.display);
				
				if(source.display == selectedItem){
					if(!(source.display instanceof ItemStack)){
						selectedItem = null;
						selectedItemIcon.setIcon(null);
						selectedItemName.setText(null);
						descriptions.setText(null);
					}
					else if(((ItemStack)source.display).number<1){
						selectedItem = null;
						selectedItemIcon.setIcon(null);
						selectedItemName.setText(null);
						descriptions.setText(null);
					}
				}
			}
		}

		public void rightClicked(GItemField source) {
			if(source.display != null){
				selectedItem = source.display;
				selectedItemIcon.setIcon(source.display.icon);
				selectedItemName.setText(source.display.name);
				descriptions.setText(source.display.getInformation());
			}
		}
	}
	private class EquipmentFieldHandler extends GItemFieldListener{
		public void leftClicked(GItemField source) {
			if(source.display != null){
				if(source.display instanceof ArmorItem)((ArmorItem)source.display).unequip(GameLoop.player);
				else if(source.display instanceof BaseWeapon)((BaseWeapon)source.display).unequip(GameLoop.player);
				else if(source.display instanceof OffHandItem)((OffHandItem)source.display).unequip(GameLoop.player);
			}
			if(source.display == selectedItem){
				selectedItem = null;
				selectedItemIcon.setIcon(null);
				selectedItemName.setText(null);
				descriptions.setText(null);
			}
		}
		public void rightClicked(GItemField source) {
			if(source.display != null){
				selectedItem = source.display;
				selectedItemIcon.setIcon(source.display.icon);
				selectedItemName.setText(source.display.name);
				descriptions.setText(source.display.getInformation());
			}
		}
	}
}