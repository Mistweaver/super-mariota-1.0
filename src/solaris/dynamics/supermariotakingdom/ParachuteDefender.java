package solaris.dynamics.supermariotakingdom;

public class ParachuteDefender extends GameObject {

	public ParachuteDefender() {
		width = 35;
		height = 53;
		xVelocity = -1;
		yVelocity = 1;
		
		
	}
	
	public void setActive() {
		isActive = true;
		xCoordinate = 480;
		yCoordinate = -53;
		setActive();
		
	}
}
