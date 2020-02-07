package solaris.dynamics.supermariotakingdom;

import framework.Game;
import framework.Graphics;
import framework.Screen;
import framework.Graphics.PixmapFormat; 

public class LoadingScreen extends Screen {
	
	public LoadingScreen(Game game) {
		super(game);
	}
	
	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		//Graphics assets loading
		Assets.background = g.newPixmap("Background.png", PixmapFormat.RGB565);
		Assets.mainScreen = g.newPixmap("mainScreen2.png", PixmapFormat.ARGB4444);
		Assets.menuOptions = g.newPixmap("menuOptions.png", PixmapFormat.ARGB4444);
		Assets.plainbackground = g.newPixmap("plainbackground.png", PixmapFormat.ARGB4444);
		Assets.soundOFF = g.newPixmap("soundOFF.png", PixmapFormat.ARGB4444);
		Assets.soundON = g.newPixmap("soundON.png", PixmapFormat.ARGB4444);
		Assets.numbers = g.newPixmap("numbers.png", PixmapFormat.ARGB4444);
		Assets.gameOver = g.newPixmap("GAME_OVER.png", PixmapFormat.ARGB4444);
		Assets.gameScore = g.newPixmap("gameScore.png", PixmapFormat.ARGB4444);
		Assets.gameScreenScore = g.newPixmap("gameScreenScore.png", PixmapFormat.ARGB4444);
		Assets.gameScreenNumbers = g.newPixmap("gameScreenNumbers.png", PixmapFormat.ARGB4444);
		
		//buttons
		Assets.backbutton = g.newPixmap("backbutton.png", PixmapFormat.ARGB4444);
		Assets.jumpbutton = g.newPixmap("jumpbutton.png", PixmapFormat.ARGB4444);
		Assets.throwbutton = g.newPixmap("throwbutton.png", PixmapFormat.ARGB4444);
		Assets.pausebutton = g.newPixmap("pausebutton.png", PixmapFormat.ARGB4444);
		Assets.readybutton = g.newPixmap("readybutton.png", PixmapFormat.ARGB4444);
		Assets.resumebutton = g.newPixmap("resumebutton.png", PixmapFormat.ARGB4444);
		Assets.pausedbutton = g.newPixmap("pausedbutton.png", PixmapFormat.ARGB4444);
		
		//Character loads
		Assets.SuperMariota = g.newPixmap("SuperMariota.png", PixmapFormat.ARGB4444);
		Assets.SuperMariota2 = g.newPixmap("SuperMariota2.png", PixmapFormat.ARGB4444);
		Assets.SuperMariota2Empty = g.newPixmap("SuperMariota2Empty.png", PixmapFormat.ARGB4444);
		Assets.SuperMariota2Jump1 = g.newPixmap("SuperMariota2Jump1.png", PixmapFormat.ARGB4444);
		Assets.SuperMariota2JumpEmpty = g.newPixmap("SuperMariota2JumpEmpty.png", PixmapFormat.ARGB4444);
		Assets.SuperMariota2Run1 = g.newPixmap("SuperMariota2Run1.png", PixmapFormat.ARGB4444);
		Assets.SuperMariota2RunEmpty = g.newPixmap("SuperMariota2RunEmpty.png", PixmapFormat.ARGB4444);
		Assets.SuperMariota2Throw1 = g.newPixmap("SuperMariota2Throw1.png", PixmapFormat.ARGB4444);
		Assets.SuperMariota2Throw2 = g.newPixmap("SuperMariota2Throw2.png", PixmapFormat.ARGB4444);
		Assets.SuperMariotaRun1 = g.newPixmap("SuperMariotaRun1.png", PixmapFormat.ARGB4444);
		Assets.SuperMariotaRun2 = g.newPixmap("SuperMariotaRun2.png", PixmapFormat.ARGB4444);
		Assets.SuperMariota2JumpThrow1 = g.newPixmap("SuperMariota2JumpThrow1.png", PixmapFormat.ARGB4444);
		Assets.SuperMariota2JumpThrow2 = g.newPixmap("SuperMariota2JumpThrow2.png", PixmapFormat.ARGB4444);
		
		//object asset loading
		Assets.football = g.newPixmap("football.png", PixmapFormat.ARGB4444);
		Assets.defenderStill = g.newPixmap("DefenderStill.png", PixmapFormat.ARGB4444);
		Assets.defenderRun = g.newPixmap("DefenderRun.png", PixmapFormat.ARGB4444);
		Assets.defenderJump = g.newPixmap("DefenderJump.png", PixmapFormat.ARGB4444);
		Assets.powerUp = g.newPixmap("PowerUp.png", PixmapFormat.ARGB4444);
		Assets.fieldGoal = g.newPixmap("FieldGoal.png", PixmapFormat.ARGB4444);
		Assets.blimp = g.newPixmap("BadWeekBlimp.png", PixmapFormat.ARGB4444);
		Assets.parachuter = g.newPixmap("Parachuter2.png", PixmapFormat.ARGB4444);
		
		Assets.flag = g.newPixmap("flag.png", PixmapFormat.ARGB4444);
		Assets.referee = g.newPixmap("Referee.png", PixmapFormat.ARGB4444);
		Assets.refereethrow = g.newPixmap("RefereeThrow.png", PixmapFormat.ARGB4444);
		Assets.refereethrow2 = g.newPixmap("RefereeThrow2.png", PixmapFormat.ARGB4444);
		Assets.h2opower = g.newPixmap("H2OPower.png", PixmapFormat.ARGB4444);
		
		//Sound assets loading
		//Music assets
		Assets.testsong = game.getAudio().newMusic("tetris1.mid");
		Assets.testsong.setLooping(true);
		Assets.testsong.setVolume(0.5f);
		Settings.load(game.getFileIO());
		game.setScreen(new MainScreen(game));
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		
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
