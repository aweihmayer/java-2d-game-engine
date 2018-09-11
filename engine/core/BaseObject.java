package engine.core;

import java.util.HashMap;
import java.util.Map;

import engine.components.BaseComponent;

public abstract class BaseObject {
	protected int registryIndex = -1;
	protected boolean isRemovedFromRegistry = false;
	protected HashMap<String, BaseComponent> components = new HashMap<String, BaseComponent>();
	
// Set-get-flag components
	
	public void setComponents(HashMap<String, BaseComponent> components){
		for(Map.Entry<String, BaseComponent> entry : components.entrySet()) {
		    this.addComponent(entry.getKey(), entry.getValue());
		}
	}
	
	public void addComponent(String name, BaseComponent component){
		component.setParent(this);
		this.components.put(name, component);
	}
	
	public HashMap<String, BaseComponent> getComponents(){ 	return this.components; 					}
	
	public BaseComponent getComponent(String name){ 		return this.components.get(name); 			}
	
	public boolean hasComponent(String name){ 				return this.components.containsKey(name); 	}

// Set-get-flag registry index
	
	public void setRegistryIndex(int index){ 				this.registryIndex = index; 				}
	
	public int getRegistryIndex(){ 							return this.registryIndex; 					}
		
	public void setRemovedFromRegistry() { 					this.isRemovedFromRegistry = true; 			}
	
	public boolean isRemovedFromRegistry(){ 				return this.isRemovedFromRegistry; 			}
}
