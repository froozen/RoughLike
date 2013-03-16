package de.fro_ozen.RoughLike.GameMechanics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import de.fro_ozen.GUI.elements.GButton;
import de.fro_ozen.GUI.elements.GButtonListener;
import de.fro_ozen.GUI.elements.GMenu;
import de.fro_ozen.RoughLike.BaseTypes.Entitys.Player;
import de.fro_ozen.RoughLike.BaseTypes.Items.ArmorItem;
import de.fro_ozen.RoughLike.BaseTypes.Items.BaseItem;
import de.fro_ozen.RoughLike.BaseTypes.Items.BaseWeapon;
import de.fro_ozen.RoughLike.BaseTypes.Items.ItemStack;

public class HUD{
	GButton test;
//	private ArrayList<DisplayItem> renderInventory;
	final int xrow = 14, yrow = 14;
	BaseItem preview;
	private Point previewPoint;
	private HashMap<String, Rectangle> equippos;
	private Player display;
	final int barLength = 200;
	private double HPbarLength;
	private double MPbarLength;
	private GMenu menu;
	private BufferedImage hpbar, mpbar, nonebar, levelelement, background, choiceIndicator;
	public HUD(Player p){
		display = p;
//		renderInventory = new ArrayList<DisplayItem>();
		try {
			nonebar = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/HUD/No-Bar.png"));
			levelelement = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/HUD/Level.png"));
			background = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/HUD/background.png"));
			choiceIndicator = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/HUD/choiceindicator.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

//		equippos = new HashMap<String, Rectangle>();
//		equippos.put("equip.get("mainHand")", new Rectangle(560, 150, 24, 24));
//		equippos.put("chestPlate", new Rectangle(590, 150, 24, 24));
//		equippos.put("offHand", new Rectangle(620, 150, 24, 24));
//		equippos.put("helmet", new Rectangle(590, 120, 24, 24));
//		equippos.put("boots", new Rectangle(590, 180, 24, 24));
//		equippos.put("gloves", new Rectangle(560, 120, 24, 24));
	}
//	private void displayPlayerStats(Graphics g){
//		g.setColor(Color.black);
//		g.setFont(g.getFont().deriveFont(Font.PLAIN, 16));
//		if(display.equip.equip.get("mainHand") != null)g.drawString("ATK: "+display.equip.equip.get("mainHand").atk+"~"+(display.equip.equip.get("mainHand").atk+display.equip.equip.get("mainHand").atkgap),520 , 230);
//		else g.drawString("ATK: 5 (no Weapon)",520 , 230);
//		g.drawString("Defense: "+display.equip.overdef,520 , 250);
//	}
//	private void displayPreview(Graphics g){
//		if(preview!=null){
//			g.setColor(Color.black);
//			g.drawImage(preview.icon, 520, 330, null);
//			g.setFont(g.getFont().deriveFont(Font.BOLD, 16));
//			g.drawString(preview.name, 550, 350);
//			g.setFont(g.getFont().deriveFont(Font.PLAIN, 16));
//			g.drawString(preview.getDescription(),550 , 370);
//			if(previewPoint != null){
//				g.drawImage(choiceIndicator, previewPoint.x-1, previewPoint.y-1, null);
//			}
//		}
//	}
//	private void refreshRenderInventory(){
//		int x = -1;
//		int y = 0;
//		renderInventory = new ArrayList<DisplayItem>();
//		for(BaseItem item:display.invent.content){
//			x++;
//			if(x>xrow){
//				x = 0;
//				y++;
//			}
//			Rectangle r = new Rectangle(65+(x*24)+(5*(x+1)), 95+(y*24)+(5*(y+1)), 24, 24);
//			DisplayItem di = new DisplayItem(x, y, item, r);
//			renderInventory.add(di);
//		}
//	}
//	private void displayMenu(Graphics g){
//		g.drawImage(background, 50, 50, null);
//		g.setFont(g.getFont().deriveFont(Font.PLAIN, 20));
//		g.setColor(Color.black);
//		g.drawString("Inventory", 120, 82);
//
//		HPbarLength = ((double)display.stats.hp.real/display.stats.hp.max)*180;
//		MPbarLength= ((double)display.stats.mp.real/display.stats.mp.max)*180;
//		try {
//			nonebar = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/HUD/No-Bar.png"));
//			hpbar = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/HUD/HP-Bar.png"));
//			mpbar = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/HUD/MP-Bar.png"));
//			nonebar = nonebar.getSubimage(0, 0, 180, 20);
//			hpbar = hpbar.getSubimage(0, 0, (int)HPbarLength, 20);
//			mpbar = mpbar.getSubimage(0, 0, (int) MPbarLength, 20);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		g.drawImage(nonebar, 520, 60, null);
//		g.drawImage(nonebar, 520, 85, null);
//		g.drawImage(hpbar, 520, 60, null);
//		g.drawImage(mpbar, 520, 85, null);
//
//		checkLeftClick();
//		checkRightClick();
//		refreshRenderInventory();
//
//		displayEquip(g);
//		displayPreview(g);
//		displayPlayerStats(g);
//		test.drawMe(g);
//
//		// Draw all items
//		for(DisplayItem di:renderInventory){
//			if(di.xline>-1 && di.xline<=xrow){
//				if(di.yline>-1 && di.yline<=yrow){
//					g.drawImage(di.item.icon, di.bounding.x, di.bounding.y, null);
//					if(di.number>1){
//						g.setColor(Color.black);
//						g.setFont(g.getFont().deriveFont(Font.BOLD, 14));
//						g.drawString(""+di.number, di.bounding.x+15, di.bounding.y+20);
//					}
//				}
//			}
//		}
//	}
	private void displayBaseHUD(Graphics g){
		HPbarLength = ((double)display.stats.hp.real/display.stats.hp.max)*barLength;
		MPbarLength= ((double)display.stats.mp.real/display.stats.mp.max)*barLength;
		try {
			nonebar = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/HUD/No-Bar.png"));
			hpbar = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/HUD/HP-Bar.png"));
			mpbar = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/HUD/MP-Bar.png"));
			if(HPbarLength <= 0)HPbarLength = 1;
			if(MPbarLength <= 0)MPbarLength = 1;
			hpbar = hpbar.getSubimage(0, 0, (int)HPbarLength, 20);
			mpbar = mpbar.getSubimage(0, 0, (int) MPbarLength, 20);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(nonebar, 50, 5, null);
		g.drawImage(nonebar, 18, 27, null);
		g.drawImage(hpbar, 50, 5, null);
		g.drawImage(mpbar, 18, 27, null);
		g.drawImage(levelelement,-5,-5, null);
		g.setColor(Color.white);
		g.drawString("MP:       "+(int)display.stats.mp.real+"/"+display.stats.mp.max, 57, 42);
		g.drawString("HP:       "+(int)display.stats.hp.real+"/"+display.stats.hp.max, 60, 20);
		if(display.equip.mainHand != null)g.drawImage(display.equip.mainHand.icon, 7, 7, null);
		g.setFont(g.getFont().deriveFont(Font.PLAIN, 18));
		g.drawString(""+display.levels.Level, 20, 15);
	}

//	private void checkRightClick(){
//		boolean choice = false;
//		if(display.keyin.rightclicked){
//			for(DisplayItem di:renderInventory){
//				if(di.bounding.contains(display.keyin.mousePoint)){
//					if(di.item == preview)choice = true;
//					di.item.use(display);
//				}
//			}
//
//			if(equippos.get("equip.get("mainHand")").contains(display.keyin.mousePoint)){
//				if(display.equip.equip.get("mainHand") != null){
//					if(display.equip.equip.get("mainHand") == preview)choice = true;
//					display.equip.equip.get("mainHand").unequip(display);
//				}
//			}
//			if(equippos.get("offHand").contains(display.keyin.mousePoint)){
//				if(display.equip.offHand != null){
//					if(display.equip.offHand == preview)choice = true;
//					display.equip.offHand.unequip(display);
//				}
//			}
//			if(equippos.get("chestPlate").contains(display.keyin.mousePoint)){
//				if(display.equip.chestPlate != null){
//					if(display.equip.chestPlate == preview)choice = true;
//					display.equip.chestPlate.unequip(display);
//				}
//			}
//			if(equippos.get("helmet").contains(display.keyin.mousePoint)){
//				if(display.equip.helmet != null){
//					if(display.equip.helmet == preview)choice = true;
//					display.equip.helmet.unequip(display);
//				}
//			}
//			if(equippos.get("boots").contains(display.keyin.mousePoint)){
//				if(display.equip.boots != null){
//					if(display.equip.boots == preview)choice = true;
//					display.equip.boots.unequip(display);
//				}
//			}
//			if(equippos.get("gloves").contains(display.keyin.mousePoint)){
//				if(display.equip.gloves != null){
//					if(display.equip.gloves == preview)choice = true;
//					display.equip.gloves.unequip(display);
//				}
//			}
//			if(choice){
//				preview = null;
//				previewPoint = null;
//			}
//
//			display.equip.refreshOverdef();
//			display.keyin.rightclicked = false;
//		}
//	}
//	private void checkLeftClick(){
//		if(display.keyin.clicked){
//			preview = null;
//			for(DisplayItem di:renderInventory){
//				if(di.bounding.contains(display.keyin.mousePoint)){
//					preview = di.item;
//					previewPoint = new Point(di.bounding.x, di.bounding.y);
//				}
//			}
//			if(equippos.get("equip.get("mainHand")").contains(display.keyin.mousePoint)){
//				preview = display.equip.equip.get("mainHand");
//				previewPoint = new Point(equippos.get("equip.get("mainHand")").x, equippos.get("equip.get("mainHand")").y);
//			}
//			if(equippos.get("offHand").contains(display.keyin.mousePoint)){
//				preview = display.equip.offHand;
//				previewPoint = new Point(equippos.get("offHand").x, equippos.get("offHand").y);
//			}
//			if(equippos.get("chestPlate").contains(display.keyin.mousePoint)){
//				preview = display.equip.chestPlate;
//				previewPoint = new Point(equippos.get("chestPlate").x, equippos.get("chestPlate").y);
//			}
//			if(equippos.get("helmet").contains(display.keyin.mousePoint)){
//				preview = display.equip.helmet;
//				previewPoint = new Point(equippos.get("helmet").x, equippos.get("helmet").y);
//			}
//			if(equippos.get("boots").contains(display.keyin.mousePoint)){
//				preview = display.equip.boots;
//				previewPoint = new Point(equippos.get("boots").x, equippos.get("boots").y);
//			}
//			if(equippos.get("gloves").contains(display.keyin.mousePoint)){
//				preview = display.equip.gloves;
//				previewPoint = new Point(equippos.get("gloves").x, equippos.get("gloves").y);
//			}
//
//			test.checkMe();
//			display.keyin.clicked = false;
//		}
//
//	}
//
//	private void displayEquip(Graphics g){
//		if(display.equip.equip.get("mainHand")!=null)g.drawImage(display.equip.equip.get("mainHand").icon, equippos.get("equip.get("mainHand")").x, equippos.get("equip.get("mainHand")").y, null);
//		if(display.equip.offHand!=null)g.drawImage(display.equip.offHand.icon, equippos.get("offHand").x, equippos.get("offHand").y, null);
//		if(display.equip.chestPlate!=null)g.drawImage(display.equip.chestPlate.icon, equippos.get("chestPlate").x, equippos.get("chestPlate").y, null);
//		if(display.equip.helmet!=null)g.drawImage(display.equip.helmet.icon, equippos.get("helmet").x, equippos.get("helmet").y, null);
//		if(display.equip.boots!=null)g.drawImage(display.equip.boots.icon, equippos.get("boots").x, equippos.get("boots").y, null);
//		if(display.equip.gloves!=null)g.drawImage(display.equip.gloves.icon, equippos.get("gloves").x, equippos.get("gloves").y, null);
//	}
//
//	public class DisplayItem{
//		int number;
//		int xline, yline;
//		BaseItem item;
//		Rectangle bounding;
//		public DisplayItem(int xline, int yline, BaseItem item, Rectangle bounding){
//			if(item instanceof ItemStack){
//				ItemStack is = (ItemStack)item;
//				number = is.number;
//			}
//			else{
//				number = 1;
//			}
//			this.xline = xline;
//			this.yline = yline;
//			this.item = item;
//			this.bounding = bounding;
//		}
//	}

	public void displayHUD(Graphics g){
		displayBaseHUD(g);
	}
}