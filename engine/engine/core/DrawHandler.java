package engine.core;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.util.HashMap;
import java.util.Map;

import engine.components.BaseComponent;

public class DrawHandler extends BaseHandler {
	
// Set reference list object
	
	public void add(BaseObject object){ 	this.add(object, BaseComponent.DRAW); 		}
	
	public void remove(BaseObject object){ 	this.remove(object, BaseComponent.DRAW); 	}
	
// Instance methods
	
	public void handleAll() {
		BufferStrategy bufferStrategy = GameWindow.getCanvas().getBufferStrategy();
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		GameWindow.clearCanvas(g);
		
		for(int i = 0; i < this.referenceList.size(); i++) {
			if(this.referenceList.get(i) != null) {
				int index = this.referenceList.get(i);
				BaseObject object = ObjectRegistry.get(index);
				handle((Drawable) object, g);
				//g.dispose();
			}
		}
		
		bufferStrategy.show();
	}
	
	protected void handle(Drawable object, Graphics2D g) {
		HashMap<String, BaseComponent> components = ((BaseObject)object).getComponents();
		AffineTransform gRestorePoint;
		
		for(Map.Entry<String, BaseComponent> entry : components.entrySet()) {
		    BaseComponent component = entry.getValue();

		    if(component instanceof Drawable) {
			    gRestorePoint = g.getTransform();
		    	((Drawable) component).draw(g);
		    	g.setTransform(gRestorePoint);
		    }
		    
		}
		
		gRestorePoint = g.getTransform();
		object.draw(g);
		g.setTransform(gRestorePoint);
	}
}
