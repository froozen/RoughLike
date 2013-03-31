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
	final int xrow = 14, yrow = 14;
	BaseItem preview;
	private Player display;
	final int barLength = 200;
	private double HPbarLength;
	private double MPbarLength;
	private BufferedImage hpbar, mpbar, nonebar, levelelement, background, choiceIndicator;
	public HUD(Player p){
		display = p;
		try {
			nonebar = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/HUD/No-Bar.png"));
			levelelement = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/HUD/Level.png"));
			background = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/HUD/background.png"));
			choiceIndicator = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/HUD/choiceindicator.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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

	public void displayHUD(Graphics g){
		displayBaseHUD(g);
	}
}