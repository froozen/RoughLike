package de.fro_ozen.RoughLike.BaseTypes.Items;

import java.io.IOException;

import javax.imageio.ImageIO;

import de.fro_ozen.RoughLike.BaseTypes.Entitys.GunBullet;
import de.fro_ozen.RoughLike.BaseTypes.Entitys.Player;
import de.fro_ozen.RoughLike.GameMechanics.GameLoop;
import de.fro_ozen.RoughLike.GameMechanics.MouseInput;

public class Gun extends BaseWeapon{
	public Gun(){
		name = "Handgun";
		atk = 15;
		atkgap =0;
		atk -= atkgap/2;
		try {
			icon = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Icons/gun.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void attack() {
		GameLoop.player.bull = new GunBullet(MouseInput.mousePoint.x, MouseInput.mousePoint.y , (int)GameLoop.player.x+(GameLoop.player.sizex/2), (int)GameLoop.player.y+(GameLoop.player.sizex/2), GameLoop.player, atk, atkgap);
		GameLoop.player.addBullet = true;
	}

}