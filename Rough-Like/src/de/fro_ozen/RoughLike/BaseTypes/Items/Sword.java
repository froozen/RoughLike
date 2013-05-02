package de.fro_ozen.RoughLike.BaseTypes.Items;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import de.fro_ozen.RoughLike.BaseTypes.Entitys.Player;
import de.fro_ozen.RoughLike.GameMechanics.GameLoop;
import de.fro_ozen.RoughLike.GameMechanics.MouseInput;

public class Sword extends BaseWeapon{
	public Sword(int level){
		Random r = new Random();
		name = "Random-Sword";
		atk = 18 + (level*4) + r.nextInt(6);
		atkgap = r.nextInt(4);
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
			if((MouseInput.mousePoint.x-GameLoop.player.x)>0)GameLoop.player.direction=3;
			else GameLoop.player.direction=2;
		}
		else{
			if((MouseInput.mousePoint.y-GameLoop.player.y)>0)GameLoop.player.direction =1;
			else GameLoop.player.direction=4;
		}
		GameLoop.player.lastAttackTime = System.currentTimeMillis();
		GameLoop.player.attacking = true;
		if(GameLoop.player.direction == 1)GameLoop.player.attackBox = new Rectangle((int)GameLoop.player.x, (int)GameLoop.player.y+GameLoop.player.height, GameLoop.player.width, 30);
		if(GameLoop.player.direction == 2)GameLoop.player.attackBox = new Rectangle((int)GameLoop.player.x-GameLoop.player.width, (int)GameLoop.player.y+(GameLoop.player.height/2), 30, GameLoop.player.width);
		if(GameLoop.player.direction == 3)GameLoop.player.attackBox = new Rectangle((int)GameLoop.player.x+GameLoop.player.width, (int)GameLoop.player.y+(GameLoop.player.height/2), 30, GameLoop.player.width);
		if(GameLoop.player.direction == 4)GameLoop.player.attackBox = new Rectangle((int)GameLoop.player.x, (int)GameLoop.player.y-30, GameLoop.player.width, 30);
		GameLoop.player.atkDirection = GameLoop.player.direction;
	}
}