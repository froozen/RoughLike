package de.fro_ozen.RoughLike.GameMechanics.Menus;

import de.fro_ozen.GUI.elements.GLabel;
import de.fro_ozen.GUI.elements.GMenu;

public class GameOverMenu extends GMenu{
	private GLabel text;
	public GameOverMenu(){
		setBounds(300, 280, 200, 40);
		
		text = new GLabel();
		text.setBounds(60, 4, 0, 0);
		text.setText("Game Over");
		
		add(text);
		setShift();
	}
}