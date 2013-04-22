package de.fro_ozen.RoughLike.GameMechanics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

import de.fro_ozen.RoughLike.BaseTypes.Entitys.BaseEntity;
import de.fro_ozen.RoughLike.BaseTypes.Entitys.Player;

public class GameFrame extends JFrame{
	private GamePanel gp;
	private GameLoop loop;
	public boolean gamestart;
	private BufferedImage loadingScreen, startScreen;
	
	public GameFrame(){
		setTitle("Rough Like");
		setSize(800,600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setLocation(100, 100);
		setLayout(null);

		gp = new GamePanel();
		gp.setBounds(0, 0, 800, 600);
		gamestart = false;
		try {
			startScreen = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/HUD/RoughLikeTitleScreen.png"));
			loadingScreen = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/HUD/RoughLikeLoadingScreen.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		addMouseListener(new PlayerMouseHandler());
		addKeyListener(new KeyInput());
		add(gp);
		setVisible(true);
	}
	public void nextFrame(){
		gp.repaint();
	}
	public void setLoop(GameLoop loop){
		this.loop = loop;
	}
	private class GamePanel extends JLabel{
		protected void paintComponent(Graphics g){
			if(loop != null){
				for(int i= 0; i<loop.EntityContainer.size(); i++){
					loop.EntityContainer.get(i).drawMe(g);
				}
				loop.hud.displayHUD(g);
				loop.menhan.displayMenu(g);
			}
			else{
				if(gamestart)g.drawImage(loadingScreen, 0, 0, null);
				else{
					g.drawImage(startScreen, 0, 0, null);
					g.setFont(this.getFont().deriveFont(Font.PLAIN, 20));
					g.setColor(Color.black);
					g.drawString("Version 1.0", 0, 20);
				}
			}
		}
	}
	private class PlayerMouseHandler implements MouseListener{

		public void mouseReleased(MouseEvent e) {
			MouseInput.mousePoint = e.getPoint();
			if(e.getButton() == MouseEvent.BUTTON1){
				if(!gamestart)gamestart = true;
				MouseInput.leftClicked = true;
			}
			if(e.getButton() == MouseEvent.BUTTON3){
				MouseInput.rightClicked= true;
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {	
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}
	}
}
