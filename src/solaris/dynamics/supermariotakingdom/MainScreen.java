package solaris.dynamics.supermariotakingdom;

import java.util.List;

import framework.Game;
import framework.Graphics;
import framework.Input.TouchEvent;
import framework.Screen;

public class MainScreen extends Screen {
	public MainScreen(Game game) {
		super(game);
	}
	
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		int len = touchEvents.size();
		for(int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP) {
				//System.out.println("x = " + event.x + " y = " + event.y);
				//add bounds for play
				if(inBounds(event, 250, 100, 150, 50)) {
					game.setScreen(new GameScreen(game));
					return;
				}
				//add bounds check for high score
				if(inBounds(event, 250, 150, 150, 50)) {
					game.setScreen(new HighscoreScreen(game));
					return;
				}
				//add bounds for sound toggle
				if(inBounds(event, 400, 295, 80, 25)) {
					if(Settings.soundEnabled) {
						Settings.soundEnabled = false;
						//System.out.println("Sound OFF " + Settings.soundEnabled);
						
						
					} else if(!Settings.soundEnabled) {
						Settings.soundEnabled = true;
						//System.out.println("Sound ON " + Settings.soundEnabled);
						
					}
					
				}
				
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		
		g.drawPixmap(Assets.mainScreen, 0, 0);
		g.drawPixmap(Assets.menuOptions, 250, 100);
		if (Settings.soundEnabled) {
			g.drawPixmap(Assets.soundON, 400, 295);
		} else {
			g.drawPixmap(Assets.soundOFF, 400, 295);
		}
		
		
		
	}

	@Override
	public void pause() {
		Settings.save(game.getFileIO());
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
		if(event.x > x && event.x < x + width - 1 && event.y > y && event.y < y + height - 1) {
			return true;
			
		} else {
			return false;
		}
	}
}
