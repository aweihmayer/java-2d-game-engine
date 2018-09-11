package engine.components;

import java.awt.Graphics2D;

import engine.core.BaseObject;
import engine.core.Drawable;
import math.geometry.BaseShape;

public class DrawComponent extends HandlerComponent implements Drawable {
	protected BaseShape body;
	
// Events
	
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