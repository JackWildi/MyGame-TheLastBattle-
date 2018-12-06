package firsttry;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import sun.audio.*;

public class MyCanvas extends Canvas implements KeyListener, MouseListener {
	
	Image menuimg = Toolkit.getDefaultToolkit().createImage("GameFiles/startscreen.png");
	Background StartGame = new Background (0,0,1440,900, "GameFiles/startscreen.png");
	Background gameBackground = new Background (0,0,1440,900, "GameFiles/Background.png");
	Background gameover = new Background (0,0,1440,900, "GameFiles/gameover.png");
	Image winner = Toolkit.getDefaultToolkit().createImage("GameFiles/winner.png");
	Goodguy BlackDragon = new Goodguy(10,10,180,180,"GameFiles/BlackDragon.png");
	int numberprojectiles = 0;
	LinkedList badguys = new LinkedList();
	LinkedList CandyCane = new LinkedList();
	
	
	
	int time = 0;
	
	boolean Start = true;
	boolean menu = true;
	boolean GameOver = false;
	boolean winscreen = false;
	boolean win = false;
	int kills = 0;
	
	
	public MyCanvas() {
		this.setSize(1800,1000); 
		this.addKeyListener(this); 
		playIt("GameFiles/Music.wav");
		
		Random rand = new Random();
		int winwidth = this.getWidth();
		int winheight = this.getHeight();
		for(int i = 0; i<50; i++) {
			Badguy bg = new Badguy (rand.nextInt(winwidth)+1200, rand.nextInt(winheight-250),80,80, "GameFiles/villain.png");
			Rectangle r = new Rectangle(50,50,50,50);
			if (r.contains(BlackDragon.getxCoord(), BlackDragon.getyCoord())) {
				System.out.println("kachow");
				continue;
			}
			badguys.add(bg);
			
			
	
		}
		
		TimerTask repeatedTask = new TimerTask() {

	        public void run() {

	        	for(int i = 0; i < badguys.size(); i++) {// draw bad guys

	    			Badguy bg = (Badguy) badguys.get(i);

	    			bg.setxCoord(bg.getxCoord() - 5);

	        	}

	            repaint();

	        }

	    };

	    Timer timer = new Timer("Timer");

	     

	    long delay  = 1;

	    long period = 50;

	    timer.scheduleAtFixedRate(repeatedTask, delay, period);
	}
	
	@Override
	public void paint(Graphics g) {
		
		if (kills == 50) {
			gameover.setImg("GameFiles/winner.png");
			GameOver = true;
		}
		
		if (menu) {
			g.drawImage(menuimg, 0, 0, 1410, 870, this);
		} else if (!GameOver){
		
		g.drawImage(gameBackground.getImg(), gameBackground.getxCoord(), gameBackground.getyCoord(), gameBackground.getWidth(),gameBackground.getHeight(), this );
		g.drawImage(BlackDragon.getImg(), BlackDragon.getxCoord(), BlackDragon.getyCoord(), BlackDragon.getWidth(), BlackDragon.getHeight(), this); // draw good guy
		Font font = new Font("Sans-serif", Font.PLAIN, 80);
		g.setColor(Color.red);
		g.setFont(font);
		int numberleft = 30-numberprojectiles;
		g.drawString("" + numberleft, 70, 70);
		time++;
		
		for(int i = 0; i < badguys.size(); i++) {
			Badguy bg = (Badguy) badguys.get(i);
			g.drawImage(bg.getImg(), bg.getxCoord(), bg.getyCoord(), bg.getWidth(), bg.getHeight(), this); 
			Rectangle r = new Rectangle(bg.getxCoord(), bg.getyCoord(), bg.getHeight(), bg.getHeight());
			if (bg.getxCoord()< 0) {
				GameOver = true;
			}
			
			if (time%7==0) {
				bg.setxCoord(bg.getxCoord()-1);
			}
			
			
			for(int j = 0; j < CandyCane.size();j++) {
				
				
				if(numberprojectiles < 30) {
				Projectile k = (Projectile) CandyCane.get(j);

				if (k.getxCoord() >this.getWidth()) { CandyCane.remove(k); }
				
				if (time%20==0) {

				k.setxCoord (k.getxCoord() + 1);
				
				}

				g.drawImage(k.getImg(), k.getxCoord(), k.getyCoord(), k.getWidth(), k.getHeight(), this);

			

				Rectangle kr = new Rectangle((int) k.getxCoord(),k.getyCoord(),k.getWidth(),k.getHeight());

				if (kr.intersects(r)) {

					badguys.remove(i);

					kills++;
					
					CandyCane.remove(j); }
				
				repaint(); }

			}
			
		}
			
	}
		else if(GameOver) {
			g.drawImage(gameover.getImg(), gameover.getxCoord(), gameover.getyCoord(), gameover.getWidth(), gameover.getHeight(), this);
			
		}
		}

	

	@Override
	public void keyPressed(KeyEvent e) {
		
		if (menu && e.getKeyCode() == 10) {
			menu = false;
		}
		
		if(e.getKeyCode() == 32) {

			Projectile knife = new Projectile(BlackDragon.getxCoord() + 60 , BlackDragon.getyCoord(), 150, 150,"GameFiles/CandyCane.png");
			numberprojectiles++;
			CandyCane.add(knife);

		}

		

		//playIt("files/grassy_footstep.wav");

		System.out.println(e);

		BlackDragon.moveIt(e.getKeyCode(), this.getWidth(), this.getHeight());

		Rectangle gg_r = new Rectangle(BlackDragon.getxCoord(), BlackDragon.getyCoord(),BlackDragon.getWidth(), BlackDragon.getHeight());

		


	
			
			
			
		BlackDragon.moveIt(e.getKeyCode(),this.getWidth(),this.getHeight()); 
			
		for(int i = 0; i < badguys.size(); i++) { 
			Badguy bg = (Badguy) badguys.get(i); 
			Rectangle r = new Rectangle(bg.getxCoord()-60,bg.getyCoord()+35,bg.getWidth()+110,bg.getHeight()+35);
			Rectangle dragonr = new Rectangle(BlackDragon.getxCoord(),BlackDragon.getyCoord(),BlackDragon.getHeight(),BlackDragon.getWidth());
			if (dragonr.intersects(r)) { 
				System.out.println("villain hit by BlackDragon");
				badguys.remove(i);
				kills++;
				

			}
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//System.out.println(e);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_ENTER) {
			Start = true;
		}

	
	}
	
	public void playIt(String filename) {
		
		try {
			InputStream in = new FileInputStream(filename);
			AudioStream as = new AudioStream(in);
			AudioPlayer.player.start(as);
		} catch (IOException e) {
			System.out.println(e);
		}
		
		if (badguys.isEmpty()) {
			winscreen = true;
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(e);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println(e);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}