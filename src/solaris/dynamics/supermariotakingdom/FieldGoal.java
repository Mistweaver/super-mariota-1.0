package solaris.dynamics.supermariotakingdom;

import solaris.dynamics.supermariotakingdom.MainCharacter.JumpState;

public class FieldGoal extends GameObject {
	
	public FieldGoal() {
		yCoordinate = 25;
		height = 118;
		xVelocity = -1;
		width = 100;
	}
	
	public void setActive() {
		isActive = true;
		xCoordinate = 480;
	}

	public void collisionTest(MainCharacter mariota) {
		if(mariota.jumpState == JumpState.FALLING) {
			if((mariota.returnXCoordinate() + mariota.returnWidth()) >= xCoordinate && (mariota.returnXCoordinate() + mariota.returnWidth()) <= (xCoordinate + height)) {
				if((mariota.returnYCoordinate() + mariota.returnHeight()) <= 147 && (mariota.returnYCoordinate() + mariota.returnHeight()) >= 141) {
					mariota.jumpState = JumpState.STILL;
					mariota.jump_rate = 0;
					mariota.betweenFieldGoal = true;
					if(mariota.isSupersized) {
						mariota.yCoordinate = 144 - mariota.returnHeight();
					} else {
						mariota.yCoordinate = 144 - mariota.returnHeight();
					}
				}
			}
		}
		if(mariota.betweenFieldGoal == true) {
			if(mariota.returnXCoordinate() > (xCoordinate + width) ) {
				if(mariota.jumpState == JumpState.STILL) {
					mariota.jumpState = JumpState.FALLING;
					mariota.jump_rate = 48;
				}
				mariota.betweenFieldGoal = false;
			}
		}
	}

}
