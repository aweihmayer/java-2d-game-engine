package engine.core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class GameWindow {
	private static JFrame frame;
	private static Canvas canvas;
	
// Static methods
	
	public static void init() {				
		createFrame();
		createCanvas();
		
		frame.add(canvas);
		canvas.createBufferStrategy(2);
	}
	
	private static void createFrame() {
		frame = new JFrame();
		frame.addMouseListener(new MouseInput());
		frame.setResizable(false);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);
		frame.setVisible(true);
	}
	
	private static void createCanvas() {
		canvas = new Canvas();
		canvas.addKeyListener(new KeyInput());
		canvas.addMouseListener(new MouseInput());
		canvas.setIgnoreRepaint(true);
		canvas.setBackground(Color.WHITE);
		canvas.setVisible(true);
		canvas.setFocusable(true);
	}
	
	public static void clearCanvas(Graphics g) { canvas.paint(g); }
	
// Get properties
	
	public static Graphics2D getDrawGraphics() {
		BufferStrategy bufferStrategy = canvas.getBufferStrategy();
		return (Graphics2D) bufferStrategy.getDrawGraphics();
	}
	
	public static int getWidth() { return canvas.getWidth(); }
	
	public static int getHeight() { return canvas.getHeight(); }
	
	public static Canvas getCanvas() { return canvas; }
}
