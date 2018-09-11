package engine.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {
	public final static int W = 87;
	public final static int A = 65;
	public final static int S = 83;
	public final static int D = 68;
	
// Static methods
	
	private static boolean[] keysPressed = new boolean[150];
	
	public static boolean isKeyPressed(int keyCode) { 	return keysPressed[keyCode]; 			}
	
// Events
	
	public void keyPressed(KeyEvent ev) {				keysPressed[ev.getKeyCode()] = true;	}

	public void keyReleased(KeyEvent ev) { 				keysPressed[ev.getKeyCode()] = false; 	}

	public void keyTyped(KeyEvent ev) { }
}