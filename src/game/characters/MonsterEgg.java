package game.characters;

import java.awt.Graphics2D;

import engine.components.BaseComponent;
import engine.components.DrawComponent;
import engine.components.HitboxComponent;
import engine.components.PositionComponent;
import engine.composites.BodyComposite;
import engine.core.BaseObject;
import engine.core.ObjectRegistry;
import engine.core.UpdateEvent;
import engine.resources.Sprite;
import math.geometry.Circle;
import math.values.Coord;
import utility.Time;

public class MonsterEgg extends BodyComposite {
	private long hatchTime;
		
	public MonsterEgg(Coord position) {
		PositionComponent positionComponent = (PositionComponent) this.getComponent(BaseComponent.POSITION);
		positionComponent.setPosition(position);
		
		DrawComponent drawing = (DrawComponent) this.getComponent(BaseComponent.DRAW);
		drawing.setBody(new Sprite("egg", 30));
		
		HitboxComponent hitbox = (HitboxComponent) this.getComponent(BaseComponent.HITBOX);
		hitbox.setBody(new Circle(50));
		
		this.hatchTime = Time.addTimeToNow(8000);
	}

	public void update(UpdateEvent ev) {
		if(Time.isTimeLaterThanNow(hatchTime) == false) {
			this.hatch();
		} else if(Time.isTimeLaterThanNow(hatchTime - 3000) == false) {
			DrawComponent drawing = (DrawComponent) this.getComponent(BaseComponent.DRAW);
			Sprite sprite = (Sprite) drawing.getBody();
			sprite.setAnimation("hatching");
		}
	}
	
	private void hatch() {
		PositionComponent positionComponent = (PositionComponent) this.getComponent(BaseComponent.POSITION);
		Monster monster = new Monster(positionComponent.getPosition());
		ObjectRegistry.add(monster);
		ObjectRegistry.remove(this);
	}
	
	public void draw(Graphics2D g) { }
	
	public void collide(BaseObject object) {
		if(object instanceof Player) {
			ObjectRegistry.remove(this);
		}
	}
}
