package de.fro_ozen.RoughLike.GameMechanics.Menus;

import de.fro_ozen.GUI.elements.GBar;
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
	private GBar hpBar, mpBar;

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
		descriptions.setBounds(425, 165, 165, 220);

		inventory = new GItemArea();
		inventory.setDisplayItems(GameLoop.player.invent.content);
		inventory.setBounds(10, 10, 405, 380);
		inventory.setItemFieldListener(new InventoryFieldHandler());

		equipment = new GEquipmentArea();
		equipment.setBounds(468, 60, 0, 0);
		equipment.setItemFieldListener(new EquipmentFieldHandler());

		add(hpBar);
		add(mpBar);
		add(equipment);
		add(descriptions);
		add(inventory);
		setShift();
	}

	private class InventoryFieldHandler extends GItemFieldListener{
		public void leftClicked(GItemField source) {
			if(source.display != null)source.display.use(GameLoop.player);
		}

		public void rightClicked(GItemField source) {
			if(source.display != null){
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
		}
		public void rightClicked(GItemField source) {
			if(source.display != null){
				descriptions.setText(source.display.getInformation());
			}
		}
	}
}