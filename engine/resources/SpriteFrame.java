package engine.resources;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import math.values.Angle;
import math.values.Coord;

public class SpriteFrame {
	protected BufferedImage image;
	protected int width;
	protected int height;

// Constructors
	
	public SpriteFrame(BufferedImage image, int width, int height) {
		this.image = image;
		this.width = width;
		this.height = height;
	}

// Instance methods
	
	public void draw(Graphics2D g, Sprite sprite) {
		AffineTransform gReset = g.getTransform();
		
		Coord position = sprite.getPosition();
		Angle rotation = sprite.getRotation();
		
		int widthRadius = (int) sprite.getWidth() / 2;
		int heightRadius = (int) sprite.getHeight() / 2;
		int x = (int) position.getX();
		int y = (int) position.getY();

		g.rotate(rotation.getValueInRadians(), x, y);
		g.drawImage(
			this.image,
			x - widthRadius, y - heightRadius,
			(int) sprite.getWidth(), (int) sprite.getHeight(),
			null
		);
		g.setTransform(gReset);
	}
}
