package dodge;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Bullet {
	int x;
	int y;
	double a;
	int speedX;
	int speedY;
	long point;

	private static Image imgs = Toolkit.getDefaultToolkit()
			.getImage(Plane.class.getClassLoader().getResource("image/bt.png"));

	public Bullet() {
		this.x = (int) (Math.random() * 240);
		this.y = 0;
		double random = Math.random();
		
		if(random < 0.2) {
			speedX = -1;
		}
		else if(random < 0.4) {
			speedX = -2;
		}
		else if(random < 0.5) {
			speedX = -3;
		}
		else if(random < 0.7) {
			speedX = 1;
		}
		else if(random < 0.9) {
			speedX = 2;
		}
		else if(random < 1) {
			speedX = 3;
		}
		
		random = Math.random();
		if (random < 0.33d) {
			speedY = 1;
		} else if (random < 0.66d) {
			speedY = 2;
		} else {
			speedY = 3;
		}

	}

	public void move(Graphics g, Game game) {
		if (x < -10 || x > 250 || y > 400) {
			game.bulletList.remove(this);
		}
		//if (game.p.getRect().intersects(this.getRect()) == false) {
			this.x = this.x + speedX;
			this.y = this.y + speedY;
		//}
		g.drawImage(imgs, this.x, this.y, null);
		if (game.w == false) {
			g.setColor(Color.WHITE);
			point = (System.currentTimeMillis() - game.time)/100;
			System.out.println("Point: " + point);
		}

	}
	
	public long mark() {
		return point;
	}

	public boolean gameOver(Game game) {
		if (game.p.getRect().intersects(this.getRect())) {
			return true;
		} else {
			return false;
		}
	}

	public Rectangle getRect() {
		return new Rectangle(x - 2, y - 2, 4, 4);
	}

}
