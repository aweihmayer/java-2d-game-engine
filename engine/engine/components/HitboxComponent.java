package engine.components;

import engine.core.BaseObject;
import math.geometry.BaseShape;

public class HitboxComponent extends HandlerComponent {
	protected int handlerCheckStatus = 1;
	protected BaseShape body;
	
// Set-get body
	
	public void setBody(BaseShape body) {
		BaseObject parent = (BaseObject) this.getParent();
		PositionComponent posComp = (PositionComponent) parent.getComponent(BaseComponent.POSITION);
		body.setPosition(posComp.getPositionRef());
		this.body = body;
	}
	
	public BaseShape getBody() { 					return this.body; 					}
	
// Set-get handler check status
	
	public void setHandlerCheckStatus(int status) {	this.handlerCheckStatus = status; 	}
	
	public int getHandlerCheckStatus() {			return this.handlerCheckStatus; 	}
}
