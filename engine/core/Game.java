package engine.core;
import utility.Time;

public class Game {
	private static boolean isPlaying = false;
	private static int framesPerSecond = 60;
	private static double frameDuration;
	private static int timeSkip;
	private static UpdateEvent lastUpdateEvent;
	
// Static methods
	
	public static void init() {
		frameDuration = 1 / getFps();
		timeSkip = (int) (frameDuration * 1000);
		lastUpdateEvent = new UpdateEvent();
		
		GameWindow.init();
		start();
	}
	
	public static void loop() {
		if(isPlaying() == true) {
			int loops = 0;
			long nextGameTick = Time.addTimeToNow(0);
	
			while(Time.isTimeLaterThanNow(nextGameTick) == false
			&& loops < 5){
				lastUpdateEvent = new UpdateEvent(lastUpdateEvent);
				ObjectRegistry.updatePhase(lastUpdateEvent);
				nextGameTick += timeSkip;
				loops++;
			}
	
			ObjectRegistry.drawPhase();
		}
	}
	
	public static void start() {				isPlaying = true; 			}
	
	public static void pause() { 				isPlaying = false; 			}
	
	public static boolean isPlaying(){ 			return isPlaying; 			}
	
// Get configurations
	
	public static int getFps() { 				return framesPerSecond; 	}
	
	public static double getFrameDuration() {	return frameDuration; 		}
}
