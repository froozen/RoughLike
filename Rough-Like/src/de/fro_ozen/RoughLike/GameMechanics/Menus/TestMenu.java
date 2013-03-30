package de.fro_ozen.RoughLike.GameMechanics.Menus;

import java.util.ArrayList;

import de.fro_ozen.GUI.elements.GBar;
import de.fro_ozen.GUI.elements.GButton;
import de.fro_ozen.GUI.elements.GItemArea;
import de.fro_ozen.GUI.elements.GItemField;
import de.fro_ozen.GUI.elements.GLabel;
import de.fro_ozen.GUI.elements.GMenu;
import de.fro_ozen.GUI.elements.GTextArea;
import de.fro_ozen.RoughLike.BaseTypes.Items.ArmorItem;
import de.fro_ozen.RoughLike.BaseTypes.Items.BaseItem;
import de.fro_ozen.RoughLike.BaseTypes.Items.ChestPlateItem;
import de.fro_ozen.RoughLike.BaseTypes.Items.GlovesItem;
import de.fro_ozen.RoughLike.BaseTypes.Items.HelmetItem;
import de.fro_ozen.RoughLike.BaseTypes.Items.Shield;
import de.fro_ozen.RoughLike.BaseTypes.Items.Sword;
import de.fro_ozen.RoughLike.BaseTypes.Simple.VariablePair;

public class TestMenu extends GMenu{
	private GButton button;
	private GBar bar;
	private GItemArea item;
	private GLabel label;
	private GTextArea text;
	public TestMenu(){
		setBounds(200, 200, 300, 300);
		triggerKey = "debug";
		
		button = new GButton();
		button.setBounds(50, 100, 100, 40);
		button.setText("Hello");
		
		bar = new GBar();
		bar.setBounds(50, 200, 150, 30);
		bar.setDisplayVariablePair(new VariablePair(40, 50));
		bar.enableText(true);
		
		ArrayList<BaseItem> items = new ArrayList<BaseItem>();
		items.add(new GlovesItem());
		items.add(new Shield());
		items.add(new HelmetItem());
		items.add(new ChestPlateItem());
		
		item = new GItemArea();
		item.setDisplayItems(items);
		item.setBounds(200, 50, 100, 100);
		
		label = new GLabel();
		label.setBounds(20, 170, 0, 0);
		label.setText("Text");
		
		text = new GTextArea();
		text.setBounds(10, 10, 100, 50);
		text.addText("1. line");
		text.addText("2. line");
		text.addText("3 Stuck");
		
		add(text);
		add(label);
		add(item);
		add(bar);
		add(button);
		setShift();
	}
}