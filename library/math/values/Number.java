package math.values;

public class Number {
	protected double value = 0;
	protected boolean isMinEnabled = false;
	protected double min = 0;
	protected boolean isMaxEnabled = false;
	protected double max = 0;
	
// Constructors
	
	public Number() { }
	
	public Number(Number num) {
		this.setValue(num.getValue());
	}
	
	public Number(double v) {
		this.setValue(v);
	}
	
	public Number(double v, double min) {
		this.setValue(v);
		this.setMin(min);
	}
	
	public Number(double v, double min, double max) {
		this.setValue(v);
		this.setMin(min);
		this.setMax(max);
	}
	
// Set-get value
	
	public void setValue(double v) {
		double min = this.getMin();
		double max = this.getMax();
	
		if(this.isMinEnabled() && v < min) {
			v = min;
		} else if(this.isMaxEnabled() && v > max) {
			v = max;
		}
		
		this.value = v;
	}
	
	public double getValue() {
		return this.value;
	}
	
// Set-get-flag min
	
	public void setMin(double min) {
		this.isMinEnabled = true;
		this.min = min;
	}
	
	public void setMin(boolean min) {
		this.isMinEnabled = min;
	}
	
	public double getMin() {
		return this.min;
	}
	
	public boolean isMinEnabled() {
		return this.isMinEnabled;
	}
	
// Set-get-flag max
	
	public void setMax(double max) {
		this.isMaxEnabled = true;
		this.max = max;
	}
	
	public void setMax(boolean max) {
		this.isMaxEnabled = max;
	}
	
	public double getMax() {
		return this.max;
	}
	
	public boolean isMaxEnabled() {
		return this.isMaxEnabled;
	}
	
// Instance methods
	
	public void add(double v){ 		this.setValue(this.value + v); 			}
	
	public void sub(double v){ 		this.setValue(this.value - v); 			}
	
	public void multiply(double v){ this.setValue(this.value * v);			}
	
	public void divide(double v){ 	this.setValue(this.value / v);			}
	
	public void round(){ 			this.setValue(Math.round(this.value)); 	}
	
	public void ceil(){ 			this.setValue(Math.ceil(this.value)); 	}
	
	public void floor(){			this.setValue(Math.floor(this.value)); 	}
	
// Static methods
	
	public static double random(double min, double max) {
		return Math.random() * ((max-min) + 1) + min;
	}
}
