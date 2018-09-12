package engine.components;

import java.util.HashMap;

public class ListComponent extends BaseComponent {
	protected HashMap<String, BaseComponent> list = new HashMap<String, BaseComponent>();
	
// Set-get-flag list item
	
	public void set(String name, BaseComponent item){ this.list.put(name,  item); }
	
	public void remove(String name){ this.list.remove(name); }

	public BaseComponent get(String name){ return this.list.get(name); }
	
	public HashMap<String, BaseComponent> getList(){ return this.list; }

	public boolean contains(String name){ return this.list.containsKey(name); }
}
