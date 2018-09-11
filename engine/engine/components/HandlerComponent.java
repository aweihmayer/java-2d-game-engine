package engine.components;

public class HandlerComponent extends BaseComponent {
	protected int referenceListIndex;
	
// Set-get reference list index
	
	public void setReferenceListIndex(int refIndex) { 	this.referenceListIndex = refIndex; }
	
	public int getReferenceListIndex() { 				return this.referenceListIndex; 	}
}
