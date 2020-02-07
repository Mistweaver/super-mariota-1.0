package solaris.dynamics.supermariotakingdom;

import java.util.Random;

public class PowerUp extends GameObject {
	Random rand = new Random();
	public PowerUp() {
		xVelocity =  -1;
		height = 19;
		width = 19;
		
	}
	
	public void setActive() {
		isActive = true;
		int  n = rand.nextInt(150) + 50;
		xCoordinate = 480;
		yCoordinate = n;

	}
	


}
