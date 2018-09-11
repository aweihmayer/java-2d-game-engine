package game.abilities;

import engine.components.ActivatableComponent;
import engine.components.BaseComponent;
import engine.components.PositionComponent;
import engine.core.MouseInput;
import engine.core.ObjectRegistry;
import game.characters.Player;
import game.projectiles.BulletProjectile;
import math.values.Angle;
import math.values.Coord;
import math.values.Number;

public class ShootAbility extends ActivatableComponent {
	public ShootAbility() {
		super(new long[1], 1000);
	}

	public void whileActive() {
		
	}

	public void whileInactive() {
		
	}

	public void onActivate() {
		Player player = (Player) this.getParent(2);
		BulletProjectile bullet;
		Coord bulletPosition;
		Coord playerPosition = ((PositionComponent) player.getComponent(BaseComponent.POSITION)).getPosition();
		Angle bulletDirection;
		Angle originalDirection = Angle.angleBetweenCoords(
			playerPosition,
			MouseInput.getMousePosition()
		);
		
		for(int i = 0; i < 6; i++) {
			bulletPosition = new Coord(playerPosition);
			bulletDirection = new Angle(originalDirection);
			bulletDirection.add(Number.random(-8, 8));
			
			bullet = new BulletProjectile(bulletPosition, bulletDirection);
			ObjectRegistry.add(bullet);
		}
	}

	public void onPhaseChange() {
		
	}

	public void onFinish() {
		
	}

	public void whileOnCooldown() {
		
	}
}
