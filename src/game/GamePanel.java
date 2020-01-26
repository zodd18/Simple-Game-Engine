package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import game.scenes.SceneManager;
import game.util.Colors;
import game.util.RandomUtil;
import game.util.input.KeyHandler;
import game.util.input.MouseHandler;

public class GamePanel extends JPanel implements Runnable {

	public static int width;
	public static int height;

	public static int oldFrameCount;

	private Thread thread;
	private BufferedImage img;
	private Graphics2D g;

	private boolean running = false;

	private SceneManager sceneManager;

	private MouseHandler mouse;
	private KeyHandler key;

	private Color backgroundColor;

	public GamePanel(int width, int height) {
		this.width = width;
		this.height = height;
		this.backgroundColor = new Color(15, 15, 15);

		setPreferredSize(new Dimension(width, height));
		setFocusable(true);
		requestFocus();
	}

	@Override
	public void addNotify() {
		super.addNotify();

		if (thread == null) {
			thread = new Thread(this, "GameThread");
			thread.start();
		}
	}

	public void init() {
		running = true;
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		g = (Graphics2D) img.getGraphics();

		mouse = new MouseHandler(this);
		key = new KeyHandler(this);
		sceneManager = new SceneManager();
	}

	public void setCursor() {
		try {
			setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ImageIO.read(new File("/cursors/cursor0.png")), new Point(0, 0), "cursor0"));
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		sceneManager.update();
	}

	public void input(MouseHandler mouse, KeyHandler key) {
		sceneManager.input(mouse, key);
	}

	public void render() {
		if (g != null) {
//			g.setColor(new Color(25, 25, 25));
			g.setColor(backgroundColor);
			g.fillRect(0, 0, width, height);
			sceneManager.render(g);
		}
	}

	public void draw() {
		Graphics g2 = (Graphics2D) this.getGraphics();
		g2.drawImage(img, 0, 0, width, height, null);
		g2.dispose();
	}

	@Override
	public void run() {
		init();

		final double GAME_HERTZ = 60.0;
		final double TBU = 1000000000 / GAME_HERTZ; // TIME BEFORE UPDATE
		final int MUBR = 5; // MUST UPDATE BEFORE RENDER

		double lastUpdateTime = System.nanoTime();
		double lastRenderTime;

		final double TARGET_FPS = 60.0;
		final double TTBR = 1000000000 / TARGET_FPS; // TOTAL TIME BEFORE RENDER

		int frameCount = 0;
		int lastSecondTime = (int) (lastUpdateTime / 1000000000);
		oldFrameCount = 0;

		// GAME LOOP

		while (running) {
			double now = System.nanoTime();
			int updateCount = 0;
			while (((now - lastUpdateTime) > TBU) && (updateCount < MUBR)) {
				update();
				input(mouse, key);
				lastUpdateTime += TBU;
				updateCount++;
			}

			if (now - lastUpdateTime > TBU) {
				lastUpdateTime = now - TBU;
			}

			input(mouse, key);
			update();
			render();
			draw();
			lastRenderTime = now;
			frameCount++;
			int thisSecond = (int) (lastUpdateTime / 1000000000);
			if (thisSecond > lastSecondTime) {
				if (frameCount != oldFrameCount) {
					// System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
					oldFrameCount = frameCount;
				}

				frameCount = 0;
				lastSecondTime = thisSecond;
			}

			while (now - lastRenderTime < TTBR && now - lastUpdateTime < TBU) {
				Thread.yield();

				try {
					Thread.sleep(1);
				} catch (Exception e) {
					System.out.println("ERROR: Yielding thread");
					e.printStackTrace();
				}
				now = System.nanoTime();
			}
		}
	}

}
