package solaris.dynamics.supermariotakingdom;

public class Referee extends GameObject {
	
	public Referee() {
		width = 24;
		height = 35;
		xVelocity = -1;
		yCoordinate = 190;
	}
	
	public void setActive() {
		isActive = true;
		xCoordinate = 480;
		System.out.println("Referee Active");
	}

}
