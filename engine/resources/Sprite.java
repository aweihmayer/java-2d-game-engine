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
	protected int animationIndex = 0;
	protected long nextFrameTime = 0;
	
// Constructors
	
	public Sprite(String spriteSheetName, double size) {
		this(spriteSheetName, size, size);
	}
	
	public Sprite(String spriteSheetName, double width, double height) {
		super(width, height);
		this.spriteSheet = ResourceRegistry.getSpriteSheet(spriteSheetName);
		this.setAnimation(SpriteAnimation.DEFAULT);
	}

// Set animation
	
	public void setAnimation(String animationName) {
		this.animationIndex = 0;
		this.animation = this.spriteSheet.getAnimation(animationName);
		this.repeatAnimation();
	}
	
// Instance methods
	
	public void update(UpdateEvent ev) {
		if(this.animation.getFrameCount() > 1 && this.isTimeForNextFrame()) {
			if(this.isAnimationDone() && this.animation.isRepeatable()) {
				this.repeatAnimation();
			}
			else {
				this.moveToNextFrame();
			}
		}
	}

	public void draw(Graphics2D g) {		
		int[] frame = this.animation.getFrameAtIndex(this.animationIndex);
		this.spriteSheet.getFrame(frame[0], frame[1]).draw(g, this);
	}
	
	public void repeatAnimation() {
		this.animationIndex = 0;
		this.nextFrameTime = Time.addTimeToNow(this.animation.getSpeed());
	}
	
	public void moveToNextFrame() {
		this.animationIndex++;
		this.nextFrameTime = Time.addTimeToNow(this.animation.getSpeed());
	}
	
	protected boolean isTimeForNextFrame() {
		return !Time.isTimeLaterThanNow(this.nextFrameTime);
	}
	
	protected boolean isAnimationDone() {
		return this.animationIndex == this.animation.getFrameCount() - 1;
	}
}
