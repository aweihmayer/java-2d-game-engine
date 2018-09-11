package engine.core;

public interface Activatable {

// Events
	
	public void whileActive();
	
	public void whileInactive();
	
	public void onActivate();
	
	public void onPhaseChange();
	
	public void onFinish();
	
	public void whileOnCooldown();
}