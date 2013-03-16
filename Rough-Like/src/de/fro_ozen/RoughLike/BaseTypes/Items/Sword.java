package de.fro_ozen.RoughLike.BaseTypes.Items;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.fro_ozen.RoughLike.BaseTypes.Entitys.Player;
import de.fro_ozen.RoughLike.GameMechanics.GameLoop;
import de.fro_ozen.RoughLike.GameMechanics.MouseInput;

public class Sword extends BaseWeapon{
	public Sword(){
		name = "Random-Sword";
		atk = 25;
		atkgap = 0;
		atk -= atkgap/2;
		try {
			icon = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Icons/sword.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String toString(){
		return "ATK "+atk+"~"+(atk+atkgap);
	}
	public void attack() {
		if(Math.abs(MouseInput.mousePoint.x-GameLoop.player.x)>Math.abs(MouseInput.mousePoint.y-GameLoop.player.y)){
			if((MouseInput.mousePoint.x-GameLoop.player.x)>0)GameLoop.player.dir=3;
			else GameLoop.player.dir=2;
		}
		else{
			if((MouseInput.mousePoint.y-GameLoop.player.y)>0)GameLoop.player.dir =1;
			else GameLoop.player.dir=4;
		}
		GameLoop.player.lastAttack = System.currentTimeMillis();
		GameLoop.player.attacking = true;
		if(GameLoop.player.dir == 1)GameLoop.player.attackbox = new Rectangle((int)GameLoop.player.x, (int)GameLoop.player.y+GameLoop.player.sizey, GameLoop.player.sizex, 30);
		if(GameLoop.player.dir == 2)GameLoop.player.attackbox = new Rectangle((int)GameLoop.player.x-GameLoop.player.sizex, (int)GameLoop.player.y+(GameLoop.player.sizey/2), 30, GameLoop.player.sizex);
		if(GameLoop.player.dir == 3)GameLoop.player.attackbox = new Rectangle((int)GameLoop.player.x+GameLoop.player.sizex, (int)GameLoop.player.y+(GameLoop.player.sizey/2), 30, GameLoop.player.sizex);
		if(GameLoop.player.dir == 4)GameLoop.player.attackbox = new Rectangle((int)GameLoop.player.x, (int)GameLoop.player.y-30, GameLoop.player.sizex, 30);
		GameLoop.player.atkdir = GameLoop.player.dir;
	}
}