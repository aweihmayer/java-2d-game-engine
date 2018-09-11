package math.values;

public class Angle extends Number {
	
// Constructors
	
	public Angle() { }
	
	public Angle(Angle angle) {
		this.setValue(angle.getValue());
	}
	
	public Angle(double val) {
		this.setValue(val);
	}
	
// Set-get value
	
	public void setValue(double val) {
		if(val > 360){
			val = this.getValue() % 360;
		}
		else if(val < 0){
			val = 360 - this.getValue();
		}
		
		super.setValue(val);
	}
	
	public double getValueInRadians() { return Math.toRadians(this.getValue()); }
	
// Static methods
	
	public static Angle angleBetweenCoords(Coord c1, Coord c2) {
		c2 = new Coord(c2);
		c2.sub(c1);
		double angle = Math.atan2(c2.getY(), c2.getX());
		angle = Math.toDegrees(angle);
		
	    if(angle < 0){
	        angle += 360;
	    }
		
		return new Angle(angle);
	}
}
