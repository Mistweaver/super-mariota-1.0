package solaris.dynamics.supermariotakingdom;

import framework.Screen;
import framework.impl.AndroidGame;

public class SuperMariotaMainActivity extends AndroidGame {
	@Override
	public Screen getStartScreen() {
		return new LoadingScreen(this);
	}
}