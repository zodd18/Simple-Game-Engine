package game.graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import game.util.LogSystem;
import game.util.Vector2D;

public class _Sprite {
	private BufferedImage SPRITE_SHEET = null;
	private BufferedImage[][] spriteArray;
	private final static int TILE_SIZE = 32;

	public int w;
	public int h;

	private int wSprite;
	private int hSprite;

	public _Sprite(String file) {
		this(file, TILE_SIZE, TILE_SIZE);
	}

	public _Sprite(String file, int w, int h) {
		this.w = w;
		this.h = h;

		System.out.println("Loading: " + file + "...");
		SPRITE_SHEET = loadSprite(file);

		wSprite = SPRITE_SHEET.getWidth() / w;
		hSprite = SPRITE_SHEET.getHeight() / h;
		loadSpriteArray();
	}

	public void setSize(int width, int height) {
		setWidth(width);
		setHeight(height);
	}

	public void setWidth(int width) {
		w = width;
		wSprite = SPRITE_SHEET.getWidth() / w;
	}

	public void setHeight(int height) {
		h = height;
		hSprite = SPRITE_SHEET.getHeight() / h;
	}

	public int getWidth() {
		return w;
	}

	public int getHeight() {
		return h;
	}

	private BufferedImage loadSprite(String file) {
		BufferedImage sprite = null;

		try {
			sprite = ImageIO.read(getClass().getClassLoader().getResource(file));
//			sprite = ImageIO.read(new File(file));
		} catch (Exception e) {
			LogSystem.errorLog("Could not load: " + file);
			e.printStackTrace();
		}

		return sprite;
	}

	public void loadSpriteArray() {
		spriteArray = new BufferedImage[hSprite][wSprite];

		for (int y = 0; y < hSprite; y++) {
			for (int x = 0; x < wSprite; x++) {
				spriteArray[y][x] = getSprite(x, y);
			}
		}
	}

	public BufferedImage getSpriteSheet() {
		return SPRITE_SHEET;
	}

	public BufferedImage getSprite(int x, int y) {
		return SPRITE_SHEET.getSubimage(x * w, y * h, w, h);
	}

	public BufferedImage[] getSpriteArray(int i) {
		return spriteArray[i];
	}

	public BufferedImage[][] getSpriteArray2(int i) {
		return spriteArray;
	}

	public static void drawArray
			(Graphics2D g, ArrayList<BufferedImage> img, Vector2D pos, int width, int height, int xOffset, int yOffset) {

		float x = pos.x;
		float y = pos.y;

		for (int i = 0; i < img.size(); i++) {
			if (img.get(i) != null) {
				g.drawImage(img.get(i), (int) x, (int) y, width, height, null);
			}

			x += xOffset;
			y += yOffset;
		}
	}

	public static void drawArray
			(Graphics2D g, _Font f, String word, Vector2D pos, int width, int height, int xOffset, int yOffset) {
		float x = pos.x;
		float y = pos.y;

		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) != 32) {
				g.drawImage(f.getFont(word.charAt(i)), (int) x, (int) y, width, height, null);
			}

			x += xOffset;
			y += yOffset;
		}

	}

	public int getSpriteSheetWidth() {
		return SPRITE_SHEET.getWidth();
	}

	public int getSpriteSheetHeight() {
		return SPRITE_SHEET.getHeight();
	}
}
