package de.fro_ozen.RoughLike.GameMechanics;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		GameLoop loop = new GameLoop();
		loop.initialize();
		GameFrame gf = new GameFrame(loop.player, loop.EntityContainer, loop.hud, loop.menhan);
		while(true){
			loop.computeFrame();
			gf.nextFrame();
			Thread.sleep(15);
		}
	}
}
