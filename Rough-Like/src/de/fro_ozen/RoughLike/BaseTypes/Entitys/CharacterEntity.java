package de.fro_ozen.RoughLike.BaseTypes.Entitys;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class CharacterEntity extends BaseEntity{
	int anitime;
	long walkingSince;
	String spritesource;
	public int lastdir, aniframe;
	
	public boolean isFeetColiding(BaseEntity e){
		return feetbox.intersects(e.feetbox);
	}
	protected void constructorHelp(String source){
		free = true;
		spritesource = source;
		aniframe =1;
		dir = 1;
		refreshSprite();
		walkingSince = System.currentTimeMillis();
		sizex = sprite.getWidth();
		sizey = sprite.getHeight();
		colission = new Rectangle((int)x, (int)y, sizex, sizey);
		feetbox = new Rectangle ((int)x, (int)y+(sizey/4)*3, sizex, sizey/4);
		anitime = 160000 / speed;
		initLastcoor((int)x, (int)y);
	}
	protected void calcAniframe(){
		if(moving){
			if(dir == lastdir && lastdir!=0){
				long i = (System.currentTimeMillis()-walkingSince)%anitime;
				if(i<anitime/4)aniframe = 1;
				else if(i<2*(anitime/4))aniframe = 2;
				else if(i<3*(anitime/4))aniframe = 3;
				else aniframe =4;
			}
			else{
				walkingSince = System.currentTimeMillis();
				aniframe = 1;
			}
		}
		else{
			aniframe=1;
		}
	}
	public void updateBoxes(){
		feetbox = new Rectangle ((int)x, (int)y+(sizey/4)*3, sizex, sizey/4);
		colission = new Rectangle ((int)x, (int)y, sizex, sizey);
	}
	
	public BufferedImage createCharacterSprite(String imageLocation) {
		BufferedImage image = new BufferedImage(2, 2, BufferedImage.TYPE_INT_RGB);
		try {
			image = ImageIO.read(getClass().getClassLoader().getResourceAsStream(imageLocation));
			image = image.getSubimage((image.getWidth()/4)*(aniframe-1), (image.getHeight()/4)*(dir-1), ((image.getWidth()/4)), (image.getHeight()/4));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public void refreshSprite() {
		try {
			sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(spritesource));
			sprite = sprite.getSubimage((sprite.getWidth()/4)*(aniframe-1), (sprite.getHeight()/4)*(dir-1), ((sprite.getWidth()/4)), (sprite.getHeight()/4));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
