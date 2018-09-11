package engine.core;

import java.util.List;

import java.util.ArrayList;
import java.util.HashMap;

public class ObjectRegistry {
	private static List<BaseObject> 			registry = 			new ArrayList<BaseObject>();
	private static HashMap<String, BaseObject> 	namedRegistry = 	new HashMap<String, BaseObject>();
	
	private static List<Integer> 				availableIndexes = 	new ArrayList<Integer>();
	private static List<BaseObject> 			objectsToAdd = 		new ArrayList<BaseObject>();
	private static List<BaseObject> 			objectsToRemove = 	new ArrayList<BaseObject>();
	
	private static UpdateHandler 				updateHandler = 	new UpdateHandler();
	private static DrawHandler 					drawHandler = 		new DrawHandler();
	private static CollisionHandler 			collisionHandler = 	new CollisionHandler();
	
// Set-get registry object
	
	public static void reset() {
		registry.clear();
		availableIndexes.clear();
		objectsToAdd.clear();
		objectsToRemove.clear();
		
		updateHandler.reset();
		drawHandler.reset();
		collisionHandler.reset();
	}
	
	public static void add(BaseObject object) {
		if(object instanceof BaseObject) {
			objectsToAdd.add(object);
		}
	}
	
	public static void add(BaseObject object, String key) {
		add(object);
		namedRegistry.put(key, object);
	}
	
	public static void remove(BaseObject object) {
		int index = object.getRegistryIndex();
		object.setRemovedFromRegistry();
		
		objectsToRemove.add(object);
		availableIndexes.add(index);
	}
	
	public static BaseObject get(int index) {		
		return get(index, false);
	}
	
	public static BaseObject get(int index, boolean validIfRemoved) {
		BaseObject object = null;

		if(registry.get(index) != null) {
			object = registry.get(index);
			
			if(object.isRemovedFromRegistry() == true && validIfRemoved == false) {
				object = null;
			}
		}
		
		return object;
	}
	
	public static BaseObject get(String key) {		
		return get(key, false);
	}
	
	public static BaseObject get(String key, boolean validIfRemoved) {
		BaseObject object = null;
		
		if(namedRegistry.get(key) != null) {
			object = namedRegistry.get(key);
			
			if(object.isRemovedFromRegistry() == true && validIfRemoved == false) {
				object = null;
			}
		}
		
		return object;
	}

// Events
	
	public static void updatePhase(UpdateEvent ev) {
		updateHandler.handleAll(ev);
		collisionHandler.handleAll();
		clean();
	}
	
	public static void drawPhase() {
		drawHandler.handleAll();
	}
	
// Static methods
	
	private static void clean() {
		completeRemoveProcess();
		completeAddProcess();
	}
	
	private static void completeRemoveProcess() {
		for(int i = 0; i < objectsToRemove.size(); i++) {
			BaseObject object = objectsToRemove.get(i);
			
			if(object instanceof Updateable) { 	updateHandler.remove(object); 		}
			if(object instanceof Drawable) { 	drawHandler.remove(object); 		}
			if(object instanceof Collideable) { collisionHandler.remove(object); 	}
			
			registry.set(object.getRegistryIndex(), null);
		}
		
		objectsToRemove.clear();
	}
	
	private static void completeAddProcess() {
		for(int i = 0; i < objectsToAdd.size(); i++) {
			BaseObject object = objectsToAdd.get(i);
			int registryIndex;
			
			if(availableIndexes.isEmpty() != true) {
				registryIndex = availableIndexes.remove(0);
				registry.set(
					registryIndex,
					object
				);
			}
			else {
				registryIndex = registry.size();
				registry.add(object);
			}
			
			object.setRegistryIndex(registryIndex);
			
			if(object instanceof Updateable) { 	updateHandler.add(object); 		}
			if(object instanceof Drawable) { 	drawHandler.add(object); 		}
			if(object instanceof Collideable) { collisionHandler.add(object); 	}
		}
		
		objectsToAdd.clear();
	}
}
