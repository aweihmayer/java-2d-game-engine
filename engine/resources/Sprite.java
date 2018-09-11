package engine.resources;

import java.awt.Graphics2D;

import engine.core.ResourceRegistry;
import engine.core.UpdateEvent;
import engine.core.Updateable;
import math.geometry.SimpleShape;
import utility.Time;

public class Sprite extends SimpleShape implements Updateable {
	protected SpriteSheet spriteSheet;
	protected SpriteAnimation animation;
	protected long nextFrameTime = 0;
	protected int currentRow = 0;
	protected int currentCol = 0;
	
// Constructors
	
	public Sprite(String spriteSheetName, double size) {
		this(spriteSheetName, size, size);
	}
	
	public Sprite(String spriteSheetName, double width, double height) {
		super(width, height);
		this.spriteSheet = ResourceRegistry.getSpriteSheet(spriteSheetName);
	}

// Set animation
	
	public void setAnimation(String animationName) {
		this.animation = this.spriteSheet.getAnimation(animationName);
		this.repeatAnimation();
	}
	
// Instance methods
	
	public void update(UpdateEvent ev) {
		if(this.isTimeForNextFrame()) {
			if(this.isAnimationDone() && this.animation.isRepeatable()) {
				this.repeatAnimation();
			}
			else {
				this.moveToNextFrame();
			}
		}
	}

	public void draw(Graphics2D g) {
		this.spriteSheet.getFrame(this.currentCol, this.currentRow).draw(g, this);
	}
	
	public void repeatAnimation() {
		this.currentCol = this.animation.startCol;
		this.currentRow = this.animation.startRow;
	}
	
	public void moveToNextFrame() {
		this.currentCol += this.animation.colDirection;
		this.currentRow += this.animation.rowDirection;
		this.nextFrameTime = Time.addTimeToNow(this.animation.getSpeed());
	}
	
	protected boolean isTimeForNextFrame() {
		return !Time.isTimeLaterThanNow(this.nextFrameTime);
	}
	
	protected boolean isAnimationDone() {
		return this.currentCol == animation.endCol && this.currentRow == animation.endRow;
	}
}
