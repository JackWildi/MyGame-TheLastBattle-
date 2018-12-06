package firsttry;

import java.awt.Image;

import java.awt.Toolkit;



public class Projectile {



	private int xCoord;

	private int yCoord;

	private int width;

	private int height;

	private Image img;

	public Image getImg;

	

	public Projectile() {

		setxCoord(10);

		setyCoord(10);

		setWidth(30);

		setHeight(30);

		setImg("GameFiles/CandyCane.png");

	}

	public Projectile(int x, int y,int w, int h, String imgpath) {

		//int x = 0;

		setxCoord(x);

		setyCoord(y);

		setWidth(w);

		setHeight(h);

		setImg(imgpath);

		

	}
	
	public void fireballmoveIt(int direction) {
		
		System.out.println(direction);
		
		
		//int speed = 20;
		//int x = getxCoord();
		//int y = getyCoord();
		
		
			//x = x + speed;
			//setxCoord(x); 
			//setImg ("GameFiles/Fireball.png");
	} 


	

	public void setImg(Image img) {

		this.img =img;

	}

	

	public void setImg(String imgpath) {

		this.img = Toolkit.getDefaultToolkit().getImage(imgpath);

	}



	public int getxCoord() {

		return this.xCoord;

	}



	public void setxCoord(int xCoord) {

		this.xCoord = xCoord;

	}



	public int getyCoord() {

		return this.yCoord;

	}



	public void setyCoord(int yCoord) {

		this.yCoord = yCoord;

	}



	public int getWidth() {

		return this.width;

	}



	public void setWidth(int width) {

		this.width = width;

	}



	public int getHeight() {

		return this.height;

	}



	public void setHeight(int height) {

		this.height = height;

	}



	public Image getImg() {

		return img;

	}

		

	

}