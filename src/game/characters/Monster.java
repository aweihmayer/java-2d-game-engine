package game.characters;

import java.awt.Graphics2D;

import engine.components.BaseComponent;
import engine.components.DrawComponent;
import engine.components.HitboxComponent;
import engine.components.MoveComponent;
import engine.components.PositionComponent;
import engine.composites.BodyComposite;
import engine.core.BaseObject;
import engine.core.ObjectRegistry;
import engine.core.UpdateEvent;
import math.geometry.Circle;
import math.geometry.SimpleShape;
import math.values.Angle;
import math.values.Coord;
import math.values.Number;
import engine.resources.Sprite;
import game.projectiles.BulletProjectile;

public class Monster extends BodyComposite {
	protected Number health = new Number(1, 0 , 4);
	
	public Monster(Coord position) {
		PositionComponent positionComponent = (PositionComponent) this.getComponent(BaseComponent.POSITION);
		positionComponent.setPosition(position);
		
		DrawComponent drawing = (DrawComponent) this.getComponent(BaseComponent.DRAW);
		Sprite sprite = new Sprite("monster", 50);
		sprite.setAnimation("moving");
		drawing.setBody(sprite);
		
		HitboxComponent hitbox = (HitboxComponent) this.getComponent(BaseComponent.HITBOX);
		hitbox.setBody(new Circle(50));
		
		MoveComponent movement = (MoveComponent) this.getComponent(BaseComponent.MOVE);
		movement.setBaseSpeed(100);
		movement.startMoving();
	}
	
	public void update(UpdateEvent ev) {
		this.followPlayer();
		this.increaseHealth(ev);
		this.matchSizeToHealth();
	}
	
	private void followPlayer() {
		PositionComponent monsterPcomp = (PositionComponent) this.getComponent(BaseComponent.POSITION);
		Coord monsterPosition = monsterPcomp.getPosition();
		
		Player player = (Player) ObjectRegistry.get("player");
		PositionComponent playerPcomp = (PositionComponent) player.getComponent(BaseComponent.POSITION);
		Coord playerPosition = playerPcomp.getPosition();
		
		MoveComponent monsterMvcomp = (MoveComponent) this.getComponent(BaseComponent.MOVE);
		monsterMvcomp.setDirection(playerPosition);

		Angle facingAngle = Angle.angleBetweenCoords(monsterPosition, playerPosition);
		DrawComponent drawing = (DrawComponent) this.getComponent(BaseComponent.DRAW);
		drawing.getBody().setRotation(facingAngle);
	}
	
	private void increaseHealth(UpdateEvent ev) {
		this.health.add(0.3 * ev.getFractionOfSecond());
	}

	private void matchSizeToHealth() {
		DrawComponent dcomp = (DrawComponent) this.getComponent(BaseComponent.DRAW);
		SimpleShape body = (SimpleShape) dcomp.getBody();
		body.setScale(this.health.getValue());
	}

	public void draw(Graphics2D g) { }

	public void collide(BaseObject object) {
		if(object instanceof BulletProjectile) {
			this.health.sub(((BulletProjectile) object).getDamage());

			if(health.getValue() <= 0.7) {
				ObjectRegistry.remove(this);
			}
		}
	}
}
