package game.scenes;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

import game.GamePanel;
import game.entities.Entity;
import game.entities.creatures.Enemy;
import game.entities.creatures.Player;
import game.entities.creatures.stats.Stat;
import game.entities.creatures.stats.StatBar;
import game.entities.creatures.stats.Stats;
import game.graphics.UI;
import game.util.LogSystem;
import game.util.RandomUtil;
import game.util.input.KeyHandler;
import game.util.input.MouseHandler;
import game.util.Vector2D;
import game.util.audio.AudioLine;

public class Scene0 extends Scene {

	private Player player;

	private int spawnTimer;
	private final int spawnCooldown = 50;

	private int killCounter;
	private boolean songChanged;

	private BufferedImage backgroundImage;

	private int backgroundTransitionCooldown = 30;
	private int backgroundTransitionTimer;

	AudioLine song;

	public Scene0(SceneManager gsm) {
		super("SCENE_0", gsm);

		backgroundImage = initBackground();

		Stats playerStats = new Stats (
				new Stat("HEALTH", 100),
				new Stat("MOVE_SPEED", 3),
				new Stat("ATTACK_SPEED", 15),
				new Stat("ATTACK_POWER", 300),
				new Stat("INVINCIBLE_TIME", 100)
		);
		player = new Player(new Vector2D(50, 520), new Vector2D(64, 64), playerStats);

		player.getHitBox().setOffset(20, 0, -40, 0);
		player.setSprite("resources/sprites/spaceship.png");
		// PROBLEM HERE
		player.addStatBar("HEALTH", 200, 20);
		player.getStatBar("HEALTH").setMode(StatBar.FIXED_POSITION, new Vector2D(20, 20));
		player.getStatBar("HEALTH").setBack(Color.RED);
		for (StatBar sb : player.getStatBars()) uis.add(sb);
		//
		entities.add(player);

		spawnTimer = 0;
		killCounter = 0;
		backgroundTransitionTimer = 0;

		songChanged = false;
		song = new AudioLine();
		song.playMusic("resources/music/ducktales_moon.wav");
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(backgroundImage, 0, 0, GamePanel.width, GamePanel.height, null);

		if(backgroundTransitionTimer == 0) {
			renderBackground();
			backgroundTransitionTimer = backgroundTransitionCooldown;
		}

		for (Entity e : entities) e.render(g);

		// UI
		g.setColor(new Color(255, 0, 151));
		g.drawString("FPS: " + Integer.toString(GamePanel.oldFrameCount), 730, 40);
		g.setColor(new Color(255, 0, 151));
		g.drawString("Kills: " + killCounter, 730, 20);

		for (UI ui : uis) {
			// STAT BARS
			if (ui instanceof StatBar) {
				StatBar sb = (StatBar) ui;
				Stat stat = sb.getCreature().getStats().get(sb.statName);
				if (stat != null && stat.showBar) { sb.render(g); }
			}
		}
	}

	private BufferedImage initBackground() {
		BufferedImage img = new BufferedImage(100, 75, BufferedImage.TYPE_INT_ARGB);

		Color backgroundColor = new Color(18, 17, 37);
		Color starColor = new Color(192, 201, 255);

		for (int i = 0; i < img.getHeight(); i++) {
			for (int j = 0; j < img.getWidth(); j++) {
				int r = RandomUtil.getRandom(0, 100);
				if (r == 0) img.setRGB(j, i, starColor.getRGB());
				else img.setRGB(j, i, backgroundColor.getRGB());
			}
		}

		return img;
	}

	private void renderBackground() {
		Color backgroundColor = new Color(18, 17, 37);
		Color starColor = new Color(192, 201, 255);

//		for (int i = 0; i < backgroundImage.getHeight() - 1; i++) {
//			for (int j = 0; j < backgroundImage.getWidth(); j++) {
//				int rgb = backgroundImage.getRGB(j, i);
//				backgroundImage.setRGB(j, i + 1, rgb);
//			}
//		}

		for (int k = 0; k < backgroundImage.getWidth(); k++) {
			int r = RandomUtil.getRandom(0, 100);
			if (r == 0) backgroundImage.setRGB(k, 0, starColor.getRGB());
			else backgroundImage.setRGB(k, 0, backgroundColor.getRGB());
		}
	}

	@Override
	public void update() {
		if (spawnTimer < spawnCooldown) spawnTimer++;
		if (backgroundTransitionTimer > 0) backgroundTransitionTimer--;

		LinkedList<Entity> toDelete = new LinkedList<>();

		for (Entity e : entities) {
			e.update();
			if(e.destroy) toDelete.add(e);
		}
		for (Entity e : toDelete) {
			if(e instanceof Enemy) killCounter++;
			entities.remove(e);
		}

		if(spawnTimer >= spawnCooldown) {
			Random rand = new Random();
			int size = RandomUtil.getRandom(40, 100);
			LogSystem.log(Integer.toString(size * 4));
			Stats enemyStats =
							new Stats(new Stat("HEALTH", size * 4)
							, new Stat("MOVE_SPEED", 3)
							, new Stat("ATTACK_SPEED", 25)
							, new Stat("ATTACK_POWER", (int) ((double) size / 3)));
			int x = RandomUtil.getRandom(size, GamePanel.width - size);
			Enemy enemy = new Enemy(new Vector2D(x, -30), new Vector2D(size, size), enemyStats);
			enemy.setSprite("resources/sprites/alien.png");
			enemy.getHitBox().setOffset(15, 15, -30, -30);
			entities.add(enemy);
			spawnTimer = 0;
		}

		// Change song (lags a little bit!)
//		if (killCounter >= 10 && !songChanged) {
//			songChanged = true;
//			song.stop();
//			song.playMusic("resources/music/death_by_glamour.wav");
//		}

		if (player.getStats().get("HEALTH").current <= 0) {
			song.stop();
			song = null;
			SceneManager.currentScene = new GameOver(gsm, killCounter);
		}
	}

	@Override
	public void input(MouseHandler mouse, KeyHandler key) {
		player.input(mouse, key);
	}
}