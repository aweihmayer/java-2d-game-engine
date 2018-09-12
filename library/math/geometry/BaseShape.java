package math.geometry;

import math.values.Angle;
import math.values.Coord;

public abstract class BaseShape implements Shape {
	protected double baseWidth = 1;
	protected double widthScale = 1;
	protected Angle rotation = new Angle();
	protected Coord position = new Coord();
	
// Constructors
	
	public BaseShape(double width) { 			this.setBaseWidth(width); 							}
	
// Set-get base width
	
	public void setBaseWidth(double width) { 	this.baseWidth = width; 							}
	
	public double getBaseWidth() { 				return this.baseWidth; 								}
	
// Set-get width scale
	
	public void setWidthScale(double scale) { 	this.widthScale = scale; 							}
	
	public double getWidthScale() { 			return this.widthScale; 							}
	
	public void setScale(double scale) { 		this.widthScale = scale; 							}
	
// Set-get width
	
	public void setWidth(double width) {
		this.setBaseWidth(width);
		this.setWidthScale(1);
	}
	
	public double getWidth() { 					return this.getBaseWidth() * this.getWidthScale(); 	}	
	
// Set-get position
	
	public void setPosition(Coord position) {	this.position = position;							}
	
	public Coord getPosition() {				return this.position;								}
	
// Set-get rotation
	
	public void setRotation(Angle rotation) {	this.rotation = rotation;							}
	
	public void setRotation(double rotation) {	this.rotation.setValue(rotation);					}
	
	public Angle getRotation() {				return this.rotation;								}
}
