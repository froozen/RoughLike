package de.fro_ozen.RoughLike.GameMechanics;

public class Main {
	static GameLoop loop;
	public static void main(String[] args) throws InterruptedException {
		GameFrame gf = new GameFrame();
		while(true){
			gf.nextFrame();
			if(gf.gamestart){
				if(loop != null){
					loop.computeFrame();
					if(loop.end){
						gf.setLoop(null);
						loop = null;
						gf.gamestart = false;
					}
				}
				else{
					loop = new GameLoop();
					loop.initialize();
					gf.setLoop(loop);
				}
			}
			Thread.sleep(15);
		}
	}
}
