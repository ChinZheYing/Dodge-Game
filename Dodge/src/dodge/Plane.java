package dodge;

import java.awt.*;
import java.awt.event.KeyEvent;

import dodge.Game.Action;

public class Plane extends Action {
	int x;
	int y;
	int imageIndex = 0;
	boolean left, right, up, down;
	boolean b = false;
	long p;

	private static Image[] imgs = {
			Toolkit.getDefaultToolkit().getImage(Plane.class.getClassLoader().getResource("image/p1.png")),
			Toolkit.getDefaultToolkit().getImage(Plane.class.getClassLoader().getResource("image/p2.png")),
			Toolkit.getDefaultToolkit().getImage(Plane.class.getClassLoader().getResource("image/p3.png")),
			Toolkit.getDefaultToolkit().getImage(Plane.class.getClassLoader().getResource("image/bang.png")) };

	public Plane() {
		x = 120;
		y = 180;

	}

	public Rectangle getRect() {
		return new Rectangle(x - 5, y - 5, 10, 10);
	}

	public void move(Graphics g, boolean a, Game game) {
		if (a == true) {
			b = true;
		}
		if (x <= 10 && b == false) {
			x = 10;
		}
		if (x >= 220 && b == false) {
			x = 220;
		}
		if (y <= 30 && b == false) {
			y = 30;
		}
		if (y >= 340 && b == false) {
			y = 340;
		}

		if (left && up && b == false) {
			x = x - 1;
			y = y - 1;
		}
		if (right && up && b == false) {
			x = x + 1;
			y = y - 1;
		}
		if (right && down && b == false) {
			x = x + 1;
			y = y + 1;
		}
		if (left && down && b == false) {
			x = x - 1;
			y = y + 1;
		}
		if (left && down == false && up == false && b == false) {
			x = x - 2;
			imageIndex = 1;
		}
		if (right && down == false && up == false && b == false) {
			x = x + 2;
			imageIndex = 2;
		}
		if (up && right == false && left == false && b == false) {
			y = y - 2;
			imageIndex = 0;
		}
		if (down && right == false && left == false && b == false) {
			y = y + 2;
			imageIndex = 0;
		}
		if (b == false) {
			g.drawImage(imgs[imageIndex], x, y, null);
		} else if (b == true) {
			imageIndex = 3;
			g.drawImage(imgs[imageIndex], x, y, null);
			game.repaint();

		}
	}

	public boolean dead() {
		if (imageIndex == 3) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void keyPressed(KeyEvent event) {
		// System.out.println(event.getKeyCode());
		if (event.getKeyCode() == 37) {
			left = true;
		}
		if (event.getKeyCode() == 38) {
			up = true;
		}
		if (event.getKeyCode() == 39) {
			right = true;
		}
		if (event.getKeyCode() == 40) {
			down = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
		//System.out.println(event.getKeyCode());
		if (event.getKeyCode() == 37) {
			left = false;
			imageIndex = 0;
		}
		if (event.getKeyCode() == 38) {
			up = false;
			imageIndex = 0;
		}
		if (event.getKeyCode() == 39) {
			right = false;
			imageIndex = 0;
		}
		if (event.getKeyCode() == 40) {
			down = false;
			imageIndex = 0;
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

}
