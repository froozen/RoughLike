package de.fro_ozen.RoughLike.GameMechanics;

import java.awt.Point;

public class MouseInput {
	public static boolean leftClicked, rightClicked, lastLeftClicked, lastRightClicked;
	public static Point mousePoint = new Point();
	
	public static void resetClicks(){
		leftClicked = lastLeftClicked;
		rightClicked = lastRightClicked;
		lastLeftClicked = false;
		lastRightClicked = false;
	}
}