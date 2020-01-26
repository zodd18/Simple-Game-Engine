package game;

import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Window extends JFrame {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	public Window() {
		setTitle("Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		GamePanel gamePanel = new GamePanel(WIDTH, HEIGHT);
//		gamePanel.setCursor();
		setContentPane(gamePanel);
		setSize(WIDTH, HEIGHT);

		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}
}
