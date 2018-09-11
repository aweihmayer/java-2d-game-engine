package engine.core;

import engine.components.BaseComponent;
import engine.components.HitboxComponent;
import math.geometry.Shape;
import math.geometry.Circle;

public class CollisionHandler extends BaseHandler {
	private int checkStatus = 1; // Alternates between 1 and -1
	
// Set reference list object
	
	public void add(BaseObject object){ 	
		HitboxComponent hitbox = (HitboxComponent) object.getComponent(BaseComponent.HITBOX);
		hitbox.setHandlerCheckStatus(this.checkStatus);
		this.add(object, BaseComponent.HITBOX); 		
	}
	
	public void remove(BaseObject object){ 	this.remove(object, BaseComponent.HITBOX); 		}
	
// Instance methods
	
	public void handleAll() {
		for(int i = 0; i < this.referenceList.size(); i++) {
			if(this.referenceList.get(i) != null) {
				int index = this.referenceList.get(i);
				BaseObject object = ObjectRegistry.get(index);
				
				if(object != null) {
					this.handle(object);
				}
			}
		}

		this.checkStatus *= -1;
	}
	
	protected void handle(BaseObject object) {
		HitboxComponent hitbox1 = (HitboxComponent) object.getComponent(BaseComponent.HITBOX);
		
		for(int i = 0; i < this.referenceList.size(); i++) {
			if(this.referenceList.get(i) != null) {
				int index2 = this.referenceList.get(i);
				BaseObject object2 = ObjectRegistry.get(index2);
				
				if(object2 != null) {
					HitboxComponent hitbox2 = (HitboxComponent) object2.getComponent(BaseComponent.HITBOX);
		
					if(object != object2
					&& this.wasHandled(hitbox2) == false
					&& this.checkCollision(object, object2) == true) {
						((Collideable)object).collide(object2);
						((Collideable)object2).collide(object);
					}
				}
			}
		}

		hitbox1.setHandlerCheckStatus(this.checkStatus);
	}
	
	private boolean wasHandled(HitboxComponent hitbox) {
		return hitbox.getHandlerCheckStatus() == this.checkStatus;
	}
	
	private boolean checkCollision(BaseObject object1, BaseObject object2) {
		boolean areColliding = false;

		HitboxComponent hitbox1 = (HitboxComponent) object1.getComponent(BaseComponent.HITBOX);
		Shape body1 = hitbox1.getBody();
		
		HitboxComponent hitbox2 = (HitboxComponent) object2.getComponent(BaseComponent.HITBOX);
		Shape body2 = hitbox2.getBody();
		
		// TODO: AABB
		// (o1.x + o1.r + o2.r) > o2.x
		// && o1.x < (o2.x + o1.r + o2.r)
		// && (o1.y + o1.r + o2.r) > o2.y 
		// && o1.y < (o2.y + o1.r + o2.r)

		if(body1 instanceof Circle && body2 instanceof Circle){
			areColliding = this.checkCircleToCircleCollision((Circle) body1, (Circle) body2);
		}
		
		return areColliding;
	}
	
	private boolean checkCircleToCircleCollision(Circle c1, Circle c2) {
		double radiusTotal = c1.getRadius() + c2.getRadius();
		double distanceFromRadius = c1.getPosition().distanceFromCoord(c2.getPosition());

		return distanceFromRadius < radiusTotal;
	}
}
