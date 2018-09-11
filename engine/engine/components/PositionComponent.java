package engine.components;

import math.values.Coord;

public class PositionComponent extends BaseComponent {
	protected Coord position = new Coord(0, 0);
	
// Set-get position
	
	public void setPosition(Coord position) {	this.position = position; 			}
	
	public Coord getPositionRef() { 			return this.position; 	}
	
	public Coord getPosition() { 				return new Coord(this.position); 	}
	
// Instance methods
	
	public void movePosition(Coord coord) { 	this.position.add(coord); 			}
}
