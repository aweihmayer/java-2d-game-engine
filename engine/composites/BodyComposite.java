package engine.composites;

import java.util.HashMap;

import engine.components.BaseComponent;
import engine.components.DrawComponent;
import engine.components.HitboxComponent;
import engine.components.MoveComponent;
import engine.components.PositionComponent;
import engine.components.UpdateComponent;
import engine.core.BaseObject;
import engine.core.Collideable;
import engine.core.Drawable;
import engine.core.Updateable;

public abstract class BodyComposite extends BaseObject implements Updateable, Drawable, Collideable {

// Constructors
	
	public BodyComposite() {
		HashMap<String, BaseComponent> components = new HashMap<String, BaseComponent>();
		components.put(BaseComponent.UPDATE, 	new UpdateComponent());
		components.put(BaseComponent.DRAW, 		new DrawComponent());
		components.put(BaseComponent.POSITION, 	new PositionComponent());
		components.put(BaseComponent.MOVE, 		new MoveComponent());
		components.put(BaseComponent.HITBOX, 	new HitboxComponent());
		
		this.setComponents(components);
	}
}
