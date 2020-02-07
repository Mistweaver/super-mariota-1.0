package solaris.dynamics.supermariotakingdom;

import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import framework.Game;
import framework.Graphics;
import framework.Screen;
import framework.Input.TouchEvent;

public class HighscoreScreen extends Screen {
	
	String lines[] = new String[5];
	
	public HighscoreScreen(Game game) {
		super(game);
		for (int i = 0; i < 5; i++ ) {
			lines[i] = "" + (i + 1) + ". " + Settings.highscores[i];
		}
	}
	
	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		int len = touchEvents.size();
		for(int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP) {
				if(event.x < 128 && event.y < 64) {
					game.setScreen(new MainScreen(game));
					return;
					
				}
			}
		}
	}
	
	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		
		g.drawPixmap(Assets.plainbackground, 0, 0);
		g.drawPixmap(Assets.menuOptions, 180, 40, 0, 50, 200, 50);
		g.drawPixmap(Assets.backbutton,0,0);
		int y = 100;
		for (int i = 0; i < 5; i++) {
			drawText(g, lines[i], 210, y);
			y+=30;
		}
	}

	public void drawText(Graphics g, String line, int x, int y) {
        int len = line.length();
        for (int i = 0; i < len; i++) {
            char character = line.charAt(i);
    
            if (character == ' ') {
                x += 20;
                continue;
            }
    
            int srcX = 0;
            int srcWidth = 0;
            if (character == '.') {
                srcX = 200;
                srcWidth = 10;
            } else {
                srcX = (character - '0') * 20;
                srcWidth = 20;
            }
    
            g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 32);
            x += srcWidth;
        }
    }
	
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
