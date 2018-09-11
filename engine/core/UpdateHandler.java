package engine.core;

import java.util.HashMap;
import java.util.Map;

import engine.components.BaseComponent;

public class UpdateHandler extends BaseHandler {
	
// Set reference list object
	
	public void add(BaseObject object){ 	this.add(object, BaseComponent.UPDATE); 	}
	
	public void remove(BaseObject object){ 	this.remove(object, BaseComponent.UPDATE); 	}
	
// Instance methods
	
	public void handleAll(UpdateEvent ev) {
		for(int i = 0; i < this.referenceList.size(); i++) {
			if(this.referenceList.get(i) != null) {
				int index = this.referenceList.get(i);
				Updateable object = (Updateable) ObjectRegistry.get(index);
				
				if(object != null) {
					this.handle(object, ev);
				}
			}
		}
	}
	
	protected void handle(Updateable object, UpdateEvent ev) {
		HashMap<String, BaseComponent> components = ((BaseObject)object).getComponents();
		
		for(Map.Entry<String, BaseComponent> entry : components.entrySet()) {
		    BaseComponent component = entry.getValue();
		    
		    if(component instanceof Updateable) {
		    	((Updateable) component).update(ev);
		    }
		}
		
		object.update(ev);
	}
}
