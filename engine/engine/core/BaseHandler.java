package engine.core;

import java.util.ArrayList;

import engine.components.HandlerComponent;

public abstract class BaseHandler {
	protected ArrayList<Integer> referenceList = 	new ArrayList<Integer>();
	protected ArrayList<Integer> availableIndexes = new ArrayList<Integer>();
	
// Set reference list object
	
	public void reset() {
		this.referenceList.clear();
		this.availableIndexes.clear();
	}
	
	public void add(BaseObject object, String handlerComponentName){
		int registryIndex = object.getRegistryIndex();
		int referenceListIndex;

		if(availableIndexes.isEmpty() != true) {
			referenceListIndex = availableIndexes.remove(0);
			this.referenceList.set(
				referenceListIndex,
				registryIndex
			);
		}
		else {
			referenceListIndex = referenceList.size();
			this.referenceList.add(registryIndex);
		}
		
		HandlerComponent objectHandlerComponent = (HandlerComponent) object.getComponent(handlerComponentName);
		objectHandlerComponent.setReferenceListIndex(referenceListIndex);
	}
	
	public void remove(BaseObject object, String handlerComponentName){
		HandlerComponent objectHandlerComponent = (HandlerComponent) object.getComponent(handlerComponentName);
		int index = objectHandlerComponent.getReferenceListIndex();
		
		this.availableIndexes.add(index);
		this.referenceList.set(index, null);
	}
}
