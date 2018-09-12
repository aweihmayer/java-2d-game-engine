package game.events;

import engine.components.BaseComponent;
import engine.components.UpdateComponent;
import engine.core.BaseObject;
import engine.core.ObjectRegistry;
import engine.core.UpdateEvent;
import engine.core.Updateable;
import math.values.Coord;
import utility.Time;
import game.characters.MonsterEgg;

public class MonsterSpawn extends BaseObject implements Updateable {
	protected long cooldownDuration = 1000;
	protected long cooldownTime = 0;
	
	protected int spawnXLimit;
	protected int spawnYLimit;
	
	public MonsterSpawn(int spawnXLimit, int spawnYLimit) {
		this.spawnXLimit = spawnXLimit;
		this.spawnYLimit = spawnYLimit;
		
		this.addComponent(BaseComponent.UPDATE, new UpdateComponent());
		this.resetCooldownTime();
	}
	
	public void update(UpdateEvent ev) {				
		if(this.isOnCooldown() == false) {
			MonsterEgg egg = new MonsterEgg(new Coord(
				Math.random() * this.spawnXLimit,
				Math.random() * this.spawnYLimit
			));
			ObjectRegistry.add(egg);
			this.resetCooldownTime();
		}
	}
		
	public void resetCooldownTime() { 	this.cooldownTime = Time.addTimeToNow(this.cooldownDuration); 	}
	
	public boolean isOnCooldown() {		return Time.isTimeLaterThanNow(this.cooldownTime);				}
}
