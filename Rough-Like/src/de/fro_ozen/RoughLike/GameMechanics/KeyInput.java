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
		// TODO Auto-generated method stub
		
	}
}