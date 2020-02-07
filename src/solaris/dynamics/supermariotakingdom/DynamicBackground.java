package solaris.dynamics.supermariotakingdom;

import framework.Graphics;
import framework.Pixmap;

public class DynamicBackground {
	public int background_speed = 4;
	public int background_count = 0;
	
	public int background_x_coord = 0;
	
	public DynamicBackground() {
		
	}
	
	public void drawBackground(Graphics g) {
		g.clear(0);
		if(background_x_coord == 480) {
			background_x_coord = 0;
		}
		if(background_x_coord == 0) {
			//System.out.println("BACKGROUND_X_COORD = 0");
			//System.out.println(background_x_coord);
			g.drawPixmap(Assets.background, background_x_coord, 0);
			background_count += 2;
			if(background_count == background_speed) {
				background_x_coord += 1;
				background_count = 0;
			}
		} else {
			//System.out.println("ELSE STATEMENT");
			//System.out.println(background_x_coord);
			g.drawPixmap(Assets.background, 0, 0, background_x_coord, 0, 480-background_x_coord, 320);
			g.drawPixmap(Assets.background, 479 - background_x_coord, 0, 0,0, background_x_coord, 320);
			background_count += 2;
			if(background_count == background_speed) {
				background_x_coord += 1;
				background_count = 0;
			}
		}
	}
	
	
}
