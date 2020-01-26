package game.entities;

import game.util.LogSystem;

import java.awt.*;

public class HitBox {

    private Entity e;
    private int xOffset, yOffset, widthOffset, heightOffset;

    private Color color;

    public HitBox(Entity e) {
        this(e, 0, 0, 0, 0);
    }

    public HitBox(Entity e, int xOffset, int yOffset, int widthOffset, int heightOffset) {
        this.e = e;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.widthOffset = widthOffset;
        this.heightOffset = heightOffset;

        this.color = new Color(22, 255, 0);
    }

    public void setOffset (int xOffset, int yOffset, int widthOffset, int heightOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.widthOffset = widthOffset;
        this.heightOffset = heightOffset;
    }

    public Rectangle getBounds () {
        return new Rectangle(e.pos.x + xOffset, e.pos.y + yOffset, e.size.x + widthOffset, e.size.y + heightOffset);
    }

    public boolean overlaps (HitBox other) {
        return     getX() < other.getX() + other.getWidth()
                && getX() + getWidth() > other.getX()
                && getY() < other.getY() + other.getHeight()
                && getY() + getHeight() > other.getY();
    }

    public boolean overlaps (Entity e) {
        return overlaps(e.getHitBox());
    }

    public void render (Graphics2D g) {
        if (LogSystem.getInfoLevel() == LogSystem.ALL) {
            g.setColor(color);
            g.drawRect(getX(), getY(), getWidth(), getHeight());
        }
    }

    public void setColor(Color color) { this.color = color; }

    public int getHeightOffset() { return heightOffset; }
    public int getWidthOffset() { return widthOffset;}
    public int getxOffset() { return xOffset; }
    public int getyOffset() { return yOffset; }

    public int getX () { return e.pos.x + xOffset; }
    public int getY () { return e.pos.y + yOffset; }
    public int getWidth () { return e.size.x + widthOffset; }
    public int getHeight () { return e.size.y + heightOffset; }
}
