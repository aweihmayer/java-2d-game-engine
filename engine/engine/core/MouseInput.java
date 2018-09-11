package engine.core;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import math.values.Coord;

public class MouseInput implements MouseListener {
	public static final int LEFT = 1;
	
	private static boolean[] mouseButtonsPressed = new boolean[10];
	
// Static methods
	
	public static Coord getMousePosition() {
		Point position = MouseInfo.getPointerInfo().getLocation();
		return new Coord(position.getX(), position.getY());
	}
	
	public static boolean isMousePressed(int keyCode) { return mouseButtonsPressed[keyCode]; 			}
	
// Events
	
	public void mousePressed(MouseEvent ev) { 			mouseButtonsPressed[ev.getButton()] = true; 	}

	public void mouseReleased(MouseEvent ev) { 			mouseButtonsPressed[ev.getButton()] = false;	}
	
	public void mouseClicked(MouseEvent ev) { }

	public void mouseEntered(MouseEvent ev) { }

	public void mouseExited(MouseEvent ev) { }
}
