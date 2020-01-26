package game.graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import game.util.Vector2D;

public class _Font {
	private BufferedImage FONT_SHEET = null;
	private BufferedImage[][] spriteArray;
	private final static int TILE_SIZE = 32;

	public int w;
	public int h;

	private int wLetter;
	private int hLetter;

	public _Font(String file) {
		this(file, TILE_SIZE, TILE_SIZE);
	}

	public _Font(String file, int w, int h) {
		this.w = w;
		this.h = h;

		System.out.println("Loading: " + file + "...");
		FONT_SHEET = loadFont(file);
		wLetter = FONT_SHEET.getWidth() / w;
		hLetter = FONT_SHEET.getHeight() / h;
		loadFontArray();
	}

	private BufferedImage loadFont(String file) {
		BufferedImage sprite = null;

		try {
			// sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
			sprite = ImageIO.read(new File(file));
		} catch (Exception e) {
			System.out.println("ERROR: could not load file: " + file);
			e.printStackTrace();
		}

		return sprite;
	}

	public void loadFontArray() {
		spriteArray = new BufferedImage[wLetter][hLetter];

		for (int x = 0; x < wLetter; x++) {
			for (int y = 0; y < hLetter; y++) {
				spriteArray[x][y] = getLetter(x, y);
			}
		}
	}

	public static void printString(Graphics2D g, _Font font, String str, int size, Vector2D pos) {
		_Sprite.drawArray(g, font, str, pos, size, size, 24, 0);
	}

	// ---------- GETTERS AND SETTERS ---------- //

	public BufferedImage getFontSheet() {
		return FONT_SHEET;
	}

	public BufferedImage getLetter(int x, int y) {
		return FONT_SHEET.getSubimage(x * w, y * h, w, h);
	}

	public BufferedImage getFont(char letter) {
		int value = letter; // letter - 65 + 32

		int x = value % wLetter;
		int y = value / wLetter;

		// return FONT_SHEET.getSubimage(x, y, w, h);
		return getLetter(x, y);
	}

	public void setSize(int width, int height) {
		setWidth(width);
		setHeight(height);
	}

	public void setWidth(int width) {
		w = width;
		wLetter = FONT_SHEET.getWidth() / w;
	}

	public void setHeight(int height) {
		h = height;
		hLetter = FONT_SHEET.getHeight() / h;
	}

	public int getWidth() {
		return w;
	}

	public int getHeight() {
		return h;
	}
}
