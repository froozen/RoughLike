package de.fro_ozen.RoughLike.BaseTypes.Misc;

import java.awt.Color;

import de.fro_ozen.RoughLike.BaseTypes.Entitys.FloatingText;
import de.fro_ozen.RoughLike.BaseTypes.Simple.VariablePair;
import de.fro_ozen.RoughLike.GameMechanics.GameLoop;

public class Leveling {
	public int Level, expEver;
	public VariablePair exp;
	public Leveling(){
		Level = 1;
		exp = new VariablePair();
		exp.max = 25;
		expEver = 0;
		exp.real = 0;
	}
	private void levelUp(){
		GameLoop.player.floatingText.add(new FloatingText(GameLoop.player.x + (GameLoop.player.width/2), GameLoop.player.y - 20, "LEVEL UP", 1000, new Color(146, 25, 222)));
		GameLoop.player.stats.hp.max *= 1.05;
		GameLoop.player.stats.mp.max *= 1.05;
		GameLoop.player.stats.hp.real = GameLoop.player.stats.hp.max;
		GameLoop.player.stats.mp.real = GameLoop.player.stats.mp.max;
		Level++;
	}
	public void addExp(int amount){
		boolean levelup;
		GameLoop.player.floatingText.add(new FloatingText(GameLoop.player.x + (GameLoop.player.width/2), GameLoop.player.y - 10, "+"+amount+" EXP", 1000, new Color(146, 25, 222)));
		expEver+=amount;
		while(amount>0){
			if((exp.max-exp.real)-amount<=0){
				levelup = true;
				levelUp();
				amount=amount-(exp.max-exp.real);
				exp.max = 2*exp.max;
				exp.real = 0;
			}
			else{
				exp.real+=amount;
				amount=0;
			}
		}
	}
}