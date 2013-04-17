package de.fro_ozen.RoughLike.GameMechanics.Menus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.fro_ozen.RoughLike.BaseTypes.Items.BaseItem;
import de.fro_ozen.RoughLike.BaseTypes.Items.ItemStack;
import de.fro_ozen.RoughLike.GameMechanics.GameLoop;
import de.fro_ozen.RoughLike.GameMechanics.KeyInput;

public class ItemShortCutBar {
	public static BaseItem[] items;
	private BufferedImage itembar;
	public static long lastTimeAssigned = 0;

	public ItemShortCutBar(){
		items = new BaseItem[10];
		try {
			itembar = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/HUD/itembar.png"));
		} catch (IOException e){e.printStackTrace();}
	}

	public void drawMe(Graphics g){
		g.drawImage(itembar, 234, 566, null);
		for(int i=0; i<items.length; i++){
			if(items[i] != null){
				g.drawImage(items[i].icon, 7+(33*i)+234, 574, null);
				if(items[i] instanceof ItemStack){
					if(((ItemStack)items[i]).number>1){
						g.setColor(Color.black);
						g.drawString(((ItemStack)items[i]).number+"", 7+(33*i)+234+20, 600);
						g.setColor(Color.white);
					}
				}
			}
			g.drawString(i+"", 7+(33*i)+234, 600);
		}
	}

	public static void checkKeys(){
		for(int i = 0; i<10; i++){
			if(!GameLoop.player.invent.content.contains(items[i]))items[i]=null;
			if(KeyInput.wasReleased(i+"") && items[i] != null && ((System.currentTimeMillis()-lastTimeAssigned)/1000)>1){
				if(items[i].use(GameLoop.player))GameLoop.player.invent.content.remove(items[i]);
			}
		}
	}
}