package de.fro_ozen.RoughLike.GameMechanics;

import java.awt.Point;

public class MouseInput {
	public static boolean leftClicked, rightClicked;
	public static Point mousePoint = new Point();
	
	public static void resetClicks(){
		leftClicked = false;
		rightClicked = false;
	}
}