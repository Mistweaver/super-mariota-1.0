package solaris.dynamics.supermariotakingdom;

import java.util.Random;

import solaris.dynamics.supermariotakingdom.MainCharacter.JumpState;
import framework.Pixmap;

public class Defenders extends GameObject {
	
	private static final float defender_animation_rate = 0.4f;
	private static final float defender_run_animation_rate = 0.2f;
	private float defender_animation_count = defender_animation_rate;
	private float defender_run_animation_count = defender_run_animation_rate;
	public Pixmap current_animation = Assets.defenderStill;
	DefenderJumpState defender_jumpState = DefenderJumpState.STILL;
	public boolean is_defender_jumper;
	private int defender_run_Phase = 0;
	private int defender_jump_start;
	private int defender_jump_max = 90;
	public int defender_jump_count;
	private int defender_jump_rate = 0;
	private Random rand = new Random();
	
	
	enum DefenderJumpState {
		STILL,
		RISING,
		FALLING
	}
	
	public Defenders() {
		yCoordinate = 190;
		width = 24;
		height = 36;
		defender_jump_count = 0;
	}
	
	public void setActive() {
		isActive = true;
		int  n = rand.nextInt(3) + 1;
		xVelocity = -n;
		xCoordinate = 480;
	}
	
	public void deActivate() {
		isActive = false;
	}
	
	public void defenderJump() {
		if(defender_jumpState == DefenderJumpState.STILL) {
			defender_jumpState = DefenderJumpState.RISING;
			defender_jump_start = yCoordinate;
		}
	}
	
	public void updateDefender() {
		updateXCoordinate();
		if(defender_jumpState == DefenderJumpState.RISING) {
			defender_jump_rate++;
			if((defender_jump_start - yCoordinate) < defender_jump_max) {
				yCoordinate = (int) (defender_jump_start - (-0.0444 * defender_jump_rate * defender_jump_rate + 4 * defender_jump_rate));
			}
		}
		if(defender_jumpState == DefenderJumpState.FALLING) {
			defender_jump_rate--;
			yCoordinate = (int) (defender_jump_start - (-0.0444 * defender_jump_rate * defender_jump_rate + 4 * defender_jump_rate));
		}
	}
	
	
	public Pixmap updateAnimation(float deltaTime) {
		defender_run_animation_count -= deltaTime;
		defender_animation_count -= deltaTime;
			if(defender_jumpState == DefenderJumpState.STILL) {
					if(defender_run_Phase == 0) {
						
						if (defender_run_animation_count < 0) {
							defender_run_Phase = 1;
							defender_run_animation_count = defender_run_animation_rate;
						}
						return Assets.defenderRun;
						
					} else if (defender_run_Phase == 1) {
						
						if (defender_run_animation_count < 0) {
							defender_run_Phase = 0;
							defender_run_animation_count = defender_run_animation_rate;
						}
						return Assets.defenderStill;
					}
			} else if(defender_jumpState == DefenderJumpState.RISING || defender_jumpState == DefenderJumpState.FALLING) {
				return Assets.defenderJump;
			}
		return current_animation;
	}
	
public void checkBounds() {
		//check screen bounds
		if((xCoordinate + width) < 0) {
			deActivate();
		}
		//check ceiling collision
		if((defender_jump_start - yCoordinate) >= defender_jump_max ) {
			defender_jumpState = DefenderJumpState.FALLING;
		}
		//check floor collision
		if((yCoordinate + height) > 225) {
			defender_jumpState = DefenderJumpState.STILL;
			yCoordinate = 190;
		}
	}
}
