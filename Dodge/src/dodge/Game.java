package dodge;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JFrame;

public class Game extends JFrame implements KeyListener {

	Plane p;
	Image offScreenImage = null;
	List<Bullet> bulletList;
	boolean w = false;
	long time;
	int bulletGenerated;
	long mark;

	public Game() throws HeadlessException {
		p = new Plane();
		bulletList = new CopyOnWriteArrayList<Bullet>();
		this.setSize(240, 360);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("The Game");
		Container c = this.getContentPane();
		c.setBackground(Color.BLACK);
		c.setFocusable(true);
		c.requestFocus();
		c.addKeyListener(this);
		new PaintThread().start();
		this.setVisible(true);
		time = System.currentTimeMillis();

	}

	private class PaintThread extends Thread {

		@Override
		public void run() {

			while (w == false) {
				repaint();
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}
	

	public static void main(String[] args) {
		Game g = new Game();

	}

	@Override
	public void paint(Graphics g) {

		if (offScreenImage == null) {
			offScreenImage = this.createImage(240, 360);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, 240, 360);
		super.paint(gOffScreen);
		p.move(gOffScreen, false, this);
		if (w == false) {
			if (bulletList.size() < 2000) {
				Bullet bullet = new Bullet();
				bulletList.add(bullet);
				bulletGenerated++;
			}
		}
		for (Bullet bullet : bulletList) {
			// if(w == false) {
			bullet.move(gOffScreen, this);
			// }
			if (bullet.gameOver(this)) {
				p.move(gOffScreen, true, this);
				w = true;
				mark = bullet.mark();
			}
		}

		g.drawImage(offScreenImage, 0, 0, null);

	}

	public abstract static class Action implements KeyListener {

	}

	@Override
	public void keyPressed(KeyEvent event) {
		p.keyPressed(event);
	}

	@Override
	public void keyReleased(KeyEvent event) {
		p.keyReleased(event);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
