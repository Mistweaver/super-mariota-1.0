package solaris.dynamics.supermariotakingdom;

import framework.Pixmap;

public class MainCharacter extends GameObject {
	
	public int height_supersize = 35;
	public int width_supersize = 24;
	//CharacterStates
	enum JumpState {
		STILL,
		RISING,
		FALLING
	}
	boolean isThrowing = false;
	boolean isSupersized = false;
	boolean hasThrown = false;
	boolean betweenFieldGoal = false;
	
	private int jump_max = 90;
	public int jump_start;
	private int throw_Phase;
	private int run_Phase = 0;
	JumpState jumpState = JumpState.STILL;
	private static final float animation_rate = 0.4f;
	private static final float run_animation_rate = 0.2f;
	private float animation_count = animation_rate;
	private float run_animation_count = run_animation_rate;
	public Pixmap current_animation = Assets.SuperMariota;
	public int jump_rate = 0;
	
	
	public MainCharacter() {
		xCoordinate = 60;
		yCoordinate = 198;
		width = 19;
		height = 27;
		isActive = true;
	}
	
	public void powerUp() {
		isSupersized = true;
		width = width_supersize;
		height = height_supersize;
	}
	
	public void powerDown() {
		isSupersized = false;
		width = 19;
		height = 27;
		if(jumpState == JumpState.STILL) {
			yCoordinate = 198;
		}
	}
	
	public void deActivate() {
		if(isSupersized == true) {
			powerDown();
		} else {
			isActive = false;
		}
	}
	
	public void updateLocation() {
		if(jumpState == JumpState.RISING) {
			jump_rate++;
			if((jump_start - yCoordinate) < jump_max) {
				yCoordinate = (int) (jump_start - (-0.0444 * jump_rate * jump_rate + 4 * jump_rate));
			}
		}
		if(jumpState == JumpState.FALLING) {
			jump_rate++;
			yCoordinate = (int) (jump_start - (-0.0444 * jump_rate * jump_rate + 4 * jump_rate));
		}
		
	}
	
	
	public void jump() {
		if(jumpState == JumpState.STILL) {
			jumpState = JumpState.RISING;
			jump_start = yCoordinate;
		}
	}
	
	public boolean throwFootball() {
		
		if(isSupersized) {
			hasThrown = true;
			isThrowing = true;
			throw_Phase = 1;
		}
		return hasThrown;
	}
	
	public void checkBounds() {
		//check ceiling collision
		if((jump_start - yCoordinate) >= jump_max) {
			jumpState = JumpState.FALLING;
		}
		//check floor collision
		if((yCoordinate + height) > 225) {
			jumpState = JumpState.STILL;
			jump_rate = 0;
			if(isSupersized) {
				yCoordinate = 190;
			} else {
				yCoordinate = 198;
			}
		}
	}
	
	
	public Pixmap updateAnimation(float deltaTime) {
		run_animation_count -= deltaTime;
		animation_count -= deltaTime;
			if(jumpState == JumpState.STILL) {
				if(isSupersized) {	//Use SuperMariota2### files
					if(isThrowing) {
						if(throw_Phase == 1) {
							if (animation_count < 0) {
								throw_Phase += 1;
								animation_count = animation_rate;
							}
							return Assets.SuperMariota2Throw1;
						} else if(throw_Phase == 2) {
							if (animation_count < 0) {
								throw_Phase += 1;
								animation_count = animation_rate;
							}
							return Assets.SuperMariota2Throw2;
						} else if(throw_Phase == 3) {
							if (animation_count < 0) {
								isThrowing = false;
								animation_count = animation_rate;
							}
							return Assets.SuperMariota2Empty;
						}
					} else {
						if(run_Phase == 0) {
							
							if (run_animation_count < 0) {
								run_Phase = 1;
								run_animation_count = run_animation_rate;
							}
							return Assets.SuperMariota2;
						} else if (run_Phase == 1) {
							
							if (run_animation_count < 0) {
								run_Phase = 0;
								run_animation_count = run_animation_rate;
							}
							return Assets.SuperMariota2Run1;
						}	
					}
				} else {
					if(run_Phase == 0) {
						
						if (run_animation_count < 0) {
							run_Phase = 1;
							run_animation_count = run_animation_rate;
						}
						return Assets.SuperMariota;
						
					} else if (run_Phase == 1) {
						
						if (run_animation_count < 0) {
							run_Phase = 0;
							run_animation_count = run_animation_rate;
						}
						return Assets.SuperMariotaRun1;
					}
				
				}
			} else if(jumpState == JumpState.RISING || jumpState == JumpState.FALLING) {
				if(isSupersized) { //Use SuperMariota2### files
					if(isThrowing) {
						if(throw_Phase == 1) {
							if (animation_count < 0) {
								throw_Phase += 1;
								animation_count = animation_rate;
							}
							return Assets.SuperMariota2JumpThrow1;
						} else if(throw_Phase == 2) {
							if (animation_count < 0) {
								throw_Phase += 1;
								animation_count = animation_rate;
							}
							return Assets.SuperMariota2JumpThrow2;
						} else if(throw_Phase == 3) {
							if (animation_count < 0) {
								isThrowing = false;
								animation_count = animation_rate;
							}
							return Assets.SuperMariota2JumpEmpty;
						}
					} else {
						animation_count = animation_rate;
						return Assets.SuperMariota2Jump1;
					}
				} else {
					animation_count = animation_rate;
					return Assets.SuperMariotaRun2;
				
				}
			}
			
		return current_animation;
	}
}
