package de.fro_ozen.GUI.elements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import de.fro_ozen.RoughLike.GameMechanics.KeyInput;

public abstract class BaseElement {
	protected BufferedImage uiset; //The Image where the other Images are cut out
	public Image img; //The basic Image of the GElement
	public Rectangle box; //Represents the colission-box of the GElement for recognizing clicks on it
	//borderwidth: size of the border (i.e. the golden border of the GButton)
	//picturex/yshift: the position of the raw Image in the UIset
	//picturelength/height: size of the raw Image in the UIset
	//noedgewidth/height: size of the GElemnet without the borders
	protected int borderwidth, picturelength, pictureheight, picturexshift,pictureyshift;
	protected int noedgeheight, noedgewidth; 
	protected int fontheight = 16; //Fontsize, used for calculating

	public abstract void checkMe(); //Called by the GMenu every Frame
	public abstract void setBounds(int x, int y, int width, int height); //Sets the position and the size of the Element, also used as Constuctor

	//Just draws the basic Image img
	public void drawMe(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(box.x , box.y , box.width, box.height);
		if (img != null) {
			g.drawImage(img, box.x , box.y , null);
		}
	}
	
	//Imports the UIset.png graphic
	protected void loadUIset() {
		try {
			uiset = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/HUD/UIset.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Used to create the main Image for the GElement
	//Saves the important variables which may be needed for calculation
	protected Image createBaseImage(int width, int height, int picturexshift,
			int pictureyshift, int picturelength, int pictureheight,
			int borderwidth) {
		this.picturexshift = picturexshift;
		this.pictureyshift = pictureyshift;
		this.picturelength = picturelength;
		this.pictureheight = pictureheight;
		this.borderwidth = borderwidth;
		return createUIPart(width, height, picturexshift, pictureyshift,
				picturelength, pictureheight, borderwidth);
	}
	
	//Makes sure the Fontcolor and -size are right
	protected void applyTextStandarts(Graphics g){
		g.setColor(Color.black);
		g.setFont(g.getFont().deriveFont(Font.PLAIN, fontheight));
	}
	
	//Applys posxshift and posyshift
	public void setShift(int posxshift, int posyshift){
		box.x += posxshift;
		box.y += posyshift;
	}
	
	//Returns a cut out Image for a GElement from the UIset
	protected Image createUIPart(int width, int height, int picturexshift,int pictureyshift, int picturelength, int pictureheight,int borderwidth) {
		Image crimg = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
		Graphics myGraphics = crimg.getGraphics();
		loadUIset();
		noedgeheight = pictureheight - 2 * borderwidth;
		noedgewidth = picturelength - 2 * borderwidth;
		int restheight = height - 2 * borderwidth;
		int restwidth = width - 2 * borderwidth;
		int nowwidth = 0;
		int nowheight = 0;

		if (borderwidth > 0) {
			// Draw the edges
			myGraphics.drawImage(uiset.getSubimage(picturexshift,
					pictureyshift, borderwidth, borderwidth), 0, 0, null);
			myGraphics.drawImage(uiset.getSubimage(picturexshift, pictureyshift
					+ pictureheight - borderwidth, borderwidth, borderwidth),
					0, height - borderwidth, null);
			myGraphics.drawImage(uiset.getSubimage(picturexshift + borderwidth
					+ noedgewidth, pictureyshift, borderwidth, borderwidth),
					width - borderwidth, 0, null);
			myGraphics.drawImage(uiset.getSubimage(picturexshift + borderwidth
					+ noedgewidth, pictureyshift + borderwidth + noedgeheight,
					borderwidth, borderwidth), width - borderwidth, height
					- borderwidth, null);

			// Draw the left and right Borders
			while (restheight > 0) {
				if (restheight > noedgeheight) {
					myGraphics.drawImage(uiset.getSubimage(picturexshift,
							pictureyshift + borderwidth + 1, borderwidth,
							noedgeheight), 0, nowheight + borderwidth, null);
					myGraphics.drawImage(
							uiset.getSubimage(picturexshift + borderwidth
									+ noedgewidth, pictureyshift + borderwidth
									+ 1, borderwidth, noedgeheight), width
									- borderwidth, nowheight + borderwidth,
									null);
					restheight -= noedgeheight;
					nowheight += noedgeheight;
				} else {
					myGraphics
					.drawImage(uiset.getSubimage(picturexshift,
							pictureyshift + borderwidth + 1,
							borderwidth, restheight), 0, borderwidth
							+ nowheight, null);
					myGraphics.drawImage(
							uiset.getSubimage(picturexshift + borderwidth
									+ noedgewidth, pictureyshift + borderwidth
									+ 1, borderwidth, restheight), width
									- borderwidth, nowheight + borderwidth,
									null);
					restheight = 0;
				}
			}

			// Draw the top and lower borders
			while (restwidth > 0) {
				if (restwidth > noedgewidth) {
					myGraphics.drawImage(uiset.getSubimage(picturexshift
							+ borderwidth + 1, pictureyshift, noedgewidth,
							borderwidth), borderwidth + nowwidth, 0, null);
					myGraphics.drawImage(uiset.getSubimage(picturexshift
							+ borderwidth + 1, pictureyshift + borderwidth
							+ noedgeheight, noedgewidth, borderwidth),
							borderwidth + nowwidth, height - borderwidth, null);
					restwidth -= noedgewidth;
					nowwidth += noedgewidth;
				} else {
					myGraphics.drawImage(uiset.getSubimage(picturexshift
							+ borderwidth + 1, pictureyshift, restwidth,
							borderwidth), borderwidth + nowwidth, 0, null);
					myGraphics.drawImage(uiset.getSubimage(picturexshift
							+ borderwidth + 1, pictureyshift + borderwidth
							+ noedgeheight, restwidth, borderwidth),
							borderwidth + nowwidth, height - borderwidth, null);
					restwidth = 0;
				}
			}
		}

		// Draw the Box-Background
		restheight = height - 2 * borderwidth;
		nowheight = 0;
		int doheight;

		while (restheight > 0) {
			if (restheight > noedgeheight) {
				doheight = noedgeheight;
				restheight -= noedgeheight;
			} else {
				doheight = restheight;
				restheight = 0;
			}
			nowwidth = 0;
			restwidth = width - 2 * borderwidth;
			while (restwidth > 0) {
				if (restwidth > noedgewidth) {
					myGraphics.drawImage(uiset.getSubimage(picturexshift
							+ borderwidth, pictureyshift + borderwidth,
							noedgewidth, doheight), borderwidth + nowwidth,
							borderwidth + nowheight, null);
					restwidth -= noedgewidth;
					nowwidth += noedgewidth;
				} else {
					myGraphics.drawImage(uiset.getSubimage(picturexshift
							+ borderwidth, pictureyshift + borderwidth,
							restwidth, doheight), borderwidth + nowwidth,
							borderwidth + nowheight, null);
					restwidth = 0;
				}
			}
			nowheight += doheight;
		}
		return crimg;
	}
}
