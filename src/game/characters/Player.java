package game.characters;

import java.awt.Graphics2D;

import engine.components.ActivatableComponent;
import engine.components.ActiveListComponent;
import engine.components.BaseComponent;
import engine.components.DrawComponent;
import engine.components.HitboxComponent;
import engine.components.MoveComponent;
import engine.components.PositionComponent;
import engine.composites.CharacterComposite;
import engine.core.BaseObject;
import engine.core.KeyInput;
import engine.core.MouseInput;
import engine.core.UpdateEvent;
import math.geometry.Circle;
import math.values.Angle;
import math.values.Coord;
import engine.resources.Sprite;
import game.DemoGame;
import game.abilities.ShootAbility;

public class Player extends CharacterComposite {
	public Player(Coord position) {
		PositionComponent positionComponent = (PositionComponent) this.getComponent(BaseComponent.POSITION);
		positionComponent.setPosition(position);
		
		DrawComponent drawing = (DrawComponent) this.getComponent(BaseComponent.DRAW);
		drawing.setBody(new Sprite("player", 50));
		
		HitboxComponent hitbox = (HitboxComponent) this.getComponent(BaseComponent.HITBOX);
		hitbox.setBody(new Circle(50));
		
		MoveComponent movement = (MoveComponent) this.getComponent(BaseComponent.MOVE);
		movement.setBaseSpeed(500);
		
		ActiveListComponent abilities = (ActiveListComponent) this.getComponent(BaseComponent.ABILITIES);
		abilities.set("shoot", new ShootAbility());
	}
	
	public void update(UpdateEvent ev) {
		this.updateMovement();
		this.updateFacingAngle();
		this.updateAttack();
	}
	
	private void updateMovement() {
		boolean isMoving = false;
		MoveComponent moveComponent = (MoveComponent) this.getComponent(BaseComponent.MOVE);

		// 87: W
		// 83: S
		// 65: A
		// 68: D
		if(KeyInput.isKeyPressed(87) || KeyInput.isKeyPressed(83) || KeyInput.isKeyPressed(65) || KeyInput.isKeyPressed(68)) {
			if(KeyInput.isKeyPressed(87)) {	
				if(KeyInput.isKeyPressed(65)) { 		moveComponent.setDirection(225); 	}
				else if(KeyInput.isKeyPressed(68)){ 	moveComponent.setDirection(315); 	}
				else { 									moveComponent.setDirection(270); 	}
			}
			else if(KeyInput.isKeyPressed(83)) {
				if(KeyInput.isKeyPressed(65)) { 		moveComponent.setDirection(135); 	}
				else if(KeyInput.isKeyPressed(68)){ 	moveComponent.setDirection(45); 	}
				else { 									moveComponent.setDirection(90); 	}
			}
			else if(KeyInput.isKeyPressed(65)) { 		moveComponent.setDirection(180); 	}
			else if(KeyInput.isKeyPressed(68)) { 		moveComponent.setDirection(0); 		}
			
			isMoving = true;
		}

		if(isMoving == true && moveComponent.isMoving() == false) {
			moveComponent.startMoving();
		}
		else {
			moveComponent.halt();
		}
	}
	
	private void updateFacingAngle() {
		Coord mousePosition = MouseInput.getMousePosition();
		PositionComponent posComp = (PositionComponent) this.getComponent(BaseComponent.POSITION);
		Coord playerPosition = posComp.getPosition();
		
		Angle facingAngle = Angle.angleBetweenCoords(playerPosition, mousePosition);
		
		DrawComponent drawing = (DrawComponent) this.getComponent(BaseComponent.DRAW);
		drawing.getBody().setRotation(facingAngle);
	}
	
	private void updateAttack() {
		if(MouseInput.isMousePressed(MouseInput.LEFT)) {
			ActiveListComponent abilities = (ActiveListComponent) this.getComponent(BaseComponent.ABILITIES);
			ActivatableComponent shootAbility = abilities.get("shoot");
			shootAbility.activate();
		}
	}

	public void draw(Graphics2D g) { }

	public void collide(BaseObject object) {
		if(object instanceof Monster) {
			DemoGame.gameOver();
		}
	}
}
