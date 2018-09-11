package engine.components;

import engine.core.BaseObject;
import engine.core.UpdateEvent;
import engine.core.Updateable;
import math.geometry.Vector;
import math.values.Angle;
import math.values.Coord;

public class MoveComponent extends BaseComponent implements Updateable {
	protected int movementState = 0;
	protected Vector movement = new Vector();
	protected double baseSpeed = 10;
	
// Events
	
	public void update(UpdateEvent ev) {
		int state = this.getState();
		
		// TODO: accelerate, cruise, drift, decelerate to base speed, decelerate
		switch(state) {
			case 1: // Moving at base speed
				this.move(ev);
				break;
			case 0: // Stopped
				break;
		}
	}
	
// Instance methods
	
	protected void move(UpdateEvent ev) {
		Coord movement = this.movement.getHead();

		BaseObject parent = (BaseObject) this.getParent();
		PositionComponent positionComponent = (PositionComponent) parent.getComponent(BaseComponent.POSITION);

		double xMovement = movement.getX() * ev.getFractionOfSecond();
		double yMovement = movement.getY() * ev.getFractionOfSecond();
		movement.setX(xMovement);
		movement.setY(yMovement);

		positionComponent.movePosition(movement);
	}

	public void startMoving() {
		if(this.isSpeedLowerThanBaseSpeed()) {
			this.setSpeed(this.getBaseSpeed());
		}
		
		this.setState(1);
	}

	public void halt() { 
		this.setState(0);
		this.setSpeed(0);
	}
	
// Set-get base speed
	
	public void setBaseSpeed(double baseSpeed) { 	this.baseSpeed = baseSpeed; 								}
	
	public double getBaseSpeed() { 					return this.baseSpeed; 										}
	
// Set-get-flag speed
	
	public void setSpeed(double speed) { 			this.movement.setMagnitude(speed); 							}
	
	public double getSpeed() { 						return this.movement.getMagnitude(); 						}
	
	protected boolean isSpeedLowerThanBaseSpeed() { return this.movement.getMagnitude() < this.getBaseSpeed(); 	}
	
// Set-get direction
	
	public void setDirection(Angle direction) {		this.movement.setDirection(direction);			 			}
	
	public void setDirection(double direction) { 	this.movement.setDirection(direction); 						}
	
	public void setDirection(Coord directionCoord) {
		BaseObject parent = (BaseObject) this.getParent();
		PositionComponent positionComponent = (PositionComponent) parent.getComponent(BaseComponent.POSITION);
		Coord position = positionComponent.getPosition();
		
		directionCoord = new Coord(directionCoord);
		directionCoord.sub(position);
		
		this.movement.setDirection(directionCoord);
	}
		
	public double getDirection() { 					return this.movement.getDirection(); 						}
	
// Set-get-flag state
	
	protected void setState(int movementState) { 	this.movementState = movementState; 						}
	
	protected int getState() { 						return this.movementState; 									}
			
	public boolean isMoving() { 					return this.getState() != 0; 								}
}