package de.fro_ozen.RoughLike.GameMechanics;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import de.fro_ozen.RoughLike.BaseTypes.Items.Inventory;

public class KeyInput implements KeyListener{
	private static HashMap<String,Integer>keyNames;
	private static HashMap<Integer,Boolean>keyPressed, keyReleased, keyWasReleased;
	public KeyInput(){
		keyNames= new HashMap<String,Integer>();
		keyPressed= new HashMap<Integer,Boolean>();
		keyWasReleased = new HashMap<Integer, Boolean>();
		keyReleased = new HashMap<Integer, Boolean>();
		
		keyNames.put("up", KeyEvent.VK_W);
		keyNames.put("down", KeyEvent.VK_S);
		keyNames.put("left", KeyEvent.VK_A);
		keyNames.put("right", KeyEvent.VK_D);
		keyNames.put("inventory", KeyEvent.VK_E);
		keyNames.put("debug", KeyEvent.VK_Q);
		keyNames.put("0", KeyEvent.VK_0);
		keyNames.put("1", KeyEvent.VK_1);
		keyNames.put("2", KeyEvent.VK_2);
		keyNames.put("3", KeyEvent.VK_3);
		keyNames.put("4", KeyEvent.VK_4);
		keyNames.put("5", KeyEvent.VK_5);
		keyNames.put("6", KeyEvent.VK_6);
		keyNames.put("7", KeyEvent.VK_7);
		keyNames.put("8", KeyEvent.VK_8);
		keyNames.put("9", KeyEvent.VK_9);
	}
	public static void resetReleasedKeys(){
		keyWasReleased = keyReleased;
		keyReleased = new HashMap<Integer, Boolean>();
	}
	public static boolean isPressed(String keyName){
		Boolean bool = keyPressed.get(keyNames.get(keyName));
		if(bool != null)return bool;
		else return false;
	}
	public static boolean wasReleased(String keyName){
		Boolean bool = keyWasReleased.get(keyNames.get(keyName));
		if(bool != null)return bool;
		else return false;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keyPressed.put(e.getKeyCode(), true);
	}
	@Override
	public void keyReleased(KeyEvent e) {
		keyPressed.put(e.getKeyCode(), false);
		keyReleased.put(e.getKeyCode(), true);
	}
	
	
	
	
	
	@Override
	public void keyTyped(KeyEvent e) {		
	}
}