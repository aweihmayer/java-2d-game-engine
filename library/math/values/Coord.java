package math.values;

public class Coord {
	protected double x = 0;
	protected double y = 0;
	
// Constructors
	
	public Coord() { }
	
	public Coord(double x, double y) {
		this.setX(x);
		this.setY(y);
	}
	
	public Coord(Coord coord) {
		this(coord.getX(), coord.getY());
	}
	
// Set-get x
	
	public void setX(double x) { 	this.x = x; 	}
	
	public double getX() { 			return this.x; 	}
	
// Set-get y
	
	public void setY(double y) { 	this.y = y; 	}
	
	public double getY() { 			return this.y; 	}

// Instance methods
	
	public void add(Coord coord) {
		this.x += coord.getX();
		this.y += coord.getY();
	}
	
	public void sub(Coord coord) {
		this.x -= coord.getX();
		this.y -= coord.getY();
	}
	
	public double distanceFromCoord(Coord coord) {
		coord = new Coord(coord);
		coord.sub(this);
		double x = coord.getX();
		double y = coord.getY();
		
		return Math.sqrt((x * x) + (y * y));
	}
}
