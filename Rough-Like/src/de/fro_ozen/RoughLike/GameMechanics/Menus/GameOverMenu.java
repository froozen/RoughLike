package de.fro_ozen.RoughLike.GameMechanics.Menus;

import de.fro_ozen.GUI.elements.GLabel;
import de.fro_ozen.GUI.elements.GMenu;
import de.fro_ozen.RoughLike.GameMechanics.GameLoop;

public class GameOverMenu extends GMenu{
	private GLabel text;
	private GLabel score;
	public GameOverMenu(){
		setBounds(300, 280, 200, 60);
		
		text = new GLabel();
		text.setBounds(60, 4, 0, 0);
		text.setText("Game Over");
		
		score = new GLabel();
		score.setBounds(50, 24, 0, 0);
		score.setText("Score: "+GameLoop.player.levels.expEver);
		
		add(score);
		add(text);
		setShift();
	}
}