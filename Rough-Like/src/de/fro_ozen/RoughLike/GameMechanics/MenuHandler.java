package de.fro_ozen.RoughLike.GameMechanics;

import java.awt.Graphics;
import java.util.ArrayList;

import de.fro_ozen.GUI.elements.GMenu;
import de.fro_ozen.RoughLike.BaseTypes.Entitys.Player;
import de.fro_ozen.RoughLike.GameMechanics.Menus.GameOverMenu;
import de.fro_ozen.RoughLike.GameMechanics.Menus.InventoryMenu;
import de.fro_ozen.RoughLike.GameMechanics.Menus.TestMenu;

public class MenuHandler {
	private ArrayList<GMenu> menus;
	private Player player;
	GMenu runningMenu;
	public boolean gameOver;
	public MenuHandler(Player owner){
		player = owner;
		
		menus = new ArrayList<GMenu>();
		menus.add(new InventoryMenu(player));
		menus.add(new TestMenu());
	}
	public boolean checkMenus(){
		if(!gameOver){
			if(runningMenu != null){
				if(KeyInput.wasReleased(runningMenu.triggerKey)){
					runningMenu = null;
					return false;
				}
				runningMenu.checkMe();
				return true;
			}
			else{
				for(GMenu menu:menus){
					if(KeyInput.wasReleased(menu.triggerKey)){
						runningMenu = menu;
						runningMenu.checkMe();
						return true;
					}
				}
				return false;
			}
		}
		else{
			if(!(runningMenu instanceof GameOverMenu)){
				runningMenu = new GameOverMenu();
			}
			return true;
		}
	}
	public void displayMenu(Graphics g) {
		if(runningMenu != null){
			runningMenu.drawMe(g);
		}
	}
}