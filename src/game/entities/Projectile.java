package game.entities;

import game.entities.creatures.Enemy;
import game.scenes.SceneManager;
import game.util.Colors;
import game.util.input.KeyHandler;
import game.util.input.MouseHandler;
import game.util.Vector2D;

import java.awt.*;

public class Projectile extends Entity {

    private int dir;
    private final int speed = 5;

    private final static int AUTODESTRUCTION_TIME = 200;
    private int timer;

    private int damage;

    private Color projectileColor;

    public Projectile(Vector2D pos, Vector2D size, int dir, int damage) {
        super(pos, size);
        this.dir = dir;
        this.damage = damage;
        timer = 0;

        projectileColor = Colors.fixedRandomColor();
    }

    @Override
    public void render(Graphics2D g) {
        if(!destroy) {
            g.setColor(projectileColor);
            g.fillRect(pos.x, pos.y, size.x, size.y);
        }
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {

    }

    @Override
    public void update() {
        if(!destroy) {
            switch (dir) {
                case 0: pos.y -= speed; break;
                case 1: pos.y += speed; break;
                case 2: pos.x -= speed; break;
                case 3: pos.x += speed; break;
                default: break;
            }
            timer++;

            for (Entity e : SceneManager.currentScene.entities) {
                if(e instanceof Enemy && overlaps(e)) {
                    ((Enemy) e).damage(damage);
                    destroy = true;
                }
            }

            if (timer >= AUTODESTRUCTION_TIME) destroy = true;
        }
    }
}
