package engine.components;

import java.util.Map;

import engine.core.UpdateEvent;
import engine.core.Updateable;

public class ActiveListComponent extends ListComponent implements Updateable {

// Events
	
	public void update(UpdateEvent ev) {
		for(Map.Entry<String, BaseComponent> entry : this.getList().entrySet()) {
			ActivatableComponent item = (ActivatableComponent) entry.getValue();
			item.update(ev);
		}
	}
	
// Set-get list item
	
	public void set(String name, BaseComponent item){
		item.setParent(this);
		super.set(name, item);
	}
	
	public ActivatableComponent get(String name){ return (ActivatableComponent) this.list.get(name); }
}
