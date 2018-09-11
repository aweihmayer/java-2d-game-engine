package engine.composites.projectiles;


import engine.components.BaseComponent;
import engine.components.DrawComponent;
import engine.components.HitboxComponent;
import engine.components.MoveComponent;
import engine.components.PositionComponent;
import engine.composites.BodyComposite;
import math.geometry.BaseShape;
import math.values.Coord;

public abstract class BaseProjectileComposite extends BodyComposite {
	
// Constructors
	
	public BaseProjectileComposite(Coord origin, double speed, BaseShape body) {
		PositionComponent positionComponent = (PositionComponent) this.getComponent(BaseComponent.POSITION);
		positionComponent.setPosition(origin);

		MoveComponent moveComponent = (MoveComponent) this.getComponent(BaseComponent.MOVE);
		moveComponent.setBaseSpeed(speed);
		moveComponent.startMoving();
		
		DrawComponent drawComponent = (DrawComponent) this.getComponent(BaseComponent.DRAW);
		drawComponent.setBody(body);
		
		HitboxComponent hitbox = (HitboxComponent) this.getComponent(BaseComponent.HITBOX);
		hitbox.setBody((BaseShape) body);
	}
}
