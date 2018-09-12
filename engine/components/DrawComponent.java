package engine.components;

import java.awt.Graphics2D;

import engine.core.BaseObject;
import engine.core.Drawable;
import engine.core.UpdateEvent;
import engine.core.Updateable;
import math.geometry.BaseShape;

public class DrawComponent extends HandlerComponent implements Drawable, Updateable {
	protected BaseShape body;
	
// Events
	
	public void update(UpdateEvent ev) {
		BaseShape body = this.getBody();
		
		if(body instanceof Updateable) {
			((Updateable) body).update(ev);
		}
	}
	
	public void draw(Graphics2D g) {
		this.getBody().draw(g);
	}
	
// Set-get body
	
	public void setBody(BaseShape body) {
		BaseObject parent = (BaseObject) this.getParent();
		PositionComponent posComp = (PositionComponent) parent.getComponent(BaseComponent.POSITION);
		body.setPosition(posComp.getPositionRef());
		this.body = body;
	}
	
	public BaseShape getBody() { return this.body; }
}