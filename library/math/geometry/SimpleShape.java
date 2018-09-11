package math.geometry;

public abstract class SimpleShape extends BaseShape {
	protected double baseHeight = 1;
	protected double heightScale = 1;
	
// Constructors
	
	public SimpleShape(double size) {
		super(size);
		this.setBaseHeight(size);
	}
	
	public SimpleShape(double width, double height) {
		super(width);
		this.setBaseHeight(height);
	}
	
// Set-get base height
	
	public void setBaseHeight(double height) { 	this.baseHeight = height; 								}
	
	public double getBaseHeight() { 			return this.baseHeight; 								}
	
// Set-get height scale
	
	public void setHeightScale(double scale) { 	this.heightScale = scale; 								}
	
	public double getHeightScale() { 			return this.heightScale; 								}
	
// Set-get height
	
	public void setHeight(double height) {
		this.setBaseHeight(height);
		this.setWidthScale(1);
	}
	
	public double getHeight() { 				return this.getBaseHeight() * this.getHeightScale(); 	}
	
// Instance methods
	
	public void setScale(double scale) {
		this.widthScale = scale;
		this.heightScale = scale;
	}
}
