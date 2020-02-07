package solaris.dynamics.supermariotakingdom;

public class Blimp extends GameObject {
	
	public Blimp() {
		width = 68;
		height = 23;
		xVelocity = -1;
		yCoordinate = 45;
	}
	
	public void setActive() {
		isActive = true;
		xCoordinate = 480;
	}
}
