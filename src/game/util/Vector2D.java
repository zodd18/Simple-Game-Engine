package game.util;

public class Vector2D {
	public int x, y;

	public Vector2D() {
		x = 0;
		y = 0;
	}

	public Vector2D(Vector2D pos) {
		new Vector2D(pos.x, pos.y);
	}

	public Vector2D(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void addX(int f) {
		x += f;
	}

	public void addY(int f) {
		y += f;
	}

	public void setVector(Vector2D v) {
		this.x = v.x;
		this.y = v.y;
	}

	public void setVector(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
