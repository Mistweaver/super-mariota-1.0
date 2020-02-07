package solaris.dynamics.supermariotakingdom;

import java.util.Random;

public class H2OPower extends GameObject {
	
	Random rand = new Random();
	public H2OPower() {
		xVelocity =  -1;
		height = 18;
		width = 5;
		
	}
	
	public void setActive() {
		isActive = true;
		int  n = rand.nextInt(150) + 50;
		xCoordinate = 480;
		yCoordinate = n;

	}

}
