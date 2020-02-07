package solaris.dynamics.supermariotakingdom;

import java.lang.Math;
import java.util.Random;
import framework.Graphics;

public class Stadium {
	
	public boolean gameOver = false;
	public int score = 0;
	public static float GAME_CONSTANT = 1.0f;
	public float gameTime = GAME_CONSTANT;
	public float spawntime = 1.0f;
	public int totalGameTime = 0;
	
	public DynamicBackground dynaGround = new DynamicBackground();
	public MainCharacter mariota = new MainCharacter();
	public Football primaryfootball = new Football();
	FieldGoal fieldGoal = new FieldGoal();
	PowerUp powerUp = new PowerUp();
	Referee referee = new Referee();
	Blimp blimp = new Blimp();
	Defenders[] defenderArray = new Defenders[10];
	public int defenderIndex = 0;
	H2OPower[] h2oArray = new H2OPower[5];
	public int h2oIndex = 0;
	
	public float defenderSpawnRate = 3.0f;
	public int fieldGoalSpawnRate = 12;
	public int h2oSpawnRate = 4;
	public int powerUpSpawnRate = 13;
	public int refereeSpawnRate = 16;
	public int blimpSpawnRate = 20;
	
	public int h2oSpawnCounter;
	public float defenderSpawnCounter;
	public int fieldGoalSpawnCounter;
	public int powerUpSpawnCounter;
	public int refereeSpawnCounter;
	public int blimpSpawnCounter;
	
	public Stadium() {
		
		fieldGoalSpawnCounter = fieldGoalSpawnRate;
		defenderSpawnCounter = defenderSpawnRate;
		h2oSpawnCounter = h2oSpawnRate;
		powerUpSpawnCounter = powerUpSpawnRate;
		refereeSpawnCounter = refereeSpawnRate;
		blimpSpawnCounter = blimpSpawnRate;
		
		for(int i = 0; i < defenderArray.length; i++) {
			defenderArray[i] = new Defenders();
		}
		for(int i = 0; i < h2oArray.length; i++) {
			h2oArray[i] = new H2OPower();
		}
	}
	
	public void update(Graphics g, float deltaTime) {
		spawnGameObjects(deltaTime);
		updateObjectLocations(deltaTime);
		checkObjectCollisions();
		scoreUpdate(deltaTime);
	}
	
	private void spawnGameObjects(float deltaTime) {
		spawntime -= deltaTime;
		
		if (spawntime < 0) {
			totalGameTime += 1;
			spawntime = 1.0f;
			fieldGoalSpawnCounter -= 1;
			defenderSpawnCounter -= 0.5;
			h2oSpawnCounter -= 1;
			powerUpSpawnCounter -= 1;
			refereeSpawnCounter -= 1;
			blimpSpawnCounter -= 1;
		}
		
		if((totalGameTime + 1) % 60 == 0) {
			defenderSpawnRate -= 0.2;
			totalGameTime += 1;
		}
		
		if(refereeSpawnCounter < 0) {
			referee.setActive();
			refereeSpawnCounter = refereeSpawnRate;
		}
		if(fieldGoalSpawnCounter < 0) {
			fieldGoal.setActive();
			fieldGoalSpawnCounter = fieldGoalSpawnRate;
		}
		if(blimpSpawnCounter < 0) {
			blimp.setActive();
			blimpSpawnCounter = blimpSpawnRate;
		}
		if(defenderSpawnCounter < 0) {
			defenderArray[defenderIndex].setActive();
			defenderIndex++;
			if(defenderIndex >= defenderArray.length) {
				defenderIndex = 0;
			}
			defenderSpawnCounter = defenderSpawnRate;
		}
		
		if(h2oSpawnCounter < 0) {
			h2oArray[h2oIndex].setActive();
			h2oIndex++;
			if(h2oIndex >= h2oArray.length) {
				h2oIndex = 0;
			}
			h2oSpawnCounter = h2oSpawnRate;
		}
		
		if(powerUpSpawnCounter < 0) {
			powerUp.setActive();
			powerUpSpawnCounter = powerUpSpawnRate;
		}
	}
	private void updateObjectLocations(float deltaTime) {
		mariota.updateLocation();
		if(mariota.hasThrown) {
			if(primaryfootball.isActive == false) {
				primaryfootball.setActive();
				primaryfootball.setXCoordinate(mariota.xCoordinate);
				primaryfootball.setYCoordinate(mariota.yCoordinate);
				mariota.hasThrown = false;
			} 
		}
		if(primaryfootball.isActive) {
			primaryfootball.updateXCoordinate();
		}
		if(fieldGoal.isActive) {
			fieldGoal.updateXCoordinate();
		}
		if(referee.isActive) {
			referee.updateXCoordinate();
		}
		if(powerUp.isActive) {
			powerUp.updateXCoordinate();
		}
		if(blimp.isActive) {
			blimp.updateXCoordinate();
		}
		for(Defenders def : defenderArray) {
			if(def.isActive) {
				def.updateXCoordinate();
			}
		}
		for(H2OPower pow : h2oArray) {
			if(pow.isActive) {
				pow.updateXCoordinate();
			}
		}
	}
	
	private void checkObjectCollisions() {
		mariota.checkBounds();
		if(primaryfootball.isActive) {
			primaryfootball.checkBounds();
			for(Defenders def : defenderArray) {
				if(def.isActive) {
					collisionTest(primaryfootball, def);
				}
			}
			if(blimp.isActive) {
				blimp.checkBounds();
				collisionTest(primaryfootball, blimp);
			}
			if(referee.isActive) {
				referee.checkBounds();
				collisionTest(primaryfootball, referee);
			}
		}
		if(blimp.isActive) {
			blimp.checkBounds();
			collisionTest(mariota, blimp);
		}
		if(fieldGoal.isActive) {
			fieldGoal.checkBounds();
			fieldGoal.collisionTest(mariota);
		}
		if(referee.isActive) {
			referee.checkBounds();
			collisionTest(mariota, referee);
		}
		if(powerUp.isActive) {
			powerUp.checkBounds();
			collisionTest(mariota, powerUp);
		}
		for(Defenders def : defenderArray) {
			if(def.isActive) {
				def.checkBounds();
				collisionTest(mariota, def);
			}
		}
		for(H2OPower pow : h2oArray) {
			if(pow.isActive) {
				pow.checkBounds();
				collisionTest(mariota, pow);
			}
		}
		
	}
	
	public void scoreUpdate(float deltaTime) {
		gameTime -= deltaTime;
		if (gameTime < 0) {
			score += 1;
			gameTime = GAME_CONSTANT;
		}
	}
	
	public void draw(Graphics g, float deltaTime) {
		if(gameOver == false) {
			dynaGround.drawBackground(g);
			if(fieldGoal.isActive) {
				g.drawPixmap(Assets.fieldGoal, fieldGoal.xCoordinate, fieldGoal.yCoordinate);
			}
			for(Defenders def : defenderArray) {
				if(def.isActive()) {
					g.drawPixmap(def.updateAnimation(deltaTime), def.xCoordinate, def.yCoordinate);
				}
			}
			for(H2OPower pow : h2oArray) {
				if(pow.isActive) {
					g.drawPixmap(Assets.h2opower, pow.xCoordinate, pow.yCoordinate);
				}
			}
			if(primaryfootball.isActive) {
				g.drawPixmap(Assets.football, primaryfootball.xCoordinate, primaryfootball.yCoordinate);
			}
			if(powerUp.isActive) {
				g.drawPixmap(Assets.powerUp, powerUp.xCoordinate, powerUp.yCoordinate);
			}
			if(referee.isActive) {
				g.drawPixmap(Assets.referee, referee.xCoordinate, referee.yCoordinate);
			}
			if(blimp.isActive) {
				g.drawPixmap(Assets.blimp, blimp.xCoordinate, blimp.yCoordinate);
			}
			g.drawPixmap(mariota.updateAnimation(deltaTime), mariota.xCoordinate, mariota.yCoordinate);

		}
	}
	
	private void collisionTest(GameObject object1, GameObject object2) {
		if(Math.abs(object1.xCoordinate - object2.xCoordinate) * 2 <= (object1.width + object2.width) && 
				Math.abs(object1.yCoordinate - object2.yCoordinate) * 2 <= (object1.height + object2.height)) {
			if(object1 instanceof MainCharacter) {
				if(object2 instanceof PowerUp) {
					object2.deActivate();
					mariota.powerUp();
					score += 10;	
				} else if (object2 instanceof Blimp || object2 instanceof Flag) {
					object2.deActivate();
					mariota.deActivate();
				} else if (object2 instanceof H2OPower) {
					object2.deActivate();
					score += 10;
				} else if (object2 instanceof Defenders || object2 instanceof Referee || object2 instanceof ParachuteDefender) {
					if((object1.yCoordinate + object1.height - 3) <= object2.yCoordinate) {
						object2.deActivate();
						score += 20;
					} else {
						object2.deActivate();
						object1.deActivate();
						if(object1.isActive == false) {
							gameOver = true;
						}
					}
				} 
			}
			if(object1 instanceof Football) {
				if(object2 instanceof Blimp) {
					object1.deActivate();
					object2.deActivate();
					score += 100;
				} else if (object2 instanceof Defenders || object2 instanceof ParachuteDefender || object2 instanceof Referee) {
					object1.deActivate();
					object2.deActivate();
					score += 10;
				}
			}
		}
	} //end collisionTest
	
	

}
