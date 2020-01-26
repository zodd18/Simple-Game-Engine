package game.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class _Animation {
	private BufferedImage[] frames;
	private int currentFrame;
	private int numFrames;

	private int count;
	private int delay;

	private int timesPlayed;

	public _Animation(BufferedImage[] frames) {
		this.frames = frames;
		currentFrame = 0;
		count = 0;
		timesPlayed = 0;
		delay = 2;
		numFrames = frames.length;
	}

	public _Animation() {
		timesPlayed = 0;
	}

	public void setFrames() {
		this.frames = frames;
		currentFrame = 0;
		count = 0;
		timesPlayed = 0;
		delay = 2;
		numFrames = frames.length;
	}

	public void update() {
		if (delay != -1) {
			count++;

			if (count == delay) {
				currentFrame++;
				count = 0;
			}

			if (currentFrame == numFrames) {
				currentFrame = 0;
				timesPlayed++;
			}
		}
	}

	public void specialUpdate(int frame) {
		if (hasPlayedOnce()) {
			if (delay != -1) {
				count++;

				if (count == delay) {
					currentFrame++;
					count = 0;
				}

				if (currentFrame == numFrames) {
					currentFrame = frame;
					timesPlayed++;
				}
			}
		} else {
			if (delay != -1) {
				count++;

				if (count == delay) {
					currentFrame++;
					count = 0;
				}

				if (currentFrame == numFrames) {
					currentFrame = frame;
					timesPlayed++;
				}
			}
		}
	}

	// BOOLEANS

	public boolean hasPlayedOnce() {
		return timesPlayed > 0;
	}

	public boolean hasPlayed(int timesPlayed) {
		return this.timesPlayed == timesPlayed;
	}

	// GETTERS AND SETTERS

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public void setCurrentFrame(int currentFrame) {
		this.currentFrame = currentFrame;
	}

	public void setNumFrames(int numFrames) {
		this.numFrames = numFrames;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public int getCurrentFrame() {
		return currentFrame;
	}

	public int getDelay() {
		return delay;
	}

	public BufferedImage getImage() {
		return frames[currentFrame];
	}

	public BufferedImage getFlippedImage(int direction) {
		BufferedImage img = frames[currentFrame];
		int width = img.getWidth();
		int height = img.getHeight();

		BufferedImage flipped = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				switch (direction) {
				default:
					flipped.setRGB(width - 1 - x, y, img.getRGB(x, y));
				}
			}
		}

		return flipped;
	}

	public BufferedImage getRotatedImage(double angle) {
		BufferedImage img = frames[currentFrame];
		double rads = Math.toRadians(angle);
		double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
		int w = img.getWidth();
		int h = img.getHeight();
		int newWidth = (int) Math.floor(w * cos + h * sin);
		int newHeight = (int) Math.floor(h * cos + w * sin);

		BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = rotated.createGraphics();
		AffineTransform at = new AffineTransform();
		at.translate((newWidth - w) / 2, (newHeight - h) / 2);

		int x = w / 2;
		int y = h / 2;

		at.rotate(rads, x, y);
		g2d.setTransform(at);
		g2d.drawImage(img, 0, 0, null);
//		g2d.setColor(Color.RED);
//		g2d.drawRect(0, 0, newWidth - 1, newHeight - 1);
		g2d.dispose();

		return rotated;
	}

}
