package game.entities;

import game.graphics.Sprite;
import game.util.input.KeyHandler;
import game.util.input.MouseHandler;
import game.util.Vector2D;

import java.awt.*;

public abstract class Entity {

    protected Vector2D pos;
    protected Vector2D size;

    private HitBox hitBox;
    public boolean destroy;

    public Sprite sprite;

    public Entity(Vector2D pos, Vector2D size) {
        this.pos = pos;
        this.size = size;
        this.destroy = false;

        this.hitBox = new HitBox(this);
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setSprite(String imagePath) {
        this.sprite = new Sprite(imagePath, size.x, size.y);
    }

    public abstract void render(Graphics2D g);
    public abstract void input(MouseHandler mouse, KeyHandler key);
    public abstract void update();

    public boolean overlaps(Entity e) { return getHitBox().overlaps(e); }
    public HitBox getHitBox() { return hitBox; }
}
