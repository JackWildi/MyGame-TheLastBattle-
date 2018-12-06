package firsttry;

import java.awt.Image;

import java.awt.Toolkit;

public class Goodguy {
	
	
		private int xCoord = 0;

		private int yCoord = 0;

		private int Width = 100;

		private int Height = 100;

		private Image img;

		

		public Goodguy() {

			setxCoord(10);

			setyCoord(10);

			setWidth(30);

			setHeight(30);

			setImg("GameFiles/BlackDragon.png");

		}

		public Goodguy(int x, int y, int w, int h, String imgpath) {

			setxCoord(x);

			setyCoord(y);

			setWidth(w);

			setHeight(h);

			setImg(imgpath); 
			
			} 
		
			public void moveIt(int direction, int w, int h) {
				
				System.out.println(direction);
				
				
				int speed = 20;
				int x = getxCoord();
				int y = getyCoord();
				
				if (direction == 39) {
					x = x + speed;
					if (x > w) { x = x - speed * 3; }
					setxCoord(x); 
					setImg ("GameFiles/Right.png");


		    } else if (direction == 37) {
					x = x - speed;
					if (x < 0) { x = x - speed * 3; }
					setxCoord(x);
					setImg("GameFiles/Left.png");

			} else if (direction == 38) {
					y = y - speed;
					if (y < 0) { x = y - speed * 3; }
					setyCoord(y);
					setImg("GameFiles/Up.png");

			} else if (direction == 40) {
							y = y + speed;
							if (y > h) { x = y - speed * 3; }
							setyCoord(y);
							setImg("GameFiles/Up.png");


				}
			} 


		public int getxCoord() {

			return xCoord;

		}

		public void setxCoord(int xCoord) {
			
				

			this.xCoord = xCoord;

		}

		public int getyCoord() {
			
		
			return yCoord;

		}

		public void setyCoord(int yCoord) {
			
			
		
			this.yCoord = yCoord;

		}

		public int getWidth() {

			return Width;

		}

		public void setWidth(int width) {

			Width = width;

		}

		public int getHeight() {

			return Height;

		}

		public void setHeight(int height) {

			Height = height;

		}

		public Image getImg() {

			return img;

		}

		public void setImg(String imgpath) {

			this.img = Toolkit.getDefaultToolkit().getImage(imgpath);

			 

		}
}

