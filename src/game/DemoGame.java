package game;

import engine.core.Game;
import engine.core.GameWindow;
import engine.core.ObjectRegistry;
import engine.core.ResourceRegistry;
import math.values.Coord;
import game.characters.Player;
import game.events.MonsterSpawn;

public class DemoGame {	
	public static void main(String[] args) {
		Game.init();

		ResourceRegistry.loadSpriteSheets("/config/sprites.xml");
		reset();
		
		while(true) {
			Game.loop();
		}
	}
	
	private static void reset() {
		Player player = new Player(new Coord(220, 220));
		ObjectRegistry.add(player, "player");

		ObjectRegistry.add(new MonsterSpawn(
			GameWindow.getWidth(),
			GameWindow.getHeight()
		));
	}
	
	public static void gameOver() {
		ObjectRegistry.reset();
	}
}
