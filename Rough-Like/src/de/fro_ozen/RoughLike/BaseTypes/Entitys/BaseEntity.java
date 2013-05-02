package de.fro_ozen.RoughLike.BaseTypes.Entitys;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class BaseEntity {
	boolean moving; //Wether the Entity is moving
	public boolean  remove, free; //remove: wether the Entity may be removed
	//free: wether the Entity may move freely
	int forcedDirection; //Direction in that the Entity is pushed
	double forceWay; //Way it is pushed
	Point[] lastCoordinates; //The coordinates of the Entity the last bunch of Frames
	public int direction; //Direction in which it moves
	public static float timeSinceLastFrame; //The time since the last Frame in seconds
	public double x, y; //x and y position of the Entity
	public int speed, width, height; //speed: speed of movement
	//sizex/y: size of the Entity
	public Rectangle colissionBox; //Colission box of the Entity
	public Rectangle feetBox; //Area where the Entity touches the ground
	public BufferedImage sprite; //sprite displayed
	
	
	public abstract void compute(); //Called every Frame in the MainLoop
	public abstract void drawMe(Graphics g); //Used to draw the Entity
	
	//Wether Entity collides with another Entity
	public boolean isColiding(BaseEntity e){
		return colissionBox.intersects(e.colissionBox);
	}
	
	//Updates the Boxes so they match the current position
	public void updateBoxes(){
		colissionBox = new Rectangle ((int)x, (int)y, width, height);
		feetBox = colissionBox;
	}
	
	//Sets the Entity back to its positon the of the Frame before
	public void stepBack(){
		x = lastCoordinates[2].x;
		y = lastCoordinates[2].y;
		for(int i=3; i>0; i--){
			lastCoordinates[i]=lastCoordinates[i-1];
		}
	}
	
	//Updates lastcoor[]
	public void nextStep(){
		for(int i=0; i<3; i++){
			lastCoordinates[i]=lastCoordinates[i+1];
		}
		lastCoordinates[3]= new Point((int)x, (int)y);
	}
	
	//Creates points in lastcoor[], used for constructors
	protected void initLastcoor(int x, int y){
		lastCoordinates = new Point[4];
		Point p = new Point(x,y);
		for(int i=0; i<4; i++){
			lastCoordinates[i]=p;
		}
	}
	
	//Does things needed in every Constructor of an Entity
	protected void constructorHelp(){
		free = true;
		direction = 1;
		colissionBox = new Rectangle((int)x, (int)y, width, height);
		feetBox = colissionBox;
		initLastcoor((int)x, (int)y);
	}
	
	//Does even more because it has the Sprite
	protected void constructorHelp(BufferedImage sprite){
		free = true;
		direction = 1;
		this.sprite = sprite;
		width = sprite.getWidth();
		height = sprite.getHeight();
		colissionBox = new Rectangle((int)x, (int)y, width, height);
		feetBox = colissionBox;
		initLastcoor((int)x, (int)y);
	}
	
	//Creates the Sprite from a given source
	protected void constructorHelp(String spritesource){
		try {
			sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(spritesource));
		} catch (IOException e) {
			e.printStackTrace();
		}
		free = true;
		direction = 1;
		this.sprite = sprite;
		width = sprite.getWidth();
		height = sprite.getHeight();
		colissionBox = new Rectangle((int)x, (int)y, width, height);
		initLastcoor((int)x, (int)y);
	}
	
}