package engine.components;

public abstract class BaseComponent {
	protected Object parent;
	
	public static final String UPDATE = 	"update";
	public static final String DRAW = 		"draw";
	public static final String POSITION = 	"position";
	public static final String MOVE = 		"move";
	public static final String HITBOX = 	"hitbox";
	public static final String ABILITIES = 	"abilities";

// Set-get parent
	
	public void setParent(Object parent) { 			this.parent = parent; 		}
	
	public Object getParent() { 					return this.parent; 		}
	
	public Object getParent(int depth) {
		Object parent = this.getParent();
		
		for(int i = 1; i < depth; i++) {
			parent = ((BaseComponent) parent).getParent();
		}
		
		return parent;
	}
}
