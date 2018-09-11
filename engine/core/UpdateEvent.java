package engine.core;

public class UpdateEvent {
	private long timestamp;
	private long timeSinceLastUpdate;
	private double fractionOfSecond;
	
// Constructors
	
	public UpdateEvent() {
		this.timestamp = System.nanoTime();
		this.timeSinceLastUpdate = 0;
		this.fractionOfSecond = 0;
	}
	
	public UpdateEvent(UpdateEvent ev) {
		this.timestamp = System.nanoTime();
		this.timeSinceLastUpdate = this.timestamp - ev.getTimestamp();
		this.fractionOfSecond = (double)((float)this.timeSinceLastUpdate / (float)1000000000);
	}
	
// Get properties
	
	public long getTimestamp() { 			return this.timestamp; 				}
	
	public long getTimeSinceLastUpdate() { 	return this.timeSinceLastUpdate; 	}
	
	public double getFractionOfSecond() { 	return this.fractionOfSecond; 		}
}
