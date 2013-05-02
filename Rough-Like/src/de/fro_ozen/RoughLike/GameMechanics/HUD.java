package de.fro_ozen.RoughLike.GameMechanics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.fro_ozen.RoughLike.BaseTypes.Entitys.Player;
import de.fro_ozen.RoughLike.BaseTypes.Items.BaseItem;
import de.fro_ozen.RoughLike.GameMechanics.Menus.ItemShortCutBar;

public class HUD{
	public static BaseItem[] shortCuts;
	private Player display;
	final int barLength = 200;
	private int expBarSize;
	private double HPbarLength, MPbarLength, expBarHeight;
	private BufferedImage hpbar, mpbar, expbar, overlay, underlay;
	private ItemShortCutBar itemshorts;
	public HUD(Player p){
		itemshorts = new ItemShortCutBar();
		display = p;
		try {
			underlay = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/HUD/HUD-underlay.png"));
			overlay = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/HUD/HUD-overlay.png"));
			expbar = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/HUD/expbar.png"));
			expBarSize = expbar.getHeight();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		shortCuts = new BaseItem[10];
	}

	private void displayBaseHUD(Graphics g){
		HPbarLength = ((double)display.stats.hp.real/display.stats.hp.max)*barLength;
		MPbarLength= ((double)display.stats.mp.real/display.stats.mp.max)*barLength;
		expBarHeight = ((double)display.levels.exp.real/display.levels.exp.max)*expBarSize;
		try {
			hpbar = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/HUD/hpbar.png"));
			mpbar = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/HUD/mpbar.png"));
			expbar = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/HUD/expbar.png"));
			if(HPbarLength <= 1)HPbarLength = 1;
			if(MPbarLength <= 1)MPbarLength = 1;
			if(expBarHeight <= 1)expBarHeight = 1;
			hpbar = hpbar.getSubimage(0, 0, (int)HPbarLength, 10);
			mpbar = mpbar.getSubimage(0, 0, (int) MPbarLength, 10);
			expbar = expbar.getSubimage(0, 0, 8, (int)expBarHeight);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(underlay, 0, 0, null);
		g.drawImage(hpbar, 80, 2, null);
		g.drawImage(mpbar, 80, 17, null);
		g.drawImage(expbar, 37, 38-expbar.getHeight(), null);
		g.drawImage(overlay, 0, 0, null);
		g.setColor(Color.white);
		g.setFont(g.getFont().deriveFont(Font.PLAIN, 10));
		g.drawString(""+display.stats.hp.real, 48, 10);
		g.drawString(""+display.stats.mp.real, 48, 25);
		if(display.equipment.mainHand != null)g.drawImage(display.equipment.mainHand.icon, 6, 4, null);
		g.drawString(""+display.levels.Level, 5, 41);
		itemshorts.drawMe(g);
	}

	public void displayHUD(Graphics g){
		displayBaseHUD(g);
	}
}