package engine.composites.projectiles;

import engine.components.BaseComponent;
import engine.components.MoveComponent;
import math.geometry.BaseShape;
import math.values.Angle;
import math.values.Coord;

public abstract class LinearProjectileComposite extends BaseProjectileComposite {
	
// Constructors
	
	public LinearProjectileComposite(Coord origin, Coord target, double speed, BaseShape body) {
		super(origin, speed, body);
		
		MoveComponent moveComponent = (MoveComponent) this.getComponent(BaseComponent.MOVE);
		moveComponent.setDirection(target);
	}
	
	public LinearProjectileComposite(Coord origin, Angle direction, double speed, BaseShape body) {
		super(origin, speed, body);
		
		MoveComponent moveComponent = (MoveComponent) this.getComponent(BaseComponent.MOVE);
		moveComponent.setDirection(direction);
	}
}
