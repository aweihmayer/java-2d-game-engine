package math.values;

public class ScalableNumber extends Number {
	protected double baseValue = 1;
	protected double scale = 1;
	
// Constructors
	
	public ScalableNumber() {
		this(1, 1);
	}
	
	public ScalableNumber(double baseValue) {
		this(baseValue, 1);
	}
	
	public ScalableNumber(double baseValue, double scale) {
		this.setBaseValue(baseValue);
		this.setScale(scale);
	}
	
// Set-get base value
	
	public void setBaseValue(double baseValue) {
		this.baseValue = baseValue;
		this.refreshValue();
	}
	
	public double getBaseValue() {
		return this.baseValue;
	}
	
// Set-get scale
	
	public void setScale(double scale) {
		this.scale = scale;
		this.refreshValue();
	}
	
	public double getScale() {
		return this.scale;
	}
	
// Instance methods
	
	protected void refreshValue() {
		double value = this.getBaseValue() * this.getScale();
		this.setValue(value);
	}
}
