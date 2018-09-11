package math.geometry;

import java.awt.Graphics2D;

import math.values.Coord;

public class Circle extends BaseShape {
	
// Constructors
	
	public Circle(double width) {
		super(width);
	}

// Instance methods
	
	public double getRadius() { return this.getWidth() / 2; }
	
	public void draw(Graphics2D g) {
		Coord position = this.getPosition();
		double x = position.getX();
		double y = position.getY();

		double r = this.getWidth();
		x = (int) (x - (r / 2));
		y = (int) (y - (r /2));
		
		g.fillOval(
			(int) x, (int) y,
			(int) r, (int) r
		);
	}
}
