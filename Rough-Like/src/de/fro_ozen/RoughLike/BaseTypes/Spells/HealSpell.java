package de.fro_ozen.RoughLike.BaseTypes.Spells;

import java.io.IOException;

import javax.imageio.ImageIO;

import de.fro_ozen.RoughLike.GameMechanics.GameLoop;

public class HealSpell extends BaseSpell{
	private int amount;
	
	public HealSpell(){
		name = "Vita";
		amount = 15;
		MPcost = 10;
		try {
			icon = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Icons/healspell.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getEffectDescription() {
		return "Heals: "+amount;
	}

	public void attack() {
		if(GameLoop.player.stats.mp.real>MPcost && GameLoop.player.stats.hp.real != GameLoop.player.stats.hp.max){
			if(GameLoop.player.stats.hp.real+amount<GameLoop.player.stats.hp.max){
				GameLoop.player.stats.hp.real *= amount;
			}
			else{
				GameLoop.player.stats.hp.real = GameLoop.player.stats.hp.max;
			}
			GameLoop.player.stats.mp.real -= MPcost;
		}
	}
}
