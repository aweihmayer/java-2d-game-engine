package engine.components;

import engine.core.Activatable;
import engine.core.UpdateEvent;
import engine.core.Updateable;
import utility.Time;

public abstract class ActivatableComponent extends BaseComponent implements Activatable, Updateable {
	protected long[] phaseDurations;
	protected int currentPhase = 0;
	protected long nextPhaseTime = -1;
	protected long cooldownDuration = 0;
	protected long cooldownTime = 0;

// Constructors
	
	public ActivatableComponent(long[] phases, long cooldownDuration) {
		this.phaseDurations = phases;
		this.cooldownDuration = cooldownDuration;
	}
	
// Events
	
	public void update(UpdateEvent ev) {
		if(this.isActive() == true) {
			this.updateActive();
		}
		else {
			this.updateInactive();
		}
	}
	
	protected void updateActive() {
		if(this.isCurrentPhaseDone()) {
			if(this.isOnLastPhase()) {
				this.finish();
			}
			else {
				this.goToNextPhase();
				this.whileActive();
			}
		}
		else {
			this.whileActive();
		}
	}
	
	protected void updateInactive() {
		this.whileInactive();
		
		if(this.isOnCooldown()) {
			this.whileOnCooldown();
		}
	}
	
// Instance methods
	
	public void activate() {
		if(this.isOnCooldown() == false) {
			this.currentPhase = 0;
			this.resetNextPhaseTime();
			this.resetCooldownTime();
			this.onActivate();
		}
	}
	
	public void finish() {
		this.stop();
		this.onFinish();
	}
	
	public void stop() {
		this.currentPhase = 0;
		this.nextPhaseTime = -1;
	}
	
// Set-get cooldown duration
	
	public void setCooldownDuration(long duration){ 	this.cooldownDuration = duration; 									}

	public long getCooldownDuration(){ 					return this.cooldownDuration; 										}
	
// Set-get-flag cooldown time
	
	public long getCooldownTime(){ 						return this.cooldownTime; 											}

	public void resetCooldownTime() { 					this.cooldownTime = Time.addTimeToNow(this.getCooldownDuration()); 	}
	
	public boolean isOnCooldown() { 					return Time.isTimeLaterThanNow(this.getCooldownTime()); 			}
	
// Set-get-flag phases
	
	public void setPhaseDurations(long[] durations) { 	this.phaseDurations = durations; 									}
	
	public int getCurrentPhase() { 						return this.currentPhase; 											}
	
	protected boolean isCurrentPhaseDone() { 			return !Time.isTimeLaterThanNow(this.nextPhaseTime); 				}
	
	protected boolean isOnLastPhase() { 				return this.getCurrentPhase() == (this.phaseDurations.length - 1); 	}
	
	public boolean isActive() { 						return this.nextPhaseTime != -1; 									}
	
	protected void goToNextPhase() {
		this.currentPhase ++;
		this.resetNextPhaseTime();
	}
		
	protected void resetNextPhaseTime() {
		int phase = this.getCurrentPhase();
		this.nextPhaseTime = Time.addTimeToNow(this.phaseDurations[phase]);
	}
}
