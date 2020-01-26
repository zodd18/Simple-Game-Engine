package game.scenes;

import java.awt.*;
import java.util.stream.IntStream;

import game.GamePanel;
import game.util.GraphicsUtil;
import game.util.input.KeyHandler;
import game.util.input.MouseHandler;

import static game.util.GraphicsUtil.drawCenteredString;

public class GameOver extends Scene {

	private int kills;

	public GameOver(SceneManager gsm, int kills) {
		super("GAME_OVER", gsm);
		this.kills = kills;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void input(MouseHandler mouse, KeyHandler key) {
		if (key.enter.down) {
			SceneManager.currentScene = new Scene0(gsm);
		}

	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.width, GamePanel.height);
		g.setColor(new Color(255, 0, 151));

		GraphicsUtil.drawCenteredString(g, "GAME OVER", new Rectangle(0, 0, GamePanel.width, GamePanel.height - 200), new Font("Arial", Font.PLAIN, 100));
		GraphicsUtil.drawCenteredString(g, "Kills: " + Integer.toString(kills), new Rectangle(0, 0, GamePanel.width, (int) (GamePanel.height * 1.5 - 200)), new Font("Arial", Font.PLAIN, 50));
		GraphicsUtil.drawCenteredString(g, "Press ENTER to try again!", new Rectangle(0, 0, GamePanel.width, (int) (GamePanel.height * 2 - 200)), new Font("Arial", Font.PLAIN, 20));
	}
}
