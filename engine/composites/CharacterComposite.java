package engine.composites;

import engine.components.ActiveListComponent;
import engine.components.BaseComponent;

public abstract class CharacterComposite extends BodyComposite {
	
// Constructors
	
	public CharacterComposite() {
		super();
		this.addComponent(BaseComponent.ABILITIES, new ActiveListComponent());
	}
}
