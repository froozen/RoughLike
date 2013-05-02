package de.fro_ozen.RoughLike.BaseTypes.Entitys;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class CharacterEntity extends BaseEntity{
	int animationTime;
	long walkingSince;
	String spriteLocation;
	public int lastDirection, animationFrame;
	
	public boolean isFeetColiding(BaseEntity e){
		return feetBox.intersects(e.feetBox);
	}
	protected void constructorHelp(String source){
		free = true;
		spriteLocation = source;
		animationFrame =1;
		direction = 1;
		refreshSprite();
		walkingSince = System.currentTimeMillis();
		width = sprite.getWidth();
		height = sprite.getHeight();
		colissionBox = new Rectangle((int)x, (int)y, width, height);
		feetBox = new Rectangle ((int)x, (int)y+(height/4)*3, width, height/4);
		animationTime = 160000 / speed;
		initLastcoor((int)x, (int)y);
	}
	protected void calcAniframe(){
		if(moving){
			if(direction == lastDirection && lastDirection!=0){
				long i = (System.currentTimeMillis()-walkingSince)%animationTime;
				if(i<animationTime/4)animationFrame = 1;
				else if(i<2*(animationTime/4))animationFrame = 2;
				else if(i<3*(animationTime/4))animationFrame = 3;
				else animationFrame =4;
			}
			else{
				walkingSince = System.currentTimeMillis();
				animationFrame = 1;
			}
		}
		else{
			animationFrame=1;
		}
	}
	public void updateBoxes(){
		feetBox = new Rectangle ((int)x, (int)y+(height/4)*3, width, height/4);
		colissionBox = new Rectangle ((int)x, (int)y, width, height);
	}
	
	public BufferedImage createCharacterSprite(String imageLocation) {
		BufferedImage image = new BufferedImage(2, 2, BufferedImage.TYPE_INT_RGB);
		try {
			image = ImageIO.read(getClass().getClassLoader().getResourceAsStream(imageLocation));
			image = image.getSubimage((image.getWidth()/4)*(animationFrame-1), (image.getHeight()/4)*(direction-1), ((image.getWidth()/4)), (image.getHeight()/4));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public void refreshSprite(){
		try {
			sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(spriteLocation));
			sprite = sprite.getSubimage((sprite.getWidth()/4)*(animationFrame-1), (sprite.getHeight()/4)*(direction-1), ((sprite.getWidth()/4)), (sprite.getHeight()/4));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
