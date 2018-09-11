package game.projectiles;

import java.awt.Graphics2D;

import engine.components.BaseComponent;
import engine.components.PositionComponent;
import engine.composites.projectiles.LinearProjectileComposite;
import engine.core.BaseObject;
import engine.core.GameWindow;
import engine.core.ObjectRegistry;
import engine.core.UpdateEvent;
import game.characters.Monster;
import math.geometry.Circle;
import math.values.Angle;
import math.values.Coord;

public class BulletProjectile extends LinearProjectileComposite {
	protected double damage = 0.2;
	
	public BulletProjectile(Coord origin, Angle direction) {
		super(origin, direction, 800, new Circle(10));
	}
	
	public void update(UpdateEvent ev) {			
		PositionComponent pcomp = (PositionComponent) this.getComponent(BaseComponent.POSITION);
		Coord position = pcomp.getPosition();
		double x = position.getX();
		double y = position.getY();
		
		if(x < 0
		|| x > GameWindow.getWidth()
		|| y < 0
		|| y > GameWindow.getHeight()) {
			ObjectRegistry.remove(this);
		}
	}
	
	public double getDamage() { return this.damage; }

	public void draw(Graphics2D g) {
		
	}

	public void collide(BaseObject object) {
		if(object instanceof Monster) {
			ObjectRegistry.remove(this);
		}
	}
}
