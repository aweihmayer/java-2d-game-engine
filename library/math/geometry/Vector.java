package math.geometry;

import math.values.Angle;
import math.values.Coord;

public class Vector {
	protected double magnitude = 0;
	protected Angle direction = new Angle();
	
// Constructors
	
	public Vector() { }
	
	public Vector(Vector vector) {
		this(vector.getMagnitude(), vector.getDirection());
	}
	
	public Vector(double magnitude) {
		this(magnitude, 0);
	}
	
	public Vector(double magnitude, Angle direction) {
		this(magnitude, direction.getValue());
	}
	
	public Vector(double magnitude, double direction) {
		this.setMagnitude(magnitude);
		this.setDirection(direction);
	}
	
// Set-get magnitude
	
	public void setMagnitude(double magnitude) {	this.magnitude = magnitude; 				}
	
	public double getMagnitude() {					return this.magnitude; 						}
	
// Set-get direction
	
	public void setDirection(double direction) {	this.direction.setValue(direction); 		}
	
	public void setDirection(Angle direction) {		this.direction = direction;			 		}
	
	public void setDirection(Coord direction) {
		double angle = Math.atan2(direction.getY(), direction.getX());
		angle = Math.toDegrees(angle);
		
	    if(angle < 0){
	        angle += 360;
	    }
		
		this.setDirection(angle);
	}
	
	public double getDirection() {					return this.direction.getValue(); 			}
	
	public double getDirectionInRadians() {			return this.direction.getValueInRadians(); 	}

// Instance methods
	
	public Coord getHead() {
		Double direction = this.getDirectionInRadians();
		Double magnitude = this.getMagnitude();

		return new Coord(
			Math.cos(direction) * magnitude,
			Math.sin(direction) * magnitude
		);
	}
}
