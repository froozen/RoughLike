package de.fro_ozen.RoughLike.GameMechanics;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import de.fro_ozen.RoughLike.BaseTypes.Entitys.BaseEntity;
import de.fro_ozen.RoughLike.BaseTypes.Entitys.Player;

public class GameFrame extends JFrame{
	private MenuHandler menhan;
	private HUD hud;
	private GamePanel gp;
	private ArrayList<BaseEntity> entitys;
	public GameFrame(Player hero, ArrayList<BaseEntity> entitys, HUD hud, MenuHandler menhan){
		setTitle("Rough Like");
		setSize(800,600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setLocation(100, 100);
		setLayout(null);

		this.entitys = entitys;
		gp = new GamePanel();
		gp.setBounds(0, 0, 800, 600);
		this.hud = hud;
		this.menhan = menhan;

		addMouseListener(new PlayerMouseHandler());
		addKeyListener(new KeyInput());
		add(gp);
		setVisible(true);
	}
	public void nextFrame(){
		gp.repaint();
	}
	private class GamePanel extends JLabel{
		protected void paintComponent(Graphics g){
			for(int i= 0; i<entitys.size(); i++){
				entitys.get(i).drawMe(g);
			}
			hud.displayHUD(g);
			menhan.displayMenu(g);
		}
	}
	private class PlayerMouseHandler implements MouseListener{
		
		public void mouseReleased(MouseEvent e) {
			MouseInput.mousePoint = e.getPoint();
			if(e.getButton() == MouseEvent.BUTTON1){
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
