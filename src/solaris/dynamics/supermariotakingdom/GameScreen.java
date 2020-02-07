package solaris.dynamics.supermariotakingdom;

import java.util.List;

import android.graphics.Color;
import framework.Game;
import framework.Graphics;
import framework.Graphics.PixmapFormat;
import framework.Input.TouchEvent;
import framework.Pixmap;
import framework.Screen;

public class GameScreen extends Screen {
    enum GameState {
        Ready,
        Running,
        Paused,
        GameOver
    }
    
    GameState state = GameState.Ready;
    Stadium stadium;
    int oldScore = 0;
    String score = "0";
    
    public GameScreen(Game game) {
        super(game);
        stadium = new Stadium();
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        
        if(state == GameState.Ready)
            updateReady(touchEvents);
        if(state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if(state == GameState.Paused)
            updatePaused(touchEvents);
        if(state == GameState.GameOver)
            updateGameOver(touchEvents);        
    }
    
    private void updateReady(List<TouchEvent> touchEvents) {
    	if(touchEvents.size() > 0)
            state = GameState.Running;
    }
    
    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {        
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x > 220 && event.x < 270 && event.y > 256) {
                    state = GameState.Paused;
                    return;
                }
            }
            if(event.type == TouchEvent.TOUCH_DOWN) {
                if(event.x < 128 && event.y > 256) {
                   stadium.mariota.jump();
                }
                if(event.x > 342  && event.y > 256) {
                	stadium.mariota.throwFootball();
                	//System.out.println("THROWING FOOTBALL");
                }
            }
        }
        Graphics g = game.getGraphics();
        stadium.update(g, deltaTime);
        if(stadium.gameOver) {
            state = GameState.GameOver;
        }
        if(oldScore != stadium.score) {
            oldScore = stadium.score;
            score = "" + oldScore;
        }
    }
    
    private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x > 200 && event.x <= 328) {
                    if(event.y > 100 && event.y <= 164) {
                        state = GameState.Running;
                        return;
                    }                    
                    if(event.y > 164 && event.y < 228) {
                        game.setScreen(new MainScreen(game));                        
                        return;
                    }
                }
            }
        }
    }
    
    private void updateGameOver(List<TouchEvent> touchEvents) {
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
        stadium.draw(g, deltaTime);
        if(state == GameState.Ready) 
            drawReadyUI();
        if(state == GameState.Running)
        	drawRunningUI();
        if(state == GameState.Paused)
            drawPausedUI();
        if(state == GameState.GameOver)
            drawGameOverUI();         
    }
    
    
    private void drawReadyUI() {
        Graphics g = game.getGraphics();
        g.clear(0);
        g.drawPixmap(Assets.plainbackground, 0, 0);
        g.drawPixmap(Assets.readybutton, 200, 150);
    }
    
    private void drawRunningUI() {
    	Graphics g = game.getGraphics();
		g.drawPixmap(Assets.jumpbutton, 0, 256);
		if(stadium.mariota.isSupersized) {
			g.drawPixmap(Assets.throwbutton, 342, 256);
		}
		g.drawPixmap(Assets.pausebutton, 220, 256);
		g.drawPixmap(Assets.gameScreenScore, 0, 0);
	    String string_score = score;
	    drawGameText(g, string_score, 100, 0);
    }
    
    private void drawPausedUI() {
    	Graphics g = game.getGraphics();
    	g.clear(0);
		g.drawPixmap(Assets.resumebutton, 190, 100);
		g.drawPixmap(Assets.backbutton, 190, 164);
    }

    private void drawGameOverUI() {
    	Graphics g = game.getGraphics();
        g.clear(0);
        g.drawPixmap(Assets.plainbackground, 0, 0);
        g.drawPixmap(Assets.backbutton,0,0);
        g.drawPixmap(Assets.gameOver, 125, 50);
        g.drawPixmap(Assets.gameScore, 155, 195);
        String string_score = score;
        drawText(g, string_score, 280, 209);
    }
    
    @Override
    public void pause() {
        if(state == GameState.Running) {
        	state = GameState.Paused;
        }
        if(stadium.gameOver) {
            Settings.addScore(stadium.score);
            Settings.save(game.getFileIO());
        }
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void dispose() {
        
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
        
    public void drawGameText(Graphics g, String line, int x, int y) {
    	int len = line.length();
        for (int i = 0; i < len; i++) {
            char character = line.charAt(i);
        
            if (character == ' ') {
                x += 13;
                continue;
            }
        
            int srcX = 0;
            int srcWidth = 0;
                
            srcX = (character - '0') * 13;
            srcWidth = 13;
                
        
            g.drawPixmap(Assets.gameScreenNumbers, x, y, srcX, 0, srcWidth, 32);
            x += srcWidth;
        }
    }
}