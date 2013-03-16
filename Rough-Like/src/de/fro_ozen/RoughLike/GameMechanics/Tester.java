package de.fro_ozen.RoughLike.GameMechanics;

import de.fro_ozen.RoughLike.BaseTypes.Entitys.Player;
import de.fro_ozen.RoughLike.BaseTypes.Items.ChestPlateItem;
import de.fro_ozen.RoughLike.BaseTypes.Items.Sword;

public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Player p = new Player(0,0);
		ChestPlateItem a = new ChestPlateItem();
		ChestPlateItem b = new ChestPlateItem();
		if(!(a==null&&b==null)){
			System.out.println(a.getCompareString().equals(b.getCompareString()));
		}
	}

}
