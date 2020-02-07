package solaris.dynamics.supermariotakingdom;

import framework.Pixmap;

public class GameObject {
	
	protected int xCoordinate;
	protected int yCoordinate;
	protected int width;
	protected int height;
	protected int xVelocity;
	protected int yVelocity;
	protected boolean isActive;
	
	public GameObject() {
		isActive = false;
	}
	
	protected boolean isActive() {
		return isActive;
	}
	
	protected void setActive() {
		isActive = true;
	}
	
	protected void deActivate() {
		isActive = false;
	}
	
	protected int returnXCoordinate() {
		return xCoordinate;
	}
	
	protected int returnYCoordinate() {
		return yCoordinate;
	}
	
	protected void setXCoordinate(int x) {
		xCoordinate = x;
	}
	
	protected void setYCoordinate(int y) {
		yCoordinate = y;
	}
	
	protected void updateXCoordinate() {
		xCoordinate += xVelocity;
	}
	
	protected void updateYCoordinate() {
		yCoordinate += yVelocity;
	}
	
	protected int returnWidth() {
		return width;
	}
	
	protected int returnHeight() {
		return height;
	}
	
	protected void checkBounds() {
		if(xCoordinate > 480 || (xCoordinate + width) < 0 || yCoordinate < 0 || (yCoordinate + height) > 320 ) {
			deActivate();
		}
	}
}
