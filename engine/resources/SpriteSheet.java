package engine.resources;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class SpriteSheet {	
	protected String name;
	protected String source;
	protected BufferedImage image;
	protected SpriteFrame[][] frames;
	protected int width;
	protected int height;
	protected HashMap<String, SpriteAnimation> animations = new HashMap<String, SpriteAnimation>();
	
// Constructors
	
	public SpriteSheet(String name, String source, int frameWidth, int frameHeight) {
		this.name = name;

		try {
			InputStream stream = getClass().getResourceAsStream(source);
			this.image = ImageIO.read(stream);
			this.width = this.image.getWidth();
			this.height = this.image.getHeight();
			this.splitSheetIntoFrames(frameWidth, frameHeight);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		this.addAnimation(new SpriteAnimation(SpriteAnimation.DEFAULT));
	}

// Set animation
	
	public void addAnimation(SpriteAnimation animation) { 	this.animations.put(animation.getName(),  animation); 	}
	
// Get configurations
	
	public String getName() { 								return this.name; 										}
	
	public SpriteFrame getFrame(int col, int row) { 		return this.frames[col][row]; 							}
	
	public String getSource() { 							return this.source; 									}
			
	public SpriteAnimation getAnimation(String name) { 		return this.animations.get(name); 						}
	
// Instance methods
	
	private void splitSheetIntoFrames(int frameWidth, int frameHeight) {
		int colCount = this.width / frameWidth;
		int rowCount = this.height / frameHeight;
		this.frames = new SpriteFrame[colCount][rowCount];
		
		for(int col = 0; col < colCount; col++) {
			for(int row = 0; row < rowCount; row++) {
				BufferedImage frameImage = this.image.getSubimage(
					col * frameWidth, row * frameHeight,
					frameWidth, frameHeight
				);
				this.frames[col][row] = new SpriteFrame(frameImage, frameWidth, frameHeight);
			}
		}
	}
}
